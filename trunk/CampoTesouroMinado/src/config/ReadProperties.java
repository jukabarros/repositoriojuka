package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "./configJogo.properties");
        props.load(file);
        return props;
 
    }
 
    public static void  main(String args[]) throws IOException {
        String login; //Variavel que guardará o login do servidor.
        String host; //Variavel que guardará o host do servidor.
        String password; //Variável que guardará o password do usúario.
         
        Properties prop = getProp();
        
        host = prop.getProperty("prop.test.host");
        login = prop.getProperty("prop.test.login");
        password = prop.getProperty("prop.test.password");
         
        System.out.println("Login = " + login);
        System.out.println("Host = " + host);
        System.out.println("Password = " + password);
    }
}