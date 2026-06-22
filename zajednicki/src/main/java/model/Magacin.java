/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.sql.SQLException;

/**
 *
 * @author Andjela
 */
public class Magacin implements OpstiDomenskiObjekat {

    
    private int idMagacin;
    
    private String naziv;
    
    private String adresa;
    
    private List<Knjiga> knjige;

    public Magacin() {
    }

    public Magacin(int idMagacin, String naziv, String adresa) {
        this.idMagacin = idMagacin;
        this.naziv = naziv;
        this.adresa = adresa;
    }
    
      public Magacin(String naziv, String adresa) {
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public int getIdMagacin() {
        return idMagacin;
    }

    public void setIdMagacin(int idMagacin) {
        this.idMagacin = idMagacin;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }

    @Override
    public String toString() {
        return "Magacin{" + "naziv=" + naziv + ", adresa=" + adresa + '}';
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
        final Magacin other = (Magacin) obj;
        return Objects.equals(this.naziv, other.naziv);
    }
    
    
    
    
    
    @Override
    public String vratiNazivTabele() {
         return "magacin";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){

            int idMagacin = rs.getInt("magacin.idMagacin");
            String naziv = rs.getString("magacin.naziv");
            String adresa = rs.getString("magacin.adresa");
           
            Magacin m = new Magacin(idMagacin,naziv,adresa);
            lista.add(m);
            
        }
        
        return lista;
    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,adresa";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return "'"+naziv+"','"+adresa+"','";
    }

    @Override
    public String vratiPrimarniKljuc() {
       return "magacin.idMagacin="+idMagacin;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Magacin magacin = null;
        try {
            while (rs.next()) {

                int idMagacin = rs.getInt("magacin.idMagacin");
                String naziv = rs.getString("magacin.naziv");
                String adresa = rs.getString("magacin.adresa");
                magacin = new Magacin(idMagacin, naziv,adresa);
            }
            return magacin;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja magacina: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
            return "naziv='"+naziv+"', adresa='"+adresa+"'";
    }
    
}

