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
 *
 * @author Ljilja
 */
public class SOPromeniKnjiga extends OpsteSistemskeOperacije {
    
    private boolean uspesno=false;
    private boolean postoji=false;

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

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
            
            broker.promeni((Knjiga)param);
            uspesno=true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
    
}
