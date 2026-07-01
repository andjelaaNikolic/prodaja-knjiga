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
 * Sistemska operacija za pretragu jednog racuna na osnovu njegovog ID-a,
 * zajedno sa svim njegovim stavkama.
 *
 * @author Andjela
 * @see Racun
 * @see StavkaRacuna
 */
public class SOPretraziRacun extends OpsteSistemskeOperacije {
    
    /** Racun pronadjen pretragom. */
    private Racun racun;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Racun} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu racuna na osnovu ID-a prosledjenog racuna, zajedno
     * sa prodavcem, kupcem i njegovim mestom. Nakon dobavljanja racuna,
     * dodatno se ucitava i lista njegovih stavki.
     *
     * @param param objekat tipa {@link Racun} koji sadrzi ID za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       
        String uslov = " JOIN prodavac ON prodavac.idProdavac="+((Racun)param).getProdavac().getIdProdavac()+" JOIN kupac ON kupac.idKupac="+((Racun)param).getKupac().getIdKupac()+" JOIN mesto ON mesto.idMesto=kupac.mesto WHERE idRacun="+((Racun)param).getIdRacun();
        racun = (Racun) broker.vratiObjekat(new Racun(), uslov);
        
        String uslov1 = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin JOIN racun ON racun.idRacun=stavka_racuna.racun WHERE idRacun="+((Racun)param).getIdRacun();
        List<StavkaRacuna> stavke = broker.vratiSve(new StavkaRacuna(), uslov1);
        racun.setStavke(stavke);
        this.racun=racun;
        
        
    }

    /**
     * Vraca racun pronadjen pretragom.
     *
     * @return pronadjeni racun, ili null ako racun nije pronadjen
     */
    public Racun getRacun() {
        return racun;
    }
    
    
    
}

