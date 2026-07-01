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
 * Sistemska operacija za promenu podataka postojeceg mesta.
 * Pre izmene proverava da li drugo mesto sa istim nazivom vec postoji u
 * bazi podataka.
 *
 * @author Andjela
 * @see Mesto
 */
public class SOPromeniMesto extends OpsteSistemskeOperacije {

    /** Indikator da li drugo mesto sa istim nazivom vec postoji u bazi podataka. */
    private boolean postoji=false;
    /** Indikator uspesnosti izmene mesta. */
    private boolean uspesno=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * mesto sa istim nazivom vec postoji u bazi podataka.
     *
     * @param param objekat tipa {@link Mesto} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Mesto)){
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Mesto mesto = (Mesto) param;
          try {

            String upit = "SELECT * FROM mesto WHERE nazivMesta='" + ((Mesto) param).getNazivMesta()+"'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
        
        
    }

    /**
     * Izvrsava izmenu podataka mesta u bazi podataka. Izmena se sprovodi
     * samo ukoliko mesto sa istim nazivom ne postoji.
     *
     * @param param objekat tipa {@link Mesto} koji se menja
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
        broker.promeni((Mesto)param);
        uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti izmene mesta.
     *
     * @return true ako je mesto uspesno izmenjeno, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
