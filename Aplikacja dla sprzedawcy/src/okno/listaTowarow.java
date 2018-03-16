/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okno;

import db.Connector;
import db.Kategoria;
import db.KategoriaDB;
import db.Towar;
import db.TowarDB;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Przemek
 */
public class listaTowarow extends javax.swing.JPanel {

    /**
     * Creates new form listaTowarow
     */
    DefaultTableModel model;
    public Connector con;
    public String SelectID = "";
    public String SelectNazwa = "";
    public String SelectJM = "";
    public String SelectCena = "";
    public String SelectVAT = "";
    private int typ;
    private String kat;
    private DecimalFormat df =  new DecimalFormat("###,##0.00");

    public listaTowarow(Connector con) throws SQLException {
        initComponents();
        this.con = con;
        model = (DefaultTableModel) Table.getModel();
        wypiszzDB();
        Table.getColumnExt(9).setMinWidth(0);
        Table.getColumnExt(9).setMaxWidth(0);
        Table.getColumnExt(9).setWidth(0);

         

    }

    public listaTowarow(Connector con, int typ) throws SQLException {
        initComponents();
        this.con = con;
        model = (DefaultTableModel) Table.getModel();
        wypiszzDB();
        
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        this.typ = typ;
        
        Table.getColumnExt(9).setMinWidth(0);
       Table.getColumnExt(9).setMaxWidth(0);
       Table.getColumnExt(9).setWidth(0);
       

    }

    public void wypiszzDB() throws SQLException {
        TowarDB towarDB = new TowarDB(con);
        ArrayList<Towar> lista = towarDB.selectTowarInnerJoin();
        model.fireTableDataChanged();
        int liczba = lista.size();
       
        for (int i = 0; i < liczba; i++) {
            double netto = 0;
            double vat = 0;
            double brutto = 0;
            String net = lista.get(i).getCena();
            String v = lista.get(i).getVAT();
            String br = "";

            try {

                netto = Double.parseDouble(lista.get(i).getCena());
                vat = Double.parseDouble(lista.get(i).getVAT());

                vat = vat / 100;

                brutto = netto + netto * vat;
                br = Double.toString(brutto);

            } catch (NullPointerException e) {
               
                br = "";

            }
DecimalFormat df = new DecimalFormat("#,##0.00");
            model.insertRow(i, new Object[]{lista.get(i).getNazwa(), lista.get(i).getKodTowaru(),
                lista.get(i).getKodKreskowy(), lista.get(i).getKategoria(), lista.get(i).getDostepne(),
                lista.get(i).getJednostkaMiary(), df.format(netto), v, df.format(brutto),
                lista.get(i).getId()});

        }
    }

