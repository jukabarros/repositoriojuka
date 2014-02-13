package exercicio03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;

class EnviaThread implements Runnable {
    
	private Conferencia conferencia;
    
    EnviaThread(Conferencia conferencia) {
        this.conferencia = conferencia;
    }
    
    private String obterMensagem() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        try {
            s = new String(reader.readLine());
        } 
        catch (IOException e) {
            System.out.println(conferencia.getLogin() + ": estou com problemas :-/");
        }
        if (s.length() > 0)
            return(conferencia.getLogin() + ": " + s);
        else
            return s;
    }
    
    public void run() {
        // envia saudacao
        String msg = new String(conferencia.getLogin() + ": ola a todos, cheguei!!!");
        DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.length(), 
        		conferencia.getIpGrupo(), conferencia.getPortaGrupo());
        try {
            conferencia.getMulticastSocket().send(datagramPacket);
        } 
        catch (IOException e) {
            System.out.println("ERRO: problema ao enviar mensagem: " + e.getMessage());
        }
        // envia mensagem lida do teclado
        msg = new String(obterMensagem());
        while (msg.length() > 0) {
            datagramPacket = new DatagramPacket(msg.getBytes(), msg.length(), 
            		conferencia.getIpGrupo(), conferencia.getPortaGrupo());
            try {
                conferencia.getMulticastSocket().send(datagramPacket);
            } 
            catch (IOException e) {
                System.out.println("ERRO: problema ao enviar mensagem: " + e.getMessage());
            }
            msg = obterMensagem();
        }
    }
}