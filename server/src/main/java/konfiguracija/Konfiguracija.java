/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ljilja
 */
public class Konfiguracija {
    
    private static Konfiguracija instance;
    private Properties konfiguracija;
    
    private Konfiguracija(){
        konfiguracija = new Properties();
        try {
            konfiguracija.load(new FileInputStream("config/config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Konfiguracija getInstance(){
        if(instance==null){
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getProperty(String key){
        return konfiguracija.getProperty(key, "n/a");
        
    }
    public void setProperty(String key, String value){
        konfiguracija.setProperty(key, value);
    }
    
    public void sacuvajIzmene(){
        
        try {
            konfiguracija.store(new FileOutputStream("config/config.properties"),null);
        } catch (IOException ex) {
            ex.getStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String ucitajPodatak(String kljuc) {
        Properties p = new Properties();
        String putanja = "config/config.properties";

        try (FileInputStream fis = new FileInputStream(putanja)) {
            p.load(fis);

        } catch (IOException e) {
            System.err.println("Greška pri učitavanju konfiguracionog fajla: " + e);
            JOptionPane.showMessageDialog(null, "Greška pri učitavanju konfiguracionog fajla.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        if (kljuc.equals("port")) {
            int port;
            if (!p.getProperty("port", "N/A").equals("N/A")) {
                try {

                    port = Integer.parseInt(p.getProperty(kljuc));

                } catch (NumberFormatException numberFormatException) {
                    System.err.println("Broj porta nije dat u ispravnom formatu. Broj porta se može sastojati samo od cifara.");
                    return "N/A";

                }
                if (port < 0 || port > 65536) {
                    System.err.println("Broj porta nije dat u okviru dozvoljenog opsega (0-65535).");
                    return "N/A";
                }

            }

        }
        return p.getProperty(kljuc, "N/A");
        
    }
    
    
    
}
