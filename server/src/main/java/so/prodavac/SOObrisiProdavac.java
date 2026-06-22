/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import db.repozitorijum.DBKonekcija;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


/**
 *
 * @author Ljilja
 */
public class SOObrisiProdavac extends OpsteSistemskeOperacije {
    
    private boolean uspesno=false;
    private boolean koristiSe=false;

    @Override
    protected void preduslovi(Object param) throws Exception {
       if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
       
       try{
           String upit1 = "SELECT * FROM prodavac JOIN racun ON racun.prodavac="+((Prodavac)param).getIdProdavac();
           Statement st = DBKonekcija.getInstance().getConnection().createStatement();
           ResultSet rs = st.executeQuery(upit1);
           while(rs.next()){
               koristiSe=true;
           }
       }catch(SQLException ex){
           throw ex;
       }
       try{
           String upit2 = "SELECT * FROM prodavac JOIN prss ON prss.prodavac="+((Prodavac)param).getIdProdavac();
           Statement st = DBKonekcija.getInstance().getConnection().createStatement();
           ResultSet rs = st.executeQuery(upit2);
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
            broker.obrisi((Prodavac)param);
            uspesno=true;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
