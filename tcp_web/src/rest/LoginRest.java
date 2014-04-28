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
	
	private String login;
	private String password;
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
		properties = readProperties.getProp();
		
		/*
		 * Verificar URI
		 */
		restServer =  properties.getProperty("ip.rest.server");
		restPort = properties.getProperty("port.rest.server");
		uri = restServer+restPort+"/domino_rest/rest";
		
	}
	
	/*
	 * TERMINAR
	 */
	public List<Player> authenticate(){
		try{
			
			GenericType<List<Player>> generic = new GenericType<List<Player>>() {};
			WebResource resource = Client.create().resource(uri);
			List<Player> result = resource
					.path("/authenticate")      
					.accept(MediaType.APPLICATION_JSON)
					.post(generic);

			playerList = result;
			return result;
		}catch(Exception e){
			System.err.println("******* ERRO *******"+e.getMessage());
			return null;
		}
	}

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
