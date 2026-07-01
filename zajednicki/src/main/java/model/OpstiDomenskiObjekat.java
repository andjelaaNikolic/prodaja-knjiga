/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 * Predstavlja modele sistema.
 * Definise zajednicke operacije koje svaki domenski objekat mora da implementira
 * da bi mogao da se cuva, ucitava i obradjuje preko genericke baze podataka.
 *
 * @author Andjela
 */
public interface OpstiDomenskiObjekat extends Serializable {
    
    /**
     * Vraca naziv tabele iz baze podataka.
     * Za domenske objekte koji se cuvaju u vise tabela,
     * ova metoda moze vratiti i JOIN izraz koji spaja odgovarajuce tabele.
     *
     * @return naziv tabele ili JOIN izraz iz koje se ucitavaju podaci
     */
    public String vratiNazivTabele();
    
    /**
     * Vraca listu svih objekata kreiranih na osnovu podataka iz ResultSet-a.
     * Iterira kroz sve redove ResultSet-a i za svaki red kreira odgovarajuci objekat.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista domenskih objekata kreiranih iz ResultSet-a
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja objekta u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    public String vratiKoloneZaUbacivanje();
    
    /**
     * Vraca vrednosti atributa objekta koje se upisuju u bazu podataka..
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    public String vratiVrednostiZaUbacivanje();
    
    /**
     * Vraca uslov koji se koristi za identifikaciju objekta u bazi podataka
     * preko njegovog primarnog kljuca.
     *
     * @return string sa uslovom na osnovu primarnog kljuca objekta
     */
    public String vratiPrimarniKljuc();
    
    /**
     * Vraca jedan objekat kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return domenski objekat kreiran iz ResultSet-a, ili null ako ResultSet ne sadrzi podatke
     * @throws Exception ako dodje do greske pri citanju podataka iz ResultSet-a
     */
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    /**
     * Vraca string sa parovima kolona i vrednosti atributa objekta
     * koji se koristi prilikom azuriranja podataka u bazi.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    public String vratiVrednostiZaIzmenu();
 
}

