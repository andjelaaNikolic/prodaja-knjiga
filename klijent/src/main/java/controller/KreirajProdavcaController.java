/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajProdavcaForma;
import forme.UbaciStrSpremuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.PrSS;
import model.Prodavac;
import model.StrSprema;
import model.tabele.ModelTabeleProdavacStrSprema;

/**
 *
 * @author Andjela
 */
public class KreirajProdavcaController {
    
   private KreirajProdavcaForma pkf;
   private Prodavac pronadjen1;
   private PrSS selektovan;

    public KreirajProdavcaController(KreirajProdavcaForma pkf) {
        this.pkf = pkf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        pkf.kreirajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                String korisnickoIme = pkf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra = pkf.getjTextFieldLozinka().getText();
                String email = pkf.getjTextFieldEmail().getText().trim();
                List<PrSS> prss = new ArrayList<>(); 
                StrSprema strSprema = (StrSprema) pkf.getjComboBox1().getSelectedItem();
                ModelTabeleProdavacStrSprema mtprss = (ModelTabeleProdavacStrSprema) pkf.getjTableStrSprema().getModel();
                prss = mtprss.getPrss();
                
                if(ime==null || ime.isEmpty()){
                   JOptionPane.showMessageDialog(pkf, "Niste uneli ime.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                   }
                if(prezime==null || prezime.isEmpty()){
                     JOptionPane.showMessageDialog(pkf, "Niste uneli prezime", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                    }
                if(email==null  || email.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Niste uneli email.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                       
                    }
                if(korisnickoIme==null || korisnickoIme.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Niste uneli korisnicko ime.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                    }
                if(sifra==null || sifra.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Niste uneli sifru.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                    }
                if (ime.trim().length() > 50 || prezime.trim().length() > 50 || email.trim().length() > 50 ||  korisnickoIme.trim().length() > 50) {

                    if (ime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Ime može sadržati maksimalno 50 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
             
                    }
                    if (prezime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Prezime može sadržati maksimalno 50 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        
                    }
                    if (email.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        
                    }
                    if (korisnickoIme.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Korisničko ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                         
                    }
                    

                    return;
                    
                }
                if(sifra.length()>10 || sifra.length()<10){
                     JOptionPane.showMessageDialog(pkf,"Sifra mora da sadrzi 10 karaktera.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime || !email.contains("@")) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(pkf, "Ime može sadržati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(pkf, "Prezime može sadržati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }

                    if (!email.contains("@")) {
                        JOptionPane.showMessageDialog(pkf, "E-mail mora da sadrži znak @.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }
                
                
                
                
                
                else{
                    Prodavac p = new Prodavac(-1,ime,prezime,korisnickoIme,sifra,email);
                    
                    
                for (PrSS ps : prss) {
                    Date datum = ps.getDatumSticanja();
                    Date datumZaBazu = new java.sql.Date(datum.getTime());
                    ps.setDatumSticanja(datumZaBazu);
                    ps.setProdavac(p);;
                }
                 p.setPrss(prss);
                 
                 boolean uspesno = Komunikacija.getInstance().kreirajProdavac(p);

                if (uspesno==false) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da zapamti prodavca!.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {

                    JOptionPane.showMessageDialog(pkf, "Sistem je zapamtio prodavca.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                    pkf.dispose();
                }

                    
                    
                    
                }
            }
            
        });
        
        pkf.dodajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                
                pkf.getjButtonSacuvajPromeneStrSprema().setVisible(true);
                String datum;
                StrSprema ss = (StrSprema) pkf.getjComboBoxStepenStrSpreme().getSelectedItem();
                String institucija = pkf.getjTextFieldInstitucija().getText().trim();
                String grad = pkf.getjTextFieldGrad().getText().trim();
                datum = pkf.getjTextFieldDatum().getText().trim();
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                Date datumSticanja = new Date();
                String danS;
                String mesecS;
                String godinaS;
                int dan;
                int mesec;
                int godina;
                
                if (ss == null || institucija == null || grad==null || datum==null || institucija.isEmpty() || grad.isEmpty() || datum.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ss == null) {
                    JOptionPane.showMessageDialog(pkf, "Niste izabrali strucnu spremu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;

                } 
                if (institucija == null) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli instituciju.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;

                } 
                if (datum==null) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum sticanja strucne spreme.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }
                
                

                try {

                    String[] datumNiz = datum.split("\\.");
                    danS = datumNiz[0];
                    mesecS = datumNiz[1];
                    godinaS = datumNiz[2];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    dan = Integer.parseInt(danS);
                    mesec = Integer.parseInt(mesecS);
                    godina = Integer.parseInt(godinaS);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pkf, "Dan, mesec i godina se mogu sastojati samo od cifara.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (dan < 1 || dan > 31 || mesec < 1 || mesec > 12) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (mesec == 4 || mesec == 6 || mesec == 9 || mesec == 11) {
                    if (dan > 30) {
                        JOptionPane.showMessageDialog(pkf, "Uneti mesec ima 30 dana.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (godina % 4 == 0) {
                    if (mesec == 2) {
                        if (dan > 29) {
                            JOptionPane.showMessageDialog(pkf, "Februar ima 29 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                } else {
                    if (mesec == 2) {
                        if (dan > 28) {
                            JOptionPane.showMessageDialog(pkf, "Februar ima 28 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                }

                try {
                    if (!datum.trim().isEmpty()) {

                        datumSticanja = format.parse(datum);
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumSticanja.after(new Date())) {
                    JOptionPane.showMessageDialog(pkf, "Datum sticanja ne sme biti datum u buducnosti.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                ModelTabeleProdavacStrSprema mtprss = (ModelTabeleProdavacStrSprema) pkf.getjTableStrSprema().getModel();
                List<PrSS> prss = mtprss.getPrss();

                PrSS ps = new PrSS(ss, null, datumSticanja, institucija, grad);
                if (prss.contains(ps)) {

                    JOptionPane.showMessageDialog(pkf, "Ova strucna sprema već postoji u listi", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                } else {
                    mtprss.dodaj(ps);
                }
                
                
                
            }
            
        });
        
        pkf.dodajStrSpremuAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UbaciStrSpremuController uss = new UbaciStrSpremuController(new UbaciStrSpremuForma());
                uss.otvoriFormu();
                //Koordinator.getInstance().otvoriUbaciStrSpremuFormu();
                popuniComboBox();
                pkf.getjComboBoxStepenStrSpreme().setSelectedItem(null);
                
            }
            
        });
        
        
        pkf.odustaniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int odgovor = JOptionPane.showConfirmDialog(pkf, "Da li ste sigurni da zelite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if(odgovor==0){
                pkf.dispose();
                }
                else{
                    return;
                }
            }
            
        });
        
        pkf.odustaniStrSpremaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int odgovor = JOptionPane.showConfirmDialog(pkf, "Da li ste sigurni da zelite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if(odgovor==0){
                pkf.dispose();
                }
                else{
                    return;
                }
            }
            
        });
        
        pkf.sacuvajIzmeneAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProdavac = Integer.parseInt(pkf.getjTextFieldId().getText().trim());
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                String korisnickoIme = pkf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra = pkf.getjTextFieldLozinka().getText();
                String email = pkf.getjTextFieldEmail().getText().trim();
                List<PrSS> prss = new ArrayList<>(); 
                StrSprema strSprema = (StrSprema) pkf.getjComboBox1().getSelectedItem();
                ModelTabeleProdavacStrSprema mtprss = (ModelTabeleProdavacStrSprema) pkf.getjTableStrSprema().getModel();
                prss = mtprss.getPrss();
                
                if(ime==null || ime.isEmpty()){
                   JOptionPane.showMessageDialog(pkf, "Niste uneli ime.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                   }
                if(prezime==null || prezime.isEmpty()){
                     JOptionPane.showMessageDialog(pkf, "Niste uneli prezime", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                    }
                if(email==null  || email.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Niste uneli email.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                       
                    }
                if(korisnickoIme==null || korisnickoIme.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Niste uneli korisnicko ime.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                    }
                if(sifra==null || sifra.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Niste uneli sifru.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                   return;
                    }
                if(sifra.length()>10 || sifra.length()<10){
                 JOptionPane.showMessageDialog(pkf,"Sifra mora da sadrzi 10 karaktera.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                 return;
               }
                if (ime.trim().length() > 50 || prezime.trim().length() > 50 || email.trim().length() > 50 ||  korisnickoIme.trim().length() > 50) {

                    if (ime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Ime može sadržati maksimalno 50 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Prezime može sadržati maksimalno 50 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (email.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (korisnickoIme.trim().length() > 50) {
                        JOptionPane.showMessageDialog(pkf, "Korisničko ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }
                
                if(sifra.length()>10 || sifra.length()<10){
                     JOptionPane.showMessageDialog(pkf,"Sifra mora da sadrzi 10 karaktera.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime || !email.contains("@")) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(pkf, "Ime može sadržati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(pkf, "Prezime može sadržati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }

                    if (!email.contains("@")) {
                        JOptionPane.showMessageDialog(pkf, "E-mail mora da sadrži znak @.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }
                            
                else{
                    Prodavac p = new Prodavac(idProdavac,ime,prezime,korisnickoIme,sifra,email);

                for (PrSS ps : prss) {
                    Date datum = ps.getDatumSticanja();
                    Date datumZaBazu = new java.sql.Date(datum.getTime());
                    ps.setDatumSticanja(datumZaBazu);
                    ps.setProdavac(p);;
                }
                 p.setPrss(prss);
                 boolean uspesno = Komunikacija.getInstance().promeniProdavac(p);
                 if(uspesno==true){
                    JOptionPane.showMessageDialog(pkf, "Sistem je zapamtio prodavca.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                    pkf.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(pkf, "Sistem nije zapamtio prodavca.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                }
                
            }
            
        });
        
        pkf.obrisiAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProdavac = Integer.parseInt(pkf.getjTextFieldId().getText().trim());
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                String korisnickoIme = pkf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra = pkf.getjTextFieldLozinka().getText().trim();
                String email = pkf.getjTextFieldEmail().getText().trim();
                List<PrSS> prss = new ArrayList<>(); 
                ModelTabeleProdavacStrSprema mtprss = (ModelTabeleProdavacStrSprema) pkf.getjTableStrSprema().getModel();
                prss = mtprss.getPrss();
                
                Prodavac prodavac = new Prodavac(idProdavac, ime, prezime, korisnickoIme, sifra, email);
                prodavac.setPrss(prss);
                boolean uspesno = Komunikacija.getInstance().obrisiProdavac(prodavac);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(pkf, "Sistem je obrisao prodavca.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                    pkf.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(pkf, "Sistem nije obrisao prodavca.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
            
        });
        
        pkf.promeniDugmeAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pkf.getjButtonPROMENIDUGME().setVisible(false);
                pkf.getjButtonPROMENIDUGME().setEnabled(false);
        
                pkf.getjButtonPrikazi().setVisible(true);
                pkf.getjButtonPrikazi().setEnabled(true);

                pkf.getjButtonSacuvajPromeneStrSprema().setVisible(false);
                pkf.getjButtonSacuvajPromeneStrSprema().setEnabled(false);

                pkf.getjButtonObrisi().setVisible(true);
                pkf.getjButtonObrisi().setEnabled(false);

                pkf.getjButtonSacuvajIzmene().setVisible(true);
                pkf.getjButtonSacuvajIzmene().setEnabled(true);
                
                pkf.getjButtonOdustaniStrSprema().setVisible(false);
                pkf.getjButtonOdustaniStrSprema().setEnabled(false);
               
                pkf.getjButtonDodajStrSpremu().setVisible(true);
                pkf.getjButtonDodajStrSpremu().setEnabled(true);
                
                pkf.getjButtonOdustani().setVisible(false);
                pkf.getjButtonOdustani().setEnabled(false);
        
                pkf.getjButtonOdustani1().setVisible(true);
                pkf.getjButtonOdustani1().setEnabled(true);
 
                pkf.getjButtonDodaj().setVisible(true);
                pkf.getjButtonDodaj().setEnabled(true);
                
               pkf.getjTextFieldIme().setEditable(true);
        
               pkf.getjTextFieldEmail().setEditable(true);
                
               pkf.getjTextFieldPrezime().setEditable(true);
                
               pkf.getjTextFieldKorisnickoIme().setEditable(true);
                
               pkf.getjTextFieldLozinka().setEditable(true);
               
               pkf.getjTableStrSprema().setEnabled(true);
               
               pkf.getjComboBoxStepenStrSpreme().setEnabled(true);
               
               pkf.getjTextFieldInstitucija().setEditable(true);
               
               pkf.getjTextFieldDatum().setEditable(true);
               
               pkf.getjTextFieldGrad().setEditable(true);
            }
            
        });
        
        pkf.prikaziStrSpremaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableStrSprema().getSelectedRow();
                if(red!=-1){
                    pkf.getjButtonSacuvajPromeneStrSprema().setVisible(true);
                    pkf.getjButtonSacuvajPromeneStrSprema().setEnabled(true);
                    pkf.getjButtonDodaj().setVisible(true);
                    pkf.getjButtonDodaj().setEnabled(false); 
                    
                    ModelTabeleProdavacStrSprema mts = (ModelTabeleProdavacStrSprema) pkf.getjTableStrSprema().getModel();
                    selektovan = mts.getPrss().get(red);
                    
                    pkf.getjTextFieldInstitucija().setText(selektovan.getInstitucija());
                    
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                    String datumNovi = format.format(selektovan.getDatumSticanja());
                    pkf.getjTextFieldDatum().setText(datumNovi);
                    pkf.getjTextFieldGrad().setText(selektovan.getGrad());
                    pkf.getjComboBoxStepenStrSpreme().setSelectedItem(selektovan.getStrSprema());
                    
                }
                else{
                    JOptionPane.showMessageDialog(pkf, "Niste izabrali strucnu spremu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
        });
        
        pkf.sacuvajPromeneStrSpremaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selektovan!=null){
                pkf.getjButtonDodaj().setEnabled(true);
                pkf.getjButtonSacuvajPromeneStrSprema().setEnabled(false);
                String datumNovi;
                StrSprema ssNova = (StrSprema) pkf.getjComboBoxStepenStrSpreme().getSelectedItem();
                String institucijaNova = pkf.getjTextFieldInstitucija().getText().trim();
                String gradNovi = pkf.getjTextFieldGrad().getText().trim();
                datumNovi = pkf.getjTextFieldDatum().getText().trim();
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                Date datumSticanja = new Date();
                String danS;
                String mesecS;
                String godinaS;
                int dan;
                int mesec;
                int godina;
                
                if (ssNova == null || institucijaNova == null || gradNovi==null || datumNovi==null || institucijaNova.isEmpty() || gradNovi.isEmpty() || datumNovi.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ssNova == null) {
                    JOptionPane.showMessageDialog(pkf, "Niste izabrali strucnu spremu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;

                } 
                if (institucijaNova == null) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli instituciju.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;

                } 
                if (datumNovi==null) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum sticanja strucne spreme.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }

                try {

                    String[] datumNiz = datumNovi.split("\\.");
                    danS = datumNiz[0];
                    mesecS = datumNiz[1];
                    godinaS = datumNiz[2];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    dan = Integer.parseInt(danS);
                    mesec = Integer.parseInt(mesecS);
                    godina = Integer.parseInt(godinaS);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pkf, "Dan, mesec i godina se mogu sastojati samo od cifara.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (dan < 1 || dan > 31 || mesec < 1 || mesec > 12) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (mesec == 4 || mesec == 6 || mesec == 9 || mesec == 11) {
                    if (dan > 30) {
                        JOptionPane.showMessageDialog(pkf, "Uneti mesec ima 30 dana.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (godina % 4 == 0) {
                    if (mesec == 2) {
                        if (dan > 29) {
                            JOptionPane.showMessageDialog(pkf, "Februar ima 29 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                } else {
                    if (mesec == 2) {
                        if (dan > 28) {
                            JOptionPane.showMessageDialog(pkf, "Februar ima 28 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                }

                try {
                    if (!datumNovi.trim().isEmpty()) {

                        datumSticanja = format.parse(datumNovi);
                        
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumSticanja.after(new Date())) {
                    JOptionPane.showMessageDialog(pkf, "Datum sticanja ne sme biti datum u buducnosti.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                java.sql.Date sqlDatum = new java.sql.Date(datumSticanja.getTime());
                
                selektovan.setDatumSticanja(datumSticanja);
                selektovan.setGrad(gradNovi);
                selektovan.setInstitucija(institucijaNova);
                selektovan.setStrSprema(ssNova);
                
                ((ModelTabeleProdavacStrSprema) pkf.getjTableStrSprema().getModel()).refresujPodatke();
                
                }
            
            else{
                    JOptionPane.showMessageDialog(pkf, "Niste izabrali strucnu spremu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        
        pkf.odustani1AddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               otvoriFormu(pronadjen1);
               
            }
            
        });
        
   
        
    }
    
    
    private void popuniComboBox() {

        List<StrSprema> strSpreme = Komunikacija.getInstance().vratiListuSviStrSprema();

        pkf.getjComboBoxStepenStrSpreme().removeAllItems();

        for (StrSprema ss : strSpreme) {
            pkf.getjComboBoxStepenStrSpreme().addItem(ss);

        }
    }

    private void popuniTabelu() {
        
        ModelTabeleProdavacStrSprema mtprss = new ModelTabeleProdavacStrSprema(new ArrayList<>());
        pkf.getjTableStrSprema().setModel(mtprss);

    }

    public void otvoriFormu() {
        
        pkf.getjButtonOdustaniStrSprema().setVisible(false);
        pkf.getjButtonOdustani1().setVisible(true);
        pkf.getjButtonObrisi().setVisible(false);
        pkf.getjButtonSacuvajIzmene().setVisible(false);
        pkf.getjButtonKreiraj().setVisible(true);
        pkf.getjButtonDodaj().setVisible(true);
        pkf.getjButtonDodajStrSpremu().setVisible(true);
        pkf.getjButtonPROMENIDUGME().setVisible(false);
        pkf.getjButtonPrikazi().setVisible(false);
        pkf.getjButtonSacuvajPromeneStrSprema().setVisible(false);
        
        pkf.getjLabelId().setVisible(false);
        pkf.getjTextFieldId().setVisible(false);
        
        
        
        
        popuniComboBox();
        popuniTabelu();
        pkf.setVisible(true);
    }

    public void otvoriFormu(Prodavac pronadjen) {
        pronadjen1 = pronadjen;
        popuniTabelu();
        
        pkf.getjButtonPROMENIDUGME().setVisible(true);
        pkf.getjButtonPROMENIDUGME().setEnabled(true);
        
        pkf.getjButtonPrikazi().setVisible(true);
        pkf.getjButtonPrikazi().setEnabled(false);
        
        pkf.getjButtonSacuvajPromeneStrSprema().setVisible(false);
        pkf.getjButtonSacuvajPromeneStrSprema().setEnabled(false);
        
        pkf.getjButtonDodaj().setVisible(false);
        pkf.getjButtonDodaj().setEnabled(false);
        
        pkf.getjButtonObrisi().setVisible(true);
        pkf.getjButtonObrisi().setEnabled(true);
        
         pkf.getjButtonOdustaniStrSprema().setVisible(false);
         pkf.getjButtonOdustaniStrSprema().setEnabled(false);
        
        pkf.getjButtonDodajStrSpremu().setVisible(true);
        pkf.getjButtonDodajStrSpremu().setEnabled(false);
        
        pkf.getjButtonOdustani().setVisible(true);
        pkf.getjButtonOdustani().setEnabled(true);
        
        pkf.getjButtonOdustani1().setVisible(false);
        pkf.getjButtonOdustani1().setEnabled(false);
 
        pkf.getjButtonSacuvajIzmene().setVisible(false);
        
        pkf.getjButtonKreiraj().setVisible(false);
        
        
        pkf.getjLabelId().setVisible(true);
        pkf.getjTextFieldId().setVisible(true);
        pkf.getjTextFieldId().setText(pronadjen.getIdProdavac()+"");
        pkf.getjTextFieldId().setEditable(false);
        
        pkf.getjTextFieldIme().setText(pronadjen.getIme());
        pkf.getjTextFieldIme().setEditable(false);
        
        pkf.getjTextFieldEmail().setEditable(false);
        pkf.getjTextFieldEmail().setText(pronadjen.getEmail());
        
        pkf.getjTextFieldPrezime().setEditable(false);
        pkf.getjTextFieldPrezime().setText(pronadjen.getPrezime());
        
        pkf.getjTextFieldKorisnickoIme().setEditable(false);
        pkf.getjTextFieldKorisnickoIme().setText(pronadjen.getKorisnickoIme());
        
        pkf.getjTextFieldLozinka().setEditable(false);
        pkf.getjTextFieldLozinka().setText(pronadjen.getSifra());
        
        pkf.getjTextFieldInstitucija().setEditable(false);
        pkf.getjTextFieldDatum().setEditable(false);
        pkf.getjTextFieldGrad().setEditable(false);
        
        pkf.getjComboBoxStepenStrSpreme().setEnabled(false);
        
        List<PrSS> prss = pronadjen.getPrss();
        ModelTabeleProdavacStrSprema mtprss = (ModelTabeleProdavacStrSprema) pkf.getjTableStrSprema().getModel();
        mtprss.setPrss(prss);
        pkf.getjTableStrSprema().setEnabled(false);
        popuniComboBox();
        pkf.setVisible(true);
    }
    
 

   
  
    
    
    
    
}
