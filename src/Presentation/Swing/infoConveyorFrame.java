/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Swing;

import Application.Controller.Controller;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

/**
 *
 * @author Marcleking
 */
public class infoConveyorFrame extends javax.swing.JFrame { // Pourquoi c'est un Frame. Cette classe contient une méthode main()??

    private MainFrame _parent;
    private Controller _controller;
    private Color _color;
    
    
    /**
     * Creates new form infoSortStation
     */
    public infoConveyorFrame() {
        initComponents();
    }
    
    public infoConveyorFrame(Controller controller, MainFrame parent) {
        initComponents();
        
        this._controller = controller;
        this._parent = parent;
        
        Map<String, Object> infoElement = this._controller.getSelectedElementAttributes();
        
        this.setStationKgHMax((Float) infoElement.get("speedMax"));
        
        this._color = (Color) infoElement.get("color");
        
        HashMap<Integer, Float> listMatter = (HashMap<Integer, Float>) infoElement.get("matterQuantities");
        
        matterQuantities matterQuantitiesTable = new matterQuantities(listMatter, controller);
        matterQuantitiesTable.setSize(300, 300);
        matterQuantitiesTable.setLocation(2, 300);
        this.panelInformation2.add(matterQuantitiesTable);
        
        pack();
    }

    public void setStationKgHMax(Float txtStationKgHMax) {
        this.txtConveyorKgHMax.setText(txtStationKgHMax.toString());
    }
    
    public JPanel getPanel() {
        return this.panelInformation2;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInformation2 = new javax.swing.JPanel();
        btnDeleteConveyor = new javax.swing.JButton();
        txtConveyorKgHMax = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnColorPciker = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelInformation2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelInformation2.setPreferredSize(new java.awt.Dimension(216, 2));

        btnDeleteConveyor.setText("Supprimer");
        btnDeleteConveyor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteConveyorActionPerformed(evt);
            }
        });

        txtConveyorKgHMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConveyorKgHMaxKeyPressed(evt);
            }
        });

        jLabel4.setText("Kg/h max:");

        btnColorPciker.setText("Couleur");
        btnColorPciker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnColorPcikerMouseClicked(evt);
            }
        });
        btnColorPciker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorPcikerActionPerformed(evt);
            }
        });

        jButton1.setText("Voir les erreurs");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInformation2Layout = new javax.swing.GroupLayout(panelInformation2);
        panelInformation2.setLayout(panelInformation2Layout);
        panelInformation2Layout.setHorizontalGroup(
            panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformation2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteConveyor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(txtConveyorKgHMax, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnColorPciker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelInformation2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelInformation2Layout.setVerticalGroup(
            panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformation2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteConveyor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConveyorKgHMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnColorPciker)
                .addContainerGap(439, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInformation2, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteConveyorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteConveyorActionPerformed
        this._controller.deleteConveyor();
        this._parent.cleanInformationPanel();
        this._parent.repaint();
    }//GEN-LAST:event_btnDeleteConveyorActionPerformed

    private void txtConveyorKgHMaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConveyorKgHMaxKeyPressed
        String strKgHMax = this.txtConveyorKgHMax.getText();
        
        float kgHMax;
        try {
            kgHMax = Float.parseFloat(strKgHMax);
        } catch (NumberFormatException e) {
            return;
        }
        
        this._controller.EditConveyor(kgHMax, null);
    }//GEN-LAST:event_txtConveyorKgHMaxKeyPressed

    private void txtStationNbExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStationNbExitKeyPressed

    }//GEN-LAST:event_txtStationNbExitKeyPressed

    private void btnColorPcikerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnColorPcikerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnColorPcikerMouseClicked

    private void btnColorPcikerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorPcikerActionPerformed
        Color colorStation = JColorChooser.showDialog(null, "Veuillez choisir la couleur de la station", this._color);

        this._controller.EditConveyor(null, colorStation);

        this._parent.repaint();
    }//GEN-LAST:event_btnColorPcikerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ErrorsFrame errorsFrame = new ErrorsFrame(this._controller);
        errorsFrame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(infoConveyorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infoConveyorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infoConveyorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infoConveyorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new infoConveyorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColorPciker;
    private javax.swing.JButton btnDeleteConveyor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panelInformation2;
    private javax.swing.JTextField txtConveyorKgHMax;
    // End of variables declaration//GEN-END:variables

    
}
