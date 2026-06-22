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


public class SOKreirajProdavac extends OpsteSistemskeOperacije {
    
    private boolean uspesno=false;
    private boolean postoji=false;

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

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
