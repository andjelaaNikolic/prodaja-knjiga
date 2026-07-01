package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;

/**
 * Predstavlja prodavca koji obradjuje racune kupaca.
 * Sadrzi podatke za prijavu prodavca (korisnicko ime i sifra) i listu
 * strucnih sprema koje prodavac poseduje.
 * 
 *@author Andjela
 * @see PrSS
 * @see StrSprema
 * @see Racun
 */
public class Prodavac implements OpstiDomenskiObjekat{
    /** Jedinstveni identifikator prodavca u bazi podataka. */
    private int idProdavac;
    /** Ime prodavca. */
    private String ime;
    /** Prezime prodavca. */
    private String prezime;
    /** Korisnicko ime prodavca koje se koristi za prijavu. */
    private String korisnickoIme;
    /** Sifra prodavca koja se koristi za prijavu. */
    private String sifra;
    /** Email prodavca. */
    private String email;
    /** Lista strucnih sprema koje prodavac poseduje. */
    private List<PrSS> prss;

    public Prodavac() {
    }
    
 
    /**
     * Konstruktor koji inicijalizuje sve atribute prodavca ukljucujuci i ID.
     * Lista strucnih sprema se inicijalizuje kao prazna lista.
     *
     * @param idProdavac jedinstveni identifikator prodavca
     * @param ime ime prodavca
     * @param prezime prezime prodavca
     * @param korisnickoIme korisnicko ime za prijavu
     * @param sifra sifra za prijavu
     * @param email email prodavca
     */
    public Prodavac(int idProdavac, String ime, String prezime, String korisnickoIme, String sifra, String email) {
    	setIdProdavac(idProdavac);
    	setIme(ime);
    	setPrezime(prezime);
    	setKorisnickoIme(korisnickoIme);
    	setSifra(sifra);
    	setEmail(email);
        this.prss=new ArrayList<>();
    }

    /**
     * Konstruktor koji inicijalizuje atribute prodavca bez ID-a.
     * Koristi se prilikom kreiranja novog prodavca pre unosa u bazu podataka.
     * Lista strucnih sprema se inicijalizuje kao prazna lista.
     *
     * @param ime ime prodavca
     * @param prezime prezime prodavca
     * @param korisnickoIme korisnicko ime za prijavu
     * @param sifra sifra za prijavu
     * @param email email prodavca
     */
    public Prodavac(String ime, String prezime, String korisnickoIme, String sifra, String email) {
    	setIme(ime);
    	setPrezime(prezime);
    	setKorisnickoIme(korisnickoIme);
    	setSifra(sifra);
    	setEmail(email);
        this.prss=new ArrayList<>();
    }

    public List<PrSS> getPrss() {
        return prss;
    }

    /**
     * Postavlja listu strucnih sprema prodavca.
     *
     * @param prss lista strucnih sprema, ne sme biti null
     * @throws NullPointerException ako je prss null
     */
    public void setPrss(List<PrSS> prss) {
    	if(prss==null)
    		throw new NullPointerException("Lista stručnih sprema ne sme biti null.");
        this.prss = prss;
    }


    public int getIdProdavac() {
        return idProdavac;
    }

