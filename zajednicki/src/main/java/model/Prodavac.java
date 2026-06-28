package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;


public class Prodavac implements OpstiDomenskiObjekat{
    private int idProdavac;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private String email;
    private List<PrSS> prss;

    public Prodavac() {
    }
    
 
    public Prodavac(int idProdavac, String ime, String prezime, String korisnickoIme, String sifra, String email) {
    	setIdProdavac(idProdavac);
    	setIme(ime);
    	setPrezime(prezime);
    	setKorisnickoIme(korisnickoIme);
    	setSifra(sifra);
    	setEmail(email);
        this.prss=new ArrayList<>();
    }

    public Prodavac(String ime, String prezime, String korisnickoIme, String sifra, String email) {
    	setIme(ime);
    	setPrezime(prezime);
    	setKorisnickoIme(korisnickoIme);
    	setSifra(sifra);
    	setEmail(email);
        this.prss=new ArrayList<>();
    }

    public List<PrSS> getPrss() {
        return prss;
    }

    public void setPrss(List<PrSS> prss) {
    	if(prss==null)
    		throw new NullPointerException();
        this.prss = prss;
    }


    public int getIdProdavac() {
        return idProdavac;
    }

    public void setIdProdavac(int idProdavac) {
    	 if (idProdavac <= 0)
             throw new IllegalArgumentException();
        this.idProdavac = idProdavac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
    	if(ime==null) 
    		throw new NullPointerException();
    	
    	if(ime.length()>50) 
    		throw new IllegalArgumentException();
    	
    	if (ime.isBlank())
            throw new IllegalArgumentException();
    	
        if (!ime.matches("[a-zA-ZčćšđžČĆŠĐŽ ]+"))
            throw new IllegalArgumentException();
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
    	if(prezime==null) 
    		throw new NullPointerException();
    	
    	if(prezime.length()>50) 
    		throw new IllegalArgumentException();
    	
    	if (prezime.isBlank())
            throw new IllegalArgumentException();
    	
        if (!prezime.matches("[a-zA-ZčćšđžČĆŠĐŽ ]+"))
            throw new IllegalArgumentException();
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
    	if(korisnickoIme==null) 
    		throw new NullPointerException();
    	
    	if(korisnickoIme.length()>50) 
    		throw new IllegalArgumentException();
    	
    	if (korisnickoIme.isBlank())
            throw new IllegalArgumentException();
    	
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

	public void setSifra(String sifra) {

		if (sifra == null)
			throw new NullPointerException();
		if (sifra.isBlank())
			throw new IllegalArgumentException();
		if (sifra.length() > 50)
			throw new IllegalArgumentException();
		this.sifra = sifra;
	}

    public String getEmail() {
        return email;
    }

	public void setEmail(String email) {
		if (email == null)
			throw new NullPointerException();
		if (email.isBlank())
			throw new IllegalArgumentException();
		if (email.length() > 50)
			throw new IllegalArgumentException();
		if (!email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
			throw new IllegalArgumentException();
		this.email = email;
	}

    @Override
    public String toString() {
        return  korisnickoIme;
    }


    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Prodavac p = (Prodavac) obj;
    return this.korisnickoIme != null && this.korisnickoIme.equals(p.korisnickoIme);
}

    @Override
    public int hashCode() {
    return korisnickoIme != null ? korisnickoIme.hashCode() : 0;
    }

    @Override
    public String vratiNazivTabele() {
        return "prodavac";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try{ 
        while(rs.next()){

            int idProdavac = rs.getInt("prodavac.idProdavac");
            String ime = rs.getString("prodavac.ime");
            String prezime = rs.getString("prodavac.prezime");
            String korisnickoIme = rs.getString("prodavac.korisnickoIme");
            String sifra = rs.getString("prodavac.sifra");
            String email = rs.getString("prodavac.email");
           
            
            Prodavac p = new Prodavac(idProdavac,ime,prezime,korisnickoIme,sifra,email);
            lista.add(p);
            
        }
        
        return lista;
        }catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste prodavac: " + ex.getMessage());
            return null;
        }
    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra,email";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
         return "'"+ime+"','"+prezime+"','"+korisnickoIme+"','"+sifra+"','"+email+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavac.idProdavac="+idProdavac;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
      try {
            Prodavac p = null;
            while (rs.next()) {
            
            int idProdavac = rs.getInt("prodavac.idProdavac");
            String imeP = rs.getString("prodavac.ime");
            String prezimeP = rs.getString("prodavac.prezime");
            String korisnickoImeP = rs.getString("prodavac.korisnickoIme");
            String sifraP = rs.getString("prodavac.sifra");
            String emailP = rs.getString("prodavac.email");
           
            p = new Prodavac(idProdavac, imeP, prezimeP, korisnickoImeP, sifraP, emailP);

            }

            return p;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja prodavca: " + ex.getMessage());
            return null;
        }
      
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', korisnickoIme='"+korisnickoIme+"', sifra='"+sifra+"', email='"+email+"'";
    }
    
    
    
    
}
