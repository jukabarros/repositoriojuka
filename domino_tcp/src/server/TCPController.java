package server;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TCPController implements Serializable{
	
	private static final long serialVersionUID = 3279655764974233100L;

	private ArrayList<TCPConnected> listConnection;
	private TCPConnected tcpConnected;
	
	public TCPController(ArrayList<TCPConnected> listConnection) {
		super();
		this.listConnection = listConnection;
	}
	
	
	public void sendChatMsgToAll(String message) throws IOException{
		System.out.println("Num de Conexoes: "+this.listConnection.size());
		for (int i = 0; i < this.listConnection.size(); i++) {
			System.out.println("CONEXAO: "+this.listConnection.get(i));
			this.listConnection.get(i).getOutToClient().writeBytes(message+"\n");
			
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
