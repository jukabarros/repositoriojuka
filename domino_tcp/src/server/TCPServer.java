package server;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import config.ReadProperties;

public class TCPServer implements Serializable {

	private static final long serialVersionUID = -8446218767117876665L;
	private ServerSocket welcomeSocket;
	private Socket socket;
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	private String socketPort;
	private static int socketPortint;
	private TCPConnected tcpConnected;
	private ArrayList<TCPConnected> connectionList;
	private TCPController tcpController;
	
	public TCPServer() throws IOException {
		this.properties = readProperties.getProp();
		this.socketPort = properties.getProperty("port.tcp.server");
		socketPortint = Integer.parseInt(socketPort);
		
		this.connectionList = new ArrayList<TCPConnected>();
		
	}
	
	public static void main(String argv[]) throws Exception {
		try{
			TCPServer server = new TCPServer();
			server.welcomeSocket = new ServerSocket(socketPortint);
			System.out.println("TCP Server iniciado na porta: "+socketPortint);
			
			server.tcpController = new TCPController(server.connectionList);
			
			while(true) {
				server.socket = server.welcomeSocket.accept();
				server.tcpConnected = new TCPConnected(server.socket, server.tcpController);
				server.connectionList.add(server.tcpConnected);

			} // Fechando o WHILE
		}catch (Exception e){
			System.out.println("*** Erro no servidor: "+e.getMessage());
		}
	} // Fechando o MAIN

	
	/*
	 * GET AND SET
	 */
	public ArrayList<TCPConnected> getConnectionList() {
		return connectionList;
	}
	
	public void setConnectionList(ArrayList<TCPConnected> connectionList) {
		this.connectionList = connectionList;
	}

	public TCPController getTcpController() {
		return tcpController;
	}

	public void setTcpController(TCPController tcpController) {
		this.tcpController = tcpController;
	}
	

}
