/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import java.util.List;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu liste racuna koje je izdao odredjeni
 * prodavac.
 *
 * @author Andjela
 * @see Racun
 * @see Prodavac
 */
public class SOVratiListuRacunProdavac extends OpsteSistemskeOperacije {

    /** Lista racuna koje je izdao prosledjeni prodavac. */
    private List<Racun> racuni;

    /**
     * Vraca listu racuna pronadjenih pretragom.
     *
     * @return lista racuna koje je izdao prosledjeni prodavac
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
     * Izvrsava pretragu liste racuna koje je izdao prodavac prosledjen kroz
     * kljuc, i za svaki pronadjeni racun dodatno ucitava listu njegovih
     * stavki.
     *
     * @param param objekat tipa {@link Racun}
     * @param kljuc objekat tipa {@link Prodavac} po kojem se racuni pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        Prodavac prodavac = (Prodavac) kljuc;
        String upit = " JOIN prodavac ON prodavac.idProdavac=racun.prodavac JOIN kupac ON kupac.idKupac=racun.kupac JOIN mesto ON mesto.idMesto=kupac.mesto WHERE prodavac.idProdavac="+prodavac.getIdProdavac();
        racuni = broker.vratiSve((Racun)param, upit);
         for (Racun r : racuni) {
            String uslov1 = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin WHERE racun="+r.getIdRacun();
            List<StavkaRacuna> stavke = broker.vratiSve(new StavkaRacuna(), uslov1);
            r.setStavke(stavke);
        }
         this.racuni=racuni;
        
    }
    
}

