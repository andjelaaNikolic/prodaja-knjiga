package so.knjige;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Knjiga;
import model.Magacin;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOVratiListuSviKnjigaTest {

    SOVratiListuSviKnjiga so;

    @BeforeEach
    void setUp() throws Exception {
        so = new SOVratiListuSviKnjiga();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    @Test
    void testGetKnjigePocetnaVrednost() {
        assertNull(so.getKnjige(), "Početna vrednost liste knjiga mora biti null");
    }

	@Test
	void testPredusloviNULL() {
		Exception ex = assertThrows(java.lang.Exception.class, ()->so.preduslovi(null));
		assertEquals("Nije prosledjen parametar odgovarajuceg tipa.",ex.getMessage());
	}
	
	@Test
	void testPredusloviObjekatDrugaciji() {
		Exception ex = assertThrows(java.lang.Exception.class, ()->so.preduslovi(new String()));
		assertEquals("Nije prosledjen parametar odgovarajuceg tipa.",ex.getMessage());
	}


    private SOVratiListuSviKnjiga napraviSOSaMockom(Repozitorijum mockBroker) throws Exception {
        SOVratiListuSviKnjiga soSaMockom = new SOVratiListuSviKnjiga();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiOperacijuVracaListuSvihKnjiga() throws Exception {
        Magacin magacin = new Magacin(1, "Centralni", "Bulevar 1");
        Knjiga k1 = new Knjiga(1, "Na Drini ćuprija", "Roman", 2020, 990, 10, magacin);
        Knjiga k2 = new Knjiga(2, "Travnička hronika", "Roman", 2021, 1200, 5, magacin);

        List<Knjiga> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(k1);
        ocekivanaLista.add(k2);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(Knjiga.class), anyString())).thenReturn((List) ocekivanaLista);

        SOVratiListuSviKnjiga soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(new Knjiga(), null);

        assertNotNull(soSaMockom.getKnjige());
        assertEquals(2, soSaMockom.getKnjige().size());
        assertTrue(soSaMockom.getKnjige().contains(k1));
        assertTrue(soSaMockom.getKnjige().contains(k2));
    }

    @Test
    void testIzvrsiOperacijuPraznaListaKadaNemaKnjiga() throws Exception {
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(Knjiga.class), anyString())).thenReturn(new ArrayList<>());

        SOVratiListuSviKnjiga soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(new Knjiga(), null);

        assertNotNull(soSaMockom.getKnjige());
        assertTrue(soSaMockom.getKnjige().isEmpty());
    }
}
