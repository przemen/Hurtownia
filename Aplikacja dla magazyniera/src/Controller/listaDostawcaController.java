/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.listaDostawcaModel;
import Model.nowyDostawcaModel;
import View.listaDostawcaView;
import View.nowyDostawcaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemek
 */
public class listaDostawcaController implements ListaController {

    private final listaDostawcaModel dlModel;
    private final listaDostawcaView dlView;
    private final ListaController controller;

    public listaDostawcaController(listaDostawcaModel dlModel, listaDostawcaView dlView) throws SQLException {
        this.dlModel = dlModel;
        this.dlView = dlView;

        this.dlModel.setDTM((DefaultTableModel) this.dlView.getjXTable5().getModel());

        this.dlView.wypisz(this.dlModel.getDTM(), this.dlModel.selectFirma());
        this.dlView.addJxSearchLis(new jxSearchLis());
        this.dlView.addjButton2Lis(new btnOdswiezLis());
        this.dlView.addjButton1Lis(new btnNewLis());
        controller = this;

    }

    @Override
    public void refresh() throws SQLException {
        dlModel.setRowCount0();
        dlView.wypisz(dlModel.getDTM(), dlModel.selectFirma());

    }

   

    class jxSearchLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                dlModel.setRowCount0();
                dlModel.setSelectString(dlView.getjXSearchField1().getText());
                dlView.wypisz(dlModel.getDTM(), dlModel.selectFirma());
            } catch (SQLException ex) {
            }
        }
    }

    class btnOdswiezLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                refresh();
            } catch (SQLException ex) {

            }
        }
    }

    class btnNewLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new nowyDostawcaController(new nowyDostawcaModel(dlModel.getCon()), new nowyDostawcaView(), controller);
        }
    }

}
