/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Swing;

import Application.Controller.Controller;
import Domain.SortStation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Marcleking
 */
public class infoTransStationFrame extends javax.swing.JFrame { // Pourquoi c'est un Frame. Cette classe contient une méthode main()??

    private MainFrame _parent;
    private Controller _controller;
    private Color _stationColor;
    
    
    /**
     * Creates new form infoSortStation
     */
    public infoTransStationFrame() {
        initComponents();
    }
    
    public infoTransStationFrame(Controller controller, MainFrame parent) {
        initComponents();
        
        this._controller = controller;
        
        Map<String, Object> infoElement = this._controller.getSelectedElementAttributes();
        
        this.setStationName((String) infoElement.get("name"));
        this.setStationDescription((String) infoElement.get("description"));
        this.setStationKgHMax((Float) infoElement.get("speedMax"));
        this._stationColor = (Color) infoElement.get("color");
        
        this._parent = parent;
    }

    public void setStationDescription(String txtStationDescription) {
        this.txtStationDescription.setText(txtStationDescription);
    }

    public void setStationKgHMax(Float txtStationKgHMax) {
        this.txtStationKgHMax.setText(txtStationKgHMax.toString());
    }

    public void setStationName(String txtStationName) {
        this.txtStationName.setText(txtStationName);
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
        btnDeleteStation = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtStationDescription = new javax.swing.JTextArea();
        txtStationName = new javax.swing.JTextField();
        txtStationKgHMax = new javax.swing.JTextField();
        btnColorPciker = new javax.swing.JButton();
        btnImgStation = new javax.swing.JButton();
        btnTransMatrix = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        outletBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelInformation2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelInformation2.setPreferredSize(new java.awt.Dimension(216, 2));

        btnDeleteStation.setText("Supprimer");
        btnDeleteStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteStationMouseClicked(evt);
            }
        });
        btnDeleteStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStationActionPerformed(evt);
            }
        });

        txtStationDescription.setColumns(20);
        txtStationDescription.setRows(5);
        txtStationDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStationDescriptionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtStationDescription);

        txtStationName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStationNameKeyPressed(evt);
            }
        });

        txtStationKgHMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStationKgHMaxKeyPressed(evt);
            }
        });

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

        btnImgStation.setText("Image");
        btnImgStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImgStationMouseClicked(evt);
            }
        });

        btnTransMatrix.setText("Matrice de transformation");
        btnTransMatrix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransMatrixMouseClicked(evt);
            }
        });

        jLabel1.setText("Nom:");

        jLabel2.setText("Description:");

        jLabel4.setText("Kg/h max:");

        outletBtn.setText("Configurer les sorties");
        outletBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outletBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInformation2Layout = new javax.swing.GroupLayout(panelInformation2);
        panelInformation2.setLayout(panelInformation2Layout);
        panelInformation2Layout.setHorizontalGroup(
            panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformation2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtStationName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(txtStationKgHMax, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnColorPciker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImgStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransMatrix, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelInformation2Layout.createSequentialGroup()
                        .addGroup(panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(outletBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelInformation2Layout.setVerticalGroup(
            panelInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformation2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteStation, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStationKgHMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnColorPciker)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outletBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTransMatrix)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImgStation)
                .addContainerGap(199, Short.MAX_VALUE))
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

    private void btnDeleteStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteStationActionPerformed

    private void btnDeleteStationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteStationMouseClicked
        this._controller.DeleteStation();
        this._parent.cleanInformationPanel();
        this._parent.repaint();
    }//GEN-LAST:event_btnDeleteStationMouseClicked

    private void txtStationNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStationNameKeyPressed
        this._controller.EditStation(
            this.txtStationName.getText(), 
            this.txtStationDescription.getText(), 
            null, 
            null,
            Float.valueOf(this.txtStationKgHMax.getText()),
            null
        );
        
        this._parent.repaint();
    }//GEN-LAST:event_txtStationNameKeyPressed

    private void txtStationDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStationDescriptionKeyPressed
        this._controller.EditStation(
            this.txtStationName.getText(), 
            this.txtStationDescription.getText(), 
            null, 
            null,
            Float.valueOf(this.txtStationKgHMax.getText()),
            null
        );
    }//GEN-LAST:event_txtStationDescriptionKeyPressed

    private void txtStationKgHMaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStationKgHMaxKeyPressed
        String strKgHMax = this.txtStationKgHMax.getText();
        
        float kgHMax;
        try {
            kgHMax = Float.parseFloat(strKgHMax);
        } catch (NumberFormatException e) {
            return;
        }
        
        this._controller.EditStation(
            this.txtStationName.getText(), 
            this.txtStationDescription.getText(), 
            null, 
            null,
            kgHMax,
            null
        );
    }//GEN-LAST:event_txtStationKgHMaxKeyPressed

    private void txtStationNbExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStationNbExitKeyPressed

    }//GEN-LAST:event_txtStationNbExitKeyPressed

    private void btnColorPcikerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnColorPcikerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnColorPcikerMouseClicked

    private void btnColorPcikerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorPcikerActionPerformed
        Color colorStation = JColorChooser.showDialog(null, "Veuillez choisir la couleur de la station", this._stationColor);
        
        this._controller.EditStation(
            this.txtStationName.getText(), 
            this.txtStationDescription.getText(), 
            colorStation, 
            null,
            Float.valueOf(this.txtStationKgHMax.getText()),
            null
        );
        
        this._parent.repaint();
    }//GEN-LAST:event_btnColorPcikerActionPerformed

    private void btnImgStationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImgStationMouseClicked
        JFileChooser filePicker = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, GIF & PNG Images", "jpg", "gif", "png");
        filePicker.setFileFilter(filter);
        int responce = filePicker.showOpenDialog(this._parent);
        if(responce == JFileChooser.APPROVE_OPTION) {
            
            this._controller.EditStation(
                this.txtStationName.getText(), 
                this.txtStationDescription.getText(), 
                null, 
                filePicker.getSelectedFile().getAbsolutePath(),
                Float.valueOf(this.txtStationKgHMax.getText()),
                null
            );
        }
        this._parent.repaint();
    }//GEN-LAST:event_btnImgStationMouseClicked

    private void outletBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outletBtnActionPerformed
        JFrame outletMatterFrame = new OutletMatterFrame(this._controller, this._parent);
        outletMatterFrame.setVisible(true);
    }//GEN-LAST:event_outletBtnActionPerformed

    private void btnTransMatrixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransMatrixMouseClicked
        JFrame transMatrixFrame = new OutletMatterTransformationFrame(this._controller, this._parent);
        transMatrixFrame.setVisible(true);        
    }//GEN-LAST:event_btnTransMatrixMouseClicked

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
            java.util.logging.Logger.getLogger(infoTransStationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infoTransStationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infoTransStationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infoTransStationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new infoTransStationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColorPciker;
    private javax.swing.JButton btnDeleteStation;
    private javax.swing.JButton btnImgStation;
    private javax.swing.JButton btnTransMatrix;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton outletBtn;
    private javax.swing.JPanel panelInformation2;
    private javax.swing.JTextArea txtStationDescription;
    private javax.swing.JTextField txtStationKgHMax;
    private javax.swing.JTextField txtStationName;
    // End of variables declaration//GEN-END:variables

    
}