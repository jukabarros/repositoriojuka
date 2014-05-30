package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.MsgChat;


public class TCPServer implements Serializable {

	private static final long serialVersionUID = -8446218767117876665L;
	private MsgChat chat;
	private List<MsgChat> chatList;
	static TCPServer server;
	private static ServerSocket welcomeSocket;

	public TCPServer() {
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		System.out.println("TCP Server iniciado...");
	}
	
	public static void main(String argv[]) throws Exception {
		String clientCommand;
		server = new TCPServer();
		welcomeSocket = new ServerSocket(1901);
		while(true) {
				
	          Socket connectionSocket = welcomeSocket.accept();
	          BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	          DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	          clientCommand = inFromClient.readLine();

	          /*
	           * Tratamento dos Comandos recebidos do Cliente
	           */
	          String[] brokenString = clientCommand.split("::");
	          String tcpCommandClient = brokenString[0]; // String de Comando do cliente
	          if (tcpCommandClient.equals("sendChatMsg")){
	        	  System.out.println("MSG CHEGOU NO SERVIDOR");
	        	  outToClient.writeBytes(clientCommand);
	          }
	          
		} // Fechando o WHILE
		
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
	
	
	

}
