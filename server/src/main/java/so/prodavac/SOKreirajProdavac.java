package so.prodavac;

import db.repozitorijum.DBKonekcija;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import model.Knjiga;
import model.PrSS;
import model.Prodavac;

/**
 * Sistemska operacija za kreiranje novog prodavca.
 * Pre kreiranja proverava da li prodavac sa istim korisnickim imenom i
 * emailom vec postoji u bazi podataka. Po uspesnom kreiranju prodavca,
 * upisuju se i njegove strucne spreme ({@link PrSS}) ukoliko su prosledjene.
 *
 * @author Andjela
 * @see Prodavac
 * @see PrSS
 */
public class SOKreirajProdavac extends OpsteSistemskeOperacije {
    
    /** Indikator uspesnosti kreiranja prodavca. */
    private boolean uspesno=false;
    /** Indikator da li prodavac sa istim korisnickim imenom i emailom vec postoji u bazi podataka. */
    private boolean postoji=false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * prodavac sa istim korisnickim imenom i emailom vec postoji u bazi
     * podataka.
     *
     * @param param objekat tipa {@link Prodavac} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
        throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {

            String upit = "SELECT * FROM prodavac WHERE korisnickoIme='" + ((Prodavac) param).getKorisnickoIme()+"' AND email='"+((Prodavac)param).getEmail()+"'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Izvrsava kreiranje prodavca u bazi podataka. Prodavac se upisuje samo
     * ukoliko jos uvek ne postoji u bazi. Nakon upisa prodavca, ucitava se
     * njegov generisani ID iz baze i, ukoliko postoje prosledjene strucne
     * spreme, one se takodje upisuju povezane sa novokreiranim prodavcem.
     *
     * @param param objekat tipa {@link Prodavac} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        
       if (postoji!=true) {
        Prodavac p = (Prodavac) param;

        broker.dodaj(p); 
        
        String uslov = " WHERE korisnickoIme = '" + p.getKorisnickoIme() + "'";
        Prodavac pIzBaze = (Prodavac) broker.vratiObjekat(new Prodavac(), uslov);

        
        List<PrSS> prss = p.getPrss();
        if (prss != null && !prss.isEmpty()) {
            for (PrSS prs : prss) {
                prs.setProdavac(pIzBaze);
                broker.dodaj(prs);
            }
        }

        uspesno = true;
    }  
    }

    /**
     * Vraca indikator uspesnosti kreiranja prodavca.
     *
     * @return true ako je prodavac uspesno kreiran, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}

