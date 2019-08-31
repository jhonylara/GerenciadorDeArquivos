package modelo;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexao.ConnectionMySql;

public class RegionalDAO {
	
    
    

	public ArrayList<String> regionalBanco(String comando, String resultado) {
		ArrayList<String> item = new ArrayList<String>();
		
		Connection connect = ConnectionMySql.getConnection();
	    PreparedStatement stat = null;
	    ResultSet result = null;
		
		try{ 

			stat = connect.prepareStatement(comando);
            result = stat.executeQuery();
			
			while(result.next()) {
				item.add(result.getString(resultado));
			}
			
		 } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "ERRO AO POPULAR STRING");
	     }finally{
	            ConnectionMySql.endConnection(connect, stat, result);
	     }
		
		return item;		
	}
	
	public ArrayList<String> passarParametrosRegional() {
		return regionalBanco("SELECT * FROM regional", "regionalName");
	}
	

	
	
	
	
	public void adicionarRegional(Regional arq){
	    
        Connection connect = ConnectionMySql.getConnection();
        PreparedStatement stat = null;
        
        try {
            stat = connect.prepareStatement("INSERT INTO regional (regionalName) VALUES (?)");
            stat.setString(1, arq.getRegionalName());
   

            
            
            stat.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "REGIONAL SALVA COM SUCESSO");
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FALHA AO SALVAR REGIONAL NO BD");
        }finally{
            ConnectionMySql.endConnection(connect, stat);
        }
    
    }

    public String nomeRegional(int regionalID){
   	 Connection connect = ConnectionMySql.getConnection();
        PreparedStatement stat = null;
        ResultSet result = null;
        String nome = null;
        
        try {
	         stat = connect.prepareStatement("SELECT * FROM regional WHERE regionalID = ?");
	         stat.setInt(1, regionalID);
	         result = stat.executeQuery();
	         
	         if(result.next()){
	        	nome = result.getString("regionalName");
	         }
	         

        }
   	 catch (SQLException ex){
   		 JOptionPane.showMessageDialog(null, "ERRO AO PEGAR NOME DO REGIONAL POR ID");
   	 }finally{
            ConnectionMySql.endConnection(connect, stat, result);
     }
        
   	 return nome;
     }
    
    
	
 //TESTE OUTRO MEIO
    
    public ArrayList<String> comboRegional(){
    	ArrayList<String> item = new ArrayList<String>();
      	 Connection connect = ConnectionMySql.getConnection();
           PreparedStatement stat = null;
           ResultSet result = null;
          
           
           try {
   	         stat = connect.prepareStatement("SELECT * FROM regional");
   	         
   	         result = stat.executeQuery();
   	         
   	         while(result.next()){
   	        	
   	        	item.add(/*String.valueOf((result.getInt("regionalID"))) + " - " + */result.getString("regionalName"));
   	        	
   	         }
   	         

           }
      	 catch (SQLException ex){
      		 JOptionPane.showMessageDialog(null, "ERRO AO PEGAR NOME DO REGIONAL POR ID");
      	 }finally{
               ConnectionMySql.endConnection(connect, stat, result);
        }
           
      	 return item;
        }
    
}
