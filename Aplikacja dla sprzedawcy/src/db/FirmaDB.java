
package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Przemek
 */
public class FirmaDB {
    Connector con;
    public FirmaDB(Connector con){this.con=con;}
   
    public FirmaDB(String dbHost, String dbName, String dbUser, String dbPass){
        con = new Connector(dbHost, dbName, dbUser, dbPass);
    }
        public FirmaDB(String [] conection){
        con = new Connector(conection[0], conection[1], conection[2], conection[3]);
    }
    
   public int liczbaKod(String kod) throws SQLException{
         
          
            String query = "SELECT * FROM Firma WHERE KodKlienta = '"+kod+"'";
            ResultSet rs= con.querySelect(query);
            System.out.println(query);
            int i=0;
            while (rs.next())
            {i++;}
        return i;
   }
   
     public int liczbaNIP(String nip) throws SQLException{
         
          
            String query = "SELECT * FROM Firma WHERE NIP = '"+nip+"'";
            ResultSet rs= con.querySelect(query);
            System.out.println(query);
            int i=0;
            while (rs.next())
            {i++;}
        return i;
   }
    
    public void insertFirma(Firma firma){
        String nazwa = firma.getNazwa();
        String nip=firma.getNip();
        String nrBudynku=firma.getNrBudynku();
        String nrLokalu=firma.getNrLokalu();
        String ulica=firma.getUlica();
        String kodPocztowy= firma.getKodPocztowy();
        String miejscowosc = firma.getMiejscowosc();
        String kod = firma.getKod();
        String notatka = firma.getNotatka();
        
        String query = "Insert into Firma (Nazwa,NIP,NrBudynku,NrLokalu,Ulica,KodPocztowy,Miejscowosc, Notatka, KodKlienta) values ('"
                +nazwa+"','"+nip+"','"+nrBudynku+"','"+nrLokalu+"','"+ulica+"','"+kodPocztowy+"','"+miejscowosc+"','"+notatka
                +"','"+kod+"')";
       
        con.query(query);
    }
    
    public void updateFirma(int id){
    
    
    }
    
    public void updateFirma(Firma firma){
        
        String query = "UPDATE Firma"
                + " SET "
                + " Nazwa = "+"'"+firma.getNazwa()+"'"
                + " ,NIP = "+"'"+firma.getNip()+"'"
                + " ,NrBudynku = "+"'"+firma.getNrBudynku()+"'"
                + " ,NrLokalu = "+"'"+firma.getNrLokalu()+"'"
                + " ,Ulica = "+"'"+firma.getUlica()+"'"
                + " ,KodPocztowy = "+"'"+firma.getKodPocztowy()+"'"
                + " ,Miejscowosc = " + "'"+firma.getMiejscowosc()+"'"
                + " ,Notatka =" +"'"+firma.getNotatka()+"'"
                + " WHERE Id = "+firma.getId() ;
        con.query(query);
      
        
    }
    
    public void deleteFirma(String id){
        String query ="Delete From Firma where  Id= "+id;
        con.query(query);
    }
        public void deleteFirma(Firma firma){
        String query ="Delete From Firma where  Id= "+firma.getId();
        con.query(query);
    }

    
    public ArrayList<Firma> selectFirma() throws SQLException{
            ArrayList listaFirm = new ArrayList();
            Firma firma;
            String query = "SELECT * FROM Firma";
            ResultSet rs= con.querySelect(query);
            
            while(rs.next()){
                firma=new Firma();
                firma.setId(rs.getInt(1));
                firma.setNazwa(rs.getString(2));
                firma.setNip(rs.getString(3));
                firma.setNrBudynku(rs.getString(4));
                firma.setNrLokalu(rs.getString(5));
                firma.setUlica(rs.getString(6));
                firma.setKodPocztowy(rs.getString(7));
                firma.setMiejscowosc(rs.getString(8));
                firma.setNotatka(rs.getString(9));
                listaFirm.add(firma);
            }
            return listaFirm;
    }
    
    public Firma selectFirmaID(String id) throws SQLException{
            Firma firma;
            String query = "SELECT * FROM Firma Where Id = "+id;
            ResultSet rs= con.querySelect(query);
            ArrayList listaFirm = new ArrayList();
            while(rs.next()){
            firma=new Firma();
            firma.setId(rs.getInt(1));
            firma.setNazwa(rs.getString(2));
            firma.setNip(rs.getString(3));
            firma.setNrBudynku(rs.getString(4));
            firma.setNrLokalu(rs.getString(5));
            firma.setUlica(rs.getString(6));
            firma.setKodPocztowy(rs.getString(7));
            firma.setMiejscowosc(rs.getString(8));
            firma.setNotatka(rs.getString(9));
            listaFirm.add(firma);
            }
            
            return (Firma) listaFirm.get(0);
        }
    
    
     public ArrayList<Firma> selectFirma(String szukaj) throws SQLException{
            ArrayList listaFirm = new ArrayList();
            Firma firma;
            String selekcja = "Nazwa LIKE '%"+szukaj+"%' ";
            String query = "SELECT * FROM Firma WHERE "+selekcja;
            ResultSet rs= con.querySelect(query);
            
            while(rs.next()){
                firma=new Firma();
                firma.setId(rs.getInt(1));
                firma.setNazwa(rs.getString(2));
                firma.setNip(rs.getString(3));
                firma.setNrBudynku(rs.getString(4));
                firma.setNrLokalu(rs.getString(5));
                firma.setUlica(rs.getString(6));
                firma.setKodPocztowy(rs.getString(7));
                firma.setMiejscowosc(rs.getString(8));
                firma.setNotatka(rs.getString(9));
                listaFirm.add(firma);
            }
            
            
            
            return listaFirm;
    }
    
    
}
