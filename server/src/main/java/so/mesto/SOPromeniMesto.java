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
 *
 * @author Ljilja
 */
public class SOPromeniMesto extends OpsteSistemskeOperacije {

    private boolean postoji=false;
    private boolean uspesno=false;
    
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

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
        broker.promeni((Mesto)param);
        uspesno=true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