    /**
     * Postavlja jedinstveni identifikator prodavca.
     *
     * @param idProdavac jedinstveni identifikator prodavca, mora biti veci od nule
     * @throws IllegalArgumentException ako je idProdavac manji ili jednak nuli
     */
    public void setIdProdavac(int idProdavac) {
    	 if (idProdavac <= 0)
             throw new IllegalArgumentException("ID prodavca mora biti veći od nule.");
        this.idProdavac = idProdavac;
    }

    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime prodavca.
     *
     * @param ime ime prodavca, ne sme biti null, prazno, duze od 50 karaktera
     *            ili sadrzati znakove koji nisu slova
     * @throws NullPointerException ako je ime null
     * @throws IllegalArgumentException ako je ime prazno, duze od 50 karaktera
     *                                   ili sadrzi znakove koji nisu slova
     */
    public void setIme(String ime) {
    	if(ime==null) 
    		throw new NullPointerException("Ime ne sme biti null.");
    	
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
     * Postavlja prezime prodavca.
     *
     * @param prezime prezime prodavca, ne sme biti null, prazno, duze od 50 karaktera
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisnicko ime prodavca koje se koristi za prijavu.
     *
     * @param korisnickoIme korisnicko ime, ne sme biti null, prazno ili duze od 50 karaktera
     * @throws NullPointerException ako je korisnickoIme null
     * @throws IllegalArgumentException ako je korisnickoIme prazno ili duze od 50 karaktera
     */
    public void setKorisnickoIme(String korisnickoIme) {
    	if(korisnickoIme==null) 
    		throw new NullPointerException("Korisničko ime ne sme biti null.");
    	
    	if(korisnickoIme.length()>50) 
    		throw new IllegalArgumentException("Korisničko ime ne sme imati više od 50 karaktera.");
    	
    	if (korisnickoIme.isBlank())
            throw new IllegalArgumentException("Korisničko ime ne sme biti prazno.");
    	
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

	/**
     * Postavlja sifru prodavca koja se koristi za prijavu.
     *
     * @param sifra sifra, ne sme biti null, prazna ili duza od 50 karaktera
     * @throws NullPointerException ako je sifra null
     * @throws IllegalArgumentException ako je sifra prazna ili duza od 50 karaktera
     */
	public void setSifra(String sifra) {

		if (sifra == null)
			throw new NullPointerException("Šifra ne sme biti null.");
		if (sifra.isBlank())
			throw new IllegalArgumentException("Šifra ne sme biti prazna.");
		if (sifra.length() > 50)
			throw new IllegalArgumentException("Šifra ne sme imati više od 50 karaktera.");
		this.sifra = sifra;
	}

    public String getEmail() {
        return email;
    }

	/**
     * Postavlja email prodavca.
     *
     * @param email email, ne sme biti null, prazan, duzi od 50 karaktera
     *              ili u neispravnom formatu
     * @throws NullPointerException ako je email null
     * @throws IllegalArgumentException ako je email prazan, duzi od 50 karaktera
     *                                   ili nije u ispravnom formatu
     */
	public void setEmail(String email) {
		if (email == null)
			throw new NullPointerException("Email ne sme biti null.");
		if (email.isBlank())
			throw new IllegalArgumentException("Email ne sme biti prazan.");
		if (email.length() > 50)
			throw new IllegalArgumentException("Email ne sme imati više od 50 karaktera.");
		if (!email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
			throw new IllegalArgumentException("Email nije u ispravnom formatu.");
		this.email = email;
	}

    /**
     * Vraca tekstualnu reprezentaciju prodavca koja sadrzi korisnicko ime.
     *
     * @return korisnicko ime prodavca
     */
    @Override
    public String toString() {
        return  korisnickoIme;
    }


    /**
     * Poredi ovog prodavca sa drugim objektom na osnovu korisnickog imena.
     * Ovo je specificno za prodavca jer se prijava u sistem vrsi
     * preko korisnickog imena.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su prodavci istog tipa i imaju isto korisnickoIme, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se korisnickoIme razlikuje
     */
    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Prodavac p = (Prodavac) obj;
    return this.korisnickoIme != null && this.korisnickoIme.equals(p.korisnickoIme);
}

    /**
     * Vraca hash kod prodavca racunat na osnovu korisnickog imena,
     * u skladu sa atributom koji koristi {@link #equals(Object)}.
     *
     * @return hash kod prodavca
     */
    @Override
    public int hashCode() {
    return korisnickoIme != null ? korisnickoIme.hashCode() : 0;
    }

    /**
     * Vraca naziv tabele "prodavac" u bazi podataka.
     *
     * @return naziv tabele "prodavac"
     */
    @Override
    public String vratiNazivTabele() {
        return "prodavac";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try{ 
        while(rs.next()){

            int idProdavac = rs.getInt("prodavac.idProdavac");
            String ime = rs.getString("prodavac.ime");
            String prezime = rs.getString("prodavac.prezime");
            String korisnickoIme = rs.getString("prodavac.korisnickoIme");
            String sifra = rs.getString("prodavac.sifra");
            String email = rs.getString("prodavac.email");
           
            
            Prodavac p = new Prodavac(idProdavac,ime,prezime,korisnickoIme,sifra,email);
            lista.add(p);
            
        }
        
        return lista;
        }catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste prodavac: " + ex.getMessage());
            return null;
        }
    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra,email";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
         return "'"+ime+"','"+prezime+"','"+korisnickoIme+"','"+sifra+"','"+email+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavac.idProdavac="+idProdavac;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
      try {
            Prodavac p = null;
            while (rs.next()) {
            
            int idProdavac = rs.getInt("prodavac.idProdavac");
            String imeP = rs.getString("prodavac.ime");
            String prezimeP = rs.getString("prodavac.prezime");
            String korisnickoImeP = rs.getString("prodavac.korisnickoIme");
            String sifraP = rs.getString("prodavac.sifra");
            String emailP = rs.getString("prodavac.email");
           
            p = new Prodavac(idProdavac, imeP, prezimeP, korisnickoImeP, sifraP, emailP);

            }

            return p;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja prodavca: " + ex.getMessage());
            return null;
        }
      
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', korisnickoIme='"+korisnickoIme+"', sifra='"+sifra+"', email='"+email+"'";
    }
    
    
    
    
}

