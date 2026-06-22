/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import model.Mesto;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOPretraziMesto extends OpsteSistemskeOperacije {
    
    Mesto mesto;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Mesto)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov = " WHERE idMesto = " + ((Mesto) param).getIdMesto();
       Mesto mesto = (Mesto) broker.vratiObjekat((Mesto)param,uslov);
       this.mesto=mesto;
    }

    public Mesto getMesto() {
        return mesto;
    }
    
    
    
}
