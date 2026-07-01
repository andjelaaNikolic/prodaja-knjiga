/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strSprema;

import java.util.List;
import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu liste strucnih sprema na osnovu stepena.
 *
 * @author Andjela
 * @see StrSprema
 */
public class SOVratiListuStrSprema extends OpsteSistemskeOperacije {

    /** Lista strucnih sprema koje odgovaraju uslovu pretrage. */
    private List<StrSprema> strSpreme;
    
    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link StrSprema}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof StrSprema)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu liste strucnih sprema ciji stepen sadrzi prosledjeni
     * kljuc kao podstring.
     *
     * @param param objekat tipa {@link StrSprema}
     * @param kljuc deo stepena po kojem se strucne spreme pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String stepen = ((String) kljuc).trim();
        String upit = "";
      
//WHERE stepen LIKE '%" + stepen + "%'
        upit += " WHERE stepen LIKE '%" + stepen + "%'";

    
        strSpreme = broker.vratiSve((StrSprema) param, upit);
        this.strSpreme = strSpreme;
        
        
        
        
        
    }

    /**
     * Vraca listu strucnih sprema pronadjenih pretragom.
     *
     * @return lista strucnih sprema koje odgovaraju uslovu pretrage
     */
    public List<StrSprema> getStrSpreme() {
        return strSpreme;
    }
    
    
}

