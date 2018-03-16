/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Enity.WZ;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Przemek
 */
public class listaWZView extends javax.swing.JFrame {

    /**
     * Creates new form listaWZView
     */
    DecimalFormat df = new DecimalFormat("###,##0.00");

    public listaWZView() {
        initComponents();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setVisible(true);
        this.setTitle("Lista WZ");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Date next = new Date(new Date().getTime() - (15 * 1000 * 60 * 60 * 24) - (15 * 1000 * 60 * 60 * 24));
        /* */
        jXDatePicker1.setDate(next);

        jButton3.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numer", "Klient", "Data Wystawienia", "Wartość", "Wydano", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jXTable1);
        if (jXTable1.getColumnModel().getColumnCount() > 0) {
            jXTable1.getColumnModel().getColumn(0).setMinWidth(150);
            jXTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jXTable1.getColumnModel().getColumn(0).setMaxWidth(150);
            jXTable1.getColumnModel().getColumn(2).setMinWidth(150);
            jXTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
            jXTable1.getColumnModel().getColumn(2).setMaxWidth(150);
            jXTable1.getColumnModel().getColumn(3).setMinWidth(150);
            jXTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jXTable1.getColumnModel().getColumn(3).setMaxWidth(150);
            jXTable1.getColumnModel().getColumn(4).setMinWidth(75);
            jXTable1.getColumnModel().getColumn(4).setPreferredWidth(75);
            jXTable1.getColumnModel().getColumn(4).setMaxWidth(75);
            jXTable1.getColumnModel().getColumn(5).setMinWidth(0);
            jXTable1.getColumnModel().getColumn(5).setPreferredWidth(0);
            jXTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jXLabel1.setText("Data Wystawienia od");

        jXLabel2.setText("do");

        jButton2.setText("Odśwież");

        jButton3.setText("Wydano");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void addData1Lis(ActionListener aL) {
        jXDatePicker1.addActionListener(aL);
    }

    public void addData2Lis(ActionListener aL) {
        jXDatePicker2.addActionListener(aL);
    }

    public void addSerchLis(ActionListener aL) {
        jXSearchField1.addActionListener(aL);
    }

    public void addTableLis(MouseListener mL) {
        jXTable1.addMouseListener(mL);
    }

    public String getSzukaj() {
        return jXSearchField1.getText();
    }

    public void addRefreshLis(ActionListener aL) {
        jButton2.addActionListener(aL);
    }

    public Date dataOd() {
        return jXDatePicker1.getDate();
    }

    public Date dataDo() {
        return jXDatePicker2.getDate();
    }

    public JXDatePicker getjXDatePicker1() {
        return jXDatePicker1;
    }

    public void setjXDatePicker1(JXDatePicker jXDatePicker1) {
        this.jXDatePicker1 = jXDatePicker1;
    }

    public JXDatePicker getjXDatePicker2() {
        return jXDatePicker2;
    }

    public void setjXDatePicker2(JXDatePicker jXDatePicker2) {
        this.jXDatePicker2 = jXDatePicker2;
    }

    public JXTable getjXTable1() {
        return jXTable1;
    }

    public void setjXTable1(JXTable jXTable1) {
        this.jXTable1 = jXTable1;
    }

    public void wypisz(DefaultTableModel DTM, ArrayList<WZ> lista) {
        DTM.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getStan() == 1) {
                DTM.addRow(new Object[]{lista.get(i).getNumer(), lista.get(i).getDostawcaNazwa(),
                    lista.get(i).getDataWystawienia(), df.format(lista.get(i).getKwota()), Boolean.TRUE, lista.get(i).getId()});
            } else {
                DTM.addRow(new Object[]{lista.get(i).getNumer(), lista.get(i).getDostawcaNazwa(),
                    lista.get(i).getDataWystawienia(), df.format(lista.get(i).getKwota()), Boolean.FALSE, lista.get(i).getId()});
            }
        }

    }

    public void ustawWydano(boolean stan) {
        jButton3.setEnabled(stan);
    }

    public DefaultTableModel gtDTM() {
        return (DefaultTableModel) jXTable1.getModel();
    }

    public void jButton2addActionListener(ActionListener l) {
        jButton2.addActionListener(l);
    }

    public void jButton3addActionListener(ActionListener l) {
        jButton3.addActionListener(l);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables
}