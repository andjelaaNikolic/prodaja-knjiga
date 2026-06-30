package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
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

class MagacinTest {

    Magacin m;
    
    @Mock 
    ResultSet rs;

    @BeforeEach
    void setUp() throws Exception {
        m = new Magacin();
    }

    @AfterEach
    void tearDown() throws Exception {
        m = null;
    }


    @Test
    void testSetIdMagacin() {
        m.setIdMagacin(1);
        assertEquals(1, m.getIdMagacin());
    }

    @Test
    void testSetIdMagacinNula() {
        Exception ex = assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setIdMagacin(0));
        assertEquals("ID magacina mora biti veći od nule.", ex.getMessage());
    }

    @Test
    void testSetIdMagacinNegativno() {
        Exception ex = assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setIdMagacin(-1));
        assertEquals("ID magacina mora biti veći od nule.", ex.getMessage());
    }


    @Test
    void testSetNaziv() {
        m.setNaziv("Magacin Centar");
        assertEquals("Magacin Centar", m.getNaziv());
    }

    @Test
    void testSetNazivNull() {
        Exception ex = assertThrows(java.lang.NullPointerException.class,
                () -> m.setNaziv(null));
        assertEquals("Naziv ne sme biti null.", ex.getMessage());
    }

    @Test
    void testSetNazivPredugacak() {
        String predugacak = "A".repeat(101);
        Exception ex = assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setNaziv(predugacak));
        assertEquals("Naziv ne sme imati više od 20 karaktera.", ex.getMessage());
    }
    
    @Test
	void testSetNazivBlank() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->m.setNaziv(" "));
		assertEquals("Naziv ne sme biti prazan.", ex.getMessage());
	}


    @Test
    void testSetAdresa() {
        m.setAdresa("Ulica 1, Beograd");
        assertEquals("Ulica 1, Beograd", m.getAdresa());
    }

    @Test
    void testSetAdresaNull() {
        Exception ex = assertThrows(java.lang.NullPointerException.class,
                () -> m.setAdresa(null));
        assertEquals("Adresa ne sme biti null.", ex.getMessage());
    }

    @Test
    void testSetAdresaPredugacka() {
        String predugacka = "A".repeat(70);
        Exception ex = assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setAdresa(predugacka));
        assertEquals("Adresa ne sme imati više od 50 karaktera.", ex.getMessage());
    }


    @Test
    void testToString() {
        m.setNaziv("Magacin Sever");
        String st = m.toString();
        assertTrue(st.contains("Magacin Sever"));
    }


    @Test
    void testEqualsIstiObjekat() {
        assertTrue(m.equals(m));
    }

    @Test
    void testEqualsNullObjekat() {
        assertFalse(m.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {
        assertFalse(m.equals(new String()));
    }

    @ParameterizedTest
    @CsvSource({
        "Magacin A, Magacin A, true",
        "Magacin A, Magacin B, false",
        "Magacin B, Magacin A, false"
    })
    void testEquals(String naziv1, String naziv2, boolean jednako) {
        m.setNaziv(naziv1);

        Magacin m2 = new Magacin();
        m2.setNaziv(naziv2);

        assertEquals(jednako, m.equals(m2));
    }
    
    @Test
    void testVratiListuJedanRed() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("magacin.idMagacin")).thenReturn(1);
        when(rs.getString("magacin.naziv")).thenReturn("Magacin A");
        when(rs.getString("magacin.adresa")).thenReturn("Ulica 1");
 
        List<OpstiDomenskiObjekat> lista = m.vratiListu(rs);
 
        assertEquals(1, lista.size());
        Magacin rezultat = (Magacin) lista.get(0);
        assertEquals(1, rezultat.getIdMagacin());
        assertEquals("Magacin A", rezultat.getNaziv());
        assertEquals("Ulica 1", rezultat.getAdresa());
    }
 
    @Test
    void testVratiListuViseRedova() throws Exception {
        when(rs.next()).thenReturn(true, true, true, false);
        when(rs.getInt("magacin.idMagacin")).thenReturn(1, 2, 3);
        when(rs.getString("magacin.naziv")).thenReturn("Magacin A", "Magacin B", "Magacin C");
        when(rs.getString("magacin.adresa")).thenReturn("Ulica 1", "Ulica 2", "Ulica 3");
 
        List<OpstiDomenskiObjekat> lista = m.vratiListu(rs);
 
        assertEquals(3, lista.size());
    }
 
    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);
 
        List<OpstiDomenskiObjekat> lista = m.vratiListu(rs);
 
        assertNotNull(lista);
        assertEquals(0, lista.size());
    }
 
 
    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("magacin.idMagacin")).thenReturn(1);
        when(rs.getString("magacin.naziv")).thenReturn("Magacin A");
        when(rs.getString("magacin.adresa")).thenReturn("Ulica 1");
 
        Magacin rezultat = (Magacin) m.vratiObjekatIzRS(rs);
 
        assertNotNull(rezultat);
        assertEquals(1, rezultat.getIdMagacin());
        assertEquals("Magacin A", rezultat.getNaziv());
        assertEquals("Ulica 1", rezultat.getAdresa());
    }
 
    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);
 
        Magacin rezultat = (Magacin) m.vratiObjekatIzRS(rs);
 
        assertNull(rezultat);
    }
    
    @Test
    void testVratiNazivTabele() {
        assertEquals("magacin", m.vratiNazivTabele());
    }
 
 
    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("naziv,adresa", m.vratiKoloneZaUbacivanje());
    }
 
 
    @Test
    void testVratiVrednostiZaUbacivanje() {
        m.setNaziv("Magacin A");
        m.setAdresa("Ulica 1");
        assertEquals("'Magacin A','Ulica 1'", m.vratiVrednostiZaUbacivanje());
    }
 
 
    @Test
    void testVratiPrimarniKljuc() {
        m.setIdMagacin(1);
        assertEquals("magacin.idMagacin=1", m.vratiPrimarniKljuc());
    }
 

    @Test
    void testVratiVrednostiZaIzmenu() {
        m.setNaziv("Magacin A");
        m.setAdresa("Ulica 1");
        assertEquals("naziv='Magacin A', adresa='Ulica 1'", m.vratiVrednostiZaIzmenu());
    }
 
}
    
    
    

