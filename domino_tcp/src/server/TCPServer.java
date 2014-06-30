package server;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
	private static ArrayList<TCPConnected> connectionList;
	
	public TCPServer() throws IOException {
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		properties = readProperties.getProp();
		socketPort = properties.getProperty("port.tcp.server");
		socketPortint = Integer.parseInt(socketPort);
		
		connectionList = new ArrayList<TCPConnected>();
		
	}
	
	public void removeTCPConnection(){
		System.out.println("***** TCP CON: "+tcpCon);
		connectionList.remove(tcpCon);
	}
	
	public static void main(String argv[]) throws Exception {
		try{
			server = new TCPServer();
			welcomeSocket = new ServerSocket(socketPortint);
			System.out.println("TCP Server iniciado na porta: "+socketPortint);
			
			while(true) {
				socket = welcomeSocket.accept();
				tcpCon = new TCPConnected(socket);
				connectionList.add(tcpCon);
				System.out.println("CON LIST: "+connectionList);

			} // Fechando o WHILE
		}catch (Exception e){
			System.out.println("*** Erro no servidor: "+e.getMessage());
		}
	} // Fechando o MAIN
	
	
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

	public static ArrayList<TCPConnected> getConnectionList() {
		return connectionList;
	}

	public static void setConnectionList(ArrayList<TCPConnected> connectionList) {
		TCPServer.connectionList = connectionList;
	}
	
	
	

}
