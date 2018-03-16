/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.PZ;
import Enity.pozycjaPZ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Przemek
 */
public class podgladPZModel {

    Connector con;
    String idPZ;

    public podgladPZModel(Connector con) {
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
    
    public void zatwierdz () throws SQLException{
     String query;query = "UPDATE Dostawa SET  stan = 1 WHERE id ="+idPZ;
                con.query(query);
        query = "SELECT Towar, ilosc FROM PozycjaDostawy WHERE IdDostawa = " + idPZ;
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
