package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;

/**
 * Predstavlja strucnu spremu (stepen obrazovanja) koju prodavac moze posedovati.
 *
 * @see PrSS
 * @see Prodavac
 */
public class StrSprema implements OpstiDomenskiObjekat{
	
    /** Jedinstveni identifikator strucne spreme u bazi podataka. */
    private int idStrucnaSprema;
    /** Stepen strucne spreme. */
    private String stepen;

    public StrSprema() {
    }

    /**
     * Konstruktor koji inicijalizuje stepen strucne spreme bez ID-a.
     * Koristi se prilikom kreiranja nove strucne spreme pre unosa u bazu podataka.
     *
     * @param stepen stepen strucne spreme
     */
       public StrSprema( String stepen) {
    
        this.stepen = stepen;
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute strucne spreme ukljucujuci i ID.
     *
     * @param idStrucnaSprema jedinstveni identifikator strucne spreme
     * @param stepen stepen strucne spreme
     */
    public StrSprema(int idStrucnaSprema, String stepen) {
        this.idStrucnaSprema = idStrucnaSprema;
        this.stepen = stepen;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    /**
     * Postavlja jedinstveni identifikator strucne spreme.
     *
     * @param idStrucnaSprema jedinstveni identifikator strucne spreme, mora biti veci od nule
     * @throws IllegalArgumentException ako je idStrucnaSprema manji ili jednak nuli
     */
    public void setIdStrucnaSprema(int idStrucnaSprema) {
    	
    	if(idStrucnaSprema<=0) {
    		throw new IllegalArgumentException("ID stručne spreme mora biti veći od nule.");
    	}
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public String getStepen() {
        return stepen;
    }

    /**
     * Postavlja stepen strucne spreme.
     *
     * @param stepen stepen strucne spreme, ne sme biti null, prazan ili duzi od 50 karaktera
     * @throws NullPointerException ako je stepen null
     * @throws IllegalArgumentException ako je stepen prazan ili duzi od 50 karaktera
     */
    public void setStepen(String stepen) {
    	
    	if(stepen==null) {
    		throw new NullPointerException("Stepen ne sme biti null.");
    	}
    	
    	if(stepen.length()>50) {
    		throw new IllegalArgumentException("Stepen ne sme imati više od 50 karaktera.");
    	}
    	
    	if (stepen.isBlank())
            throw new IllegalArgumentException("Stepen ne sme biti prazan.");
    	
        this.stepen = stepen;
    }

    /**
     * Vraca tekstualnu reprezentaciju strucne spreme koja sadrzi njen stepen.
     *
     * @return stepen strucne spreme
     */
    @Override
    public String toString() {
        return stepen ;
    }

    /**
     * Vraca hash kod strucne spreme racunat na osnovu stepena.
     *
     * @return hash kod strucne spreme
     */
    @Override
	public int hashCode() {
		return Objects.hash(stepen);
	}
    
    /**
     * Poredi ovu strucnu spremu sa drugim objektom na osnovu stepena.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su strucne spreme istog tipa i imaju isti stepen, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se stepen razlikuje
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrSprema other = (StrSprema) obj;
		return Objects.equals(stepen, other.stepen);
	}
	
	/**
     * Vraca naziv tabele "str_sprema" u bazi podataka.
     *
     * @return naziv tabele "str_sprema"
     */
	@Override
    public String vratiNazivTabele() {
        return "str_sprema";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
            int idStrucnaSprema = rs.getInt("str_sprema.idStrucnaSprema");
            String stepen = rs.getString("str_sprema.stepen");
            
            StrSprema ss = new StrSprema(idStrucnaSprema,stepen);
            lista.add(ss);
            
        }
        
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "stepen";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+stepen+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "str_sprema.idStrucnaSprema="+idStrucnaSprema;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
           StrSprema strSprema = null;
        try {
            while (rs.next()) {

                int id = rs.getInt("str_sprema.idStrucnaSprema");
                String stepen = rs.getString("str_sprema.stepen");
                strSprema= new StrSprema(id, stepen);
            }
            return strSprema;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja strucne spreme: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
       return "stepen='"+stepen+"'";
    }
    
    
    
}
