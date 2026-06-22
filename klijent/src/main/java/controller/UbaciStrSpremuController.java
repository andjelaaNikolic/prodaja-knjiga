package controller;

import forme.UbaciStrSpremuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.StrSprema;

/**
 *
 * @author Andjela
 */
public class UbaciStrSpremuController {
    
    private UbaciStrSpremuForma ussf;
    private StrSprema pronadjena1;
    public UbaciStrSpremuController(UbaciStrSpremuForma ussf) {
        this.ussf = ussf;
        addActionListeners();
        
    }



    private void addActionListeners() {
        ussf.ubaciAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String stepen = ussf.getjTextFieldStepen().getText().trim();
                if(stepen==null || stepen.isEmpty()){
                    JOptionPane.showMessageDialog(ussf, "Niste uneli podatke.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int brojac=0;
              for (int i = 0; i < stepen.length(); i++) {
                if (Character.isDigit(stepen.charAt(i))) {
                    brojac++;
                }
            }

            if (brojac > 1) {
                JOptionPane.showMessageDialog(ussf, "Stepen stručne spreme može da sadrži najviše jednu cifru.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                return;
            }
                if (stepen.length() > 50){
                    JOptionPane.showMessageDialog(ussf, "Stepen strucne spreme moze da sadrzi najvise 50 karaktera.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                    
                }
                int odgovor = JOptionPane.showConfirmDialog(ussf, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                StrSprema ss = new StrSprema();
                ss.setStepen(stepen);
                boolean uspesno = Komunikacija.getInstance().ubaciStrSpremu(ss);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(ussf, "Sistem je zapamtio strucnu spremu.", "Uspešno!", JOptionPane.INFORMATION_MESSAGE);
                        ussf.dispose();
                    
                }
                else{
                    JOptionPane.showMessageDialog(ussf, "Sistem ne može da zapamti strucnu spremu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                return;
                }
                }
                
                
            }
            
        });
        
        ussf.odustaniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int odgovor = JOptionPane.showConfirmDialog(ussf, "Da li ste sigurni da zelite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if(odgovor==0){
                ussf.dispose();
                }
                else{
                    return;
                }
            }
           
            
        });
        
        ussf.obrisiAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(ussf.getjTextFieldId().getText().trim());
                String stepen = ussf.getjTextFieldStepen().getText().trim();
                StrSprema zaBrisanje = new StrSprema(id,stepen);
                
                int odgovor = JOptionPane.showConfirmDialog(ussf, "Da li sigurno zelite da obrisete strucnu spremu?", "Potvrda",JOptionPane.YES_NO_OPTION );
                if(odgovor==0){
                    boolean uspesno = Komunikacija.getInstance().obrisiStrSpremu(zaBrisanje);
                        if(uspesno==true){
                            JOptionPane.showMessageDialog(ussf, "Sistem je obrisao strucnu spremu.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                            ussf.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(ussf, "Sistem nije obrisao strucnu spremu.", "Greska!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                }
                else{
                    return;
                }
                
            }
            
        });
        
        
        ussf.sacuvajPromeneAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(ussf.getjTextFieldId().getText().trim());
                String stepen = ussf.getjTextFieldStepen().getText().trim();
                if(stepen==null || stepen.isEmpty()){
                    JOptionPane.showMessageDialog(ussf, "Morate da unesete stepen strucne spreme.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(stepen.length()>50){
                    JOptionPane.showMessageDialog(ussf, "Stepen strucne spreme moze da sadrzi najvise 50 karaktera.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int odgovor = JOptionPane.showConfirmDialog(ussf, "Da li sigurno zelite da sacuvate promene?", "Potvrda",JOptionPane.YES_NO_OPTION );
                StrSprema zaIzmenu = new StrSprema(id,stepen);
                if(odgovor==0){
                    boolean uspesno = Komunikacija.getInstance().promeniStrSpremu(zaIzmenu);
                        if(uspesno==true){
                            JOptionPane.showMessageDialog(ussf, "Sistem je zapamtio strucnu spremu.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                            ussf.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(ussf, "Sistem ne moze da zapamti strucnu spremu.", "Greska!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                }
                else{
                    return;
                }
             
            }
            
        });
        
        ussf.promeniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               ussf.getjButtonPromeni().setVisible(false);
                
               ussf.getjButtonOdustani().setVisible(false);
               ussf.getjButtonOdustani1().setEnabled(true);
        
              ussf.getjButtonOdustani1().setVisible(true);
               
               ussf.getjButtonObrisi().setVisible(true);
               ussf.getjButtonObrisi().setEnabled(false);
               
               ussf.getjButtonSacuvajPromene().setVisible(true);
               ussf.getjButtonSacuvajPromene().setEnabled(true);
               
               ussf.getjTextFieldStepen().setEditable(true);
               
            }
            
        });
        
        ussf.odustani1AddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
               otvoriFormu(pronadjena1);
            }
            
        });
        
        
    }
    
    
    public void otvoriFormu() {
        ussf.getjButtonObrisi().setVisible(false);
        ussf.getjButtonSacuvajPromene().setVisible(false);
        ussf.getjButtonUbaci().setVisible(true);
        ussf.getjTextFieldId().setVisible(false);
        ussf.getjLabelId().setVisible(false);
        ussf.getjButtonPromeni().setVisible(false);
        ussf.getjButtonOdustani1().setVisible(false);
        ussf.setVisible(true);
       
    }

    

    public void otvoriFormu(StrSprema pronadjena) {
        
        pronadjena1 = pronadjena;
        
        ussf.getjButtonPromeni().setVisible(true);
        ussf.getjButtonPromeni().setEnabled(true);
        
        ussf.getjButtonOdustani().setVisible(true);
        ussf.getjButtonOdustani().setEnabled(true);
        
        ussf.getjButtonOdustani1().setVisible(false);
        
        ussf.getjButtonObrisi().setVisible(true);
        ussf.getjButtonObrisi().setEnabled(true);
        
        ussf.getjButtonSacuvajPromene().setVisible(false);
        ussf.getjButtonUbaci().setVisible(false);
        ussf.getjTextFieldId().setVisible(true);
        ussf.getjLabelId().setVisible(true);
        ussf.getjTextFieldId().setText(pronadjena.getIdStrucnaSprema()+"");
        ussf.getjTextFieldId().setEditable(false);
        ussf.getjTextFieldStepen().setText(pronadjena.getStepen());
        
        ussf.getjTextFieldStepen().setEditable(false);
        ussf.setVisible(true);        
    }
    
}
