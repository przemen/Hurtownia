/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Przemek
 */
public class JednostkaMiaryDB {

    Connector con;

 

    public JednostkaMiaryDB(Connector con) {
        this.con = con;
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

    public void insertJednostkaMiary(String nazwa, String skrot) {
        String query = "Insert into JednostkaMiary "
                + "(Nazwa, skrot) "
                + "values ('"
                + nazwa
                + "','"
                + skrot
                + "')";
        con.query(query);
    }

    public void deleteJednostkaMiary(String id) {
        String query;
        query = "DELETE FROM JednostkaMiary where  Id= " + id;
        con.query(query);
    }

    public void deleteJednostkaMiary(JednostkaMiary jm) {
        String query;
        query = "DELETE FROM JednostkaMiary where  Id= " + jm.getId().toString();
        con.query(query);
    }

    public void updateJednostkaMiary(JednostkaMiary jm) {
        String query = "UPDATE JednostkaMiary"
                + " SET "
                + " Nazwa = " + jm.getNazwa()
                + " skrot = " + jm.getSkrot()
                + " WHERE Id = " + jm.getId();
        con.query(query);
    }

    public void updateJednostkaMiary(String id, String nazwa, String skrot) {
        String query;
        query = "UPDATE JednostkaMiary"
                + " SET "
                + " Nazwa = " + nazwa
                + " skrot = " + skrot
                + " WHERE Id = " + id;
        con.query(query);
    }

    public ArrayList<Kategoria> selectJednostkaMiary() throws SQLException {
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
    
     public ArrayList<Kategoria> selectJednostkaMiaryOrdDESC() throws SQLException {
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
    

    public Kategoria selectJednostkaMiaryID(String id) throws SQLException {
        ArrayList listaKategoria = new ArrayList();
        Kategoria kategoria;
        String query = "SELECT * FROM Kategoria";
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            kategoria = new Kategoria();
            kategoria.setId(rs.getInt(1));
            kategoria.setNazwa(rs.getString(2));
            listaKategoria.add(kategoria);
        }
        return (Kategoria) listaKategoria.get(0);
    }

}
