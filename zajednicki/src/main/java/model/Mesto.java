/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;

/**
 * Predstavlja mesto koje se koristi za odredjivanje prebivalista kupca.
 * 
 *@author Andjela
 * @see Kupac
 */
public class Mesto implements OpstiDomenskiObjekat{
    
    /** Jedinstveni identifikator mesta u bazi podataka. */
    private int idMesto;
    /** Naziv mesta. */
    private String nazivMesta;

    public Mesto() {
    }
    
    /**
     * Konstruktor koji inicijalizuje naziv mesta bez ID-a.
     * Koristi se prilikom kreiranja novog mesta pre unosa u bazu podataka.
     *
     * @param nazivMesta naziv mesta
     */
     public Mesto( String nazivMesta) {
        
        setNazivMesta(nazivMesta);
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute mesta ukljucujuci i ID.
     *
     * @param idMesto jedinstveni identifikator mesta
     * @param nazivMesta naziv mesta
     */
    public Mesto(int idMesto, String nazivMesta) {
    	setIdMesto(idMesto);
        setNazivMesta(nazivMesta);
    }

    public int getIdMesto() {
        return idMesto;
    }

    /**
     * Postavlja jedinstveni identifikator mesta.
     *
     * @param idMesto jedinstveni identifikator mesta, mora biti veci od nule
     * @throws IllegalArgumentException ako je idMesto manji ili jednak nuli
     */
    public void setIdMesto(int idMesto) {
 
    	if (idMesto <= 0) {
            throw new IllegalArgumentException("ID mesta mora biti veći od nule.");
        }
    	
        this.idMesto = idMesto;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    /**
     * Postavlja naziv mesta.
     *
     * @param nazivMesta naziv mesta, ne sme biti null, prazan, sadrzati vise od 50
     *                   karaktera ili sadrzati znakove koji nisu slova
     * @throws NullPointerException ako je nazivMesta null
     * @throws IllegalArgumentException ako je nazivMesta prazan, duzi od 50 karaktera
     *                                   ili sadrzi znakove koji nisu slova
     */
    public void setNazivMesta(String nazivMesta) {
    	
       	if (nazivMesta == null)
    			throw new NullPointerException("Naziv mesta ne sme biti null.");
    			
       	if (nazivMesta.length() > 50)
    			throw new IllegalArgumentException("Naziv mesta ne sme imati više od 50 karaktera.");
       	
        if (!nazivMesta.matches("[a-zA-ZčćšđžČĆŠĐŽ ]+"))
            throw new IllegalArgumentException("Naziv mesta sme sadržati samo slova.");
       	
       	if(nazivMesta.isBlank()) {
       		throw new IllegalArgumentException("Naziv mesta ne sme biti prazan.");
       	}
    		
        this.nazivMesta = nazivMesta;
    }

    /**
     * Vraca tekstualnu reprezentaciju mesta koja sadrzi naziv mesta.
     *
     * @return naziv mesta
     */
    @Override
    public String toString() {
        return nazivMesta;
    }

    /**
     * Vraca hash kod mesta racunat na osnovu naziva mesta.
     *
     * @return hash kod mesta
     */
    @Override
	public int hashCode() {
		return Objects.hash(nazivMesta);
	}


    /**
     * Poredi ovo mesto sa drugim objektom na osnovu naziva mesta.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su mesta istog tipa i imaju isti nazivMesta, false ako je
     *         obj null, ako je obj drugog tipa, ili ako se nazivMesta razlikuju
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesto other = (Mesto) obj;
		return Objects.equals(nazivMesta, other.nazivMesta);
	}

	/**
     * Vraca naziv tabele "mesto" u bazi podataka.
     *
     * @return naziv tabele "mesto"
     */
	@Override
    public String vratiNazivTabele() {
       return "mesto";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){

            int idMesta = rs.getInt("mesto.idMesto");
            String naziv = rs.getString("mesto.nazivMesta");
           
            Mesto m = new Mesto(idMesta,naziv);
            lista.add(m);
            
        }
        
        return lista;
    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivMesta";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivMesta+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
       return "mesto.idMesto="+idMesto;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Mesto mesto = null;
        try {
            while (rs.next()) {

                int idMesto = rs.getInt("mesto.idMesto");
                String naziv = rs.getString("mesto.nazivMesta");
                mesto = new Mesto(idMesto, naziv);
            }
            return mesto;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja mesta: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
       return "nazivMesta='"+nazivMesta+"'";
    }
    
    
}

