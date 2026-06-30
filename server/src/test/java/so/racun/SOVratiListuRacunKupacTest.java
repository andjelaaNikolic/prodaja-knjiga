package so.racun;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import db.repozitorijum.DBKonekcija;
import model.Kupac;
import model.Mesto;
import model.Racun;
import model.StavkaRacuna;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOVratiListuRacunKupacTest {

    private SOVratiListuRacunKupac so;

    @BeforeEach
    void setUp() {
        so = new SOVratiListuRacunKupac();
    }

    private Kupac napraviKupca() {
        Mesto m = new Mesto(1, "Beograd");
        return new Kupac(1, "Marko", "Marković", "0611111111", m);
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi(null));

        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.",ex.getMessage());
        
    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi("test"));

        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.",ex.getMessage());
        
    }

    @Test
    void testPredusloviOK() {
        assertDoesNotThrow(() -> so.preduslovi(new Racun()));
    }

    private SOVratiListuRacunKupac napraviSOSaMockom(
            Repozitorijum mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {

        DBKonekcija mockDB = mock(DBKonekcija.class);

        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDB);
        when(mockDB.getConnection()).thenReturn(mockConnection);

        SOVratiListuRacunKupac soMock = new SOVratiListuRacunKupac();

        Field f = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        f.setAccessible(true);
        f.set(soMock, mockBroker);

        return soMock;
    }

    @Test
    void testVratiListuRacunaSaStavkama() throws Exception {

        Kupac kupac = napraviKupca();

        Racun r = new Racun();
        r.setIdRacun(1);

        List<Racun> listaRacuna = new ArrayList<>();
        listaRacuna.add(r);

        StavkaRacuna s = new StavkaRacuna();
        List<StavkaRacuna> stavke = new ArrayList<>();
        stavke.add(s);

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection conn = mock(Connection.class);

        when(mockBroker.vratiSve(any(Racun.class), anyString()))
                .thenReturn(listaRacuna);


        when(mockBroker.vratiSve(any(StavkaRacuna.class), anyString()))
                .thenReturn(stavke);

        try (MockedStatic<DBKonekcija> mocked = mockStatic(DBKonekcija.class)) {

            SOVratiListuRacunKupac soMock = napraviSOSaMockom(
                    mockBroker,
                    conn,
                    mocked
            );

            soMock.preduslovi(new Racun());
            soMock.izvrsiOperaciju(new Racun(), kupac);

            List<Racun> rezultat = soMock.getRacuni();

            assertNotNull(rezultat);
            assertEquals(1, rezultat.size());
            assertEquals(1, rezultat.get(0).getStavke().size());

            verify(mockBroker, times(1)).vratiSve(any(Racun.class), anyString());
            verify(mockBroker, times(1)).vratiSve(any(StavkaRacuna.class), anyString());
        }
    }

    @Test
    void testVratiPraznuListu() throws Exception {

        Kupac kupac = napraviKupca();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        when(mockBroker.vratiSve(any(Racun.class), anyString()))
                .thenReturn(new ArrayList<>());

        try (MockedStatic<DBKonekcija> mocked = mockStatic(DBKonekcija.class)) {

            SOVratiListuRacunKupac soMock = napraviSOSaMockom(
                    mockBroker,
                    mock(Connection.class),
                    mocked
            );

            soMock.preduslovi(new Racun());
            soMock.izvrsiOperaciju(new Racun(), kupac);

            assertTrue(soMock.getRacuni().isEmpty());
        }
    }
}