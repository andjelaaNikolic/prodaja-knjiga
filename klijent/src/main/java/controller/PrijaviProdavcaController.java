package controller;

import forme.PrijaviProdavcaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static java.lang.Character.isLetter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Prodavac;

/**
 * @author Andjela
 */
public class PrijaviProdavcaController {
    private final PrijaviProdavcaForma pp;

    public PrijaviProdavcaController(PrijaviProdavcaForma pp) {
        this.pp = pp;
        addActionListeners();
    }

    private void addActionListeners() {
        
        pp.prijavaProdavcaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                          
               String korisnickoIme = pp.getjTextFieldKorisnickoIme().getText().trim();
               String sifra = String.valueOf(pp.getjPasswordFieldSifra().getPassword());
                
               if(korisnickoIme==null || korisnickoIme.isEmpty()){
                   JOptionPane.showMessageDialog(pp,"Niste uneli korisnicko ime.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               if(sifra==null || sifra.isEmpty()){
                   JOptionPane.showMessageDialog(pp,"Niste uneli sifru.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               
               boolean ispravnoKorisnickoIme=true;
               for(int i=0;i<korisnickoIme.length();i++){
                   if(!Character.isLetter(korisnickoIme.charAt(i))){
                       ispravnoKorisnickoIme=false;
                   }
               }
               
               
               if(korisnickoIme.length()>50){
                 JOptionPane.showMessageDialog(pp,"Korisnicko ime moze sadrzati najvise 50 karaktera.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                 return;

               }
               if(sifra.length()>10 || sifra.length()<10){
                 JOptionPane.showMessageDialog(pp,"Sifra mora da sadrzi 10 karaktera.","Upozorenje!", JOptionPane.WARNING_MESSAGE);
                 return;
               }

               Prodavac p = new Prodavac();
               p.setKorisnickoIme(korisnickoIme);
               p.setSifra(sifra);
               
                try {
                    Komunikacija.getInstance().konekcija();
                } catch (IOException ex) {
                    Logger.getLogger(PrijaviProdavcaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                boolean uspesno = Komunikacija.uspeh;
                if(!uspesno){
                    JOptionPane.showMessageDialog(pp, "Server nije u funkciji.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    Prodavac prodavac = Komunikacija.getInstance().prijaviProdavca(p);
                    if(prodavac==null){
                        JOptionPane.showMessageDialog(pp, "Korisnicko ime i sifra nisu ispravni..", "Greska!", JOptionPane.ERROR_MESSAGE);
                        Komunikacija.getInstance().odjaviProdavca(null);
                        return;
                    }
                    else{
                         boolean prijavljen = Komunikacija.getInstance().dodajPrijavljenogKorisnika(prodavac);

                    if (prijavljen) {

                        JOptionPane.showMessageDialog(pp, "Vec ste prijavljeni.", "Neuspesna prijava", JOptionPane.ERROR_MESSAGE);
                        Komunikacija.getInstance().odjaviProdavca(null);

                        pp.dispose();

                    } else {

                       Koordinator.getInstance().setUlogovaniProdavac(prodavac);

                        JOptionPane.showMessageDialog(pp, "Korisnicko ime i sifra su ispravni.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);

                        try {
                            Koordinator.getInstance().otvoriGlavnuFormu();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(pp, "Ne moze da se otvori glavna forma.", "Greska!", JOptionPane.ERROR_MESSAGE);

                        }

                        pp.dispose();

                    }
                    }
                }
           
               
            }
        });
        
    }

    public void otvoriFormu() {
        pp.setVisible(true);
    }
    
    
    
    
    
}
