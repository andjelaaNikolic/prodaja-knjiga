package so.strSprema;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Knjiga;
import model.Magacin;
import model.StrSprema;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;
import so.knjige.SOPretraziKnjiga;

class SOPretraziStrSpremaTest {

	private SOPretraziStrSprema so;
	
	
	@BeforeEach
	void setUp() throws Exception {
		so = new SOPretraziStrSprema();
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testPredusloviNUll() {
		Exception ex = assertThrows(java.lang.Exception.class, ()->so.preduslovi(null));
		assertEquals("Nije prosledjen parametar odgovarajuceg tipa.",ex.getMessage());
	}
	
	@Test
	void testPredusloviPogresanTip() {
		Exception ex = assertThrows(java.lang.Exception.class, ()->so.preduslovi(new String()));
		assertEquals("Nije prosledjen parametar odgovarajuceg tipa.",ex.getMessage());
	}
	
    private SOPretraziStrSprema napraviSOSaMockom(Repozitorijum mockBroker) throws Exception {
        SOPretraziStrSprema soSaMockom = new SOPretraziStrSprema();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiOperacijuVracaPronadjenuStrSpremu() throws Exception {
        
    	StrSprema ss = new StrSprema(1,"I stepen");

        StrSprema parametar = new StrSprema();
        parametar.setIdStrucnaSprema(1);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiObjekat(any(StrSprema.class), anyString())).thenReturn(ss);

        SOPretraziStrSprema soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, null);

        assertNotNull(soSaMockom.getSs());
        assertEquals(ss, soSaMockom.getSs());
        verify(mockBroker, times(1)).vratiObjekat(any(StrSprema.class), contains("idStrucnaSprema= 1"));
    }

    @Test
    void testIzvrsiOperacijuStrSpremaNijePronadjenaVracaNull() throws Exception {

        StrSprema parametar = new StrSprema();
        parametar.setIdStrucnaSprema(150);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiObjekat(any(StrSprema.class), anyString())).thenReturn(null);

        SOPretraziStrSprema soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, null);

        assertNull(soSaMockom.getSs());
    }
	
	
	

}
