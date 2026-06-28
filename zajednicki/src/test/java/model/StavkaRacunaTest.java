package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StavkaRacunaTest {

    StavkaRacuna stavka;
    Knjiga knjiga;
    Racun racun;

    @Mock
    ResultSet rs;

    @BeforeEach
    void setUp() throws Exception {
        stavka = new StavkaRacuna();
        Magacin magacin = new Magacin(1, "Magacin1", "Bulevar 1");
        knjiga = new Knjiga(1, "Naslov knjige", "Roman", 2020, 1500.0, 10, magacin);
        racun = new Racun();
        racun.setIdRacun(1);
    }

    @AfterEach
    void tearDown() throws Exception {
        stavka = null;
        knjiga = null;
        racun = null;
    }


    @Test
    void testSetRb() {
        stavka.setRb(3);
        assertEquals(3, stavka.getRb());
    }

    @Test
    void testSetRbNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setRb(0));
    }

    @Test
    void testSetRbNegativan() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setRb(-1));
    }


    @Test
    void testSetKolicina() {
        stavka.setKolicina(5);
        assertEquals(5, stavka.getKolicina());
    }

    @Test
    void testSetKolicinaNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setKolicina(0));
    }

    @Test
    void testSetKolicinaNegativna() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setKolicina(-1));
    }


    @Test
    void testSetKnjiga() {
        stavka.setKnjiga(knjiga);
        assertEquals(knjiga, stavka.getKnjiga());
    }

    @Test
    void testSetKnjigaNull() {
        assertThrows(NullPointerException.class, () -> stavka.setKnjiga(null));
    }


    @Test
    void testSetIznos() {
        stavka.setIznos(2500.0);
        assertEquals(2500.0, stavka.getIznos());
    }

    @Test
    void testSetIznosNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setIznos(0));
    }

    @Test
    void testSetIznosNegativan() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setIznos(-100));
    }


    @Test
    void testSetRacun() {
        stavka.setRacun(racun);
        assertEquals(racun, stavka.getRacun());
    }

    @Test
    void testSetRacunNull() {
        assertThrows(NullPointerException.class, () -> stavka.setRacun(null));
    }


    @Test
    void testSetJedinicnaCena() {
        stavka.setJedinicnaCena(1500.0);
        assertEquals(1500.0, stavka.getJedinicnaCena());
    }

    @Test
    void testSetJedinicnaCenaNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setJedinicnaCena(0));
    }

    @Test
    void testSetJedinicnaCenaNegativna() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setJedinicnaCena(-50));
    }


    @Test
    void testToString() {
        StavkaRacuna sr = new StavkaRacuna(1, 2, knjiga, 3000.0, racun, 1500.0);
        String st = sr.toString();

        assertTrue(st.contains("rb=1"));
        assertTrue(st.contains("kolicina=2"));
        assertTrue(st.contains("iznos=3000.0"));
    }


    @Test
    void testEqualsIstiObjekat() {
        assertTrue(stavka.equals(stavka));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(stavka.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(stavka.equals(new String()));
    }

    @Test
    void testEqualsJednakaKnjiga() {
        StavkaRacuna sr1 = new StavkaRacuna(1, 2, knjiga, 3000.0, racun, 1500.0);
        StavkaRacuna sr2 = new StavkaRacuna(9, 7, knjiga, 9999.0, racun, 1500.0);

        assertTrue(sr1.equals(sr2));
    }

    @Test
    void testEqualsRazlicitaKnjiga() throws Exception {
        Magacin magacin2 = new Magacin(2, "Magacin2", "Bulevar 2");
        Knjiga knjiga2 = new Knjiga(2, "Druga knjiga", "Drama", 2021, 2000.0, 5, magacin2);

        StavkaRacuna sr1 = new StavkaRacuna(1, 2, knjiga, 3000.0, racun, 1500.0);
        StavkaRacuna sr2 = new StavkaRacuna(1, 2, knjiga2, 3000.0, racun, 2000.0);

        assertFalse(sr1.equals(sr2));
    }

    @Test
    void testEqualsObaBezKnjige() {
        StavkaRacuna sr1 = new StavkaRacuna();
        StavkaRacuna sr2 = new StavkaRacuna();

        assertTrue(sr1.equals(sr2));
    }



    @Test
    void testVratiNazivTabele() {
        assertEquals("stavka_racuna", stavka.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("racun,rb,kolicina,knjiga,iznos,jedinicnaCena", stavka.vratiKoloneZaUbacivanje());
    }


    @Test
    void testVratiVrednostiZaUbacivanje() {
        StavkaRacuna sr = new StavkaRacuna(1, 2, knjiga, 3000.0, racun, 1500.0);

        String ocekivano = racun.getIdRacun() + ", 1, 2, " + knjiga.getIdKnjiga() + ", 3000.0, 1500.0";

        assertEquals(ocekivano, sr.vratiVrednostiZaUbacivanje());
    }


    @Test
    void testVratiVrednostiZaIzmenu() {
        StavkaRacuna sr = new StavkaRacuna(1, 2, knjiga, 3000.0, racun, 1500.0);

        String ocekivano = "racun = " + racun.getIdRacun() + ", rb = 1, kolicina = 2, knjiga = "
                + knjiga.getIdKnjiga() + ", iznos = 3000.0, jedinicnaCena = 1500.0";

        assertEquals(ocekivano, sr.vratiVrednostiZaIzmenu());
    }


    @Test
    void testVratiPrimarniKljuc() {
        StavkaRacuna sr = new StavkaRacuna(1, 2, knjiga, 3000.0, racun, 1500.0);

        String ocekivano = "racun = " + racun.getIdRacun() + " AND rb = 1";

        assertEquals(ocekivano, sr.vratiPrimarniKljuc());
    }


    @Test
    void testVratiListuJedanRed() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("stavka_racuna.rb")).thenReturn(1);
        when(rs.getInt("stavka_racuna.kolicina")).thenReturn(2);
        when(rs.getDouble("stavka_racuna.iznos")).thenReturn(3000.0);
        when(rs.getInt("stavka_racuna.knjiga")).thenReturn(1);
        when(rs.getString("knjiga.naslov")).thenReturn("Naslov knjige");
        when(rs.getString("knjiga.zanr")).thenReturn("Roman");
        when(rs.getDouble("knjiga.cena")).thenReturn(1500.0);
        when(rs.getInt("knjiga.kolicina")).thenReturn(10);
        when(rs.getInt("knjiga.magacin")).thenReturn(1);
        when(rs.getString("magacin.naziv")).thenReturn("Magacin1");
        when(rs.getString("magacin.adresa")).thenReturn("Bulevar 1");
        when(rs.getInt("knjiga.godinaIzdanja")).thenReturn(2020);
        when(rs.getInt("stavka_racuna.racun")).thenReturn(5);

        List<OpstiDomenskiObjekat> lista = stavka.vratiListu(rs);

        assertEquals(1, lista.size());
        StavkaRacuna rezultat = (StavkaRacuna) lista.get(0);
        assertEquals(1, rezultat.getRb());
        assertEquals(2, rezultat.getKolicina());
        assertEquals(3000.0, rezultat.getIznos());
        assertEquals(1, rezultat.getKnjiga().getIdKnjiga());
        assertEquals(5, rezultat.getRacun().getIdRacun());
        assertEquals(1500.0, rezultat.getJedinicnaCena());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<OpstiDomenskiObjekat> lista = stavka.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }



    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("stavka_racuna.rb")).thenReturn(1);
        when(rs.getInt("stavka_racuna.kolicina")).thenReturn(2);
        when(rs.getDouble("stavka_racuna.iznos")).thenReturn(3000.0);
        when(rs.getInt("stavka_racuna.knjiga")).thenReturn(1);
        when(rs.getString("knjiga.naslov")).thenReturn("Naslov knjige");
        when(rs.getString("knjiga.zanr")).thenReturn("Roman");
        when(rs.getDouble("knjiga.cena")).thenReturn(1500.0);
        when(rs.getInt("knjiga.godinaIzdanja")).thenReturn(2020);
        when(rs.getInt("knjiga.kolicina")).thenReturn(10);
        when(rs.getInt("knjiga.magacin")).thenReturn(1);
        when(rs.getString("magacin.naziv")).thenReturn("Magacin1");
        when(rs.getString("magacin.adresa")).thenReturn("Bulevar 1");
        when(rs.getInt("stavka_racuna.racun")).thenReturn(5);

        StavkaRacuna rezultat = (StavkaRacuna) stavka.vratiObjekatIzRS(rs);

        assertNotNull(rezultat);
        assertEquals(1, rezultat.getRb());
        assertEquals(1, rezultat.getKnjiga().getIdKnjiga());
        assertEquals(5, rezultat.getRacun().getIdRacun());
        assertEquals(1500.0, rezultat.getJedinicnaCena());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        StavkaRacuna rezultat = (StavkaRacuna) stavka.vratiObjekatIzRS(rs);

        assertNull(rezultat);
    }
}