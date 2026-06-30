
package model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
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

      
    public Knjiga(int idKnjiga, String naslov, String zanr, int godinaIzdanja, double cena, int kolicina,Magacin magacin) {
        setIdKnjiga(idKnjiga);
        setNaslov(naslov);
        setZanr(zanr);
        setGodinaIzdanja(godinaIzdanja);
        setCena(cena);
        setKolicina(kolicina);
        setMagacin(magacin);
    }
    

    public Knjiga(String naslov, String zanr, int godinaIzdanja, double cena, int kolicina, Magacin magacin) {
        setNaslov(naslov);
        setZanr(zanr);
        setGodinaIzdanja(godinaIzdanja);
        setCena(cena);
        setKolicina(kolicina);
        setMagacin(magacin);
    }


    public int getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
    	if(idKnjiga<=0)
    		throw new IllegalArgumentException("ID knjige mora biti veći od nule.");
        this.idKnjiga = idKnjiga;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
    	if(naslov==null)
    		throw new NullPointerException("Naslov ne sme biti null.");
    	if(naslov.isBlank())
    		throw new IllegalArgumentException("Naslov ne sme biti prazan.");
    	if(naslov.length()>50)
    		throw new IllegalArgumentException("Naslov ne sme imati više od 50 karaktera.");
        this.naslov = naslov;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
    	if(zanr==null)
    		throw new NullPointerException("Žanr ne sme biti null.");
    	if(zanr.isBlank())
    		throw new IllegalArgumentException("Žanr ne sme biti prazan.");
    	if(zanr.length()>20)
    		throw new IllegalArgumentException("Žanr ne sme imati više od 20 karaktera.");
        this.zanr = zanr;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
    	if(godinaIzdanja<=0)
    		throw new IllegalArgumentException("Godina izdanja mora biti veća od nule.");
    	
    	LocalDateTime dt = LocalDateTime.now();
    	int godina = dt.getYear();
    	
    	if(godinaIzdanja>godina)
    		throw new IllegalArgumentException("Godina izdanja ne sme biti u budućnosti.");
    	
        this.godinaIzdanja = godinaIzdanja;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
    	
    	if(cena<=0)
    		throw new IllegalArgumentException("Cena mora biti veća od nule.");
    	
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
    	if(kolicina<0)
    		throw new IllegalArgumentException("Količina ne sme biti negativna.");
        this.kolicina = kolicina;
    }

    public Magacin getMagacin() {
        return magacin;
    }

    public void setMagacin(Magacin magacin) {
    	if(magacin==null)
    		throw new NullPointerException("Magacin ne sme biti null.");
        this.magacin = magacin;
    }
    

    @Override
    public String toString() {
        return  "naslov=" +naslov +", cena="+cena;
    }

    @Override
	public int hashCode() {
		return Objects.hash(godinaIzdanja, naslov, zanr);
	}


    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Knjiga other = (Knjiga) obj;
		return godinaIzdanja == other.godinaIzdanja && Objects.equals(naslov, other.naslov)
				&& Objects.equals(zanr, other.zanr);
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

