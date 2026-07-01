/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import java.util.List;
import model.PrSS;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu jednog prodavca na osnovu njegovog ID-a,
 * zajedno sa listom njegovih strucnih sprema.
 *
 * @author Andjela
 * @see Prodavac
 * @see PrSS
 */
public class SOPretraziProdavac extends OpsteSistemskeOperacije {

    /** Prodavac pronadjen pretragom. */
    private Prodavac p;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Prodavac} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    }

    /**
     * Izvrsava pretragu prodavca na osnovu ID-a prosledjenog prodavca.
     * Ukoliko je prodavac pronadjen, dodatno se ucitava i njegova lista
     * strucnih sprema.
     *
     * @param param objekat tipa {@link Prodavac} koji sadrzi ID za pretragu
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       String upit = " WHERE idProdavac="+((Prodavac)param).getIdProdavac();
       p = (Prodavac) broker.vratiObjekat(new Prodavac(), upit);
       
       if (p != null) {
            String upit1 = " JOIN prodavac ON prodavac.idProdavac=prss.prodavac JOIN str_sprema ON str_sprema.idStrucnaSprema = prss.strSprema WHERE prodavac.idProdavac =" + ((Prodavac) param).getIdProdavac();
            List<PrSS> prss = (List<PrSS>) broker.vratiSve(new PrSS(), upit1);
            p.setPrss(prss);
        }
       
       
       this.p = p;
    }

    /**
     * Vraca prodavca pronadjenog pretragom.
     *
     * @return pronadjeni prodavac, ili null ako prodavac nije pronadjen
     */
    public Prodavac getP() {
        return p;
    }
    
    
    
}

