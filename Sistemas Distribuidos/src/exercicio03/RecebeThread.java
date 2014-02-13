package exercicio03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

class RecebeThread implements Runnable {
    
	private Conferencia conferencia;
   
    RecebeThread(Conferencia conferencia) {
        this.conferencia = conferencia;
    }
    
    public void run() {
        // recebe mensagens
        while (true) {
            byte[] buf = new byte[256];
            DatagramPacket recebido = new DatagramPacket(buf, buf.length);
            try {
            	 // se nao receber nada em 60s, encerra o programa
                conferencia.getMulticastSocket().setSoTimeout(60000);
            } 
            catch (SocketException e) {
                System.out.println("ERRO: nao foi possivel estabelecer tempo de" +
                		"timeout!" + e.getMessage());
            }
            
            try {
                conferencia.getMulticastSocket().receive(recebido);
            }
            catch (SocketTimeoutException e) {
                System.out.println("\nINATIVIDADE: encerrado por timeout");
                break;
            }
            catch (IOException e) {
                System.out.println("ERRO: problema no socket" + e.getMessage());
            }
            
            String str = new String(recebido.getData());
            System.out.println("(" + recebido.getAddress().getHostAddress() +
                    ":" + recebido.getPort() + ") << " + str.trim());
        }
    }
}