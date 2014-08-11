package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.MsgChat;
import config.ReadProperties;

public class TCPClient implements Serializable {

	private static final long serialVersionUID = -8446218767117876665L;
	
	private MsgChat chat;
	private List<MsgChat> chatList;
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	
	private String socketServer;
	private String socketPort;
	private Socket clientSocket;
	private DataOutputStream outToServer; 
	private BufferedReader inFromServer; 
	private String serverResponse;
	
	
	public TCPClient() throws IOException {
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		properties = readProperties.getProp();
		socketServer =  properties.getProperty("ip.tcp.server");
		socketPort = properties.getProperty("port.tcp.server");
	}
	
	public String sendTCPMsg(String command){
		try {
			outToServer.writeBytes(command + properties.getProperty("tcp.msg.end"));
			serverResponse = inFromServer.readLine();
			System.out.println("Server Reponse: "+serverResponse);
			if(serverResponse.equalsIgnoreCase("the_end")){
				tcpDesconnect();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serverResponse;
	}
	
	public String getServerMsg() throws IOException{
		serverResponse = inFromServer.readLine();
		return serverResponse;
	}
	
	
	public void tcpConnect(){
		try{
			int socketPortint = Integer.parseInt(socketPort);
			clientSocket = new Socket(socketServer, socketPortint);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//	outToServer.writeBytes(idConection + '\n'); // Enviando o ID da conexao
			System.out.println("Conexao TCP OK");
			 
		}catch(Exception e ){
			System.err.println("*** Erro ao conectar com o servidor: "+e.getMessage());
		}
	}
	
	public void tcpDesconnect(){
		try {
			outToServer.close();
			inFromServer.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public ReadProperties getReadProperties() {
		return readProperties;
	}
	public void setReadProperties(ReadProperties readProperties) {
		this.readProperties = readProperties;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public String getSocketServer() {
		return socketServer;
	}
	public void setSocketServer(String socketServer) {
		this.socketServer = socketServer;
	}
	public String getSocketPort() {
		return socketPort;
	}
	public void setSocketPort(String socketPort) {
		this.socketPort = socketPort;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public DataOutputStream getOutToServer() {
		return outToServer;
	}

	public void setOutToServer(DataOutputStream outToServer) {
		this.outToServer = outToServer;
	}

	public BufferedReader getInFromServer() {
		return inFromServer;
	}

	public void setInFromServer(BufferedReader inFromServer) {
		this.inFromServer = inFromServer;
	}

	public String getServerResponse() {
		return serverResponse;
	}

	public void setServerResponse(String serverResponse) {
		this.serverResponse = serverResponse;
	}
	
}
