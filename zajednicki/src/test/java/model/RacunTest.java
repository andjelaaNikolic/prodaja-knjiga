package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RacunTest {

    Racun racun;
    Prodavac prodavac;
    Kupac kupac;

    @Mock
    ResultSet rs;

    @BeforeEach
    void setUp() throws Exception {
        racun = new Racun();
        prodavac = new Prodavac(1, "Pera", "Peric", "pera123", "sifra123", "pera@gmail.com");
        Mesto mesto = new Mesto(1, "Beograd");
        kupac = new Kupac(1, "Marko", "Markovic", "0611234567", mesto);
    }

    @AfterEach
    void tearDown() throws Exception {
        racun = null;
        prodavac = null;
        kupac = null;
    }


    @Test
    void testSetIdRacun() {
        racun.setIdRacun(5);
        assertEquals(5, racun.getIdRacun());
    }

    @Test
    void testSetIdRacunNula() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> racun.setIdRacun(0));
        assertEquals("ID računa mora biti veći od nule.", ex.getMessage());
    }

    @Test
    void testSetIdRacunNegativan() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> racun.setIdRacun(-1));
        assertEquals("ID računa mora biti veći od nule.", ex.getMessage());
    }


    @Test
    void testSetDatum() {
        Date datum = new Date();
        racun.setDatum(datum);
        assertEquals(datum, racun.getDatum());
    }

    @Test
    void testSetDatumUBuducnosti() {
        long jedanDan = 24 * 60 * 60 * 1000L;
        Date buducnost = new Date(System.currentTimeMillis() + jedanDan);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> racun.setDatum(buducnost));
        assertEquals("Datum ne sme biti u budućnosti.", ex.getMessage());
    }

    @Test
    void testSetDatumNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> racun.setDatum(null));
        assertEquals("Datum ne sme biti null.", ex.getMessage());
    }



    @Test
    void testSetUkupanIznos() {
        racun.setUkupanIznos(2500.50);
        assertEquals(2500.50, racun.getUkupanIznos());
    }

    @Test
    void testSetUkupanIznosNula() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> racun.setUkupanIznos(0));
        assertEquals("Ukupan iznos mora biti veći od nule.", ex.getMessage());
    }

    @Test
    void testSetUkupanIznosNegativan() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> racun.setUkupanIznos(-100));
        assertEquals("Ukupan iznos mora biti veći od nule.", ex.getMessage());
    }



    @Test
    void testSetProdavac() {
        racun.setProdavac(prodavac);
        assertEquals(prodavac, racun.getProdavac());
    }

    @Test
    void testSetProdavacNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> racun.setProdavac(null));
        assertEquals("Prodavac ne sme biti null.", ex.getMessage());
    }


    @Test
    void testSetKupac() {
        racun.setKupac(kupac);
        assertEquals(kupac, racun.getKupac());
    }

    @Test
    void testSetKupacNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> racun.setKupac(null));
        assertEquals("Kupac ne sme biti null.", ex.getMessage());
    }


    @Test
    void testVratiNazivTabele() {
        assertEquals("racun", racun.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("datum, ukupanIznos, prodavac, kupac", racun.vratiKoloneZaUbacivanje());
    }


    @Test
    void testVratiVrednostiZaUbacivanje() throws Exception {
        Date datum = new Date();
        Racun r = new Racun(datum, 1500.0, prodavac, kupac);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ocekivaniDatum = sdf.format(datum);

        String ocekivano = "'" + ocekivaniDatum + "', 1500.0, " + prodavac.getIdProdavac() + ", " + kupac.getIdKupac();

        assertEquals(ocekivano, r.vratiVrednostiZaUbacivanje());
    }


    @Test
    void testVratiPrimarniKljuc() {
        racun.setIdRacun(7);
        assertEquals("racun.idRacun=7", racun.vratiPrimarniKljuc());
    }


    @Test
    void testVratiVrednostiZaIzmenu() {
        Date datum = new Date();
        Racun r = new Racun(datum, 1500.0, prodavac, kupac);

        String ocekivano = "datum='" + datum + "', ukupanIznos=1500.0, prodavac=" + prodavac.getIdProdavac()
                + ", kupac=" + kupac.getIdKupac();

        assertEquals(ocekivano, r.vratiVrednostiZaIzmenu());
    }



    @Test
    void testVratiListuJedanRed() throws Exception {
        java.sql.Date sqlDatum = new java.sql.Date(new Date().getTime());

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("racun.idRacun")).thenReturn(1);
        when(rs.getDouble("racun.ukupanIznos")).thenReturn(1500.0);
        when(rs.getDate("racun.datum")).thenReturn(sqlDatum);
        when(rs.getInt("racun.prodavac")).thenReturn(1);
        when(rs.getInt("racun.kupac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Pera");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com");
        when(rs.getInt("kupac.mesto")).thenReturn(1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd");
        when(rs.getString("kupac.ime")).thenReturn("Marko");
        when(rs.getString("kupac.prezime")).thenReturn("Markovic");
        when(rs.getString("kupac.brojTelefona")).thenReturn("0611234567");

        List<OpstiDomenskiObjekat> lista = racun.vratiListu(rs);

        assertEquals(1, lista.size());
        Racun rezultat = (Racun) lista.get(0);
        assertEquals(1, rezultat.getIdRacun());
        assertEquals(1500.0, rezultat.getUkupanIznos());
        assertEquals("pera123", rezultat.getProdavac().getKorisnickoIme());
        assertEquals("Marko", rezultat.getKupac().getIme());
    }

    @Test
    void testVratiListuViseRedova() throws Exception {
        java.sql.Date sqlDatum = new java.sql.Date(new Date().getTime());

        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("racun.idRacun")).thenReturn(1, 2);
        when(rs.getDouble("racun.ukupanIznos")).thenReturn(1500.0, 2300.0);
        when(rs.getDate("racun.datum")).thenReturn(sqlDatum, sqlDatum);
        when(rs.getInt("racun.prodavac")).thenReturn(1, 1);
        when(rs.getInt("racun.kupac")).thenReturn(1, 1);
        when(rs.getString("prodavac.ime")).thenReturn("Pera");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com");
        when(rs.getInt("kupac.mesto")).thenReturn(1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd");
        when(rs.getString("kupac.ime")).thenReturn("Marko");
        when(rs.getString("kupac.prezime")).thenReturn("Markovic");
        when(rs.getString("kupac.brojTelefona")).thenReturn("0611234567");

        List<OpstiDomenskiObjekat> lista = racun.vratiListu(rs);

        assertEquals(2, lista.size());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<OpstiDomenskiObjekat> lista = racun.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }


    @Test
    void testVratiObjekatIzRS() throws Exception {
        java.sql.Date sqlDatum = new java.sql.Date(new Date().getTime());

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("racun.idRacun")).thenReturn(1);
        when(rs.getDouble("racun.ukupanIznos")).thenReturn(1500.0);
        when(rs.getDate("racun.datum")).thenReturn(sqlDatum);
        when(rs.getInt("racun.prodavac")).thenReturn(1);
        when(rs.getInt("racun.kupac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Pera");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com");
        when(rs.getInt("kupac.mesto")).thenReturn(1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd");
        when(rs.getString("kupac.ime")).thenReturn("Marko");
        when(rs.getString("kupac.prezime")).thenReturn("Markovic");
        when(rs.getString("kupac.brojTelefona")).thenReturn("0611234567");

        Racun rezultat = (Racun) racun.vratiObjekatIzRS(rs);

        assertNotNull(rezultat);
        assertEquals(1, rezultat.getIdRacun());
        assertEquals("pera123", rezultat.getProdavac().getKorisnickoIme());
        assertEquals("Marko", rezultat.getKupac().getIme());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        Racun rezultat = (Racun) racun.vratiObjekatIzRS(rs);

        assertNull(rezultat);
    }
}

