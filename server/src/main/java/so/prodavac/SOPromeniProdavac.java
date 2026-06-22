/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import db.repozitorijum.DBKonekcija;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import model.PrSS;
import model.StrSprema;


public class SOPromeniProdavac extends OpsteSistemskeOperacije {
        
    private boolean postoji=false;
    private boolean uspesno=false;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
       if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    Prodavac prodavac = (Prodavac) param;
    List<PrSS> prssZaIzmenu = prodavac.getPrss();
     
     for (int i = 0; i < prssZaIzmenu.size(); i++) {
        for (int j = i + 1; j < prssZaIzmenu.size(); j++) {
            if ((prssZaIzmenu.get(i).getStrSprema().getIdStrucnaSprema() ==
                prssZaIzmenu.get(j).getStrSprema().getIdStrucnaSprema()) && prssZaIzmenu.get(i).getInstitucija().equals(prssZaIzmenu.get(j).getInstitucija())) {
                postoji = true; 
                return;
            }
        }
    }
     
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        Prodavac prodavac = (Prodavac) param;

    if (postoji!=true) {
        
        List<PrSS> prssZaIzmenu = prodavac.getPrss();
        String upit = " JOIN prodavac ON prodavac.idProdavac=prss.prodavac JOIN str_sprema ON str_sprema.idStrucnaSprema=prss.strSprema WHERE prodavac.idProdavac="+prodavac.getIdProdavac();
        List<PrSS> prssIzBaze = broker.vratiSve(new PrSS(), upit);
       
        for (PrSS sr : prssZaIzmenu) {
        boolean postojiUBazi = false;

        for (PrSS srBaza : prssIzBaze) {
            if ((sr.getStrSprema().getIdStrucnaSprema() == srBaza.getStrSprema().getIdStrucnaSprema()) && (sr.getInstitucija().equals(srBaza.getInstitucija()))) {
            postojiUBazi = true;
            break; 
            }
        }
        
        if (postojiUBazi) {
        broker.promeni(sr); 
        } else {
        sr.setProdavac(prodavac);
        broker.dodaj(sr); 
        }
        }   

         
        broker.promeni(prodavac);
        uspesno=true;
        
        }
   
    }
    

    public boolean isUspesno() {
        return uspesno;
    }
    
    
}
