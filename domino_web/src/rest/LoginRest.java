package rest;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.web.client.RestClientException;

import model.Player;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

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
		properties = readProperties.getProp();

		restServer =  properties.getProperty("ip.rest.server");
		restPort = properties.getProperty("port.rest.server");
		uri = "http://"+restServer+":"+restPort+"/domino_rest/rest";
	}
	
	public List<Player> authenticate(String login, String password){
		try{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println("REST REQUEST: LOGIN - "+dateFormat.format(date));
			GenericType<List<Player>> generic = new GenericType<List<Player>>() {};
			WebResource resource = Client.create().resource(uri);
			
			MultivaluedMap<String, String> formParams = new MultivaluedMapImpl();
			formParams.add("login", login);
			formParams.add("password", password);

			List<Player> result = resource
					.path("/authenticate")      
					.accept(MediaType.APPLICATION_JSON)
					.entity(formParams)
					.post(generic);

			return result;
		}catch(RestClientException e){
			System.err.println("******* ERRO NO LOGIN VIA REST: RestClient Exception *******"+e.getMessage());
			return null;
		}catch (Exception e) {
			System.err.println("******* ERRO NO LOGIN VIA REST: Exception *******"+e.getMessage());
			return null;
		}
	}
	
	public String logout(String playerID){
		try{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println("REST REQUEST: LOGOUT - "+dateFormat.format(date));
			GenericType<String> generic = new GenericType<String>() {};
			WebResource resource = Client.create().resource(uri);
			
			String result = resource
					.path("/logout")      
					.accept(MediaType.APPLICATION_JSON)
					.entity(playerID)
					.post(generic);
			
			
			return result;
			
		}catch(Exception e){
			System.err.println("******* ERRO NO LOGOUT VIA REST *******"+e.getMessage());
			return null;
		}
	}
	
	/*
	 * GET AND SET
	 */
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
