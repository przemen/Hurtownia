/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.listaTowarModel;
import Model.nowyTowarModel;
import View.nowyTowarView;
import Enity.Connector;
import Enity.JednostkaMiary;
import Enity.Kategoria;
import Enity.Towar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Przemek
 */
public class nowyTowarController {

    private final nowyTowarView nTView;
    private final nowyTowarModel nTModel;
    private Connector con;
    private final ListaController controller;

    public nowyTowarController(nowyTowarView nTView, nowyTowarModel nTModel, ListaController controller) throws SQLException {

        this.nTModel = nTModel;
        this.nTView = nTView;
        this.controller = controller;

        this.nTView.addDodajLis(new bttonDodajLis());
        this.nTView.addAnulujLis(new bttonAnulujLis());
        this.nTView.addNowaKategLis(new bttonNowaKategoriaLis());
        this.nTView.addNowaJMLis(new bttonNowaJM());

        this.nTView.wypiszKategoria(this.nTModel.selectKategoria());
        this.nTView.wypiszJM(this.nTModel.selectJednostkaMiary());

    }

    private boolean validKod() throws SQLException {
        if (!nTView.getKod().equals("")) {
            if (nTModel.liczbaKod(nTView.getKod()) > 0) {
                nTView.errorMessage("Istnieje już towar o kodzie produktu " + nTView.getKod(), "Error");
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    class bttonDodajLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Towar towar = nTView.getTowar();
                if (validKod() && !nTView.getNazwa().equals("")) {
                    nTModel.insertTowar(towar);
                    nTView.dispose();
                    controller.refresh();
                } else {
                      nTView.errorMessage("Proszę sprawdzić dane", "Error");
                }
            } catch (SQLException ex) {
                Logger.getLogger(nowyTowarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class bttonAnulujLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            nTView.dispose();
        }

    }

    class bttonNowaKategoriaLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Kategoria kategoria = nTView.newKategoria();
            if (kategoria.getNazwa().equals("")) {
            } else {
                nTModel.insertKategoria(kategoria);
                try {
                    nTView.wypiszKategoria(nTModel.selectKategoriaOrdDESC());
                } catch (SQLException ex) {
                    Logger.getLogger(nowyTowarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class bttonNowaJM implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JednostkaMiary jm = nTView.newJednostkaMiary();

            if (jm.getSkrot().equals("") || jm.getNazwa().equals("")) {
            } else {
                nTModel.insertJednostkaMiary(jm);
                try {
                    nTView.wypiszJM(nTModel.selectJednostkaMiaryOrdDESC());
                } catch (SQLException ex) {
                    Logger.getLogger(nowyTowarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
