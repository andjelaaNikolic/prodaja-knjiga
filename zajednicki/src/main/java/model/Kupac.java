package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;

/**
 * Predstavlja kupca koji kupuje knjige.
 * Sadrzi osnovne podatke o kupcu i mesto u kojem zivi.
 * 
 * @author Andjela
 *
 * @see Mesto
 * @see Racun
 */
public class Kupac implements OpstiDomenskiObjekat{
    /** Jedinstveni identifikator kupca u bazi podataka. */
    private int idKupac;
    
    /** Ime kupca. */
    private String ime;
    
    /** Prezime kupca. */
    private String prezime;
    
    /** Broj telefona kupca. */
    private String brojTelefona;
    
    /** Mesto u kojem kupac zivi, ne sme biti null. */
    private Mesto mesto;

    public Kupac() {
    }
    
    /**
     * Konstruktor koji inicijalizuje atribute kupca bez ID-a.
     * Koristi se prilikom kreiranja novog kupca pre unosa u bazu podataka.
     *
     * @param ime ime kupca
     * @param prezime prezime kupca
     * @param brojTelefona broj telefona kupca
     * @param mesto mesto u kojem kupac zivi
     */
    public Kupac( String ime, String prezime, String brojTelefona, Mesto mesto) {
    	setIme(ime);
    	setPrezime(prezime);
    	setBrojTelefona(brojTelefona);
    	setMesto(mesto);
    }


    /**
     * Konstruktor koji inicijalizuje sve atribute kupca ukljucujuci i ID.
     *
     * @param idKupac jedinstveni identifikator kupca
     * @param ime ime kupca
     * @param prezime prezime kupca
     * @param brojTelefona broj telefona kupca
     * @param mesto mesto u kojem kupac zivi
     */
    public Kupac(int idKupac, String ime, String prezime, String brojTelefona, Mesto mesto) {
    	setIdKupac(idKupac);
    	setIme(ime);
    	setPrezime(prezime);
    	setBrojTelefona(brojTelefona);
    	setMesto(mesto);
    }

    public int getIdKupac() {
        return idKupac;
    }

