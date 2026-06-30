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

class MestoTest {

	Mesto m;
	
	@Mock 
	ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		m = new Mesto();
	}

	@AfterEach
	void tearDown() throws Exception {
		m = null;
	}
	
	@Test
	void testSetIdMesto() {
		m.setIdMesto(1);
		assertEquals(1, m.getIdMesto());
	}
	

	@Test
	void testSetIdMestoNula() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class, 
				() -> m.setIdMesto(0));
		assertEquals("ID mesta mora biti veći od nule.", ex.getMessage());
	}
	
	@Test
	void testSetIdMestoNegativno() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class, 
				() -> m.setIdMesto(-1));
		assertEquals("ID mesta mora biti veći od nule.", ex.getMessage());
	}
	
	@Test
	void testSetNazivMesta() {
		m.setNazivMesta("Beograd");;
		assertEquals("Beograd", m.getNazivMesta());
	}
	
	@Test
	void testSetNazivMestaNull() {
		Exception ex = assertThrows(java.lang.NullPointerException.class, 
				() -> m.setNazivMesta(null));
		assertEquals("Naziv mesta ne sme biti null.", ex.getMessage());
	}
	
	@Test
	void testSetNazivMestaPredugacak() {
		String predugacak = "A".repeat(55);
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class, 
				() -> m.setNazivMesta(predugacak));
		assertEquals("Naziv mesta ne sme imati više od 50 karaktera.", ex.getMessage());
	}
	@Test
	void testSetNazivMestaBlank() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->m.setNazivMesta(" "));
		assertEquals("Naziv mesta ne sme biti prazan.", ex.getMessage());
	}
	
	@Test
	void testSetNazivMestaBrojevi() {
		String sadrziBrojeve = "Beograd1";
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->m.setNazivMesta(sadrziBrojeve));
		assertEquals("Naziv mesta sme sadržati samo slova.", ex.getMessage());
	}
	
	
	@Test
	void testToString() {
		m.setNazivMesta("Novi Sad");;
		String st = m.toString();
		assertTrue(st.contains("Novi Sad"));
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
	@CsvSource ({
		"Beograd, Beograd, true",
		"Beograd, Novi Sad, false",
		"Novi Sad, Beograd, false"
	})
	void testEquals(String mesto1, String mesto2,  boolean jednako) {
		m.setNazivMesta(mesto1);
		
		Mesto m2 = new Mesto();
		m2.setNazivMesta(mesto2);
		
		assertEquals(jednako, m.equals(m2));
	}

	
	@Test
    void testVratiListuJedanRed() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("mesto.idMesto")).thenReturn(1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd");
 
        List<OpstiDomenskiObjekat> lista = m.vratiListu(rs);
 
        assertEquals(1, lista.size());
        Mesto rezultat = (Mesto) lista.get(0);
        assertEquals(1, rezultat.getIdMesto());
        assertEquals("Beograd", rezultat.getNazivMesta());
    }
 
    @Test
    void testVratiListuViseRedova() throws Exception {
        when(rs.next()).thenReturn(true, true, true, false);
        when(rs.getInt("mesto.idMesto")).thenReturn(1, 2, 3);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd", "Novi Sad", "Nis");
 
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
        when(rs.getInt("mesto.idMesto")).thenReturn(1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd");
 
        Mesto rezultat = (Mesto) m.vratiObjekatIzRS(rs);
 
        assertNotNull(rezultat);
        assertEquals(1, rezultat.getIdMesto());
        assertEquals("Beograd", rezultat.getNazivMesta());
    }
 
    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);
 
        Mesto rezultat = (Mesto) m.vratiObjekatIzRS(rs);
 
        assertNull(rezultat);
    }
    
    @Test
    void testVratiNazivTabele() {
        assertEquals("mesto", m.vratiNazivTabele());
    }
 
 
    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("nazivMesta", m.vratiKoloneZaUbacivanje());
    }
 
 
    @Test
    void testVratiVrednostiZaUbacivanje() {
        m.setNazivMesta("Beograd");
        assertEquals("'Beograd'", m.vratiVrednostiZaUbacivanje());
    }
 
 
    @Test
    void testVratiPrimarniKljuc() {
        m.setIdMesto(1);
        assertEquals("mesto.idMesto=1", m.vratiPrimarniKljuc());
    }
 
 
    @Test
    void testVratiVrednostiZaIzmenu() {
        m.setNazivMesta("Beograd");
        assertEquals("nazivMesta='Beograd'", m.vratiVrednostiZaIzmenu());
    }
	
}

