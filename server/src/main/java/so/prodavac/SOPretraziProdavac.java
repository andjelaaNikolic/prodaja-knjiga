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
 *
 * @author Ljilja
 */
public class SOPretraziProdavac extends OpsteSistemskeOperacije {

    private Prodavac p;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
    }

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

    public Prodavac getP() {
        return p;
    }
    
    
    
}
