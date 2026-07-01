/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;


/**
 * Sistemska operacija za pretragu jedne knjige na osnovu njenog ID-a.
 *
 * @author Andjela
 * @see Knjiga
 */
public class SOPretraziKnjiga extends OpsteSistemskeOperacije {

    /** Knjiga pronadjena pretragom. */
    private Knjiga k;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Knjiga} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof Knjiga)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
    }
    }
    /**
     * Izvrsava pretragu knjige na osnovu ID-a prosledjene knjige.
     *
     * @param param objekat tipa {@link Knjiga} koji sadrzi ID za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov = " JOIN magacin ON knjiga.magacin = magacin.idMagacin WHERE idKnjiga="+ ((Knjiga)param).getIdKnjiga();
        k=  (Knjiga) broker.vratiObjekat(new Knjiga(),uslov);
        this.k = k;
    }

    /**
     * Vraca knjigu pronadjenu pretragom.
     *
     * @return pronadjena knjiga, ili null ako knjiga nije pronadjena
     */
    public Knjiga getK() {
        return k;
    }
    
}

