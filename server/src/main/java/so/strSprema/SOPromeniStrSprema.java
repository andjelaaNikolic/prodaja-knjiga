/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strSprema;

import db.repozitorijum.DBKonekcija;
import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sistemska operacija za promenu podataka postojece strucne spreme.
 * Pre izmene proverava da li druga strucna sprema sa istim stepenom vec
 * postoji u bazi podataka.
 *
 * @author Andjela
 * @see StrSprema
 */
public class SOPromeniStrSprema extends OpsteSistemskeOperacije {
    
    /** Indikator uspesnosti izmene strucne spreme. */
    private boolean uspesno = false;
    
    /** Indikator da li druga strucna sprema sa istim stepenom vec postoji u bazi podataka. */
    private boolean postoji=false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * druga strucna sprema (razlicita od one koja se menja) sa istim
     * stepenom vec postoji u bazi podataka.
     *
     * @param param objekat tipa {@link StrSprema} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof StrSprema)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
        try{
        
        String upit = "SELECT * FROM str_sprema WHERE stepen='"+((StrSprema)param).getStepen()+"' AND idStrucnaSprema <> "+((StrSprema)param).getIdStrucnaSprema();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        while(rs.next()){
            postoji=true;
        }
        
        }catch(SQLException ex){
            throw ex;
        }
 
        
    }

    /**
     * Izvrsava izmenu podataka strucne spreme u bazi podataka. Izmena se
     * sprovodi samo ukoliko druga strucna sprema sa istim stepenom ne
     * postoji.
     *
     * @param param objekat tipa {@link StrSprema} koji se menja
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
            broker.promeni((StrSprema) param);
            uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti izmene strucne spreme.
     *
     * @return true ako je strucna sprema uspesno izmenjena, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
