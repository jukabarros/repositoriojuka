package config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conexao extends DriverManagerDataSource {
	
	public Conexao(){
		
		this.setDriverClassName("com.mysql.jdbc.Driver");
		this.setUrl("jdbc:mysql://localhost:3306/usuario");
		this.setUsername("root");
		this.setPassword("bsi2011");		
		
	}
	
	
}
