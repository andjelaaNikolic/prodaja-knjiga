/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import java.util.List;
import model.Kupac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuKupacKupac extends OpsteSistemskeOperacije {
    
    private List<Kupac> kupci;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

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

    public List<Kupac> getKupci() {
        return kupci;
    }
    
    
    
}
