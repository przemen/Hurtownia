/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.listaTowarModel;
import Model.nowyWZModel;
import Other.wybierzTowar;
import View.nowyWZView;
import Enity.Firma;
import Enity.WZ;
import Enity.Towar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Przemek
 */
public final class nowyWZController {

    private final nowyWZView wzView;
    private final nowyWZModel wzModel;
    private ListaController controller;

    public nowyWZController(nowyWZModel wzModel, nowyWZView wzView,
            ListaController controller) throws SQLException {
        this.wzModel = wzModel;
        this.wzView = wzView;
        this.controller = controller;

        wzView.addAnulijLis(new AnulujListener());
        wzView.addDodajLis(new DodajListener());
        wzView.addTextLis(new JTextLis());
        wzView.addFirmaTxtis(new FirmaTxtLis());
        wzView.addZapiszLis(new ZapiszListener());
        wzView.addjXTable1Lis(new TableLis(), new TableKLis());
        wzView.ustawNumer(numer(wzModel.lastNumer()));

        wzView.setDate1(new Date());

        wzView.ustawNumer(numer(wzModel.lastNumer()));

    }

    public nowyWZController(nowyWZModel wzModel, nowyWZView wzView) throws SQLException {
        this.wzModel = wzModel;
        this.wzView = wzView;

        wzView.addAnulijLis(new AnulujListener());
        wzView.addDodajLis(new DodajListener());
        wzView.addTextLis(new JTextLis());
        wzView.addFirmaTxtis(new FirmaTxtLis());
        wzView.addZapiszLis(new ZapiszListener());
        wzView.addjXTable1Lis(new TableLis(), new TableKLis());
        wzView.ustawNumer(numer(wzModel.lastNumer()));

        wzView.setDate1(new Date());

        wzView.ustawNumer(numer(wzModel.lastNumer()));

    }

    public void licz() {
        JXTable jxt = wzView.getjXTable1();
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
        wzView.ustawSuma(suma);
    }

    public boolean walidacja() {
        boolean valid = true;
        for (int i = 0; i < wzView.getDTM().getRowCount(); i++) {
            if (wzView.getjXTable1().getValueAt(i, 4).toString().equals("")
                    || wzView.getjXTable1().getValueAt(i, 1).toString().equals("")
                    || wzView.getjXTable1().getValueAt(i, 3).toString().equals("")) {
                valid = false;
                break;
            }
        }

        return valid;
    }

    public String numer(String numer) {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        String nrY = "";
        String nrM = "";
        String nr = "";

        if (!numer.isEmpty()) {
            nrY = numer.substring(numer.length() - 4, numer.length());
            nrM = numer.substring(numer.length() - 7, numer.length() - 5);
            nr = numer.substring(0, numer.length() - 8);

            int nrY_int = Integer.parseInt(nrY);
            int nrM_int = Integer.parseInt(nrM);
            int nr_int = Integer.parseInt(nr);

            boolean flag = true;
            if (nrY_int < year || nrM_int < month) {
                nr_int = 1;
                flag = false;
            }
            if (nrY_int < year) {
                nrY_int = year;
            }
            if (nrM_int < month) {
                nrM_int = month;
            }

            if (flag) {
                nr = Integer.toString(nr_int + 1);
            } else {
                nr = Integer.toString(nr_int);
            }

            if (nrM_int < 10) {
                nrM = "0" + Integer.toString(nrM_int);
            } else {
                nrM = Integer.toString(nrM_int);
            }
            nrY = Integer.toString(nrY_int);

        } else {

            if (month < 10) {
                nrM = "0" + Integer.toString(month);
            } else {
                nrM = Integer.toString(month);
            }
            nrY = Integer.toString(year);

            nr = "1";
        }
        String newNumer = nr + "/" + nrM + "/" + nrY;

        return newNumer;
    }

    class AnulujListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wzView.dispose();
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
                string = wzView.getjXTextField1().getText();
                Firma firma = null;

                try {
                    firma = wzModel.selectFirmaID(string);
                    if (!firma.getNazwa().equals("")) {
                        wzModel.setIdFirma(firma.getId().toString());
                        wzView.ustawNazwaFirma(firma);
                        System.out.println(wzModel.getIdFirma());
                    }
                } catch (SQLException | NullPointerException ex) {

                }

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

                id = wzView.getjTextField1().getText();
                Towar towar;
                towar = null;
                try {
                    towar = wzModel.getTowarS(id);
                    if (!towar.getNazwa().equals("")) {
                        wzView.getjTextField1().setText("");
                        wzView.addRow(towar);
                    }

                } catch (SQLException ex) {

                }

            }
        }
    }

    class ZapiszListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            WZ wz = wzView.getWZ();
            try {
                if (!wzModel.getIdFirma().equals("")) {
                    if (wzView.getDTM().getRowCount() != 0) {

                        if (walidacja()) {
                            wz.setNumer(numer(wzModel.lastNumer()));

                            if (wzView.zatwierdz()) {
                                wzModel.insertWZconf(wzView.getDTM(), wz, wzView.getjXTable1());
                            } else {
                                wzModel.insertWZnonConf(wzView.getDTM(), wz, wzView.getjXTable1());
                            }
                            wzView.dispose();
                            if (controller != null) {
                                controller.refresh();
                            }
                        } else {
                            wzView.ErrorMesage("Proszę sprawdzić dane", "Błąd");
                        }

                    } else {
                        wzView.ErrorMesage("WZ musi zawierac conajmniej jedną pozycję", "Błąd");
                    }
                } else {
                    if (wzView.getDTM().getRowCount() == 0) {
                        wzView.ErrorMesage("WZ musi zawierac conajmniej jedną pozycję", "Błąd");
                    }
                    wzView.ErrorMesage("Proszę wybrać dostawcę", "Brak wybranego dostawcy");
                }

            } catch (SQLException ex) {
            }
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

    class DodajListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String id = "0";
            try {
                id = wzView.selectTowar(new wybierzTowar(new listaTowarModel(wzModel.getCon())));
                System.out.println(id);
            } catch (SQLException ex) {
            }
            Towar towar;
            towar = null;
            if (!id.equals("0")) {
                try {
                    towar = wzModel.getTowar(id);
                } catch (SQLException ex) {
                }

                wzView.addRow(towar);
            }
        }
    }

    class TableKLis implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            licz();
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

}
