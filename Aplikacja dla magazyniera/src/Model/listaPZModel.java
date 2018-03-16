/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.PZ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Przemek
 */
public class listaPZModel {

    Connector con;
    String string;
    boolean editable;
    DefaultTableModel DTM;
    String selectString = "";
    String dataOD = "1900-01-01";
    String dataDO = "2100-12-31";

    public listaPZModel(Connector con) {
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

    public ArrayList<PZ> SelectTowar() throws SQLException {

        ArrayList listaPZ = new ArrayList();
        PZ pz;
        String query;
        query = "SELECT Dostawa.Id, Dostawa.Numer, Dostawa.Data, Dostawa.Kwota, Dostawa.stan, "
                + " Dostawca.id, Dostawca.Nazwa "
                + " FROM Dostawa INNER JOIN Dostawca ON Dostawa.IdDostawca = Dostawca.Id"
                + " WHERE  Data  BETWEEN '" + dataOD + "' AND '" + dataDO + "' AND (numer "
                + " LIKE '%" + selectString + "%' OR Dostawca.Nazwa LIKE '%"+selectString+"%'"
                + " OR Dostawca.kod LIKE '%"+selectString+"%' OR Dostawca.NIP LIKE '%"+selectString+"%'  )";

        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            pz = new PZ();
            pz.setId(rs.getString(1));
            pz.setNumer(rs.getString(2));
            pz.setDataWystawienia(rs.getDate(3));
            pz.setKwota(rs.getDouble(4));
            pz.setStan(rs.getInt(5));
            pz.setDostawcaNazwa(rs.getString(7));

            listaPZ.add(pz);

        }
        return listaPZ;
    }

    public void zatwierdz() throws SQLException {

        String query;query = "UPDATE Dostawa SET  stan = 1 WHERE id ="+string;
                con.query(query);
        query = "SELECT Towar, ilosc FROM PozycjaDostawy WHERE IdDostawa = " + string;
        ResultSet rs = con.querySelect(query);
        
        
        
        while (rs.next()) {
            query = "UPDATE Towar SET dostepne = dostepne +" + 
                    rs.getString(2).replaceAll(",", ".") + ", "+ 
                    "ilosc = ilosc + "+ rs.getString(2).replaceAll(",", ".") +
                    " where id = " + rs.getString(1);
                con.query(query);
             
            
        }

    }
}
