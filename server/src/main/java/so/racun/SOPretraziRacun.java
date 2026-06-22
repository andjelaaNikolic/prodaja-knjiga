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
 *
 * @author Ljilja
 */
public class SOPretraziRacun extends OpsteSistemskeOperacije {
    
    private Racun racun;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       
        String uslov = " JOIN prodavac ON prodavac.idProdavac="+((Racun)param).getProdavac().getIdProdavac()+" JOIN kupac ON kupac.idKupac="+((Racun)param).getKupac().getIdKupac()+" JOIN mesto ON mesto.idMesto=kupac.mesto WHERE idRacun="+((Racun)param).getIdRacun();
        racun = (Racun) broker.vratiObjekat(new Racun(), uslov);
        
        String uslov1 = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin JOIN racun ON racun.idRacun=stavka_racuna.racun WHERE idRacun="+((Racun)param).getIdRacun();
        List<StavkaRacuna> stavke = broker.vratiSve(new StavkaRacuna(), uslov1);
        racun.setStavke(stavke);
        this.racun=racun;
        
        
    }

    public Racun getRacun() {
        return racun;
    }
    
    
    
}
