/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.JednostkaMiary;
import Enity.Kategoria;
import Enity.Towar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Przemek
 */
public class nowyTowarModel {

    Connector con;

    public nowyTowarModel(Connector con) {
        this.con = con;

    }

    public int liczbaKod(String kod) throws SQLException {

        String query = "SELECT * FROM Towar WHERE KodTowaru = '" + kod + "'";
        ResultSet rs = con.querySelect(query);
        System.out.println(query);
        int i = 0;
        while (rs.next()) {
            i++;
        }
        return i;
    }

    public ArrayList<Kategoria> selectKategoriaOrdDESC() throws SQLException {
        ArrayList listaKategoria = new ArrayList();
        Kategoria kategoria;
        String query;
        query = "SELECT * FROM Kategoria ORDER BY Id DESC";
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            kategoria = new Kategoria();
            kategoria.setId(rs.getInt(1));
            kategoria.setNazwa(rs.getString(2));
            listaKategoria.add(kategoria);
        }
        return listaKategoria;
    }

    public ArrayList<Kategoria> selectKategoria() throws SQLException {
        ArrayList listaKategoria = new ArrayList();
        Kategoria kategoria;
        String query;
        query = "SELECT * FROM Kategoria ORDER BY Nazwa ASC";
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            kategoria = new Kategoria();
            kategoria.setId(rs.getInt(1));
            kategoria.setNazwa(rs.getString(2));
            listaKategoria.add(kategoria);
        }
        return listaKategoria;
    }

    public void insertKategoria(Kategoria kategoria) {
        String nazwa = kategoria.getNazwa();
        String query = "Insert into Kategoria (Nazwa) values ('"
                + nazwa + "')";
        con.query(query);
    }

    public void insertJednostkaMiary(JednostkaMiary jm) {
        String query = "Insert into JednostkaMiary "
                + "(Nazwa, skrot) "
                + "values ('"
                + jm.getNazwa()
                + "','"
                + jm.getSkrot()
                + "')";
        con.query(query);
    }

    public ArrayList<JednostkaMiary> selectJednostkaMiary() throws SQLException {
        ArrayList lista = new ArrayList();
        JednostkaMiary jednostkaMiary;
        String query;
        query = "SELECT * FROM JednostkaMiary ORDER BY Nazwa ASC";
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            jednostkaMiary = new JednostkaMiary();
            jednostkaMiary.setId(rs.getInt(1));
            jednostkaMiary.setNazwa(rs.getString(2));
            jednostkaMiary.setSkrot(rs.getString(3));
            lista.add(jednostkaMiary);
        }
        return lista;
    }

    public ArrayList<JednostkaMiary> selectJednostkaMiaryOrdDESC() throws SQLException {
        ArrayList lista = new ArrayList();
        JednostkaMiary jednostkaMiary;
        String query;
        query = "SELECT * FROM JednostkaMiary ORDER BY Id DESC";
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            jednostkaMiary = new JednostkaMiary();
            jednostkaMiary.setId(rs.getInt(1));
            jednostkaMiary.setNazwa(rs.getString(2));
            jednostkaMiary.setSkrot(rs.getString(3));
            lista.add(jednostkaMiary);
        }
        return lista;
    }

    public void insertTowar(Towar towar) {
        String query = "INSERT INTO Towar(Nazwa,Opis,JednostkaMiary,KodTowaru,KodKreskowy,PKWiU,idKategoria, VAT) values("
                + "'" + towar.getNazwa() + "', "
                + "'" + towar.getOpis() + "', "
                + "'" + towar.getJednostkaMiary() + "', "
                + "'" + towar.getKodTowaru() + "', "
                + "'" + towar.getKodKreskowy() + "', "
                + "'" + towar.getPKWiU() + "', "
                + "'" + towar.getKategoria() + "',"
                + " " + towar.getVAT() + ")";
        System.out.println(query);
        con.query(query);
    }

}
