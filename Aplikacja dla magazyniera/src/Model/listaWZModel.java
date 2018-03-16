/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.WZ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemek
 */
public class listaWZModel {

    Connector con;
    String string;
    boolean editable;
    DefaultTableModel DTM;
    String selectString = "";
    String dataOD = "1900-01-01";
    String dataDO = "2100-12-31";

    public listaWZModel(Connector con) {
        this.con = con;
    }

    public Connector getCon() {
        return con;
    }

    public void setCon(Connector con) {
        this.con = con;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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

    public String getDataOD() {
        return dataOD;
    }

    public void setDataOD(String dataOD) {
        this.dataOD = dataOD;
    }

    public String getDataDO() {
        return dataDO;
    }

    public void setDataDO(String dataDO) {
        this.dataDO = dataDO;
    }

    public ArrayList<WZ> SelectTowar() throws SQLException {

        ArrayList listaWZ = new ArrayList();
        WZ wz;
        String query;
        query = "SELECT Faktura.Id, Faktura.Numer, Faktura.DataWystawienia, "
                + " SUM(CenaWydanie *ilosc) AS 'sumawydanie' ,   Faktura.editable, "
                + "               Faktura.id, Firma.Nazwa, Faktura.wydano  "
                + "               FROM Faktura  INNER JOIN Firma ON Faktura.IdKlient= Firma.Id "
                + "			   INNER JOIN PozycjaFaktury ON PozycjaFaktury.IdFaktura = Faktura.id "
                + " WHERE  Faktura.DataWystawienia  BETWEEN '" + dataOD + "' AND '" + dataDO + "' AND (Faktura.Numer  "
                + " LIKE '%" + selectString + "%' OR Firma.Nazwa LIKE '%" + selectString + "%' OR"
                + " Firma.NIP LIKE  '%" + selectString + "%' OR "
                + "Firma.KodKlienta LIKE  '%" + selectString + "%'        "  + ") AND Faktura.editable != 1 "
                + "	GROUP BY  Faktura.id, Faktura.Numer, Faktura.DataWystawienia, Faktura.editable,"
                + "               Faktura.id, Firma.Nazwa, Faktura.wydano ;";
   
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            wz = new WZ();
            wz.setId(rs.getString(1));
            wz.setNumer(rs.getString(2));
            wz.setDataWystawienia(rs.getDate(3));
            wz.setKwota(rs.getDouble(4));
            wz.setStan(rs.getInt(8));
            wz.setDostawcaNazwa(rs.getString(7));
          
            listaWZ.add(wz);

        }
        return listaWZ;
    }

    public void wydano() throws SQLException {

        String query;
        query = "UPDATE Faktura SET  wydano = 1 WHERE id =" + string;
        con.query(query);
        query = "SELECT Towar, ilosc FROM PozycjaFaktury WHERE PozycjaFaktury = " + string;
        ResultSet rs = con.querySelect(query);
       

        while (rs.next()) {
            query = "UPDATE Towar SET "
                    + "ilosc = ilosc - " + rs.getString(2).replaceAll(",", ".")
                    + " where id = " + rs.getString(1);
            con.query(query);
         

        }

    }
}
