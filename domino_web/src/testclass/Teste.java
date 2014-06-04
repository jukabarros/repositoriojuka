package testclass;

import java.io.IOException;
import java.util.Properties;

import config.ReadProperties;

public class Teste {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ReadProperties readProperties = new ReadProperties();
		Properties properties;
		
		String restServer;
		String restPort;
		
		properties = readProperties.getProp();
		restServer =  properties.getProperty("ip.rest.server");
		restPort = properties.getProperty("port.rest.server");
		String uri = restServer+":"+restPort+"/domino_rest/rest";
		
		System.out.println("URI: "+uri);
	}

}
