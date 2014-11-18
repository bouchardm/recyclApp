/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Swing;

import Application.Controller.Controller;
import java.awt.geom.Point2D;
import javax.swing.AbstractButton;
import Presentation.Swing.infoSortStationFrame;
import Domain.SortStation;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Marcleking
 */
public class MainFrame extends javax.swing.JFrame {
    
    Controller _controller;
    SortStation _sortStationSelected;
    /**
     * Creates new form fenetre
     */
    public MainFrame() {
        _controller = new Controller();
        _sortStationSelected = null;
        initComponents();
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

        PanelButton = new javax.swing.JPanel();
        btnAddEntrace = new javax.swing.JButton();
        btnAddConveyor = new javax.swing.JButton();
        btnAddJunction = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnCreateNewProject = new javax.swing.JButton();
        btnUndo = new javax.swing.JButton();
        btnRedo = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnAddStation = new javax.swing.JToggleButton();
        panelWrokspace = new javax.swing.JPanel();
        viewportBar = new javax.swing.JPanel();
        zoomOutButton = new javax.swing.JButton();
        zoomInButton = new javax.swing.JButton();
        cursorCoordsLabel = new javax.swing.JLabel();
        gridCheckBox = new javax.swing.JCheckBox();
        snapCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        xGridDimFTextField = new javax.swing.JFormattedTextField();
        yGridDimFTextField = new javax.swing.JFormattedTextField();
        viewportScrollPane = new javax.swing.JScrollPane();
        viewport = new Presentation.Swing.Viewport(this);
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelInformation = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        newMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        btnAboutUs = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAddEntrace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/entry.png"))); // NOI18N
        btnAddEntrace.setToolTipText("Ajouter une entrée");
        btnAddEntrace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEntraceActionPerformed(evt);
            }
        });

        btnAddConveyor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/conveyor.png"))); // NOI18N
        btnAddConveyor.setToolTipText("Ajouter un convoyeur");
        btnAddConveyor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddConveyorActionPerformed(evt);
            }
        });

        btnAddJunction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/junction.png"))); // NOI18N
        btnAddJunction.setToolTipText("Ajouter une jonction");
        btnAddJunction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddJunctionActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exit.png"))); // NOI18N
        jButton5.setToolTipText("Ajouter une sortie");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnCreateNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        btnCreateNewProject.setToolTipText("Créé un nouveau projet");
        btnCreateNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewProjectActionPerformed(evt);
            }
        });

        btnUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/undo.png"))); // NOI18N
        btnUndo.setToolTipText("Annuler");
        btnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoActionPerformed(evt);
            }
        });

        btnRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/redo.png"))); // NOI18N
        btnRedo.setToolTipText("Refaire");
        btnRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedoActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        btnSave.setToolTipText("Enregistrer");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/open.png"))); // NOI18N
        btnOpen.setToolTipText("Ouvrir un projet");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnAddStation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/station.png"))); // NOI18N
        btnAddStation.setToolTipText("Ajouter une station");
        btnAddStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddStationMouseClicked(evt);
            }
        });
        btnAddStation.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btnAddStationMouseDragged(evt);
            }
        });
        btnAddStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelButtonLayout = new javax.swing.GroupLayout(PanelButton);
        PanelButton.setLayout(PanelButtonLayout);
        PanelButtonLayout.setHorizontalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelButtonLayout.createSequentialGroup()
                .addGroup(PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRedo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCreateNewProject, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAddJunction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btnAddConveyor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAddEntrace, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAddStation, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnOpen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        PanelButtonLayout.setVerticalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButtonLayout.createSequentialGroup()
                .addComponent(btnAddEntrace, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddStation, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddConveyor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddJunction, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateNewProject, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelWrokspace.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelWrokspace.setLayout(new java.awt.BorderLayout());

        viewportBar.setPreferredSize(new java.awt.Dimension(710, 30));

        zoomOutButton.setText("-");
        zoomOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutButtonActionPerformed(evt);
            }
        });

        zoomInButton.setText("+");
        zoomInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInButtonActionPerformed(evt);
            }
        });

        cursorCoordsLabel.setText("cursorCoords");

        gridCheckBox.setText("grid");
        gridCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridCheckBoxActionPerformed(evt);
            }
        });

        snapCheckBox.setText("snap");
        snapCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapCheckBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("x");

        xGridDimFTextField.setValue(1.00f);
        xGridDimFTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00 m"))));
        xGridDimFTextField.setText("1,00 m");
        xGridDimFTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xGridDimFTextFieldActionPerformed(evt);
            }
        });

        yGridDimFTextField.setValue(1.00f);
        yGridDimFTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00 m"))));
        yGridDimFTextField.setText("1,00 m");
        yGridDimFTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yGridDimFTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewportBarLayout = new javax.swing.GroupLayout(viewportBar);
        viewportBar.setLayout(viewportBarLayout);
        viewportBarLayout.setHorizontalGroup(
            viewportBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewportBarLayout.createSequentialGroup()
                .addComponent(gridCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xGridDimFTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yGridDimFTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(snapCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 540, Short.MAX_VALUE)
                .addComponent(cursorCoordsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zoomOutButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zoomInButton))
        );
        viewportBarLayout.setVerticalGroup(
            viewportBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewportBarLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(viewportBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewportBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(snapCheckBox)
                        .addComponent(gridCheckBox)
                        .addComponent(xGridDimFTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(yGridDimFTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewportBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(zoomInButton)
                        .addComponent(zoomOutButton)
                        .addComponent(cursorCoordsLabel)))
                .addGap(2, 2, 2))
        );

        panelWrokspace.add(viewportBar, java.awt.BorderLayout.PAGE_END);

        viewport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewportMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewportMouseReleased(evt);
            }
        });
        viewport.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                viewportMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                viewportMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout viewportLayout = new javax.swing.GroupLayout(viewport);
        viewport.setLayout(viewportLayout);
        viewportLayout.setHorizontalGroup(
            viewportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
        );
        viewportLayout.setVerticalGroup(
            viewportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );

        viewportScrollPane.setViewportView(viewport);

        panelWrokspace.add(viewportScrollPane, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Information");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        panelInformation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelInformationLayout = new javax.swing.GroupLayout(panelInformation);
        panelInformation.setLayout(panelInformationLayout);
        panelInformationLayout.setHorizontalGroup(
            panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        panelInformationLayout.setVerticalGroup(
            panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("Fichier");

        openMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, 0));
        openMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/open.png"))); // NOI18N
        openMenu.setText("Ouvrir");
        jMenu1.add(openMenu);
        jMenu1.add(jSeparator1);

        newMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        newMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        newMenu.setText("Nouveau");
        newMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuActionPerformed(evt);
            }
        });
        jMenu1.add(newMenu);
        jMenu1.add(jSeparator2);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        jMenuItem2.setText("Enregistrer");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Édition");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, 0));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/undo.png"))); // NOI18N
        jMenuItem4.setText("Annuler");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, 0));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/redo.png"))); // NOI18N
        jMenuItem5.setText("Refaire");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        btnAboutUs.setText("À propos");
        btnAboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAboutUsMousePressed(evt);
            }
        });
        jMenuBar1.add(btnAboutUs);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelWrokspace, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelWrokspace, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddEntraceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEntraceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddEntraceActionPerformed

    private void btnAddConveyorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddConveyorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddConveyorActionPerformed

    private void btnAddJunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddJunctionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddJunctionActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRedoActionPerformed

    private void btnUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUndoActionPerformed

    private void btnCreateNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewProjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCreateNewProjectActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpenActionPerformed

    private void newMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newMenuActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnAboutUsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutUsMousePressed
        this._controller.showAboutUs();
    }//GEN-LAST:event_btnAboutUsMousePressed

    private void viewportMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewportMouseMoved
        Point2D.Float position = this.viewport.createPointInMeter(evt.getX(), evt.getY());
        cursorCoordsLabel.setText(String.format("x : %.2f m  y : %.2f m\n", position.x, position.y));
    }//GEN-LAST:event_viewportMouseMoved

    private void zoomInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomInButtonActionPerformed
        viewport.setZoomFactor(viewport.getZoomFactor() + 0.1f);
    }//GEN-LAST:event_zoomInButtonActionPerformed

    private void zoomOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutButtonActionPerformed
        viewport.setZoomFactor(viewport.getZoomFactor() - 0.1f);
    }//GEN-LAST:event_zoomOutButtonActionPerformed

    private void gridCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridCheckBoxActionPerformed
        AbstractButton abstractButton = (AbstractButton)evt.getSource();
        viewport.displayGrid(abstractButton.getModel().isSelected());
    }//GEN-LAST:event_gridCheckBoxActionPerformed

    private void snapCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapCheckBoxActionPerformed
        AbstractButton abstractButton = (AbstractButton)evt.getSource();
        viewport.snapToGrid(abstractButton.getModel().isSelected());
    }//GEN-LAST:event_snapCheckBoxActionPerformed

    private void xGridDimFTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xGridDimFTextFieldActionPerformed
        Point2D.Float dim = new Point2D.Float(
                Float.valueOf(xGridDimFTextField.getValue().toString()),
                Float.valueOf(yGridDimFTextField.getValue().toString()));
        viewport.setGridDimensions(dim);
        requestFocus();
    }//GEN-LAST:event_xGridDimFTextFieldActionPerformed

    private void yGridDimFTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yGridDimFTextFieldActionPerformed
        Point2D.Float dim = new Point2D.Float(
                Float.valueOf(xGridDimFTextField.getValue().toString()),
                Float.valueOf(yGridDimFTextField.getValue().toString()));
        viewport.setGridDimensions(dim);
        requestFocus();
    }//GEN-LAST:event_yGridDimFTextFieldActionPerformed

    private void viewportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewportMouseReleased
        if (this.viewport.getCreationMode() == Viewport.CREATION_MODES.SORT_STATION) {
            Point2D.Float position = this.viewport.createPointInMeter(evt.getX(), evt.getY());
            if (viewport.isSnapToGrid())
            {
                position = viewport.snap(position);
            }
            this._controller.AddStation(position);
            
            this.viewport.display();
            this.viewport.setCreationMode(Viewport.CREATION_MODES.NONE);
            btnAddStation.setSelected(false);
        }
        
        this._sortStationSelected = null;
    }//GEN-LAST:event_viewportMouseReleased

    private void btnAddStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStationActionPerformed
        if (btnAddStation.isSelected()) {
            this.viewport.setCreationMode(Viewport.CREATION_MODES.SORT_STATION);
        } else {
            this.viewport.setCreationMode(Viewport.CREATION_MODES.NONE);
        }
    }//GEN-LAST:event_btnAddStationActionPerformed

    private void btnAddStationMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddStationMouseDragged
        btnAddStation.setSelected(true);
        this.viewport.setCreationMode(Viewport.CREATION_MODES.SORT_STATION);
    }//GEN-LAST:event_btnAddStationMouseDragged

    private void viewportMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewportMouseDragged
        Point2D.Float position = this.viewport.createPointInMeter(evt.getX(), evt.getY());
        cursorCoordsLabel.setText(String.format("x : %.2f m  y : %.2f m\n", position.x, position.y));
        
        if (viewport.isSnapToGrid())
        {
            position = viewport.snap(position);
        }
        
        if  (_sortStationSelected == null) {
            this._sortStationSelected = this._controller.getProject().getSortCenter().getSortStationCursorIn(position);
        } else {
            this._controller.MouveStation(this._sortStationSelected, position);
        }
        
        this.viewport.display();
    }//GEN-LAST:event_viewportMouseDragged

    private void btnAddStationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddStationMouseClicked
        btnAddStation.setSelected(true);
        this.viewport.setCreationMode(Viewport.CREATION_MODES.SORT_STATION);
    }//GEN-LAST:event_btnAddStationMouseClicked

    private void viewportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewportMouseClicked
        Point2D.Float position = this.viewport.createPointInMeter(evt.getX(), evt.getY());
        this.cleanInformationPanel();
        
        SortStation sortStation = this._controller.getProject().getSortCenter().getSortStationCursorIn(position); // mauvais utilisation du contrôleur
        
        this._controller.getProject().getSortCenter().unselectAll(); // mauvais utilisation du contrôleur
        
        if (sortStation != null) {
            sortStation.setSelected(true);
            infoSortStationFrame infoSortStationFrame = new infoSortStationFrame(
                sortStation, 
                this._controller.getProject().getSortCenter().getSortStationList(),// mauvais utilisation du contrôleur
                this
            );
            
            JPanel sortStationPanel = infoSortStationFrame.getPanel();

            sortStationPanel.setSize(this.panelInformation.getWidth(), this.panelInformation.getHeight());
            this.panelInformation.add(sortStationPanel);
        }
        
        repaint();
    }//GEN-LAST:event_viewportMouseClicked

    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelButton;
    private javax.swing.JMenu btnAboutUs;
    private javax.swing.JButton btnAddConveyor;
    private javax.swing.JButton btnAddEntrace;
    private javax.swing.JButton btnAddJunction;
    private javax.swing.JToggleButton btnAddStation;
    private javax.swing.JButton btnCreateNewProject;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnRedo;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUndo;
    private javax.swing.JLabel cursorCoordsLabel;
    private javax.swing.JCheckBox gridCheckBox;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem newMenu;
    private javax.swing.JMenuItem openMenu;
    private javax.swing.JPanel panelInformation;
    private javax.swing.JPanel panelWrokspace;
    private javax.swing.JCheckBox snapCheckBox;
    private Presentation.Swing.Viewport viewport;
    private javax.swing.JPanel viewportBar;
    private javax.swing.JScrollPane viewportScrollPane;
    private javax.swing.JFormattedTextField xGridDimFTextField;
    private javax.swing.JFormattedTextField yGridDimFTextField;
    private javax.swing.JButton zoomInButton;
    private javax.swing.JButton zoomOutButton;
    // End of variables declaration//GEN-END:variables

    public void cleanInformationPanel() {
        this.panelInformation.removeAll();
    }
}
