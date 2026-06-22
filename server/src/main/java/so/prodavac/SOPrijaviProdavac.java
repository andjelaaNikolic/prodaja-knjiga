package so.prodavac;

import java.util.List;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;


public class SOPrijaviProdavac extends OpsteSistemskeOperacije {

    private Prodavac prodavac;
    private boolean prijavljen=false;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Sistem ne moze da nadje prodavca");
        }

        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        List<Prodavac> prodavci = broker.vratiSve((Prodavac)param,null);
        System.out.println("KLASA PRIJAVIPRODAVCA "+prodavci);
        
        for (Prodavac p : prodavci) {
            if(p.getKorisnickoIme().equals(((Prodavac)param).getKorisnickoIme()) && p.getSifra().equals(((Prodavac)param).getSifra())){
                prodavac= p;
                return;
            }
            
        }
        prodavac=null;
        
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

   
   
    
    
    
    
}
