/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.podgladPZModel;
import View.podgladPZView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek
 */
public class podgladPZController {

    private final podgladPZModel pPZModel;
    private final podgladPZView pPZView;
    private ListaController controller;
    public final String id;

    public podgladPZController(podgladPZModel pPZModel, podgladPZView pPZView, String id) throws SQLException {
        this.pPZModel = pPZModel;
        this.pPZView = pPZView;
        this.id = id;
        run();
    }

    public podgladPZController(podgladPZModel pPZModel, podgladPZView pPZView,
            ListaController controller, String id) throws SQLException {
        this.pPZModel = pPZModel;
        this.pPZView = pPZView;
        this.controller = controller;
        this.id = id;
        run();
    }

    public void run() throws SQLException {
        pPZModel.setIdPZ(id);
        pPZView.ustawDane(pPZModel.getPZ());
        pPZView.wypisz(pPZModel.getPozycjaPZ());
        
        pPZView.AnulujLis(new AnulujLis());
        pPZView.ZatwierdzLis(new ZatwierdzLis());
    }

    class ZatwierdzLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                pPZModel.zatwierdz();
                pPZView.akt();
                controller.refresh();
            } catch (SQLException ex) {
                Logger.getLogger(podgladPZController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         }
    }

    class AnulujLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pPZView.dispose();
        }
    }
}
