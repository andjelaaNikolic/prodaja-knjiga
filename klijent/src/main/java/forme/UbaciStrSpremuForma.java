/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ljilja
 */
public class UbaciStrSpremuForma extends javax.swing.JFrame {

    /**
     * Creates new form UbaciStrSpremuForma
     */
    public UbaciStrSpremuForma() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ubaci strucnu spremu");
        dizajn();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldStepen = new javax.swing.JTextField();
        jButtonUbaci = new javax.swing.JButton();
        jButtonOdustani = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jButtonSacuvajPromene = new javax.swing.JButton();
        jButtonPromeni = new javax.swing.JButton();
        jButtonOdustani1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(250, 243, 224));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelId.setForeground(new java.awt.Color(125, 64, 71));
        jLabelId.setText("Id:");
        getContentPane().add(jLabelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 50, 40));

        jLabel2.setForeground(new java.awt.Color(125, 64, 71));
        jLabel2.setText("Stepen:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 65, 20));
        getContentPane().add(jTextFieldId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 199, -1));
        getContentPane().add(jTextFieldStepen, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 280, -1));

        jButtonUbaci.setText("Ubaci");
        getContentPane().add(jButtonUbaci, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 120, -1));

        jButtonOdustani.setText("Odustani");
        getContentPane().add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 120, -1));

        jButtonObrisi.setText("Obrisi");
        jButtonObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 120, -1));

        jButtonSacuvajPromene.setText("Sacuvaj promene");
        getContentPane().add(jButtonSacuvajPromene, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, -1, -1));

        jButtonPromeni.setText("Promeni");
        getContentPane().add(jButtonPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 120, -1));

        jButtonOdustani1.setText("Odustani");
        getContentPane().add(jButtonOdustani1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonObrisiActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonOdustani1;
    private javax.swing.JButton jButtonPromeni;
    private javax.swing.JButton jButtonSacuvajPromene;
    private javax.swing.JButton jButtonUbaci;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldStepen;
    // End of variables declaration//GEN-END:variables

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public JButton getjButtonSacuvajPromene() {
        return jButtonSacuvajPromene;
    }

    public JButton getjButtonUbaci() {
        return jButtonUbaci;
    }

    public JButton getjButtonOdustani() {
        return jButtonOdustani;
    }

    public JButton getjButtonPromeni() {
        return jButtonPromeni;
    }
    
    public JLabel getjLabelId() {
        return jLabelId;
    }

    public JTextField getjTextFieldId() {
        return jTextFieldId;
    }

    public JTextField getjTextFieldStepen() {
        return jTextFieldStepen;
    }

    public JButton getjButtonOdustani1() {
        return jButtonOdustani1;
    }
    
    public void odustani1AddActionListener(ActionListener actionListener){
        jButtonOdustani1.addActionListener(actionListener);
    }   

    public void promeniAddActionListener(ActionListener actionListener){
        jButtonPromeni.addActionListener(actionListener);
    }    

    public void ubaciAddActionListener(ActionListener actionListener){
        jButtonUbaci.addActionListener(actionListener);
    }

    public void sacuvajPromeneAddActionListener(ActionListener actionListener){
        jButtonSacuvajPromene.addActionListener(actionListener);
    }

    public void obrisiAddActionListener(ActionListener actionListener){
        jButtonObrisi.addActionListener(actionListener);
    }

    public void odustaniAddActionListener(ActionListener actionListener){
        jButtonOdustani.addActionListener(actionListener);
    }

    public void dizajn() {
    // Font
    Font boldFont = new Font("Times New Roman", Font.BOLD, 18);
    
     setSize(600, 300);
     setResizable(false);
     setLocationRelativeTo(null);


    // Labele
    java.awt.Color labelColor = new java.awt.Color(46, 78, 31); // tamna zelena
    JLabel[] labele = new JLabel[]{ jLabel2, jLabelId };
    for (JLabel lbl : labele) {
        lbl.setFont(boldFont);
        lbl.setForeground(labelColor);
    }

    // Dugmad
    java.awt.Color dugmeZelena = new java.awt.Color(46, 78, 31);
    java.awt.Color dugmeBela = Color.WHITE;
    JButton[] dugmad = new JButton[]{ 
        jButtonObrisi, jButtonOdustani, jButtonOdustani1,
        jButtonPromeni, jButtonSacuvajPromene, jButtonUbaci
    };
    for (JButton btn : dugmad) {
        btn.setFont(boldFont);
        btn.setBackground(dugmeZelena);
        btn.setForeground(dugmeBela);
    }

    // Tekstualna polja
    java.awt.Color inputTextColor = new java.awt.Color(43, 43, 43); // tamno siva
    java.awt.Color inputBg = Color.WHITE;
    java.awt.Color disabledBg = new java.awt.Color(220, 220, 220); // svetlo siva za disabled

    JTextField[] polja = new JTextField[]{ jTextFieldId, jTextFieldStepen };

   Color inputDisabledBg = new Color(200, 230, 200);

        for (JTextField tf : polja) {
            tf.setFont(boldFont);
            tf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(46, 78, 31), 2));
            
           if(tf.isEditable()) {
                tf.setBackground(inputBg);
                tf.setForeground(inputTextColor);
            } else {
                tf.setBackground(inputDisabledBg);
                tf.setForeground(inputTextColor.brighter()); 
            }
             
            
        }
}



}
