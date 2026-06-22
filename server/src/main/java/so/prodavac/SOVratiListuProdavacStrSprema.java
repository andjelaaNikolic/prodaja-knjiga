/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import java.util.ArrayList;
import java.util.List;
import model.PrSS;
import model.Prodavac;
import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuProdavacStrSprema extends OpsteSistemskeOperacije {
    
    private List<Prodavac> prodavci;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
         
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        
        String uslov = " JOIN prss ON prss.prodavac=prodavac.idProdavac JOIN str_sprema ON str_sprema.idStrucnaSprema=prss.strSprema WHERE str_sprema.idStrucnaSprema="+((StrSprema)kljuc).getIdStrucnaSprema();

        prodavci = broker.vratiSve((Prodavac)param, uslov);
        this.prodavci=prodavci;
        

    }

    public List<Prodavac> getProdavci() {
        return prodavci;
    }
    
    
    
}
