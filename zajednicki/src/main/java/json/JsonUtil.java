package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Klasa za serijalizaciju i deserijalizaciju JSON fajlova.
 * Koristi Gson biblioteku za konverziju Java objekata u JSON format i obrnuto.
 * 
 * @author Andjela
 */
public class JsonUtil {

    /**
     * Format koji se koristi za prikaz datuma u racunu: "dd.MM.yyyy.".
     */
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy.");

    /**
     * Serijalizuje dati racun u JSON fajl na zadatoj putanji.
     * Racun sadrzi osnovne podatke o racunu, podatke o prodavcu i kupcu,
     * sve stavke racuna, i ukupan iznos u RSD. Ukoliko je dostupna internet
     * konekcija, dodaje se i ukupan iznos preracunat u EUR na osnovu trenutnog
     * kursa; u suprotnom se za iznos u EUR upisuje vrednost "N/A".
     * Ako folder na zadatoj putanji ne postoji, kreira se automatski.
     *
     * @param racun racun koji se serijalizuje
     * @param putanja putanja fajla u koji se racun upisuje
     * @throws IOException ako dodje do greske prilikom pisanja u fajl
     */
    public static void serijalizujRacun(Racun racun, String putanja) throws IOException {
        JsonObject json = racunUJson(racun);

        java.io.File folder = new java.io.File(putanja).getParentFile();
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }

