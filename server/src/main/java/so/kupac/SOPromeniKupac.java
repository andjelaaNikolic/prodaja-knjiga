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
 *
 * @author Ljilja
 */
public class SOPromeniKupac extends OpsteSistemskeOperacije {
    
    private boolean uspesno=false;
    private boolean postoji = false;

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

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       if(postoji!=true){
           broker.promeni((Kupac)param);
           uspesno=true;
       }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
