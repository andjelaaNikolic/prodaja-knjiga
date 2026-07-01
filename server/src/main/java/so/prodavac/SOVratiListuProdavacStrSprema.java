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
 * Sistemska operacija za pretragu liste prodavaca koji poseduju odredjenu
 * strucnu spremu.
 *
 * @author Andjela
 * @see Prodavac
 * @see StrSprema
 */
public class SOVratiListuProdavacStrSprema extends OpsteSistemskeOperacije {
    
    /** Lista prodavaca koji poseduju prosledjenu strucnu spremu. */
    private List<Prodavac> prodavci;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Prodavac}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
         
    }

    /**
     * Izvrsava pretragu liste prodavaca koji poseduju strucnu spremu
     * prosledjenu kroz kljuc.
     *
     * @param param objekat tipa {@link Prodavac}
     * @param kljuc objekat tipa {@link StrSprema} po kojoj se prodavci pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        
        String uslov = " JOIN prss ON prss.prodavac=prodavac.idProdavac JOIN str_sprema ON str_sprema.idStrucnaSprema=prss.strSprema WHERE str_sprema.idStrucnaSprema="+((StrSprema)kljuc).getIdStrucnaSprema();

        prodavci = broker.vratiSve((Prodavac)param, uslov);
        this.prodavci=prodavci;
        

    }

    /**
     * Vraca listu prodavaca pronadjenih pretragom.
     *
     * @return lista prodavaca koji poseduju prosledjenu strucnu spremu
     */
    public List<Prodavac> getProdavci() {
        return prodavci;
    }
    
    
    
}

