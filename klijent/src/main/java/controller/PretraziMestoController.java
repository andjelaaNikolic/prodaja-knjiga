/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.PretraziMestoForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Mesto;
import model.tabele.ModelTabeleMesto;

/**
 *
 * @author Andjela
 */
public class PretraziMestoController {
    
    Mesto m;
    
    List<Mesto> mesta;
    private PretraziMestoForma pmf;

    public PretraziMestoController(PretraziMestoForma pmf) {
        this.pmf = pmf;
        addActionListeners();
    }

    private void addActionListeners() {
        pmf.vratiListuSviMestoAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mesta = Komunikacija.getInstance().vratiListuSviMesto();
                ModelTabeleMesto mtm = new ModelTabeleMesto(mesta);
                pmf.getjTable1().setModel(mtm);
            }
            
        });
        
        pmf.prikaziMestoAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getjTable1().getSelectedRow();
                if(red!=-1){
                    ModelTabeleMesto mtm = (ModelTabeleMesto) pmf.getjTable1().getModel();
                    m = mtm.getMesta().get(red);
                    Mesto mesto = Komunikacija.getInstance().pretraziMesto(m);
                    if(mesto!=null){
                     JOptionPane.showMessageDialog(pmf,"Sistem je nasao mesto.","Uspesno!",JOptionPane.INFORMATION_MESSAGE);

                        pmf.dispose();
                        Koordinator.getInstance().otvoriKreirajMestoFormu(mesto);
                    }
                     else{
                    JOptionPane.showMessageDialog(pmf,"Sistem ne moze da pronadje mesto.","GRESKA!",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                     }
                else{
                         JOptionPane.showMessageDialog(pmf,"Morate da obelezite mesto.","GRESKA!",JOptionPane.ERROR_MESSAGE);
                         return;
                        }    
                        
            }
            
        });
        
        pmf.pretraziMestoAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Mesto> mesta = new ArrayList<>();
                String nazivMesta=null;

                try {

                    if (!pmf.getjTextFieldMesto().getText().trim().isEmpty()) {
                        nazivMesta = pmf.getjTextFieldMesto().getText().trim();
                    }


                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pmf, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ((nazivMesta == null || nazivMesta.trim().isEmpty())) {
                    JOptionPane.showMessageDialog(pmf, "Morate uneti kriterijum za pretraživanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (nazivMesta!= null) {
                    Mesto mestoK = new Mesto(nazivMesta);

                    mesta= Komunikacija.getInstance().vratiListuMesto(mestoK);
                        if (mesta.isEmpty()) {
                    JOptionPane.showMessageDialog(pmf, "Sistem ne može da nađe mesta po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pmf, "Sistem je našao mesta po zadatom kriterijumu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleMesto mtm = new ModelTabeleMesto(mesta);
                    pmf.getjTable1().setModel(mtm);

                }

 
                
                    
                }

               
            }
            
            
        });
        
        
        
        
        
        
    }

    public void otvoriFormu() {
        ModelTabeleMesto mtm = new ModelTabeleMesto(new ArrayList<>());
        pmf.getjTable1().setModel(mtm);
        pmf.setVisible(true);
    }
    
    
    
    
}
