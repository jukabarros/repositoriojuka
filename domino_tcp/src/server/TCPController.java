package server;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import config.ReadProperties;

public class TCPController implements Serializable{
	
	private static final long serialVersionUID = 3279655764974233100L;

	private ArrayList<TCPConnected> listConnection;
	private TCPConnected tcpConnected;
	
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	
	
	public TCPController(ArrayList<TCPConnected> listConnection) throws IOException {
		super();
		this.listConnection = listConnection;
		this.properties = readProperties.getProp();
	}
	
	/*
	 * Enviando msg para todos (broadcast)
	 * Tirar a msg para o remetente?
	 */
	public void sendMsgBroadCast(String message) throws IOException{
		System.out.println("Num de Conexoes: "+this.listConnection.size());
		for (int i = 0; i < this.listConnection.size(); i++) {
			this.listConnection.get(i).getOutToClient().writeBytes(message+properties.getProperty("tcp.msg.end"));
			
		}
	}
	
	public void removeTcpConnection(Thread tcpConnected){
		System.out.println("Removendo a conexao: "+tcpConnected+" da lista");
		this.listConnection.remove(tcpConnected);
	}
	/*
	 * GET AND SET
	 */
	public ArrayList<TCPConnected> getListConnection() {
		return listConnection;
	}

	public void setListConnection(ArrayList<TCPConnected> listConnection) {
		this.listConnection = listConnection;
	}


	public TCPConnected getTcpConnected() {
		return tcpConnected;
	}


	public void setTcpConnected(TCPConnected tcpConnected) {
		this.tcpConnected = tcpConnected;
	}
	
}
