package controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import config.ReadProperties;
import model.MsgChat;
import model.Player;
import rest.LoginRest;
import service.PlayerService;
import tcp.TCPClient;


@ManagedBean(name="gameController")
@ViewScoped
public class GameController implements Serializable {

	private static final long serialVersionUID = -4469076347767463454L;
	
	private MsgChat msgChat;
	private ArrayList<MsgChat> msgChatList;
	private LoginRest loginRest; // logout
	private Player player;
	private DateFormat dateFormat;
	private TCPClient tcpClient;
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;

	private PlayerService playerService;
	private List<Player> playerList; // para listar os players online
	
	public GameController() throws IOException {
		this.properties = readProperties.getProp();
		
		this.tcpClient = new TCPClient();
		this.tcpClient.tcpConnect();
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.player = (Player) session.getAttribute("player");
		
		this.loginRest = new LoginRest(); // Logout
		
		this.msgChat = new MsgChat();
		this.msgChatList = new ArrayList<MsgChat>();
		
		this.playerService = new PlayerService();
		this.playerList = playerService.listPlayerOnline();
		
		/*
		 * Msg broadcast avisando aos outros que está online
		 */
		String msgWelcome = this.player.getId().toString()
				+this.properties.getProperty("tcp.msg.split")+
				"welcomePlayer"+
				this.properties.getProperty("tcp.msg.split")+
				this.player.getLogin();
		
		this.tcpClient.sendTCPMsg(msgWelcome);
	}
	
	public String sendChatMsg() throws IOException{
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		/*
		 * ERRO QUANDO ENVIA MSG COM QUEBRA DE LINHA!!
		 * CRIAR UM TEMPO PARA ENVIO DE MSG POR CLIENTE, PARA NAO SOBRECARREGAR O SERVIDOR
		 */
		this.msgChat.setLogin(this.player.getLogin());
		this.msgChat.setDate(date);
		this.msgChat.setDateString(dateFormat.format(date));
		String commandTCP = this.player.getId().toString()+this.properties.getProperty("tcp.msg.split")
				+"sendChatMsg"
				+this.properties.getProperty("tcp.msg.split")
				+this.msgChat.getLogin()
				+this.properties.getProperty("tcp.msg.split")
				+this.msgChat.getDateString()
				+this.properties.getProperty("tcp.msg.split")
				+this.msgChat.getMsg();

		this.tcpClient.sendTCPMsg(commandTCP);
		this.msgChatList = addMsgChatList(this.msgChat);

		this.msgChat = new MsgChat();
		return null;
	}
	
	public void getServerMsg() throws IOException{
		String commandTCP = this.player.getId().toString()+
				this.properties.getProperty("tcp.msg.split")+"getServerMsg"+this.player.getLogin();

		this.tcpClient.sendTCPMsg(commandTCP);
		/*
		 * Fazer o temporizador
		 */
//		boolean timeOverFlow = false;
//		while(timeOverFlow == false){

		String serverResponse = tcpClient.getServerMsg();

		/*
		 * Tratamento dos Comandos recebidos do Cliente
		 * Inserir o separador em um properties
		 */
		String[] brokenTcpMsg = serverResponse.split(properties.getProperty("tcp.msg.split"));
		String tcpCommandClient = brokenTcpMsg[1];

		/*
		 * Comando do Cliente
		 */
		switch (tcpCommandClient) {

		case "welcomePlayer":
			String loginPlayer = brokenTcpMsg[2];
			Player playerOnline = new Player();
			playerOnline = this.playerService.listPlayerByLoginService(loginPlayer);
			this.playerList.add(playerOnline); // Adicionando na lista
			break;

		case "logout":
			String login = brokenTcpMsg[1];
			Player p = new Player();
			p = this.playerService.listPlayerByLoginService(login);
			this.playerList.remove(p); // Removendo da lista

			break;

		case "sendChatMsg":
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
			break;

		default:
			//				timeOverFlow = true;
			break;

		} // Fechando Switch
		//		} // Fechando While

	}
	
	
	public ArrayList<MsgChat> addMsgChatList (MsgChat msgChat){
		this.msgChatList.add(msgChat);
		return this.msgChatList;
	}
	
	public String logout() throws IOException{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.player = (Player) session.getAttribute("player");
		tcpClient.sendTCPMsg(this.player.getId().toString()+this.properties.getProperty("tcp.msg.split")
				+"logout"
				+this.properties.getProperty("tcp.msg.split")
				+this.player.getLogin());
		String responseRest = this.loginRest.logout(this.player.getId().toString());
		session.invalidate();
		
		
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

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

}