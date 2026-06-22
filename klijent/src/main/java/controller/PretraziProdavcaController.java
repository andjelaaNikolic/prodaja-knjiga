/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziProdavcaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.PrSS;
import model.Prodavac;
import model.StrSprema;
import model.tabele.ModelTabeleProdavac;

/**
 *
 * @author Andjela
 */
public class PretraziProdavcaController {
    
    List<Prodavac> prodavci;
    private PretraziProdavcaForma ppf;

    public PretraziProdavcaController(PretraziProdavcaForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }

    private void addActionListeners() {
        ppf.vratiListuSvihProdavacaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               prodavci = Komunikacija.getInstance().vratiListuSvihProdavaca();
               if(prodavci==null || prodavci.isEmpty()){
                   JOptionPane.showMessageDialog(ppf, "Sistem nije nasao prodavce po zadatim kriterijumima.","Greska!",JOptionPane.ERROR_MESSAGE);
                   return;
               }
               else{
                   JOptionPane.showMessageDialog(ppf, "Sistem je nasao prodavce po zadatim kriterijumima.","Uspesno!",JOptionPane.INFORMATION_MESSAGE);
                   ModelTabeleProdavac mtp =new ModelTabeleProdavac(prodavci);
                   ppf.getjTableProdavci().setModel(mtp);
               }
            }
        
        }
        );
        
        ppf.vratiListuProdavacAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Prodavac> prodavci = new ArrayList<>();
                String prodavac = null;//ppf.getjTextFieldIme().getText().trim();
                StrSprema strSprema = (StrSprema) ppf.getjComboBoxStrSprema().getSelectedItem();
                
                
                
                if(prodavac==null && strSprema==null){
                    JOptionPane.showMessageDialog(ppf, "Niste uneli kriterijum za pretragu prodavaca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(ppf.getjCheckBoxImeIPrezime().isSelected()){
                    
                    if(!ppf.getjTextFieldIme().getText().trim().isEmpty()){
                    prodavac = ppf.getjTextFieldIme().getText().trim();
                }
                    
                if(prodavac!=null && prodavac.strip().split(" ").length>=3){
                    JOptionPane.showMessageDialog(ppf, "Možete uneti samo ime i prezime za prodavca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                boolean ispravanProdavac = true;
                if(prodavac!=null){
                for (String imePrezime : prodavac.split(" ")) {
                    for(int i=0; i<imePrezime.length();i++){
                        if(!Character.isLetter(imePrezime.charAt(i))){
                            ispravanProdavac=false;
                        }
                        
                    }
                }
                
                if(ispravanProdavac!=true){
                    JOptionPane.showMessageDialog(ppf, "Ime i prezime ne mogu da cine samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                }
                
                if(prodavac!=null){
                    prodavci=null;
                    prodavci = Komunikacija.getInstance().vratiListuProdavacProdavac(prodavac);
                    
                    for (Prodavac p : prodavci) {
                        if(!prodavci.contains(p)){
                            prodavci.add(p);
                        }
                        
                    }
                }
                }
                
                if(ppf.getjCheckBoxStrSprema().isSelected()){
                
                if(strSprema!=null){
                    prodavci=null;
                    prodavci = Komunikacija.getInstance().vratiListuProdavacStrSprema(strSprema);
                    for (Prodavac p : prodavci) {
                        if(!prodavci.contains(p)){
                            prodavci.add(p);
                        }
                        
                    }
                }
                }
                
                if(prodavci.isEmpty()){
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nadje prodavce po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else{
                     JOptionPane.showMessageDialog(ppf, "Sistem je nasao prodavce po zadatim kriterijumima.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                }
                
                ModelTabeleProdavac mtp = (ModelTabeleProdavac) ppf.getjTableProdavci().getModel();
                
                 if (prodavci.size() > 50) {
                    List<Prodavac> lista = prodavci.subList(0, Math.min(50, prodavci.size()));
                    mtp.setProdavci(lista);
                    mtp.refresujPodatke();
                } else {
                    mtp.setProdavci(prodavci);
                    mtp.refresujPodatke();
                }
                
  
            }
            
        });
        
        ppf.prikaziProdavacAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getjTableProdavci().getSelectedRow();
                if(red!=-1){
                    ModelTabeleProdavac mtp = (ModelTabeleProdavac) ppf.getjTableProdavci().getModel();
                    Prodavac zaPretragu = mtp.getProdavci().get(red);
                    Prodavac pronadjen = Komunikacija.getInstance().pretraziProdavac(zaPretragu);
                    if(pronadjen==null){
                        JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje prodavca.", "Greska!",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        JOptionPane.showMessageDialog(ppf, "Sistem je nasao prodavca.","Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().otvoriKreirajProdavcaFormu(pronadjen);
                        ppf.dispose();
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(ppf, "Niste izabrali prodavca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
        });
        
        
        
    }

    


    public void otvoriFormu() {
        popuniComboBox();
        popuniTabelu();
        
        ppf.getjTextFieldIme().setEnabled(false);
        ppf.getjComboBoxStrSprema().setEnabled(false);
        
        ppf.getjCheckBoxImeIPrezime().addActionListener(e ->{
            if(ppf.getjCheckBoxImeIPrezime().isSelected()){
                 ppf.getjTextFieldIme().setEnabled(true);
                 ppf.getjCheckBoxStrSprema().setSelected(false);
                 ppf.getjComboBoxStrSprema().setEnabled(false);
            }
        });
        
        ppf.getjCheckBoxStrSprema().addActionListener(e ->{
            if(ppf.getjCheckBoxStrSprema().isSelected()){
                ppf.getjCheckBoxImeIPrezime().setSelected(false);
                ppf.getjTextFieldIme().setEnabled(false);
                ppf.getjComboBoxStrSprema().setEnabled(true);
            }
        });
        
        
        
        
        
        ppf.setVisible(true);
        
        
        
    }
    
    public void popuniTabelu(){
        ModelTabeleProdavac mtp = new ModelTabeleProdavac(new ArrayList<>());
        ppf.getjTableProdavci().setModel(mtp);
        mtp.refresujPodatke();
    }
    
    public void popuniComboBox(){
        
        List<StrSprema> ss = Komunikacija.getInstance().vratiListuSviStrSprema();
        for (StrSprema s : ss) {
            ppf.getjComboBoxStrSprema().addItem(s);
            
        }
        
    }
    
    
}
