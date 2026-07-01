package model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Predstavlja vezu izmedju prodavca i strucne spreme koju je prodavac stekao,
 * zajedno sa podacima o datumu, instituciji i gradu sticanja.
 *
 *@author Andjela
 * @see Prodavac
 * @see StrSprema
 */
public class PrSS implements OpstiDomenskiObjekat{
    
    /** Strucna sprema koju je prodavac stekao, ne sme biti null. */
    private StrSprema strSprema;
    /** Prodavac koji je stekao strucnu spremu, ne sme biti null. */
    private Prodavac prodavac;
    /** Datum sticanja strucne spreme, ne sme biti u buducnosti. */
    private Date datumSticanja;
    /** Institucija u kojoj je strucna sprema stecena. */
    private String institucija;
    /** Grad u kojem je strucna sprema stecena. */
    private String grad;

    public PrSS() {
    }
    
    
    /**
     * Konstruktor koji inicijalizuje sve atribute veze prodavac-strucna sprema.
     *
     * @param strSprema strucna sprema koju je prodavac stekao
     * @param prodavac prodavac koji je stekao strucnu spremu
     * @param datumSticanja datum sticanja strucne spreme
     * @param institucija institucija u kojoj je strucna sprema stecena
     * @param grad grad u kojem je strucna sprema stecena
     */
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

    /**
     * Postavlja strucnu spremu koju je prodavac stekao.
     *
     * @param strSprema strucna sprema, ne sme biti null
     * @throws NullPointerException ako je strSprema null
     */
    public void setStrSprema(StrSprema strSprema) {
    	if(strSprema==null)
    		throw new NullPointerException("Stručna sprema ne sme biti null.");
        this.strSprema = strSprema;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    /**
     * Postavlja prodavca koji je stekao strucnu spremu.
     *
     * @param prodavac prodavac, ne sme biti null
     * @throws NullPointerException ako je prodavac null
     */
    public void setProdavac(Prodavac prodavac) {
    	if(prodavac==null)
    		throw new NullPointerException("Prodavac ne sme biti null.");
        this.prodavac = prodavac;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    /**
     * Postavlja datum sticanja strucne spreme.
     *
     * @param datumSticanja datum sticanja, ne sme biti null niti u buducnosti
     * @throws NullPointerException ako je datumSticanja null
     * @throws IllegalArgumentException ako je datumSticanja u buducnosti
     */
    public void setDatumSticanja(Date datumSticanja) {
    	if (datumSticanja == null)
            throw new NullPointerException("Datum sticanja ne sme biti null.");
        if (datumSticanja.after(new Date()))
            throw new IllegalArgumentException("Datum sticanja ne sme biti u budućnosti.");
        this.datumSticanja = datumSticanja;
    }

    public String getInstitucija() {
        return institucija;
    }

    /**
     * Postavlja instituciju u kojoj je strucna sprema stecena.
     *
     * @param institucija institucija, ne sme biti null, prazna ili duza od 50 karaktera
     * @throws NullPointerException ako je institucija null
     * @throws IllegalArgumentException ako je institucija prazna ili duza od 50 karaktera
     */
    public void setInstitucija(String institucija) {
    	if(institucija==null) 
    		throw new NullPointerException("Institucija ne sme biti null.");
    	
    	if(institucija.length()>50) 
    		throw new IllegalArgumentException("Institucija ne sme imati više od 50 karaktera.");
    	
    	if (institucija.isBlank())
            throw new IllegalArgumentException("Institucija ne sme biti prazna.");
        this.institucija = institucija;
    }

    public String getGrad() {
        return grad;
    }

    /**
     * Postavlja grad u kojem je strucna sprema stecena.
     *
     * @param grad grad, ne sme biti null, prazan, duzi od 50 karaktera
     *             ili sadrzati znakove koji nisu slova
     * @throws NullPointerException ako je grad null
     * @throws IllegalArgumentException ako je grad prazan, duzi od 50 karaktera
     *                                   ili sadrzi znakove koji nisu slova
     */
    public void setGrad(String grad) {
    	if(grad==null)
    		throw new NullPointerException("Grad ne sme biti null.");
    	if(grad.isBlank())
    		throw new IllegalArgumentException("Grad ne sme biti prazan.");
    	if(grad.length()>50)
    		throw new IllegalArgumentException("Grad ne sme imati više od 50 karaktera.");
        if (!grad.matches("[a-zA-ZčćšđžČĆŠĐŽ ]+"))
            throw new IllegalArgumentException("Grad sme sadržati samo slova.");
        this.grad = grad;
    }

    /**
     * Vraca konstantan hash kod, posto ova klasa nema prirodan jedinstveni atribut
     * pogodan za hesiranje.
     *
     * @return konstantan hash kod
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi ovu vezu prodavac-strucna sprema sa drugim objektom na osnovu
     * institucije, grada, strucne spreme, prodavca i datuma sticanja.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su sve navedene vrednosti jednake, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se neka od vrednosti razlikuje
     */
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

    /**
     * Vraca tekstualnu reprezentaciju veze prodavac-strucna sprema sa svim atributima.
     *
     * @return string sa svim atributima
     */
    @Override
    public String toString() {
        return "PrSS{" + "strSprema=" + strSprema + ", prodavac=" + prodavac + ", datumSticanja=" + datumSticanja + ", institucija=" + institucija + ", grad=" + grad + '}';
    }

    /**
     * Vraca naziv tabele "prss" u bazi podataka.
     *
     * @return naziv tabele "prss"
     */
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

