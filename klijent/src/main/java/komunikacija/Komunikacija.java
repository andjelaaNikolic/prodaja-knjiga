package komunikacija;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Knjiga;
import model.Kupac;
import model.Magacin;
import model.Mesto;
import model.Prodavac;
import model.Racun;
import model.StrSprema;
import transfer.Odgovor;
import transfer.Zahtev;


public class Komunikacija {

    public static boolean uspeh;
    
    private Socket s;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;
    private int port;
    private Komunikacija(){
        
    }
    public static Komunikacija getInstance(){
        
        if(instance==null){
            instance=new Komunikacija();
        }
        return instance;
        
    }
    
    public void konekcija() throws IOException{
        ucitajPort();
        try {
            
            s = new Socket("localhost",port);
            posiljalac = new Posiljalac(s);
            primalac = new Primalac(s);
            uspeh=true;
        } catch (IOException ex) {
            uspeh=false;
            System.out.println("Server nije povezan");
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    public void ucitajPort() throws IOException{
        
        String putanja = "config/config.properties";

        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(putanja)) {
            p.load(fis);

            try {
                port = Integer.parseInt(p.getProperty("port"));
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Broj porta nije dat u ispravnom formatu.");

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Greska pri učitavanju konfiguracionog fajla.", "Greska", JOptionPane.ERROR_MESSAGE);
            
            throw e;

        }
        
        
    }

    public Prodavac prijaviProdavca(Prodavac p) {
       
        Zahtev zahtev = new Zahtev(p,Operacije.PRIJAVI_PRODAVCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Prodavac p1 = (Prodavac) odgovor.getOdgovor();
        return p1;
    }

    public List<Knjiga> vratiListuSvihKnjiga() {
       Zahtev zahtev = new Zahtev(null,Operacije.UCITAJ_SVE_KNJIGE);
       List<Knjiga> knjige = new ArrayList<>();
       posiljalac.posalji(zahtev);
       Odgovor odgovor = (Odgovor) primalac.primi();
       knjige = (List<Knjiga>) odgovor.getOdgovor();
       
       return knjige;
    }

    public boolean obrisiKnjigu(Knjiga k) {
        Zahtev zahtev = new Zahtev(k,Operacije.OBRISI_KNJIGU);
        boolean uspesno;
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
        
    }

    public boolean kreirajKnjigu(Knjiga k) {
        Zahtev zahtev = new Zahtev(k,Operacije.KREIRAJ_KNJIGU);
        boolean uspesno;
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public List<Prodavac> vratiListuSvihProdavaca() {
        Zahtev zahtev = new Zahtev(null,Operacije.VRATI_SVE_PRODAVCE);
        List<Prodavac> prodavci = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        prodavci = (List<Prodavac>) odgovor.getOdgovor();
        return prodavci;
    }

    public boolean kreirajMesto(Mesto m) {
        Zahtev zahtev = new Zahtev(m,Operacije.KREIRAJ_MESTO);
        posiljalac.posalji(zahtev);
        boolean uspesno;
        Odgovor odgovor = (Odgovor) primalac.primi();
        uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public List<Mesto> vratiListuSviMesto() {
        List<Mesto> mesta = new ArrayList<>();
        Zahtev zahtev = new Zahtev(null,Operacije.VRATI_SVA_MESTA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        mesta = (List<Mesto>) odgovor.getOdgovor();
        return mesta;
    }

    public boolean kreirajKupca(Kupac kupac) {
        boolean uspesno;
        Zahtev zahtev = new Zahtev(kupac,Operacije.KREIRAJ_KUPCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
        
    }

    public Mesto pretraziMesto(Mesto m) {
        Mesto mesto;
        Zahtev zahtev = new Zahtev(m,Operacije.PRETRAZI_MESTO);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        mesto = (Mesto) odgovor.getOdgovor();
        return mesto;
        
    }

    public boolean promeniMesto(Mesto mestoPromena) {
        Zahtev zahtev = new Zahtev(mestoPromena,Operacije.PROMENI_MESTO);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public boolean obrisiMesto(Mesto mestoBrisanje) {
        Zahtev zahtev = new Zahtev(mestoBrisanje,Operacije.OBRISI_MESTO);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
        
    }

    public List<Mesto> vratiListuMesto(Mesto m) {
        List<Mesto> mesta;
       Zahtev zahtev = new Zahtev(m,Operacije.VRATI_LISTU_MESTA);
       posiljalac.posalji(zahtev);
       Odgovor odgovor = (Odgovor) primalac.primi();
       mesta = (List<Mesto>) odgovor.getOdgovor();
        System.out.println("STA PRIMA KOMUNIKACIJA "+mesta);
       return mesta;
    }

    public Knjiga pretraziKnjigu(Knjiga k) {
        Zahtev zahtev = new Zahtev(k,Operacije.PRETRAZI_KNJIGU);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Knjiga knjiga = (Knjiga) odgovor.getOdgovor();
        return knjiga;
        
    }

    public boolean promeniKnjigu(Knjiga knjigaZaIzmenu) {
      Zahtev zahtev = new Zahtev(knjigaZaIzmenu,Operacije.PROMENI_KNJIGU);
      posiljalac.posalji(zahtev);
      Odgovor odgovor = (Odgovor) primalac.primi();
      boolean uspesno = (boolean) odgovor.getOdgovor();
      return uspesno;
    }

    public List<StrSprema> vratiListuSviStrSprema() {
        Zahtev zahtev = new Zahtev(null,Operacije.VRATI_SVE_STR_SPREMA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<StrSprema> strSpreme = (List<StrSprema>) odgovor.getOdgovor();
        return strSpreme;
    }

    public List<Kupac> vratiListuSviKupac() {
        Zahtev zahtev = new Zahtev(null,Operacije.VRATI_SVE_KUPAC);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Kupac> kupci = (List<Kupac>) odgovor.getOdgovor(); 
        System.out.println("Primljeni kupci: "+kupci);
        return kupci;
       
    }

    public Kupac pretraziKupca(Kupac k) {
        Zahtev zahtev = new Zahtev(k,Operacije.PRETRAZI_KUPCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Kupac kupac = (Kupac) odgovor.getOdgovor();
        return kupac;
    }

    public boolean promeniKupca(Kupac kupac) {
        Zahtev zahtev = new Zahtev(kupac,Operacije.PROMENI_KUPCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public boolean obrisiKupca(Kupac zaBrisanje) {
        Zahtev zahtev = new Zahtev(zaBrisanje,Operacije.OBRISI_KUPCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
        
    }

    public List<Kupac> vratiListuKupacKupac(String kupac) {
        Zahtev zahtev = new Zahtev(kupac,Operacije.VRATI_KUPCE_KUPAC);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Kupac> kupci = (List<Kupac>) odgovor.getOdgovor();
        return kupci;
    }

    public List<Kupac> vratiListuKupacMesto(Mesto mesto) {
        Zahtev zahtev = new Zahtev(mesto,Operacije.VRATI_KUPCE_MESTO);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Kupac> kupci = (List<Kupac>) odgovor.getOdgovor();
        return kupci;
        
    }

    public void odjaviProdavca(Prodavac ulogovaniProdavac) {
         Zahtev zahtev = new Zahtev(ulogovaniProdavac, Operacije.ODJAVI_PRODAVCA);

        posiljalac.posalji(zahtev);

        if (s != null && !s.isClosed()) {
            try {
                s.close();
                instance = null;

            } catch (IOException ex) {
                System.err.println("Greška prilikom odjavljivanja: " + ex.getMessage());
            }

        }
    }

    public List<Knjiga> vratiListuKnjiga(String knjiga) {
        Zahtev zahtev = new Zahtev(knjiga,Operacije.VRATI_KNJIGE_KNJIGA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Knjiga> knjige = (List<Knjiga>) odgovor.getOdgovor();
        return knjige;
    }

    public boolean ubaciStrSpremu(StrSprema ss) {
        Zahtev zahtev = new Zahtev(ss,Operacije.UBACI_STR_SPREMU);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno =  (boolean) odgovor.getOdgovor();
        System.out.println("Odgovor sa servera: " + odgovor.getOdgovor());
        return uspesno;
    }

    public StrSprema pretraziStrSprema(StrSprema ss) {
        Zahtev zahtev = new Zahtev(ss,Operacije.PRETRAZI_STR_SPREMU);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        StrSprema strSprema =  (StrSprema) odgovor.getOdgovor();
        System.out.println("Odgovor sa servera: " + odgovor.getOdgovor());
        return strSprema;
    }

    public boolean obrisiStrSpremu(StrSprema zaBrisanje) {
        Zahtev zahtev = new Zahtev(zaBrisanje,Operacije.OBRISI_STR_SPREMU);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public boolean promeniStrSpremu(StrSprema zaIzmenu) {
        Zahtev zahtev = new Zahtev(zaIzmenu,Operacije.PROMENI_STR_SPREMU);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public List<StrSprema> vratiListuStrSprema(String strSprema) {
        Zahtev zahtev = new Zahtev(strSprema,Operacije.VRATI_STR_SPREME_STR_SPREMA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<StrSprema> strSpreme = (List<StrSprema>) odgovor.getOdgovor();
        return strSpreme;
    }

    public boolean kreirajProdavac(Prodavac p) {
        Zahtev zahtev = new Zahtev(p,Operacije.KREIRAJ_PRODAVCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
        
    }

    public Prodavac pretraziProdavac(Prodavac zaPretragu) {
        Zahtev zahtev = new Zahtev(zaPretragu,Operacije.PRETRAZI_PRODAVCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Prodavac p = (Prodavac) odgovor.getOdgovor();
        return p;
    }

    public boolean obrisiProdavac(Prodavac prodavac) {
        Zahtev zahtev = new Zahtev(prodavac,Operacije.OBRISI_PRODAVCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public boolean promeniProdavac(Prodavac p) {
        Zahtev zahtev = new Zahtev(p,Operacije.PROMENI_PRODAVCA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
    }

    public List<Prodavac> vratiListuProdavacProdavac(String prodavac) {
        Zahtev zahtev = new Zahtev(prodavac,Operacije.VRATI_PRODAVCI_PRODAVAC);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Prodavac> prodavci = (List<Prodavac>) odgovor.getOdgovor();
        return prodavci;
    }

    public List<Prodavac> vratiListuProdavacStrSprema(StrSprema strSprema) {
        Zahtev zahtev = new Zahtev(strSprema,Operacije.VRATI_PRODAVCI_STR_SPREMA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Prodavac> prodavci = (List<Prodavac>) odgovor.getOdgovor();
        return prodavci;
    }

    public boolean kreirajRacun(Racun racun) {
        Zahtev zahtev = new Zahtev(racun,Operacije.KREIRAJ_RACUN);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
        
    }
    
    public boolean promeniRacun(Racun racun){
        Zahtev zahtev = new Zahtev(racun,Operacije.PROMENI_RACUN);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean uspesno = (boolean) odgovor.getOdgovor();
        return uspesno;
        
    }
    
    public Racun pretraziRacun(Racun racun){
        Zahtev zahtev = new Zahtev(racun,Operacije.PRETRAZI_RACUN);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Racun pronadjen = (Racun) odgovor.getOdgovor();
        return pronadjen;
    }
    
    public List<Racun> vratiListuSviRacun(){
        Zahtev zahtev = new Zahtev(null,Operacije.VRATI_LISTU_SVI_RACUN);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Racun> racuni = (List<Racun>) odgovor.getOdgovor();
        return racuni;
    }

     public List<Racun> vratiListuRacunKupac(Kupac kupac) {
        Zahtev zahtev = new Zahtev(kupac,Operacije.VRATI_LISTU_RACUN_KUPAC);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Racun> racuni = (List<Racun>) odgovor.getOdgovor();
        return racuni;
        
    }
     
     public List<Racun> vratiListuRacunProdavac(Prodavac prodavac) {
        Zahtev zahtev = new Zahtev(prodavac,Operacije.VRATI_LISTU_RACUN_PRODAVAC);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Racun> racuni = (List<Racun>) odgovor.getOdgovor();
        return racuni;
        
    }
     
     public List<Racun> vratiListuRacunKnjiga(Knjiga knjiga) {
        Zahtev zahtev = new Zahtev(knjiga,Operacije.VRATI_LISTU_RACUN_KNJIGA);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Racun> racuni = (List<Racun>) odgovor.getOdgovor();
        return racuni;
        
    }
     
     public List<Racun> vratiListuRacunRacun(double iznos) {
        Zahtev zahtev = new Zahtev(iznos,Operacije.VRATI_LISTU_RACUN_RACUN);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Racun> racuni = (List<Racun>) odgovor.getOdgovor();
        return racuni;
        
    }

    public boolean dodajPrijavljenogKorisnika(Prodavac prodavac) {
       Zahtev zahtev = new Zahtev(prodavac, Operacije.DODAJ_PRIJAVLJENOG);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return false;
        }
        return (boolean) so.getOdgovor();
    }

    public List<Magacin> vratiListuSviMagacini() {
       Zahtev zahtev = new Zahtev(null,Operacije.UCITAJ_SVE_MAGACINE);
       List<Magacin> magacini = new ArrayList<>();
       posiljalac.posalji(zahtev);
       Odgovor odgovor = (Odgovor) primalac.primi();
       magacini = (List<Magacin>) odgovor.getOdgovor();
       
       return magacini;
    }

    
    
}
