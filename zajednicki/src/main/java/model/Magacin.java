package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;

/**
 * Predstavlja magacin u kojem se cuvaju knjige.
 * Sadrzi osnovne podatke o magacinu i listu knjiga koje se u njemu nalaze.
 * 
 *@author Andjela
 * @see Knjiga
 */
public class Magacin implements OpstiDomenskiObjekat {

    /** Jedinstveni identifikator magacina u bazi podataka. */
    private int idMagacin;
    /** Naziv magacina. */
    private String naziv;
    /** Adresa magacina. */
    private String adresa;
    /** Lista knjiga koje se nalaze u ovom magacinu. */
    private List<Knjiga> knjige;

    public Magacin() {
    }

    /**
     * Konstruktor koji inicijalizuje naziv i adresu magacina bez ID-a.
     * Koristi se prilikom kreiranja novog magacina pre unosa u bazu podataka.
     *
     * @param naziv naziv magacina
     * @param adresa adresa magacina
     */
    public Magacin(String naziv, String adresa) {
    	setNaziv(naziv);
    	setAdresa(adresa);

    }

    /**
     * Konstruktor koji inicijalizuje sve atribute magacina ukljucujuci i ID.
     *
     * @param idMagacin jedinstveni identifikator magacina
     * @param naziv naziv magacina
     * @param adresa adresa magacina
     */
    public Magacin(int idMagacin, String naziv, String adresa) {
        setIdMagacin(idMagacin);
    	setNaziv(naziv);
    	setAdresa(adresa);

    }

    public int getIdMagacin() {
        return idMagacin;
    }

    /**
     * Postavlja jedinstveni identifikator magacina.
     *
     * @param idMagacin jedinstveni identifikator magacina, mora biti veci od nule
     * @throws IllegalArgumentException ako je idMagacin manji ili jednak nuli
     */
    public void setIdMagacin(int idMagacin) {
        if (idMagacin <= 0)
            throw new IllegalArgumentException("ID magacina mora biti veći od nule.");
        this.idMagacin = idMagacin;
    }

    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv magacina.
     *
     * @param naziv naziv magacina, ne sme biti null, prazan ili sadrzati vise od 20 karaktera
     * @throws NullPointerException ako je naziv null
     * @throws IllegalArgumentException ako je naziv prazan ili duzi od 20 karaktera
     */
    public void setNaziv(String naziv) {
        if (naziv == null)
            throw new NullPointerException("Naziv ne sme biti null.");
        if (naziv.length() > 20)
            throw new IllegalArgumentException("Naziv ne sme imati više od 20 karaktera.");
        if(naziv.isBlank()) {
        	throw new IllegalArgumentException("Naziv ne sme biti prazan.");
        }
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    /**
     * Postavlja adresu magacina.
     *
     * @param adresa adresa magacina, ne sme biti null ili sadrzati vise od 50 karaktera
     * @throws NullPointerException ako je adresa null
     * @throws IllegalArgumentException ako je adresa duza od 50 karaktera
     */
    public void setAdresa(String adresa) {
        if (adresa == null)
            throw new NullPointerException("Adresa ne sme biti null.");
        if (adresa.length() > 50)
            throw new IllegalArgumentException("Adresa ne sme imati više od 50 karaktera.");
        this.adresa = adresa;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }

    /**
     * Vraca tekstualnu reprezentaciju magacina koja sadrzi njegov naziv.
     *
     * @return naziv magacina
     */
    @Override
    public String toString() {
        return naziv;
    }

    /**
     * Vraca hash kod magacina racunat na osnovu naziva magacina.
     *
     * @return hash kod magacina
     */
    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    /**
     * Poredi ovaj magacin sa drugim objektom na osnovu naziva magacina.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su magacini istog tipa i imaju isti naziv, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se naziv razlikuje
     */
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

    /**
     * Vraca naziv tabele "magacin" u bazi podataka.
     *
     * @return naziv tabele "magacin"
     */
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

