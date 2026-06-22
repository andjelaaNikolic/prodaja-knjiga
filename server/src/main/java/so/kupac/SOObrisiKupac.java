/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.repozitorijum.DBKonekcija;
import model.Kupac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Ljilja
 */
public class SOObrisiKupac extends OpsteSistemskeOperacije {

    private boolean uspesno=false;
    private boolean koristiSe=false;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null||!(param instanceof Kupac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        try{
        String upit = "SELECT * FROM kupac JOIN racun r ON kupac.idKupac=r.kupac WHERE idKupac="+((Kupac)param).getIdKupac();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        while(rs.next()){
            koristiSe=true;
        }
        }catch(SQLException ex){
            throw ex;
        }
        
        
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(koristiSe!=true){
            broker.obrisi((Kupac)param);
            uspesno=true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
