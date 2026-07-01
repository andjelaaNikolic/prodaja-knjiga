/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije;

import db.repozitorijum.DBRepozitorijum;
import repozitorijum.Repozitorijum;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

/**
 * Predstavlja apstraktnu sistemsku operaciju koja definise zajednicki tok
 * izvrsavanja za sve konkretne sistemske operacije (SO) u sistemu.
 * Tok izvrsavanja se sastoji od provere preduslova, otvaranja transakcije,
 * izvrsavanja konkretne logike operacije i potvrdjivanja transakcije. Ukoliko
 * dodje do greske u bilo kojem koraku, transakcija se ponistava i prosledjuje se izuzetak.
 *
 * @author Andjela
 * @see Repozitorijum
 * @see DBRepozitorijumGenericki
 */
public abstract class OpsteSistemskeOperacije {
    
    /** Broker preko kojeg se vrsi komunikacija sa bazom podataka. */
    protected final Repozitorijum broker;

    /**
     * Konstruktor koji inicijalizuje brokera kao novu instancu
     * {@link DBRepozitorijumGenericki}.
     */
    public OpsteSistemskeOperacije() {
        this.broker = new DBRepozitorijumGenericki();
    }
    
    /**
     * Izvrsava kompletan tok sistemske operacije: provera preduslova,
     * otvaranje transakcije, izvrsavanje konkretne logike i potvrdjivanje
     * transakcije. Ukoliko dodje do greske u bilo kojem koraku, transakcija
     * se ponistava i izuzetak se prosledjuje dalje.
     *
     * @param objekat objekat nad kojim se izvrsava operacija
     * @param uslov dodatni parametar koji koristi konkretna operacija, ili null
     *        ako nije potreban
     * @throws Exception ako preduslovi nisu ispunjeni, ako dodje do greske
     *         prilikom rada sa bazom podataka, ili ako izvrsavanje operacije
     *         baci izuzetak
     */
    public final void izvrsi(Object objekat, Object uslov) throws Exception{
        
        try{
        preduslovi(objekat);
        zapocniTransakciju();
        izvrsiOperaciju(objekat,uslov);
        potvrdiTransakciju();}
        catch(Exception e){
            ponistiTransakciju();
            throw e;
        }
        
        
    }

    /**
     * Proverava da li su ispunjeni preduslovi za izvrsavanje konkretne
     * sistemske operacije. Implementira se u svakoj konkretnoj operaciji
     * u zavisnosti od specificnih pravila te operacije.
     *
     * @param param objekat nad kojim se proveravaju preduslovi
     * @throws Exception ako preduslovi nisu ispunjeni
     */
    protected abstract void preduslovi(Object param) throws Exception; 

    /**
     * Izvrsava konkretnu logiku sistemske operacije. Implementira se u svakoj
     * konkretnoj operaciji u zavisnosti od njene poslovne logike.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param uslov dodatni parametar koji koristi konkretna operacija, ili null
     *        ako nije potreban
     * @throws Exception ako dodje do greske prilikom izvrsavanja operacije
     */
    protected  abstract void izvrsiOperaciju(Object param, Object uslov) throws Exception;
    
    /**
     * Otvara transakciju nad bazom podataka preko brokera.
     *
     * @throws Exception ako dodje do greske prilikom otvaranja konekcije
     */
    private void zapocniTransakciju() throws Exception{
        ((DBRepozitorijum) broker).povezi();
    }

    /**
     * Potvrdjuje (komituje) transakciju nad bazom podataka preko brokera.
     *
     * @throws Exception ako dodje do greske prilikom potvrdjivanja transakcije
     */
    private void potvrdiTransakciju() throws Exception{
        ((DBRepozitorijum)broker).commit();
    }

    /**
     * Ponistava (rollback) transakciju nad bazom podataka preko brokera.
     * Poziva se kada dodje do greske u toku izvrsavanja sistemske operacije.
     *
     * @throws Exception ako dodje do greske prilikom ponistavanja transakcije
     */
    private void ponistiTransakciju()throws Exception {
        ((DBRepozitorijum)broker).rollback();
    }

    /**
     * Zatvara konekciju sa bazom podataka preko brokera. Trenutno se ne
     * poziva u toku izvrsavanja operacije.
     *
     * @throws Exception ako dodje do greske prilikom zatvaranja konekcije
     */
    private void ugasiKonekciju()throws Exception {
       ((DBRepozitorijum)broker).prekiniVezu();
    }
    
}