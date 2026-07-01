/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import db.repozitorijum.DBKonekcija;
import model.Mesto;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Sistemska operacija za brisanje mesta.
 * Pre brisanja proverava da li mesto vec koristi neki kupac, jer se u tom
 * slucaju brisanje ne sme dozvoliti.
 *
 * @author Andjela
 * @see Mesto
 */
public class SOObrisiMesto extends OpsteSistemskeOperacije {

    /** Indikator da li mesto vec koristi neki kupac. */
    private boolean koristiSe=false;
    /** Indikator uspesnosti brisanja mesta. */
    private boolean uspesno=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * mesto koje se brise vec koristi neki kupac.
     *
     * @param param objekat tipa {@link Mesto} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
                if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM kupac km JOIN mesto m ON m.idMesto = km.mesto WHERE m.idMesto = " + ((Mesto) param).getIdMesto();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                koristiSe = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Izvrsava brisanje mesta iz baze podataka. Mesto se brise samo ukoliko
     * ga ne koristi nijedan kupac.
     *
     * @param param objekat tipa {@link Mesto} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(koristiSe!=true){
            broker.obrisi((Mesto)param);
            uspesno=true;
        }
        
    }

    /**
     * Vraca indikator uspesnosti brisanja mesta.
     *
     * @return true ako je mesto uspesno obrisano, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
}
