package so.prodavac;

import java.util.List;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za prijavu prodavca u sistem na osnovu korisnickog
 * imena i sifre.
 *
 * @author Andjela
 * @see Prodavac
 */
public class SOPrijaviProdavac extends OpsteSistemskeOperacije {

    /** Prijavljeni prodavac, ili null ako prijava nije uspela. */
    private Prodavac prodavac;
    /** Nije koriscen za spoljasnje citanje stanja prijave. */
    private boolean prijavljen=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Prodavac} koji sadrzi korisnicko ime i sifru
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Sistem ne moze da nadje prodavca");
        }

        
    }

    /**
     * Izvrsava prijavu prodavca. Ucitava sve prodavce iz baze i trazi onog
     * ciji se korisnicko ime i sifra poklapaju sa prosledjenim podacima.
     *
     * @param param objekat tipa {@link Prodavac} koji sadrzi korisnicko ime i sifru
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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

    /**
     * Vraca prijavljenog prodavca.
     *
     * @return prijavljeni prodavac, ili null ako prijava nije uspela
     */
    public Prodavac getProdavac() {
        return prodavac;
    }

   
   
    
    
    
    
}
