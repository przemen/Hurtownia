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
public class KategoriaDB {
    
    Connector con;
 
        public KategoriaDB (Connector con){
        this.con=con;
    }
    
    public void insertKategoria (Kategoria kategoria){
        String nazwa = kategoria.getNazwa();
                String query = "Insert into Kategoria (Nazwa) values ('"
                +nazwa+"')";
        con.query(query);
    }
    
    public void insertKategoria (String nazwa){
        String query = "Insert into Kategoria (Nazwa) values ('"
                +nazwa+"')";
        con.query(query);
    }
    
    public void deleteKategoria (String id){
                String query ="DELETE FROM Kategoria where  Id= "+id;
        con.query(query);
    }
    
    public void deleteKategoria (Kategoria kategoria){
        String query ="DELETE FROM Kategoria where  Id= "+kategoria.getId().toString();
        con.query(query);
    }
    
    public void updateKategoria (Kategoria kategoria) {
            String query = "UPDATE Kategoria"
                + " SET "
                + " Nazwa = "+kategoria.getNazwa()
                + " WHERE Id = "+kategoria.getId() ;
        con.query(query);
    }
    
    public void updateKategoria (String id, String nazwa) {
            String query ;
                query = "UPDATE Kategoria"
                + " SET "
                + " Nazwa = "+nazwa
                + " WHERE Id = "+id;
        con.query(query);
    }
    
    public ArrayList<Kategoria> selectKategoria() throws SQLException{
            ArrayList listaKategoria = new ArrayList();
            Kategoria kategoria;
            String query;
            query = "SELECT * FROM Kategoria ORDER BY Nazwa ASC";
            ResultSet rs= con.querySelect(query);
            
            while(rs.next()){
                kategoria =new Kategoria();
                kategoria.setId(rs.getInt(1));
                kategoria.setNazwa(rs.getString(2));
                listaKategoria.add(kategoria);
            }
            return listaKategoria;
    }
    
        public ArrayList<Kategoria> selectKategoriaOrdDESC() throws SQLException{
            ArrayList listaKategoria = new ArrayList();
            Kategoria kategoria;
            String query;
            query = "SELECT * FROM Kategoria ORDER BY Id DESC";
            ResultSet rs= con.querySelect(query);
            
            while(rs.next()){
                kategoria =new Kategoria();
                kategoria.setId(rs.getInt(1));
                kategoria.setNazwa(rs.getString(2));
                listaKategoria.add(kategoria);
            }
            return listaKategoria;
    }
    
    public Kategoria selectKategoriaID (String id) throws SQLException{
            ArrayList listaKategoria = new ArrayList();
            Kategoria kategoria;
            String query = "SELECT * FROM Kategoria";
            ResultSet rs= con.querySelect(query);
            
            while(rs.next()){
                kategoria =new Kategoria();
                kategoria.setId(rs.getInt(1));
                kategoria.setNazwa(rs.getString(2));
                listaKategoria.add(kategoria);
            }
            return (Kategoria) listaKategoria.get(0);
    }
    
    
}
