package Enity;

import java.sql.*; 
import javax.swing.JOptionPane;
public final class Connector {
    public Connection con; 
    public Connector() {
        dbConnect("127.0.0.1", "ZI", "sa", "zaq1@WSX");
        
    }
    
    public Connector(String dbHost, String dbName, String dbUser, String dbPass){
        dbConnect(dbHost, dbName, dbUser, dbPass);
    }
    
    public void dbConnect(String db_host, String db_name,
        String db_userid, String db_password) {
        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String driver="jdbc:sqlserver://" + db_host + ";databaseName=" + db_name + ";";
            try {
                DriverManager.setLoginTimeout(5);
                con = DriverManager.getConnection(driver,db_userid,db_password);
               
                      
}
            catch (SQLException ex) {
                    JOptionPane.showMessageDialog (null, "Błąd przy połączeniu z bazą danych =>" + ex.getErrorCode()+"\n"
                            +ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) { e.printStackTrace(); }
        
        
      }
    public static void testConect(String db_host, String db_name,
        String db_userid, String db_password){
        
        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String driver="jdbc:sqlserver://" + db_host + ";databaseName=" + db_name + ";";
            try {
                DriverManager.setLoginTimeout(5);
                DriverManager.getConnection(driver,db_userid,db_password);
                JOptionPane.showMessageDialog (null, "Połączenie OK", "OK", JOptionPane.INFORMATION_MESSAGE);
                      
}  
            catch (SQLException ex) {
                    JOptionPane.showMessageDialog (null, "Błąd przy połączeniu z bazą danych =>" + ex.getErrorCode()+"\n"
                            +ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) { e.printStackTrace(); }
        
        
    }
    
    public void dbDisconnect() {
        try {
        con.close();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    public ResultSet querySelect(String queryString) {
    ResultSet rs = null;
        try {
            Statement statement = con.createStatement();
            rs = statement.executeQuery(queryString);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null, "Błąd polecnia Select"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
    public void query(String queryString) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(queryString);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null, "Błąd polecenia Insert =>" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
       
    public void queryDelete(String queryString){
    
    
    }
    public void queryUpdate(String queryString){
    
    
    }
    
    public void setAutoCommit(Boolean a) {
        try {
            con.setAutoCommit(a);
        } catch (SQLException ex) { 
                JOptionPane.showMessageDialog (null, "Wystąpił błąd "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public void commit() {
        try {
            con.commit();
        } catch (SQLException ex) { JOptionPane.showMessageDialog (null, "Wystąpił błąd "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    
    public void rollback() {
        try {
            con.rollback();
        } catch (SQLException ex) {  JOptionPane.showMessageDialog (null, "Wystąpił błąd "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
}