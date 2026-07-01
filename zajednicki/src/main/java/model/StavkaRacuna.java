package model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja stavku racuna koja opisuje jednu kupljenu knjigu
 * u okviru racuna, zajedno sa kolicinom, jedinicnom cenom i ukupnim iznosom stavke.
 *
 *@author Andjela
 * @see Racun
 * @see Knjiga
 */
public class StavkaRacuna implements OpstiDomenskiObjekat {
    
    /** Redni broj stavke u okviru racuna. */
    private int rb;
    /** Kolicina kupljene knjige u okviru ove stavke. */
    private int kolicina;
    /** Kupljena knjiga u okviru ove stavke, ne sme biti null. */
    private Knjiga knjiga;
    /** Ukupan iznos za ovu stavku. */
    private double iznos;
    /** Racun u okviru kojeg se nalazi ova stavka, ne sme biti null. */
    private Racun racun;
    /** Jedinicna cena knjige u trenutku kupovine. */
    private double jedinicnaCena;

    public StavkaRacuna() {
    }
    

    /**
     * Konstruktor koji inicijalizuje sve atribute stavke racuna.
     *
     * @param rb redni broj stavke u okviru racuna
     * @param kolicina kolicina kupljene knjige
     * @param knjiga kupljena knjiga
     * @param iznos ukupan iznos stavke
     * @param racun racun kojem stavka pripada
     * @param jedinicnaCena jedinicna cena knjige u trenutku kupovine
     */
    public StavkaRacuna(int rb, int kolicina, Knjiga knjiga, double iznos, Racun racun, double jedinicnaCena) {
    	setRb(rb);
    	setKolicina(kolicina);
    	setKnjiga(knjiga);
    	setIznos(iznos);
    	setRacun(racun);
    	setJedinicnaCena(jedinicnaCena);

    }



    public double getJedinicnaCena() {
        return jedinicnaCena;
    }

    /**
     * Postavlja jedinicnu cenu knjige u trenutku kupovine.
     *
     * @param jedinicnaCena jedinicna cena, mora biti veca od nule
     * @throws IllegalArgumentException ako je jedinicnaCena manja ili jednaka nuli
     */
    public void setJedinicnaCena(double jedinicnaCena) {
    	if(jedinicnaCena<=0)
    		throw new IllegalArgumentException("Jedinična cena mora biti veća od nule.");
        this.jedinicnaCena = jedinicnaCena;
    }



    public Racun getRacun() {
        return racun;
    }

