

package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;


public class StrSprema implements OpstiDomenskiObjekat{
	
    private int idStrucnaSprema;
    private String stepen;

    public StrSprema() {
    }
       public StrSprema( String stepen) {
    
        this.stepen = stepen;
    }

    public StrSprema(int idStrucnaSprema, String stepen) {
        this.idStrucnaSprema = idStrucnaSprema;
        this.stepen = stepen;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(int idStrucnaSprema) {
    	
    	if(idStrucnaSprema<=0) {
    		throw new IllegalArgumentException("ID stručne spreme mora biti veći od nule.");
    	}
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public String getStepen() {
        return stepen;
    }

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

    @Override
    public String toString() {
        return stepen ;
    }

    @Override
	public int hashCode() {
		return Objects.hash(stepen);
	}
    
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
