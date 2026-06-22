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
 *
 * @author Ljilja
 */
public class SOPretraziKupac extends OpsteSistemskeOperacije {

    private Kupac k;
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Kupac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
         }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov= " JOIN mesto ON kupac.mesto=mesto.idMesto WHERE idKupac="+((Kupac)param).getIdKupac();
        k = (Kupac) broker.vratiObjekat(new Kupac(), uslov);
        this.k = k;
    }

    public Kupac getK() {
        return k;
    }
    
    
    
}
