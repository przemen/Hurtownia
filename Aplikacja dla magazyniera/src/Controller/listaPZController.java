/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.edytujPZModel;
import Model.listaPZModel;
import Model.nowyPZModel;
import Model.podgladPZModel;
import View.edytujPZView;
import View.listaPZView;
import View.nowyPZView;
import View.podgladPZView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek
 */
public final class listaPZController implements ListaController {

    private listaPZModel pzModel;
    private listaPZView pzView;
    private ListaController controllr;

    public listaPZController(listaPZModel pzModel, listaPZView pzView) throws SQLException {
        this.pzModel = pzModel;
        this.pzView = pzView;
        this.controllr = this;
        this.pzModel.setDTM(pzView.gtDTM());
        this.pzView.addData1Lis(new DataOdLis());
        this.pzView.addData2Lis(new DataDoLis());
        this.pzView.addTableLis(new TableMousLis());
        this.pzView.jButton3addActionListener(new zatwierdz());
        this.pzView.jButton1addActionListener(new newLis());
        this.pzView.jButton4addActionListener(new edytujLis());
                this.pzView.addSerchLis(new SzukajLis());
 
        refresh();
    }

    @Override
    public void refresh() throws SQLException {
        this.pzView.wypisz(pzModel.getDTM(), pzModel.SelectTowar());
    }
    
    

    class newLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new nowyPZController(new nowyPZModel(pzModel.getCon()), new nowyPZView(), controllr);
            } catch (SQLException ex) {

            }

        }
    }

    class odswiez implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(listaPZController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class zatwierdz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!pzModel.isEditable()) {

                try {
                    pzModel.zatwierdz();
                    refresh();
                } catch (SQLException ex) {
                    Logger.getLogger(listaPZController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class edytujLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!pzModel.isEditable()) {

                try {
                    edytujPZModel epm = new edytujPZModel(pzModel.getCon());
                    edytujPZView epv = new edytujPZView();
                    new edytujPZController(epm, epv, controllr, pzModel.getString());
                } catch (SQLException ex) {
                    Logger.getLogger(listaPZController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    class DataOdLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            String date;
            try {
                date = DATE_FORMAT.format(pzView.dataOd());
                pzModel.setDataOD(date);
                refresh();

            } catch (NullPointerException ex) {
            } catch (SQLException ex) {
            }

        }
    }

    class DataDoLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            String date;
            try {
                date = DATE_FORMAT.format(pzView.dataDo());
                pzModel.setDataDO(date);
                refresh();

            } catch (NullPointerException ex) {
                pzModel.setDataDO("2100-12-31");
                try {
                    refresh();
                } catch (SQLException ex1) {

                }
            } catch (SQLException ex) {
            }
        }
    }

    class TableMousLis implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            pzModel.setString((String) pzView.getjXTable1().getValueAt(pzView.getjXTable1().getSelectedRow(), 5));
            pzModel.setEditable((boolean) pzView.getjXTable1().getValueAt(pzView.getjXTable1().getSelectedRow(), 4));

            pzView.ustawEdycja(!(boolean) pzView.getjXTable1().getValueAt(pzView.getjXTable1().getSelectedRow(), 4));
            if (e.getClickCount() == 2) {

                try {
                    new podgladPZController(new podgladPZModel(pzModel.getCon()), new podgladPZView(), controllr, pzModel.getString());
                } catch (SQLException ex) {
                    Logger.getLogger(listaPZController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }
    
    class SzukajLis implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            pzModel.setSelectString(pzView.getSzukaj());
            try {
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(listaWZController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
