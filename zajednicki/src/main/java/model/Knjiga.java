package model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja knjigu koja se prodaje i cuva u odredjenom magacinu.
 *
 *@author Andjela
 * @see Magacin
 * @see StavkaRacuna
 */
public class Knjiga implements OpstiDomenskiObjekat {
    
    /** Jedinstveni identifikator knjige. */
    private int idKnjiga;
    
    /** Naslov knjige. */
    
    private String naslov;
    
    /** Zanr knjige. */
    private String zanr;
    
    /** Godina izdanja knjige. */
    private int godinaIzdanja;
    
    /** Cena knjige. */
    private double cena;
    
    /** Kolicina knjige dostupna u magacinu. */
    private int kolicina;
    
    /** Magacin u kojem se knjiga cuva, ne sme biti null. */
    private Magacin magacin;

    public Knjiga() {
    }

      
    /**
     * Konstruktor koji inicijalizuje sve atribute knjige ukljucujuci i ID.
     *
     * @param idKnjiga jedinstveni identifikator knjige
     * @param naslov naslov knjige
     * @param zanr zanr knjige
     * @param godinaIzdanja godina izdanja knjige
     * @param cena cena knjige
     * @param kolicina kolicina knjige dostupna u magacinu
     * @param magacin magacin u kojem se knjiga cuva
     */
    public Knjiga(int idKnjiga, String naslov, String zanr, int godinaIzdanja, double cena, int kolicina,Magacin magacin) {
        setIdKnjiga(idKnjiga);
        setNaslov(naslov);
        setZanr(zanr);
        setGodinaIzdanja(godinaIzdanja);
        setCena(cena);
        setKolicina(kolicina);
        setMagacin(magacin);
    }
    

    /**
     * Konstruktor koji inicijalizuje atribute knjige bez ID-a.
     * Koristi se prilikom kreiranja nove knjige pre unosa u bazu podataka.
     *
     * @param naslov naslov knjige
     * @param zanr zanr knjige
     * @param godinaIzdanja godina izdanja knjige
     * @param cena cena knjige
     * @param kolicina kolicina knjige dostupna u magacinu
     * @param magacin magacin u kojem se knjiga cuva
     */
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

    /**
     * Postavlja jedinstveni identifikator knjige.
     *
     * @param idKnjiga jedinstveni identifikator knjige, mora biti veci od nule
     * @throws IllegalArgumentException ako je idKnjiga manji ili jednak nuli
     */
    public void setIdKnjiga(int idKnjiga) {
    	if(idKnjiga<=0)
    		throw new IllegalArgumentException("ID knjige mora biti veći od nule.");
        this.idKnjiga = idKnjiga;
    }

    public String getNaslov() {
        return naslov;
    }

    /**
     * Postavlja naslov knjige.
     *
     * @param naslov naslov knjige, ne sme biti null, prazan ili sadrzati vise od 50 karaktera
     * @throws NullPointerException ako je naslov null
     * @throws IllegalArgumentException ako je naslov prazan ili duzi od 50 karaktera
     */
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

    /**
     * Postavlja zanr knjige.
     *
     * @param zanr zanr knjige, ne sme biti null, prazan ili sadrzati vise od 20 karaktera
     * @throws NullPointerException ako je zanr null
     * @throws IllegalArgumentException ako je zanr prazan ili duzi od 20 karaktera
     */
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

    /**
     * Postavlja godinu izdanja knjige.
     *
     * @param godinaIzdanja godina izdanja knjige, mora biti veca od nule i ne sme biti u buducnosti
     * @throws IllegalArgumentException ako je godinaIzdanja manja ili jednaka nuli, ili
     *                                   ako je veca od trenutne godine
     */
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

    /**
     * Postavlja cenu knjige.
     *
     * @param cena cena knjige, mora biti veca od nule
     * @throws IllegalArgumentException ako je cena manja ili jednaka nuli
     */
    public void setCena(double cena) {
    	
    	if(cena<=0)
    		throw new IllegalArgumentException("Cena mora biti veća od nule.");
    	
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    /**
     * Postavlja kolicinu knjige dostupnu u magacinu.
     *
     * @param kolicina kolicina knjige, ne sme biti negativna
     * @throws IllegalArgumentException ako je kolicina negativna
     */
    public void setKolicina(int kolicina) {
    	if(kolicina<0)
    		throw new IllegalArgumentException("Količina ne sme biti negativna.");
        this.kolicina = kolicina;
    }

    public Magacin getMagacin() {
        return magacin;
    }

    /**
     * Postavlja magacin u kojem se cuva knjiga.
     *
     * @param magacin magacin u kojem se cuva knjiga, ne sme biti null
     * @throws NullPointerException ako je magacin null
     */
    public void setMagacin(Magacin magacin) {
    	if(magacin==null)
    		throw new NullPointerException("Magacin ne sme biti null.");
        this.magacin = magacin;
    }
    

    /**
     * Vraca tekst koji sadrzi naslovom i cenu knjige.
     *
     * @return string sa naslovom i cenom knjige
     */
    @Override
    public String toString() {
        return  "naslov=" +naslov +", cena="+cena;
    }

    /**
     * Vraca hash kod knjige racunat na osnovu godine izdanja, naslova i zanra.
     *
     * @return hash kod knjige
     */
    @Override
	public int hashCode() {
		return Objects.hash(godinaIzdanja, naslov, zanr);
	}


    /**
     * Poredi ovu knjigu sa drugim objektom na osnovu godine izdanja, naslova i zanra.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su knjige istog tipa i imaju istu godinaIzdanja, naslov i zanr,
     *         false ako je obj null, ako je obj drugog tipa, ili ako se neki od tih atributa razlikuju
     */
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

	/**
     * Vraca naziv tabele "knjiga" u bazi podataka.
     *
     * @return naziv tabele "knjiga"
     */
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


