/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import java.util.List;
import model.Kupac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu liste kupaca na osnovu imena i/ili prezimena.
 *
 * @author Andjela
 * @see Kupac
 */
public class SOVratiListuKupacKupac extends OpsteSistemskeOperacije {
    
    /** Lista kupaca koji odgovaraju uslovu pretrage. */
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
     * Izvrsava pretragu liste kupaca cije ime i/ili prezime odgovara
     * prosledjenom kljucu. Ukoliko kljuc sadrzi dve reci, pretraga se vrsi
     * po kombinaciji imena i prezimena (u oba redosleda), a inace po imenu
     * ili prezimenu.
     *
     * @param param objekat tipa {@link Kupac}
     * @param kljuc ime i/ili prezime po kojem se kupci pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String[] imePrezime = ((String) kljuc).split(" ");
        String upit = "";
        if (imePrezime.length == 2) {
            upit += " JOIN mesto ON kupac.mesto=mesto.idMesto WHERE (ime='" + imePrezime[0] + "' AND prezime='" + imePrezime[1] + "') OR (ime='" + imePrezime[1] + "' AND prezime='" + imePrezime[0] + "')";
        } else {
            upit += " WHERE ime='" + imePrezime[0] + "' OR prezime='" + imePrezime[0] + "'";
        }
        
        kupci = broker.vratiSve((Kupac) param, upit);
        this.kupci = kupci;

    }

    /**
     * Vraca listu kupaca pronadjenih pretragom.
     *
     * @return lista kupaca koji odgovaraju uslovu pretrage
     */
    public List<Kupac> getKupci() {
        return kupci;
    }
    
    
    
}

