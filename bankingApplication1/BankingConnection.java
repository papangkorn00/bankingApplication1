package bankingApplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankingConnection {
    public static Connection connect(){
        String URL = "jdbc:mysql://localhost:3306/mydb1";
        String username = "root";
        String password = "butter0025_P";
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BankingConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
}
