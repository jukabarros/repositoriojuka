package server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import chat.MsgChat;
import model.Player;
import dao.MsgChatDao;
import dao.PlayerDaoRest;

@Path("/")
public class RestAPI {

	PlayerDaoRest playerDao;
	List<Player> playerList;
	MsgChat chat;
	ArrayList<MsgChat> chatList;
	MsgChatDao chatDao;
	
	public RestAPI() {
		super();
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		chatDao = new MsgChatDao();
		playerDao = new PlayerDaoRest();
		playerList = new ArrayList<Player>();
	}
	
	@POST
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> authenticate(MultivaluedMap<String, String> formParams){
		
		playerList = playerDao.authenticate(formParams.getFirst("login"), formParams.getFirst("password"));
		return playerList;
	}
	
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(String playerID){
		
		String logout = playerDao.logout(playerID);
		
		return logout;
		
	}
	
	@GET
	@Path("/getMsg")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MsgChat> getMsg(){
		System.out.println("GET MSG");
		return this.chatList;
		
	}
	
	@POST
	@Path("/sendChatMsg")
	@Produces(MediaType.APPLICATION_JSON)
	public MsgChat sendMsgChat(MsgChat chatMsg){
		
		this.chatList.add(chatMsg);
//		System.out.println("*** Msg Chat: ");
//		for (int i = 0; i < this.chatList.size(); i++) {
//			System.out.println(chatMsg.getLogin() +": " +chatMsg.getMsg() );
//		}
		
		return chatMsg;
		
	}
	
	
}
