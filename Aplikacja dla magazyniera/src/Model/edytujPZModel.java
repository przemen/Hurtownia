/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.Firma;
import Enity.PZ;
import Enity.Towar;
import Enity.pozycjaPZ;
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
public class edytujPZModel {

    private Connector con;
    public String idPZ;

    public edytujPZModel(Connector con) {
        this.con = con;
    }

    public Connector getCon() {
        return con;
    }

    public void setCon(Connector con) {
        this.con = con;
    }

    public String getIdPZ() {
        return idPZ;
    }

    public void setIdPZ(String idPZ) {
        this.idPZ = idPZ;
    }

    public PZ getPZ() throws SQLException {

        PZ pz = null;
        String query;
        query = "SELECT Dostawa.Id, Dostawa.Numer, Dostawa.Data, Dostawa.Kwota, Dostawa.stan, "
                + " Dostawca.id, Dostawca.Nazwa "
                + " FROM Dostawa INNER JOIN Dostawca ON Dostawa.IdDostawca = Dostawca.Id"
                + " WHERE   Dostawa.Id = " + idPZ;

        ResultSet rs = con.querySelect(query);
        while (rs.next()) {
            pz = new PZ();
            pz.setId(rs.getString(1));
            pz.setNumer(rs.getString(2));
            pz.setDataWystawienia(rs.getDate(3));
            pz.setKwota(rs.getDouble(4));
            pz.setStan(rs.getInt(5));
            pz.setDostawcaNazwa(rs.getString(7));
        }

        return pz;
    }

    public ArrayList<pozycjaPZ> getPozycjaPZ() throws SQLException {

        ArrayList listaPozycjaPZ = new ArrayList();
        pozycjaPZ pZ;
        String query;
        query = "SELECT PozycjaDostawy.Id, PozycjaDostawy.Towar, PozycjaDostawy.ilosc, "
                + "PozycjaDostawy.Cena, PozycjaDostawy.Wartosc, Towar.Nazwa, JednostkaMiary.skrot FROM PozycjaDostawy inner join Towar on  "
                + "PozycjaDostawy.Towar = Towar.Id "
                + "inner join JednostkaMiary on Towar.JednostkaMiary = JednostkaMiary.Id where IdDostawa = " + idPZ;

        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            pZ = new pozycjaPZ();
            pZ.setId(rs.getString(1));
            pZ.setIdTowar(rs.getString(2));
            pZ.setIlosc(rs.getString(3));
            pZ.setCenaZ(rs.getString(4));
            pZ.setWartoscc(rs.getString(5));
            pZ.setNazwa(rs.getString(6));
            pZ.setJm(rs.getString(7));
            listaPozycjaPZ.add(pZ);
        }
        return listaPozycjaPZ;
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

    public Firma selectFirmaID(String searchString) throws SQLException {
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

    public void insertPZconf(DefaultTableModel DTM, PZ pz, JXTable jxt) {

        String query;
        query = "UPDATE Dostawa SET  stan = 1 WHERE id =" + idPZ;
        con.query(query);
        System.out.println(query);
        query = "DELETE FROM PozycjaDostawy where IdDostawa = " + idPZ;
        con.query(query);
        for (int i = 0; i < DTM.getRowCount(); i++) {
            query = "INSERT INTO PozycjaDostawy (Towar, ilosc, Cena, Wartosc, IdDostawa) VALUES ";
            query += "(" + jxt.getValueAt(i, 5).toString().replaceAll(",", ".") + ", "
                    + "" + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 3).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 4).toString().replaceAll(",", ".") + ","
                    + "" + idPZ + ")";
            con.query(query);
            System.out.println(query);
        }

        for (int i = 0; i < DTM.getRowCount(); i++) {

            query = "UPDATE Towar SET dostepne = dostepne +"
                    + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ", "
                    + "ilosc = ilosc + " + jxt.getValueAt(i, 1).toString().replaceAll(",", ".")
                    + " where id = " + jxt.getValueAt(i, 5).toString();

            con.query(query);
            System.out.println(query);

        }
    }

    public void insertPZnonConf(DefaultTableModel DTM, PZ pz, JXTable jxt) {

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String dateW = DATE_FORMAT.format(pz.getDataWystawienia());

        String query = "DELETE FROM PozycjaDostawy where IdDostawa = " + idPZ;
        con.query(query);
        System.out.println(query);
        for (int i = 0; i < DTM.getRowCount(); i++) {
            query = "INSERT INTO PozycjaDostawy (Towar, ilosc, Cena, Wartosc, IdDostawa) VALUES ";
            query += "(" + jxt.getValueAt(i, 5) + ", "
                    + "" + jxt.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 3).toString().replaceAll(",", ".") + ","
                    + "" + jxt.getValueAt(i, 4).toString().replaceAll(",", ".") + ","
                    + "" + idPZ + ")";
            con.query(query);
            System.out.println(query);
        }

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
}
