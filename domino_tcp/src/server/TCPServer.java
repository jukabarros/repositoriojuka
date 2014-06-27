package server;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import model.MsgChat;
import config.ReadProperties;


public class TCPServer implements Serializable {

	private static final long serialVersionUID = -8446218767117876665L;
	private MsgChat chat;
	private List<MsgChat> chatList;
	static TCPServer server;
	private static ServerSocket welcomeSocket;
	private static Socket socket;
	
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	private String socketPort;
	private static int socketPortint;
	static TCPConnected tcpCon;
	
	public TCPServer() throws IOException {
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		properties = readProperties.getProp();
		socketPort = properties.getProperty("port.tcp.server");
		socketPortint = Integer.parseInt(socketPort);
		
	}
	
	public static void main(String argv[]) throws Exception {
		try{
			server = new TCPServer();
			welcomeSocket = new ServerSocket(socketPortint);
			System.out.println("TCP Server iniciado na porta: "+socketPortint);
			
			while(true) {
				socket = welcomeSocket.accept();
				tcpCon = new TCPConnected(socket);
				

			} // Fechando o WHILE
		}catch (Exception e){
			System.out.println("*** Erro no servidor: "+e.getMessage());
		}
	} // Fechando o MAIN
	
	public List<MsgChat> addMsg(String login, String dateString, String msg, Date date){
		chat = new MsgChat();
		chat.setLogin(login);
		chat.setDateString(dateString);
		chat.setMsg(msg);
		chat.setDate(date);
		
		chatList.add(chat);
		
		System.out.println("CHAT MSG");
		for (int i = 0; i < chatList.size(); i++) {
			System.out.println("LOGIN: "+ chatList.get(i).getLogin());
			System.out.println("DATE: "+ chatList.get(i).getDateString());
			System.out.println("MSG: "+ chatList.get(i).getMsg());
			System.out.println("\n");
			
		}
		return chatList;
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
	public List<MsgChat> getChatList() {
		return chatList;
	}
	public void setChatList(List<MsgChat> chatList) {
		this.chatList = chatList;
	}
	
	
	

}
