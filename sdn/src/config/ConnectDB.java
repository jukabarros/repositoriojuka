package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class ConnectDB {
	
	Connection conn;
	Statement stmt;
	
	public Connection connect(){
		
		try{
			String host = "150.161.11.196";
			String user = "root";
			String password = "sdn2014";
			String bd = "sdn";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+bd, user, password);
			return conn;
		}catch (Exception e){
			System.out.println("Erro ao se conectar com o BD: "+e.getMessage());
		}
		return conn;
	}

}
