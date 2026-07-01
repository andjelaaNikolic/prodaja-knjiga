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
 * Sistemska operacija za brisanje strucne spreme.
 * Pre brisanja proverava da li strucnu spremu vec poseduje neki prodavac,
 * jer se u tom slucaju brisanje ne sme dozvoliti.
 *
 * @author Andjela
 * @see StrSprema
 */
public class SOObrisiStrSprema extends OpsteSistemskeOperacije {
    
    /** Indikator uspesnosti brisanja strucne spreme. */
    private boolean uspesno = false;
    
    /** Indikator da li strucnu spremu vec poseduje neki prodavac. */
    private boolean koristiSe=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * strucnu spremu koja se brise vec poseduje neki prodavac.
     *
     * @param param objekat tipa {@link StrSprema} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof StrSprema)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
        
        try{
            String upit = "SELECT * FROM str_sprema JOIN prss ps ON str_sprema.idStrucnaSprema=ps.strSprema WHERE idStrucnaSprema="+((StrSprema)param).getIdStrucnaSprema();
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
     * Izvrsava brisanje strucne spreme iz baze podataka. Strucna sprema se
     * brise samo ukoliko je ne poseduje nijedan prodavac.
     *
     * @param param objekat tipa {@link StrSprema} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(koristiSe!=true){
            broker.obrisi((StrSprema)param);
            uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti brisanja strucne spreme.
     *
     * @return true ako je strucna sprema uspesno obrisana, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}

