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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    void testSetIdProdavac() {
        prodavac.setIdProdavac(5);
        assertEquals(5, prodavac.getIdProdavac());
    }

    @Test
    void testSetIdProdavacNula() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setIdProdavac(0));
        assertEquals("ID prodavca mora biti veći od nule.", ex.getMessage());
    }

    @Test
    void testSetIdProdavacNegativan() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setIdProdavac(-1));
        assertEquals("ID prodavca mora biti veći od nule.", ex.getMessage());
    }


    @Test
    void testSetIme() {
        prodavac.setIme("Pera");
        assertEquals("Pera", prodavac.getIme());
    }

    @Test
    void testSetImeSaSrpskimSlovima() {
        prodavac.setIme("Đorđe");
        assertEquals("Đorđe", prodavac.getIme());
    }

    @Test
    void testSetImeNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> prodavac.setIme(null));
        assertEquals("Ime ne sme biti null.", ex.getMessage());
    }

    @Test
    void testSetImeBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setIme(" "));
        assertEquals("Ime ne sme biti prazno.", ex.getMessage());
    }

    @Test
    void testSetImePredugacko() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setIme("A".repeat(51)));
        assertEquals("Ime ne sme imati više od 50 karaktera.", ex.getMessage());
    }

    @Test
    void testSetImeSaBrojevima() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setIme("Pera1"));
        assertEquals("Ime sme sadržati samo slova.", ex.getMessage());
    }


    @Test
    void testSetPrezime() {
        prodavac.setPrezime("Peric");
        assertEquals("Peric", prodavac.getPrezime());
    }

    @Test
    void testSetPrezimeNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> prodavac.setPrezime(null));
        assertEquals("Prezime ne sme biti null.", ex.getMessage());
    }

    @Test
    void testSetPrezimeBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setPrezime(" "));
        assertEquals("Prezime ne sme biti prazno.", ex.getMessage());
    }

    @Test
    void testSetPrezimePredugacko() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setPrezime("A".repeat(51)));
        assertEquals("Prezime ne sme imati više od 50 karaktera.", ex.getMessage());
    }

    @Test
    void testSetPrezimeSaBrojevima() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setPrezime("Peric1"));
        assertEquals("Prezime sme sadržati samo slova.", ex.getMessage());
    }


    @Test
    void testSetKorisnickoIme() {
        prodavac.setKorisnickoIme("pera123");
        assertEquals("pera123", prodavac.getKorisnickoIme());
    }

    @Test
    void testSetKorisnickoImeNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> prodavac.setKorisnickoIme(null));
        assertEquals("Korisničko ime ne sme biti null.", ex.getMessage());
    }

    @Test
    void testSetKorisnickoImeBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setKorisnickoIme(" "));
        assertEquals("Korisničko ime ne sme biti prazno.", ex.getMessage());
    }

    @Test
    void testSetKorisnickoImePredugacko() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setKorisnickoIme("a".repeat(51)));
        assertEquals("Korisničko ime ne sme imati više od 50 karaktera.", ex.getMessage());
    }


    @Test
    void testSetSifra() {
        prodavac.setSifra("sifra123");
        assertEquals("sifra123", prodavac.getSifra());
    }

    @Test
    void testSetSifraNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> prodavac.setSifra(null));
        assertEquals("Šifra ne sme biti null.", ex.getMessage());
    }

    @Test
    void testSetSifraBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setSifra(" "));
        assertEquals("Šifra ne sme biti prazna.", ex.getMessage());
    }

    @Test
    void testSetSifraPredugacka() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setSifra("a".repeat(51)));
        assertEquals("Šifra ne sme imati više od 50 karaktera.", ex.getMessage());
    }


    @Test
    void testSetEmail() {
        prodavac.setEmail("pera@gmail.com");
        assertEquals("pera@gmail.com", prodavac.getEmail());
    }

    @Test
    void testSetEmailNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> prodavac.setEmail(null));
        assertEquals("Email ne sme biti null.", ex.getMessage());
    }

    @Test
    void testSetEmailBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail(" "));
        assertEquals("Email ne sme biti prazan.", ex.getMessage());
    }

    @Test
    void testSetEmailPredugacak() {
        String predugacak = "a".repeat(45) + "@gmail.com"; 
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail(predugacak));
        assertEquals("Email ne sme imati više od 50 karaktera.", ex.getMessage());
    }

    @Test
    void testSetEmailNevalidanFormat() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail("peragmail.com"));
        assertEquals("Email nije u ispravnom formatu.", ex.getMessage());
    }

    @Test
    void testSetEmailBezDomena() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> prodavac.setEmail("pera@"));
        assertEquals("Email nije u ispravnom formatu.", ex.getMessage());
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

    @ParameterizedTest
    @CsvSource({
        "pera123, pera123, true",   
        "pera123, mika456, false"   
    })
    void testEquals(String korisnickoIme1, String korisnickoIme2, boolean ocekivanoJednako) {
        prodavac.setKorisnickoIme(korisnickoIme1);
        Prodavac p2 = new Prodavac();
        p2.setKorisnickoIme(korisnickoIme2);
        assertEquals(ocekivanoJednako, prodavac.equals(p2));
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

