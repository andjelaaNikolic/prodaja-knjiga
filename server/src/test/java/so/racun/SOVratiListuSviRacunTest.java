package so.racun;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Racun;
import model.StavkaRacuna;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOVratiListuSviRacunTest {

    private SOVratiListuSviRacun so;

    @BeforeEach
    void setUp() throws Exception {
        so = new SOVratiListuSviRacun();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    @Test
    void testPredusloviNULL() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(null));
        assertEquals("Nije prosledjen parametar odgovarajuceg tipa.", ex.getMessage());
    }

    @Test
    void testPredusloviObjekatDrugaciji() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(new String()));
        assertEquals("Nije prosledjen parametar odgovarajuceg tipa.", ex.getMessage());
    }

    @Test
    void testGetRacuniPocetnaVrednost() {
        assertNull(so.getRacuni(), "Početna vrednost liste racuna mora biti null");
    }

    private SOVratiListuSviRacun napraviSOSaMockom(Repozitorijum mockBroker) throws Exception {
        SOVratiListuSviRacun soSaMockom = new SOVratiListuSviRacun();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiOperacijuVracaListuSvihRacunaSaStavkama() throws Exception {
        Racun r1 = new Racun();
        r1.setIdRacun(1);

        Racun r2 = new Racun();
        r2.setIdRacun(2);

        List<Racun> ocekivanaListaRacuna = new ArrayList<>();
        ocekivanaListaRacuna.add(r1);
        ocekivanaListaRacuna.add(r2);

        StavkaRacuna s1 = new StavkaRacuna();
        s1.setKolicina(2);

        List<StavkaRacuna> ocekivaneStavke = new ArrayList<>();
        ocekivaneStavke.add(s1);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(Racun.class), anyString()))
                .thenReturn((List) ocekivanaListaRacuna);
        when(mockBroker.vratiSve(any(StavkaRacuna.class), anyString()))
                .thenReturn((List) ocekivaneStavke);

        SOVratiListuSviRacun soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(new Racun(), null);

        assertNotNull(soSaMockom.getRacuni());
        assertEquals(2, soSaMockom.getRacuni().size());
        assertTrue(soSaMockom.getRacuni().contains(r1));
        assertTrue(soSaMockom.getRacuni().contains(r2));

        for (Racun r : soSaMockom.getRacuni()) {
            assertEquals(ocekivaneStavke, r.getStavke());
        }
    }

    @Test
    void testIzvrsiOperacijuPraznaListaKadaNemaRacuna() throws Exception {
        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiSve(any(Racun.class), anyString()))
                .thenReturn(new ArrayList<>());

        SOVratiListuSviRacun soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(new Racun(), null);

        assertNotNull(soSaMockom.getRacuni());
        assertTrue(soSaMockom.getRacuni().isEmpty());
    }
}