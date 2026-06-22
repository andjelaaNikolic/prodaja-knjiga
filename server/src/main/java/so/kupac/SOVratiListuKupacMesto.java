/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.util.List;
import model.Kupac;
import model.Mesto;


/**
 *
 * @author Ljilja
 */
public class SOVratiListuKupacMesto extends OpsteSistemskeOperacije {

    private List<Kupac> kupci;
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        Mesto mesto = (Mesto) kljuc;
        String upit = " JOIN mesto ON kupac.mesto=mesto.idMesto WHERE kupac.mesto="+mesto.getIdMesto();
        //Kupac k = new Kupac();
        kupci = broker.vratiSve((Kupac)param, upit);
        this.kupci = kupci;
        
    }

    public List<Kupac> getKupci() {
        return kupci;
    }
    
    
}
