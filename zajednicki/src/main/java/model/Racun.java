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

/**
 * Predstavlja racun koji je prodavac izdao kupcu.
 * Sadrzi listu stavki racuna koje opisuju koje knjige su kupljene i u
 * kojoj kolicini, kao i ukupan iznos racuna.
 *
 *@author Andjela
 * @see StavkaRacuna
 * @see Kupac
 * @see Prodavac
 */
public class Racun implements OpstiDomenskiObjekat {
    
    /** Jedinstveni identifikator racuna u bazi podataka. */
    private int idRacun;
    /** Datum izdavanja racuna, ne sme biti u buducnosti. */
    private Date datum;
    /** Ukupan iznos racuna. */
    private double ukupanIznos;
    /** Prodavac koji je izdao racun, ne sme biti null. */
    private Prodavac prodavac;
    /** Kupac kojem je racun izdat, ne sme biti null. */
    private Kupac kupac;
    /** Lista stavki koje opisuju kupljene knjige u okviru ovog racuna. */
    private List<StavkaRacuna> stavke= new ArrayList<>();

    public Racun() {
    }
    
    /**
     * Konstruktor koji inicijalizuje atribute racuna bez ID-a.
     * Koristi se prilikom kreiranja novog racuna pre unosa u bazu podataka.
     *
     * @param datum datum izdavanja racuna
     * @param ukupanIznos ukupan iznos racuna
     * @param prodavac prodavac koji je izdao racun
     * @param kupac kupac kojem je racun izdat
     */
	public Racun(Date datum, double ukupanIznos, Prodavac prodavac, Kupac kupac) {
		setDatum(datum);
		setUkupanIznos(ukupanIznos);
		setProdavac(prodavac);
		setKupac(kupac);

	}

	/**
     * Konstruktor koji inicijalizuje sve atribute racuna ukljucujuci i ID.
     *
     * @param idRacun jedinstveni identifikator racuna
     * @param datum datum izdavanja racuna
     * @param ukupanIznos ukupan iznos racuna
     * @param prodavac prodavac koji je izdao racun
     * @param kupac kupac kojem je racun izdat
     */
	public Racun(int idRacun, Date datum, double ukupanIznos, Prodavac prodavac, Kupac kupac) {
		setIdRacun(idRacun);
		setDatum(datum);
		setUkupanIznos(ukupanIznos);
		setProdavac(prodavac);
		setKupac(kupac);

	}

    /**
     * Vraca naziv tabele "racun" u bazi podataka.
     *
     * @return naziv tabele "racun"
     */
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

    /**
     * Postavlja jedinstveni identifikator racuna.
     *
     * @param idRacun jedinstveni identifikator racuna, mora biti veci od nule
     * @throws IllegalArgumentException ako je idRacun manji ili jednak nuli
     */
    public void setIdRacun(int idRacun) {
    	if(idRacun<=0)
    		throw new IllegalArgumentException("ID računa mora biti veći od nule.");
        this.idRacun = idRacun;
    }

    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum izdavanja racuna.
     *
     * @param datum datum izdavanja racuna, ne sme biti null niti u buducnosti
     * @throws NullPointerException ako je datum null
     * @throws IllegalArgumentException ako je datum u buducnosti
     */
    public void setDatum(Date datum) {
        if(datum==null)
        	throw new NullPointerException("Datum ne sme biti null.");
        if (datum.after(new Date())) 
            throw new IllegalArgumentException("Datum ne sme biti u budućnosti.");

        this.datum = datum;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Postavlja ukupan iznos racuna.
     *
     * @param ukupanIznos ukupan iznos racuna, mora biti veci od nule
     * @throws IllegalArgumentException ako je ukupanIznos manji ili jednak nuli
     */
    public void setUkupanIznos(double ukupanIznos) {
    	if(ukupanIznos<=0)
    		throw new IllegalArgumentException("Ukupan iznos mora biti veći od nule.");
        this.ukupanIznos = ukupanIznos;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    /**
     * Postavlja prodavca koji je izdao racun.
     *
     * @param prodavac prodavac, ne sme biti null
     * @throws NullPointerException ako je prodavac null
     */
    public void setProdavac(Prodavac prodavac) {
    	if(prodavac==null)
    		throw new NullPointerException("Prodavac ne sme biti null.");
        this.prodavac = prodavac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    /**
     * Postavlja kupca kojem je racun izdat.
     *
     * @param kupac kupac, ne sme biti null
     * @throws NullPointerException ako je kupac null
     */
    public void setKupac(Kupac kupac) {
    	if(kupac==null)
    		throw new NullPointerException("Kupac ne sme biti null.");
        this.kupac = kupac;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavka) {
        this.stavke = stavka;
    }

   
    
}

