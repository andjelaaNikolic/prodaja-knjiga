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
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setIdKupac(-1));
	}
	
	@Test
	void testSetIdKupacNula() {
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setIdKupac(0));
	}
	
	@Test
	void testSetIdKupac() {
		k.setIdKupac(1);
		assertEquals(1,k.getIdKupac());
	}
	
	@Test
	void testSetImeNull() {
		assertThrows(java.lang.NullPointerException.class,()->k.setIme(null));
	}
	
	@Test
	void testSetImeBlank() {
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setIme(" "));
	}
	
	@Test
	void testSetImePredugacko() {
		String predugacak = "A".repeat(51);
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setIme(predugacak));
	}
	
	@Test
	void testSetImeSadrziBrojeve() {
		String sadrziBrojeve = "Pera3";
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setIme(sadrziBrojeve));
	}

	
	@Test
	void testSetIme() {
		k.setIme("Pera");
		assertEquals("Pera",k.getIme());
	}
	
	@Test
	void testSetPrezimeNull() {
		assertThrows(java.lang.NullPointerException.class,()->k.setPrezime(null));
	}
	
	@Test
	void testSetPrezimeBlank() {
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setPrezime(" "));
	}
	
	@Test
	void testSetPrezimePredugacko() {
		String predugacak = "A".repeat(51);
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setPrezime(predugacak));
	}
	
	@Test
	void testSetPrezimeSadrziBrojeve() {
		String sadrziBrojeve = "Peric3";
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setPrezime(sadrziBrojeve));
	}

	
	@Test
	void testSetPrezime() {
		k.setPrezime("Peric");
		assertEquals("Peric",k.getPrezime());
	}
	
	@Test
	void testSetBrojTelefonaNull() {
		assertThrows(java.lang.NullPointerException.class,()->k.setBrojTelefona(null));
	}
	
	@Test
	void testSetBrojTelefonaBlank() {
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setBrojTelefona(" "));
	}
	
	@Test
	void testSetBrojTelefonaPredugacak() {
		String predugacak = "5".repeat(11);
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setBrojTelefona(predugacak));
	}
	
	@Test
	void testSetBrojTelefonaSadrziSlova() {
		String sadrziSlova = "065A555555";
		assertThrows(java.lang.IllegalArgumentException.class,()->k.setBrojTelefona(sadrziSlova));
	}

	
	@Test
	void testSetBrojTelefona() {
		k.setBrojTelefona("0623333444");
		assertEquals("0623333444",k.getBrojTelefona());
	}
	
	@Test
	void testMestoNull() {
		assertThrows(java.lang.NullPointerException.class,()->k.setMesto(null));
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
