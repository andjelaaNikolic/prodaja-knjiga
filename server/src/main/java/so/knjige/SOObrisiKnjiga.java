/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import db.repozitorijum.DBKonekcija;
import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Sistemska operacija za brisanje knjige.
 * Pre brisanja proverava da li je knjiga vec koriscena u nekoj stavci racuna,
 * jer u tom slucaju brisanje nije dozvoljeno.
 *
 * @author Andjela
 * @see Knjiga
 */
public class SOObrisiKnjiga extends OpsteSistemskeOperacije {
    
    /**  Vrednost koja oznacava da li je knjiga vec koriscena u nekoj stavci racuna. */
    private boolean koristiSe=false;
    /**  Vrednost koja oznacava uspesnost brisanja knjige. */
    private boolean uspesno=false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li je
     * knjiga koja se brise vec koriscena u nekoj stavci racuna.
     *
     * @param param objekat tipa {@link Knjiga} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Knjiga)){
            throw new Exception("Sistem ne moze da obrise knjigu");
        }
        
        try {

            String upit = "SELECT * FROM stavka_racuna sr JOIN knjiga k ON sr.knjiga = k.idKnjiga WHERE k.idKnjiga = " + ((Knjiga) param).getIdKnjiga();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                koristiSe = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Izvrsava brisanje knjige iz baze podataka. Knjiga se brise samo
     * ukoliko nije koriscena ni u jednoj stavci racuna.
     *
     * @param param objekat tipa {@link Knjiga} koji se brise
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(koristiSe!=true){
        broker.obrisi((Knjiga)param);
        uspesno=true;
        }
    }

    /**
     * Vraca true ako je knjiga uspesno obrisana i false ako nije.
     *
     * @return true ako je knjiga uspesno obrisana, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}
