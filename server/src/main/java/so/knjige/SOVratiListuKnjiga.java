/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import java.util.List;
import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;


/**
 *
 * @author Ljilja
 */
public class SOVratiListuKnjiga extends OpsteSistemskeOperacije {
    
   
    List<Knjiga> knjige;

    @Override
    protected void preduslovi(Object param) throws Exception {
      if (param == null || !(param instanceof Knjiga)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String upit = "";
        if (((String) kljuc).trim()== null) {

            upit += " JOIN magacin ON knjiga.magacin=magacin.idMagacin WHERE naslov = '" + ((String) kljuc).trim()+ "'";

        } else if (((String) kljuc).trim() != null) {
            upit += " WHERE naslov = '" + ((String) kljuc).trim() + "'";

    
        knjige = broker.vratiSve((Knjiga) param, upit);
        this.knjige = knjige;
        
        
        }
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
}
