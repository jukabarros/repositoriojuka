package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

import config.ReadProperties;


public class TCPConnected extends Thread{

	private BufferedReader inFromClient; 
	private DataOutputStream outToClient; 
	private String clientCommand;
	private String serverResponse;
	private Socket socketClient;
	private TCPController tcpController;
	
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	
	public TCPConnected(Socket socket, TCPController controller) throws IOException {
		try {
			this.properties = readProperties.getProp();
			socketClient = socket;
			tcpController = controller;
			outToClient = new DataOutputStream(socketClient.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			System.out.println("Criando nova Thread de Conexao...");
			this.start();
		}
		catch (IOException e) {
			System.out.println("*** Erro na Criaçao da Thread: " + e.getMessage());
		}
	}
	
	public void run() {
		boolean statusConnection = true;
		while(statusConnection == true){
			try {
				clientCommand = inFromClient.readLine();
				System.out.println("Comando do Cliente: "+clientCommand);

				/*
				 * Tratamento dos Comandos recebidos do Cliente
				 * Inserir o separador em um properties
				 */
				String[] brokenTcpMsg = clientCommand.split(properties.getProperty("tcp.msg.split"));
				String tcpCommandClient = brokenTcpMsg[1];

				/*
				 * Comando do Cliente
				 */
				switch (tcpCommandClient) {
				case "logout":
					System.out.println("Encerrando a conexão com o cliente: "+brokenTcpMsg[2]);
					serverResponse = "the_end"+properties.getProperty("tcp.msg.end");
					outToClient.writeBytes(serverResponse);
					outToClient.close();
					inFromClient.close();
					socketClient.close();
					statusConnection = false;
					/*
					 * Remover da lista de conexoes
					 */
					tcpController.removeTcpConnection(Thread.currentThread());
					break;

				case "sendChatMsg":
					tcpController.sendChatMsgToAll(clientCommand);
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
	public BufferedReader getInFromClient() {
		return inFromClient;
	}

	public void setInFromClient(BufferedReader inFromClient) {
		this.inFromClient = inFromClient;
	}

	public DataOutputStream getOutToClient() {
		return outToClient;
	}

	public void setOutToClient(DataOutputStream outToClient) {
		this.outToClient = outToClient;
	}

	public String getServerResponse() {
		return serverResponse;
	}

	public void setServerResponse(String serverResponse) {
		this.serverResponse = serverResponse;
	}

}


