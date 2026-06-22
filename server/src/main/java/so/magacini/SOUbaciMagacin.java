/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.magacini;

import db.repozitorijum.DBKonekcija;
import model.Magacin;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Ljilja
 */
public class SOUbaciMagacin extends OpsteSistemskeOperacije {

    private boolean postoji=false;
    private boolean uspesno=false;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Magacin)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        Magacin magacin = (Magacin) param;
        if(magacin.getNaziv()==null || magacin.getNaziv().isEmpty()){
            throw new Exception("Naziv magacina je neispravan");
        }
        if(magacin.getAdresa()==null || magacin.getAdresa().isEmpty()){
            throw new Exception("Adresa magacina je neispravna");
        }
        
          try {

            String upit = "SELECT * FROM magacin WHERE naziv='" + ((Magacin) param).getNaziv()+"'";
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
        broker.dodaj((Magacin)param);
        uspesno=true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
}
