/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziRacunForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.tabele.ModelTabeleRacun;

/**
 *
 * @author Andjela
 */
public class PretraziRacunController {
    
    private PretraziRacunForma prf;

    public PretraziRacunController(PretraziRacunForma prf) {
        this.prf = prf;
        addActionListeners();
    }

    private void addActionListeners() {
        prf.vratiListuSviRacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Racun> racuni = Komunikacija.getInstance().vratiListuSviRacun();
                if(racuni==null){
                    JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje sve racune.","Greska!", JOptionPane.WARNING_MESSAGE);
                    return;        
                }
                else{
                JOptionPane.showMessageDialog(prf, "Sistem je nasao sve racune.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                ModelTabeleRacun mtr = new ModelTabeleRacun(racuni);
                prf.getjTableRacun().setModel(mtr);
                
                if(racuni.size()>50){
                    List<Racun> lista = racuni.subList(0, Math.min(50, racuni.size()));
                    mtr.setRacuni(lista);
                    mtr.refresujTabelu();
                }
                else{
                    mtr.setRacuni(racuni);
                    mtr.refresujTabelu();
                }
         
                }
                
            }
            
        });
        
        
        prf.pretraziRacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRacun().getSelectedRow();
                if(red!=-1){
                    ModelTabeleRacun mtr = (ModelTabeleRacun) prf.getjTableRacun().getModel();
                    Racun racun = mtr.getRacuni().get(red);
                    Racun pronadjen = Komunikacija.getInstance().pretraziRacun(racun);
                    if(racun==null){
                        JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje racun.","Greska!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    else{
                        
                        JOptionPane.showMessageDialog(prf, "Sistem je nasao racun.","Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().otvoriKreirajRacunFormu(pronadjen);
                        prf.dispose();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(prf, "Niste izabrali racun.","Greska!", JOptionPane.ERROR_MESSAGE);
                        return;
                }
            }
            
        });
        
        prf.vratiListuRacunSaUslovomAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               List<Racun> racuni = new ArrayList<>();
               Knjiga knjiga=null;
               Prodavac prodavac = null;
               Kupac kupac=null;
               double iznosRacuna; 
            
                if(prf.getjCheckBoxKnjiga().isSelected()){
                knjiga = (Knjiga) prf.getjComboBoxKnjiga().getSelectedItem();
                if(knjiga!=null){
                   racuni = Komunikacija.getInstance().vratiListuRacunKnjiga(knjiga);
                   if(racuni!=null && !racuni.isEmpty()){
                           JOptionPane.showMessageDialog(prf, "Sistem je nasao racune po zadatim kriterijumima.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                       }
                   
                   else{
                       JOptionPane.showMessageDialog(prf, "Sistem ne može da nadje racune po zadatim kriterijumima.", "Greska!", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                }
                
 
                }
                
                if(prf.getjCheckBoxKupac().isSelected()){
                
               
                kupac = (Kupac) prf.getjComboBoxKupac().getSelectedItem();
                    if(kupac!=null){
                    racuni = Komunikacija.getInstance().vratiListuRacunKupac(kupac);
                    if(racuni==null || racuni.isEmpty()){
                        JOptionPane.showMessageDialog(prf, "Sistem ne može da nadje racune po zadatim kriterijumima.", "Greska!", JOptionPane.WARNING_MESSAGE);
                        return;
                           
                       }
                   
                    else{
                         JOptionPane.showMessageDialog(prf, "Sistem je nasao racune po zadatim kriterijumima.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                      
                   }  
                }
                }
                
               if(prf.getjCheckBoxProdavac().isSelected()){
                prodavac = (Prodavac) prf.getjComboBoxProdavac().getSelectedItem();
                    if(prodavac!=null){
                    racuni= Komunikacija.getInstance().vratiListuRacunProdavac(prodavac);
                    if(racuni!=null && !racuni.isEmpty() ){
                           JOptionPane.showMessageDialog(prf, "Sistem je nasao racune po zadatim kriterijumima.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                       }
                   
                   else{
                       JOptionPane.showMessageDialog(prf, "Sistem ne može da nadje racune po zadatim kriterijumima.", "Greska!", JOptionPane.WARNING_MESSAGE);
                       
                      return;
                   }
                    }
               }
               
               if(prf.getjCheckBoxRacun().isSelected()){
 
                  if(!(prf.getjTextFieldIznos().getText().trim()).isEmpty()){
                      String iznos = prf.getjTextFieldIznos().getText().trim();
                      try{
                          iznosRacuna =Double.parseDouble(prf.getjTextFieldIznos().getText().trim());
                      }
                      catch(NumberFormatException en){
                              JOptionPane.showMessageDialog(prf, "Iznos racuna moze da bude samo broj.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                              }
                      
                   
                   if(iznosRacuna>0){
                       racuni = Komunikacija.getInstance().vratiListuRacunRacun(iznosRacuna);
                       if(racuni!=null && !racuni.isEmpty()){
                           JOptionPane.showMessageDialog(prf, "Sistem je nasao racune po zadatim kriterijumima.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                           
                       }
                       else{
                       JOptionPane.showMessageDialog(prf, "Sistem ne može da nadje racune po zadatim kriterijumima.", "Greska!", JOptionPane.WARNING_MESSAGE);
                       
                       return;
                   }
                   }
                   else{
                       JOptionPane.showMessageDialog(prf, "Iznos racuna ne moze da bude nula ili negativan broj.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   }else{
                     JOptionPane.showMessageDialog(prf, "Iznos racuna mora da bude broj.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                       return;
                    }
               }
                
                
               if(knjiga==null && prodavac==null && kupac==null && (prf.getjTextFieldIznos().getText().trim())==null && (prf.getjTextFieldIznos().getText().trim()).isEmpty()){
                   JOptionPane.showMessageDialog(prf, "Niste uneli kriterijum za pretragu.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               

                
                ModelTabeleRacun mtr = (ModelTabeleRacun) prf.getjTableRacun().getModel();
                     
                if(racuni.size()>50){
                    List<Racun> lista = racuni.subList(0, Math.min(50, racuni.size()));
                    mtr.setRacuni(lista);
                    mtr.refresujTabelu();
                 }
                else{
                    mtr.setRacuni(racuni);
                    mtr.refresujTabelu();
                }
            }
        
            
        });
        
    }
    
    
    public void otvoriFormu(){
        popuniComboBoxKnjige();
        popuniComboBoxKupci();
        popuniComboBoxProdavci();
        popuniTabelu();

    
        prf.getjComboBoxKnjiga().setEnabled(false);
        prf.getjComboBoxProdavac().setEnabled(false);
        prf.getjComboBoxKupac().setEnabled(false);
        prf.getjTextFieldIznos().setEnabled(false);

        
        prf.getjCheckBoxKnjiga().addActionListener(e -> {
            if(prf.getjCheckBoxKnjiga().isSelected()){
            prf.getjCheckBoxKupac().setSelected(false);
            prf.getjCheckBoxProdavac().setSelected(false);
            prf.getjCheckBoxRacun().setSelected(false);
            prf.getjComboBoxKnjiga().setEnabled(true);
            prf.getjComboBoxKupac().setEnabled(false);
            prf.getjComboBoxProdavac().setEnabled(false);
            prf.getjTextFieldIznos().setEnabled(false);
        }
    });

        prf.getjCheckBoxKupac().addActionListener(e -> {
            if(prf.getjCheckBoxKupac().isSelected()){
                prf.getjCheckBoxKnjiga().setSelected(false);
                prf.getjCheckBoxProdavac().setSelected(false);
                prf.getjCheckBoxRacun().setSelected(false);
                prf.getjComboBoxKupac().setEnabled(true);
                prf.getjComboBoxKnjiga().setEnabled(false);
                prf.getjComboBoxProdavac().setEnabled(false);
                prf.getjTextFieldIznos().setEnabled(false);
            }
        });
        
        prf.getjCheckBoxProdavac().addActionListener(e -> {
            if(prf.getjCheckBoxProdavac().isSelected()){
                prf.getjCheckBoxKupac().setSelected(false);
                prf.getjCheckBoxKnjiga().setSelected(false);
                prf.getjCheckBoxRacun().setSelected(false);
                prf.getjComboBoxKnjiga().setEnabled(false);
                prf.getjComboBoxKupac().setEnabled(false);
                prf.getjComboBoxProdavac().setEnabled(true);
                prf.getjTextFieldIznos().setEnabled(false);
            }
        });

        prf.getjCheckBoxRacun().addActionListener(e -> {
            if(prf.getjCheckBoxRacun().isSelected()){
                prf.getjCheckBoxKnjiga().setSelected(false);
                prf.getjCheckBoxProdavac().setSelected(false);
                prf.getjCheckBoxKupac().setSelected(false);
                prf.getjComboBoxKupac().setEnabled(false);
                prf.getjComboBoxKnjiga().setEnabled(false);
                prf.getjComboBoxProdavac().setEnabled(false);
                prf.getjTextFieldIznos().setEnabled(true);
            }
        });


        prf.setVisible(true);
    }
    
    public void popuniTabelu(){
        ModelTabeleRacun mtr = new ModelTabeleRacun(new ArrayList<>());
        prf.getjTableRacun().setModel(mtr);
    }
    
    public void popuniComboBoxKnjige(){
     
        List<Knjiga> knjige = Komunikacija.getInstance().vratiListuSvihKnjiga();
        prf.getjComboBoxKnjiga().removeAllItems();
        for (Knjiga k : knjige) {
            prf.getjComboBoxKnjiga().addItem(k);
            
        }
        
    }
    
    public void popuniComboBoxKupci(){
        
        List<Kupac> kupci = Komunikacija.getInstance().vratiListuSviKupac();
        prf.getjComboBoxKupac().removeAllItems();
        for (Kupac k : kupci) {
            prf.getjComboBoxKupac().addItem(k);
            
        }

    }
    
    public void popuniComboBoxProdavci(){
        
        List<Prodavac> prodavci = Komunikacija.getInstance().vratiListuSvihProdavaca();
        prf.getjComboBoxProdavac().removeAllItems();
        for (Prodavac p : prodavci) {
            prf.getjComboBoxProdavac().addItem(p);
            
        }

    }
    
    
}
