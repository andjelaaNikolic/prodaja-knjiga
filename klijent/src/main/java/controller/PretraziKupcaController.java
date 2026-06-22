/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziKupcaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Kupac;
import model.Mesto;
import model.tabele.ModelTabeleKupac;

/**
 *
 * @author Andjela
 */
public class PretraziKupcaController {
    
    private List<Kupac> kupci;
    private PretraziKupcaForma pkf;

    public PretraziKupcaController(PretraziKupcaForma pkf) {
        this.pkf = pkf;
        addActionListeners();
    }

    private void addActionListeners() {
        pkf.vratiListuSvihKupacaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                kupci = Komunikacija.getInstance().vratiListuSviKupac();
                if(kupci==null || kupci.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nadje sve kupce.", "Greska!", JOptionPane.WARNING_MESSAGE);
                  //pkf.dispose();
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao sve kupce.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                           ModelTabeleKupac mtk = new ModelTabeleKupac(kupci);
                           pkf.getjTableKupac().setModel(mtk); 
                }
                
            }
            
        });
        pkf.pretraziKupcaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pkf.getjTableKupac().getSelectedRow();
                if(selektovaniRed!=-1){
                    ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupac().getModel();
                    Kupac k = mtk.getKupci().get(selektovaniRed);
                    Kupac pronadjen = Komunikacija.getInstance().pretraziKupca(k);
                    if(pronadjen!=null){
                        
                        JOptionPane.showMessageDialog(pkf, "Sistem je nasao kupca","Uspesno!",JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().otvoriKreirajKupcaFormu(pronadjen);
                        pkf.dispose();
                    }
                    else{
                    JOptionPane.showMessageDialog(pkf,"Sistem ne moze da nadje kupca.","Greska!",JOptionPane.WARNING_MESSAGE);
                    //pkf.dispose();
                    return;
                }
                    
                    
            }
                 else{
                    JOptionPane.showMessageDialog(pkf,"Niste izabrali kupca.","Greska!",JOptionPane.WARNING_MESSAGE);
                    return;
                
                    
                }
            }
            
        });
        
        pkf.vratiListuSaUslovomKupcaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                List<Kupac> kupci = new ArrayList<>();
                String kupac = null;
                Mesto mesto = null;

               

                if(pkf.getjCheckBoxImeIPrezime().isSelected()){
                    
                    if (!pkf.getjTextFieldImeIPrezime().getText().trim().isEmpty()) {
                    kupac = pkf.getjTextFieldImeIPrezime().getText().trim();
                    }
                
                
                    if (kupac != null && kupac.strip().split(" ").length >= 3) {
                    JOptionPane.showMessageDialog(pkf, "Možete uneti samo ime i prezime za kupca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                    }

                    if (kupac!= null) {

                    List<Kupac> kupciImePrezime = Komunikacija.getInstance().vratiListuKupacKupac(kupac);

                    for (Kupac k : kupciImePrezime) {
                        if (!kupci.contains(k)) {
                            kupci.add(k);
                        }

                    }
                    }
                }
                
                if(pkf.getjCheckBoxMesto().isSelected()){

                     mesto = (Mesto) pkf.getjComboBoxMesto().getSelectedItem();
                    
                     if (mesto != null) {

                        List<Kupac> listaMesto = Komunikacija.getInstance().vratiListuKupacMesto(mesto);
                        for (Kupac k : listaMesto) {
                            if (!kupci.contains(k)) {
                            kupci.add(k);
                            }

                        }
                    }
                }
                
                 if (kupac == null && mesto == null) {
                    JOptionPane.showMessageDialog(pkf, "Niste uneli kriterijum za pretragu kupaca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                 
             
                if (kupci.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                  // pkf.dispose();
                     return;
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je našao kupce po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupac().getModel();

                if (kupci.size() > 50) {
                    List<Kupac> lista = kupci.subList(0, Math.min(50, kupci.size()));
                    mtk.setKupci(lista);
                    mtk.refresujPodatke();
                } else {
                    mtk.setKupci(kupci);
                    mtk.refresujPodatke();
                }
                
                
                

            }
        });
        
        
        
        
       
    }
    
   
    

    public void otvoriFormu() {
        popuniComboBox();
        popuniTabelu();
        
        
        pkf.getjTextFieldImeIPrezime().setEditable(false);
        pkf.getjComboBoxMesto().setEnabled(false);
        
        
        
        pkf.getjCheckBoxImeIPrezime().addActionListener(e ->{
            if(pkf.getjCheckBoxImeIPrezime().isSelected()){
                pkf.getjTextFieldImeIPrezime().setEditable(true);
                pkf.getjComboBoxMesto().setEnabled(false);
                pkf.getjCheckBoxMesto().setSelected(false);
               
            }
        });
        
        pkf.getjCheckBoxMesto().addActionListener(e ->{
                pkf.getjTextFieldImeIPrezime().setEditable(false);
                pkf.getjComboBoxMesto().setEnabled(true);
                pkf.getjCheckBoxImeIPrezime().setSelected(false);
                
        });
        
        
        pkf.setVisible(true);
    }
    
    public void popuniTabelu(){
        ModelTabeleKupac mtk = new ModelTabeleKupac(new ArrayList<>());
        pkf.getjTableKupac().setModel(mtk);
    }
    
    public void popuniComboBox(){
        ModelTabeleKupac mtk = new ModelTabeleKupac(new ArrayList<>());
        pkf.getjTableKupac().setModel(mtk);
        List<Mesto> mesta = Komunikacija.getInstance().vratiListuSviMesto();
        for(Mesto m:mesta){
            pkf.getjComboBoxMesto().addItem(m);
        }
        
    }
    
    
}
