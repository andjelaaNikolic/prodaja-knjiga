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


public class Mesto implements OpstiDomenskiObjekat{
    
    private int idMesto;
    private String nazivMesta;

    public Mesto() {
    }
    
     public Mesto( String nazivMesta) {
        
        this.nazivMesta = nazivMesta;
    }

    public Mesto(int idMesto, String nazivMesta) {
        this.idMesto = idMesto;
        this.nazivMesta = nazivMesta;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    @Override
    public String toString() {
        return nazivMesta;
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
        final Mesto other = (Mesto) obj;
        if (this.idMesto != other.idMesto) {
            return false;
        }
        return Objects.equals(this.nazivMesta, other.nazivMesta);
    }

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
