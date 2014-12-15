/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Swing;

import Application.Controller.Controller;
import Domain.MatterBasket;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcleking
 */
public class InfoExitPointFrame extends JFrame { // Pourquoi c'est un Frame. Cette classe contient une méthode main()??

    private MainFrame _parent;
    private Controller _controller;

    /**
     * Creates new form infoSortStation
     */
    public InfoExitPointFrame() {
        initComponents();
    }

    public InfoExitPointFrame(Controller controller, MainFrame parent) {
        initComponents();

        this._controller = controller;
        this._parent = parent;

        Map<String, Object> infoElement = this._controller.getSelectedElementAttributes();

        MatterBasket matterBasket = (MatterBasket) infoElement.get("matterBasket");

        int size = matterBasket.getNumberOfMatterInBasket();

        String[] nameCol = {"Matière" , "Quantité"};
      
            
        String[][] data = new String[size+1][size+1];

        HashMap<Integer, Float> matterHashMap = matterBasket.getQuantities();
        int i = 0;
        Float totalValue = 0.0f;
        for (Map.Entry<Integer, Float> iter : matterHashMap.entrySet()) {
            Integer matterID = iter.getKey();
            float qty = iter.getValue();
            if(qty!=0) {
                data[i][0] =  _controller.getProject().getSortCenter().getMatterList().getMatterName(matterID);
                data[i][1] = iter.getValue().toString(); 
                totalValue += iter.getValue();
                i++;
            }
            
            
           
            
        }

        matterTable.setModel(new DefaultTableModel(data, nameCol));
        totalValueTextField.setText(totalValue.toString());
        
        
        

        this.repaint();
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        panelInformation2 = new javax.swing.JPanel();
        totalValueTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnDeleteStation = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        matterTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelInformation2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelInformation2.setPreferredSize(new java.awt.Dimension(216, 2));

        totalValueTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalValueTextField.setEnabled(false);
        totalValueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalValueTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Total");

        btnDeleteStation.setText("Supprimer");
        btnDeleteStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStationActionPerformed(evt);
            }
        });

        matterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Matière", "Quantité"
            }
        ));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, matterTable, org.jdesktop.beansbinding.ObjectProperty.create(), matterTable, org.jdesktop.beansbinding.BeanProperty.create("selectedElements"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(matterTable);

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
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteStation, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformation2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelInformation2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelInformation2Layout.setVerticalGroup(
            panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformation2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteStation, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStationActionPerformed
        this._controller.deleteExitPoint();
        this._parent.cleanInformationPanel();
        this._parent.repaint();
    }//GEN-LAST:event_btnDeleteStationActionPerformed

    private void txtStationNbExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStationNbExitKeyPressed

    }//GEN-LAST:event_txtStationNbExitKeyPressed

    private void totalValueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalValueTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalValueTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(InfoExitPointFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoExitPointFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoExitPointFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoExitPointFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new InfoExitPointFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteStation;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable matterTable;
    private javax.swing.JPanel panelInformation2;
    private javax.swing.JTextField totalValueTextField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
