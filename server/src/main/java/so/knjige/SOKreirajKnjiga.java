/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import db.repozitorijum.DBKonekcija;
import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Ljilja
 */
public class SOKreirajKnjiga extends OpsteSistemskeOperacije {
    
    private boolean uspesno=false;
    private boolean postoji=false;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Knjiga)){
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Knjiga knjiga = (Knjiga) param;
        if(knjiga.getNaslov()==null || knjiga.getNaslov().isEmpty()){
            throw new Exception("Naslov je neispravan");
        }
        if(knjiga.getZanr()==null || knjiga.getZanr().isEmpty()){
            throw new Exception("Zanr je neispravan"); 
        }
        if(knjiga.getGodinaIzdanja()<2017){
            throw new Exception("Godina izdanja mora da bude 2017. ili veca");
        }
        if(knjiga.getCena()<=200){
            throw new Exception("Cena mora da bude veca od 200");
        }
        if(knjiga.getKolicina()<=0){
           throw new Exception("Dostupna kolicina mora da bude veca od 0");
        }
        if(knjiga.getMagacin()==null){
            throw new Exception("Magacin je neispravan");

        }
        try {

            String upit = "SELECT * FROM knjiga WHERE naslov='" + ((Knjiga) param).getNaslov()+"'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
       broker.dodaj((Knjiga)param);
       uspesno=true;
        }
        
    }

    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
}
