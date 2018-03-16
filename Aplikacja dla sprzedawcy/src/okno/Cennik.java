/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okno;

import db.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemek
 */
public class Cennik extends javax.swing.JPanel {

    /**
     * Creates new form Cennik
     */
    Connector con;
    DefaultTableModel model;
    
    public Cennik(Connector con) throws SQLException {
        initComponents();
        this.con=con;
        model = (DefaultTableModel) jXTable1.getModel();
        wypiszDB();
    }

   
    
public void wypiszDB() throws SQLException {
        String query;
        query = "   SELECT Towar.Nazwa, Cena, DataPoczatkowa, DataKoncowa, JednostkaMiary.skrot, VAT FROM TOWAR "
                + " INNER JOIN  Cennik ON Towar.Id = Cennik.IdTowar "
                + " INNER JOIN JednostkaMiary ON Towar.JednostkaMiary = JednostkaMiary.Id";
        
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            DecimalFormat df = new DecimalFormat("#,###.00");
           
            Double VAT;
            Double Netto=0.0;
            Double Brutto=0.0;
            try{
                VAT = Double.parseDouble(rs.getString("VAT"));
                Netto = Double.parseDouble(rs.getString("Cena"));
                VAT = VAT/100;
                Brutto = Netto*VAT+Netto;
                
            }catch(NullPointerException ex){}
            
            
            model.addRow(new Object[]{rs.getString(1), df.format(rs.getDouble("Cena")), df.format(Brutto), rs.getString("skrot"), rs.getDate(3), rs.getDate(4)});
            
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nazwa", "Cenna Netto", "Cena Brutto", "j.m", "Data Od", "Data Do"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jXTable1);
        if (jXTable1.getColumnModel().getColumnCount() > 0) {
            jXTable1.getColumnModel().getColumn(0).setResizable(false);
            jXTable1.getColumnModel().getColumn(1).setMinWidth(120);
            jXTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jXTable1.getColumnModel().getColumn(1).setMaxWidth(120);
            jXTable1.getColumnModel().getColumn(2).setMinWidth(120);
            jXTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
            jXTable1.getColumnModel().getColumn(2).setMaxWidth(120);
            jXTable1.getColumnModel().getColumn(3).setMinWidth(75);
            jXTable1.getColumnModel().getColumn(3).setPreferredWidth(75);
            jXTable1.getColumnModel().getColumn(3).setMaxWidth(75);
            jXTable1.getColumnModel().getColumn(4).setMinWidth(120);
            jXTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
            jXTable1.getColumnModel().getColumn(4).setMaxWidth(120);
            jXTable1.getColumnModel().getColumn(5).setMinWidth(120);
            jXTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
            jXTable1.getColumnModel().getColumn(5).setMaxWidth(120);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables
}
