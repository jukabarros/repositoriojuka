package exercicio03;

import java.net.*;
import java.io.*;

public class UDPClient {
    
	public static void main(String args[]) {
        
		DatagramSocket datagramSocket = null;
        
		try {
            String servidor = "172.16.205.92";
            int porta = 6789;
            String msg = "agnaj od uos ue";
            
            if (args.length > 0) servidor = args[0];
            if (args.length > 1) porta = Integer.parseInt(args[1]);
            if (args.length > 2) msg = args[2];
            // transforma a mensagem do parametro em um array de bytes
            byte[] m = msg.getBytes();
            InetAddress serverName = InetAddress.getByName(servidor);
   
            datagramSocket = new DatagramSocket(); // cria um socket UDP
            System.out.println("* Socket criado na porta: " + datagramSocket.getLocalPort());
            DatagramPacket request = new DatagramPacket(m, msg.length(), serverName, porta);
            datagramSocket.send(request); // envia datagrama contendo a mensagem m
            System.out.println("* Datagrama enviado...: " + msg);
            
            byte[] buffer = new byte[1000];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            datagramSocket.setSoTimeout(10000); // timeout em ms
            datagramSocket.receive(response); // aguarda resposta do servidor - bloqueante
            System.out.println("* Resposta do servidor: " + 
                    new String(response.getData(),0,response.getLength()));
            
        }
		catch (SocketException e) {
            // timeout, erro na criacao
            System.out.println("! Erro socket: " + e.getMessage());
        }
		catch (IOException e) {
            System.out.println("! Erro envio/recepcao do pacote: " + e.getMessage());
        }  
		finally {
            if (datagramSocket != null) 
            	datagramSocket.close();
        }     
    }
    
}
