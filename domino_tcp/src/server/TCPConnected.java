package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;

import model.MsgChat;

public class TCPConnected extends Thread{

	private BufferedReader inFromClient; 
	private DataOutputStream outToClient; 
	private String clientCommand;
	private String serverResponse;
	private Socket socketClient;
	private MsgChat msgChat;
	private TCPServer tcpServer;
	
	public TCPConnected(Socket socket) throws IOException {
		msgChat = new MsgChat();
		try {
			socketClient = socket;
			System.out.println("Criando uma conexão tcp...");
			outToClient = new DataOutputStream(socketClient.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			
			this.start();
		}
		catch (IOException e) {
			System.out.println("*** Erro na Conexao: " + e.getMessage());
		}
	}
	
	public void sendChatMsgToAll(String message) throws IOException{
		System.out.println("Num de Conexoes: "+tcpServer.getConnectionList().size());
		/*
		 * Erro AQUI
		 */
		for (TCPConnected allSockets : tcpServer.getConnectionList()) {
			outToClient.writeBytes(message + '\n');
		}
	}
	
	public void run() {
		boolean statusConnection = true;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		while(statusConnection == true){
			try {
				clientCommand = inFromClient.readLine();
				System.out.println("Comando do Cliente: "+clientCommand);

				/*
				 * Tratamento dos Comandos recebidos do Cliente
				 */
				String[] brokenTcpMsg = clientCommand.split("::");
				String tcpCommandClient = brokenTcpMsg[1];

				/*
				 * Comando do Cliente
				 */
				switch (tcpCommandClient) {
				case "logout":
					System.out.println("Encerrando a conexão com o cliente: "+brokenTcpMsg[2]);
					serverResponse = "the_end\n";
					outToClient.writeBytes(serverResponse);
					outToClient.close();
					inFromClient.close();
					socketClient.close();
					statusConnection = false;
					/*
					 * Remover da lista de conexoes
					 */
					System.out.println("TCP CON ATUALIZADA: "+tcpServer.getConnectionList());
					tcpServer.removeTCPConnection();
					break;

				case "sendChatMsg":
					//serverResponse = "Retorno Cliente\n";
					sendChatMsgToAll(clientCommand);
					//outToClient.writeBytes(serverResponse);
					break;

				default:
					break;
				}//Fechando o SWITCH
			}
			catch (EOFException e) {
				System.out.println("*** Erro na Conexão: EOFException " + e.getMessage());
			}
			catch (IOException e) {
				System.out.println("*** Erro na Conexão: IOException " + e.getMessage());
			} 

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

}


