package so.racun;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import db.repozitorijum.DBKonekcija;
import model.Knjiga;
import model.Kupac;
import model.Magacin;
import model.Mesto;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOKreirajRacunTest {

    private SOKreirajRacun so;

    @BeforeEach
    void setUp() {
        so = new SOKreirajRacun();
    }

    @AfterEach
    void tearDown() {
        so = null;
    }

    private Racun napraviRacun() {

        Mesto mesto = new Mesto(1, "Beograd");

        Kupac k = new Kupac(1, "Marko", "Marković", "0611111111", mesto);
        Prodavac p = new Prodavac(1, "Pera", "Peric", "perap", "pera123", "pera@gmail.com");

        Magacin magacin = new Magacin(1, "Magacin1", "Jove Ilica 1");

        Knjiga knjiga = new Knjiga(1, "Na Drini cuprija", "Roman", 1945, 1000.0, 500, magacin);

        StavkaRacuna s1 = new StavkaRacuna();
        s1.setKnjiga(knjiga);
        s1.setKolicina(1);
        s1.setJedinicnaCena(1000);
        s1.setIznos(1000);

        List<StavkaRacuna> stavke = new ArrayList<>();
        stavke.add(s1);

        Racun r = new Racun();
        r.setIdRacun(1);
        r.setDatum(new Date());
        r.setKupac(k);
        r.setProdavac(p);
        r.setUkupanIznos(1000);
        r.setStavke(stavke);

        return r;
    }

    @Test
    void testUspesnoPocetno() {
        assertFalse(so.isUspesno());
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi(null));

        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.", ex.getMessage());
    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi("test"));
        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.", ex.getMessage());

    }

    private SOKreirajRacun napraviSOSaMockom(
            Repozitorijum mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {

        DBKonekcija mockDB = mock(DBKonekcija.class);

        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDB);
        when(mockDB.getConnection()).thenReturn(mockConnection);

        SOKreirajRacun soMock = new SOKreirajRacun();

        Field f = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        f.setAccessible(true);
        f.set(soMock, mockBroker);

        return soMock;
    }

    /**
     * preduslovi() i izvrsiOperaciju() svaki pozivaju
     * DBKonekcija.getInstance().getConnection().createStatement().executeQuery(...)
     * NEZAVISNO — svaki put nastaje nov poziv st.executeQuery(...), pa svaki
     * put treba vratiti SVOJ ResultSet mock. Ako se isti ResultSet mock deli
     * između preduslovi() i izvrsiOperaciju() (npr. preko
     * when(rs.next()).thenReturn(false, true)), Mockito nakon iscrpljivanja
     * liste ponavlja POSLEDNJU vrednost zauvek — pa druga while(rs.next())
     * petlja (u izvrsiOperaciju) nikad ne dobija false i postaje beskonačna,
     * što je izazivalo OutOfMemoryError u test run-u.
     *
     * Rešenje: dva odvojena ResultSet mock-a, vraćena redom preko
     * thenReturn(rsPreduslovi, rsIzvrsi) na samom executeQuery pozivu.
     */
    @Test
    void testKreirajRacun() throws Exception {

        Racun r = napraviRacun();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection conn = mock(Connection.class);
        Statement st = mock(Statement.class);

        // ResultSet za preduslovi(): petlja while(rs.next()) treba odmah da stane -> next() = false
        ResultSet rsPreduslovi = mock(ResultSet.class);
        when(rsPreduslovi.next()).thenReturn(false);

        // ResultSet za izvrsiOperaciju(): jedna iteracija sa idRacun, pa stop
        ResultSet rsIzvrsi = mock(ResultSet.class);
        when(rsIzvrsi.next()).thenReturn(true, false);
        when(rsIzvrsi.getInt("racun.idRacun")).thenReturn(10);

        when(conn.createStatement()).thenReturn(st);
        // Prvi poziv executeQuery (iz preduslovi) -> rsPreduslovi
        // Drugi poziv executeQuery (iz izvrsiOperaciju) -> rsIzvrsi
        when(st.executeQuery(anyString())).thenReturn(rsPreduslovi, rsIzvrsi);

        try (MockedStatic<DBKonekcija> mocked = mockStatic(DBKonekcija.class)) {

            SOKreirajRacun soMock = napraviSOSaMockom(
                    mockBroker,
                    conn,
                    mocked
            );

            soMock.preduslovi(r);
            soMock.izvrsiOperaciju(r, null);

            assertTrue(soMock.isUspesno());

            verify(mockBroker, times(1)).dodaj(r);
            verify(mockBroker, times(r.getStavke().size())).dodaj(any(StavkaRacuna.class));
        }
    }

    @Test
    void testKreirajRacunGreska() throws Exception {

        Racun r = napraviRacun();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection conn = mock(Connection.class);
        Statement st = mock(Statement.class);

        // Račun "već postoji": preduslovi() mora pronaći tačno onoliko
        // poklapajućih stavki koliko ih ima u r.getStavke() (ovde 1), pa
        // next() vraća true tačno jednom, pa false da petlja stane.
        ResultSet rsPreduslovi = mock(ResultSet.class);
        when(rsPreduslovi.next()).thenReturn(true, false);
        when(rsPreduslovi.getString("k.naslov")).thenReturn(
                r.getStavke().get(0).getKnjiga().getNaslov());
        when(rsPreduslovi.getInt("sr.kolicina")).thenReturn(
                r.getStavke().get(0).getKolicina());
        when(rsPreduslovi.getString("sr.jedinicnaCena")).thenReturn(
                String.valueOf(r.getStavke().get(0).getJedinicnaCena()));
        when(rsPreduslovi.getString("sr.iznos")).thenReturn(
                String.valueOf(r.getStavke().get(0).getIznos()));

        when(conn.createStatement()).thenReturn(st);
        when(st.executeQuery(anyString())).thenReturn(rsPreduslovi);

        try (MockedStatic<DBKonekcija> mocked = mockStatic(DBKonekcija.class)) {

            SOKreirajRacun soMock = napraviSOSaMockom(
                    mockBroker,
                    conn,
                    mocked
            );

            soMock.preduslovi(r);
            soMock.izvrsiOperaciju(r, null);

            assertFalse(soMock.isUspesno());
            verify(mockBroker, never()).dodaj(any());
        }
    }
}