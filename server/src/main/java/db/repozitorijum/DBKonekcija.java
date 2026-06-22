package db.repozitorijum;
import java.sql.Connection;
import konfiguracija.Konfiguracija;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andjela
 */
public class DBKonekcija {
    
    private static DBKonekcija instance;

   
    private Connection connection;
    
    private DBKonekcija(){
        
        
        try {
            if(connection==null || connection.isClosed()){
        String url = Konfiguracija.getInstance().getProperty("url");
        String username = Konfiguracija.getInstance().getProperty("username");
        String password = Konfiguracija.getInstance().getProperty("password");
        
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);}
            
        } catch (SQLException ex) {
            Logger.getLogger(DBKonekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public static DBKonekcija getInstance(){
        if(instance==null){
            instance=new DBKonekcija();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
     public static void resetuj() {
          instance = null;
    }

 
    
    
    
}
