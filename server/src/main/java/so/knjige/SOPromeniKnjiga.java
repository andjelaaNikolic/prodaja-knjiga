/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import db.repozitorijum.DBKonekcija;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Knjiga;
/**
 * Sistemska operacija za promenu podataka postojece knjige.
 * Pre izmene proverava da li druga knjiga sa istim naslovom vec postoji u
 * bazi podataka.
 *
 * @author Andjela
 * @see Knjiga
 */
public class SOPromeniKnjiga extends OpsteSistemskeOperacije {
    
    /**  Vrednost koja oznacava uspesnost izmene knjige. */
    private boolean uspesno=false;
    /**  Vrednost koja oznacava da li druga knjiga sa istim naslovom vec postoji u bazi podataka. */
    private boolean postoji=false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * druga knjiga (razlicita od one koja se menja) sa istim naslovom vec
     * postoji u bazi podataka.
     *
     * @param param objekat tipa {@link Knjiga} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Knjiga)){
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Knjiga knjiga = (Knjiga) param;
          try {

            String upit = "SELECT * FROM knjiga WHERE naslov='" + ((Knjiga) param).getNaslov()+"' AND idKnjiga <>"+((Knjiga)param).getIdKnjiga();
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
     * Izvrsava izmenu podataka knjige u bazi podataka. Izmena se sprovodi
     * samo ukoliko druga knjiga sa istim naslovom ne postoji.
     *
     * @param param objekat tipa {@link Knjiga} koji se menja
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
            
            broker.promeni((Knjiga)param);
            uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti izmene knjige.
     *
     * @return true ako je knjiga uspesno izmenjena, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
    
}
