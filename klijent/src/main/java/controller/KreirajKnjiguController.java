/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajKnjiguForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Knjiga;
import model.Magacin;

/**
 *
 * @author Andjela
 */
public class KreirajKnjiguController {
    
    private Knjiga pronadjena1;
    private KreirajKnjiguForma kkf;
    public KreirajKnjiguController(KreirajKnjiguForma kkf) {
        this.kkf = kkf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        kkf.kreirajKnjiguAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String naslov = kkf.getjTextFieldNaslov().getText().trim();
                if(naslov==null || naslov.isEmpty()){
                    JOptionPane.showMessageDialog(kkf, "Nije unet naslov", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String zanr = kkf.getjTextFieldZanr().getText().trim();
                if(zanr==null || zanr.isEmpty()){
                    JOptionPane.showMessageDialog(kkf, "Nije unet zanr", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int godinaIzdanja = Integer.parseInt(kkf.getjTextFieldGodinaIzdanja().getText());
                if(godinaIzdanja<1900){
                    JOptionPane.showMessageDialog(kkf, "Godina izdanja mora da bude veca ili jednaka 1900", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double cena = Double.parseDouble(kkf.getjTextFieldCena().getText());
                  if(cena<200){
                    JOptionPane.showMessageDialog(kkf, "Cena mora da bude veca od 200", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int kolicina = Integer.parseInt(kkf.getjTextFieldKolicina().getText().trim());
                  if(kolicina<=0){
                    JOptionPane.showMessageDialog(kkf, "Kolicina mora biti veca od 0", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                 Magacin magacin=null;
                if(kkf.getjComboBoxMagacin().getSelectedItem()!= null){
                magacin = (Magacin) kkf.getjComboBoxMagacin().getSelectedItem();
                }
                else{
                    JOptionPane.showMessageDialog(kkf, "Niste izabrali magacin.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                Knjiga k = new Knjiga(naslov, zanr, godinaIzdanja, cena, kolicina, magacin);
                boolean uspesno = Komunikacija.getInstance().kreirajKnjigu(k);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(kkf, "Sistem je zapamtio knjigu","Uspesno.!",JOptionPane.INFORMATION_MESSAGE);
                    kkf.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(kkf, "Sistem ne moze da zapamti knjigu","Greska!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
            
        } );
                kkf.odustaniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
              int odustani=JOptionPane.showConfirmDialog(kkf, "Da li ste sigurni da zelite da odustanete?", "", JOptionPane.YES_NO_OPTION);
                if(odustani==0){
                    kkf.dispose();
                }
            }
                    
                }
                );
                
                kkf.obrisiAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(kkf.getjTextFieldId().getText().trim());
                String naslov = kkf.getjTextFieldNaslov().getText().trim();
                String zanr = kkf.getjTextFieldZanr().getText().trim();
                int godinaIzdanja = Integer.parseInt(kkf.getjTextFieldGodinaIzdanja().getText().trim());
                double cena = Double.parseDouble(kkf.getjTextFieldCena().getText().trim());
                int kolicina = Integer.parseInt(kkf.getjTextFieldKolicina().getText().trim());
                Magacin magacin = (Magacin) kkf.getjComboBoxMagacin().getSelectedItem();
                Knjiga knjigaZaBrisanje = new Knjiga(id, naslov, zanr, godinaIzdanja, cena,kolicina, magacin);
                
                boolean uspesno = Komunikacija.getInstance().obrisiKnjigu(knjigaZaBrisanje);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(kkf, "Sistem je obrisao knjigu", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    kkf.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(kkf, "Sistem nije uspeo da obrise knjigu", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
            }
                    
                });
                
                kkf.promeniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(kkf.getjTextFieldId().getText().trim());
                String naslov = kkf.getjTextFieldNaslov().getText().trim();
                if(naslov==null || naslov.isEmpty()){
                    JOptionPane.showMessageDialog(kkf, "Nije unet naslov", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String zanr = kkf.getjTextFieldZanr().getText().trim();
                if(zanr==null || zanr.isEmpty()){
                    JOptionPane.showMessageDialog(kkf, "Nije unet zanr", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int godinaIzdanja = Integer.parseInt(kkf.getjTextFieldGodinaIzdanja().getText());
                if(godinaIzdanja<1900){
                    JOptionPane.showMessageDialog(kkf, "Godina izdanja mora da bude veca ili jednaka 1900", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double cena = Double.parseDouble(kkf.getjTextFieldCena().getText());
                  if(cena<200){
                    JOptionPane.showMessageDialog(kkf, "Cena mora da bude veca od 200", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                  
                int kolicina = Integer.parseInt(kkf.getjTextFieldKolicina().getText().trim());
                  if(kolicina<=0){
                    JOptionPane.showMessageDialog(kkf, "Kolicina mora biti veca od 0", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Magacin magacin=null;
                if(kkf.getjComboBoxMagacin().getSelectedItem()!= null){
                magacin = (Magacin) kkf.getjComboBoxMagacin().getSelectedItem();
                }
                else{
                    JOptionPane.showMessageDialog(kkf, "Niste izabrali magacin.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Knjiga knjigaZaIzmenu = new Knjiga(id,naslov, zanr, godinaIzdanja, cena,kolicina,magacin);
                boolean uspesno = Komunikacija.getInstance().promeniKnjigu(knjigaZaIzmenu);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(kkf, "Sistem je zapamtio knjigu", "Uspesno!",JOptionPane.INFORMATION_MESSAGE);
                    kkf.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(kkf, "Sistem ne moze da zapamti knjigu", "Greska!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
                    
                    
                });
                
          kkf.promeniDugmeAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                kkf.getjButtonPromeniDugme().setVisible(false);
                
                kkf.getjButtonObrisi().setVisible(true);
                kkf.getjButtonObrisi().setEnabled(false);
                
                kkf.getjButtonOdustani1().setVisible(true);
                kkf.getjButtonOdustani1().setEnabled(true);
                
                kkf.getjButtonOdustani().setVisible(false);
                kkf.getjButtonOdustani().setEnabled(false);
                
                kkf.getjButtonPromeniKnjigu().setVisible(true);
                kkf.getjButtonPromeniKnjigu().setEnabled(true);
                
                kkf.getjTextFieldCena().setEditable(true);
                kkf.getjTextFieldGodinaIzdanja().setEditable(true);
                kkf.getjTextFieldNaslov().setEditable(true);
                kkf.getjTextFieldZanr().setEditable(true);
                kkf.getjTextFieldKolicina().setEditable(true);
                kkf.getjComboBoxMagacin().setEnabled(true);
                
                
            }
      
                });
                
       kkf.odustani1AddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               otvoriFormu(pronadjena1);
            }
           
       });
        
        
        
        
        
    }

    public void otvoriFormu() {
        kkf.getjButton1().setVisible(true);
        kkf.getjButtonObrisi().setVisible(false);
        kkf.getjButtonOdustani().setVisible(true);
        kkf.getjButtonPromeniDugme().setVisible(false);
        kkf.getjButtonPromeniKnjigu().setVisible(false);
        kkf.getjLabelID().setVisible(false);
        kkf.getjTextFieldId().setVisible(false);
        
        popuniComboBox();

        
        kkf.setVisible(true);
    }

    public void otvoriFormu(Knjiga pronadjena) {
        
        pronadjena1 = pronadjena;
        Knjiga pronadjena2 = pronadjena;
        kkf.getjButtonPromeniDugme().setVisible(true);
        kkf.getjButtonPromeniDugme().setEnabled(true);
        
        kkf.getjButtonObrisi().setVisible(true);
        kkf.getjButtonObrisi().setEnabled(true);
                
        kkf.getjButtonOdustani1().setVisible(false);
        kkf.getjButtonOdustani1().setEnabled(false);
                
        kkf.getjButtonOdustani().setVisible(true);
        kkf.getjButtonOdustani().setEnabled(true);
                
        kkf.getjButtonPromeniKnjigu().setVisible(false);
        kkf.getjButtonPromeniKnjigu().setEnabled(false);
        
 
        kkf.getjButton1().setVisible(false);
        
       
        kkf.getjLabelID().setVisible(true);
        kkf.getjTextFieldId().setVisible(true);  
        kkf.getjTextFieldId().setEditable(false);
        kkf.getjTextFieldId().setText(pronadjena2.getIdKnjiga()+"");
        
        kkf.getjTextFieldCena().setText(pronadjena2.getCena()+"");
        kkf.getjTextFieldCena().setEditable(false);
        kkf.getjTextFieldGodinaIzdanja().setText(pronadjena2.getGodinaIzdanja()+"");
        kkf.getjTextFieldNaslov().setText(pronadjena2.getNaslov());
        kkf.getjTextFieldZanr().setText(pronadjena2.getZanr());
        kkf.getjTextFieldGodinaIzdanja().setEditable(false);
        kkf.getjTextFieldNaslov().setEditable(false);
        kkf.getjTextFieldZanr().setEditable(false);
        kkf.getjTextFieldKolicina().setText(pronadjena2.getKolicina()+"");
        kkf.getjTextFieldKolicina().setVisible(true);
        kkf.getjTextFieldKolicina().setEditable(false);
        kkf.getjComboBoxMagacin().setSelectedItem(pronadjena.getMagacin());
        kkf.getjComboBoxMagacin().setEnabled(false);

        
        popuniComboBox();
        
        kkf.setVisible(true);
        
    }
    
    public void popuniComboBox(){
        
        List<Magacin> magacini = Komunikacija.getInstance().vratiListuSviMagacini();
        for(Magacin m:magacini){
            kkf.getjComboBoxMagacin().addItem(m);
        }
        
    }
    
    
}
