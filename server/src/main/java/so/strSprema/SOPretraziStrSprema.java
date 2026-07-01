/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strSprema;

import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu jedne strucne spreme na osnovu njenog ID-a.
 *
 * @author Andjela
 * @see StrSprema
 */
public class SOPretraziStrSprema extends OpsteSistemskeOperacije {
    
    /** Strucna sprema pronadjena pretragom. */
    private StrSprema ss;
    

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link StrSprema} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null||!(param instanceof StrSprema)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    }

    /**
     * Izvrsava pretragu strucne spreme na osnovu ID-a prosledjene strucne
     * spreme.
     *
     * @param param objekat tipa {@link StrSprema} koji sadrzi ID za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        
        StrSprema parametar = ((StrSprema) param);
        String upit = " WHERE idStrucnaSprema= "+parametar.getIdStrucnaSprema();
        StrSprema ss = (StrSprema) broker.vratiObjekat((StrSprema) param, upit);
        this.ss=ss;
        
    }

    /**
     * Vraca strucnu spremu pronadjenu pretragom.
     *
     * @return pronadjena strucna sprema, ili null ako nije pronadjena
     */
    public StrSprema getSs() {
        return ss;
    }
    
    
    
}
