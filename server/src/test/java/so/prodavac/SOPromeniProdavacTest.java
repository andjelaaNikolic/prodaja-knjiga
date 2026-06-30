package so.prodavac;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import model.PrSS;
import model.Prodavac;
import model.StrSprema;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;
 
class SOPromeniProdavacTest {
 
    private SOPromeniProdavac so;
 
    @BeforeEach
    void setUp() {
        so = new SOPromeniProdavac();
    }
 
    @AfterEach
    void tearDown() {
        so = null;
    }
 
    private Prodavac napraviProdavca(List<PrSS> prss) {
        Prodavac p = new Prodavac(1, "Pera", "Peric", "perap", "pera123", "pera@gmail.com");
        p.setPrss(prss);
        return p;
    }
 
    private PrSS napraviPrSS(int idStrucnaSprema, String institucija) {
        StrSprema ss = new StrSprema();
        ss.setIdStrucnaSprema(idStrucnaSprema);
 
        PrSS prss = new PrSS();
        prss.setStrSprema(ss);
        prss.setInstitucija(institucija);
        return prss;
    }
 
    private SOPromeniProdavac napraviSOSaMockom(Repozitorijum mockBroker) throws Exception {
        SOPromeniProdavac soSaMockom = new SOPromeniProdavac();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }
 
    @Test
    void testIsUspesnoPocetnaVrednost() {
        assertFalse(so.isUspesno());
    }
 
    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi(null));
 
        assertEquals("Nije prosledjen parametar odgovarajuceg tipa.", ex.getMessage());
    }
 
    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi("test"));
 
        assertEquals("Nije prosledjen parametar odgovarajuceg tipa.", ex.getMessage());
    }
 

    @Test
    void testPredusloviDuplikatStrucneSpremeSprecavaIzvrsavanje() throws Exception {
        PrSS p1 = napraviPrSS(1, "FON");
        PrSS p2 = napraviPrSS(1, "FON"); 
 
        List<PrSS> prss = new ArrayList<>();
        prss.add(p1);
        prss.add(p2);
 
        Prodavac prodavac = napraviProdavca(prss);
 
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        SOPromeniProdavac soSaMockom = napraviSOSaMockom(mockBroker);
 
        soSaMockom.preduslovi(prodavac);
        soSaMockom.izvrsiOperaciju(prodavac, null);
 
        assertFalse(soSaMockom.isUspesno());
        verify(mockBroker, never()).promeni(any());
        verify(mockBroker, never()).dodaj(any());
    }
 
    @Test
    void testPredusloviRazlicitaInstitucijaNijeDuplikat() throws Exception {
        PrSS p1 = napraviPrSS(1, "ETF");
        PrSS p2 = napraviPrSS(1, "FON"); 
 
        List<PrSS> prss = new ArrayList<>();
        prss.add(p1);
        prss.add(p2);
 
        Prodavac prodavac = napraviProdavca(prss);
 
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(PrSS.class), anyString()))
                .thenReturn(new ArrayList<>());
 
        SOPromeniProdavac soSaMockom = napraviSOSaMockom(mockBroker);
 
        soSaMockom.preduslovi(prodavac);
        soSaMockom.izvrsiOperaciju(prodavac, null);
 
        assertTrue(soSaMockom.isUspesno());
    }
 
    @Test
    void testIzvrsiOperacijuNoviPrSSSeDodaje() throws Exception {
        PrSS noviPrss = napraviPrSS(1, "FON");
 
        List<PrSS> prss = new ArrayList<>();
        prss.add(noviPrss);
 
        Prodavac prodavac = napraviProdavca(prss);
 
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(PrSS.class), anyString()))
                .thenReturn(new ArrayList<>());
 
        SOPromeniProdavac soSaMockom = napraviSOSaMockom(mockBroker);
 
        soSaMockom.preduslovi(prodavac);
        soSaMockom.izvrsiOperaciju(prodavac, null);
 
        assertTrue(soSaMockom.isUspesno());
        verify(mockBroker, times(1)).dodaj(noviPrss);
        verify(mockBroker, never()).promeni(any(PrSS.class));
        verify(mockBroker, times(1)).promeni(prodavac);
        assertEquals(prodavac, noviPrss.getProdavac());
    }
 
 

    @Test
    void testIzvrsiOperacijuObrisanZapisKojiNijeViseUListi() throws Exception {
        PrSS zadrzan = napraviPrSS(1, "FTN");
 
        List<PrSS> prss = new ArrayList<>();
        prss.add(zadrzan);
 
        Prodavac prodavac = napraviProdavca(prss);
 
        PrSS izBazeZadrzan = napraviPrSS(1, "FTN");
        PrSS izBazeUklonjen = napraviPrSS(2, "FON"); 
 
        List<PrSS> prssIzBaze = new ArrayList<>();
        prssIzBaze.add(izBazeZadrzan);
        prssIzBaze.add(izBazeUklonjen);
 
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(PrSS.class), anyString()))
                .thenReturn((List) prssIzBaze);
 
        SOPromeniProdavac soSaMockom = napraviSOSaMockom(mockBroker);
 
        soSaMockom.preduslovi(prodavac);
        soSaMockom.izvrsiOperaciju(prodavac, null);
 
        assertTrue(soSaMockom.isUspesno());
        verify(mockBroker, times(1)).promeni(zadrzan);
        verify(mockBroker, times(1)).obrisi(izBazeUklonjen);
        verify(mockBroker, never()).obrisi(izBazeZadrzan);
        verify(mockBroker, never()).dodaj(any(PrSS.class));
    }
 
    @Test
    void testIzvrsiOperacijuNistaSeNeBriseAkoSeListePoklapaju() throws Exception {
        PrSS jedan = napraviPrSS(1, "FON");
 
        List<PrSS> prss = new ArrayList<>();
        prss.add(jedan);
 
        Prodavac prodavac = napraviProdavca(prss);
 
        PrSS izBaze = napraviPrSS(1, "FON");
        List<PrSS> prssIzBaze = new ArrayList<>();
        prssIzBaze.add(izBaze);
 
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(PrSS.class), anyString()))
                .thenReturn((List) prssIzBaze);
 
        SOPromeniProdavac soSaMockom = napraviSOSaMockom(mockBroker);
 
        soSaMockom.preduslovi(prodavac);
        soSaMockom.izvrsiOperaciju(prodavac, null);
 
        assertTrue(soSaMockom.isUspesno());
        verify(mockBroker, never()).obrisi(any());
    }
 
    @Test
    void testIzvrsiOperacijuPostojeciPrSSSeMenja() throws Exception {
        PrSS postojeci = napraviPrSS(1, "FON");
 
        List<PrSS> prss = new ArrayList<>();
        prss.add(postojeci);
 
        Prodavac prodavac = napraviProdavca(prss);
 
        PrSS izBaze = napraviPrSS(1, "FON");
        List<PrSS> prssIzBaze = new ArrayList<>();
        prssIzBaze.add(izBaze);
 
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(PrSS.class), anyString()))
                .thenReturn((List) prssIzBaze);
 
        SOPromeniProdavac soSaMockom = napraviSOSaMockom(mockBroker);
 
        soSaMockom.preduslovi(prodavac);
        soSaMockom.izvrsiOperaciju(prodavac, null);
 
        assertTrue(soSaMockom.isUspesno());
        verify(mockBroker, times(1)).promeni(postojeci);
        verify(mockBroker, never()).dodaj(any(PrSS.class));
        verify(mockBroker, times(1)).promeni(prodavac);
    }
}