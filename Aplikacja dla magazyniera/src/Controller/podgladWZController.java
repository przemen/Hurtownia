/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.podgladWZModel;
import View.podgladWZView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek
 */
public class podgladWZController {

    private final podgladWZModel pWZModel;
    private final podgladWZView pWZView;
    private ListaController controller;
    public final String id;

    public podgladWZController(podgladWZModel pWZModel, podgladWZView pWZView, String id) throws SQLException {
        this.pWZModel = pWZModel;
        this.pWZView = pWZView;
        this.id = id;
        run();
    }

    public podgladWZController(podgladWZModel pWZModel, podgladWZView pWZView,
            ListaController controller, String id) throws SQLException {
        this.pWZModel = pWZModel;
        this.pWZView = pWZView;
        this.controller = controller;
        this.id = id;
        run();
    }

    public void run() throws SQLException {
        pWZModel.setIdWZ(id);
        pWZView.ustawDane(pWZModel.getWZ());
        pWZView.wypisz(pWZModel.getPozycjaWZ());
        
        pWZView.AnulujLis(new AnulujLis());
        pWZView.ZatwierdzLis(new ZatwierdzLis());
    }

    class ZatwierdzLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                pWZModel.wydano();
                pWZView.akt();
                controller.refresh();
            } catch (SQLException ex) {
                Logger.getLogger(podgladWZController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         }
    }

    class AnulujLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pWZView.dispose();
        }
    }
}
