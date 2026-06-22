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
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Ljilja
 */
public class PretraziMestoForma extends javax.swing.JFrame {

  
    public PretraziMestoForma() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pretrazi mesto");
        dizajn();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonPrikaziSvaMesta = new javax.swing.JButton();
        jTextFieldMesto = new javax.swing.JTextField();
        jButtonPrikazi = new javax.swing.JButton();
        jButtonPretrazi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(250, 243, 224));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Mesto");

        jLabel6.setText("Filtriraj mesto prema nazivu:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonPrikaziSvaMesta.setText("Prikazi sva mesta");
        jButtonPrikaziSvaMesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziSvaMestaActionPerformed(evt);
            }
        });

        jTextFieldMesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMestoActionPerformed(evt);
            }
        });

        jButtonPrikazi.setText("Prikazi mesto");

        jButtonPretrazi.setText("Pretrazi");

        jLabel1.setText("Prikazi sva mesta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(jButtonPrikazi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPrikaziSvaMesta, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonPrikazi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButtonPrikaziSvaMesta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButtonPretrazi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(34, 34, 34)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldMestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMestoActionPerformed

    private void jButtonPrikaziSvaMestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziSvaMestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPrikaziSvaMestaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JButton jButtonPrikazi;
    private javax.swing.JButton jButtonPrikaziSvaMesta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldMesto;
    // End of variables declaration//GEN-END:variables

    public JTable getjTable1() {
        return jTable1;
    }

    public JTextField getjTextFieldMesto() {
        return jTextFieldMesto;
    }
    
    public void vratiListuSviMestoAddActionListener(ActionListener actionListener){
        jButtonPrikaziSvaMesta.addActionListener(actionListener);
    }
    public void prikaziMestoAddActionListener(ActionListener actionListener){
        jButtonPrikazi.addActionListener(actionListener);
    }

    public void pretraziMestoAddActionListener(ActionListener actionListener){
        jButtonPretrazi.addActionListener(actionListener);
    }

    private void dizajn() {
        // Fontovi
        Font labelFont = new Font("Times New Roman", Font.BOLD, 18);
        Font textFont = new Font("Times New Roman", Font.PLAIN, 16);
        Font dugmeFont = new Font("Times New Roman", Font.BOLD, 16);

        // Pozadina forme
        getContentPane().setBackground(new java.awt.Color(245, 245, 245)); // svetlo siva
        setSize(700, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        // Panel pozadina
        jPanel1.setBackground(new java.awt.Color(245, 245, 245)); // svetliji ton za panel

        // Labele
        JLabel[] labele = { jLabel1, jLabel5, jLabel6 };
        java.awt.Color labelColor = new java.awt.Color(46, 78, 31); // tamno zelena
        for (JLabel lbl : labele) {
            lbl.setFont(labelFont);
            lbl.setForeground(labelColor);
        }

        // Tekstualno polje
        java.awt.Color inputTextColor = new java.awt.Color(43, 43, 43);
        java.awt.Color inputBg = Color.WHITE;
        java.awt.Color disabledBg = new java.awt.Color(220, 220, 220);

        jTextFieldMesto.setFont(textFont);
        jTextFieldMesto.setBorder(BorderFactory.createLineBorder(new java.awt.Color(46, 78, 31), 2));
        if(jTextFieldMesto.isEnabled()) {
            jTextFieldMesto.setBackground(inputBg);
            jTextFieldMesto.setForeground(inputTextColor);
        } else {
            jTextFieldMesto.setBackground(disabledBg);
            jTextFieldMesto.setForeground(Color.DARK_GRAY);
        }

        // Dugmad
        JButton[] buttons = { jButtonPretrazi, jButtonPrikazi, jButtonPrikaziSvaMesta };
        java.awt.Color dugmeEnabledBg = new java.awt.Color(46, 78, 31);
        java.awt.Color dugmeDisabledBg = new java.awt.Color(180, 180, 180);
        java.awt.Color dugmeEnabledFg = Color.WHITE;
        java.awt.Color dugmeDisabledFg = new java.awt.Color(80, 80, 80);

        for (JButton btn : buttons) {
            btn.setFont(dugmeFont);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);

            if(btn.isEnabled()) {
                btn.setBackground(dugmeEnabledBg);
                btn.setForeground(dugmeEnabledFg);
            } else {
                btn.setBackground(dugmeDisabledBg);
                btn.setForeground(dugmeDisabledFg);
            }
        }

        // Tabela
        jTable1.setFont(textFont);
        jTable1.setBackground(Color.WHITE);
        jTable1.setForeground(inputTextColor);
        jTable1.getTableHeader().setFont(labelFont);
    }




}