    /**
     * Postavlja racun kojem stavka pripada.
     *
     * @param racun racun, ne sme biti null
     * @throws NullPointerException ako je racun null
     */
    public void setRacun(Racun racun) {
    	if(racun==null)
    		throw new NullPointerException("Račun ne sme biti null.");
        this.racun = racun;
    }


    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke u okviru racuna.
     *
     * @param rb redni broj stavke, mora biti veci od nule
     * @throws IllegalArgumentException ako je rb manji ili jednak nuli
     */
    public void setRb(int rb) {
    	if(rb<=0)
    		throw new IllegalArgumentException("Redni broj mora biti veći od nule.");
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    /**
     * Postavlja kolicinu kupljene knjige u okviru ove stavke.
     *
     * @param kolicina kolicina, mora biti veca od nule
     * @throws IllegalArgumentException ako je kolicina manja ili jednaka nuli
     */
    public void setKolicina(int kolicina) {
    	if(kolicina<=0)
    		throw new IllegalArgumentException("Količina mora biti veća od nule.");
        this.kolicina = kolicina;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    /**
     * Postavlja kupljenu knjigu u okviru ove stavke.
     *
     * @param knjiga knjiga, ne sme biti null
     * @throws NullPointerException ako je knjiga null
     */
    public void setKnjiga(Knjiga knjiga) {
    	if(knjiga==null)
    		throw new NullPointerException("Knjiga ne sme biti null.");
        this.knjiga = knjiga;
    }

    public double getIznos() {
        return iznos;
    }

    /**
     * Postavlja ukupan iznos za ovu stavku.
     *
     * @param iznos ukupan iznos, mora biti veci od nule
     * @throws IllegalArgumentException ako je iznos manji ili jednak nuli
     */
    public void setIznos(double iznos) {
    	if(iznos<=0)
    		throw new IllegalArgumentException("Iznos mora biti veći od nule.");
        this.iznos = iznos;
    }

    /**
     * Vraca tekstualnu reprezentaciju stavke racuna sa svim atributima.
     *
     * @return string sa svim atributima stavke
     */
    @Override
    public String toString() {
        return "rb=" + rb + ", kolicina=" + kolicina + ", knjiga=" + knjiga + ", iznos=" + iznos;
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
     * Poredi ovu stavku racuna sa drugim objektom na osnovu kupljene knjige.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su stavke istog tipa i imaju istu knjigu, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se knjiga razlikuje
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
        final StavkaRacuna other = (StavkaRacuna) obj;
        return Objects.equals(this.knjiga, other.knjiga);
    }

    

    /**
     * Vraca naziv tabele "stavka_racuna" u bazi podataka.
     *
     * @return naziv tabele "stavka_racuna"
     */
    @Override
    public String vratiNazivTabele() {
        return "stavka_racuna";
    }

    /**
     * Vraca listu stavki racuna kreiranih na osnovu podataka iz ResultSet-a.
     * Svaka stavka se kreira sa racunom koji ima postavljen samo ID - ostali
     * podaci o racunu (kupac, prodavac, ukupan iznos) se ne ucitavaju.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista stavki racuna kreiranih iz ResultSet-a, ili null
     *         ako dodje do greske pri citanju podataka
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            
            while (rs.next()) {

                int rb = rs.getInt("stavka_racuna.rb");
                int kolicina = rs.getInt("stavka_racuna.kolicina");
                double iznos = rs.getDouble("stavka_racuna.iznos");

                int idKnjiga = rs.getInt("stavka_racuna.knjiga");
                String naslov = rs.getString("knjiga.naslov");
                String zanr = rs.getString("knjiga.zanr");
                double cena = rs.getDouble("knjiga.cena");
                int dostupnaKolicina = rs.getInt("knjiga.kolicina");
                int magacinId = rs.getInt("knjiga.magacin");
                String naziv = rs.getString("magacin.naziv");
                String adresa = rs.getString("magacin.adresa");
                int godinaIzdanja = rs.getInt("knjiga.godinaIzdanja");
                
                Magacin magacin = new Magacin(magacinId,naziv,adresa);
                
                int idRacun = rs.getInt("stavka_racuna.racun");
                Racun r = new Racun();
                r.setIdRacun(idRacun);
                
                
                Knjiga k = new Knjiga(idKnjiga, naslov, zanr, godinaIzdanja, cena,dostupnaKolicina,magacin);
                
                StavkaRacuna sr = new StavkaRacuna(rb, kolicina, k, iznos, r, k.getCena());
                lista.add(sr);

            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste stavki racuna: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
       return "racun,rb,kolicina,knjiga,iznos,jedinicnaCena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return racun.getIdRacun() + ", " + rb + ", " + kolicina + ", " + knjiga.getIdKnjiga() + ", " + iznos + ", " + jedinicnaCena;
    }
    @Override
    public String vratiVrednostiZaIzmenu() {
        return "racun = " + racun.getIdRacun() + ", rb = " + rb + ", kolicina = " + kolicina + ", knjiga = " + knjiga.getIdKnjiga() + ", iznos = " + iznos + ", jedinicnaCena = " + jedinicnaCena;
    }

    @Override
    public String vratiPrimarniKljuc() {
       return "racun = " + racun.getIdRacun() + " AND rb = " + rb;
    }

    /**
     * Vraca jednu stavku racuna kreiranu na osnovu podataka iz ResultSet-a.
     * Stavka se kreira sa racunom koji ima postavljen samo ID - ostali
     * podaci o racunu (kupac, prodavac, ukupan iznos) se ne ucitavaju.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat stavke racuna kreiran iz ResultSet-a, ili null
     *         ako dodje do greske pri citanju podataka
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        try {
            StavkaRacuna sr = null;
            while (rs.next()) {

                int rb = rs.getInt("stavka_racuna.rb");
                int kolicina = rs.getInt("stavka_racuna.kolicina");
                double iznos = rs.getDouble("stavka_racuna.iznos");
                //double jedinicnaCena = rs.getDouble("stavka_racuna.jedinicnaCena");
                int idKnjiga = rs.getInt("stavka_racuna.knjiga");
                String naslov = rs.getString("knjiga.naslov");
                String zanr = rs.getString("knjiga.zanr");
                double cena = rs.getDouble("knjiga.cena");
                int godinaIzdanja = rs.getInt("knjiga.godinaIzdanja");
                int dostupnaKolicina = rs.getInt("knjiga.kolicina");
                
                
                int magacinId = rs.getInt("knjiga.magacin");
                String naziv = rs.getString("magacin.naziv");
                String adresa = rs.getString("magacin.adresa");
               
                Magacin magacin = new Magacin(magacinId,naziv,adresa);
                
                int idRacun = rs.getInt("stavka_racuna.racun");
                Racun r = new Racun();
                r.setIdRacun(idRacun);
                
                Knjiga k = new Knjiga(idKnjiga, naslov, zanr, godinaIzdanja, cena,dostupnaKolicina,magacin);
                sr = new StavkaRacuna(rb, kolicina, k, iznos, r, k.getCena());
               

            }
            return sr;
        } catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja stavke racuna: " + e.getMessage());
            return null;
        }
    }

    
    
}

