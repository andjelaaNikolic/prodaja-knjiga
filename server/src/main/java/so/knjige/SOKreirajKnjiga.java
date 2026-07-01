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
 * Sistemska operacija za kreiranje nove knjige.
 * Pre kreiranja proverava da li su vrednosti atributa knjige validne i da li
 * knjiga sa istim naslovom vec postoji u bazi podataka.
 *
 * @author Andjela
 * @see Knjiga
 */
public class SOKreirajKnjiga extends OpsteSistemskeOperacije {
    
    /** Vrednost koja oznacava uspesnost kreiranja knjige. */
    private boolean uspesno=false;
    
    /** Vrednost koja oznacava da li knjiga sa istim naslovom vec postoji u bazi podataka. */
    private boolean postoji=false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li su
     * vrednosti atributa knjige validne (naslov, zanr, godina izdanja, cena,
     * kolicina i magacin), i da li knjiga sa istim naslovom vec postoji u
     * bazi podataka.
     *
     * @param param objekat tipa {@link Knjiga} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         vrednosti atributa nisu validne
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
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
        if(knjiga.getGodinaIzdanja()<1900){
            throw new Exception("Godina izdanja mora da bude 1900. ili veca");
        }
        if(knjiga.getCena()<=0){
            throw new Exception("Cena mora da bude veca od 0");
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

    /**
     * Izvrsava kreiranje knjige u bazi podataka. Knjiga se upisuje samo
     * ukoliko knjiga sa istim naslovom jos uvek ne postoji u bazi.
     *
     * @param param objekat tipa {@link Knjiga} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
       broker.dodaj((Knjiga)param);
       uspesno=true;
        }
        
    }

    /**
     * Vraca true ako je knjiga uspesno kreirana i false ako nije.
     *
     * @return true ako je knjiga uspesno kreirana, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
}

