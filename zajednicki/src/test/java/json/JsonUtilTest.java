package json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import model.Knjiga;
import model.Kupac;
import model.Magacin;
import model.Mesto;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;


class JsonUtilTest {

    private static final String TEST_PUTANJA_RACUN = "test_racun.json";

    @AfterEach
    void tearDown() {
        new File(TEST_PUTANJA_RACUN).delete();
    }

    @Test
    void testDeserijalizujRacun() throws Exception {
        String putanja = "src/test/resources/test_racun.json";
        String racun = JsonUtil.deserijalizujRacun(putanja);

        assertNotNull(racun);
        assertTrue(racun.contains("=== RACUN br. 1 ==="));
        assertTrue(racun.contains("Pera Peric"));
        assertTrue(racun.contains("Marko Markovic"));
        assertTrue(racun.contains("Beograd"));
        assertTrue(racun.contains("Na Drini cuprija"));
        assertTrue(racun.contains("Roman"));
        assertTrue(racun.contains("5000.0 RSD"));
        assertTrue(racun.contains("42.55 EUR"));
    }

    @Test
    void testDeserijalizujRacunNePostoji() {
        assertThrows(Exception.class, () -> JsonUtil.deserijalizujRacun("src/test/resources/nepostoji.json"));
    }

    @Test
    @Timeout(10)
    void testVratiKursRsdEur() throws Exception {
        double kurs = JsonUtil.vratiKursRsdEur();
        assertTrue(kurs > 0, "Kurs mora biti pozitivan broj");
    }

    @Test
    void testSerijalizujRacunFajlKreiran() throws Exception {
        Racun racun = napraviTestniRacun(501, 5000.0);

        JsonUtil.serijalizujRacun(racun, TEST_PUTANJA_RACUN);

        assertTrue(new File(TEST_PUTANJA_RACUN).exists(), "JSON fajl racuna mora biti kreiran");
    }

    @Test
    void testSerijalizujDeserijalizujRacunIstiPodaci() throws Exception {
        Racun racun = napraviTestniRacun(502, 5000.0);

        JsonUtil.serijalizujRacun(racun, TEST_PUTANJA_RACUN);
        String tekst = JsonUtil.deserijalizujRacun(TEST_PUTANJA_RACUN);

        assertNotNull(tekst, "Deserijalizovan racun ne sme biti null");
        assertTrue(tekst.contains("502"));
        assertTrue(tekst.contains("Pera"));
        assertTrue(tekst.contains("Marko"));
        assertTrue(tekst.contains("Na Drini cuprija"));
        assertTrue(tekst.contains("5000.0 RSD"));
    }

    @Test
    void testSerijalizujRacunPraznaListaStavki() throws Exception {
        Mesto mesto = new Mesto(1, "Beograd");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "0611234567", mesto);
        Prodavac prodavac = new Prodavac(1, "Pera", "Peric", "pera123", "sifra123", "pera@gmail.com");

        Racun racun = new Racun(new Date(), 0.01, prodavac, kupac);
        racun.setIdRacun(503);

        assertDoesNotThrow(() -> JsonUtil.serijalizujRacun(racun, TEST_PUTANJA_RACUN),
                "Ne sme biti greske pri serijalizaciji racuna sa praznom listom stavki");
        assertTrue(new File(TEST_PUTANJA_RACUN).exists());
    }


    private Racun napraviTestniRacun(int id, double ukupanIznos) {
        Mesto mesto = new Mesto(1, "Beograd");
        Kupac kupac = new Kupac(1, "Marko", "Markovic", "0611234567", mesto);
        Prodavac prodavac = new Prodavac(1, "Pera", "Peric", "pera123", "sifra123", "pera@gmail.com");

        Magacin magacin = new Magacin(1, "Magacin1", "Bulevar 1");
        Knjiga knjiga = new Knjiga(1, "Na Drini cuprija", "Roman", 1945, 2500.0, 10, magacin);

        Racun racun = new Racun(new Date(), ukupanIznos, prodavac, kupac);
        racun.setIdRacun(id);

        StavkaRacuna stavka = new StavkaRacuna(1, 2, knjiga, ukupanIznos, racun, 2500.0);
        List<StavkaRacuna> stavke = new ArrayList<>();
        stavke.add(stavka);
        racun.setStavke(stavke);

        return racun;
    }
}