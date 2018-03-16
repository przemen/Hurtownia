/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okno;

import db.Connector;
import db.Firma;
import db.FirmaDB;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemek
 */
public class listaKlient extends javax.swing.JPanel {

    /**
     * Creates new form listaKlient
     *
     * @throws java.sql.SQLException
     */
    DefaultTableModel model;
    public String selectID = "";
    public String selectName = "";
    Connector con;
    private int typ=0;
    
    public listaKlient(Connector con) throws SQLException {
        initComponents();
        this.con = con;
        model = (DefaultTableModel) jXTable2.getModel();
        jXTable2.getColumnExt(7).setMaxWidth(0);
        jXTable2.getColumnExt(7).setMinWidth(0);
        jXTable2.getColumnExt(7).setPreferredWidth(0);
        wypiszzDB();
        jXTable2.setModel(model);
    }

    public listaKlient(Connector con, int typ) throws SQLException {
        initComponents();
        this.con = con;
        model = (DefaultTableModel) jXTable2.getModel();
        jXTable2.getColumnExt(7).setMaxWidth(0);
        jXTable2.getColumnExt(7).setMinWidth(0);
        jXTable2.getColumnExt(7).setPreferredWidth(0);
        wypiszzDB();
        jXTable2.setModel(model);

        jButton1.setVisible(false);
        jButton2.setVisible(false);
        Usuń.setVisible(false);
        
        this.typ=typ;
    }

    private void wypiszzDB() throws SQLException {
        FirmaDB firmaDB = new FirmaDB(con);
        ArrayList<Firma> lista = firmaDB.selectFirma();
        model.setRowCount(0);
        int liczba = lista.size();
       
        for (int i = 0; i < liczba; i++) {
            model.insertRow(i, new Object[]{lista.get(i).getNazwa(), lista.get(i).getNip(),
                lista.get(i).getNrBudynku(), lista.get(i).getNrLokalu(), lista.get(i).getUlica(),
                lista.get(i).getKodPocztowy(), lista.get(i).getMiejscowosc(), lista.get(i).getId()});

        }

    }

    public void wypiszzDB(String szukaj) throws SQLException {
        FirmaDB firmaDB = new FirmaDB(con);
        ArrayList<Firma> lista = firmaDB.selectFirma(szukaj);
        model.setRowCount(0);
        int liczba = lista.size();
        for (int i = 0; i < liczba; i++) {
            model.insertRow(i, new Object[]{lista.get(i).getNazwa(), lista.get(i).getNip(),
                lista.get(i).getNrBudynku(), lista.get(i).getNrLokalu(), lista.get(i).getUlica(),
                lista.get(i).getKodPocztowy(), lista.get(i).getMiejscowosc(), lista.get(i).getId()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Usuń = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jXTable2 = new org.jdesktop.swingx.JXTable();
        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jButton3 = new javax.swing.JButton();

        Usuń.setText("Usuń");
        Usuń.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuńActionPerformed(evt);
            }
        });

        jButton1.setText("Edytuj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nowy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jXTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nazwa", "NIP", "nr budynku", "nr lokalu", "ulica", "kod Pocztowy", "Miejscowosc", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable2.getTableHeader().setReorderingAllowed(false);
        jXTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jXTable2);

        jXSearchField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXSearchField1MouseClicked(evt);
            }
        });
        jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXSearchField1ActionPerformed(evt);
            }
        });
        jXSearchField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jXSearchField1KeyReleased(evt);
            }
        });

        jButton3.setText("Odśwież");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Usuń)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Usuń)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void UsuńActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuńActionPerformed
        if (selectID != "") {
            JDialog.setDefaultLookAndFeelDecorated(true);
            int response;
            //response = JOptionPane.showConfirmDialog(null,"Czy ","Czy ?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,"Tak");
            Object options[] = {"Tak", "Nie"};
            response = JOptionPane.showOptionDialog(null, "Czy usunąć zaznaczonego klienta?", "Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (response == JOptionPane.YES_OPTION) {
                FirmaDB firma = new FirmaDB(con);
                firma.deleteFirma(selectID);
                selectID = null;

                try {
                    model.setRowCount(0);
                    wypiszzDB();
                } catch (SQLException ex) {
                    Logger.getLogger(listaKlient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_UsuńActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!"".equals(selectID)) {

            try {
                edycjaKlient okno = null;
                okno = new edycjaKlient(selectID, con);
                okno.setVisible(true);
                JFrame f = new JFrame();
                f.add(okno);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setSize(640, 480);
                f.setVisible(true);
                f.setTitle("Hurtownia - Edycja Klienta");
                f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));
            } catch (SQLException ex) {
                Logger.getLogger(listaKlient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        nowyKlientFrame f = null;
        try {
            f = new nowyKlientFrame(con);
        } catch (SQLException ex) {
            Logger.getLogger(listaKlient.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(580, 380);
        f.setVisible(true);
        f.setResizable(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jXTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable2MouseClicked
        // TODO add your handling code here:

        selectID = jXTable2.getValueAt(jXTable2.getSelectedRow(), 7).toString();
        
        selectName = jXTable2.getValueAt(jXTable2.getSelectedRow(), 0).toString();
        if (evt.getClickCount() == 2&&typ!=1) {
            JFrame f = new JFrame();
            Klient nk = null;
            try {
                nk = new Klient(selectID, con);
            } catch (SQLException ex) {
                Logger.getLogger(listaKlient.class.getName()).log(Level.SEVERE, null, ex);
            }
            f.add(nk);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setSize(640, 480);
            f.setVisible(true);
            f.setTitle("Hurtownia - Klient");
            f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));
        }
    }//GEN-LAST:event_jXTable2MouseClicked

    private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
        model.setRowCount(0);
        try {
            wypiszzDB(jXSearchField1.getText());
        } catch (SQLException ex) {
            Logger.getLogger(listaKlient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jXSearchField1ActionPerformed

    private void jXSearchField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jXSearchField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jXSearchField1KeyReleased

    private void jXSearchField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXSearchField1MouseClicked
           // TODO add your handling code here:
    }//GEN-LAST:event_jXSearchField1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0);
        try {
            wypiszzDB(jXSearchField1.getText());
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Usuń;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    private org.jdesktop.swingx.JXTable jXTable2;
    // End of variables declaration//GEN-END:variables
}
