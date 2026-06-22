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
public class SOObrisiMesto extends OpsteSistemskeOperacije {

    private boolean koristiSe=false;
    private boolean uspesno=false;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
                if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM kupac km JOIN mesto m ON m.idMesto = km.mesto WHERE m.idMesto = " + ((Mesto) param).getIdMesto();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                koristiSe = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(koristiSe!=true){
            broker.obrisi((Mesto)param);
            uspesno=true;
        }
        
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
}
