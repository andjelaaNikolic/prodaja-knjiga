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
 *
 * @author Ljilja
 */
public class SOVratiListuRacunProdavac extends OpsteSistemskeOperacije {

    private List<Racun> racuni;

    public List<Racun> getRacuni() {
        return racuni;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

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
