/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import db.repozitorijum.DBKonekcija;
import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Ljilja
 */
public class SOObrisiKnjiga extends OpsteSistemskeOperacije {
    
    private boolean koristiSe=false;
    private boolean uspesno=false;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Knjiga)){
            throw new Exception("Sistem ne moze da obrise knjigu");
        }
        
        try {

            String upit = "SELECT * FROM stavka_racuna sr JOIN knjiga k ON sr.knjiga = k.idKnjiga WHERE k.idKnjiga = " + ((Knjiga) param).getIdKnjiga();
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
        broker.obrisi((Knjiga)param);
        uspesno=true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
