/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import java.util.List;
import model.Mesto;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
/**
 *
 * @author Ljilja
 */
public class SOVratiListuMesto extends OpsteSistemskeOperacije {
    
    private List<Mesto> mesta;

    @Override
    protected void preduslovi(Object param) throws Exception {
       if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        Mesto m = (Mesto) kljuc;
        String upit = " WHERE mesto.nazivMesta='" +m.getNazivMesta()+ "'";
      
        mesta = broker.vratiSve((Mesto) param, upit);
        this.mesta = mesta;
        
        
        
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
    
    
    
}
