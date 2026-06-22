/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author Ljilja
 */
public interface Repozitorijum <T> {
    
    List<T> vratiSve (T param, String uslov) throws Exception;
    List<T> vratiSve(T parametar) throws Exception;
    void dodaj(T param) throws Exception;
    void promeni(T param) throws Exception;
    void obrisi(T param) throws Exception;
    T vratiObjekat(T parametar, String uslov) throws Exception;
    T vratiObjekat(T parametar) throws Exception;
    
}
