
package niti;

import kontroler.Kontroler;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import model.Knjiga;
import model.Kupac;
import model.Magacin;
import model.Mesto;
import model.Prodavac;
import model.Racun;
import model.StrSprema;
import transfer.Zahtev;
import transfer.Odgovor;

/**
 *
 * @author Ljilja
 */
public class ObradaKlijentskihZahteva extends Thread {
    
    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private String email;
    private Prodavac prijavljen;

    public Prodavac getPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(Prodavac prijavljen) {
        this.prijavljen = prijavljen;
    }
    public ObradaKlijentskihZahteva() {
    }



    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        this.primalac = new Primalac(s);
        this.posiljalac = new Posiljalac(s);
        this.ime = null;
        this.prezime = null;
        this.korisnickoIme = null;
        this.sifra = null;
        this.email = null;
        
    }

  


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        while(!kraj){
        try{
        Odgovor so = new Odgovor();
        while (s != null && !s.isClosed()) {
        Zahtev z = (Zahtev) primalac.primi();
        
        switch(z.getOperacije()){
            
            case PRIJAVI_PRODAVCA:
                try{
                Prodavac pPrijava = (Prodavac) z.getParametri();
                Prodavac prodavac = Kontroler.getInstance().prijaviProdavca(pPrijava);
                so.setOdgovor(prodavac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case DODAJ_PRIJAVLJENOG:
                boolean prijavljen = Kontroler.getInstance().dodajPrijavljenog((Prodavac) z.getParametri());
                 so.setOdgovor(prijavljen);
                 break;
                
            case ODJAVI_PRODAVCA:
                Prodavac p = (Prodavac) z.getParametri();
                try {
                 Kontroler.getInstance().odjaviProdavca(p); 
                 } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case UCITAJ_SVE_KNJIGE:
                List<Knjiga> knjige;
            try {
                knjige = Kontroler.getInstance().vratiListuKnjiga();
                so.setOdgovor(knjige);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                break;
                
            case OBRISI_KNJIGU:
                Knjiga knjigaZaBrisanje = (Knjiga) z.getParametri();
            {
                try {
                    boolean obrisano = Kontroler.getInstance().obrisiKnjigu(knjigaZaBrisanje);
                    so.setOdgovor(obrisano);
                } catch (Exception ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            
            case PROMENI_KNJIGU:
                Knjiga knjigaZaIzmenu = (Knjiga) z.getParametri();
               
            try {
                boolean promenjenaKnjiga = Kontroler.getInstance().promeniKnjigu(knjigaZaIzmenu);
                so.setOdgovor(promenjenaKnjiga);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;

            
            case PRETRAZI_KNJIGU:
                
                Knjiga pronadjena;
            try {
                Knjiga zaPretragu = (Knjiga) z.getParametri();
                pronadjena = Kontroler.getInstance().pretraziKnjigu(zaPretragu);
                so.setOdgovor(pronadjena);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            
            case KREIRAJ_KNJIGU:
                boolean uspesnoKreiranaKnjiga;
            {
                try {
                    Knjiga knjiga = (Knjiga) z.getParametri();
                    uspesnoKreiranaKnjiga=Kontroler.getInstance().kreirajKnjigu(knjiga);
                    so.setOdgovor(uspesnoKreiranaKnjiga);
                } catch (Exception ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                break;
                
            case VRATI_KNJIGE_KNJIGA:
                String knjiga = (String) z.getParametri();
                try {
                List<Knjiga> listaKnjiga = Kontroler.getInstance().vratiListuKnjige(knjiga);
                so.setOdgovor(listaKnjiga);
                } catch (Exception ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                } break;
                
             
            case VRATI_SVE_PRODAVCE:
                List<Prodavac> prodavci;
            {
                try {
                    prodavci = Kontroler.getInstance().vratiListuSvihProdavaca();
                    so.setOdgovor(prodavci);
                } catch (Exception ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
                break;
            case KREIRAJ_MESTO:
                boolean kreiranoMesto;
                try{
                Mesto m = (Mesto) z.getParametri();
               kreiranoMesto= Kontroler.getInstance().kreirajMesto(m);
                so.setOdgovor(kreiranoMesto);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case VRATI_SVA_MESTA:
                
            try {
                List<Mesto> mesta;
                mesta = Kontroler.getInstance().vratiListuSviMesta();
                so.setOdgovor(mesta);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                break;
                
            case KREIRAJ_KUPCA:
                boolean kreiranKupac;
                try{
                    Kupac kupac = (Kupac) z.getParametri();
                    kreiranKupac =  Kontroler.getInstance().kreirajKupca(kupac);
                    so.setOdgovor(kreiranKupac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
                
            case VRATI_SVE_KUPAC:
                try {
                List<Kupac> kupci = Kontroler.getInstance().vratiListuSviKupac();
                so.setOdgovor(kupci);
                } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                 }
                
                break;
                
            case PRETRAZI_KUPCA:
                Kupac primljen = (Kupac) z.getParametri();
                try {
                Kupac pretrazen = Kontroler.getInstance().pretraziKupca(primljen);
                so.setOdgovor(pretrazen);
                } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break;
                
            case PROMENI_KUPCA:
                Kupac zaPromenu = (Kupac) z.getParametri();
                try {
                boolean promenjenKupac = Kontroler.getInstance().promeniKupca(zaPromenu);
                so.setOdgovor(promenjenKupac);
                } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break;
            case OBRISI_KUPCA:
                Kupac zaBrisanje = (Kupac) z.getParametri();
               try {
                boolean obrisanKupac = Kontroler.getInstance().obrisiKupca(zaBrisanje);
                so.setOdgovor(obrisanKupac);
                } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break; 
            case VRATI_KUPCE_KUPAC:
                try {
                String kupac = (String) z.getParametri();
                List<Kupac> kupciKupac = Kontroler.getInstance().vratiListuKupacKupac(kupac);
                so.setOdgovor(kupciKupac);
                } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break; 
            case VRATI_KUPCE_MESTO:
                try {
                Mesto kupacMesto = (Mesto) z.getParametri();
                List<Kupac> kupciMesto = Kontroler.getInstance().vratiListuKupacMesto(kupacMesto);
                so.setOdgovor(kupciMesto);
                } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break; 
   
               
            case PRETRAZI_MESTO:
                Mesto pretrazeno;
            try {
                Mesto mesto = (Mesto) z.getParametri();
                pretrazeno = Kontroler.getInstance().pretraziMesto(mesto);
                so.setOdgovor(pretrazeno);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                break;
                
            case PROMENI_MESTO:
               
            {
                try { 
                    Mesto mestoPromena = (Mesto) z.getParametri();
                    boolean uspesnoPromenjenoMesto=uspesnoPromenjenoMesto = Kontroler.getInstance().promeniMesto(mestoPromena);
                    so.setOdgovor(uspesnoPromenjenoMesto);
                } catch (Exception ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            
            case OBRISI_MESTO:
               
            try {
                Mesto mestoZaBrisanje = (Mesto) z.getParametri();
               boolean uspesnoObrisanoMesto= uspesnoObrisanoMesto = Kontroler.getInstance().obrisiMesto(mestoZaBrisanje);
                so.setOdgovor(uspesnoObrisanoMesto);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
                
            case VRATI_LISTU_MESTA:
                Mesto mK = (Mesto) z.getParametri();
            try {
                List<Mesto> mestaa = Kontroler.getInstance().vratiListuMesto(mK);
                System.out.println("OBRADA KL: "+mestaa);
                so.setOdgovor(mestaa);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            case VRATI_SVE_STR_SPREMA:
                
            try {
               List<StrSprema> strSpreme = Kontroler.getInstance().vratiListuSviStrSprema();
                so.setOdgovor(strSpreme);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                break;
                
            case UBACI_STR_SPREMU:
                StrSprema zaUbacivanje = (StrSprema) z.getParametri();
                try{
                    boolean ubacenaStrSprema = Kontroler.getInstance().ubaciStrSprema(zaUbacivanje);
                    so.setOdgovor(ubacenaStrSprema);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case PRETRAZI_STR_SPREMU:
                StrSprema zaPretraguStrSprema = (StrSprema) z.getParametri();
                try{
                    StrSprema pronadjenaStrSprema = Kontroler.getInstance().pretraziStrSprema(zaPretraguStrSprema);
                    so.setOdgovor(pronadjenaStrSprema);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                //OBRISI_STR_SPREMU, PROMENI_STR_SPREMU, VRATI_STR_SPREME_STR_SPREMA, 
            case OBRISI_STR_SPREMU:
                StrSprema strSpremaZaBrisanje = (StrSprema) z.getParametri();
                try{
                    boolean obrisanaStrSprema = Kontroler.getInstance().obrisiStrSprema(strSpremaZaBrisanje);
                    so.setOdgovor(obrisanaStrSprema);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case PROMENI_STR_SPREMU:
                StrSprema strSpremaZaIzmenu = (StrSprema) z.getParametri();
                try{
                    boolean promenjenaStrSprema = Kontroler.getInstance().promeniStrSprema(strSpremaZaIzmenu);
                    so.setOdgovor(promenjenaStrSprema);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;   
            case VRATI_STR_SPREME_STR_SPREMA:
                String stepen = (String) z.getParametri();
                try{
                    List<StrSprema> strSpreme = Kontroler.getInstance().vratiListuStrSprema(stepen);
                    so.setOdgovor(strSpreme);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;   
                
            case KREIRAJ_PRODAVCA:
                Prodavac pZaKreiranje = (Prodavac) z.getParametri();
                try{
                    boolean kreiranProdavac = Kontroler.getInstance().kreirajProdavac(pZaKreiranje);
                    so.setOdgovor(kreiranProdavac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; 
            case PRETRAZI_PRODAVCA:
                Prodavac pZaPretragu = (Prodavac) z.getParametri();
                try{
                    Prodavac pronadjenProdavac = Kontroler.getInstance().pretraziProdavac(pZaPretragu);
                    so.setOdgovor(pronadjenProdavac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; 
            case OBRISI_PRODAVCA:
                Prodavac pZaBrisanje = (Prodavac) z.getParametri();
                try{
                    boolean obrisanProdavac = Kontroler.getInstance().obrisiProdavac(pZaBrisanje);
                    so.setOdgovor(obrisanProdavac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; 
            case VRATI_PRODAVCI_PRODAVAC:
                String prodavac = (String) z.getParametri();
                try{
                    List<Prodavac> prodavciProdavac = Kontroler.getInstance().vratiListuProdavacProdavac(prodavac);
                    so.setOdgovor(prodavciProdavac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; 
            case VRATI_PRODAVCI_STR_SPREMA:
                StrSprema ssFilter = (StrSprema) z.getParametri();
                try{
                    List<Prodavac> prodavciStrSprema = Kontroler.getInstance().vratiListuProdavacStrSprema(ssFilter);
                    so.setOdgovor(prodavciStrSprema);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; 
            case PROMENI_PRODAVCA:
                Prodavac pZaIzmenu = (Prodavac) z.getParametri();
                try{
                    boolean promenjenProdavac = Kontroler.getInstance().promeniProdavac(pZaIzmenu);
                    so.setOdgovor(promenjenProdavac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; 
                
            case KREIRAJ_RACUN:
                Racun prosledjenRacun = (Racun) z.getParametri();
                try{
                    boolean sacuvanProdavac = Kontroler.getInstance().kreirajRacun(prosledjenRacun);
                    so.setOdgovor(sacuvanProdavac);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case VRATI_LISTU_SVI_RACUN:
                try{
                List<Racun> racuni = Kontroler.getInstance().vratiListuSviRacun();
                so.setOdgovor(racuni);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case PROMENI_RACUN:
                Racun zaIzmenu = (Racun) z.getParametri();
                try{
                boolean promenjenRacun = Kontroler.getInstance().promeniRacun(zaIzmenu);
                so.setOdgovor(promenjenRacun);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case PRETRAZI_RACUN:
                Racun zaPretragu = (Racun) z.getParametri();
                try{
                Racun pronadjenRacun = Kontroler.getInstance().pretraziRacun(zaPretragu);
                so.setOdgovor(pronadjenRacun);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case VRATI_LISTU_RACUN_KUPAC:
                Kupac kupacPretraga = (Kupac) z.getParametri();
                try{
                List<Racun> racuni = Kontroler.getInstance().vratiListuRacunKupac(kupacPretraga);
                so.setOdgovor(racuni);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case VRATI_LISTU_RACUN_PRODAVAC:
                Prodavac prodavacPretraga = (Prodavac) z.getParametri();
                try{
                List<Racun> racuni = Kontroler.getInstance().vratiListuRacunProdavac(prodavacPretraga);
                so.setOdgovor(racuni);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case VRATI_LISTU_RACUN_KNJIGA:
                Knjiga knjigaPretraga = (Knjiga) z.getParametri();
                try{
                List<Racun> racuni = Kontroler.getInstance().vratiListuRacunKnjiga(knjigaPretraga);
                so.setOdgovor(racuni);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case VRATI_LISTU_RACUN_RACUN:
                double iznos = (double) z.getParametri();
                try{
                List<Racun> racuni = Kontroler.getInstance().vratiListuRacunRacun(iznos);
                so.setOdgovor(racuni);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case UCITAJ_SVE_MAGACINE:
                try{
                List<Magacin> magacini = Kontroler.getInstance().vratiListuSviMagacini();
                so.setOdgovor(magacini);
                }catch(Exception ex){
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
                
                
            default:
                System.out.println("Greska, operacija ne postoji");
        }
        posiljalac.posalji(so);
        
        
        }
        }catch (Exception ex) {
                System.out.println("Greska");
            }
    }
    }
    
    
    
    
    
    
    
/*public void prekiniNit() {
    kraj = true;
    try {
        if (s != null && !s.isClosed()) {
            s.close(); 
        }
    } catch (IOException ex) {
        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
    }
     setPrijavljen(null);
     Kontroler.getInstance().getSf().osveziTabele();
}*/
    
    
    
}
