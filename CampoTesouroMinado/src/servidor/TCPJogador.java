package servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

import config.ReadProperties;

public class TCPJogador {
	
	ReadProperties prop; 
	Socket clientSocket;
	Properties propTest;
	DataOutputStream outToServer; 
	BufferedReader inFromServer; 
	String respostaServidor;
	
	public TCPJogador(){
		prop = new ReadProperties();
		try{
			propTest = prop.getProp(); // Capturando o valor inserido no arquivo configJogo.properties
			System.out.println("* IP DO SERVIDOR: " +propTest.getProperty("prop.ip.servidor"));
		} catch (Exception e){
			System.out.println("Erro do prop");
		}
	}
	
	public String enviarComandoServidor(String comandoTCP){ // Responsavel por pegar o comando que vem da tela
		try {
			outToServer.writeBytes(comandoTCP + '\n');
			respostaServidor = inFromServer.readLine();
			System.out.println("Resposta do Servidor: " +respostaServidor);
			if(respostaServidor.equalsIgnoreCase("fim_jogo")){
				desconectar();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respostaServidor;
	}
	
	
	public void conectar() {
		try{
			clientSocket = new Socket(propTest.getProperty("prop.ip.servidor"), 6789);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 
		}catch(Exception e ){
			System.out.println("Erro ao conectar com o servidor: "+e.getMessage());
		}
		}

	public void desconectar(){
		try {
			System.out.println("\nFim da conexão com o servidor \n");
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
