/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;


public class Racun implements OpstiDomenskiObjekat {
    
    private int idRacun;
    private Date datum;
    private double ukupanIznos;
    private Prodavac prodavac;
    private Kupac kupac;
    private List<StavkaRacuna> stavke= new ArrayList<>();

    public Racun() {
    }
    
 public Racun(Date datum, double ukupanIznos, Prodavac prodavac, Kupac kupac) {
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.prodavac = prodavac;
        this.kupac = kupac;
    }

    public Racun(int idRacun, Date datum, double ukupanIznos, Prodavac prodavac, Kupac kupac) {
        this.idRacun = idRacun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.prodavac = prodavac;
        this.kupac = kupac;
    }

    @Override
    public String vratiNazivTabele() {
       return "racun";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                int idRacun = rs.getInt("racun.idRacun");
                double ukupanIznos = rs.getDouble("racun.ukupanIznos");
                Date datum = rs.getDate("racun.datum");
                Date datumSredjen = new Date(datum.getTime());
                int idProdavac = rs.getInt("racun.prodavac");
                int idKupac = rs.getInt("racun.kupac");
                String imeP = rs.getString("prodavac.ime");
                String prezimeP = rs.getString("prodavac.prezime");
                String korisnickoIme = rs.getString("prodavac.korisnickoIme");
                String sifra = rs.getString("prodavac.sifra");
                String email = rs.getString("prodavac.email");
                int idMesto = rs.getInt("kupac.mesto");
                String nazivMesta = rs.getString("mesto.nazivMesta");
                String imeK = rs.getString("kupac.ime");
                String prezimeK = rs.getString("kupac.prezime");
                String brojTelefona = rs.getString("kupac.brojTelefona");
                
                Mesto m = new Mesto(idMesto, nazivMesta);
                Kupac k = new Kupac(idKupac, imeK, prezimeK, brojTelefona, m);
                Prodavac p = new Prodavac(idProdavac, imeP, prezimeP, korisnickoIme, sifra, email);
                
                Racun racun = new Racun(idRacun, datumSredjen, ukupanIznos, p, k);
                lista.add(racun);
            }
            return lista;
        }catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste racuna: " + e.getMessage());
            return null;
        }
   
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum, ukupanIznos, prodavac, kupac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String datumString = sdf.format(datum);
        return "'"+datumString+"', "+ukupanIznos+", "+prodavac.getIdProdavac()+", "+kupac.getIdKupac();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.idRacun="+idRacun;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        try {
            Racun r=null;
            while (rs.next()) {
                int idRacun = rs.getInt("racun.idRacun");
                double ukupanIznos = rs.getDouble("racun.ukupanIznos");
                Date datum = rs.getDate("racun.datum");
                Date datumSredjen = new Date(datum.getTime());
                int idProdavac = rs.getInt("racun.prodavac");
                int idKupac = rs.getInt("racun.kupac");
                String imeP = rs.getString("prodavac.ime");
                String prezimeP = rs.getString("prodavac.prezime");
                String korisnickoIme = rs.getString("prodavac.korisnickoIme");
                String sifra = rs.getString("prodavac.sifra");
                String email = rs.getString("prodavac.email");
                int idMesto = rs.getInt("kupac.mesto");
                String nazivMesta = rs.getString("mesto.nazivMesta");
                String imeK = rs.getString("kupac.ime");
                String prezimeK = rs.getString("kupac.prezime");
                String brojTelefona = rs.getString("kupac.brojTelefona");
                
                Mesto m = new Mesto(idMesto, nazivMesta);
                Kupac k = new Kupac(idKupac, imeK, prezimeK, brojTelefona, m);
                Prodavac p = new Prodavac(idProdavac, imeP, prezimeP, korisnickoIme, sifra, email);
                
                r = new Racun(idRacun, datumSredjen, ukupanIznos, p, k);
                
            }
            return r;
        }catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja racuna: " + e.getMessage());
            return null;
        }
       
        
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='"+datum+"', ukupanIznos="+ukupanIznos+", prodavac="+prodavac.getIdProdavac()+", kupac="+kupac.getIdKupac();
    }

    public int getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) throws Exception {
        if (datum.after(new Date())) {
            throw new Exception("Datum ne moze biti u budućnosti.");
        } else {
            this.datum = datum;
        }
        this.datum = datum;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavka) {
        this.stavke = stavka;
    }

  
    
    
    
    
    
    
    
    
}