    public void wypiszzDB(String string) throws SQLException {
        TowarDB towarDB = new TowarDB(con);
        ArrayList<Towar> lista = towarDB.selectTowarInnerJoin(string);
        model.fireTableDataChanged();
        int liczba = lista.size();
        
        for (int i = 0; i < liczba; i++) {
            double netto = 0;
            double vat = 0;
            double brutto = 0;
            String net = lista.get(i).getCena();
            String v = lista.get(i).getVAT();
            String br = "";

            try {

                netto = Double.parseDouble(lista.get(i).getCena());
                vat = Double.parseDouble(lista.get(i).getVAT());

                vat = vat / 100;

                brutto = netto + netto * vat;
                
                br = df.format(brutto);

            } catch (NullPointerException e) {
                brutto = netto + netto * vat;
                br = "";

            }
            
            model.insertRow(i, new Object[]{lista.get(i).getNazwa(), lista.get(i).getKodTowaru(),
                lista.get(i).getKodKreskowy(), lista.get(i).getKategoria(), lista.get(i).getDostepne(),
                lista.get(i).getJednostkaMiary(), net, v, br,
                lista.get(i).getId()});

        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        layoutStyle1 = new org.jdesktop.layout.LayoutStyle();
        layoutStyle2 = new org.jdesktop.layout.LayoutStyle();
        jDBCLoginService1 = new org.jdesktop.swingx.auth.JDBCLoginService();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table = new org.jdesktop.swingx.JXTable();
        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jXCollapsiblePane1 = new org.jdesktop.swingx.JXCollapsiblePane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jXTable1);

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nazwa", "Kod Towatu", "Kod Kreskowy", "Kategoria", "Ilość", "j.m.", "Cena netto", "VAT", "Cena brutto", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.getTableHeader().setReorderingAllowed(false);
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(1).setMinWidth(120);
            Table.getColumnModel().getColumn(1).setPreferredWidth(120);
            Table.getColumnModel().getColumn(1).setMaxWidth(120);
            Table.getColumnModel().getColumn(2).setMinWidth(120);
            Table.getColumnModel().getColumn(2).setPreferredWidth(120);
            Table.getColumnModel().getColumn(2).setMaxWidth(120);
            Table.getColumnModel().getColumn(3).setMinWidth(120);
            Table.getColumnModel().getColumn(3).setPreferredWidth(120);
            Table.getColumnModel().getColumn(3).setMaxWidth(120);
            Table.getColumnModel().getColumn(4).setMinWidth(75);
            Table.getColumnModel().getColumn(4).setPreferredWidth(75);
            Table.getColumnModel().getColumn(4).setMaxWidth(75);
            Table.getColumnModel().getColumn(5).setMinWidth(40);
            Table.getColumnModel().getColumn(5).setPreferredWidth(40);
            Table.getColumnModel().getColumn(5).setMaxWidth(40);
            Table.getColumnModel().getColumn(6).setMinWidth(75);
            Table.getColumnModel().getColumn(6).setPreferredWidth(75);
            Table.getColumnModel().getColumn(6).setMaxWidth(75);
            Table.getColumnModel().getColumn(7).setMinWidth(40);
            Table.getColumnModel().getColumn(7).setPreferredWidth(40);
            Table.getColumnModel().getColumn(7).setMaxWidth(40);
            Table.getColumnModel().getColumn(8).setMinWidth(75);
            Table.getColumnModel().getColumn(8).setPreferredWidth(75);
            Table.getColumnModel().getColumn(8).setMaxWidth(75);
            Table.getColumnModel().getColumn(9).setMinWidth(0);
            Table.getColumnModel().getColumn(9).setPreferredWidth(0);
            Table.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        jXSearchField1.setToolTipText("Wyszukaj towar");
        jXSearchField1.setLayoutStyle(org.jdesktop.swingx.JXSearchField.LayoutStyle.MAC);
        jXSearchField1.setPrompt("Wyszukaj towar");
        jXSearchField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXSearchField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Nowy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edytuj");

        jButton3.setText("Ustal Cene");
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
                        .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 733, Short.MAX_VALUE)
                                .addComponent(jXCollapsiblePane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXCollapsiblePane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jXSearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXSearchField1ActionPerformed

        model.setRowCount(0);
        try {
            wypiszzDB(jXSearchField1.getText());
        } catch (SQLException ex) {
            Logger.getLogger(listaTowarow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jXSearchField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // nowy towar
        nowyTowarFrame nt = null;
        try {
            nt = new nowyTowarFrame(con, this);
        } catch (SQLException ex) {
            Logger.getLogger(listaTowarow.class.getName()).log(Level.SEVERE, null, ex);
        }
        nt.setVisible(true);
        nt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nt.setResizable(false);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
       try { // TODO add your handling code here:
        SelectNazwa = Table.getValueAt(Table.getSelectedRow(), 0).toString();
        SelectID = Table.getValueAt(Table.getSelectedRow(), 9).toString();
        SelectJM = Table.getValueAt(Table.getSelectedRow(), 5).toString();
        } catch (NullPointerException ex) {
        } catch (IndexOutOfBoundsException ex){}
       
    }//GEN-LAST:event_TableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!SelectID.equals("")) {

            panelCena pc = new panelCena();
            pc.jLabel3.setVisible(false);
            pc.jXDatePicker2.setVisible(false);
            pc.jXDatePicker1.setDate(new Date());
            int response = JOptionPane.showConfirmDialog(null, pc, "Ustal Cenę",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (response == 0) {
                Double brutto;

                Date dw = pc.jXDatePicker1.getDate();

                SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
                String date = DATE_FORMAT.format(dw);

                try {

                    String query = "IF EXISTS (SELECT * FROM Cennik WHERE  DataKoncowa IS NULL AND  IdTowar = " + SelectID + "  ) "
                            + "UPDATE Cennik "
                            + "SET DataKoncowa = '" + date + "' "
                            + "WHERE IdTowar = " + SelectID + " AND (DataKoncowa IS NULL) "
                            + " INSERT INTO Cennik (IdTowar, Cena, DataPoczatkowa) VALUES (" + SelectID + ", " + pc.jTextField2.getText().replaceAll(",", ".") + ",'" + date + "')";
                    con.query(query);
                    model.setRowCount(0);

                    wypiszzDB();

                    // TODO add your handling code here:
                } catch (SQLException ex) {
                    Logger.getLogger(listaTowarow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }


    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXTable Table;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private org.jdesktop.swingx.auth.JDBCLoginService jDBCLoginService1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXCollapsiblePane jXCollapsiblePane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    private org.jdesktop.swingx.JXTable jXTable1;
    private org.jdesktop.layout.LayoutStyle layoutStyle1;
    private org.jdesktop.layout.LayoutStyle layoutStyle2;
    // End of variables declaration//GEN-END:variables
}
