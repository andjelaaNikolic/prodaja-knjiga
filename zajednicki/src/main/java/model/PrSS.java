
package model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;
import java.util.ArrayList;



public class PrSS implements OpstiDomenskiObjekat{
    
    private StrSprema strSprema;
    private Prodavac prodavac;
    private Date datumSticanja;
    private String institucija;
    private String grad;

    public PrSS() {
    }
    
    
    public PrSS(StrSprema strSprema, Prodavac prodavac, Date datumSticanja, String institucija, String grad) {
        this.strSprema = strSprema;
        this.prodavac = prodavac;
        this.datumSticanja = datumSticanja;
        this.institucija = institucija;
        this.grad = grad;
    }

    public StrSprema getStrSprema() {
        return strSprema;
    }

    public void setStrSprema(StrSprema strSprema) {
        this.strSprema = strSprema;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrSS other = (PrSS) obj;
        if (!Objects.equals(this.institucija, other.institucija)) {
            return false;
        }
        if (!Objects.equals(this.grad, other.grad)) {
            return false;
        }
        if (!Objects.equals(this.strSprema, other.strSprema)) {
            return false;
        }
        if (!Objects.equals(this.prodavac, other.prodavac)) {
            return false;
        }
        return Objects.equals(this.datumSticanja, other.datumSticanja);
    }

    @Override
    public String toString() {
        return "PrSS{" + "strSprema=" + strSprema + ", prodavac=" + prodavac + ", datumSticanja=" + datumSticanja + ", institucija=" + institucija + ", grad=" + grad + '}';
    }

    @Override
    public String vratiNazivTabele() {
       return "prss";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> prss = new ArrayList<>();
     try{
        while(rs.next()){
            
            String institucija = rs.getString("prss.institucija");
            String grad = rs.getString("prss.grad");
            Date datum = rs.getDate("prss.datumSticanja");
            Date datumSticanja = new Date(datum.getTime());
            int idStrSprema = rs.getInt("prss.strSprema");
            String stepen = rs.getString("str_sprema.stepen");
            int idProdavac = rs.getInt("prss.prodavac");
            String imeP = rs.getString("prodavac.ime");
            String prezimeP = rs.getString("prodavac.prezime");
            String korisnickoImeP = rs.getString("prodavac.korisnickoIme");
            String sifraP = rs.getString("prodavac.sifra");
            String emailP = rs.getString("prodavac.email");
 
            StrSprema ss = new StrSprema(idStrSprema, stepen);
           
            Prodavac p = new Prodavac(idProdavac, imeP, prezimeP, korisnickoImeP, sifraP, emailP);
            
           
            
            PrSS ps = new PrSS(ss, p, datumSticanja, institucija, grad);
            prss.add(ps);
            
        }
        
        return prss;
     } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste prodavac-strucna sprema: " + ex.getMessage());
            return null;
        }

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
       return "strSprema,prodavac,datumSticanja,institucija,grad";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return strSprema.getIdStrucnaSprema()+", "+prodavac.getIdProdavac()+", '"+datumSticanja+"', '"+institucija+"', '"+grad+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "strSprema="+strSprema.getIdStrucnaSprema()+" AND prodavac="+prodavac.getIdProdavac();
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        try {
            PrSS prss = null;
            while (rs.next()) {
            String institucija = rs.getString("prss.institucija");
            String grad = rs.getString("prss.grad");
            Date datum = rs.getDate("prss.datumSticanja");
            Date datumSticanja = new Date(datum.getTime());
            int idStrSprema = rs.getInt("prss.strSprema");
            String stepen = rs.getString("str_sprema.stepen");
            int idProdavac = rs.getInt("prss.prodavac");
            String imeP = rs.getString("prodavac.ime");
            String prezimeP = rs.getString("prodavac.prezime");
            String korisnickoImeP = rs.getString("prodavac.korisnickoIme");
            String sifraP = rs.getString("prodavac.sifra");
            String emailP = rs.getString("prodavac.email");
 
            StrSprema ss = new StrSprema(idStrSprema, stepen);
           
            Prodavac p = new Prodavac(idProdavac, imeP, prezimeP, korisnickoImeP, sifraP, emailP);
 
             prss = new PrSS(ss, p, datumSticanja, institucija, grad);

            }

            return prss;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja prodavac-strucna sprema: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "strSprema="+strSprema.getIdStrucnaSprema()+", prodavac="+prodavac.getIdProdavac()+", datumSticanja='"+datumSticanja+"', institucija='"+institucija+"', grad='"+grad+"'";
    }
    
    
    
    
    
    
}
