package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;

public class Magacin implements OpstiDomenskiObjekat {

    private int idMagacin;
    private String naziv;
    private String adresa;
    private List<Knjiga> knjige;

    public Magacin() {
    }

    public Magacin(String naziv, String adresa) {
    	setNaziv(naziv);
    	setAdresa(adresa);

    }

    public Magacin(int idMagacin, String naziv, String adresa) {
        setIdMagacin(idMagacin);
    	setNaziv(naziv);
    	setAdresa(adresa);

    }

    public int getIdMagacin() {
        return idMagacin;
    }

    public void setIdMagacin(int idMagacin) {
        if (idMagacin <= 0)
            throw new IllegalArgumentException();
        this.idMagacin = idMagacin;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if (naziv == null)
            throw new NullPointerException();
        if (naziv.length() > 20)
            throw new IllegalArgumentException();
        if(naziv.isBlank()) {
        	throw new IllegalArgumentException();
        }
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        if (adresa == null)
            throw new NullPointerException();
        if (adresa.length() > 50)
            throw new IllegalArgumentException();
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
        return naziv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Magacin other = (Magacin) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "magacin";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idMagacin = rs.getInt("magacin.idMagacin");
            String naziv = rs.getString("magacin.naziv");
            String adresa = rs.getString("magacin.adresa");
            Magacin m = new Magacin(idMagacin, naziv, adresa);
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
        return "'" + naziv + "','" + adresa + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "magacin.idMagacin=" + idMagacin;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Magacin magacin = null;
        try {
            while (rs.next()) {
                int idMagacin = rs.getInt("magacin.idMagacin");
                String naziv = rs.getString("magacin.naziv");
                String adresa = rs.getString("magacin.adresa");
                magacin = new Magacin(idMagacin, naziv, adresa);
            }
            return magacin;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja magacina: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', adresa='" + adresa + "'";
    }
}
