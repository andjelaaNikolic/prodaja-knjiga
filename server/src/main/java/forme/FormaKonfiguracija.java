package forme;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;


public class FormaKonfiguracija extends javax.swing.JDialog {


    public FormaKonfiguracija(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Konfiguracija");
        dizajn();
        jTextFieldURL.setText(konfiguracija.Konfiguracija.getInstance().getProperty("url"));
        jTextFieldUsername.setText(konfiguracija.Konfiguracija.getInstance().getProperty("username"));
        jPasswordField1.setText(konfiguracija.Konfiguracija.getInstance().getProperty("password"));
        jTextFieldPort.setText(konfiguracija.Konfiguracija.getInstance().getProperty("port"));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jTextFieldURL = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButtonSacuvaj = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(245, 239, 230));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(46, 46, 46));
        jLabel1.setText("URL:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 100, 40));

        jLabel2.setForeground(new java.awt.Color(46, 46, 46));
        jLabel2.setText("Username:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 118, 100, 27));

        jLabel3.setForeground(new java.awt.Color(46, 46, 46));
        jLabel3.setText("Password:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 110, 20));
        getContentPane().add(jTextFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 300, -1));
        getContentPane().add(jTextFieldURL, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 300, -1));
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 300, -1));

        jButtonSacuvaj.setText("Sacuvaj");
        jButtonSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSacuvajActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 98, -1));

        jLabel4.setForeground(new java.awt.Color(46, 46, 46));
        jLabel4.setText("Port:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 80, -1));
        getContentPane().add(jTextFieldPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 300, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSacuvajActionPerformed
        String url = jTextFieldURL.getText().trim();
        String username = jTextFieldUsername.getText().trim();
        String password = String.valueOf(jPasswordField1.getPassword()).trim();
        int port;
        try {
            port = Integer.parseInt(jTextFieldPort.getText());
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(rootPane,"Parametar mora da bude broj","GRESKA!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(port>=0 && port<=65535){
             try{
                 
        konfiguracija.Konfiguracija.getInstance().setProperty("url", url);
        konfiguracija.Konfiguracija.getInstance().setProperty("username", username);
        konfiguracija.Konfiguracija.getInstance().setProperty("password", password);
        konfiguracija.Konfiguracija.getInstance().setProperty("port", port+"");
        konfiguracija.Konfiguracija.getInstance().sacuvajIzmene();
         JOptionPane.showMessageDialog(rootPane, "Paramatri su sacuvani.","Uspesno!", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        }
             
        
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Paramatri nisu sacuvani.","Greska!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        else{
            JOptionPane.showMessageDialog(rootPane,"Broj porta ne pripada opsegu od 0 do 65535.","Upozorenje!",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
    }//GEN-LAST:event_jButtonSacuvajActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldURL;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

public void dizajn(){
    
getContentPane().setBackground(new java.awt.Color(245, 245, 245)); 

    
    jButtonSacuvaj.setBackground(new java.awt.Color(46, 78, 31)); 
    jButtonSacuvaj.setForeground(Color.WHITE);
    jButtonSacuvaj.setFont(new Font("Times New Roman", Font.BOLD, 18));

  
    java.awt.Color labelColor = new java.awt.Color(46, 78, 31); 
    Font labelFont = new Font("Times New Roman", Font.BOLD, 18);

    for (javax.swing.JLabel lbl : new javax.swing.JLabel[]{jLabel1, jLabel2, jLabel3, jLabel4}) {
        lbl.setForeground(labelColor);
        lbl.setFont(labelFont);
    }

    
    java.awt.Color inputText = new java.awt.Color(43, 43, 43); 
    java.awt.Color inputBg = Color.WHITE;
    Font inputFont = new Font("Times New Roman", Font.PLAIN, 18);

    jTextFieldPort.setForeground(inputText);
    jTextFieldPort.setBackground(inputBg);
    jTextFieldPort.setFont(inputFont);

    jTextFieldURL.setForeground(inputText);
    jTextFieldURL.setBackground(inputBg);
    jTextFieldURL.setFont(inputFont);

    jTextFieldUsername.setForeground(inputText);
    jTextFieldUsername.setBackground(inputBg);
    jTextFieldUsername.setFont(inputFont);

    jPasswordField1.setForeground(inputText);
    jPasswordField1.setBackground(inputBg);
    jPasswordField1.setFont(inputFont);

   
    setSize(500, 300);
    setResizable(false);
    this.setLocationRelativeTo(null);
    
}


}
