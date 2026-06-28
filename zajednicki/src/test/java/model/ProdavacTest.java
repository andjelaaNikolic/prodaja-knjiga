package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdavacTest {

    Prodavac prodavac;

    @Mock
    ResultSet rs;

    @BeforeEach
    void setUp() throws Exception {
        prodavac = new Prodavac();
    }

    @AfterEach
    void tearDown() throws Exception {
        prodavac = null;
    }


    @Test
    void testKonstruktorSaIdom() {
        Prodavac p = new Prodavac(1, "Pera", "Peric", "pera123", "sifra123", "pera@gmail.com");
        assertEquals(1, p.getIdProdavac());
        assertEquals("Pera", p.getIme());
        assertEquals("Peric", p.getPrezime());
        assertEquals("pera123", p.getKorisnickoIme());
        assertEquals("sifra123", p.getSifra());
        assertEquals("pera@gmail.com", p.getEmail());
        assertNotNull(p.getPrss());
        assertTrue(p.getPrss().isEmpty());
    }

    @Test
    void testKonstruktorBezIda() {
        Prodavac p = new Prodavac("Pera", "Peric", "pera123", "sifra123", "pera@gmail.com");
        assertEquals("Pera", p.getIme());
        assertEquals("Peric", p.getPrezime());
        assertEquals("pera123", p.getKorisnickoIme());
        assertEquals("sifra123", p.getSifra());
        assertEquals("pera@gmail.com", p.getEmail());
        assertNotNull(p.getPrss());
    }


    @Test
    void testSetIdProdavac() {
        prodavac.setIdProdavac(5);
        assertEquals(5, prodavac.getIdProdavac());
    }

    @Test
    void testSetIdProdavacNula() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setIdProdavac(0));
    }

    @Test
    void testSetIdProdavacNegativan() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setIdProdavac(-1));
    }


    @Test
    void testSetIme() {
        prodavac.setIme("Pera");
        assertEquals("Pera", prodavac.getIme());
    }

    @Test
    void testSetImeSaSlovimaSrpske() {
        prodavac.setIme("Đorđe Šćepan");
        assertEquals("Đorđe Šćepan", prodavac.getIme());
    }

    @Test
    void testSetImeNull() {
        assertThrows(NullPointerException.class, () -> prodavac.setIme(null));
    }

    @Test
    void testSetImeBlank() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setIme(" "));
    }

    @Test
    void testSetImePredugacko() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setIme("A".repeat(51)));
    }

    @Test
    void testSetImeSaBrojevima() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setIme("Pera1"));
    }


    @Test
    void testSetPrezime() {
        prodavac.setPrezime("Peric");
        assertEquals("Peric", prodavac.getPrezime());
    }

    @Test
    void testSetPrezimeNull() {
        assertThrows(NullPointerException.class, () -> prodavac.setPrezime(null));
    }

    @Test
    void testSetPrezimeBlank() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setPrezime(" "));
    }

    @Test
    void testSetPrezimePredugacko() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setPrezime("A".repeat(51)));
    }

    @Test
    void testSetPrezimeSaBrojevima() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setPrezime("Peric1"));
    }


    @Test
    void testSetKorisnickoIme() {
        prodavac.setKorisnickoIme("pera123");
        assertEquals("pera123", prodavac.getKorisnickoIme());
    }

    @Test
    void testSetKorisnickoImeNull() {
        assertThrows(NullPointerException.class, () -> prodavac.setKorisnickoIme(null));
    }

    @Test
    void testSetKorisnickoImeBlank() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setKorisnickoIme(" "));
    }

    @Test
    void testSetKorisnickoImePredugacko() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setKorisnickoIme("a".repeat(51)));
    }


    @Test
    void testSetSifra() {
        prodavac.setSifra("sifra123");
        assertEquals("sifra123", prodavac.getSifra());
    }

    @Test
    void testSetSifraNull() {
        assertThrows(NullPointerException.class, () -> prodavac.setSifra(null));
    }

    @Test
    void testSetSifraBlank() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setSifra(" "));
    }

    @Test
    void testSetSifraPredugacka() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setSifra("a".repeat(51)));
    }


    @Test
    void testSetEmail() {
        prodavac.setEmail("pera@gmail.com");
        assertEquals("pera@gmail.com", prodavac.getEmail());
    }

    @Test
    void testSetEmailNull() {
        assertThrows(NullPointerException.class, () -> prodavac.setEmail(null));
    }

    @Test
    void testSetEmailBlank() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail(" "));
    }

    @Test
    void testSetEmailPredugacak() {
        String predugacak = "a".repeat(45) + "@gmail.com"; // duzi od 50 karaktera
        assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail(predugacak));
    }

    @Test
    void testSetEmailNevalidanFormat() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail("peragmail.com"));
    }

    @Test
    void testSetEmailBezDomena() {
        assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail("pera@"));
    }


    @Test
    void testSetPrss() {
        List<PrSS> lista = new ArrayList<>();
        prodavac.setPrss(lista);
        assertEquals(lista, prodavac.getPrss());
    }

    @Test
    void testSetPrssNull() {
        assertThrows(NullPointerException.class, () -> prodavac.setPrss(null));
    }


    @Test
    void testToString() {
        prodavac.setKorisnickoIme("pera123");
        assertEquals("pera123", prodavac.toString());
    }


    @Test
    void testEqualsIstiObjekat() {
        assertTrue(prodavac.equals(prodavac));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(prodavac.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(prodavac.equals(new String()));
    }

    @Test
    void testEqualsJednaki() {
        prodavac.setIme("Pera");
        prodavac.setPrezime("Peric");
        prodavac.setKorisnickoIme("pera123");
        prodavac.setSifra("sifra123");
        prodavac.setEmail("pera@gmail.com");

        Prodavac p2 = new Prodavac();
        p2.setIme("Pera");
        p2.setPrezime("Peric");
        p2.setKorisnickoIme("pera123");
        p2.setSifra("sifra456");
        p2.setEmail("pera@gmail.com");

        assertTrue(prodavac.equals(p2));
    }

    @Test
    void testEqualsRazliciti() {
        prodavac.setIme("Pera");
        prodavac.setPrezime("Peric");
        prodavac.setKorisnickoIme("pera123");
        prodavac.setSifra("sifra123");
        prodavac.setEmail("pera@gmail.com");

        Prodavac p2 = new Prodavac();
        p2.setIme("Mika");
        p2.setPrezime("Mikic");
        p2.setKorisnickoIme("mika456");
        p2.setSifra("sifra123");
        p2.setEmail("mika@gmail.com");

        assertFalse(prodavac.equals(p2));
    }


    @Test
    void testVratiNazivTabele() {
        assertEquals("prodavac", prodavac.vratiNazivTabele());
    }


    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("ime,prezime,korisnickoIme,sifra,email", prodavac.vratiKoloneZaUbacivanje());
    }


    @Test
    void testVratiVrednostiZaUbacivanje() {
        prodavac.setIme("Pera");
        prodavac.setPrezime("Peric");
        prodavac.setKorisnickoIme("pera123");
        prodavac.setSifra("sifra123");
        prodavac.setEmail("pera@gmail.com");

        assertEquals("'Pera','Peric','pera123','sifra123','pera@gmail.com'",
                prodavac.vratiVrednostiZaUbacivanje());
    }


    @Test
    void testVratiPrimarniKljuc() {
        prodavac.setIdProdavac(7);
        assertEquals("prodavac.idProdavac=7", prodavac.vratiPrimarniKljuc());
    }


    @Test
    void testVratiVrednostiZaIzmenu() {
        prodavac.setIme("Pera");
        prodavac.setPrezime("Peric");
        prodavac.setKorisnickoIme("pera123");
        prodavac.setSifra("sifra123");
        prodavac.setEmail("pera@gmail.com");

        assertEquals("ime='Pera', prezime='Peric', korisnickoIme='pera123', sifra='sifra123', email='pera@gmail.com'",
                prodavac.vratiVrednostiZaIzmenu());
    }


    @Test
    void testVratiListuJedanRed() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("prodavac.idProdavac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Pera");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com");

        List<OpstiDomenskiObjekat> lista = prodavac.vratiListu(rs);

        assertEquals(1, lista.size());
        Prodavac rezultat = (Prodavac) lista.get(0);
        assertEquals(1, rezultat.getIdProdavac());
        assertEquals("Pera", rezultat.getIme());
        assertEquals("pera123", rezultat.getKorisnickoIme());
    }

    @Test
    void testVratiListuViseRedova() throws Exception {
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("prodavac.idProdavac")).thenReturn(1, 2);
        when(rs.getString("prodavac.ime")).thenReturn("Pera", "Mika");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric", "Mikic");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123", "mika456");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123", "sifra456");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com", "mika@gmail.com");

        List<OpstiDomenskiObjekat> lista = prodavac.vratiListu(rs);

        assertEquals(2, lista.size());
    }

    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        List<OpstiDomenskiObjekat> lista = prodavac.vratiListu(rs);

        assertNotNull(lista);
        assertEquals(0, lista.size());
    }


    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("prodavac.idProdavac")).thenReturn(1);
        when(rs.getString("prodavac.ime")).thenReturn("Pera");
        when(rs.getString("prodavac.prezime")).thenReturn("Peric");
        when(rs.getString("prodavac.korisnickoIme")).thenReturn("pera123");
        when(rs.getString("prodavac.sifra")).thenReturn("sifra123");
        when(rs.getString("prodavac.email")).thenReturn("pera@gmail.com");

        Prodavac rezultat = (Prodavac) prodavac.vratiObjekatIzRS(rs);

        assertNotNull(rezultat);
        assertEquals(1, rezultat.getIdProdavac());
        assertEquals("pera123", rezultat.getKorisnickoIme());
    }

    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);

        Prodavac rezultat = (Prodavac) prodavac.vratiObjekatIzRS(rs);

        assertNull(rezultat);
    }
}
