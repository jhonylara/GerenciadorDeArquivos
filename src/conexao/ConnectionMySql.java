package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConnectionMySql {

        private static final String driver = "com.mysql.jdbc.Driver";
        private static final String url = "jdbc:mysql://localhost:3306/gerenciador_arquivos?useSSL=false";
        private static final String user = "root";
        private static final String pass = "jhony";
        
        public static Connection getConnection(){
            
            try {
                Class.forName(driver);     
                return DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException | SQLException ex) {
            	JOptionPane.showMessageDialog(null, "Falha ao conectar ao DB", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Falha ao conectar ao DB", ex);
            }
        }
        
        public static void endConnection (Connection connect){
            
            try {
                if(connect != null){
                    connect.close();
                }
            } catch (SQLException ex) {            	
                Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        public static void endConnection (Connection connect, PreparedStatement stat){
            
            
            endConnection(connect);
            
            try {
                if(stat != null){
                    stat.close();
                }
            } catch (SQLException ex) {   
                Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        public static void endConnection (Connection connect, PreparedStatement stat, ResultSet result){
            
            
            endConnection(connect , stat);
            
            try {
                if(result != null){
                    result.close();
                }
            } catch (SQLException ex) {   
                Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }

}
