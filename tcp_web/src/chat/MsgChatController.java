package chat;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Player;
import rest.MsgChatRest;


@ManagedBean(name="msgChatController")
@ViewScoped
public class MsgChatController implements Serializable {

	private static final long serialVersionUID = -4469076347767463454L;
	
	private MsgChat chat;
	private ArrayList<MsgChat> chatList;
	private MsgChatRest rest;
	private Player player;
	private DateFormat dateFormat;
	
	public MsgChatController() throws IOException {
		super();
		refresh();
	}
	
	public void refresh() throws IOException{
		this.chat = new MsgChat();
		this.chatList = new ArrayList<MsgChat>();
		this.rest = new MsgChatRest();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.player = (Player) session.getAttribute("player");
		
		//get current date time with Date()
	}
	
	public String sendMsg(){
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		
		this.chat.setLogin(this.player.getLogin());
		this.chat.setDate(date);
		this.chat.setDateString(dateFormat.format(date));
		this.chatList.add(rest.sendMsg(getChat()));
		this.chat.setMsg(null);
		recieveMsg();
		
		return null;
	}
	
	public void recieveMsg(){
		System.out.println("Chatlist size: "+this.chatList.size());
		
	}
	
	/*
	 * GET AND SET
	 */
	public MsgChat getChat() {
		return chat;
	}
	public void setChat(MsgChat chat) {
		this.chat = chat;
	}
	public ArrayList<MsgChat> getChatList() {
		return chatList;
	}
	public void setChatList(ArrayList<MsgChat> chatList) {
		this.chatList = chatList;
	}

	public MsgChatRest getRest() {
		return rest;
	}

	public void setRest(MsgChatRest rest) {
		this.rest = rest;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	


}
