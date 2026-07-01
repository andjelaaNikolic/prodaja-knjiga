/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.repozitorijum.DBKonekcija;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Kupac;
import java.sql.PreparedStatement;

/**
 * Sistemska operacija za promenu podataka postojeceg kupca.
 * Pre izmene proverava da li drugi kupac sa istim brojem telefona vec
 * postoji u bazi podataka.
 *
 * @author Andjela
 * @see Kupac
 */
public class SOPromeniKupac extends OpsteSistemskeOperacije {
    
    /** Indikator uspesnosti izmene kupca. */
    private boolean uspesno=false;
    /** Indikator da li drugi kupac sa istim brojem telefona vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * drugi kupac (razlicit od onog koji se menja) sa istim brojem telefona
     * vec postoji u bazi podataka.
     *
     * @param param objekat tipa {@link Kupac} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null||!(param instanceof Kupac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
      Kupac k = (Kupac) param;
      String upit = "SELECT * FROM kupac WHERE brojTelefona = ? AND idKupac <> ?";
        try (PreparedStatement ps = DBKonekcija.getInstance().getConnection().prepareStatement(upit)) {
            ps.setString(1, k.getBrojTelefona());
            ps.setInt(2, k.getIdKupac());
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    postoji = true;  
                }
            
  
        }catch(SQLException ex){
            throw ex;
        }

    }

    /**
     * Izvrsava izmenu podataka kupca u bazi podataka. Izmena se sprovodi
     * samo ukoliko drugi kupac sa istim brojem telefona ne postoji.
     *
     * @param param objekat tipa {@link Kupac} koji se menja
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       if(postoji!=true){
           broker.promeni((Kupac)param);
           uspesno=true;
       }
    }

    /**
     * Vraca indikator uspesnosti izmene kupca.
     *
     * @return true ako je kupac uspesno izmenjen, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
