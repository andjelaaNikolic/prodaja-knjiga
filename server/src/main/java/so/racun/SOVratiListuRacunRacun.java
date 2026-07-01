/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import java.util.List;
import model.Racun;
import model.StavkaRacuna;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu liste racuna na osnovu ukupnog iznosa.
 *
 * @author Andjela
 * @see Racun
 */
public class SOVratiListuRacunRacun extends OpsteSistemskeOperacije {

    /** Lista racuna koji odgovaraju uslovu pretrage. */
    private List<Racun> racuni;

    /**
     * Vraca listu racuna pronadjenih pretragom.
     *
     * @return lista racuna koji odgovaraju uslovu pretrage
     */
    public List<Racun> getRacuni() {
        return racuni;
    }
    
    
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Racun}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu liste racuna cij ukupan iznos odgovara prosledjenom
     * kljucu, i za svaki pronadjeni racun dodatno ucitava listu njegovih
     * stavki.
     *
     * @param param objekat tipa {@link Racun}
     * @param kljuc ukupan iznos (tipa double) po kojem se racuni pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        double iznos = (double) kljuc;
        
        String upit = " JOIN prodavac ON prodavac.idProdavac = racun.prodavac JOIN kupac ON kupac.idKupac=racun.kupac JOIN mesto ON mesto.idMesto=kupac.mesto WHERE ukupanIznos="+iznos;
        racuni = broker.vratiSve((Racun)param, upit);
        
        for (Racun r : racuni) {
            String uslov1 = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin WHERE racun="+r.getIdRacun();
            List<StavkaRacuna> stavke = broker.vratiSve(new StavkaRacuna(), uslov1);
            r.setStavke(stavke);
        }
        
        
        this.racuni = racuni;
        
        
        
    }
    
}
