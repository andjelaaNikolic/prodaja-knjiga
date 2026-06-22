/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajMestoForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Mesto;

/**
 *
 * @author Ljilja
 */
public class KreirajMestoController {
    private Mesto m;
    private Mesto pronadjeno;
    private KreirajMestoForma kmf;

    public KreirajMestoController(KreirajMestoForma kmf) {
        this.kmf = kmf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        kmf.kreirajMestoAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = kmf.getjTextFieldNaziv().getText();
                if(naziv==null || naziv.isEmpty()){
                    JOptionPane.showMessageDialog(kmf, "Niste unesli naziv mesta.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(naziv.length()>50){
                    JOptionPane.showMessageDialog(kmf, "Naziv moze da sadrzi maksimalno 50 slova.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                boolean ispravniNaziv = true;
                for (int i = 0; i < naziv.length(); i++) {
                    if (!Character.isLetter(naziv.charAt(i))) {
                        ispravniNaziv = false;
                    }
                }
                if(ispravniNaziv==false){
                    JOptionPane.showMessageDialog(kmf, "Naziv mesta moze sadrzati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }    

                
                m = new Mesto(naziv);
                boolean uspesno = Komunikacija.getInstance().kreirajMesto(m);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(kmf, "Sistem je zapamtio mesto.","Uspesno!",JOptionPane.INFORMATION_MESSAGE);
                    kmf.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(kmf, "Sistem ne moze da zapamti mesto.","Greska!",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        
        kmf.odustaniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int odustajanje = JOptionPane.showConfirmDialog(kmf, "Da li ste sigurni da zelite da odustanete?","", JOptionPane.YES_NO_OPTION);
                if(odustajanje==0){
                    kmf.dispose();
                }
            }
            
        });
        
        kmf.promeniMestoAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(kmf.getjTextFieldId().getText());
                System.out.println("INT MESTA KOJE SE MENJA U KONTROLERU: "+id);
                String naziv = kmf.getjTextFieldNaziv().getText().strip();
                
                if(naziv==null || naziv.isEmpty()){
                    JOptionPane.showMessageDialog(kmf, "Niste unesli naziv mesta.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(naziv.length()>50){
                    JOptionPane.showMessageDialog(kmf, "Naziv moze da sadrzi maksimalno 50 slova.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                boolean ispravniNaziv = true;
                for (int i = 0; i < naziv.length(); i++) {
                    if (!Character.isLetter(naziv.charAt(i))) {
                        ispravniNaziv = false;
                    }
                }
                    
                if(ispravniNaziv==false){
                    JOptionPane.showMessageDialog(kmf, "Naziv može sadržati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                } 
                
                
                
                else{
                    Mesto mestoPromena = new Mesto(id,naziv);
                    boolean uspesno = Komunikacija.getInstance().promeniMesto(mestoPromena);
                    if(uspesno==true){
                        JOptionPane.showMessageDialog(kmf, "Sistem je zapamtio mesto","Uspesno!!",JOptionPane.INFORMATION_MESSAGE);
                        kmf.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(kmf, "Sistem ne moze da zapamti mesto","Greska!",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
            }
            
        });
        
        kmf.obrisiAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(kmf.getjTextFieldId().getText());
                System.out.println("INT MESTA KOJE SE BRISE U KONTROLERU: "+id);
                String naziv = kmf.getjTextFieldNaziv().getText().strip();
                if(naziv==null || naziv.isEmpty()){
                    JOptionPane.showMessageDialog(kmf, "Unesite naziv","GRESKA!",JOptionPane.ERROR_MESSAGE);
                    
                }
                else{
                    Mesto mestoBrisanje = new Mesto(id,naziv);
                    boolean uspesno = Komunikacija.getInstance().obrisiMesto(mestoBrisanje);
                    if(uspesno==true){
                        JOptionPane.showMessageDialog(kmf, "Sistem je obrisao mesto","USPESNO!",JOptionPane.INFORMATION_MESSAGE);
                        kmf.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(kmf, "Sistem ne moze da obrise mesto","Greska!",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
            }
            
        });
        
        kmf.promeniDugmeAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               kmf.getjButtonPromeniDugme().setVisible(false);
               
               kmf.getjButtonOdustani().setVisible(false);
               kmf.getjButtonOdustani().setEnabled(false);
               
               kmf.getjButtonOdustani1().setEnabled(true);
               kmf.getjButtonOdustani1().setVisible(true);
               
               kmf.getjButtonObrisi().setVisible(true);
               kmf.getjButtonObrisi().setEnabled(false);
        
               kmf.getjButtonPromeniMesto().setVisible(true);
              
               kmf.getjTextFieldNaziv().setEditable(true);
            }
            
        });
        
        kmf.odustani1AddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               otvoriFormu(pronadjeno);
            }
            
        });
        
        
    }

    public void otvoriFormu() {
        kmf.getjLabelId().setVisible(false);
        kmf.getjTextFieldId().setVisible(false);
        kmf.getjButtonPromeniMesto().setVisible(false);
        kmf.getjButtonObrisi().setVisible(false);
        kmf.getjButtonKreiraj().setVisible(true);
        kmf.getjButtonPromeniDugme().setVisible(false);
        kmf.getjButtonOdustani1().setVisible(false);
        kmf.getjButtonOdustani().setVisible(true);
        kmf.setVisible(true);
    }

    public void otvoriFormu(Mesto mesto) {
        
        pronadjeno = mesto;
        kmf.getjButtonPromeniDugme().setVisible(true);
        kmf.getjButtonObrisi().setVisible(true);
        kmf.getjButtonObrisi().setEnabled(true);
        
        kmf.getjButtonPromeniMesto().setVisible(false);
        kmf.getjButtonKreiraj().setVisible(false);
        
        kmf.getjButtonOdustani().setVisible(true);
        kmf.getjButtonOdustani().setEnabled(true);
         
        kmf.getjButtonOdustani1().setVisible(false);
        
        kmf.getjLabelId().setVisible(true);
        kmf.getjTextFieldId().setVisible(true);
        kmf.getjTextFieldId().setText(mesto.getIdMesto()+"");
        kmf.getjTextFieldId().setEditable(false);
        kmf.getjTextFieldNaziv().setText(mesto.getNazivMesta());
        kmf.getjTextFieldNaziv().setEditable(false);
        
        kmf.setVisible(true);
        
    }
    
    
    
}
