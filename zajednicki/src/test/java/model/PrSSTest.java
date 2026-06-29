package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PrSSTest {

    PrSS prss;

    @Mock
    ResultSet rs;

    @BeforeEach
    void setUp() throws Exception {
        prss = new PrSS();
    }

    @AfterEach
    void tearDown() throws Exception {
        prss = null;
    }


    @Test
    void testSetStrSprema() {
        StrSprema ss = new StrSprema(1, "Visoka strucna sprema");
        prss.setStrSprema(ss);
        assertEquals(ss, prss.getStrSprema());
    }

    @Test
    void testSetStrSpremaNull() {
        assertThrows(NullPointerException.class, () -> prss.setStrSprema(null));
    }


    @Test
    void testSetProdavac() {
        Prodavac p = new Prodavac();
        p.setIme("Pera");
        p.setPrezime("Peric");
        p.setKorisnickoIme("pera123");
        p.setSifra("sifra123");
        p.setEmail("pera@gmail.com");
        prss.setProdavac(p);
        assertEquals(p, prss.getProdavac());
    }

    @Test
    void testSetProdavacNull() {
        assertThrows(NullPointerException.class, () -> prss.setProdavac(null));
    }


    @Test
    void testSetDatumSticanja() {
        Date datum = new Date();
        prss.setDatumSticanja(datum);
        assertEquals(datum, prss.getDatumSticanja());
    }

    @Test
    void testSetDatumSticanjaNull() {
        assertThrows(NullPointerException.class, () -> prss.setDatumSticanja(null));
    }

    @Test
    void testSetDatumSticanjaUBuducnosti() {
        long jedanDan = 24 * 60 * 60 * 1000L;
        Date buducnost = new Date(System.currentTimeMillis() + jedanDan);

        assertThrows(IllegalArgumentException.class, () -> prss.setDatumSticanja(buducnost));
    }

    @Test
    void testSetDatumSticanjaDanas() {
        Date danas = new Date();
        assertDoesNotThrow(() -> prss.setDatumSticanja(danas));
    }


    @Test
    void testSetInstitucija() {
    	prss.setInstitucija("FON");
        assertEquals("FON", prss.getInstitucija());
    }

    @Test
    void testSetInstitucijaNull() {
        assertThrows(NullPointerException.class, () -> prss.setInstitucija(null));
    }

    @Test
    void testSetInstitucijaBlank() {
        assertThrows(IllegalArgumentException.class, () -> prss.setInstitucija(" "));
    }

    @Test
    void testSetInstitucijaPredugacka() {
        assertThrows(IllegalArgumentException.class, () -> prss.setInstitucija("A".repeat(51)));
    }


    @Test
    void testSetGrad() {
    	prss.setGrad("Beograd");
        assertEquals("Beograd", prss.getGrad());
    }

    @Test
    void testSetGradNull() {
        assertThrows(NullPointerException.class, () -> prss.setGrad(null));
    }

    @Test
    void testSetGradBlank() {
        assertThrows(IllegalArgumentException.class, () -> prss.setGrad(" "));
    }

    @Test
    void testSetGradPredugacak() {
        assertThrows(IllegalArgumentException.class, () -> prss.setGrad("A".repeat(51)));
    }

    @Test
    void testSetGradSaBrojevima() {
        assertThrows(IllegalArgumentException.class, () -> prss.setGrad("Beograd1"));
    }


    @Test
    void testToString() {
        StrSprema ss = new StrSprema(1, "Visoka strucna sprema");
        prss.setStrSprema(ss);
        prss.setInstitucija("FON");
        prss.setGrad("Beograd");

        String st = prss.toString();

        assertTrue(st.contains("FON"));
        assertTrue(st.contains("Beograd"));
    }


    @Test
    void testEqualsIstiObjekat() {
        assertTrue(prss.equals(prss));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(prss.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(prss.equals(new String()));
    }

    @ParameterizedTest
    @CsvSource({
        "FON, Beograd, FON, Beograd, true",
        "FON, Beograd, ETF, Beograd, false",
        "FON, Beograd, FON, Novi Sad, false"
    })
    void testEqualsInstitucijaIGrad(String institucija1, String grad1,
            String institucija2, String grad2, boolean ocekivanoJednako) {
        StrSprema ss = new StrSprema(1, "Visoka strucna sprema");
        Prodavac p = new Prodavac();
        p.setKorisnickoIme("pera123");
        Date datum = new Date();
 
        prss.setStrSprema(ss);
        prss.setProdavac(p);
        prss.setDatumSticanja(datum);
        prss.setInstitucija(institucija1);
        prss.setGrad(grad1);
 
        PrSS ps2 = new PrSS();
        ps2.setStrSprema(ss);
        ps2.setProdavac(p);
        ps2.setDatumSticanja(datum);
        ps2.setInstitucija(institucija2);
        ps2.setGrad(grad2);
 
        assertEquals(ocekivanoJednako, prss.equals(ps2));
    }
 
    @Test
    void testEqualsRazlicitaStrSprema() {
        StrSprema ss1 = new StrSprema(1, "Visoka strucna sprema");
        StrSprema ss2 = new StrSprema(2, "Osnovna strucna sprema");
        Prodavac p = new Prodavac();
        p.setKorisnickoIme("pera123");
        Date datum = new Date();
 
        prss.setStrSprema(ss1);
        prss.setProdavac(p);
        prss.setDatumSticanja(datum);
        prss.setInstitucija("FON");
        prss.setGrad("Beograd");
 
        PrSS ps2 = new PrSS();
        ps2.setStrSprema(ss2);
        ps2.setProdavac(p);
        ps2.setDatumSticanja(datum);
        ps2.setInstitucija("FON");
        ps2.setGrad("Beograd");
 
        assertFalse(prss.equals(ps2));
    }
 
    @Test
    void testEqualsRazlicitProdavac() {
        StrSprema ss = new StrSprema(1, "Visoka strucna sprema");
        Prodavac p1 = new Prodavac();
        p1.setKorisnickoIme("pera123");
        Prodavac p2 = new Prodavac();
        p2.setKorisnickoIme("mika456");
        Date datum = new Date();
 
        prss.setStrSprema(ss);
        prss.setProdavac(p1);
        prss.setDatumSticanja(datum);
        prss.setInstitucija("FON");
        prss.setGrad("Beograd");
 
        PrSS ps2 = new PrSS();
        ps2.setStrSprema(ss);
        ps2.setProdavac(p2);
        ps2.setDatumSticanja(datum);
        ps2.setInstitucija("FON");
        ps2.setGrad("Beograd");
 
        assertFalse(prss.equals(ps2));
    }
 
    @Test
    void testEqualsIstoKorisnickoImeProdavacaJednako() {
        StrSprema ss = new StrSprema(1, "Visoka strucna sprema");
        Prodavac p1 = new Prodavac();
        p1.setKorisnickoIme("pera123");
        Prodavac p2 = new Prodavac();
        p2.setKorisnickoIme("pera123");
        Date datum = new Date();
 
        prss.setStrSprema(ss);
        prss.setProdavac(p1);
        prss.setDatumSticanja(datum);
        prss.setInstitucija("FON");
        prss.setGrad("Beograd");
 
        PrSS ps2 = new PrSS();
        ps2.setStrSprema(ss);
        ps2.setProdavac(p2);
        ps2.setDatumSticanja(datum);
        ps2.setInstitucija("FON");
        ps2.setGrad("Beograd");
 
        assertTrue(prss.equals(ps2));
    }
 
    @Test
    void testEqualsRazlicitDatumSticanja() {
        StrSprema ss = new StrSprema(1, "Visoka strucna sprema");
        Prodavac p = new Prodavac();
        p.setKorisnickoIme("pera123");
        Date datum1 = new Date();
        Date datum2 = new Date(datum1.getTime() - 24L * 60 * 60 * 1000); 
 
        prss.setStrSprema(ss);
        prss.setProdavac(p);
        prss.setDatumSticanja(datum1);
        prss.setInstitucija("FON");
        prss.setGrad("Beograd");
 
        PrSS ps2 = new PrSS();
        ps2.setStrSprema(ss);
        ps2.setProdavac(p);
        ps2.setDatumSticanja(datum2);
        ps2.setInstitucija("FON");
        ps2.setGrad("Beograd");
 
        assertFalse(prss.equals(ps2));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("prss", prss.vratiNazivTabele());
    }


    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("strSprema,prodavac,datumSticanja,institucija,grad", prss.vratiKoloneZaUbacivanje());
    }


    @Test
    void testVratiPrimarniKljuc() {
        StrSprema ss = new StrSprema(1, "Visoka strucna sprema");
        Prodavac p = new Prodavac();
        p.setIdProdavac(1);
        p.setKorisnickoIme("pera123");

        prss.setStrSprema(ss);
        prss.setProdavac(p);

        assertEquals("strSprema=1 AND prodavac=1", prss.vratiPrimarniKljuc());
    }


    @Test
    void testVratiListuJedanRed() throws Exception {
        java.sql.Date sqlDatum = new java.sql.Date(new Date().getTime());

        when(rs.next()).thenReturn(true, false);
        when(rs.getString("prss.institucija")).thenReturn("FON");
        when(rs.getString("prss.grad")).thenReturn("Beograd");
        when(rs.getDate("prss.datumSticanja")).thenReturn(sqlDatum);
        when(rs.getInt("prss.strSprema")).thenReturn(1);
        when(rs.getString("str_sprema.stepen")).thenReturn("Visoka strucna sprema");
        when(rs.getInt("prss.prodavac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Pera");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com");

        List<OpstiDomenskiObjekat> lista = prss.vratiListu(rs);

        assertEquals(1, lista.size());
        PrSS rezultat = (PrSS) lista.get(0);
        assertEquals("FON", rezultat.getInstitucija());
        assertEquals("Beograd", rezultat.getGrad());
        assertEquals("pera123", rezultat.getProdavac().getKorisnickoIme());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<OpstiDomenskiObjekat> lista = prss.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }


    @Test
    void testVratiObjekatIzRS() throws Exception {
        java.sql.Date sqlDatum = new java.sql.Date(new Date().getTime());

        when(rs.next()).thenReturn(true, false);
        when(rs.getString("prss.institucija")).thenReturn("FON");
        when(rs.getString("prss.grad")).thenReturn("Beograd");
        when(rs.getDate("prss.datumSticanja")).thenReturn(sqlDatum);
        when(rs.getInt("prss.strSprema")).thenReturn(1);
        when(rs.getString("str_sprema.stepen")).thenReturn("Visoka strucna sprema");
        when(rs.getInt("prss.prodavac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Pera");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com");

        PrSS rezultat = (PrSS) prss.vratiObjekatIzRS(rs);

        assertNotNull(rezultat);
        assertEquals("FON", rezultat.getInstitucija());
        assertEquals("Beograd", rezultat.getGrad());
        assertEquals("pera123", rezultat.getProdavac().getKorisnickoIme());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        PrSS rezultat = (PrSS) prss.vratiObjekatIzRS(rs);

        assertNull(rezultat);
    }
}
