/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import Enity.Firma;
import Model.listaDostawcaModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemek
 */
public class wybierzDostawce extends javax.swing.JPanel {

    /**
     * Creates new form wybierzDostawce
     */
    listaDostawcaModel lDM;
    public DefaultTableModel model;
    public String SelectedID = "0";

    public wybierzDostawce(listaDostawcaModel lDM) {
        initComponents();
        this.lDM = lDM;
                model = (DefaultTableModel) jXTable2.getModel();
        lDM.setSelectString("");
        try {
            wypisz(lDM.selectFirma());
        } catch (SQLException ex) {
            
        }

    }
 public void wypisz( ArrayList<Firma> lista) {
     model.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            model.insertRow(i, new Object[]{lista.get(i).getNazwa(), lista.get(i).getNip(),
                lista.get(i).getNrBudynku(), lista.get(i).getNrLokalu(), lista.get(i).getUlica(),
                lista.get(i).getKodPocztowy(), lista.get(i).getMiejscowosc(), lista.get(i).getId()});

        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jXTable2 = new org.jdesktop.swingx.JXTable();

        jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXSearchField1ActionPerformed(evt);
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
                false, true, false, false, false, false, false, false
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
        if (jXTable2.getColumnModel().getColumnCount() > 0) {
            jXTable2.getColumnModel().getColumn(6).setResizable(false);
            jXTable2.getColumnModel().getColumn(7).setMinWidth(0);
            jXTable2.getColumnModel().getColumn(7).setPreferredWidth(0);
            jXTable2.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed
        try {
            lDM.setSelectString(jXSearchField1.getText());
            
            wypisz(lDM.selectFirma());
        } catch (SQLException ex) {
            Logger.getLogger(wybierzDostawce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jXSearchField1ActionPerformed

    private void jXTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable2MouseClicked
        // TODO add your handling code here:
        SelectedID= jXTable2.getValueAt(jXTable2.getSelectedRow(), 7).toString();
        System.out.print(SelectedID);
    }//GEN-LAST:event_jXTable2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    private org.jdesktop.swingx.JXTable jXTable2;
    // End of variables declaration//GEN-END:variables
}
