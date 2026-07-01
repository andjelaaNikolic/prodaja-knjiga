/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.magacini;

import java.util.List;
import model.Magacin;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za dobavljanje liste svih magacina.
 *
 * @author Andjela
 * @see Magacin
 */
public class SOVratiListuSviMagacini extends OpsteSistemskeOperacije{
    
        /** Lista svih magacina. */
        private List<Magacin> magacini;


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
     * Izvrsava dobavljanje liste svih magacina iz baze podataka.
     *
     * @param param objekat tipa {@link Magacin}
     * @param uslov nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object uslov) throws Exception {
        magacini = broker.vratiSve( (Magacin)param,"");
        this.magacini = magacini;
    }

    /**
     * Vraca listu svih magacina.
     *
     * @return lista svih magacina
     */
    public List<Magacin> getMagacini() {
        return magacini;
    }
    
    
}
