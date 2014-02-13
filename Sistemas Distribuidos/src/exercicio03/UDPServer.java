package exercicio03;

import java.net.*;
import java.io.*;

public class UDPServer {
	
    public static void main(String args[]) {
        
    	DatagramSocket datagramSocket = null;
        
    	try {
            datagramSocket = new DatagramSocket(6789); // cria um socket UDP
            byte[] buffer = new byte[1000];
            while (true) {
                System.out.println("\n\n*** Servidor aguardando request");
                // cria datagrama para recepcionar solicitacao do cliente
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(request);
                System.out.println("*** Request recebido de: " + request.getAddress()+":"+request.getPort() +
                        " tam: " + request.getLength());
                
                // prepara resposta
                byte[] buffer2 = new byte[request.getLength()];
                
                for (int i=request.getLength()-1, j=0;i>-1;i--,j++)
                    buffer2[j]=buffer[i];
                
                DatagramPacket response = new DatagramPacket(buffer2, buffer2.length,
                        request.getAddress(), request.getPort());
                datagramSocket.send(response);
            }
            
        } 
    	catch (SocketException e) {
            System.out.println("Erro de socket: " + e.getMessage());
        } 
    	catch (IOException e) {
            System.out.println("Erro envio/recepcao pacote: " + e.getMessage());
        } 
    	finally {
            if (datagramSocket != null) 
            	datagramSocket.close();
        }
    }
}
