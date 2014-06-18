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
	static TCPConnected tcpCon;
	private static Socket socket;
	
	public TCPServer() throws IOException {
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		properties = readProperties.getProp();
		socketPort = properties.getProperty("port.tcp.server");
		socketPortint = Integer.parseInt(socketPort);
		
	}
	
	public static void main(String argv[]) throws Exception {
		try{
			
			String clientCommand;
			server = new TCPServer();
			welcomeSocket = new ServerSocket(socketPortint);
			System.out.println("TCP Server iniciado na porta: "+socketPortint);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			ArrayList<String> tcpConnection = new ArrayList<String>();
//		List<MsgChat> chatMsgList = new ArrayList<MsgChat>();
			socket = welcomeSocket.accept();
			while(true) {
				System.out.println("INICIO DO WHILE");
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
				clientCommand = inFromClient.readLine();
				System.out.println("Conexao ID: "+clientCommand);
				/*
				 * Tratamento dos Comandos recebidos do Cliente
				 */
				String[] brokenTcpMsg = clientCommand.split("::");
				
				/*
				 * Controle das Conexoes
				 */
				System.out.println("Comando do Cliente: "+clientCommand);
				String tcpIdentifier = brokenTcpMsg[0];
				System.out.println("TCP ID: "+tcpIdentifier);
				System.out.println("Lista de Conexoes: "+tcpConnection.size());
				if (!tcpConnection.contains(tcpIdentifier) && !tcpConnection.isEmpty()){
					Socket socket2 = welcomeSocket.accept();
					System.out.println("Criando novo socket...");
					inFromClient = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
					outToClient = new DataOutputStream(socket2.getOutputStream());
					clientCommand = inFromClient.readLine();
					tcpConnection.add(tcpIdentifier);
				}
				
//			tcpCon = new TCPConnected(connectionSocket);
				
				System.out.println("Comando do Cliente: "+clientCommand);
				
				
				/*
				 * Comando do Cliente
				 */
				String tcpCommandClient = brokenTcpMsg[1];

				switch (tcpCommandClient) {
				case "the_end":
					/*
					 * ERRO: Logout nao encerra a conexao tcp
					 */
					serverResponse = "the_end\n";
					outToClient.writeBytes(serverResponse);
					socket.close();
					break;
					
				case "sendChatMsg":
//					String login = brokenTcpMsg[2];
//					String dateString = brokenTcpMsg[3];
//					String msg = brokenTcpMsg[4];
//					Date date = formatter.parse(dateString);
//					
//					server.addMsg(login, dateString, msg, date);
//					
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
