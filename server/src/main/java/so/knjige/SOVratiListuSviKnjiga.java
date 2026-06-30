/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import java.util.List;
import model.Knjiga;
import model.Racun;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuSviKnjiga extends OpsteSistemskeOperacije{
    
    List<Knjiga> knjige;
    Knjiga knjiga;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Knjiga)) {
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       String upit = " JOIN magacin ON knjiga.magacin = magacin.idMagacin";
       knjige = broker.vratiSve((Knjiga)param, upit);
       this.knjige = knjige;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    
    
}
