/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.magacini;

import db.repozitorijum.DBKonekcija;
import model.Magacin;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Sistemska operacija za kreiranje novog magacina.
 * Pre kreiranja proverava da li su naziv i adresa magacina validni, i da li
 * magacin sa istim nazivom vec postoji u bazi podataka.
 *
 * @author Andjela
 * @see Magacin
 */
public class SOUbaciMagacin extends OpsteSistemskeOperacije {

    /** Indikator da li magacin sa istim nazivom vec postoji u bazi podataka. */
    private boolean postoji=false;
    
    /** Indikator uspesnosti kreiranja magacina. */
    private boolean uspesno=false;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li su
     * naziv i adresa magacina validni, i da li magacin sa istim nazivom vec
     * postoji u bazi podataka.
     *
     * @param param objekat tipa {@link Magacin} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako naziv
     *         ili adresa magacina nisu validni
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Magacin)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        Magacin magacin = (Magacin) param;
        if(magacin.getNaziv()==null || magacin.getNaziv().isEmpty()){
            throw new Exception("Naziv magacina je neispravan");
        }
        if(magacin.getAdresa()==null || magacin.getAdresa().isEmpty()){
            throw new Exception("Adresa magacina je neispravna");
        }
        
          try {

            String upit = "SELECT * FROM magacin WHERE naziv='" + ((Magacin) param).getNaziv()+"'";
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
     * Izvrsava kreiranje magacina u bazi podataka. Magacin se upisuje samo
     * ukoliko magacin sa istim nazivom jos uvek ne postoji u bazi.
     *
     * @param param objekat tipa {@link Magacin} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        if(postoji!=true){
        broker.dodaj((Magacin)param);
        uspesno=true;
        }
    }

    /**
     * Vraca indikator uspesnosti kreiranja magacina.
     *
     * @return true ako je magacin uspesno kreiran, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
}

