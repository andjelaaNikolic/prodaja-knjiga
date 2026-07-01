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
 * Sistemska operacija koja vraca listu svih knjiga.
 *
 * @author Andjela
 * @see Knjiga
 */
public class SOVratiListuSviKnjiga extends OpsteSistemskeOperacije{
    
    /** Lista svih knjiga. */
    List<Knjiga> knjige;


    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Knjiga}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Knjiga)) {
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
       
    }

    /**
     * Izvrsava dobavljanje liste svih knjiga iz baze podataka.
     *
     * @param param objekat tipa {@link Knjiga}
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       String upit = " JOIN magacin ON knjiga.magacin = magacin.idMagacin";
       knjige = broker.vratiSve((Knjiga)param, upit);
       this.knjige = knjige;
    }

    /**
     * Vraca listu svih knjiga.
     *
     * @return lista svih knjiga
     */
    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    
    
}

