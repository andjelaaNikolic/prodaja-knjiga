package so.kupac;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import db.repozitorijum.DBKonekcija;
import model.Kupac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sistemska operacija za kreiranje novog kupca.
 * Pre kreiranja proverava da li kupac sa istim brojem telefona vec postoji
 * u bazi podataka.
 *
 * @author Andjela
 * @see Kupac
 */
public class SOKreirajKupac extends OpsteSistemskeOperacije {
	
    /** Indikator da li kupac sa istim brojem telefona vec postoji u bazi podataka. */
    private boolean postoji=false;
    
    /** Indikator uspesnosti kreiranja kupca. */
    private boolean uspesno = false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa i da li
     * kupac sa istim brojem telefona vec postoji u bazi podataka.
     *
     * @param param objekat tipa {@link Kupac} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Kupac)){
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        
         try{
            String upit = "SELECT * FROM kupac WHERE brojTelefona=" + ((Kupac) param).getBrojTelefona();
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
     * Izvrsava kreiranje kupca u bazi podataka. Kupac se upisuje samo
     * ukoliko kupac sa istim brojem telefona jos uvek ne postoji u bazi.
     *
     * @param param objekat tipa {@link Kupac} koji se kreira
     * @param uslov nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object uslov) throws Exception {
        if(postoji!=true){
        broker.dodaj((Kupac)param);
        uspesno =true;
        }
    }

    /**
     * Vraca indikator uspesnosti kreiranja kupca.
     *
     * @return true ako je kupac uspesno kreiran, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
    
}
