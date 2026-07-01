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
 * Sistemska operacija za pretragu liste mesta na osnovu naziva mesta.
 *
 * @author Andjela
 * @see Mesto
 */
public class SOVratiListuMesto extends OpsteSistemskeOperacije {
    
    /** Lista mesta koja odgovaraju uslovu pretrage. */
    private List<Mesto> mesta;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Mesto}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
       if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu liste mesta ciji naziv odgovara nazivu prosledjenog
     * mesta iz kljuca.
     *
     * @param param objekat tipa {@link Mesto}
     * @param kljuc objekat tipa {@link Mesto} cijim se nazivom vrsi pretraga
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        Mesto m = (Mesto) kljuc;
        String upit = " WHERE mesto.nazivMesta='" +m.getNazivMesta()+ "'";
      
        mesta = broker.vratiSve((Mesto) param, upit);
        this.mesta = mesta;
        
        
        
    }

    /**
     * Vraca listu mesta pronadjenih pretragom.
     *
     * @return lista mesta koja odgovaraju uslovu pretrage
     */
    public List<Mesto> getMesta() {
        return mesta;
    }
    
    
    
}

