/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;


/**
 *
 * @author Przemek
 */
public class FakturaDB {
    Connector con;
    public FakturaDB(Connector con){this.con = con;
   // JOptionPane.showMessageDialog (null,con.toString(), "Działa", JOptionPane.INFORMATION_MESSAGE);
    
    }
    
    public void insertFaktura(Faktura faktura){
        String numer = faktura.getNumer();
        Date DataWystawienia=faktura.getDataWystawienia();
        Date TerminZaplaty = faktura.getTerminZaplaty();
        String uwagi = faktura.getUwagi();
        String notatki = faktura.getNotatki();
        Integer idKlient = faktura.getIdKlient();
        
        String query = "Insert into Faktura(numer,DataWystawienia,TerminZaplaty,FormaZaplaty,Uwagi,Notatki,IdKlient) values ('"
                +numer+"','"+DataWystawienia+"','"+TerminZaplaty+"','"+uwagi+"','"+notatki+"',"+idKlient+")";
        con.query(query);
    }
    public void insertFaktura(String numer, Date DataWystawienia, Date TerminZaplaty,String Uwagi, String Notatki, Integer idKlient){
        String query = "Insert into Faktura(numer,DataWystawienia,TerminZaplaty,FormaZaplaty,Uwagi,Notatki,IdKlient) values ('"
                +numer+"','"+DataWystawienia+"','"+TerminZaplaty+"','"+Uwagi+"','"+Notatki+"',"+idKlient+")";
        con.query(query);
    }
    
    public ArrayList<Faktura> selectFaktura() throws SQLException{
            ArrayList listaFaktur = new ArrayList();
            Faktura faktura;
            String query = "SELECT * FROM Faktura";
            ResultSet rs= con.querySelect(query);
            
            while(rs.next()){
                faktura=new Faktura();
                faktura.setId(rs.getInt(1));
                faktura.setNumer(rs.getString(1));
                faktura.setDataWystawienia(rs.getDate(3));
                faktura.setTerminZaplaty(rs.getDate(5));
                faktura.setFormaZaplaty(rs.getString(6));
                faktura.setUwagi(rs.getString(7));
                faktura.setNotatki(rs.getString(8));
                faktura.setIdKliet(rs.getInt(9));
                
                listaFaktur.add(faktura);
                
            }
            
            
            
            return listaFaktur;
    }
    
    
}
