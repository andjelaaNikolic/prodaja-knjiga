/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import java.util.List;
import model.Kupac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za dobavljanje liste svih kupaca.
 *
 * @author Andjela
 * @see Kupac
 */
public class SOVratiListuSviKupac extends OpsteSistemskeOperacije {

    /** Lista svih kupaca. */
    private List<Kupac> kupci;
    
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
     * Izvrsava dobavljanje liste svih kupaca iz baze podataka.
     *
     * @param param objekat tipa {@link Kupac}
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String upit = " JOIN mesto ON kupac.mesto = mesto.idMesto";
        kupci = broker.vratiSve( (Kupac)param, upit);
        this.kupci = kupci;
    }

    /**
     * Vraca listu svih kupaca.
     *
     * @return lista svih kupaca
     */
    public List<Kupac> getKupci() {
        return kupci;
    }
    
    
    
}

