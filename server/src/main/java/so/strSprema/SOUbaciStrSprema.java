/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strSprema;

import db.repozitorijum.DBKonekcija;
import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 * Sistemska operacija za kreiranje nove strucne spreme.
 * Pre kreiranja proverava da li strucna sprema sa istim stepenom vec
 * postoji u bazi podataka.
 *
 * @author Andjela
 * @see StrSprema
 */
public class SOUbaciStrSprema extends OpsteSistemskeOperacije {
    
    /** Indikator uspesnosti kreiranja strucne spreme. */
    private boolean uspesno=false;
    /** Indikator da li strucna sprema sa istim stepenom vec postoji u bazi podataka. */
    private boolean postoji=false;
    

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * strucna sprema sa istim stepenom vec postoji u bazi podataka.
     *
     * @param param objekat tipa {@link StrSprema} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof StrSprema)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
        try{
        
        String upit = "SELECT * FROM str_sprema WHERE stepen='"+((StrSprema)param).getStepen()+"'";
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
     * Izvrsava kreiranje strucne spreme u bazi podataka. Strucna sprema se
     * upisuje samo ukoliko strucna sprema sa istim stepenom jos uvek ne
     * postoji u bazi.
     *
     * @param param objekat tipa {@link StrSprema} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
            broker.dodaj((StrSprema)param);
            uspesno = true;
        }
    }

    /**
     * Vraca indikator uspesnosti kreiranja strucne spreme.
     *
     * @return true ako je strucna sprema uspesno kreirana, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
}

