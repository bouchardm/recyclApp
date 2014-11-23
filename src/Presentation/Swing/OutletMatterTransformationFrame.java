/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Swing;

import Application.Controller.Controller;
import Domain.Outlet;
import Domain.SortMatrix;
import Domain.TransMatrix;
import TechnicalServices.Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Marcleking
 */
public class OutletMatterTransformationFrame extends javax.swing.JFrame {

    private MainFrame _parent;
    private Controller _controller;
    
    /**
     * Creates new form OutletMatterFrame
     */
    public OutletMatterTransformationFrame() {
        initComponents();
        this.setLocationRelativeTo(null); // Centrer la fenêtre
    }

    public OutletMatterTransformationFrame(Controller _controller, MainFrame _parent) {
        initComponents();
        
        this._controller = _controller;
        this._parent = _parent;
        
        Map<String, Object> infoStation = this._controller.getSelectedElementAttributes();
        HashMap<Integer, HashMap<Integer, Float>> transMatrix = (HashMap<Integer, HashMap<Integer, Float>>) infoStation.get("transMatrix");
        
        String[] exits = new String[transMatrix.size() + 1];
        
        exits[0] = "Recu / Transformé";
        for (int i = 1; i < exits.length; i++) {
            exits[i] = "Matière ";  
        }
        
        for (Map.Entry<Integer, HashMap<Integer, Float>> sfd : transMatrix.entrySet()) {
            Integer key = sfd.getKey();
            HashMap<Integer, Float> value = sfd.getValue();
            int i = 1;
        
            for (Map.Entry<Integer, Float> entrySet : value.entrySet()) {
                Integer key1 = entrySet.getKey();
                Float value1 = entrySet.getValue();

                exits[i] = this._controller.getMatterName(key1); 
                i++;
            }
        }
        // {1={1=0.5, 2=0.5}, 2={1=0.25, 2=0.75}}
        
        
        // Nombre de matiere : sortMatrix.getSortMatrix().size()
        // row : column
        String[][] matters = new String[transMatrix.size()][exits.length];
        
//        System.out.println(hashMapSortMatrix);
        int i = 0;
        
        for (Map.Entry<Integer, HashMap<Integer, Float>> entrySet : transMatrix.entrySet()) {
            Integer key = entrySet.getKey();
            HashMap<Integer, Float> value = entrySet.getValue();
            
            matters[i][0] = this._controller.getMatterName(key); 
            
            int j = 1;
            for (Map.Entry<Integer, Float> entrySet1 : value.entrySet()) {
                Integer key1 = entrySet1.getKey();
                Float value1 = entrySet1.getValue();
                
                matters[i][j] = value1.toString();
                j++;
            }
            i++;
        }
        
        transMatrixTable.setModel(new javax.swing.table.DefaultTableModel(
            matters, exits
        ));
        
        this.repaint();
        this.setLocationRelativeTo(null); // Centrer la fenêtre
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        transMatrixTable = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();

        setTitle("Matrice de tri - RecyclApp");

        transMatrixTable.setModel(new javax.swing.table.DefaultTableModel(
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
        transMatrixTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                transMatrixTableKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                transMatrixTableKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(transMatrixTable);

        btnSave.setText("Appliquer");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSave))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transMatrixTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transMatrixTableKeyTyped
        
        
        
    }//GEN-LAST:event_transMatrixTableKeyTyped

    private void transMatrixTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transMatrixTableKeyPressed
        
        
    }//GEN-LAST:event_transMatrixTableKeyPressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        Map<String, Object> infoStation = this._controller.getSelectedElementAttributes();
        
        HashMap<Integer, HashMap<Integer, Float>> transMatrix = (HashMap<Integer, HashMap<Integer, Float>>) infoStation.get("transMatrix");
        
        Object[][] tableData = Utility.getTableData(transMatrixTable);
        
        for (int i = 0; i < tableData.length; i++) {
            HashMap<Integer, Float> exits = new HashMap<>();
            int matterId = 0;
            Float sumExits = new Float(0);
            for (int j = 0; j < tableData[i].length; j++) {
                if (j == 0) {
                    matterId = this._controller.getMatterId((String) tableData[i][j]);
                } else {
                    Float value = null;
                    try {
                        value = new Float((String) tableData[i][j]);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Veuillez indiquez des nombres valides.", null, 0);
                        return;
                    }
                    
                    exits.put(this._controller.getMatterId(transMatrixTable.getColumnName(j)), value);
                    sumExits += value;
                }
            }
            
            if (sumExits != 1) {
                JOptionPane.showMessageDialog(null, "Le total des sorties doivents donnée 1.", null, 0);
                return;
            }
            
            transMatrix.put(matterId, exits);
        }
        
        
        this._controller.EditStation(null, null, null, null, null, null, transMatrix);
    }//GEN-LAST:event_btnSaveMouseClicked

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
            java.util.logging.Logger.getLogger(OutletMatterTransformationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OutletMatterTransformationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OutletMatterTransformationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OutletMatterTransformationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OutletMatterTransformationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable transMatrixTable;
    // End of variables declaration//GEN-END:variables
}
