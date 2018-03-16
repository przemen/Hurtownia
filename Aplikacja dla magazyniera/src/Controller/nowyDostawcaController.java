/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.nowyDostawcaModel;
import View.nowyDostawcaView;
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
public class nowyDostawcaController {

    private final nowyDostawcaModel ndModel;
    private final nowyDostawcaView ndView;
    private final ListaController controller;

    public nowyDostawcaController(nowyDostawcaModel ndModel, nowyDostawcaView ndView, ListaController controller) {
        this.ndModel = ndModel;
        this.ndView = ndView;
        this.controller = controller;

        this.ndView.addjButton1Lis(new btnAddLis());
        this.ndView.addjButton2Lis(new btnClsLis());
    }

    private boolean validKod() throws SQLException {
        boolean r = false;
        if (ndView.getKod().length() > 0) {
            if (ndModel.liczbaKod(ndView.getKod()) > 0) {
                r = false;
                ndView.errorMessage("Dostawca o kodzie " + ndView.getKod() + " już istnieje\n"
                        + "proszę wybrać nowy kod", "ERROR");
            } else {
                r = true;
            }
        } else {
            r = true;
        }

        return r;
    }

    private boolean validNipUse() throws SQLException {
        boolean r = false;
        if (ndModel.liczbaNIP(ndView.getNip()) > 0) {
            r = false;
            ndView.errorMessage("Dostawca o numerze NIP " + ndView.getNip()
                    + " już istnieje\n"
                    + "proszę sprawdzić NIP", "ERROR");

        } else {
            r = true;
        }

        return r;
    }

    private boolean validNIP() {
        if (isValidNip()) {
            return true;
        } else {
            ndView.errorMessage("NIP " + ndView.getNip() + " jest niepoprawny\n"
                    + "proszę wpisać NIP ponownie", "ERROR");
            System.out.println("NIP " + ndView.getNip() + " jest niepoprawny\n"
                    + "proszę wpisać NIP ponownie");
            return false;
        }
    }

    private String trimInput() {
        return ndView.getNip().replaceAll("\\D*", "");
    }

    public boolean isValidNip() {
        String nip;
        nip = trimInput();
        int nsize = nip.length();
        if (nsize != 10) {
            return false;
        }
        int[] weights = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        int j = 0, sum = 0, control = 0;
        int csum = new Integer(nip.substring(nsize - 1)).intValue();
        if (csum == 0) {
            csum = 10;
        }
        for (int i = 0; i < nsize - 1; i++) {
            char c = nip.charAt(i);
            j = new Integer(String.valueOf(c)).intValue();
            sum += j * weights[i];
        }
        control = sum % 11;
        return (control == csum);
    }

    class btnAddLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
  System.out.println(validNIP()+" "+validNipUse()+" "+ validKod());
                if (validNIP() && validNipUse() && validKod()) {
                    ndModel.insertFirma(ndView.dane());
                    ndView.dispose();
                    controller.refresh();
                }

            } catch (SQLException ex) {
                Logger.getLogger(nowyDostawcaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class btnClsLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ndView.wyczysc();
        }
    }

}
