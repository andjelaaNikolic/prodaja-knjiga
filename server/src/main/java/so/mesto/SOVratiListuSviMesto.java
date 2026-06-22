/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import java.util.List;
import model.Mesto;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuSviMesto extends OpsteSistemskeOperacije {

    Mesto mesto;
    List<Mesto> mesta;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       mesta= broker.vratiSve( (Mesto)param, "");
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
    
    
    
}
