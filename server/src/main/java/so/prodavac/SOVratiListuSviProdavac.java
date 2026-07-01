/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import java.util.List;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za dobavljanje liste svih prodavaca.
 *
 * @author Andjela
 * @see Prodavac
 */
public class SOVratiListuSviProdavac extends OpsteSistemskeOperacije{

    /** Lista svih prodavaca. */
    List<Prodavac> prodavci;
    /** Nije koriscena u ovoj operaciji. */
    Prodavac prodavac;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Prodavac}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof Prodavac)) {
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    }

    /**
     * Izvrsava dobavljanje liste svih prodavaca iz baze podataka.
     *
     * @param param objekat tipa {@link Prodavac}
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        prodavci = broker.vratiSve( (Prodavac)param, "");
        this.prodavci=prodavci;
    }

    /**
     * Vraca listu svih prodavaca.
     *
     * @return lista svih prodavaca
     */
    public List<Prodavac> getProdavci() {
        return prodavci;
    }
    
    
    
}

