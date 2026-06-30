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

class StrSpremaTest {
	
	
	StrSprema ss;
	
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		ss = new StrSprema();
	}

	@AfterEach
	void tearDown() throws Exception {
		ss=null;
	}

	@Test
	void testSetIdStrucnaSpremaNegativan() {
		
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->ss.setIdStrucnaSprema(-1));
		assertEquals("ID stručne spreme mora biti veći od nule.", ex.getMessage());
	}
	
	@Test
	void testSetIdStrucnaSpremaNula() {
		
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->ss.setIdStrucnaSprema(0));
		assertEquals("ID stručne spreme mora biti veći od nule.", ex.getMessage());
	}
	
	@Test
	void testSetIdStrucnaSprema() {
		
		ss.setIdStrucnaSprema(1);
		assertEquals(1,ss.getIdStrucnaSprema());
	}
	
	@Test
	void testSetStepenNull() {
		Exception ex = assertThrows(java.lang.NullPointerException.class,()->ss.setStepen(null));
		assertEquals("Stepen ne sme biti null.", ex.getMessage());
	}
	
	@Test
	void testSetStrucnaSpremaPredugacak() {
		String predugacak = "A".repeat(60);
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->ss.setStepen(predugacak));
		assertEquals("Stepen ne sme imati više od 50 karaktera.", ex.getMessage());
	}
	
	@Test
	void testSetStepenBlank() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->ss.setStepen(" "));
		assertEquals("Stepen ne sme biti prazan.", ex.getMessage());
	}

	@Test
	void testSetStepen() {
		ss.setStepen("I stepen: Osnovna škola");
		assertEquals("I stepen: Osnovna škola",ss.getStepen());
	}
	
	@Test
	void testToString() {
		ss.setStepen("I stepen: Osnovna škola");
		String s = ss.toString();
		assertTrue(s.contains("I stepen: Osnovna škola"));
	}
	
	@Test
	void testEqualsIstiObjekat() {
		assertTrue(ss.equals(ss));
	}
	
	@Test
	void testEqualsNullObjekat() {
		assertFalse(ss.equals(null));
	}
	
	@Test
	void testEqualsDrugaKlasa() {
		assertFalse(ss.equals(new String()));
	}
	
	@ParameterizedTest
	@CsvSource ({
		"I stepen, I stepen, true",
		"I stepen, II stepen,false",
		"II stepen, I stepen, false"
	})
	void testEquals(String stepen1, String stepen2,  boolean jednako) {
		ss.setStepen(stepen1);
		
		StrSprema s2 = new StrSprema();
		s2.setStepen(stepen2);
		
		assertEquals(jednako, ss.equals(s2));
	}
	
	
	@Test
	 void testVratiNazivTabele() {
		assertEquals("str_sprema",ss.vratiNazivTabele());
	}
	
	@Test
	void testVratiKoloneZaUbacivanje() {
		assertEquals("stepen",ss.vratiKoloneZaUbacivanje());
	}
	
    @Test
    void testVratiVrednostiZaUbacivanje() {
        ss.setStepen("I stepen");
        assertEquals("'I stepen'", ss.vratiVrednostiZaUbacivanje());
    }
 
 
    @Test
    void testVratiPrimarniKljuc() {
        ss.setIdStrucnaSprema(1);
        assertEquals("str_sprema.idStrucnaSprema=1", ss.vratiPrimarniKljuc());
    }
 
 
    @Test
    void testVratiVrednostiZaIzmenu() {
        ss.setStepen("I stepen");
        assertEquals("stepen='I stepen'", ss.vratiVrednostiZaIzmenu());
    }
    
	@Test
    void testVratiListuJedanRed() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("str_sprema.idStrucnaSprema")).thenReturn(1);
        when(rs.getString("str_sprema.stepen")).thenReturn("I stepen");
 
        List<OpstiDomenskiObjekat> lista = ss.vratiListu(rs);
 
        assertEquals(1, lista.size());
        StrSprema rezultat = (StrSprema) lista.get(0);
        assertEquals(1, rezultat.getIdStrucnaSprema());
        assertEquals("I stepen", rezultat.getStepen());
    }
	
	 @Test
	    void testVratiListuViseRedova() throws Exception {
	        when(rs.next()).thenReturn(true, true, true, false);
	        when(rs.getInt("str_sprema.idStrucnaSprema")).thenReturn(1, 2, 3);
	        when(rs.getString("str_sprema.stepen")).thenReturn("I stepen", "II stepen", "III stepen");
	 
	        List<OpstiDomenskiObjekat> lista = ss.vratiListu(rs);
	 
	        assertEquals(3, lista.size());
	    }
	 
	 @Test
	 void testVratiListuPrazna() throws Exception{
		 when(rs.next()).thenReturn(false);
		 List<OpstiDomenskiObjekat> lista = ss.vratiListu(rs);
		 
		 assertNotNull(lista);
		 assertEquals(0,lista.size());
	 }
	 
	 @Test
	    void testVratiObjekatIzRS() throws Exception {
	        when(rs.next()).thenReturn(true, false);
	        when(rs.getInt("str_sprema.idStrucnaSprema")).thenReturn(1);
	        when(rs.getString("str_sprema.stepen")).thenReturn("I stepen");
	        
	        StrSprema rezultat = (StrSprema) ss.vratiObjekatIzRS(rs);
	 	 
	        assertNotNull(rezultat);
	        assertEquals(1, rezultat.getIdStrucnaSprema());
	        assertEquals("I stepen", rezultat.getStepen());
	    }
	 
	    @Test
	    void testVratiObjekatIzRSPrazanRS() throws Exception {
	        when(rs.next()).thenReturn(false);
	        StrSprema rezultat = (StrSprema) ss.vratiObjekatIzRS(rs);
	        assertNull(rezultat);

	    }

}

