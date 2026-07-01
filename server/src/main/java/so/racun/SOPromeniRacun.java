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
 * Sistemska operacija za promenu podataka postojeceg racuna, ukljucujuci i
 * uskladjivanje njegove liste stavki ({@link StavkaRacuna}) sa stanjem u
 * bazi podataka.
 * Pre izmene proverava da li se u prosledjenoj listi stavki nalaze dve
 * razlicite stavke koje se odnose na istu knjigu, sto nije dozvoljeno.
 *
 * @author Andjela
 * @see Racun
 * @see StavkaRacuna
 */
public class SOPromeniRacun extends OpsteSistemskeOperacije {

    /** Indikator da li prosledjena lista stavki sadrzi dve razlicite stavke sa istom knjigom. */
    private boolean postoji=false;
    /** Indikator uspesnosti izmene racuna. */
    private boolean uspesno=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, i da li
     * se u prosledjenoj listi stavki racuna nalaze dve razlicite stavke
     * (razlicitog rednog broja) koje se odnose na istu knjigu.
     *
     * @param param objekat tipa {@link Racun} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
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

    /**
     * Izvrsava izmenu podataka racuna u bazi podataka. Uporedjuje
     * prosledjenu listu stavki sa onom iz baze: postojece stavke azurira, a
     * nove (koje jos nisu u bazi) dodaje povezane sa racunom. Na kraju
     * azurira i osnovne podatke racuna.
     *
     * @param param objekat tipa {@link Racun} koji se menja
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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


    /**
     * Vraca indikator uspesnosti izmene racuna.
     *
     * @return true ako je racun uspesno izmenjen, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}

