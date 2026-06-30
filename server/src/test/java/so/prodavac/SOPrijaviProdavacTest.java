package so.prodavac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Prodavac;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOPrijaviProdavacTest {

    private SOPrijaviProdavac so;

    @BeforeEach
    void setUp() {
        so = new SOPrijaviProdavac();
    }

    @AfterEach
    void tearDown() {
        so = null;
    }

    private Prodavac napraviProdavca(String korisnickoIme, String sifra) {
        return new Prodavac(1, "Pera", "Peric", korisnickoIme, sifra, "pera@gmail.com");
    }

    private SOPrijaviProdavac napraviSOSaMockom(Repozitorijum mockBroker) throws Exception {
        SOPrijaviProdavac soSaMockom = new SOPrijaviProdavac();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testGetProdavacPocetnaVrednost() {
        assertNull(so.getProdavac());
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi(null));

        assertEquals("Sistem ne moze da nadje prodavca", ex.getMessage());
    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi("test"));

        assertEquals("Sistem ne moze da nadje prodavca", ex.getMessage());
    }

    @Test
    void testIzvrsiOperacijuProdavacPostojiIPodaciSeSlazu() throws Exception {
        Prodavac trazeni = napraviProdavca("perap", "pera123");

        Prodavac drugi = napraviProdavca("mikam", "mika123");

        List<Prodavac> svi = new ArrayList<>();
        svi.add(drugi);
        svi.add(trazeni);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(Prodavac.class), isNull()))
                .thenReturn((List) svi);

        SOPrijaviProdavac soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.preduslovi(trazeni);
        soSaMockom.izvrsiOperaciju(trazeni, null);

        assertNotNull(soSaMockom.getProdavac());
        assertEquals("perap", soSaMockom.getProdavac().getKorisnickoIme());
        assertEquals("pera123", soSaMockom.getProdavac().getSifra());
    }

    @Test
    void testIzvrsiOperacijuPogresnaSifraProdavacNijePronadjen() throws Exception {
        Prodavac uneto = napraviProdavca("perap", "pogresnaSifra");
        Prodavac ustvariUBazi = napraviProdavca("perap", "pera123");

        List<Prodavac> svi = new ArrayList<>();
        svi.add(ustvariUBazi);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(Prodavac.class), isNull()))
                .thenReturn((List) svi);

        SOPrijaviProdavac soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.preduslovi(uneto);
        soSaMockom.izvrsiOperaciju(uneto, null);

        assertNull(soSaMockom.getProdavac());
    }

    @Test
    void testIzvrsiOperacijuPraznaListaProdavacaProdavacNijePronadjen() throws Exception {
        Prodavac uneto = napraviProdavca("perap", "pera123");

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(Prodavac.class), isNull()))
                .thenReturn(new ArrayList<>());

        SOPrijaviProdavac soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.preduslovi(uneto);
        soSaMockom.izvrsiOperaciju(uneto, null);

        assertNull(soSaMockom.getProdavac());
    }
}