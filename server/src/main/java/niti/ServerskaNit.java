/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import kontroler.Kontroler;
import db.repozitorijum.DBKonekcija;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
import model.Prodavac;

/**
 *
 * @author Ljilja
 */
public class ServerskaNit extends Thread {


    /*
    private ServerSocket serverskiSoket;
    boolean kraj=false;
    private static ObradaKlijentskihZahteva nit = null;
    private static List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    private String port;
    
        public static List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }
        public static void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        ServerskaNit.klijenti = klijenti;
    }

    @Override
    public void run() {
            try{
            try {
               port = Konfiguracija.getInstance().ucitajPodatak("port");

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
                
               serverskiSoket = new ServerSocket(Integer.parseInt(port));
               System.out.println("Server je pokrenut na portu: " + port);  
               
              
               
           while(!kraj){  
                try {
                Socket s = serverskiSoket.accept();
                System.out.println("Klijent je povezan");
                
                nit = new ObradaKlijentskihZahteva(s);
                klijenti.add(nit);
                nit.start(); 
                }catch (SocketException e) {
                    if (nit != null) {
                        nit.interrupt();
                    }
                    if (!kraj) {
                        break;
                       
                    }
                    System.err.println("Greška: " + e.getMessage());
                }
            }
            
           
            
            }catch (IOException ex) {
            kraj = false;
            System.err.println("Greška prilikom pokretanja servera.");

        }
        
    }
    
    public void zaustaviServer(){
        kraj=true;
        try{
        
            for (ObradaKlijentskihZahteva nit : klijenti) {
                klijenti.remove(nit);
                nit.prekiniNit();
            }
            serverskiSoket.close();
             
        } catch (IOException ex) {
            Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server je zaustavljen.");
        
        
     
        
    }

    
   public static void odjaviProdavca(Prodavac prodavac) {
    if (prodavac == null) return;

    Iterator<ObradaKlijentskihZahteva> it = klijenti.iterator();
    while (it.hasNext()) {
        ObradaKlijentskihZahteva nit = it.next();

        if (nit.getIme().equals(prodavac.getIme())
                && nit.getPrezime().equals(prodavac.getPrezime())
                && nit.getEmail().equals(prodavac.getEmail())
                && nit.getKorisnickoIme().equals(prodavac.getKorisnickoIme())
                && nit.getSifra().equals(prodavac.getSifra())) {
            try {
                nit.interrupt();
                if (nit.getS() != null && !nit.getS().isClosed()) {
                    nit.getS().close();
                }
                it.remove();
                System.out.println("Klijent " + prodavac.getIme() + " " + prodavac.getPrezime() + " se odjavio.");
            } catch (IOException e) {
                Logger.getLogger(ServerskaNit.class.getName())
                        .log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", e);
            }
            break; 
        }
    }
    try {
            nit.interrupt();
            nit.getS().close();
            klijenti.remove(nit);
        } catch (IOException e) {
            Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", e);

        }
    Kontroler.getInstance().getSf().osveziTabele();
}*/

    
    /*private ServerSocket serverskiSoket;
    private static Socket s;
    private static boolean kraj = false;
    private static ObradaKlijentskihZahteva nit = null;
    private String port;
    private static List<ObradaKlijentskihZahteva> prijavljeniKorisnici = new ArrayList<>();

    public static List<ObradaKlijentskihZahteva> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public static void setPrijavljeniKorisnici(List<ObradaKlijentskihZahteva> prijavljeniKorisnici) {
        ServerskaNit.prijavljeniKorisnici = prijavljeniKorisnici;
    }

    @Override
    public void run() {
        try {

            try {
                port = Konfiguracija.getInstance().ucitajPodatak("port");

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }

            serverskiSoket = new ServerSocket(Integer.parseInt(port));

            

            System.out.println("Server je pokrenut na portu: " + port);
            while (!kraj) {
                try {

                    s = serverskiSoket.accept();

                    System.out.println("Klijent je povezan.");
                    nit = new ObradaKlijentskihZahteva(s);
                    prijavljeniKorisnici.add(nit);
                    nit.start();
                } catch (SocketException e) {
                    if (nit != null) {
                        nit.interrupt();
                    }
                    if (kraj) {
                        break;
                    }
                    System.err.println("Greška: " + e.getMessage());
                }

            }

        } catch (IOException ex) {
            kraj = true;
            System.err.println("Greška prilikom pokretanja servera.");

        }

    }

    public void zaustaviServer() {

        try {
            if (serverskiSoket != null && !serverskiSoket.isClosed()) {
                serverskiSoket.close();
                kraj=true;
                for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                    try {
                        nit.interrupt();
                        nit.getS().close();
                        prijavljeniKorisnici.remove(nit);
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }

                }

                System.out.println("Server je zaustavljen.");

            }

            DBKonekcija.resetuj();

        } catch (IOException e) {
            System.err.println("Greška pri zaustavljanju servera: " + e.getMessage());
        }
    }

    public static void odjaviKlijenta(Prodavac prodavac) {

        if (prodavac != null) {
            for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                if (nit.getIme().equals(prodavac.getIme())
                        && nit.getPrezime().equals(prodavac.getPrezime())
                        && nit.getEmail().equals(prodavac.getEmail())
                        && nit.getKorisnickoIme().equals(prodavac.getKorisnickoIme())
                        && nit.getSifra().equals(prodavac.getSifra())) {
                    try {
                        nit.interrupt();
                        nit.getS().close();
                        prijavljeniKorisnici.remove(nit);
                        System.out.println("Klijent " + prodavac.getIme() + " " + prodavac.getPrezime() + " se odjavio.");

                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }
                }
            }
        }
        try {
            nit.interrupt();
            nit.getS().close();
            prijavljeniKorisnici.remove(nit);
        } catch (IOException e) {
            Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", e);

        }
    }

    public static void dodajKorisnika() {
        prijavljeniKorisnici.add(nit);
    }

    public static boolean isKraj() {
        return kraj;
    }

    public static void setKraj(boolean kraj) {
        ServerskaNit.kraj= kraj;
    }*/
    
