/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajKupcaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kupac;
import model.Mesto;

/**
 *
 * @author Andjela
 */
public class KreirajKupacController {
    Kupac k;
    private KreirajKupcaForma kkf;

    public KreirajKupacController(KreirajKupcaForma kkf) {
        this.kkf = kkf;
        addActionListeners();
    }

    private void addActionListeners() {
        kkf.kreirajKupcaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = kkf.getjTextFieldIme().getText().trim();
                String prezime = kkf.getjTextFieldPrezime().getText().trim();
                String brojTelefona = kkf.getjTextFieldBrojTelefona().getText().trim();
                Mesto mesto=null;
                if(kkf.getjComboBox1().getSelectedItem()!= null){
                mesto = (Mesto) kkf.getjComboBox1().getSelectedItem();
                }
                else{
                    JOptionPane.showMessageDialog(kkf, "Niste izabrali mesto.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                 if (ime.trim().isEmpty() || prezime.trim().isEmpty()  || brojTelefona.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kkf, "Niste uneli sve podatke.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 50 || prezime.trim().length() > 50 || brojTelefona.trim().length()>11 || brojTelefona.trim().length()<10) {

                    if (ime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(kkf, "Ime moze sadrzati maksimalno 50 slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(kkf, "Prezime moze sadrzati maksimalno 50 slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }
                    
                    if (brojTelefona.trim().length()>10 || brojTelefona.trim().length()<10) {
                        JOptionPane.showMessageDialog(kkf, "Broj telefona mora da sadrzi 10 cifara.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }

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
                
                boolean ispravanBrojTelefona = true;
                for (int i = 0; i < brojTelefona.length(); i++) {
                    if (!Character.isDigit(brojTelefona.charAt(i))) {
                        ispravanBrojTelefona = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime || !ispravanBrojTelefona) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(kkf, "Ime moze sadrzati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(kkf, "Prezime moze sadrzati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }

                    if (!ispravanBrojTelefona) {
                        JOptionPane.showMessageDialog(kkf, "Broj telefona moze da sadrzi samo cifre.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }
                
                
                Kupac kupac = new Kupac(ime, prezime, brojTelefona, mesto);
                
                
                int odgovor = JOptionPane.showConfirmDialog(kkf, "Da li ste sigurni da zelite da sacuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().kreirajKupca(kupac);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kkf, "Sistem ne moze da zapamti kupca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                       
                        return;
                    } else {

                        JOptionPane.showMessageDialog(kkf, "Sistem je zapamtio kupca.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                        kkf.dispose();
                    }
                }
     
            }
            
        });
        
        kkf.promeniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(kkf.getjTextFieldId().getText().trim());
                String ime = kkf.getjTextFieldIme().getText().trim();
                String prezime = kkf.getjTextFieldPrezime().getText().trim();
                String brojTelefona = kkf.getjTextFieldBrojTelefona().getText().trim();
                
                Mesto mesto=null;
                if(kkf.getjComboBox1().getSelectedItem()!= null){
                mesto = (Mesto) kkf.getjComboBox1().getSelectedItem();
                }
                else{
                    JOptionPane.showMessageDialog(kkf, "Niste izabrali mesto.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                 if (ime.trim().isEmpty() || prezime.trim().isEmpty()  || brojTelefona.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kkf, "Niste uneli sve podatke.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 50 || prezime.trim().length() > 50 || brojTelefona.trim().length()>10 || brojTelefona.trim().length()<10) {

                    if (ime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(kkf, "Ime može sadržati maksimalno 50 slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 50) {
                        JOptionPane.showMessageDialog(kkf, "Prezime može sadržati maksimalno 50 slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }
                    
                    if (brojTelefona.trim().length()>10 || brojTelefona.trim().length()<10) {
                        JOptionPane.showMessageDialog(kkf, "Broj telefona mora da sadrži 10 cifara.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }

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
                
                boolean ispravanBrojTelefona = true;
                for (int i = 0; i < brojTelefona.length(); i++) {
                    if (!Character.isDigit(brojTelefona.charAt(i))) {
                        ispravanBrojTelefona = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime || !ispravanBrojTelefona) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(kkf, "Ime može sadržati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(kkf, "Prezime može sadržati samo slova.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);

                    }

                    if (!ispravanBrojTelefona) {
                        JOptionPane.showMessageDialog(kkf, "Broj telefona može da sadrži samo cifre.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }
                
                
                Kupac kupac = new Kupac(id,ime, prezime, brojTelefona, mesto);
                
                
                int odgovor = JOptionPane.showConfirmDialog(kkf, "Da li ste sigurni da zelite da sacuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniKupca(kupac);

                    if (uspesno==false) {
                        JOptionPane.showMessageDialog(kkf, "Sistem ne moze da zapamti kupca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else {

                        JOptionPane.showMessageDialog(kkf, "Sistem je zapamtio kupca.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                        kkf.dispose();
                    }
                }
   
            }
            
        });
        
        kkf.odustaniAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int odustani = JOptionPane.showConfirmDialog(kkf, "Da li ste sigurni da zelite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if(odustani==0){
                    kkf.dispose();
                }
                else{
                    return;
                }
            }
            
        });
        kkf.obrisiKupcaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(kkf.getjTextFieldId().getText().trim());
                String ime = kkf.getjTextFieldIme().getText().trim();
                String prezime = kkf.getjTextFieldPrezime().getText().trim();
                String brojTelefona = kkf.getjTextFieldBrojTelefona().getText().trim();
                Mesto mesto=(Mesto) kkf.getjComboBox1().getSelectedItem();
                Kupac zaBrisanje = new Kupac(id,ime,prezime,brojTelefona,mesto);
              
                boolean uspesno=Komunikacija.getInstance().obrisiKupca(zaBrisanje);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(kkf, "Sistem je obrisao kupca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kkf.dispose();
                   
                }else{
                     JOptionPane.showMessageDialog(kkf,"Sistem ne može da obriše kupca.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                     return;
                }
            }
            
        });
        
        kkf.promeniDugmeAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                kkf.getjButtonPromeniDugme().setVisible(false);
        
                kkf.getjButtonOdustani().setVisible(false);
                kkf.getjButtonOdustani().setEnabled(false);
        
                kkf.getjButtonOdustani1().setVisible(true);
                kkf.getjButtonOdustani1().setEnabled(true);

                kkf.getjButtonObrisi().setVisible(true);
                kkf.getjButtonObrisi().setEnabled(false);

                kkf.getjButtonPromeni().setVisible(true);
                kkf.getjButtonPromeni().setEnabled(true);


                kkf.getjTextFieldIme().setEditable(true);

              
                kkf.getjTextFieldPrezime().setEditable(true);

               
                kkf.getjTextFieldBrojTelefona().setEditable(true);

                kkf.getjComboBox1().setEnabled(true);
                
            }
            
        });
        
        kkf.odustani1AddActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
              Kupac k1 = Komunikacija.getInstance().pretraziKupca(k);
              otvoriFormu(k1);
            }
            
        });
        
        
        
        
        
        
    }

    public void otvoriFormu() {
        
        
        kkf.getjButtonKreiraj().setVisible(true);
        kkf.getjButtonPromeni().setVisible(false);
        kkf.getjLabelId().setVisible(false);
        kkf.getjTextFieldId().setVisible(false);
        kkf.getjButtonObrisi().setVisible(false);
        kkf.getjButtonOdustani1().setVisible(false);
        kkf.getjButtonOdustani().setVisible(true);
        kkf.getjButtonPromeniDugme().setVisible(false);
        
        popuniComboBox();
        kkf.setVisible(true);
       
    }
    
    public void popuniComboBox(){
        
        List<Mesto> mesta = Komunikacija.getInstance().vratiListuSviMesto();
        for(Mesto m:mesta){
            kkf.getjComboBox1().addItem(m);
        }
        
    }

    public void otvoriFormu(Kupac pronadjen) {
        k = pronadjen;
        kkf.getjButtonPromeniDugme().setVisible(true);
        kkf.getjButtonPromeniDugme().setEnabled(true);
        
        kkf.getjButtonOdustani().setVisible(true);
        kkf.getjButtonOdustani().setEnabled(true);
        
        kkf.getjButtonOdustani1().setVisible(false);
        kkf.getjButtonOdustani1().setEnabled(false);
        
        kkf.getjButtonObrisi().setVisible(true);
        kkf.getjButtonObrisi().setEnabled(true);
        
        kkf.getjButtonKreiraj().setVisible(false);
        
        kkf.getjButtonPromeni().setVisible(false);
        
        
        
        kkf.getjLabelId().setVisible(true);
        kkf.getjTextFieldId().setVisible(true);
        kkf.getjTextFieldId().setEditable(false);
        kkf.getjTextFieldId().setText(pronadjen.getIdKupac()+"");
        
        kkf.getjTextFieldIme().setText(pronadjen.getIme());
        kkf.getjTextFieldIme().setEditable(false);
        
        kkf.getjTextFieldPrezime().setText(pronadjen.getPrezime());
        kkf.getjTextFieldPrezime().setEditable(false);
        
        kkf.getjTextFieldBrojTelefona().setText(pronadjen.getBrojTelefona());
        kkf.getjTextFieldBrojTelefona().setEditable(false);
        
        kkf.getjComboBox1().setSelectedItem(pronadjen.getMesto());
        kkf.getjComboBox1().setEnabled(false);
        popuniComboBox();
        kkf.setVisible(true);        
    }

    
  
    
    
    
}
