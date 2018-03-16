/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.listaTowarModel;
import Model.nowyTowarModel;
import View.listaTowarView;
import View.nowyTowarView;
import Enity.Connector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek
 */
public class listaTowarControler implements ListaController {

    private final listaTowarModel towarModel;
    private final listaTowarView towarView;

    public ListaController controller;
    private String selectID;

    /**
     *
     * @param towarModel
     * @param towarView
     * @throws SQLException
     */
    public listaTowarControler(listaTowarModel towarModel, listaTowarView towarView) throws SQLException {

        this.towarModel = towarModel;
        this.towarView = towarView;
        this.towarView.addListenerJxtable(new JxTableLis());

        this.towarView.wypisz(this.towarModel.SelectTowar());
        this.towarView.addListenerJxsearch(new jxSearchLis());
        this.towarView.addListenerJbutton(new jButtonLIS());
        this.towarView.addListenerOdswierz(new jButtonOdsLIS());
        controller = this;
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void refresh() throws SQLException {
        this.towarView.wypisz(this.towarModel.SelectTowar());
    }

    class JxTableLis implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            towarModel.setSelectedID((String) towarView.getjXTable2().getValueAt(towarView.getjXTable2().getSelectedRow(), 5));
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

    class jxSearchLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                towarModel.setSearchSting(towarView.getjXSearchField1().getText());
                towarView.wypisz(towarModel.SelectTowar());

            } catch (SQLException ex) {

            }
        }
    }

    class jButtonLIS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            nowyTowarModel ntModel = new nowyTowarModel(towarModel.getCon());
            nowyTowarView nTView = new nowyTowarView(ntModel);

            try {
                new nowyTowarController(nTView, ntModel, controller);
            } catch (SQLException ex) {
                Logger.getLogger(listaTowarControler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class jButtonOdsLIS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                controller.refresh();
            } catch (SQLException ex) {
                Logger.getLogger(listaTowarControler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
