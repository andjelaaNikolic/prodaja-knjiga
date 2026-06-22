/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Ljilja
 */
public interface OpstiDomenskiObjekat extends Serializable {
    
    public String vratiNazivTabele();
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    public String vratiKoloneZaUbacivanje();
    public String vratiVrednostiZaUbacivanje();
    public String vratiPrimarniKljuc();
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    public String vratiVrednostiZaIzmenu();
 
}
