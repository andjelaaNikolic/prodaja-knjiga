
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Knjiga implements OpstiDomenskiObjekat {
    
    private int idKnjiga;
    private String naslov;
    private String zanr;
    private int godinaIzdanja;
    private double cena;
    
    private int kolicina;
    private Magacin magacin;

    public Knjiga() {
    }

    /*
    public Knjiga( String naslov, String zanr, int godinaIzdanja, double cena) {
        
        this.naslov = naslov;
        this.zanr = zanr;
        this.godinaIzdanja = godinaIzdanja;
        this.cena = cena;
    }*/
      
    public Knjiga(int idKnjiga, String naslov, String zanr, int godinaIzdanja, double cena, int kolicina,Magacin magacin) {
        this.idKnjiga = idKnjiga;
        this.naslov = naslov;
        this.zanr = zanr;
        this.godinaIzdanja = godinaIzdanja;
        this.cena = cena;
        this.kolicina = kolicina;
        this.magacin = magacin;
    }
    
    /*
        public Knjiga(int idKnjiga, String naslov, String zanr, int godinaIzdanja, double cena) {
        this.idKnjiga = idKnjiga;
        this.naslov = naslov;
        this.zanr = zanr;
        this.godinaIzdanja = godinaIzdanja;
        this.cena = cena;
    }*/

    public Knjiga(String naslov, String zanr, int godinaIzdanja, double cena, int kolicina, Magacin magacin) {
        this.naslov = naslov;
        this.zanr = zanr;
        this.godinaIzdanja = godinaIzdanja;
        this.cena = cena;
        this.kolicina = kolicina;
        this.magacin = magacin;
    }


    public int getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Magacin getMagacin() {
        return magacin;
    }

    public void setMagacin(Magacin magacin) {
        this.magacin = magacin;
    }
    
    
    
    

    @Override
    public String toString() {
        return  "naslov=" +naslov +", cena="+cena;
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
        final Knjiga other = (Knjiga) obj;
        if (this.idKnjiga != other.idKnjiga) {
            return false;
        }
        if (this.godinaIzdanja != other.godinaIzdanja) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (!Objects.equals(this.naslov, other.naslov)) {
            return false;
        }
        return Objects.equals(this.zanr, other.zanr);
    }

    @Override
    public String vratiNazivTabele() {
       return "knjiga";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){

            int idKnjiga = rs.getInt("knjiga.idKnjiga");
            String naslov = rs.getString("knjiga.naslov");
            String zanr = rs.getString("knjiga.zanr");
            int godinaIzdanja = rs.getInt("knjiga.godinaIzdanja");
            double cena = rs.getDouble("knjiga.cena");
            int kolicina = rs.getInt("knjiga.kolicina");
            int magacinId = rs.getInt("knjiga.magacin");
         
            String naziv = rs.getString("magacin.naziv");
            String adresa = rs.getString("magacin.adresa");
               
            Magacin m = new Magacin(magacinId, naziv, adresa);
            
            Knjiga k = new Knjiga(idKnjiga,naslov,zanr,godinaIzdanja,cena,kolicina,m);
            lista.add(k);
            
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naslov,zanr,godinaIzdanja,cena,kolicina,magacin";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return  "'"+naslov+"','"+zanr+"',"+godinaIzdanja+","+cena+","+kolicina+","+magacin.getIdMagacin();
    }

    @Override
    public String vratiPrimarniKljuc() {
       return "knjiga.idKnjiga="+idKnjiga;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
               Knjiga knjiga = null;
        try {
            while (rs.next()) {

                int idKnjiga = rs.getInt("knjiga.idKnjiga");
                String naslov = rs.getString("knjiga.naslov");
                String zanr = rs.getString("knjiga.zanr");
                double cena = rs.getDouble("knjiga.cena");
                int godinaIzdanja = rs.getInt("knjiga.godinaIzdanja");
                int kolicina = rs.getInt("knjiga.kolicina");
                int magacinId = rs.getInt("knjiga.magacin");
                String naziv = rs.getString("magacin.naziv");
                String adresa = rs.getString("magacin.adresa");
                Magacin m = new Magacin(magacinId, naziv, adresa);
            
                knjiga = new Knjiga(idKnjiga,naslov,zanr,godinaIzdanja,cena,kolicina,m);
            }
            return knjiga;
        } catch (Exception ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja knjige: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naslov='"+naslov+"', zanr='"+zanr+"', godinaIzdanja="+godinaIzdanja+", cena="+cena+", kolicina="+kolicina+", magacin="+magacin.getIdMagacin();
    }
    
    
    
    
    
}
