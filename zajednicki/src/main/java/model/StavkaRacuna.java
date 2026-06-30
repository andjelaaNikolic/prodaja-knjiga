
package model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;
import java.util.ArrayList;


public class StavkaRacuna implements OpstiDomenskiObjekat {
    
    private int rb;
    private int kolicina;
    private Knjiga knjiga;
    private double iznos;
    private Racun racun;
    private double jedinicnaCena;

    public StavkaRacuna() {
    }
    

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

    public void setJedinicnaCena(double jedinicnaCena) {
    	if(jedinicnaCena<=0)
    		throw new IllegalArgumentException("Jedinična cena mora biti veća od nule.");
        this.jedinicnaCena = jedinicnaCena;
    }



    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
    	if(racun==null)
    		throw new NullPointerException("Račun ne sme biti null.");
        this.racun = racun;
    }


    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
    	if(rb<=0)
    		throw new IllegalArgumentException("Redni broj mora biti veći od nule.");
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
    	if(kolicina<=0)
    		throw new IllegalArgumentException("Količina mora biti veća od nule.");
        this.kolicina = kolicina;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
    	if(knjiga==null)
    		throw new NullPointerException("Knjiga ne sme biti null.");
        this.knjiga = knjiga;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
    	if(iznos<=0)
    		throw new IllegalArgumentException("Iznos mora biti veći od nule.");
        this.iznos = iznos;
    }

    @Override
    public String toString() {
        return "rb=" + rb + ", kolicina=" + kolicina + ", knjiga=" + knjiga + ", iznos=" + iznos;
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
        final StavkaRacuna other = (StavkaRacuna) obj;
        return Objects.equals(this.knjiga, other.knjiga);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "stavka_racuna";
    }

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

