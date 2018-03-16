/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.listaTowarModel;
import Model.nowyPZModel;
import Other.wybierzTowar;
import View.nowyPZView;
import Enity.Firma;
import Enity.PZ;
import Enity.Towar;
import Model.listaDostawcaModel;
import Other.wybierzDostawce;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Przemek
 */
public final class nowyPZController {

    private final nowyPZView pzView;
    private final nowyPZModel pzModel;
    private ListaController controller;
    DecimalFormat df;

    public nowyPZController(nowyPZModel pzModel, nowyPZView pzView,
            ListaController controller) throws SQLException {
        this.df = new DecimalFormat("##0.00");
        this.pzModel = pzModel;
        this.pzView = pzView;
        this.controller = controller;

        pzView.addAnulijLis(new AnulujListener());
        pzView.addDodajLis(new DodajListener());
        pzView.addTextLis(new JTextLis());
        pzView.addFirmaTxtis(new FirmaTxtLis());
        pzView.addZapiszLis(new ZapiszListener());
        pzView.addjXTable1Lis(new TableLis(), new TableKLis());
        pzView.ustawNumer(numer(pzModel.lastNumer()));
        pzView.addUsunLis(new UsunLis());
        pzView.setDate1(new Date());
 pzView.zbazyLis(new ZbazyListener());
        pzView.ustawNumer(numer(pzModel.lastNumer()));

    }

    public nowyPZController(nowyPZModel pzModel, nowyPZView pzView) throws SQLException {
        this.df = new DecimalFormat("##0.00");
        this.pzModel = pzModel;
        this.pzView = pzView;

        pzView.addAnulijLis(new AnulujListener());
        pzView.addDodajLis(new DodajListener());
        pzView.addTextLis(new JTextLis());
        pzView.addFirmaTxtis(new FirmaTxtLis());
        pzView.addZapiszLis(new ZapiszListener());
        pzView.addjXTable1Lis(new TableLis(), new TableKLis());
        pzView.ustawNumer(numer(pzModel.lastNumer()));
        pzView.addUsunLis(new UsunLis());
        pzView.setDate1(new Date());
        pzView.zbazyLis(new ZbazyListener());
        pzView.ustawNumer(numer(pzModel.lastNumer()));

    }

    public void licz() {
        JXTable jxt = pzView.getjXTable1();
        double suma = 0.0;
        for (int i = 0; i < jxt.getRowCount(); i++) {

            try {
                Double ilosc = Double.parseDouble((String) jxt.getValueAt(i, 1).toString().replaceAll(",", "."));
                Double cena = Double.parseDouble((String) jxt.getValueAt(i, 3).toString().replaceAll(",", "."));

                Double wartosc = ilosc * cena;
                suma += wartosc;
                jxt.setValueAt(df.format(wartosc), i, 4);

            } catch (NumberFormatException e) {
            }
        }
        pzView.ustawSuma(suma);
    }

    public boolean walidacja() {
        boolean valid = true;
        for (int i = 0; i < pzView.getDTM().getRowCount(); i++) {
            if (pzView.getjXTable1().getValueAt(i, 4).toString().equals("")
                    || pzView.getjXTable1().getValueAt(i, 1).toString().equals("")
                    || pzView.getjXTable1().getValueAt(i, 3).toString().equals("")) {
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
                nrM_int = month;
            }
            
                nrM_int = month;
            

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
            pzView.dispose();
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
                string = pzView.getjXTextField1().getText();
                Firma firma = null;

                try {
                    firma = pzModel.selectFirmaSearch(string);
                    if (!firma.getNazwa().equals("")) {
                        pzModel.setIdFirma(firma.getId().toString());
                        pzView.ustawNazwaFirma(firma);
                        System.out.println(pzModel.getIdFirma());
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

                id = pzView.getjTextField1().getText();
                Towar towar;
                towar = null;
                try {
                    towar = pzModel.getTowarS(id);
                    if (!towar.getNazwa().equals("")) {
                        pzView.getjTextField1().setText("");
                        pzView.addRow(towar);
                    }

                } catch (SQLException ex) {

                }

            }
        }
    }

    class ZapiszListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PZ pz = pzView.getPZ();
            try {
                if (!pzModel.getIdFirma().equals("")) {
                    if (pzView.getDTM().getRowCount() != 0) {

                        if (walidacja()) {
                            pz.setNumer(numer(pzModel.lastNumer()));

                            if (pzView.zatwierdz()) {
                                pzModel.insertPZconf(pzView.getDTM(), pz, pzView.getjXTable1());
                            } else {
                                pzModel.insertPZnonConf(pzView.getDTM(), pz, pzView.getjXTable1());
                            }
                            pzView.dispose();
                            if (controller != null) {
                                controller.refresh();
                            }
                        } else {
                            pzView.ErrorMesage("Proszę sprawdzić dane", "Błąd");
                        }

                    } else {
                        pzView.ErrorMesage("PZ musi zawierac conajmniej jedną pozycję", "Błąd");
                    }
                } else {
                    if (pzView.getDTM().getRowCount() == 0) {
                        pzView.ErrorMesage("PZ musi zawierac conajmniej jedną pozycję", "Błąd");
                    }
                    pzView.ErrorMesage("Proszę wybrać dostawcę", "Brak wybranego dostawcy");
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
    class ZbazyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Firma firma = null;
            String idFIrma = "0";
            idFIrma=pzView.selectFirma(new wybierzDostawce(new listaDostawcaModel(pzModel.getCon())));
             try {
                    firma = pzModel.selectFirmaID(idFIrma);
                    System.out.println(firma.getNazwa());
                     if (!firma.getNazwa().equals("")) {
                        pzModel.setIdFirma(firma.getId().toString());
                        pzView.ustawNazwaFirma(firma);
                     }
                     
                } catch (SQLException | NullPointerException ex) {

                }
        }
    }
    class DodajListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String id = "0";
            try {
                id = pzView.selectTowar(new wybierzTowar(new listaTowarModel(pzModel.getCon())));
                System.out.println(id);
            } catch (SQLException ex) {
            }
            Towar towar;
            towar = null;
            if (!id.equals("0")) {
                try {
                    towar = pzModel.getTowar(id);
                } catch (SQLException ex) {
                }

                pzView.addRow(towar);
            }
        }
    }

    class UsunLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pzView.remowRow();
            licz();
            
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
