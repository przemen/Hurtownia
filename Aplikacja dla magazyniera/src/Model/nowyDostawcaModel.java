/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enity.Connector;
import Enity.Firma;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Przemek
 */
public class nowyDostawcaModel {

    Connector con;

    public nowyDostawcaModel(Connector con) {
        this.con = con;
    }

    public void insertFirma(Firma firma) {
        String nazwa = firma.getNazwa();
        String nip = firma.getNip();
        String nrBudynku = firma.getNrBudynku();
        String nrLokalu = firma.getNrLokalu();
        String ulica = firma.getUlica();
        String kodPocztowy = firma.getKodPocztowy();
        String miejscowosc = firma.getMiejscowosc();
        String kod = firma.getKod();
        String notatka = firma.getNotatka();

        String query;
        query = "Insert into Dostawca (Nazwa,NIP,NrBudynku,NrLokalu,Ulica,KodPocztowy,Miejscowosc, Notatka, Kod) values ('"
                + nazwa + "','" + nip + "','" + nrBudynku + "','" + nrLokalu + "','" + ulica + "','" + kodPocztowy + "','" + miejscowosc + "','" + notatka
                + "','" + kod + "')";

        con.query(query);
    }

   public int liczbaKod(String kod) throws SQLException{
         
          
            String query = "SELECT * FROM Dostawca WHERE kod = '"+kod+"'";
            ResultSet rs= con.querySelect(query);
            System.out.println(query);
            int i=0;
            while (rs.next())
            {i++;}
        return i;
   }
   
     public int liczbaNIP(String nip) throws SQLException{
         
          
            String query = "SELECT * FROM Dostawca WHERE NIP = '"+nip+"'";
            ResultSet rs= con.querySelect(query);
            System.out.println(query);
            int i=0;
            while (rs.next())
            {i++;}
        return i;
   }
}
