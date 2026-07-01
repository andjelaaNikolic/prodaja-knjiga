/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.repozitorijum.DBKonekcija;
import model.Kupac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 * Sistemska operacija za brisanje kupca.
 * Pre brisanja proverava da li kupac vec ima izdate racune, jer se u tom
 * slucaju brisanje ne sme dozvoliti.
 *
 * @author Andjela
 * @see Kupac
 */
public class SOObrisiKupac extends OpsteSistemskeOperacije {

    /** Indikator uspesnosti brisanja kupca. */
    private boolean uspesno=false;
    /** Indikator da li kupac vec ima izdate racune. */
    private boolean koristiSe=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * kupac koji se brise vec ima izdate racune.
     *
     * @param param objekat tipa {@link Kupac} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null||!(param instanceof Kupac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        try{
        String upit = "SELECT * FROM kupac JOIN racun r ON kupac.idKupac=r.kupac WHERE idKupac="+((Kupac)param).getIdKupac();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        while(rs.next()){
            koristiSe=true;
        }
        }catch(SQLException ex){
            throw ex;
        }
        
        
        
        
    }

    /**
     * Izvrsava brisanje kupca iz baze podataka. Kupac se brise samo ukoliko
     * nema izdatih racuna.
     *
     * @param param objekat tipa {@link Kupac} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(koristiSe!=true){
            broker.obrisi((Kupac)param);
            uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti brisanja kupca.
     *
     * @return true ako je kupac uspesno obrisan, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}

