/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import Model.edytujWZModel;
//import Model.listaWZModel;
import Model.listaWZModel;
import Model.nowyWZModel;
import Model.podgladWZModel;
//import Model.podgladWZModel;
//import View.edytujWZView;
import View.listaWZView;
import View.nowyWZView;
import View.podgladWZView;
//import View.nowyWZView;
//import View.podgladWZView;
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
public final class listaWZController implements ListaController {

    private listaWZModel wzModel;
    private listaWZView wzView;
    private ListaController controllr;

    public listaWZController(listaWZModel wzModel, listaWZView wzView) throws SQLException {
        this.wzModel = wzModel;
        this.wzView = wzView;
        this.controllr = this;
        this.wzModel.setDTM(wzView.gtDTM());
        this.wzView.addData1Lis(new DataOdLis());
        this.wzView.addData2Lis(new DataDoLis());
        this.wzView.addTableLis(new TableMousLis());
        this.wzView.jButton3addActionListener(new zatwierdz());
        this.wzView.addSerchLis(new SzukajLis());
 
        this.wzView.jButton2addActionListener(new odswiez());
        refresh();
    }

    public listaWZController(listaWZModel listaWZModel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refresh() throws SQLException {
        this.wzView.wypisz(wzModel.getDTM(), wzModel.SelectTowar());
    }

    class newLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new nowyWZController(new nowyWZModel(wzModel.getCon()), new nowyWZView(), controllr);
            } catch (SQLException ex) {
                Logger.getLogger(listaWZController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class odswiez implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(listaWZController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class zatwierdz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!wzModel.isEditable()) {

                try {
                    wzModel.wydano();
                    refresh();
                } catch (SQLException ex) {
                    Logger.getLogger(listaWZController.class.getName()).log(Level.SEVERE, null, ex);
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
                date = DATE_FORMAT.format(wzView.dataOd());
                wzModel.setDataOD(date);
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
                date = DATE_FORMAT.format(wzView.dataDo());
                wzModel.setDataDO(date);
                refresh();  
                
            } catch (NullPointerException ex) {
                wzModel.setDataDO("2100-12-31");
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
            wzModel.setString((String) wzView.getjXTable1().getValueAt(wzView.getjXTable1().getSelectedRow(), 5));
            wzModel.setEditable((boolean) wzView.getjXTable1().getValueAt(wzView.getjXTable1().getSelectedRow(), 4));
            boolean edit = false;
            try {
                String s = wzView.getjXTable1().getValueAt(wzView.getjXTable1().getSelectedRow(), 1).toString();
                edit = true;
            } catch (NullPointerException ex) {
                edit = false;
            }

             
            wzView.ustawWydano(!(boolean) wzView.getjXTable1().getValueAt(wzView.getjXTable1().getSelectedRow(), 4));
              if (e.getClickCount() == 2) {

             try {
             new podgladWZController(new podgladWZModel(wzModel.getCon()), new podgladWZView(), controllr, wzModel.getString());
             } catch (SQLException ex) {
             Logger.getLogger(listaWZController.class.getName()).log(Level.SEVERE, null, ex);
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
            wzModel.setSelectString(wzView.getSzukaj());
            try {
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(listaWZController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
