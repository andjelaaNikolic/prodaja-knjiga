/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziKnjigeForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Knjiga;
import model.tabele.ModelTabeleKnjige;

/**
 *
 * @author Andjela
 */
public class PretraziKnjigeController {
    
    private PretraziKnjigeForma pkf;
    List<Knjiga> knjige;
   
    public PretraziKnjigeController(PretraziKnjigeForma pkf) {
        this.pkf = pkf;
        addActionListeners();
    }
    
    
    

    public void otvoriFormu() {
       popuniTabelu();
       pkf.setVisible(true);
       
    }

    private void addActionListeners() {
    
        pkf.vratiListuSvihKnjigaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               knjige = komunikacija.Komunikacija.getInstance().vratiListuSvihKnjiga();
               if(knjige==null || knjige.isEmpty()){
                   JOptionPane.showMessageDialog(pkf, "Sistem nije nasao sve knjige", "Greska!", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               else{
                   JOptionPane.showMessageDialog(pkf, "Sistem je nasao sve knjige", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                  ModelTabeleKnjige mtk = new ModelTabeleKnjige(knjige);
                  pkf.getjTableKnjige().setModel(mtk);
               }
               
                 //ModelTabeleKnjige mtk = new ModelTabeleKnjige(knjige);
                 //pkf.getjTableKnjige().setModel(mtk);
            }
  
        }
        );
        
        
        pkf.prikaziKnjiguAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Knjiga pronadjena;
            int red = pkf.getjTableKnjige().getSelectedRow();
                if(red!=-1){
                    ModelTabeleKnjige mtk = (ModelTabeleKnjige) pkf.getjTableKnjige().getModel();
                    Knjiga k = mtk.getKnjige().get(red);
                    pronadjena = Komunikacija.getInstance().pretraziKnjigu(k);
                    if(pronadjena!=null){
                        JOptionPane.showMessageDialog(pkf, "Sistem je nasao knjigu","Uspesno!",JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().otvoriKreirajKnjiguFormu(pronadjena);
                        pkf.dispose();
                    }
                    else{
                    JOptionPane.showMessageDialog(pkf,"Sistem ne moze da nadje knjigu.","Greska!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    
                    
            }
                 else{
                    JOptionPane.showMessageDialog(pkf,"Niste izabrali knjigu.","Upoznorenje!",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        
        pkf.vratiListuKnjigaKnjigaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String knjiga=pkf.getjTextFieldNaslov().getText().trim();
                if(knjiga==null || knjiga.isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Morate uneti naslov za pretragu", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(knjiga.length()>50){
                    JOptionPane.showMessageDialog(pkf, "Naslov ne moze da sadrzi vise od 50 slova", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                boolean ispravanNaziv=true;
                for(int i=0;i<knjiga.length();i++){
                    if(!Character.isLetter(knjiga.charAt(i))){
                        ispravanNaziv=false;
                    }
                }
                if(ispravanNaziv==false){
                    JOptionPane.showMessageDialog(pkf, "Naslov moze da sadrzi samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                knjige = Komunikacija.getInstance().vratiListuKnjiga(knjiga);
                
                if (knjige.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nadje knjige po zadatim kriterijumima.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao knjige po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                
                ModelTabeleKnjige mtk = (ModelTabeleKnjige) pkf.getjTableKnjige().getModel();
                if (knjige.size() > 50) { 
                    List<Knjiga> lista = knjige.subList(0, Math.min(50, knjige.size())); 
                    mtk.setKnjige(lista); 
                    mtk.refresujPodatke(); } 
                else { mtk.setKnjige(knjige);
                mtk.refresujPodatke(); 
                } 
            }
                

                
                
 
                
            
            
        });
        
    
        
    }
    
   private void popuniTabelu() {
      ModelTabeleKnjige mtk = new ModelTabeleKnjige(new ArrayList<>());
       pkf.getjTableKnjige().setModel(mtk);
    }
    
  
 
  
    
}
