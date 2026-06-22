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
public class SOVratiListuSviStrSprema extends OpsteSistemskeOperacije {

    private List<StrSprema> strSpreme;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        strSpreme = broker.vratiSve( (StrSprema) param, "");
        this.strSpreme = strSpreme;
    }

    public List<StrSprema> getStrSpreme() {
        return strSpreme;
    }
    
    
    
}
