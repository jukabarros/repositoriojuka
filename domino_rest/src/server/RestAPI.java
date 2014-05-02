package server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.PlayerDao;
import model.Player;

@Path("/")
public class RestAPI {

	PlayerDao dao = new PlayerDao();
	List<Player> playerList = new ArrayList<Player>();
	
	@POST
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> authenticate(String login, String password){
		
		System.out.println("REST API");
		playerList = dao.authenticate(login, password);
		return playerList;
	}
}
