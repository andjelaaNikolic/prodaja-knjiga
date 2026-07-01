/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import model.Mesto;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu jednog mesta na osnovu njegovog ID-a.
 *
 * @author Andjela
 * @see Mesto
 */
public class SOPretraziMesto extends OpsteSistemskeOperacije {
    
    /** Mesto pronadjeno pretragom. */
    Mesto mesto;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Mesto} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Mesto)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
    }

    /**
     * Izvrsava pretragu mesta na osnovu ID-a prosledjenog mesta.
     *
     * @param param objekat tipa {@link Mesto} koji sadrzi ID za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov = " WHERE idMesto = " + ((Mesto) param).getIdMesto();
       Mesto mesto = (Mesto) broker.vratiObjekat((Mesto)param,uslov);
       this.mesto=mesto;
    }

    /**
     * Vraca mesto pronadjeno pretragom.
     *
     * @return pronadjeno mesto, ili null ako mesto nije pronadjeno
     */
    public Mesto getMesto() {
        return mesto;
    }
    
    
    
}

