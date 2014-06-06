package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
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
	
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	private String socketPort;
	private static int socketPortint;
	private static String serverResponse;
	
	public TCPServer() throws IOException {
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		properties = readProperties.getProp();
		socketPort = properties.getProperty("port.tcp.server");
		socketPortint = Integer.parseInt(socketPort);
		
	}
	
	public static void main(String argv[]) throws Exception {
		String clientCommand;
		server = new TCPServer();
		welcomeSocket = new ServerSocket(socketPortint);
		System.out.println("TCP Server iniciado na porta: "+socketPortint);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		List<MsgChat> chatMsgList = new ArrayList<MsgChat>();
		Socket connectionSocket = welcomeSocket.accept();
		while(true) {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientCommand = inFromClient.readLine();
			
			System.out.println("Comando do Cliente: "+clientCommand);
			
			/*
			 * Tratamento dos Comandos recebidos do Cliente
			 */
			String[] brokenTcpMsg = clientCommand.split("::");
			String tcpCommandClient = brokenTcpMsg[0];
			
			/*
			 * Comando do Cliente
			 */
			switch (tcpCommandClient) {
			case "the_end":
				/*
				 * ERRO: Logout nao encerra a conexao tcp
				 */
				serverResponse = "the_end\n";
				outToClient.writeBytes(serverResponse);
				break;
				
			case "sendChatMsg":
				String login = brokenTcpMsg[1];
				String dateString = brokenTcpMsg[2];
				String msg = brokenTcpMsg[3];
				Date date = formatter.parse(dateString);
				
				server.addMsg(login, dateString, msg, date);
				
				serverResponse = "Retorno Cliente\n";
				
				outToClient.writeBytes(serverResponse);
				break;
				
			case "getChatMsg":
				
				serverResponse = "Retorno Cliente\n";
				
				outToClient.writeBytes(serverResponse);
				break;

			default:
				break;
			}//Fechando o SWITCH

		} // Fechando o WHILE
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
