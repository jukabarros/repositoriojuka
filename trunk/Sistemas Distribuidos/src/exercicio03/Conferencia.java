package exercicio03;

/*******************************************************************************
 * Cria um chat em conferencia onde varias pessoas unem-se ao grupo para trocar* 
 * mensagens.       														   *
 * 																			   *
 * Parametros:																   *
 * args[0] endereco de multicast  Ex: 224.225.226.227						   *
 * args[1] porta                  Ex: 6868									   *
 * args[2] login da pessoa 		  Ex: victor								   *
 ******************************************************************************/

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Conferencia {

	private int portaGrupo = 6789;
    private InetAddress ipGrupo = null;
    private String login = null;
    private MulticastSocket multicastSocket = null;
    
    /*
     * construtor
     */
    public Conferencia(String endereco, String porta, String login) {
        // armazena o numero da porta do grupo localmente
    	try {
            this.portaGrupo = Integer.parseInt(porta);
        }
        catch (NumberFormatException e) {
            System.out.println("ERRO: numero de porta invalido. assumindo porta padrao: " + this.portaGrupo);
        }
        
        // junta-se a um grupo de multicast e envia saudacoes
        this.login = new String(login);
        
        try {
            ipGrupo = InetAddress.getByName(endereco);
            multicastSocket = new MulticastSocket(this.portaGrupo);
            multicastSocket.joinGroup(ipGrupo);
        }
        catch (SocketException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
    public static void main(String args[]) {
        
        if (args.length < 3) {
            System.out.println("ERRO: fornecer argumentos: <IP grupo multicast> <porta> <login>");
            System.exit(1);
        }
        System.out.println("parametros: " + args[0] + " " + args[1] + " " + args[2]);
        Conferencia c = new Conferencia(args[0], args[1], args[2]);
        Thread envia = new Thread(new EnviaThread(c));
        Thread recebe = new Thread(new RecebeThread(c));
        envia.start();
        recebe.start();
        try {
            envia.join();
            recebe.join();
        } 
        catch (InterruptedException e) {
            System.out.println("ERRO: problema ao se unir ao grupo: " + e.getMessage());
        } 
        finally {
            c.deixarGrupo();
        }
    }
    
    private void deixarGrupo() {
        try {
            multicastSocket.leaveGroup(ipGrupo);
            if (multicastSocket != null)
            	multicastSocket.close();
        } 
        catch (IOException e) {
            System.out.println("ERRO: problema ao deixar grupo: " + e.getMessage());
        }
    }
    
    public int getPortaGrupo() {
		return portaGrupo;
	}

	public InetAddress getIpGrupo() {
		return ipGrupo;
	}

	public String getLogin() {
		return login;
	}

	public MulticastSocket getMulticastSocket() {
		return multicastSocket;
	}
}