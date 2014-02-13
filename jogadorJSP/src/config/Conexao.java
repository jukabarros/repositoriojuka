package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {
	
	Connection conn;
	Statement stmt;
	
	public Connection conectar(){
		
		try{
			String host = "localhost";
			String user = "root";
			String password = "bsi2012";
			String bd = "jspufpe";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+bd, user, password);
			System.out.println("ConexaoOK");
			return conn;
		}catch (Exception e){
			System.out.println("Erro ao se conectar com o BD: "+e.getMessage());
		}
		return conn;
	}

}