    /**
     * Postavlja jedinstveni identifikator kupca.
     *
     * @param idKupac jedinstveni identifikator kupca, mora biti veci od nule
     * @throws IllegalArgumentException ako je idKupac manji ili jednak nuli
     */
    public void setIdKupac(int idKupac) {
    	if(idKupac<=0) {
    		throw new IllegalArgumentException("ID kupca mora biti veći od nule.");
    	}
        this.idKupac = idKupac;
    }

    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime kupca.
     *
     * @param ime ime kupca, ne sme biti null, prazno, duze od 50 karaktera
     *            ili sadrzati znakove koji nisu slova
     * @throws NullPointerException ako je ime null
     * @throws IllegalArgumentException ako je ime prazno, duze od 50 karaktera
     *                                   ili sadrzi znakove koji nisu slova
     */
    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("Ime ne sme biti null.");
    	}
    	
    	if(ime.length()>50) 
    		throw new IllegalArgumentException("Ime ne sme imati više od 50 karaktera.");
    	
    	if (ime.isBlank())
            throw new IllegalArgumentException("Ime ne sme biti prazno.");
    	
        if (!ime.matches("[a-zA-ZčćšđžČĆŠĐŽ ]+"))
            throw new IllegalArgumentException("Ime sme sadržati samo slova.");
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime kupca.
     *
     * @param prezime prezime kupca, ne sme biti null, prazno, duze od 50 karaktera
     *                ili sadrzati znakove koji nisu slova
     * @throws NullPointerException ako je prezime null
     * @throws IllegalArgumentException ako je prezime prazno, duze od 50 karaktera
     *                                   ili sadrzi znakove koji nisu slova
     */
    public void setPrezime(String prezime) {
    	if(prezime==null) 
    		throw new NullPointerException("Prezime ne sme biti null.");
    	
    	if(prezime.length()>50) 
    		throw new IllegalArgumentException("Prezime ne sme imati više od 50 karaktera.");
    	
    	if (prezime.isBlank())
            throw new IllegalArgumentException("Prezime ne sme biti prazno.");
    	
        if (!prezime.matches("[a-zA-ZčćšđžČĆŠĐŽ ]+"))
            throw new IllegalArgumentException("Prezime sme sadržati samo slova.");
    	
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona kupca.
     *
     * @param brojTelefona broj telefona kupca, ne sme biti null, prazan, duzi od 10
     *                     cifara ili sadrzati znakove koji nisu cifre
     * @throws NullPointerException ako je brojTelefona null
     * @throws IllegalArgumentException ako je brojTelefona prazan, duzi od 10 cifara
     *                                   ili sadrzi znakove koji nisu cifre
     */
    public void setBrojTelefona(String brojTelefona) {
    	if(brojTelefona==null) {
    		throw new NullPointerException("Broj telefona ne sme biti null.");
    	}
    	if(brojTelefona.length()>10) {
    		throw new IllegalArgumentException("Broj telefona ne sme imati više od 10 cifara.");
    	}
    	if (!brojTelefona.matches("[0-9]+"))
            throw new IllegalArgumentException("Broj telefona sme sadržati samo cifre.");
    	
    	if (brojTelefona.isBlank())
            throw new IllegalArgumentException("Broj telefona ne sme biti prazan.");
    	
        this.brojTelefona = brojTelefona;
    }

    public Mesto getMesto() {
        return mesto;
    }

    /**
     * Postavlja mesto u kojem kupac zivi.
     *
     * @param mesto mesto u kojem kupac zivi, ne sme biti null
     * @throws NullPointerException ako je mesto null
     */
    public void setMesto(Mesto mesto) {
    	if(mesto==null)
    		throw new NullPointerException("Mesto ne sme biti null.");
        this.mesto = mesto;
    }

    /**
     * Vraca tekstualnu reprezentaciju kupca koja sadrzi ID, ime i prezime.
     *
     * @return string sa ID-om, imenom i prezimenom kupca
     */
    @Override
    public String toString() {
        return  "id=" + idKupac + ", ime i prezime=" + ime + " " + prezime ;
    }

    /**
     * Vraca hash kod kupca racunat na osnovu broja telefona, imena i prezimena.
     *
     * @return hash kod kupca
     */
    @Override
	public int hashCode() {
		return Objects.hash(brojTelefona, ime, prezime);
	}



    /**
     * Poredi ovog kupca sa drugim objektom na osnovu broja telefona, imena i prezimena.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su kupci istog tipa i imaju isti brojTelefona, ime i prezime,
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
		Kupac other = (Kupac) obj;
		return Objects.equals(brojTelefona, other.brojTelefona) && Objects.equals(ime, other.ime)
				&& Objects.equals(prezime, other.prezime);
	}

	/**
     * Vraca naziv tabele "kupac" u bazi podataka.
     *
     * @return naziv tabele "kupac"
     */
	@Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
     List<OpstiDomenskiObjekat> kupci = new ArrayList<>();
     try{
        while(rs.next()){

            int idKupac = rs.getInt("kupac.idKupac");
            String ime = rs.getString("kupac.ime");
            String prezime = rs.getString("kupac.prezime");
            String brojTelefona = rs.getString("kupac.brojTelefona");
            int mestoId = rs.getInt("kupac.mesto");
            String naziv = rs.getString("mesto.nazivMesta");
            Mesto m = new Mesto(mestoId,naziv);
            
           
            
            Kupac k = new Kupac(idKupac,ime,prezime,brojTelefona,m);
            kupci.add(k);
            
        }
        
        return kupci;
     } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste kupaca: " + ex.getMessage());
            return null;
        }

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,brojTelefona,mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return "'"+ime+"','"+prezime+"','"+brojTelefona+"',"+mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupac="+idKupac;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
         Kupac k = null;
        try {
            while (rs.next()) {
            int idKupac = rs.getInt("kupac.idKupac");
            String ime = rs.getString("kupac.ime");
            String prezime = rs.getString("kupac.prezime");
            String brojTelefona = rs.getString("kupac.brojTelefona");
            int mestoId = rs.getInt("kupac.mesto");
            String naziv = rs.getString("mesto.nazivMesta");
            Mesto m = new Mesto(mestoId,naziv);
            
           
            
             k = new Kupac(idKupac,ime,prezime,brojTelefona,m);

            }

            return k;

        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja kupaca: " + ex.getMessage());
            return null;
        }
        
        
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', brojTelefona='"+brojTelefona+"', mesto="+mesto.getIdMesto();
    }
    
    
    
    
    
    
}
