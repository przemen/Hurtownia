/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.Firma;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemek
 */
public class listaDostawcaModel {
    Connector con;
    String string;
    DefaultTableModel DTM;
    String selectString ="";

    public listaDostawcaModel(Connector con) {
        this.con = con;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public DefaultTableModel getDTM() {
        return DTM;
    }

    public void setDTM(DefaultTableModel DTM) {
        this.DTM = DTM;
    }

    public String getSelectString() {
        return selectString;
    }

    public void setSelectString(String selectString) {
        this.selectString = selectString;
    }

    public Connector getCon() {
        return con;
    }
    
    public void setRowCount0 (){
        DTM.setRowCount(0);
    };
    
    
     public Firma selectFirmaID() throws SQLException {
        Firma firma = new Firma();

        String query = "SELECT * FROM Dostawca WHERE Nazwa LIKE '%"+string+"%'";

        ResultSet rs = con.querySelect(query);

        while (rs.next()) {

            firma.setId(rs.getInt(1));
            firma.setNazwa(rs.getString(2));
            firma.setNip(rs.getString(3));
            firma.setNrBudynku(rs.getString(4));
            firma.setNrLokalu(rs.getString(5));
            firma.setUlica(rs.getString(6));
            firma.setKodPocztowy(rs.getString(7));
            firma.setMiejscowosc(rs.getString(8));
            firma.setNotatka(rs.getString(9));
        }

        return firma;
    }
     
     public ArrayList<Firma> selectFirma() throws SQLException{
            ArrayList listaFirm = new ArrayList();
            Firma firma;
            String selekcja = "Nazwa LIKE '%"+selectString+"%' OR kod like '%"+selectString+"%' "
                    + "OR NIP LIKE '%"+selectString+"%'";
            String query = "SELECT * FROM Dostawca WHERE "+selekcja;
            ResultSet rs= con.querySelect(query);
            
            while(rs.next()){
                firma=new Firma();
                firma.setId(rs.getInt(1));
                firma.setNazwa(rs.getString(2));
                firma.setNip(rs.getString(3));
                firma.setNrBudynku(rs.getString(4));
                firma.setNrLokalu(rs.getString(5));
                firma.setUlica(rs.getString(6));
                firma.setKodPocztowy(rs.getString(7));
                firma.setMiejscowosc(rs.getString(8));
                firma.setNotatka(rs.getString(9));
                listaFirm.add(firma);
            }
            
            
            
            return listaFirm;
    }
}