    private ServerSocket serverskiSoket;
    private static Socket s;
    private static boolean pokrenut = false;
    private static ObradaKlijentskihZahteva nit = null;
    private String port;
    private static List<ObradaKlijentskihZahteva> prijavljeniKorisnici = new ArrayList<>();

    public static List<ObradaKlijentskihZahteva> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public static void setPrijavljeniKorisnici(List<ObradaKlijentskihZahteva> prijavljeniKorisnici) {
        ServerskaNit.prijavljeniKorisnici = prijavljeniKorisnici;
    }

    @Override
    public void run() {
        try {

            try {
                port = Konfiguracija.getInstance().ucitajPodatak("port");

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }

            serverskiSoket = new ServerSocket(Integer.parseInt(port));

            pokrenut = true;

            System.out.println("Server je pokrenut na portu: " + port);
            while (pokrenut) {
                try {

                    s = serverskiSoket.accept();

                    System.out.println("Klijent je povezan.");
                    nit = new ObradaKlijentskihZahteva(s);
                    prijavljeniKorisnici.add(nit);
                    nit.start();
                } catch (SocketException e) {
                    if (nit != null) {
                        nit.interrupt();
                    }
                    if (!pokrenut) {
                        break;
                    }
                    System.err.println("Greška: " + e.getMessage());
                }

            }

        } catch (IOException ex) {
            pokrenut = false;
            System.err.println("Greška prilikom pokretanja servera.");

        }

    }

    public void zaustaviServer() {
         pokrenut = false;
        try {
            if (serverskiSoket != null && !serverskiSoket.isClosed()) {
                serverskiSoket.close();
               
                for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                    try {
                        nit.interrupt();
                        nit.getS().close();
                        prijavljeniKorisnici.remove(nit);
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }

                }

                System.out.println("Server je zaustavljen.");

            }

            DBKonekcija.resetuj();

        } catch (IOException e) {
            System.err.println("Greška pri zaustavljanju servera: " + e.getMessage());
        }
    }

    public static void odjaviProdavca(Prodavac p) {

        if (p!= null) {
            for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                if (nit.getIme().equals(p.getIme())
                        && nit.getPrezime().equals(p.getPrezime())
                        && nit.getEmail().equals(p.getEmail())
                        && nit.getKorisnickoIme().equals(p.getKorisnickoIme())
                        && nit.getSifra().equals(p.getSifra())) {
                    try {
                        nit.interrupt();
                        nit.getS().close();
                        prijavljeniKorisnici.remove(nit);
                        System.out.println("Klijent " + p.getIme() + " " + p.getPrezime() + " se odjavio.");

                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }
                }
            }
        }
        try {
            nit.interrupt();
            nit.getS().close();
            prijavljeniKorisnici.remove(nit);
        } catch (IOException e) {
            Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", e);

        }
    }

    public static void dodajKorisnika() {
        prijavljeniKorisnici.add(nit);
    }

    public static boolean isPokrenut() {
        return pokrenut;
    }

    public static void setPokrenut(boolean pokrenut) {
        ServerskaNit.pokrenut = pokrenut;
    }

}

   
 




   
    
  
    
    

