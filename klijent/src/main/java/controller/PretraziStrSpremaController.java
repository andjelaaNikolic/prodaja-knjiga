/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziStrSpremaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.StrSprema;
import model.tabele.ModelTabeleStrSprema;

/**
 *
 * @author Andjela
 */
public class PretraziStrSpremaController {
    
    private PretraziStrSpremaForma pstr;

    public PretraziStrSpremaController(PretraziStrSpremaForma pstr) {
        this.pstr = pstr;
        addActionListeners();
    }

    private void addActionListeners() {
        pstr.vratiListuSviStrSpremaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               List<StrSprema> strSpreme = new ArrayList<>();
               strSpreme = Komunikacija.getInstance().vratiListuSviStrSprema();
               if(strSpreme==null || strSpreme.isEmpty()){
                   JOptionPane.showMessageDialog(pstr, "Sistem nije nasao strucne spreme po zadatim kriterijumima.", "Greska!",JOptionPane.ERROR_MESSAGE);
                   return;
               }
               else{
                   JOptionPane.showMessageDialog(pstr, "Sistem je nasao strucne spreme po zadatim kriterijumima.", "Uspesno!",JOptionPane.INFORMATION_MESSAGE);
                   ModelTabeleStrSprema mtss = new ModelTabeleStrSprema(strSpreme);
                   pstr.getjTable1().setModel(mtss);
               }
            }
            
        });
        
        pstr.prikaziAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pstr.getjTable1().getSelectedRow();
                if(selektovaniRed!=-1){
                    ModelTabeleStrSprema mts = (ModelTabeleStrSprema) pstr.getjTable1().getModel();
                    StrSprema ss = mts.getStrSpreme().get(selektovaniRed);
                    StrSprema pronadjena = Komunikacija.getInstance().pretraziStrSprema(ss);
                    if(pronadjena!=null){
                        JOptionPane.showMessageDialog(pstr, "Sistem je nasao strucnu spremu.","Uspesno!",JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().otvoriUbaciStrucnuSpremuFormu(pronadjena);
                        pstr.dispose();
                    }
                    else{
                    JOptionPane.showMessageDialog(pstr,"Sistem ne moze da nadje strucnu spremu.","Greska!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    
                    
            }
                 else{
                    JOptionPane.showMessageDialog(pstr,"Niste izabrali strucnu spremu.","Upozorenje!",JOptionPane.WARNING_MESSAGE);
                    return;
                
                    
                }
            }
            
        });
        
        pstr.vratiListuStrSpremaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String strSprema = pstr.getjTextFieldStepen().getText().trim();
                if(strSprema==null || strSprema.isEmpty()){
                    JOptionPane.showMessageDialog(pstr, "Morate da unesete stepen strucne spreme.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(strSprema.length()>50){
                    JOptionPane.showMessageDialog(pstr, "Stepen strucne spreme moze da sadrzi najvise 50 karaktera.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                List<StrSprema> strSpreme = Komunikacija.getInstance().vratiListuStrSprema(strSprema);
                if (strSpreme.isEmpty()) {
                    JOptionPane.showMessageDialog(pstr, "Sistem ne može da nađe strucne spreme po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pstr, "Sistem je našao strucne spreme po zadatom kriterijumu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                
                ModelTabeleStrSprema mtk = (ModelTabeleStrSprema) pstr.getjTable1().getModel();
                if (strSpreme.size() > 50) { 
                    List<StrSprema> lista = strSpreme.subList(0, Math.min(50, strSpreme.size())); 
                    mtk.setStrSpreme(lista); 
                    mtk.refresujPodatke(); } 
                else { 
                    mtk.setStrSpreme(strSpreme);
                    mtk.refresujPodatke(); 
                } 
 
                }
 
        });
        
       
    }

    public void otvoriFormu() {
        popuniTabelu();
        pstr.setVisible(true);
    }
    
    public void popuniTabelu(){
        
        ModelTabeleStrSprema mtss = new ModelTabeleStrSprema(new ArrayList<>());
        pstr.getjTable1().setModel(mtss);
        
    }
    
    
    
    
}
