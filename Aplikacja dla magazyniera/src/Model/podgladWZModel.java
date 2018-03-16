/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.WZ;
import Enity.pozycjaWZ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Przemek
 */
public class podgladWZModel {

    Connector con;
    String idWZ;

    public podgladWZModel(Connector con) {
        this.con = con;
    }

    public Connector getCon() {
        return con;
    }

    public void setCon(Connector con) {
        this.con = con;
    }

    public String getIdWZ() {
        return idWZ;
    }

    public void setIdWZ(String idWZ) {
        this.idWZ = idWZ;
    }

    public WZ getWZ() throws SQLException {

        WZ wz = null;
        String query;
        query = "SELECT Faktura.Id, Faktura.Numer, Faktura.DataWystawienia, "
                + " SUM(CenaWydanie) AS 'sumawydanie' ,   Faktura.wydano, "
                + "               Faktura.id, Firma.Nazwa "
                + "               FROM Faktura  INNER JOIN Firma ON Faktura.IdKlient= Firma.Id "
                + "			   INNER JOIN PozycjaFaktury ON PozycjaFaktury.IdFaktura = Faktura.id "
                + " WHERE Faktura.Id = " + idWZ
                + "			   GROUP BY  Faktura.id, Faktura.Numer, Faktura.DataWystawienia, Faktura.wydano,"
                + "               Faktura.id, Firma.Nazwa ";
        System.out.println(query);
        ResultSet rs = con.querySelect(query);
        while (rs.next()) {
            wz = new WZ();
            wz.setId(rs.getString(1));
            wz.setNumer(rs.getString(2));
            wz.setDataWystawienia(rs.getDate(3));
            wz.setKwota(rs.getDouble(4));
            wz.setStan(rs.getInt(5));
            wz.setDostawcaNazwa(rs.getString(7));
        }

        return wz;
    }

    public ArrayList<pozycjaWZ> getPozycjaWZ() throws SQLException {

        ArrayList listaPozycjaWZ = new ArrayList();
        pozycjaWZ pZ;
        String query;
        query = "SELECT PozycjaFaktury.Id, PozycjaFaktury.Towar, PozycjaFaktury.ilosc, "
                + "PozycjaFaktury.CenaWydanie, PozycjaFaktury.ilosc * PozycjaFaktury.CenaWydanie, Towar.Nazwa, JednostkaMiary.skrot FROM PozycjaFaktury inner join Towar on  "
                + "PozycjaFaktury.Towar = Towar.Id "
                + "inner join JednostkaMiary on Towar.JednostkaMiary = JednostkaMiary.Id where idFaktura = " + idWZ;

        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            pZ = new pozycjaWZ();
            pZ.setId(rs.getString(1));
            pZ.setIdTowar(rs.getString(2));
            pZ.setIlosc(rs.getString(3));
            pZ.setCenaZ(rs.getString(4));
            pZ.setWartoscc(rs.getString(5));
            pZ.setNazwa(rs.getString(6));
            pZ.setJm(rs.getString(7));
            listaPozycjaWZ.add(pZ);
        }
        return listaPozycjaWZ;
    }

    public void wydano() throws SQLException {

        String query;
        query = "UPDATE Faktura SET  stan = 1 WHERE id =" + idWZ;
        con.query(query);
        query = "SELECT Towar, ilosc FROM PozycjaFaktury WHERE PozycjaFaktury = " + idWZ;
        ResultSet rs = con.querySelect(query);
   

        while (rs.next()) {
            query = "UPDATE Towar SET "
                    + "ilosc = ilosc - " + rs.getString(2).replaceAll(",", ".")
                    + " where id = " + rs.getString(1);
            con.query(query);
            System.out.println(query);

        }

    }
}
