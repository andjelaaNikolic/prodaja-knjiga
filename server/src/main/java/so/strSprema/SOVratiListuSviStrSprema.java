/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strSprema;

import java.util.List;
import model.StrSprema;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za dobavljanje liste svih strucnih sprema.
 *
 * @author Andjela
 * @see StrSprema
 */
public class SOVratiListuSviStrSprema extends OpsteSistemskeOperacije {

    /** Lista svih strucnih sprema. */
    private List<StrSprema> strSpreme;
    
    /**
     * Ova operacija nema dodatnih preduslova.
     *
     * @param param nije koriscen u ovoj operaciji
     * @throws Exception nikada se ne baca u ovoj implementaciji
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
       
    }

    /**
     * Izvrsava dobavljanje liste svih strucnih sprema iz baze podataka.
     *
     * @param param objekat tipa {@link StrSprema}
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        strSpreme = broker.vratiSve( (StrSprema) param, "");
        this.strSpreme = strSpreme;
    }

    /**
     * Vraca listu svih strucnih sprema.
     *
     * @return lista svih strucnih sprema
     */
    public List<StrSprema> getStrSpreme() {
        return strSpreme;
    }
    
    
    
}

