/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import java.util.List;
import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;


/**
 * Sistemska operacija za pretragu liste knjiga na osnovu naslova.
 *
 * @author Andjela
 * @see Knjiga
 */
public class SOVratiListuKnjiga extends OpsteSistemskeOperacije {
    
   
    /** Lista knjiga koje odgovaraju uslovu pretrage. */
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
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu liste knjiga cij naslov odgovara prosledjenom kljucu.
     *
     * @param param objekat tipa {@link Knjiga}
     * @param kljuc naslov (ili deo naslova) po kojem se knjige pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String upit = "";
        if (((String) kljuc).trim()== null) {

            upit += " JOIN magacin ON knjiga.magacin=magacin.idMagacin WHERE naslov = '" + ((String) kljuc).trim()+ "'";

        } else if (((String) kljuc).trim() != null) {
            upit += " WHERE naslov = '" + ((String) kljuc).trim() + "'";

    
        knjige = broker.vratiSve((Knjiga) param, upit);
        this.knjige = knjige;
        
        
        }
    }

    /**
     * Vraca listu knjiga pronadjenih pretragom.
     *
     * @return lista knjiga koje odgovaraju uslovu pretrage
     */
    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
}

