/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.magacini;

import java.util.List;
import model.Magacin;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Andjela
 */
public class SOVratiListuSviMagacini extends OpsteSistemskeOperacije{
    
        private List<Magacin> magacini;


    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object uslov) throws Exception {
        magacini = broker.vratiSve( (Magacin)param,"");
        this.magacini = magacini;
    }

    public List<Magacin> getMagacini() {
        return magacini;
    }
    
    
}
