/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.edytujPZModel;
import Model.listaTowarModel;
import Other.wybierzTowar;
import View.edytujPZView;
import Enity.Firma;
import Enity.PZ;
import Enity.Towar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Przemek
 */
public class edytujPZController {

    private final edytujPZModel ePZModel;
    private final edytujPZView ePZView;
    private ListaController controller;
    public final String id;

    public edytujPZController(edytujPZModel ePZModel, edytujPZView ePZView,
            String id) throws SQLException {
        this.ePZModel = ePZModel;
        this.ePZView = ePZView;
        this.id = id;
        run();
    }

    public edytujPZController(edytujPZModel ePZModel, edytujPZView ePZView, ListaController controller,
            String id) throws SQLException {
        this.ePZModel = ePZModel;
        this.ePZView = ePZView;
        this.controller = controller;
        this.id = id;
        run();
    }

    private void run() throws SQLException {
        ePZModel.setIdPZ(id);
        ePZView.ustawDane(ePZModel.getPZ());
        ePZView.wypisz(ePZModel.getPozycjaPZ());
        ePZView.addTextLis(new JTextLis());
        ePZView.addUsunLis(new usunLis());
        ePZView.addZapiszLis(new zapiszLis());
        ePZView.addDodajLis(new DodajListener());
        ePZView.addjXTable1Lis(new TableLis(), new TableKLis());
        licz();

    }

    public void licz() {
        JXTable jxt = ePZView.getjXTable1();
        double suma = 0.0;
        for (int i = 0; i < jxt.getRowCount(); i++) {

            try {
                Double ilosc = Double.parseDouble((String) jxt.getValueAt(i, 1));
                Double cena = Double.parseDouble((String) jxt.getValueAt(i, 3));

                Double wartosc = ilosc * cena;
                suma += wartosc;
                jxt.setValueAt(wartosc, i, 4);

            } catch (NumberFormatException e) {
            }
        }
        ePZView.ustawSuma(suma);
    }

    public boolean walidacja() {
        boolean valid = true;
        for (int i = 0; i < ePZView.getDTM().getRowCount(); i++) {
            if (ePZView.getjXTable1().getValueAt(i, 4).toString().equals("")
                    || ePZView.getjXTable1().getValueAt(i, 1).toString().equals("")
                    || ePZView.getjXTable1().getValueAt(i, 3).toString().equals("")) {
                valid = false;
                break;
            }
        }

        return valid;
    }

    class AnulujListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ePZView.dispose();
        }
    }

    class FirmaTxtLis implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String string = "";
                string = ePZView.getjXTextField1().getText();
                Firma firma = null;

                try {
                    firma = ePZModel.selectFirmaID(string);
                    if (!firma.getNazwa().equals("")) {

                    }
                } catch (SQLException | NullPointerException ex) {

                }

            }

        }
    }

    class DodajListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String id = "0";
            try {
                id = ePZView.selectTowar(new wybierzTowar(new listaTowarModel(ePZModel.getCon())));
                System.out.println(id);
            } catch (SQLException ex) {
            }
            Towar towar;
            towar = null;
            if (!id.equals("0")) {
                try {
                    towar = ePZModel.getTowar(id);
                } catch (SQLException ex) {
                }

                ePZView.addRow(towar);
            }
        }
    }
class JTextLis implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String id = "";

                id = ePZView.getjTextField1().getText();
                Towar towar;
                towar = null;
                try {
                    towar = ePZModel.getTowarS(id);
                    if (!towar.getNazwa().equals("")) {
                        ePZView.getjTextField1().setText("");
                        ePZView.addRow(towar);
                    }

                } catch (SQLException ex) {

                }

            }
        }
    }

    class usunLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ePZView.usunWiersz(ePZView.getSelectRow());
            licz();
        }
    }

    

    class zapiszLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PZ pz = ePZView.getPZ();
            try {
                if (true) {
                    if (ePZView.getDTM().getRowCount() != 0) {

                        if (walidacja()) {

                            if (ePZView.zatwierdz()) {
                                ePZModel.insertPZconf(ePZView.getDTM(), pz, ePZView.getjXTable1());
                            } else {
                                ePZModel.insertPZnonConf(ePZView.getDTM(), pz, ePZView.getjXTable1());
                            }
                            ePZView.dispose();
                            if (controller != null) {
                                controller.refresh();
                            }
                        } else {
                            ePZView.ErrorMesage("Proszę sprawdzić dane", "Błąd");
                        }

                    } else {
                        ePZView.ErrorMesage("PZ musi zawierac conajmniej jedną pozycję", "Błąd");
                    }
                } else {
                    if (ePZView.getDTM().getRowCount() == 0) {
                        ePZView.ErrorMesage("PZ musi zawierac conajmniej jedną pozycję", "Błąd");
                    }
                    ePZView.ErrorMesage("Proszę wybrać dostawcę", "Brak wybranego dostawcy");
                }

            } catch (SQLException ex) {
            }
        }
    }

    class TableKLis implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            licz();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            licz();
        }
    }

    class TableLis implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            licz();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            licz();
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
