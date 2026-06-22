/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strSprema;

import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOPretraziStrSprema extends OpsteSistemskeOperacije {
    
    private StrSprema ss;
    

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null||!(param instanceof StrSprema)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        
        StrSprema parametar = ((StrSprema) param);
        String upit = " WHERE idStrucnaSprema= "+parametar.getIdStrucnaSprema();
        StrSprema ss = (StrSprema) broker.vratiObjekat((StrSprema) param, upit);
        this.ss=ss;
        
    }

    public StrSprema getSs() {
        return ss;
    }
    
    
    
}
