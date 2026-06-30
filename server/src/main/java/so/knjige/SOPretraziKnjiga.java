/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;


public class SOPretraziKnjiga extends OpsteSistemskeOperacije {

    private Knjiga k;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof Knjiga)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
    }
    }
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov = " JOIN magacin ON knjiga.magacin = magacin.idMagacin WHERE idKnjiga="+ ((Knjiga)param).getIdKnjiga();
        k=  (Knjiga) broker.vratiObjekat(new Knjiga(),uslov);
        this.k = k;
    }

    public Knjiga getK() {
        return k;
    }
    
}
