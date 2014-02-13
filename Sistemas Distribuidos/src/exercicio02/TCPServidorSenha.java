package exercicio02;
import java.io.*;
import java.net.*;

public class TCPServidorSenha {

	public static void main(String argv[]) throws Exception {
		String sentence;
		String modifiedSentence;
		System.out.println("COMANDO 1:");
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		Socket clientSocket = new Socket("172.16.206.71", 7050);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		sentence = inFromUser.readLine();
		int i = 0;
		int a = 0;
		while (i == 0) {			
			//System.out.println("SENTECE: "+sentence);
			outToServer.writeBytes(sentence + '\n');
			modifiedSentence = inFromServer.readLine();
			System.out.println("FROM SERVER: " + modifiedSentence);
			System.out.println("a: "+a++);
			//clientSocket.close();
			if (!modifiedSentence.equals("usuario nao autorizado a usar este comando!")){
				i = 1;
				}
		} // while
		
		System.out.println("COMANDO 2:");
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
		
	}
	}

