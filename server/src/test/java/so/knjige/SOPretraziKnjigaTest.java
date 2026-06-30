package so.knjige;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Knjiga;
import model.Magacin;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;


class SOPretraziKnjigaTest {

    SOPretraziKnjiga so;

    @BeforeEach
    void setUp() throws Exception {
        so = new SOPretraziKnjiga();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    @Test
    void testGetKPocetnaVrednost() {
        assertNull(so.getK(), "Početna vrednost atributa k mora biti null");
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(null));
        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.",ex.getMessage());
    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi("pogrešan tip"));
        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.",ex.getMessage());
    }

    private SOPretraziKnjiga napraviSOSaMockom(Repozitorijum mockBroker) throws Exception {
        SOPretraziKnjiga soSaMockom = new SOPretraziKnjiga();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testIzvrsiOperacijuVracaPronadjenuKnjigu() throws Exception {
        Magacin magacin = new Magacin(1, "Centralni", "Bulevar 1");
        Knjiga trazenaKnjiga = new Knjiga(5, "Na Drini ćuprija", "Roman", 2020, 990, 10, magacin);

        Knjiga parametar = new Knjiga();
        parametar.setIdKnjiga(5);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiObjekat(any(Knjiga.class), anyString())).thenReturn(trazenaKnjiga);

        SOPretraziKnjiga soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, null);

        assertNotNull(soSaMockom.getK());
        assertEquals(trazenaKnjiga, soSaMockom.getK());
        verify(mockBroker, times(1)).vratiObjekat(any(Knjiga.class), contains("idKnjiga=5"));
    }

    @Test
    void testIzvrsiOperacijuKnjigaNijePronadjenaVracaNull() throws Exception {
        Knjiga parametar = new Knjiga();
        parametar.setIdKnjiga(99);

        Repozitorijum mockBroker = mock(Repozitorijum.class);
        when(mockBroker.vratiObjekat(any(Knjiga.class), anyString())).thenReturn(null);

        SOPretraziKnjiga soSaMockom = napraviSOSaMockom(mockBroker);

        soSaMockom.izvrsiOperaciju(parametar, null);

        assertNull(soSaMockom.getK());
    }
}
