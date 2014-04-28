package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConection {
	Connection conn;
	Statement stmt;
	
	public Connection connect(){
		
		try{
			String host = "localhost";
			String user = "root";
			String password = "bsi2011";
			String bd = "tcp_domino";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+bd, user, password);
			return conn;
		}catch (Exception e){
			System.out.println("Conection Fail: "+e.getMessage());
		}
		return conn;
	}

}
