package rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import model.Player;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.Properties;
import config.ReadProperties;

public class LoginRest implements Serializable {

	private static final long serialVersionUID = 4090099969434848360L;
	
	private Player player;
	private List<Player> playerList;
	private String uri;
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	
	private String restServer;
	private String restPort;

	public LoginRest() throws IOException {
		player = new Player();
		playerList = new ArrayList<Player>();
		//properties = readProperties.getProp();

//		restServer =  properties.getProperty("ip.rest.server");
//		restPort = properties.getProperty("port.rest.server");
	//	uri = restServer+":"+restPort+"/domino_rest/rest";
		
		/*
		 * ERRO AQUI: TOMCAT NAO ENCONTRA O ARQUIVO PROPERTIES
		 */
		uri = "http://127.0.0.1:8080/domino_rest/rest";
	}
	
	public List<Player> authenticate(String login, String password){
		try{
			System.out.println("REST SERVER: "+uri);
			GenericType<List<Player>> generic = new GenericType<List<Player>>() {};
			WebResource resource = Client.create().resource(uri);
			/*
			 * ERRO AQUI: ENVIAR DOIS PARAMETROS VIA REST
			 */
			List<Player> result = resource
					.path("/authenticate")      
					.accept(MediaType.APPLICATION_JSON)
					.entity(login, password)
					.post(generic);

			playerList = result;
			return result;
		}catch(Exception e){
			System.err.println("******* ERRO NO ENVIO AO SERVIDOR REST *******"+e.getMessage());
			return null;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
		

}
