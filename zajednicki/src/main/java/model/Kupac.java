
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
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.mesto = mesto;
    }


    public Kupac(int idKupac, String ime, String prezime, String brojTelefona, Mesto mesto) {
        this.idKupac = idKupac;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.mesto = mesto;
    }

    public int getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(int idKupac) {
        this.idKupac = idKupac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return  "id=" + idKupac + ", ime i prezime=" + ime + " " + prezime ;
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
        final Kupac other = (Kupac) obj;
        if (this.idKupac != other.idKupac) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        return Objects.equals(this.mesto, other.mesto);
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
