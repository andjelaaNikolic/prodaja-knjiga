/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Knjiga;
import model.Kupac;
import model.Magacin;
import model.Mesto;
import model.Prodavac;
import model.Racun;
import model.StrSprema;
import model.tabele.ModelTabeleProdavac;
import niti.ObradaKlijentskihZahteva;
import niti.ServerskaNit;
import so.knjige.SOKreirajKnjiga;
import so.knjige.SOObrisiKnjiga;
import so.knjige.SOPretraziKnjiga;
import so.knjige.SOPromeniKnjiga;
import so.knjige.SOVratiListuKnjiga;
import so.prodavac.SOPrijaviProdavac;
import so.knjige.SOVratiListuSviKnjiga;
import so.kupac.SOKreirajKupac;
import so.kupac.SOObrisiKupac;
import so.kupac.SOPretraziKupac;
import so.kupac.SOPromeniKupac;
import so.kupac.SOVratiListuKupacKupac;
import so.kupac.SOVratiListuKupacMesto;
import so.kupac.SOVratiListuSviKupac;
import so.magacini.SOVratiListuSviMagacini;
import so.mesto.SOUbaciMesto;
import so.mesto.SOObrisiMesto;
import so.mesto.SOPretraziMesto;
import so.mesto.SOPromeniMesto;
import so.mesto.SOVratiListuMesto;
import so.mesto.SOVratiListuSviMesto;
import so.prodavac.SOKreirajProdavac;
import so.prodavac.SOObrisiProdavac;
import so.prodavac.SOPretraziProdavac;
import so.prodavac.SOPromeniProdavac;
import so.prodavac.SOVratiListuProdavacProdavac;
import so.prodavac.SOVratiListuProdavacStrSprema;
import so.prodavac.SOVratiListuSviProdavac;
import so.racun.SOKreirajRacun;
import so.racun.SOPretraziRacun;
import so.racun.SOPromeniRacun;
import so.racun.SOVratiListuRacunKnjiga;
import so.racun.SOVratiListuRacunKupac;
import so.racun.SOVratiListuRacunProdavac;
import so.racun.SOVratiListuRacunRacun;
import so.racun.SOVratiListuSviRacun;
import so.strSprema.SOObrisiStrSprema;
import so.strSprema.SOPretraziStrSprema;
import so.strSprema.SOPromeniStrSprema;
import so.strSprema.SOUbaciStrSprema;
import so.strSprema.SOVratiListuStrSprema;
import so.strSprema.SOVratiListuSviStrSprema;

/**
 *
 * @author Ljilja
 */
public class Kontroler {
    private static Kontroler instance;
    private Prodavac prijavljenProdavac = null;
    ServerskaForma sf = new ServerskaForma();
    

    private Kontroler() {
    }
    
