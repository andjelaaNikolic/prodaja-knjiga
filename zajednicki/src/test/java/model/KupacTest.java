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

class KupacTest {

	Kupac k;
	
	@Mock
	ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		k= new Kupac();
	}

	@AfterEach
	void tearDown() throws Exception {
		k = null;
	}

	@Test
	void testSetIdKupacNegativan() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setIdKupac(-1));
		assertEquals("ID kupca mora biti veći od nule.", ex.getMessage());
	}
	
	@Test
	void testSetIdKupacNula() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setIdKupac(0));
		assertEquals("ID kupca mora biti veći od nule.", ex.getMessage());
	}
	
	@Test
	void testSetIdKupac() {
		k.setIdKupac(1);
		assertEquals(1,k.getIdKupac());
	}
	
	@Test
	void testSetImeNull() {
		Exception ex = assertThrows(java.lang.NullPointerException.class,()->k.setIme(null));
		assertEquals("Ime ne sme biti null.", ex.getMessage());
	}
	
	@Test
	void testSetImeBlank() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setIme(" "));
		assertEquals("Ime ne sme biti prazno.", ex.getMessage());
	}
	
	@Test
	void testSetImePredugacko() {
		String predugacak = "A".repeat(51);
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setIme(predugacak));
		assertEquals("Ime ne sme imati više od 50 karaktera.", ex.getMessage());
	}
	
	@Test
	void testSetImeSadrziBrojeve() {
		String sadrziBrojeve = "Pera3";
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setIme(sadrziBrojeve));
		assertEquals("Ime sme sadržati samo slova.", ex.getMessage());
	}

	
	@Test
	void testSetIme() {
		k.setIme("Pera");
		assertEquals("Pera",k.getIme());
	}
	
	@Test
	void testSetPrezimeNull() {
		Exception ex = assertThrows(java.lang.NullPointerException.class,()->k.setPrezime(null));
		assertEquals("Prezime ne sme biti null.", ex.getMessage());
	}
	
	@Test
	void testSetPrezimeBlank() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setPrezime(" "));
		assertEquals("Prezime ne sme biti prazno.", ex.getMessage());
	}
	
	@Test
	void testSetPrezimePredugacko() {
		String predugacak = "A".repeat(51);
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setPrezime(predugacak));
		assertEquals("Prezime ne sme imati više od 50 karaktera.", ex.getMessage());
	}
	
	@Test
	void testSetPrezimeSadrziBrojeve() {
		String sadrziBrojeve = "Peric3";
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setPrezime(sadrziBrojeve));
		assertEquals("Prezime sme sadržati samo slova.", ex.getMessage());
	}

	
	@Test
	void testSetPrezime() {
		k.setPrezime("Peric");
		assertEquals("Peric",k.getPrezime());
	}
	
	@Test
	void testSetBrojTelefonaNull() {
		Exception ex = assertThrows(java.lang.NullPointerException.class,()->k.setBrojTelefona(null));
		assertEquals("Broj telefona ne sme biti null.", ex.getMessage());
	}
	
	@Test
	void testSetBrojTelefonaBlank() {
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setBrojTelefona(" "));
		assertEquals("Broj telefona sme sadržati samo cifre.", ex.getMessage());
	}
	
	@Test
	void testSetBrojTelefonaPredugacak() {
		String predugacak = "5".repeat(11);
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setBrojTelefona(predugacak));
		assertEquals("Broj telefona ne sme imati više od 10 cifara.", ex.getMessage());
	}
	
	@Test
	void testSetBrojTelefonaSadrziSlova() {
		String sadrziSlova = "065A555555";
		Exception ex = assertThrows(java.lang.IllegalArgumentException.class,()->k.setBrojTelefona(sadrziSlova));
		assertEquals("Broj telefona sme sadržati samo cifre.", ex.getMessage());
	}

	
	@Test
	void testSetBrojTelefona() {
		k.setBrojTelefona("0623333444");
		assertEquals("0623333444",k.getBrojTelefona());
	}
	
	@Test
	void testMestoNull() {
		Exception ex = assertThrows(java.lang.NullPointerException.class,()->k.setMesto(null));
		assertEquals("Mesto ne sme biti null.", ex.getMessage());
	}
	
	@Test
	void testSetMesto() {
        Mesto m = new Mesto(1, "Beograd");
        k.setMesto(m);
        assertEquals(m, k.getMesto());
	}
	
	@Test
	void testToString() {
		k.setIdKupac(1);
		k.setIme("Pera");
		k.setPrezime("Peric");
		
		String kupac = k.toString();
		
		assertTrue(kupac.contains("1"));
		assertTrue(kupac.contains("Pera"));
		assertTrue(kupac.contains("Peric"));
		
	}
	
	@ParameterizedTest
    @CsvSource({
        "Pera, Peric, 0623333444, Pera, Peric, 0623333444, true",
        "Pera, Peric, 0623333444, Ana, Peric, 0623333444, false",
        "Pera, Peric, 0623333444, Ana, Anic, 0623333444, false",
        "Pera, Peric, 0623333444, Ana, Anic, 062333355, false"
    })
    void testEquals(String ime1, String prezime1, String brojTelefona1,String ime2, String prezime2, String brojTelefona2, boolean jednako) {
        k.setIme(ime1);
        k.setPrezime(prezime1);
        k.setBrojTelefona(brojTelefona1);
        
        Kupac k2 = new Kupac();
        
        k2.setIme(ime2);
        k2.setPrezime(prezime2);
        k2.setBrojTelefona(brojTelefona2);

        assertEquals(jednako, k.equals(k2));
    }
	
	@Test
	void vratiNazivTabele() {
		assertEquals("kupac",k.vratiNazivTabele());
	}
	
	@Test
	void vratiKoloneZaUbacivanje() {
		assertEquals("ime,prezime,brojTelefona,mesto",k.vratiKoloneZaUbacivanje());
	}
	
	@Test
	void vratiVrednostiZaUbacivanje() {
		k.setIme("Pera");
		k.setPrezime("Peric");
		k.setBrojTelefona("0651111444");
		Mesto m = new Mesto(1,"Beograd");
		k.setMesto(m);
		assertEquals("'Pera','Peric','0651111444',1",k.vratiVrednostiZaUbacivanje());
	}
	
    @Test
    void testVratiPrimarniKljuc() {
        k.setIdKupac(1);
        assertEquals("kupac.idKupac=1", k.vratiPrimarniKljuc());
    }
    
    @Test
    void testVratiVrednostiZaIzmenu() {
		k.setIme("Pera");
		k.setPrezime("Peric");
		k.setBrojTelefona("0651111444");
		Mesto m = new Mesto(1,"Beograd");
		k.setMesto(m);
        assertEquals("ime='Pera', prezime='Peric', brojTelefona='0651111444', mesto=1", k.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListuJedanRed() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("kupac.idKupac")).thenReturn(1);
        when(rs.getString("kupac.ime")).thenReturn("Pera");
        when(rs.getString("kupac.prezime")).thenReturn("Peric");
        when(rs.getString("kupac.brojTelefona")).thenReturn("0631111222");
        when(rs.getInt("kupac.mesto")).thenReturn(1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd");
        
 
        List<OpstiDomenskiObjekat> lista = k.vratiListu(rs);
 
        assertEquals(1, lista.size());
        Kupac rezultat = (Kupac) lista.get(0);
        assertEquals(1, rezultat.getIdKupac());
        assertEquals("Pera", rezultat.getIme());
        assertEquals("Peric", rezultat.getPrezime());
        assertEquals("Beograd", rezultat.getMesto().getNazivMesta());
    }
 
    @Test
    void testVratiListuViseRedova() throws Exception {
        when(rs.next()).thenReturn(true, true, true, false);
        when(rs.getInt("kupac.idKupac")).thenReturn(1,2,3);
        when(rs.getString("kupac.ime")).thenReturn("Pera","Ana","Mika");
        when(rs.getString("kupac.prezime")).thenReturn("Peric","Anic","Mikic");
        when(rs.getString("kupac.brojTelefona")).thenReturn("0631111222","0633333222","0631111555");
        when(rs.getInt("kupac.mesto")).thenReturn(1,1,1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd","Beograd","Beograd");
 
        List<OpstiDomenskiObjekat> lista = k.vratiListu(rs);
 
        assertEquals(3, lista.size());
    }
 
    @Test
    void testVratiListuPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);
 
        List<OpstiDomenskiObjekat> lista = k.vratiListu(rs);
 
        assertNotNull(lista);
        assertEquals(0, lista.size());
    }
 
 
    @Test
    void testVratiObjekatIzRS() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("kupac.idKupac")).thenReturn(1);
        when(rs.getString("kupac.ime")).thenReturn("Pera");
        when(rs.getString("kupac.prezime")).thenReturn("Peric");
        when(rs.getString("kupac.brojTelefona")).thenReturn("0631111222");
        when(rs.getInt("kupac.mesto")).thenReturn(1);
        when(rs.getString("mesto.nazivMesta")).thenReturn("Beograd");
 
        Kupac rezultat = (Kupac) k.vratiObjekatIzRS(rs);
 
        assertNotNull(rezultat);
        assertEquals(1, rezultat.getIdKupac());
        assertEquals("Pera", rezultat.getIme());
        assertEquals("Peric", rezultat.getPrezime());
        assertEquals("Beograd", rezultat.getMesto().getNazivMesta());
    }
 
    @Test
    void testVratiObjekatIzRSPrazanRS() throws Exception {
        when(rs.next()).thenReturn(false);
 
        Kupac rezultat = (Kupac) k.vratiObjekatIzRS(rs);
 
        assertNull(rezultat);
    }
    
	

	
	
}
