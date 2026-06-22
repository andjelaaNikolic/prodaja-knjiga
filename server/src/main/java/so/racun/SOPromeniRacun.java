/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import model.Racun;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Knjiga;
import model.StavkaRacuna;

/**
 *
 * @author Ljilja
 */
public class SOPromeniRacun extends OpsteSistemskeOperacije {

    private boolean postoji=false;
    private boolean uspesno=false;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
       if (param == null || !(param instanceof Racun)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
       
       Racun racunZaPromenu = (Racun) param;
       
        try{
       
           List<StavkaRacuna> stavkeProsledjene = racunZaPromenu.getStavke();
           String upit = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin = magacin.idMagacin JOIN racun ON racun.idRacun=stavka_racuna.racun WHERE racun.idRacun="+racunZaPromenu.getIdRacun();
           List<StavkaRacuna> stavkeIzBaze = broker.vratiSve(new StavkaRacuna(), upit);
           
           for (StavkaRacuna sr : stavkeIzBaze) {
               for (StavkaRacuna sr1 : stavkeProsledjene) {
                   int idSR = sr.getRb();
                   int idSR1 = sr1.getRb();
                   Knjiga k = sr.getKnjiga();
                   Knjiga k1 = sr1.getKnjiga();
                   if(idSR!=idSR1){
                       if(k.equals(k1)){
                           postoji = true;
                       }
                   }
               }
           }
        }catch(SQLException ex){
            throw ex;
        }
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
        Racun racunZaPromenu = (Racun) param;
        List<StavkaRacuna> stavkeProsledjene = racunZaPromenu.getStavke();
        String upit = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin = magacin.idMagacin JOIN racun ON racun.idRacun=stavka_racuna.racun WHERE racun.idRacun="+racunZaPromenu.getIdRacun();
        List<StavkaRacuna> stavkeIzBaze = broker.vratiSve(new StavkaRacuna(), upit);
       
  
            
        for (StavkaRacuna sr : stavkeProsledjene) {
        boolean postojiUBazi = false;

        for (StavkaRacuna srBaza : stavkeIzBaze) {
            if (sr.getRb() == srBaza.getRb()) {
            postojiUBazi = true;
            break; 
            }
        }
        
        if (postojiUBazi) {
        broker.promeni(sr); 
        } else {
        sr.setRacun(racunZaPromenu); 
        broker.dodaj(sr); 
        }
        }   
                 
         
        broker.promeni(racunZaPromenu);
        uspesno=true;
        
        }
    } 


    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
