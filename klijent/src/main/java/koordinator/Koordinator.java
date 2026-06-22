/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import controller.GlavnaFormaController;
import controller.KreirajKnjiguController;
import controller.KreirajKupacController;
import controller.KreirajMestoController;
import controller.KreirajProdavcaController;
import controller.KreirajRacunController;
import controller.PrijaviProdavcaController;
import controller.PretraziKnjigeController;
import controller.PretraziKupcaController;
import controller.PretraziMestoController;
import controller.PretraziProdavcaController;
import controller.PretraziRacunController;
import controller.PretraziStrSpremaController;
import controller.UbaciStrSpremuController;
import forme.GlavnaForma;
import forme.KreirajKnjiguForma;
import forme.KreirajKupcaForma;
import forme.KreirajMestoForma;
import forme.KreirajProdavcaForma;
import forme.KreirajRacunForma;
import forme.PretraziKnjigeForma;
import forme.PretraziKupcaForma;
import forme.PretraziMestoForma;
import forme.PretraziProdavcaForma;
import forme.PretraziRacunForma;
import forme.PretraziStrSpremaForma;
import forme.PrijaviProdavcaForma;
import forme.UbaciStrSpremuForma;
import java.util.List;
import model.Knjiga;
import model.Kupac;
import model.Mesto;
import model.Prodavac;
import model.Racun;
import model.StrSprema;

/**
 *
 * @author Ljilja
 */
public class Koordinator {
    private static Koordinator instance;
    private Prodavac ulogovaniProdavac;
    private PrijaviProdavcaController ppController;
    private PretraziProdavcaController pretrazipController;
    private GlavnaFormaController gfController;
    private PretraziKnjigeController pkController;
    private KreirajKnjiguController kkController;
    private KreirajKupacController kkupcaController;
    private KreirajMestoController kmController;
    private PretraziMestoController pm;
    private PretraziStrSpremaController pssController;
    private PretraziKupcaController pretraziKupcaController;
    private UbaciStrSpremuController ssController;
    private KreirajProdavcaController kreirajPController;
    private KreirajRacunController kreirajRController;
    private PretraziRacunController prController;
    
    private Koordinator(){
        
    }
    public static Koordinator getInstance(){
        if(instance==null){
            instance=new Koordinator();
        }
        return instance;
    }

    public void otvoriPrijaviProdavcaFormu() {
        ppController = new PrijaviProdavcaController(new PrijaviProdavcaForma());
        ppController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
       
        gfController = new GlavnaFormaController(new GlavnaForma());
        gfController.otvoriFormu();
        
    }

    public Prodavac getUlogovaniProdavac() {
        return ulogovaniProdavac;
    }

    public void setUlogovaniProdavac(Prodavac ulogovaniProdavac) {
        this.ulogovaniProdavac = ulogovaniProdavac;
    }

    public void otvoriPretraziKnjigeFormu() {
        pkController = new PretraziKnjigeController(new PretraziKnjigeForma());
        pkController.otvoriFormu();
    }

    public void otvoriKreirajKnjiguFormu() {
        kkController = new KreirajKnjiguController(new KreirajKnjiguForma());
        kkController.otvoriFormu();
    }

    public void otvoriPretraziProdavcaFormu() {
        pretrazipController = new PretraziProdavcaController(new PretraziProdavcaForma());
        pretrazipController.otvoriFormu();
    }


    public void otvoriKreirajMestoFormu() {
        kmController = new KreirajMestoController(new KreirajMestoForma());
        kmController.otvoriFormu();
    }

    public void otvoriPretraziMestoFormu() {
        pm = new PretraziMestoController(new PretraziMestoForma());
        pm.otvoriFormu();
       
    }

    public void otvoriKreirajMestoFormu(Mesto mesto) {
        kmController = new KreirajMestoController(new KreirajMestoForma());
        kmController.otvoriFormu(mesto);
    }

    public void otvoriKreirajKnjiguFormu(Knjiga pronadjena) {
        kkController = new KreirajKnjiguController(new KreirajKnjiguForma());
        kkController.otvoriFormu(pronadjena);
        
    }

    public void otvoriPretraziStrSpremuFormu() {
       pssController = new PretraziStrSpremaController(new PretraziStrSpremaForma());
       pssController.otvoriFormu();
    }
    
    public void otvoriKreirajKupcaFormu(){
        kkupcaController = new KreirajKupacController(new KreirajKupcaForma());
        kkupcaController.otvoriFormu();
       
    }

    public void otvoriPretraziKupcaFormu() {
        pretraziKupcaController = new PretraziKupcaController( new PretraziKupcaForma());
        pretraziKupcaController.otvoriFormu();
        
    }

    public void otvoriKreirajKupcaFormu(Kupac pronadjen) {
        kkupcaController = new KreirajKupacController(new KreirajKupcaForma());
        kkupcaController.otvoriFormu(pronadjen);
        
    }

    public void otvoriUbaciStrSpremuFormu() {
        ssController = new UbaciStrSpremuController(new UbaciStrSpremuForma());
        ssController.otvoriFormu();
    }

    public void otvoriUbaciStrucnuSpremuFormu(StrSprema pronadjena) {
        ssController = new UbaciStrSpremuController(new UbaciStrSpremuForma());
        ssController.otvoriFormu(pronadjena);
    }

    public void otvoriKreirajProdavcaFormu() {
        kreirajPController = new KreirajProdavcaController(new KreirajProdavcaForma());
        kreirajPController.otvoriFormu();
    }

    public void otvoriKreirajProdavcaFormu(Prodavac pronadjen) {
        kreirajPController = new KreirajProdavcaController(new KreirajProdavcaForma());
        kreirajPController.otvoriFormu(pronadjen);
    }

    public void otvoriKreirajRacunFormu() {
        kreirajRController = new KreirajRacunController(new KreirajRacunForma());
        kreirajRController.otvoriFormu();
    }
    
    public void otvoriKreirajRacunFormu(Racun racun) {
        kreirajRController = new KreirajRacunController(new KreirajRacunForma());
        kreirajRController.otvoriFormu(racun);
    }

    public void otvoriPretraziRacunFormu() {
       prController = new PretraziRacunController(new PretraziRacunForma());
       prController.otvoriFormu();
    }
    
  
    
    
    
    
}
