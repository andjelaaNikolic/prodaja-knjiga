/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import db.repozitorijum.DBKonekcija;
import model.Mesto;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 * Sistemska operacija za kreiranje novog mesta.
 * Pre kreiranja proverava da li je naziv mesta validan, i da li mesto sa
 * istim nazivom vec postoji u bazi podataka.
 *
 * @author Andjela
 * @see Mesto
 */
public class SOUbaciMesto extends OpsteSistemskeOperacije {

    /** Indikator da li mesto sa istim nazivom vec postoji u bazi podataka. */
    private boolean postoji=false;
    /** Indikator uspesnosti kreiranja mesta. */
    private boolean uspesno=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li je
     * naziv mesta validan, i da li mesto sa istim nazivom vec postoji u
     * bazi podataka.
     *
     * @param param objekat tipa {@link Mesto} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako naziv
     *         mesta nije validan
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Mesto)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        Mesto mesto = (Mesto) param;
        if(mesto.getNazivMesta()==null || mesto.getNazivMesta().isEmpty()){
            throw new Exception("Naziv mesta je neispravan");
        }
        
        
          try {

            String upit = "SELECT * FROM mesto WHERE nazivMesta='" + ((Mesto) param).getNazivMesta()+"'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            if (rs.next()) {
                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
        
        
        
    }

    /**
     * Izvrsava kreiranje mesta u bazi podataka. Mesto se upisuje samo
     * ukoliko mesto sa istim nazivom jos uvek ne postoji u bazi.
     *
     * @param param objekat tipa {@link Mesto} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
        broker.dodaj((Mesto)param);
        uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti kreiranja mesta.
     *
     * @return true ako je mesto uspesno kreirano, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
    
}

