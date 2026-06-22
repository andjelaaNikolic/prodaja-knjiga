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
 *
 * @author Ljilja
 */
public class SOPromeniStrSprema extends OpsteSistemskeOperacije {
    
    private boolean uspesno = false;
    private boolean postoji=false;

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

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
            broker.promeni((StrSprema) param);
            uspesno=true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
