/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import komunikacija.Operacije;

/**
 *
 * @author Ljilja
 */
public class Zahtev implements Serializable {
    
    private Object parametri;
    private Operacije operacije;

    public Zahtev() {
    }

    public Zahtev(Object parametri, Operacije operacije) {
        this.parametri = parametri;
        this.operacije = operacije;
    }

    public Object getParametri() {
        return parametri;
    }

    public void setParametri(Object parametri) {
        this.parametri = parametri;
    }

    public Operacije getOperacije() {
        return operacije;
    }

    public void setOperacije(Operacije operacije) {
        this.operacije = operacije;
    }
    
    
    
    
}
