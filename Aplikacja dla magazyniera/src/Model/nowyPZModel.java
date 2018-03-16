/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.Towar;
import Enity.Firma;
import Enity.PZ;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Przemek
 */
public class nowyPZModel {

    Connector con;
    String idFirma = "";

    public nowyPZModel(Connector con) {
        this.con = con;
    }

    public Connector getCon() {
        return con;
    }

    public void setCon(Connector con) {
        this.con = con;
    }

    public String getIdFirma() {
        return idFirma;
    }

    public void setIdFirma(String idFirma) {
        this.idFirma = idFirma;
    }

    public Towar getTowar(String id) throws SQLException {

        Towar towar = new Towar();
        String query;
        query = "SELECT * FROM Towar "
                + "                INNER JOIN Kategoria ON IdKategoria=Kategoria.Id  "
                + "                INNER JOIN JednostkaMiary ON JednostkaMiary=JednostkaMiary.Id "
                + " WHERE Towar.id = " + id;

        ResultSet rs = con.querySelect(query);

        while (rs.next()) {

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

        }
        return towar;

    }

    public Towar getTowarS(String searchSting) throws SQLException {

        Towar towar = new Towar();
        String query;
        query = "SELECT * FROM Towar "
                + "                INNER JOIN Kategoria ON IdKategoria=Kategoria.Id  "
                + "                INNER JOIN JednostkaMiary ON JednostkaMiary=JednostkaMiary.Id "
                + " WHERE KodTowaru LIKE '%" + searchSting + "%' ;";

        ResultSet rs = con.querySelect(query);

        while (rs.next()) {

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

        }
        return towar;

    }

    public Firma selectFirmaSearch(String searchString) throws SQLException {
        Firma firma = new Firma();

        String query = "SELECT * FROM Dostawca WHERE Kod = '" + searchString + "' OR "
                + "NIP = '" + searchString + "'";

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
 public Firma selectFirmaID(String searchString) throws SQLException {
        Firma firma = new Firma();

        String query = "SELECT * FROM Dostawca WHERE Id = "+searchString;

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
    public String lastNumer() throws SQLException {
        String query = "SELECT * FROM Dostawa ORDER BY Id DESC";
        ResultSet rs = con.querySelect(query);
        String nr = "";
        while (rs.next()) {
            nr = rs.getString(2);
            break;
        }

        return nr;
    }

    public void insertPZconf(DefaultTableModel DTM, PZ pz, JXTable jxt) {

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String dateW = DATE_FORMAT.format(pz.getDataWystawienia());

        int stan;

        String query = "declare @id int ";
        query += "INSERT INTO Dostawa (Numer, IdDostawca, Data, Kwota, Stan) Values"
                + "('" + pz.getNumer() + "', "
                + "" + idFirma + ","
                + "'" + dateW + "',"
                + "" + pz.getKwota() + ","
                + "" + "1" + ""
                + ") "
                + " SELECT TOP 1 @id = id FROM Dostawa ORDER BY id DESC"
                + " INSERT INTO PozycjaDostawy (Towar, ilosc, Cena, Wartosc,IloscPozostala, IdDostawa  ) VALUES ";

        for (int i = 0; i < DTM.getRowCount(); i++) {
            query += "(" + jxt.getValueAt(i, 5) + ", "
                    + "" + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 3).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 4).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                    + " @id)";

            if (i < DTM.getRowCount() - 1) {
                query += ",";
            }

        }
        con.query(query);

        for (int i = 0; i < DTM.getRowCount(); i++) {

            query = "UPDATE Towar SET dostepne = dostepne +"
                    + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ", "
                    + "ilosc = ilosc + " + jxt.getValueAt(i, 1).toString().replaceAll(",", ".")
                    + " where id = " + jxt.getValueAt(i, 5).toString();;

            con.query(query);

        }
    }

    public void insertPZnonConf(DefaultTableModel DTM, PZ pz, JXTable jxt) {

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String dateW = DATE_FORMAT.format(pz.getDataWystawienia());

        int stan;

        String query = "declare @id int ";
        query += "INSERT INTO Dostawa (Numer, IdDostawca, Data, Kwota, Stan) Values"
                + "('" + pz.getNumer() + "', "
                + "" + idFirma + ","
                + "'" + dateW + "',"
                + "" + pz.getKwota() + ","
                + "" + "0" + ""
                + ") "
                + " SELECT TOP 1 @id = id FROM Dostawa ORDER BY id DESC"
                + " INSERT INTO PozycjaDostawy (Towar, ilosc, Cena, Wartosc,IloscPozostala, IdDostawa) VALUES ";

        for (int i = 0; i < DTM.getRowCount(); i++) {
            query += "(" + jxt.getValueAt(i, 5) + ", "
                    + "" + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 3).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 4).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                    + " @id)";

            if (i < DTM.getRowCount() - 1) {
                query += ",";
            }

        }

        con.query(query);

    }
}
