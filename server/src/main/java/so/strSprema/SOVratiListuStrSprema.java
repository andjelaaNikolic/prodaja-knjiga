/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strSprema;

import java.util.List;
import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuStrSprema extends OpsteSistemskeOperacije {

    private List<StrSprema> strSpreme;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof StrSprema)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String stepen = ((String) kljuc).trim();
        String upit = "";
      
//WHERE stepen LIKE '%" + stepen + "%'
        upit += " WHERE stepen LIKE '%" + stepen + "%'";

    
        strSpreme = broker.vratiSve((StrSprema) param, upit);
        this.strSpreme = strSpreme;
        
        
        
        
        
    }

    public List<StrSprema> getStrSpreme() {
        return strSpreme;
    }
    
    
}
