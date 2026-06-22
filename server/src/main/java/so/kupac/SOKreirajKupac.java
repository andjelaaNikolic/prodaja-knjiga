package so.kupac;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import db.repozitorijum.DBKonekcija;
import model.Kupac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ljilja
 */
public class SOKreirajKupac extends OpsteSistemskeOperacije {
    private boolean postoji=false;
    private boolean uspesno = false;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Kupac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
         try{
            String upit = "SELECT * FROM kupac WHERE brojTelefona=" + ((Kupac) param).getBrojTelefona();
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
    protected void izvrsiOperaciju(Object param, Object uslov) throws Exception {
        if(postoji!=true){
        broker.dodaj((Kupac)param);
        uspesno =true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
}
