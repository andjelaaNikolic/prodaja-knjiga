
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;


public class Kupac implements OpstiDomenskiObjekat{
    private int idKupac;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private Mesto mesto;

    public Kupac() {
    }
    
    public Kupac( String ime, String prezime, String brojTelefona, Mesto mesto) {
    	setIme(ime);
    	setPrezime(prezime);
    	setBrojTelefona(brojTelefona);
    	setMesto(mesto);
    }


    public Kupac(int idKupac, String ime, String prezime, String brojTelefona, Mesto mesto) {
    	setIdKupac(idKupac);
    	setIme(ime);
    	setPrezime(prezime);
    	setBrojTelefona(brojTelefona);
    	setMesto(mesto);
    }

    public int getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(int idKupac) {
    	if(idKupac<=0) {
    		throw new IllegalArgumentException();
    	}
        this.idKupac = idKupac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException();
    	}
    	
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

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
    	if(brojTelefona==null) {
    		throw new NullPointerException();
    	}
    	if(brojTelefona.length()>10) {
    		throw new IllegalArgumentException();
    	}
    	if (!brojTelefona.matches("[0-9]+"))
            throw new IllegalArgumentException();
    	
    	if (brojTelefona.isBlank())
            throw new IllegalArgumentException();
    	
        this.brojTelefona = brojTelefona;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
    	if(mesto==null)
    		throw new NullPointerException();
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return  "id=" + idKupac + ", ime i prezime=" + ime + " " + prezime ;
    }

    @Override
	public int hashCode() {
		return Objects.hash(brojTelefona, ime, prezime);
	}



    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kupac other = (Kupac) obj;
		return Objects.equals(brojTelefona, other.brojTelefona) && Objects.equals(ime, other.ime)
				&& Objects.equals(prezime, other.prezime);
	}

	@Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
     List<OpstiDomenskiObjekat> kupci = new ArrayList<>();
     try{
        while(rs.next()){

            int idKupac = rs.getInt("kupac.idKupac");
            String ime = rs.getString("kupac.ime");
            String prezime = rs.getString("kupac.prezime");
            String brojTelefona = rs.getString("kupac.brojTelefona");
            int mestoId = rs.getInt("kupac.mesto");
            String naziv = rs.getString("mesto.nazivMesta");
            Mesto m = new Mesto(mestoId,naziv);
            
           
            
            Kupac k = new Kupac(idKupac,ime,prezime,brojTelefona,m);
            kupci.add(k);
            
        }
        
        return kupci;
     } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste kupaca: " + ex.getMessage());
            return null;
        }

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,brojTelefona,mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return "'"+ime+"','"+prezime+"','"+brojTelefona+"',"+mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupac="+idKupac;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
         Kupac k = null;
        try {
            while (rs.next()) {
            int idKupac = rs.getInt("kupac.idKupac");
            String ime = rs.getString("kupac.ime");
            String prezime = rs.getString("kupac.prezime");
            String brojTelefona = rs.getString("kupac.brojTelefona");
            int mestoId = rs.getInt("kupac.mesto");
            String naziv = rs.getString("mesto.nazivMesta");
            Mesto m = new Mesto(mestoId,naziv);
            
           
            
             k = new Kupac(idKupac,ime,prezime,brojTelefona,m);

            }

            return k;

        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja kupaca: " + ex.getMessage());
            return null;
        }
        
        
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', brojTelefona='"+brojTelefona+"', mesto="+mesto.getIdMesto();
    }
    
    
    
    
    
    
}
