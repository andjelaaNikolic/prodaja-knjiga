/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import java.util.List;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuProdavacProdavac extends OpsteSistemskeOperacije {

    private List<Prodavac> prodavci;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String imePrezime = (String) kljuc;
        String[] imeIPrezime = imePrezime.strip().split(" ");
        String ime = imeIPrezime[0];
        String prezime = imeIPrezime[1];
        
        String uslov = " WHERE prodavac.ime='"+ime+"' AND prodavac.prezime='"+prezime+"'";
        prodavci = broker.vratiSve((Prodavac)param, uslov);
        this.prodavci = prodavci;
        
        
        
    }

    public List<Prodavac> getProdavci() {
        return prodavci;
    }
    
    
    
}
