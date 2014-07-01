package controller;

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

import model.MsgChat;
import model.Player;
import rest.LoginRest;
import tcp.TCPClient;


@ManagedBean(name="gameController")
@ViewScoped
public class GameController implements Serializable {

	private static final long serialVersionUID = -4469076347767463454L;
	
	private MsgChat msgChat;
	private ArrayList<MsgChat> msgChatList;
	private LoginRest loginRest;
	private Player player;
	private DateFormat dateFormat;
	private TCPClient tcpClient;
	
	private boolean statusCon;
	
	
	public GameController() throws IOException {
		this.tcpClient = new TCPClient();
		this.tcpClient.tcpConnect();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.player = (Player) session.getAttribute("player");
		this.loginRest = new LoginRest();
		this.msgChat = new MsgChat();
		this.msgChatList = new ArrayList<MsgChat>();
		this.statusCon = true;
		
		getChatMsg();
	}
	
	public String sendChatMsg() throws IOException{
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		
		this.msgChat.setLogin(this.player.getLogin());
		this.msgChat.setDate(date);
		this.msgChat.setDateString(dateFormat.format(date));
		String commandTCP = this.player.getId().toString()+"::sendChatMsg::"+this.msgChat.getLogin()+"::"+this.msgChat.getDateString()+"::"+this.msgChat.getMsg();

		this.tcpClient.sendTCPMsg(commandTCP);
		this.msgChat = new MsgChat();
		return null;
	}
	
	public void getChatMsg() throws IOException{
		String serverResponse = tcpClient.getChatMsg();
		String[] brokenTcpMsg = serverResponse.split("::");
		/*
		 * Montando a msg que vem do servidor
		 */
		this.msgChat.setLogin(brokenTcpMsg[2]);
		this.msgChat.setDateString(brokenTcpMsg[3]);
		this.msgChat.setMsg(brokenTcpMsg[4]);
		/*
		 * add na lista para ser visualizada
		 */
		setMsgChatList(this.addMsgChatList(msgChat));
	}
	
	
	public ArrayList<MsgChat> addMsgChatList (MsgChat msgChat){
		this.msgChatList.add(msgChat);
		return this.msgChatList;
	}
	
	public String logout() throws IOException{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.player = (Player) session.getAttribute("player");
		tcpClient.sendTCPMsg(this.player.getId().toString()+"::logout::"+this.player.getLogin());
		String responseRest = this.loginRest.logout(this.player.getId().toString());
		session.invalidate();
		
		this.statusCon = false;
		
		if (responseRest.equals("logoutOK")){
			System.out.println("** Player: "+player.getLogin()+" logout!");
			return "index.xhtml?faces-redirect=true";
		}else{
			System.err.println("Erro ao fazer o logout");
			return null;
		}
		
	}
	/*
	 * GET AND SET
	 */
	public MsgChat getMsgChat() {
		return msgChat;
	}
	public void setMsgChat(MsgChat msgChat) {
		this.msgChat = msgChat;
	}
	public ArrayList<MsgChat> getMsgChatList() {
		return msgChatList;
	}
	public void setMsgChatList(ArrayList<MsgChat> msgChatList) {
		this.msgChatList = msgChatList;
	}

	public LoginRest getLoginRest() {
		return loginRest;
	}

	public void setLoginRest(LoginRest loginRest) {
		this.loginRest = loginRest;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	

}
