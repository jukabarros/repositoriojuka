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
			this.socketClient = socket;
			this.tcpController = controller;
			this.outToClient = new DataOutputStream(this.socketClient.getOutputStream());
			this.inFromClient = new BufferedReader(new InputStreamReader(this.socketClient.getInputStream()));
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
				this.clientCommand = this.inFromClient.readLine();
				System.out.println("Comando do Cliente: "+this.clientCommand);

				/*
				 * Tratamento dos Comandos recebidos do Cliente
				 * Inserir o separador em um properties
				 */
				String[] brokenTcpMsg = this.clientCommand.split(this.properties.getProperty("tcp.msg.split"));
				String tcpCommandClient = brokenTcpMsg[1];

				/*
				 * Comando do Cliente
				 */
				switch (tcpCommandClient) {
				
				case "welcomePlayer":
					this.tcpController.sendMsgBroadCast(this.clientCommand);
					break;
					
				case "logout":
					System.out.println("Encerrando a conexão com o cliente: "+brokenTcpMsg[2]);

					this.tcpController.sendMsgBroadCast(this.clientCommand);
					statusConnection = false;
					
					/*
					 * Remover da lista de conexoes
					 */
					
					this.tcpController.removeTcpConnection(Thread.currentThread());
					Thread.sleep(4000); // Aguarda um momento para fechar a conexao
					this.outToClient.close();
					this.inFromClient.close();
					this.socketClient.close();

					break;

				case "sendChatMsg":
					this.tcpController.sendMsgBroadCast(this.clientCommand);
					break;
				
				/*
				 * Long polling
				 * aguardar um tempo (inicio - 5 segundos) para ver se tem alguma coisa
				 * pro cliente especifico, caso nao tenha nada, descartar
				 * a msg
				 * Implementar isso no controller???Sim
				 */
				case "getServerMsg":
					// Criar uma lista de mensagens por clientes??
					System.out.println("Mensagem para: "+brokenTcpMsg[2]);
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
			} catch (InterruptedException e) {
				System.out.println("*** Erro na Thread: " + e.getMessage());
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