        try (FileWriter writer = new FileWriter(putanja)) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(json));
        }
    }

    /**
     * Kreira JSON reprezentaciju datog racuna.
     * Ukljucuje osnovne podatke o racunu, podatke o prodavcu i kupcu, sve
     * stavke racuna i ukupan iznos u RSD. Ukoliko je dostupna internet
     * konekcija, dodaje se i ukupan iznos preracunat u EUR na osnovu
     * trenutnog kursa; u suprotnom se za iznos u EUR upisuje vrednost "N/A".
     *
     * @param racun racun koji se pretvara u JSON
     * @return JSON objekat koji predstavlja dati racun
     */
    private static JsonObject racunUJson(Racun racun) {
        JsonObject json = new JsonObject();
        json.addProperty("idRacun", racun.getIdRacun());
        json.addProperty("datum", FORMATTER.format(racun.getDatum()));
        json.addProperty("ukupanIznos", racun.getUkupanIznos());

        try {
            double kurs = vratiKursRsdEur();
            double ukupanIznosEUR = Math.round(racun.getUkupanIznos() * kurs * 100.0) / 100.0;
            json.addProperty("ukupanIznosEUR", ukupanIznosEUR);
        } catch (Exception e) {
            System.err.println("Upozorenje: Nije moguce dobiti kurs valuta: " + e.getMessage());
            json.addProperty("ukupanIznosEUR", "N/A");
        }

        Prodavac prodavac = racun.getProdavac();
        JsonObject prodavacJson = new JsonObject();
        prodavacJson.addProperty("ime", prodavac.getIme());
        prodavacJson.addProperty("prezime", prodavac.getPrezime());
        prodavacJson.addProperty("korisnickoIme", prodavac.getKorisnickoIme());
        json.add("prodavac", prodavacJson);

        Kupac kupac = racun.getKupac();
        JsonObject kupacJson = new JsonObject();
        kupacJson.addProperty("ime", kupac.getIme());
        kupacJson.addProperty("prezime", kupac.getPrezime());
        kupacJson.addProperty("mesto", kupac.getMesto().getNazivMesta());
        json.add("kupac", kupacJson);

        JsonArray stavkeJson = new JsonArray();
        for (StavkaRacuna s : racun.getStavke()) {
            Knjiga k = s.getKnjiga();
            JsonObject stavkaJson = new JsonObject();
            stavkaJson.addProperty("rb", s.getRb());
            stavkaJson.addProperty("naslovKnjige", k.getNaslov());
            stavkaJson.addProperty("zanr", k.getZanr());
            stavkaJson.addProperty("kolicina", s.getKolicina());
            stavkaJson.addProperty("jedinicnaCena", s.getJedinicnaCena());
            stavkaJson.addProperty("iznos", s.getIznos());
            stavkeJson.add(stavkaJson);
        }
        json.add("stavke", stavkeJson);

        return json;
    }

    /**
     * Deserijalizuje racun iz JSON fajla na zadatoj putanji i formatira ga
     * kao tekstualni prikaz pogodan za ispis korisniku.
     *
     * @param putanja putanja JSON fajla koji sadrzi racun
     * @return tekstualni prikaz racuna sa podacima o racunu, prodavcu,
     *         kupcu, svim stavkama i ukupnim iznosom (u RSD, i u EUR ako je dostupan)
     * @throws IOException ako dodje do greske prilikom citanja fajla
     */
    public static String deserijalizujRacun(String putanja) throws IOException {
        try (FileReader reader = new FileReader(putanja)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            StringBuilder sb = new StringBuilder();
            sb.append("=== RACUN br. ").append(json.get("idRacun").getAsInt()).append(" ===\n");
            sb.append("Datum: ").append(json.get("datum").getAsString()).append("\n\n");

            JsonObject prodavac = json.getAsJsonObject("prodavac");
            sb.append("Prodavac: ").append(prodavac.get("ime").getAsString())
                    .append(" ").append(prodavac.get("prezime").getAsString()).append("\n");

            JsonObject kupac = json.getAsJsonObject("kupac");
            sb.append("Kupac: ").append(kupac.get("ime").getAsString())
                    .append(" ").append(kupac.get("prezime").getAsString())
                    .append(" (").append(kupac.get("mesto").getAsString()).append(")\n\n");

            sb.append("--- Stavke ---\n");
            JsonArray stavke = json.getAsJsonArray("stavke");
            for (int i = 0; i < stavke.size(); i++) {
                JsonObject stavka = stavke.get(i).getAsJsonObject();
                sb.append(stavka.get("rb").getAsInt()).append(". ")
                        .append(stavka.get("naslovKnjige").getAsString())
                        .append(" (").append(stavka.get("zanr").getAsString()).append(")\n");
                sb.append("   Kolicina: ").append(stavka.get("kolicina").getAsInt())
                        .append(", Jedinicna cena: ").append(stavka.get("jedinicnaCena").getAsDouble())
                        .append(" RSD, Iznos: ").append(stavka.get("iznos").getAsDouble()).append(" RSD\n");
            }

            sb.append("\nUkupan iznos: ").append(json.get("ukupanIznos").getAsDouble()).append(" RSD");
            if (json.has("ukupanIznosEUR") && !json.get("ukupanIznosEUR").getAsString().equals("N/A")) {
                sb.append("\nUkupan iznos: ").append(json.get("ukupanIznosEUR").getAsDouble()).append(" EUR");
            }
            return sb.toString();
        }
    }

    /**
     * Izvozi listu racuna u JSON fajl na zadatoj putanji, u vidu skracenih
     * sazetaka (ID racuna, datum, ukupan iznos, korisnicko ime prodavca i
     * ime i prezime kupca), bez pojedinacnih stavki.
     * Ako folder na zadatoj putanji ne postoji, kreira se automatski.
     *
     * @param racuni lista racuna koja se izvozi
     * @param putanja putanja fajla u koji se lista upisuje
     * @throws IOException ako dodje do greske prilikom pisanja u fajl
     */
    public static void izvezListuRacuna(List<Racun> racuni, String putanja) throws IOException {
        JsonArray niz = new JsonArray();

        for (Racun r : racuni) {
            JsonObject sazetak = new JsonObject();
            sazetak.addProperty("idRacun", r.getIdRacun());
            sazetak.addProperty("datum", FORMATTER.format(r.getDatum()));
            sazetak.addProperty("ukupanIznos", r.getUkupanIznos());
            sazetak.addProperty("prodavac", r.getProdavac().getKorisnickoIme());
            sazetak.addProperty("kupac", r.getKupac().getIme() + " " + r.getKupac().getPrezime());
            niz.add(sazetak);
        }

        java.io.File folder = new java.io.File(putanja).getParentFile();
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }

        try (FileWriter writer = new FileWriter(putanja)) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(niz));
        }
    }

    /**
     * Racuna ukupan prihod na osnovu liste racuna izvezene u JSON fajl na
     * zadatoj putanji (metodom {@link #izvezListuRacuna(List, String)}),
     * sabiranjem ukupnog iznosa svih racuna iz fajla.
     *
     * @param putanja putanja JSON fajla koji sadrzi izvezenu listu racuna
     * @return ukupan prihod, kao suma ukupnih iznosa svih racuna iz fajla
     * @throws IOException ako dodje do greske prilikom citanja fajla
     */
    public static double izracunajUkupanPrihod(String putanja) throws IOException {
        try (FileReader reader = new FileReader(putanja)) {
            JsonArray niz = JsonParser.parseReader(reader).getAsJsonArray();

            double ukupno = 0;
            for (int i = 0; i < niz.size(); i++) {
                JsonObject racun = niz.get(i).getAsJsonObject();
                ukupno += racun.get("ukupanIznos").getAsDouble();
            }
            return ukupno;
        }
    }

    /**
     * Dobavlja trenutni kurs RSD u EUR pozivanjem eksternog web servisa
     * (open.er-api.com).
     *
     * @return kurs RSD u EUR (koliko EUR vredi 1 RSD)
     * @throws Exception ako dodje do greske prilikom poziva web servisa
     *         ili obrade odgovora
     */
    public static double vratiKursRsdEur() throws Exception {
        String urlString = "https://open.er-api.com/v6/latest/RSD";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("rates");
        return rates.get("EUR").getAsDouble();
    }
}