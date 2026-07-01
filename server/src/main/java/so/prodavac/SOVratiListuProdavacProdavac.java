/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import java.util.List;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu liste prodavaca na osnovu imena i prezimena.
 *
 * @author Andjela
 * @see Prodavac
 */
public class SOVratiListuProdavacProdavac extends OpsteSistemskeOperacije {

    /** Lista prodavaca koji odgovaraju uslovu pretrage. */
    private List<Prodavac> prodavci;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Prodavac}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    }

    /**
     * Izvrsava pretragu liste prodavaca cije ime i prezime odgovara
     * prosledjenom kljucu (ocekuje se format "ime prezime").
     *
     * @param param objekat tipa {@link Prodavac}
     * @param kljuc ime i prezime po kojima se prodavci pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String imePrezime = (String) kljuc;
        String[] imeIPrezime = imePrezime.strip().split(" ");
        String ime = imeIPrezime[0];
        String prezime = imeIPrezime[1];
        
        String uslov = " WHERE prodavac.ime='"+ime+"' AND prodavac.prezime='"+prezime+"'";
        prodavci = broker.vratiSve((Prodavac)param, uslov);
        this.prodavci = prodavci;
        
        
        
    }

    /**
     * Vraca listu prodavaca pronadjenih pretragom.
     *
     * @return lista prodavaca koji odgovaraju uslovu pretrage
     */
    public List<Prodavac> getProdavci() {
        return prodavci;
    }
    
    
    
}

