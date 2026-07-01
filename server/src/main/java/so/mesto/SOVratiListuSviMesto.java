/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import java.util.List;
import model.Mesto;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za dobavljanje liste svih mesta.
 *
 * @author Andjela
 * @see Mesto
 */
public class SOVratiListuSviMesto extends OpsteSistemskeOperacije {

    /** Nije koriscena u ovoj operaciji. */
    Mesto mesto;
    /** Lista svih mesta. */
    List<Mesto> mesta;
    
    /**
     * Ova operacija nema dodatnih preduslova.
     *
     * @param param nije koriscen u ovoj operaciji
     * @throws Exception nikada se ne baca u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    /**
     * Izvrsava dobavljanje liste svih mesta iz baze podataka.
     *
     * @param param objekat tipa {@link Mesto}
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       mesta= broker.vratiSve( (Mesto)param, "");
    }

    /**
     * Vraca listu svih mesta.
     *
     * @return lista svih mesta
     */
    public List<Mesto> getMesta() {
        return mesta;
    }
    
    
    
}
