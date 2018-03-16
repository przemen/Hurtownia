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
public class TowarDB {

    Connector con;

    public TowarDB(Connector con) {
        this.con = con;
    }

    public void insertTowar(Towar towar) {

        String query = "INSERT INTO Towar(Nazwa,Opis,JednostkaMiary,KodTowaru,KodKreskowy,PKWiU,idKategoria) values("
                + "'" + towar.getNazwa() + "', "
                + "'" + towar.getOpis() + "', "
                + "'" + towar.getJednostkaMiary() + "', "
                + "'" + towar.getKodTowaru() + "', "
                + "'" + towar.getKodKreskowy() + "', "
                + "'" + towar.getPKWiU() + "', "
                + "'" + towar.getKategoria() + ")";
        con.query(query);
    }
    
        public void insertTowar(String nazwa, String opis, String JM, String kodTowaru, String kodKreskowy,
                String PKWiU,String Kategoria,String VAT) {

        String query = "INSERT INTO Towar(Nazwa,Opis,JednostkaMiary,KodTowaru,KodKreskowy,PKWiU,idKategoria, VAT) values("
                + "'" + nazwa + "', "
                + "'" + opis + "', "
                + "'" + JM + "', "
                + "'" + kodTowaru + "', "
                + "'" + kodKreskowy+ "', "
                + "'" + PKWiU+ "', "
                + "'" + Kategoria+ "',"
                + "'" + VAT+ "')";
        
        con.query(query);
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
    

  
    public void deleteTowar(String id) {
        String query = "Delete From Towar where  Id= " + id;
        con.query(query);
    }

    public void deleteTowar(Towar towar) {
        String query = "Delete From Firma where  Id= " + towar.getId();
        con.query(query);
    }

    public ArrayList<Towar> selectTowarInnerJoin() throws SQLException {
        ArrayList listaTowar = new ArrayList();
        Towar towar;
        String query = "SELECT * FROM Towar " +
"                INNER JOIN Kategoria ON IdKategoria=Kategoria.Id  "+
"                INNER JOIN JednostkaMiary ON JednostkaMiary=JednostkaMiary.Id " +
"				left join Cennik on Cennik.IdTowar= Towar.id " +
"				WHERE ((Cennik.DataKoncowa IS NULL OR  GETDATE() "
                + "<= Cennik.DataKoncowa) AND GETDATE() >= Cennik.DataPoczatkowa) OR  Towar.Id "
                + "NOT IN (Select IdTowar From cennik )";
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
            towar.setCena(rs.getString(19));
            listaTowar.add(towar);
        }
        return listaTowar;
    }
    
     public ArrayList<Towar> selectTowarInnerJoin(String string) throws SQLException {
        ArrayList listaTowar = new ArrayList();
        Towar towar;
        // String selekcja = " Towar.Nazwa LIKE '%"+string+"%'; ";
       String query = "SELECT * FROM Towar " +
"                INNER JOIN Kategoria ON IdKategoria=Kategoria.Id  "+
"                INNER JOIN JednostkaMiary ON JednostkaMiary=JednostkaMiary.Id " +
"				left join Cennik on Cennik.IdTowar= Towar.id " +
"				WHERE (((Cennik.DataKoncowa IS NULL OR  GETDATE() "
                + "<= Cennik.DataKoncowa) AND GETDATE() >= Cennik.DataPoczatkowa) OR  Towar.Id NOT IN (Select IdTowar From cennik ))AND  "+
                 "  (Towar.Nazwa LIKE '%"+string+"%' OR Towar.KodTowaru LIKE '%"+string+"%' OR Towar.KodKreskowy LIKE '%"+string+"%')";
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
            towar.setCena(rs.getString(19));
            listaTowar.add(towar);
        }
        return listaTowar;
    }
    
    

  
    
}