    public static Kontroler getInstance(){
        if(instance==null){
            instance= new Kontroler();
        }
        return instance;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public Prodavac getPrijavljenProdavac() {
        return prijavljenProdavac;
    }

    public void setPrijavljenProdavac(Prodavac prijavljenProdavac) {
        this.prijavljenProdavac = prijavljenProdavac;
    }
    
    

    public Prodavac prijaviProdavca(Prodavac p) {
        SOPrijaviProdavac pp = new SOPrijaviProdavac();
        try {
            pp.izvrsi(p, null);
            System.out.println("KONTROLER "+pp.getProdavac());
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pp.getProdavac();
    }

    public List<Knjiga> vratiListuKnjiga() throws Exception {
        SOVratiListuSviKnjiga vlk = new SOVratiListuSviKnjiga();
        vlk.izvrsi(new Knjiga(), null);
        return vlk.getKnjige();
    }

    public boolean obrisiKnjigu(Knjiga knjiga) throws Exception {
        SOObrisiKnjiga ok = new SOObrisiKnjiga();
        ok.izvrsi(knjiga, null);
        return ok.isUspesno();
        
    }

    public boolean kreirajKnjigu(Knjiga knjiga) throws Exception {
        SOKreirajKnjiga kk = new SOKreirajKnjiga();
        kk.izvrsi(knjiga, null);
        System.out.println(knjiga);
        return kk.isUspesno();
    }

    public List<Prodavac> vratiListuSvihProdavaca() throws Exception {
       SOVratiListuSviProdavac vlsp = new SOVratiListuSviProdavac();
       vlsp.izvrsi(new Prodavac(), null);
       return vlsp.getProdavci();
    }

    public boolean kreirajMesto(Mesto m) throws Exception {
        SOUbaciMesto km = new SOUbaciMesto();
       km.izvrsi(m, null);
        System.out.println(m);
        return km.isUspesno();
    }

    public List<Mesto> vratiListuSviMesta() throws Exception {
        SOVratiListuSviMesto sm = new SOVratiListuSviMesto();
        sm.izvrsi(new Mesto(), null);
        return sm.getMesta();
        
    }

    public boolean kreirajKupca(Kupac kupac) throws Exception {
        SOKreirajKupac kk = new SOKreirajKupac();
        kk.izvrsi(kupac, null);
        return kk.isUspesno();
         
                }

    public Mesto pretraziMesto(Mesto mesto) throws Exception {
        SOPretraziMesto pm = new SOPretraziMesto();
        pm.izvrsi(mesto, null);
        return pm.getMesto();
    }

    public boolean promeniMesto(Mesto mestoPromena) throws Exception {
        SOPromeniMesto pm = new SOPromeniMesto();
        pm.izvrsi(mestoPromena, null);
        return pm.isUspesno();
        
    }

    public boolean obrisiMesto(Mesto mestoZaBrisanje) throws Exception {
        SOObrisiMesto om = new SOObrisiMesto();
        om.izvrsi(mestoZaBrisanje, null);
        return om.isUspesno();
    }

    public List<Mesto> vratiListuMesto(Mesto m) throws Exception {
        SOVratiListuMesto vlm = new SOVratiListuMesto();
        vlm.izvrsi(new Mesto(), m);
        System.out.println("KONTROLER SERVER VRACA: "+vlm.getMesta());
        return vlm.getMesta();
    }

    public Knjiga pretraziKnjigu(Knjiga zaPretragu) throws Exception {
        SOPretraziKnjiga pk = new SOPretraziKnjiga();
        pk.izvrsi(zaPretragu, null);
        return pk.getK();
    }

    public boolean promeniKnjigu(Knjiga knjigaZaIzmenu) throws Exception {
       SOPromeniKnjiga pk = new SOPromeniKnjiga();
       pk.izvrsi(knjigaZaIzmenu,null);
       return pk.isUspesno();
    }

    public List<StrSprema> vratiListuSviStrSprema() throws Exception {
        SOVratiListuSviStrSprema lss = new SOVratiListuSviStrSprema();
        lss.izvrsi(new StrSprema(), null);
        return lss.getStrSpreme();
    }

    public List<Kupac> vratiListuSviKupac() throws Exception {
       SOVratiListuSviKupac vlsk = new SOVratiListuSviKupac();
       vlsk.izvrsi(new Kupac(), null);
       return vlsk.getKupci();
    }

    public Kupac pretraziKupca(Kupac primljen) throws Exception {
        SOPretraziKupac pk = new SOPretraziKupac();
        pk.izvrsi(primljen, null);
        return pk.getK();
    }

    public boolean promeniKupca(Kupac zaPromenu) throws Exception {
        SOPromeniKupac spk = new SOPromeniKupac();
        spk.izvrsi(zaPromenu, null);
        return spk.isUspesno();
    }

    public boolean obrisiKupca(Kupac zaBrisanje) throws Exception {
        SOObrisiKupac ok = new SOObrisiKupac();
        ok.izvrsi(zaBrisanje, null);
        return ok.isUspesno();
        
    }

    public List<Kupac> vratiListuKupacKupac(String kupac) throws Exception {
       SOVratiListuKupacKupac lkk = new SOVratiListuKupacKupac();
       lkk.izvrsi(new Kupac(),kupac);
       return lkk.getKupci();
    }

    public List<Kupac> vratiListuKupacMesto(Mesto kupacMesto) throws Exception{
        SOVratiListuKupacMesto lkm = new SOVratiListuKupacMesto();
        lkm.izvrsi(new Kupac(), kupacMesto);
        return lkm.getKupci();
    }
    
    public void osveziTabele() {
        sf.osveziTabele();
    }

    
    public boolean dodajPrijavljenog(Prodavac prodavac) throws Exception {
       
       boolean prijavljen = false;

        for (ObradaKlijentskihZahteva korisnik : ServerskaNit.getPrijavljeniKorisnici()) {
            if (korisnik.getIme() != null && korisnik.getIme().equals(prodavac.getIme())
                    && korisnik.getPrezime() != null && korisnik.getPrezime().equals(prodavac.getPrezime())
                    && korisnik.getKorisnickoIme() != null && korisnik.getKorisnickoIme().equals(prodavac.getKorisnickoIme())
                    && korisnik.getSifra() != null && korisnik.getSifra().equals(prodavac.getSifra())
                    && korisnik.getEmail() != null && korisnik.getEmail().equals(prodavac.getEmail())) {
                prijavljen = true;

            }
        }

     
            if (!prijavljen) {
            for (ObradaKlijentskihZahteva korisnik : ServerskaNit.getPrijavljeniKorisnici()) {

                if (korisnik.getIme() == null
                        && korisnik.getPrezime() == null
                        && korisnik.getKorisnickoIme() == null
                        && korisnik.getSifra() == null
                        && korisnik.getEmail() == null) {
                    korisnik.setIme(prodavac.getIme());
                    korisnik.setPrezime(prodavac.getPrezime());
                    korisnik.setKorisnickoIme(prodavac.getKorisnickoIme());
                    korisnik.setSifra(prodavac.getSifra());
                    korisnik.setEmail(prodavac.getEmail());
                    break;
            }
            }
            }
            

        
        Kontroler.getInstance().getSf().osveziTabele();

        return prijavljen;
    }
    
    
    public void odjaviProdavca(Prodavac prodavac) throws Exception {
        ServerskaNit.odjaviProdavca(prodavac);
        Kontroler.getInstance().getSf().osveziTabele();

    }

    public List<Knjiga> vratiListuKnjige(String knjiga) throws Exception {
        SOVratiListuKnjiga vlk = new SOVratiListuKnjiga();
        vlk.izvrsi(new Knjiga(), knjiga);
        return vlk.getKnjige();
    }

    public boolean ubaciStrSprema(StrSprema zaUbacivanje) throws Exception {
        SOUbaciStrSprema uss = new SOUbaciStrSprema();
        uss.izvrsi(zaUbacivanje, null);
        return uss.isUspesno();
    }

    public StrSprema pretraziStrSprema(StrSprema zaPretraguStrSprema) throws Exception {
        SOPretraziStrSprema pss = new SOPretraziStrSprema();
        pss.izvrsi(zaPretraguStrSprema, null);
        return pss.getSs();
        
    }

    public boolean obrisiStrSprema(StrSprema strSpremaZaBrisanje) throws Exception {
       SOObrisiStrSprema oss = new SOObrisiStrSprema();
       oss.izvrsi(strSpremaZaBrisanje, null);
       return oss.isUspesno();
    }

    public boolean promeniStrSprema(StrSprema strSpremaZaIzmenu) throws Exception {
       SOPromeniStrSprema pss = new SOPromeniStrSprema();
       pss.izvrsi(strSpremaZaIzmenu, null);
       return pss.isUspesno();
    }

    public List<StrSprema> vratiListuStrSprema(String stepen) throws Exception {
        SOVratiListuStrSprema vlss = new SOVratiListuStrSprema();
        vlss.izvrsi(new StrSprema(), stepen);
        return vlss.getStrSpreme();
        
    }

    public boolean kreirajProdavac(Prodavac pZaKreiranje) throws Exception {
        SOKreirajProdavac kp = new SOKreirajProdavac();
        kp.izvrsi(pZaKreiranje, null);
        return kp.isUspesno();
    }

    public Prodavac pretraziProdavac(Prodavac pZaPretragu) throws Exception {
        SOPretraziProdavac pp = new SOPretraziProdavac();
        pp.izvrsi(pZaPretragu, null);
        return pp.getP();
    }

    public boolean obrisiProdavac(Prodavac pZaBrisanje) throws Exception {
        SOObrisiProdavac op = new SOObrisiProdavac();
        op.izvrsi(pZaBrisanje, null);
        return op.isUspesno();
    }

    public List<Prodavac> vratiListuProdavacProdavac(String prodavac) throws Exception {
        SOVratiListuProdavacProdavac pp = new SOVratiListuProdavacProdavac();
        pp.izvrsi(new Prodavac(), prodavac);
        return pp.getProdavci();
       
    }

    public List<Prodavac> vratiListuProdavacStrSprema(StrSprema ssFilter) throws Exception {
        SOVratiListuProdavacStrSprema pss = new SOVratiListuProdavacStrSprema();
        pss.izvrsi(new Prodavac(), ssFilter);
        return pss.getProdavci();
    }

    public boolean promeniProdavac(Prodavac pZaIzmenu) throws Exception {
        SOPromeniProdavac pp = new SOPromeniProdavac();
        pp.izvrsi(pZaIzmenu, null);
        return pp.isUspesno();
    }

    public boolean kreirajRacun(Racun prosledjenRacun) throws Exception {
        SOKreirajRacun kr = new SOKreirajRacun();
        kr.izvrsi(prosledjenRacun, null);
        return kr.isUspesno();
    }

    public List<Racun> vratiListuSviRacun() throws Exception {
        SOVratiListuSviRacun vlsr = new SOVratiListuSviRacun();
        vlsr.izvrsi(new Racun(), null);
        return vlsr.getRacuni();
    }

    public boolean promeniRacun(Racun zaIzmenu) throws Exception {
        SOPromeniRacun pr = new SOPromeniRacun();
        pr.izvrsi(zaIzmenu, null);
        return pr.isUspesno();
    }

    public Racun pretraziRacun(Racun zaPretragu) throws Exception {
       SOPretraziRacun pr = new SOPretraziRacun();
       pr.izvrsi(zaPretragu, null);
       return pr.getRacun();
    }

    public List<Racun> vratiListuRacunKupac(Kupac kupacPretraga) throws Exception {
       SOVratiListuRacunKupac rk = new SOVratiListuRacunKupac();
       rk.izvrsi(new Racun(), kupacPretraga);
       return rk.getRacuni();
    }

    public List<Racun> vratiListuRacunProdavac(Prodavac prodavacPretraga) throws Exception {
       SOVratiListuRacunProdavac rp = new SOVratiListuRacunProdavac();
       rp.izvrsi(new Racun(), prodavacPretraga);
       return rp.getRacuni();
    }

    public List<Racun> vratiListuRacunKnjiga(Knjiga knjigaPretraga) throws Exception {
       SOVratiListuRacunKnjiga rk = new SOVratiListuRacunKnjiga();
       rk.izvrsi(new Racun(), knjigaPretraga);
       return rk.getRacuni();
    }

    public List<Racun> vratiListuRacunRacun(double iznos) throws Exception {
       SOVratiListuRacunRacun rr = new SOVratiListuRacunRacun();
       rr.izvrsi(new Racun(), iznos);
       return rr.getRacuni();
    }

    public List<Magacin> vratiListuSviMagacini() throws Exception {
        SOVratiListuSviMagacini sm = new SOVratiListuSviMagacini();
        sm.izvrsi(new Magacin(), null);
        return sm.getMagacini();
    }


   
    
    
}
