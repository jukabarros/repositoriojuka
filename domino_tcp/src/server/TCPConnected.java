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

public class TCPConnected extends Thread{

	BufferedReader inFromClient; 
	DataOutputStream outToClient; 
	String clientCommand;
	String serverResponse;
	Socket socketClient;
	
	public TCPConnected(Socket socket) {
		try {
			socketClient = socket;
			System.out.println("Criando uma conexão tcp...");
			outToClient = new DataOutputStream(socketClient.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			this.start();
		}
		catch (IOException e) {
			System.out.println("Erro IO Conexao: " + e.getMessage());
		}
	}

	public void run() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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
			case "the_end":
				/*
				 * ERRO: Logout nao encerra a conexao tcp
				 */
				serverResponse = "the_end\n";
				outToClient.writeBytes(serverResponse);
				System.out.println("Fechando o socket...");
				socketClient.close();
				break;
				
			case "sendChatMsg":
				String login = brokenTcpMsg[1];
				String dateString = brokenTcpMsg[2];
				String msg = brokenTcpMsg[3];
				Date date = formatter.parse(dateString);
				
				
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
			System.out.println("Conexao: EOFException " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("Conexao: IOException " + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				System.out.println("Fechando o socket...");
				outToClient.close();
				inFromClient.close();
				socketClient.close();
			}
			catch (IOException e) {
				System.out.println("Conexao: erro close do socket");
			}
		}
	}

}


