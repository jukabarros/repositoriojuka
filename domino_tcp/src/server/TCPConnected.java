package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.MsgChat;

public class TCPConnected extends Thread{

	private BufferedReader inFromClient; 
	private DataOutputStream outToClient; 
	private String clientCommand;
	private String serverResponse;
	private Socket socketClient;
	private MsgChat msgChat;
	private ArrayList<MsgChat> msgChatlist;
	
	public TCPConnected(Socket socket) {
		try {
			socketClient = socket;
			System.out.println("Criando uma conexão tcp...");
			outToClient = new DataOutputStream(socketClient.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			msgChat = new MsgChat();
			msgChatlist = new ArrayList<MsgChat>();
			this.start();
		}
		catch (IOException e) {
			System.out.println("*** Erro na Conexao: " + e.getMessage());
		}
	}
	
	
	public void run() {
		boolean status = true;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		while(status == true){
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
					serverResponse = "the_end\n";
					outToClient.writeBytes(serverResponse);
					System.out.println("Encerrando a conexão com o cliente: "+brokenTcpMsg[2]);
					outToClient.close();
					inFromClient.close();
					socketClient.close();
					status = false;
					break;

				case "sendChatMsg":
//					String login = brokenTcpMsg[1];
//					String dateString = brokenTcpMsg[2];
//					String msg = brokenTcpMsg[3];
//					Date date = formatter.parse(dateString);
					

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


