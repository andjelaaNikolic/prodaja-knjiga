/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Kupac;


/**
 * Sistemska operacija za pretragu jednog kupca na osnovu njegovog ID-a.
 *
 * @author Andjela
 * @see Kupac
 */
public class SOPretraziKupac extends OpsteSistemskeOperacije {

    /** Kupac pronadjen pretragom. */
    private Kupac k;
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Kupac} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Kupac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
         }
    }

    /**
     * Izvrsava pretragu kupca na osnovu ID-a prosledjenog kupca.
     *
     * @param param objekat tipa {@link Kupac} koji sadrzi ID za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov= " JOIN mesto ON kupac.mesto=mesto.idMesto WHERE idKupac="+((Kupac)param).getIdKupac();
        k = (Kupac) broker.vratiObjekat(new Kupac(), uslov);
        this.k = k;
    }

    /**
     * Vraca kupca pronadjenog pretragom.
     *
     * @return pronadjeni kupac, ili null ako kupac nije pronadjen
     */
    public Kupac getK() {
        return k;
    }
    
    
    
}
