/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.Towar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Przemek
 */
public class listaTowarModel {

    Connector con;
    String selectedID;
    String searchSting;
    
    public listaTowarModel(Connector con) {
        this.con = con;
        searchSting = "";
    }

    public String getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(String selectedID) {
        this.selectedID = selectedID;
    }

    public String getSearchSting() {
        return searchSting;
    }

    public void setSearchSting(String searchSting) {
        this.searchSting = searchSting;
    }

    public Connector getCon() {
        return con;
    }

    public void setCon(Connector con) {
        this.con = con;
    }

    public ArrayList SelectTowar () throws SQLException {

        ArrayList listaTowar = new ArrayList();
        Towar towar;
        String query;
        query = "SELECT * FROM Towar "
                + "                INNER JOIN Kategoria ON IdKategoria=Kategoria.Id  "
                + "                INNER JOIN JednostkaMiary ON JednostkaMiary=JednostkaMiary.Id "
                + " WHERE Towar.Nazwa LIKE '%" + searchSting + "%' OR KodTowaru LIKE '%" + searchSting + "%' ;";
     		
        ResultSet rs = con.querySelect(query);
        
        while (rs.next()) {
            towar = new Towar();
            towar.setId(rs.getInt(1));
            towar.setNazwa(rs.getString(2));
            towar.setOpis(rs.getString(3));
            towar.setKategoria(rs.getString(13));
            towar.setKodTowaru(rs.getString(5));
            towar.setKodKreskowy(rs.getString(6));
            towar.setPKWiU(rs.getString(7));
            towar.setJednostkaMiary(rs.getString(16));
            towar.setVAT(rs.getString(9));
            towar.setIlosc(rs.getString(10));
            towar.setDostepne(rs.getString(11));
            listaTowar.add(towar);
        }
        return listaTowar;
    }
    
    public void addElement(){
    
    
    
    }

}
