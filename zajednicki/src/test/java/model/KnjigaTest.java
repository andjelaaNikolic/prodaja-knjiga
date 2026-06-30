package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.time.LocalDateTime;
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

class KnjigaTest {
	
	Knjiga k;
	
	@Mock
	ResultSet rs;

	@BeforeEach
	void setUp() throws Exception {
		k = new Knjiga();
	}

	@AfterEach
	void tearDown() throws Exception {
		k = null;
	}

	 @Test
	    void testSetIdKnjigaNegativan() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setIdKnjiga(-1));
	        assertEquals("ID knjige mora biti veći od nule.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetIdKnjigaNula() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setIdKnjiga(0));
	        assertEquals("ID knjige mora biti veći od nule.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetIdKnjiga() {
	        k.setIdKnjiga(1);
	        assertEquals(1, k.getIdKnjiga());
	    }
	 
	 
	    @Test
	    void testSetNaslovNull() {
	        Exception ex = assertThrows(NullPointerException.class, () -> k.setNaslov(null));
	        assertEquals("Naslov ne sme biti null.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetNaslovBlank() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setNaslov(" "));
	        assertEquals("Naslov ne sme biti prazan.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetNaslovPredugacak() {
	        String predugacak = "A".repeat(51);
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setNaslov(predugacak));
	        assertEquals("Naslov ne sme imati više od 50 karaktera.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetNaslov() {
	        k.setNaslov("Zlocin i kazna");
	        assertEquals("Zlocin i kazna", k.getNaslov());
	    }
	 
	    @Test
	    void testSetNaslovTacnoNaGranici() {
	        String tacnoNaGranici = "A".repeat(50);
	        assertDoesNotThrow(() -> k.setNaslov(tacnoNaGranici));
	    }
	 
	 
	    @Test
	    void testSetZanrNull() {
	        Exception ex = assertThrows(NullPointerException.class, () -> k.setZanr(null));
	        assertEquals("Žanr ne sme biti null.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetZanrBlank() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setZanr(" "));
	        assertEquals("Žanr ne sme biti prazan.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetZanrPredugacak() {
	        String predugacak = "A".repeat(21);
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setZanr(predugacak));
	        assertEquals("Žanr ne sme imati više od 20 karaktera.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetZanr() {
	        k.setZanr("Roman");
	        assertEquals("Roman", k.getZanr());
	    }
	 
	    @Test
	    void testSetZanrTacnoNaGranici() {
	        String tacnoNaGranici = "A".repeat(20);
	        assertDoesNotThrow(() -> k.setZanr(tacnoNaGranici));
	    }
	 
	 
	    @Test
	    void testSetGodinaIzdanjaNegativan() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setGodinaIzdanja(-1));
	        assertEquals("Godina izdanja mora biti veća od nule.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetGodinaIzdanjaNula() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setGodinaIzdanja(0));
	        assertEquals("Godina izdanja mora biti veća od nule.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetGodinaIzdanjaUBuducnosti() {
	        int buducaGodina = LocalDateTime.now().getYear() + 1;
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setGodinaIzdanja(buducaGodina));
	        assertEquals("Godina izdanja ne sme biti u budućnosti.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetGodinaIzdanja() {
	        k.setGodinaIzdanja(2020);
	        assertEquals(2020, k.getGodinaIzdanja());
	    }
	 
	    @Test
	    void testSetGodinaIzdanjaTekucaGodina() {
	        int tekucaGodina = LocalDateTime.now().getYear();
	        assertDoesNotThrow(() -> k.setGodinaIzdanja(tekucaGodina));
	    }
	 
	 
	    @Test
	    void testSetCenaNegativna() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setCena(-1.0));
	        assertEquals("Cena mora biti veća od nule.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetCenaNula() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setCena(0));
	        assertEquals("Cena mora biti veća od nule.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetCena() {
	        k.setCena(500.0);
	        assertEquals(500.0, k.getCena());
	    }
	 
	 
	    @Test
	    void testSetKolicinaNegativna() {
	        Exception ex = assertThrows(IllegalArgumentException.class, () -> k.setKolicina(-1));
	        assertEquals("Količina ne sme biti negativna.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetKolicinaNula() {
	        assertDoesNotThrow(() -> k.setKolicina(0));
	    }
	 
	    @Test
	    void testSetKolicina() {
	        k.setKolicina(10);
	        assertEquals(10, k.getKolicina());
	    }
	 
	 
	    @Test
	    void testSetMagacinNull() {
	        Exception ex = assertThrows(NullPointerException.class, () -> k.setMagacin(null));
	        assertEquals("Magacin ne sme biti null.", ex.getMessage());
	    }
	 
	    @Test
	    void testSetMagacin() {
	        Magacin m = new Magacin(1, "Magacin A", "Jove Ilica");
	        k.setMagacin(m);
	        assertEquals(m, k.getMagacin());
	    }
	 
	 
	    @Test
	    void testToString() {
	        k.setNaslov("Lovac na zmajeve");
	        k.setCena(1000.0);
	 
	        String st = k.toString();
	 
	        assertTrue(st.contains("Lovac na zmajeve"));
	        assertTrue(st.contains("1000.0"));
	    }
	 
	 
	    @Test
	    void testEqualsIstiObjekat() {
	        assertTrue(k.equals(k));
	    }
	 
	    @Test
	    void testEqualsNullObjekat() {
	        assertFalse(k.equals(null));
	    }
	 
	    @Test
	    void testEqualsDrugaKlasa() {
	        assertFalse(k.equals(new String()));
	    }
	 
	    @ParameterizedTest
	    @CsvSource({
	        "Lovac na zmajeve, Roman, 2020, Lovac na zmajeve, Roman, 2020, true",
	        "Lovac na zmajeve, Roman, 2020, Na Drini cuprija, Roman, 2020, false",
	        "Lovac na zmajeve, Roman, 2020, Lovac na zmajeve, Drama, 2020, false",
	        "Lovac na zmajeve, Roman, 2020, Lovac na zmajeve, Roman, 2021, false",
	        "Lovac na zmajeve, Roman, 2020, Na Drini cuprija, Istorijski roman, 1945, false"
	    })
	    void testEquals(String naslov1, String zanr1, int godina1,
	                    String naslov2, String zanr2, int godina2, boolean jednako) {
	        k.setNaslov(naslov1);
	        k.setZanr(zanr1);
	        k.setGodinaIzdanja(godina1);
	 
	        Knjiga k2 = new Knjiga();
	        k2.setNaslov(naslov2);
	        k2.setZanr(zanr2);
	        k2.setGodinaIzdanja(godina2);
	 
	        assertEquals(jednako, k.equals(k2));
	    }
	 	 
	    @Test
	    void testVratiNazivTabele() {
	        assertEquals("knjiga", k.vratiNazivTabele());
	    }
	 
	 
	    @Test
	    void testVratiKoloneZaUbacivanje() {
	        assertEquals("naslov,zanr,godinaIzdanja,cena,kolicina,magacin", k.vratiKoloneZaUbacivanje());
	    }
	 
	 
	    @Test
	    void testVratiVrednostiZaUbacivanje() {
	        k.setNaslov("Na Drini cuprija");
	        k.setZanr("Roman");
	        k.setGodinaIzdanja(1945);
	        k.setCena(1000.0);
	        k.setKolicina(10);
	        Magacin m = new Magacin(1, "Magacin A", "Ulica 1");
	        k.setMagacin(m);
	 
	        assertEquals("'Na Drini cuprija','Roman',1945,1000.0,10,1", k.vratiVrednostiZaUbacivanje());
	    }
	 
	 
	    @Test
	    void testVratiPrimarniKljuc() {
	        k.setIdKnjiga(1);
	        assertEquals("knjiga.idKnjiga=1", k.vratiPrimarniKljuc());
	    }
	 
	 
	    @Test
	    void testVratiVrednostiZaIzmenu() {
	        k.setNaslov("Na Drini cuprija");
	        k.setZanr("Roman");
	        k.setGodinaIzdanja(1945);
	        k.setCena(1000.0);
	        k.setKolicina(10);
	        Magacin m = new Magacin(1, "Magacin A", "Ulica 1");
	        k.setMagacin(m);
	 
	        assertEquals("naslov='Na Drini cuprija', zanr='Roman', godinaIzdanja=1945, cena=1000.0, kolicina=10, magacin=1",
	                k.vratiVrednostiZaIzmenu());
	    }
	 
	 
	    @Test
	    void testVratiListuJedanRed() throws Exception {
	        when(rs.next()).thenReturn(true, false);
	        when(rs.getInt("knjiga.idKnjiga")).thenReturn(1);
	        when(rs.getString("knjiga.naslov")).thenReturn("Na Drini cuprija");
	        when(rs.getString("knjiga.zanr")).thenReturn("Roman");
	        when(rs.getInt("knjiga.godinaIzdanja")).thenReturn(1945);
	        when(rs.getDouble("knjiga.cena")).thenReturn(1000.0);
	        when(rs.getInt("knjiga.kolicina")).thenReturn(10);
	        when(rs.getInt("knjiga.magacin")).thenReturn(1);
	        when(rs.getString("magacin.naziv")).thenReturn("Magacin A");
	        when(rs.getString("magacin.adresa")).thenReturn("Ulica 1");
	 
	        List<OpstiDomenskiObjekat> lista = k.vratiListu(rs);
	 
	        assertEquals(1, lista.size());
	        Knjiga rezultat = (Knjiga) lista.get(0);
	        assertEquals(1, rezultat.getIdKnjiga());
	        assertEquals("Na Drini cuprija", rezultat.getNaslov());
	        assertEquals("Roman", rezultat.getZanr());
	        assertEquals(1945, rezultat.getGodinaIzdanja());
	        assertEquals(1000.0, rezultat.getCena());
	        assertEquals(10, rezultat.getKolicina());
	        assertEquals("Magacin A", rezultat.getMagacin().getNaziv());
	    }
	 
	    @Test
	    void testVratiListuViseRedova() throws Exception {
	        when(rs.next()).thenReturn(true, true, true, false);
	        when(rs.getInt("knjiga.idKnjiga")).thenReturn(1, 2, 3);
	        when(rs.getString("knjiga.naslov")).thenReturn("Naslov A", "Naslov B", "Naslov C");
	        when(rs.getString("knjiga.zanr")).thenReturn("Roman", "Drama", "Roman");
	        when(rs.getInt("knjiga.godinaIzdanja")).thenReturn(2020, 2021, 2022);
	        when(rs.getDouble("knjiga.cena")).thenReturn(500.0, 300.0, 400.0);
	        when(rs.getInt("knjiga.kolicina")).thenReturn(10, 5, 8);
	        when(rs.getInt("knjiga.magacin")).thenReturn(1, 1, 1);
	        when(rs.getString("magacin.naziv")).thenReturn("Magacin A", "Magacin A", "Magacin A");
	        when(rs.getString("magacin.adresa")).thenReturn("Ulica 1", "Ulica 1", "Ulica 1");
	 
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
	        when(rs.getInt("knjiga.idKnjiga")).thenReturn(1);
	        when(rs.getString("knjiga.naslov")).thenReturn("Na Drini cuprija");
	        when(rs.getString("knjiga.zanr")).thenReturn("Roman");
	        when(rs.getInt("knjiga.godinaIzdanja")).thenReturn(1945);
	        when(rs.getDouble("knjiga.cena")).thenReturn(1000.0);
	        when(rs.getInt("knjiga.kolicina")).thenReturn(10);
	        when(rs.getInt("knjiga.magacin")).thenReturn(1);
	        when(rs.getString("magacin.naziv")).thenReturn("Magacin A");
	        when(rs.getString("magacin.adresa")).thenReturn("Ulica 1");
	 
	        Knjiga rezultat = (Knjiga) k.vratiObjekatIzRS(rs);
	 
	        assertNotNull(rezultat);
	        assertEquals(1, rezultat.getIdKnjiga());
	        assertEquals("Na Drini cuprija", rezultat.getNaslov());
	        assertEquals("Roman", rezultat.getZanr());
	        assertEquals(1945, rezultat.getGodinaIzdanja());
	        assertEquals(1000.0, rezultat.getCena());
	        assertEquals(10, rezultat.getKolicina());
	        assertEquals("Magacin A", rezultat.getMagacin().getNaziv());
	    }
	 
	    @Test
	    void testVratiObjekatIzRSPrazanRS() throws Exception {
	        when(rs.next()).thenReturn(false);
	 
	        Knjiga rezultat = (Knjiga) k.vratiObjekatIzRS(rs);
	 
	        assertNull(rezultat);
	    }

}

