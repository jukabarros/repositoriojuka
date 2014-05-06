package server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import model.Player;
import dao.PlayerDao;

@Path("/")
public class RestAPI {

	PlayerDao dao = new PlayerDao();
	List<Player> playerList = new ArrayList<Player>();
	
	@POST
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> authenticate(MultivaluedMap<String, String> formParams){
		
		playerList = dao.authenticate(formParams.getFirst("login"), formParams.getFirst("password"));
		return playerList;
	}
	
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(String playerID){
		
		String logout = dao.logout(playerID);
		
		return logout;
		
	}
}
