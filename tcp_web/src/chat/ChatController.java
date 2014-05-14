package chat;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Player;
import rest.ChatRest;


@ManagedBean(name="chatController")
@ViewScoped
public class ChatController implements Serializable {

	private static final long serialVersionUID = -4469076347767463454L;
	
	private Chat chat;
	private ArrayList<Chat> chatList;
	private ChatRest rest;
	private Player player;
	
	public ChatController() {
		super();
		refresh();
	}
	
	public void refresh(){
		this.chat = new Chat();
		this.chatList = new ArrayList<Chat>();
		this.rest = new ChatRest();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.player = (Player) session.getAttribute("player");
	}
	
	public void sendMsg(){
		
	}
	
	public void recieveMsg(){
		
	}
	
	/*
	 * GET AND SET
	 */
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public ArrayList<Chat> getChatList() {
		return chatList;
	}
	public void setChatList(ArrayList<Chat> chatList) {
		this.chatList = chatList;
	}

	public ChatRest getRest() {
		return rest;
	}

	public void setRest(ChatRest rest) {
		this.rest = rest;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	


}
