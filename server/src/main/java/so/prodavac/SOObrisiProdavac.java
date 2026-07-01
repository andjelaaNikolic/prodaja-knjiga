/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import db.repozitorijum.DBKonekcija;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


/**
 * Sistemska operacija za brisanje prodavca.
 * Pre brisanja proverava da li prodavac vec ima izdate racune ili unete
 * strucne spreme, jer se u tom slucaju brisanje ne sme dozvoliti.
 *
 * @author Andjela
 * @see Prodavac
 */
public class SOObrisiProdavac extends OpsteSistemskeOperacije {
    
    /** Indikator uspesnosti brisanja prodavca. */
    private boolean uspesno=false;
    /** Indikator da li prodavac vec ima izdate racune ili unete strucne spreme. */
    private boolean koristiSe=false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * prodavac koji se brise vec ima izdate racune ili unete strucne
     * spreme.
     *
     * @param param objekat tipa {@link Prodavac} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
       if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
       
       try{
           String upit1 = "SELECT * FROM prodavac JOIN racun ON racun.prodavac="+((Prodavac)param).getIdProdavac();
           Statement st = DBKonekcija.getInstance().getConnection().createStatement();
           ResultSet rs = st.executeQuery(upit1);
           while(rs.next()){
               koristiSe=true;
           }
       }catch(SQLException ex){
           throw ex;
       }
       try{
           String upit2 = "SELECT * FROM prodavac JOIN prss ON prss.prodavac="+((Prodavac)param).getIdProdavac();
           Statement st = DBKonekcija.getInstance().getConnection().createStatement();
           ResultSet rs = st.executeQuery(upit2);
           while(rs.next()){
               koristiSe=true;
           }
       }catch(SQLException ex){
           throw ex;
       }
       
       
       
       
       
    }

    /**
     * Izvrsava brisanje prodavca iz baze podataka. Prodavac se brise samo
     * ukoliko nema izdatih racuna niti unetih strucnih sprema.
     *
     * @param param objekat tipa {@link Prodavac} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(koristiSe!=true){
            broker.obrisi((Prodavac)param);
            uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti brisanja prodavca.
     *
     * @return true ako je prodavac uspesno obrisan, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}

