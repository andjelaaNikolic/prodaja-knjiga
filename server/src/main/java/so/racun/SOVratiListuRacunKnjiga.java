/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import java.util.ArrayList;
import java.util.List;
import model.Knjiga;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuRacunKnjiga extends OpsteSistemskeOperacije {
    
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
        Knjiga k = (Knjiga) kljuc;
        String upit = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin JOIN racun ON racun.idRacun=stavka_racuna.racun WHERE knjiga.idKnjiga="+k.getIdKnjiga();
        List<StavkaRacuna> stavke = broker.vratiSve(new StavkaRacuna(), upit);
        racuni = new ArrayList<>();
        
  
       for (StavkaRacuna ss : stavke) {
        int idRacun = ss.getRacun().getIdRacun();
        String upit1 = " JOIN prodavac ON prodavac.idProdavac = racun.prodavac JOIN kupac ON kupac.idKupac=racun.kupac JOIN mesto ON mesto.idMesto=kupac.mesto WHERE racun.idRacun="+idRacun;
        Racun racun = (Racun) broker.vratiObjekat(new Racun(), upit1);
        racuni.add(racun);
        }
       
       for (Racun r : racuni) {
            String uslov1 = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin WHERE racun="+r.getIdRacun();
            r.setStavke(stavke);
        }
       
       
       
        this.racuni=racuni;
       
       

   
       
       
    }
    
}
