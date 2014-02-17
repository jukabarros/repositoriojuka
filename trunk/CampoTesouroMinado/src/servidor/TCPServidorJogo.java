package servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

import dao.JogadorDAO;
import dao.PartidaDAO;
import entidade.Jogador;
import entidade.Partida;

public class TCPServidorJogo {
	private JogadorDAO daoJogador;
	static String[][] jogo;
	static TCPServidorJogo servidor;
	static Jogador jogador;
	
	private ArrayList<Jogador> listaJogadoresOnline = new ArrayList<Jogador>(); // Inserir os jogadores validados nessa lista
	
	
	public TCPServidorJogo(){
		daoJogador = new JogadorDAO();
		// Inserir os jogadores na primeira vez que executar o programa
		if(daoJogador.getListaJogadores().isEmpty()==true){
			daoJogador.inserirJogador();
		}
	}
	
	public static void main(String argv[]) throws Exception {
		String comandoCliente;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		servidor = new TCPServidorJogo();
		while(true) {
				
	          Socket connectionSocket = welcomeSocket.accept();
	          BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	          DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	          comandoCliente = inFromClient.readLine();
	          System.out.println("**** SERVIDOR ****");
	          System.out.println("Comando do Cliente: " + comandoCliente);
	          /*
	           * Tratamento dos Comandos recebidos do Cliente
	           */
	          String[] dividirString = comandoCliente.split(" ");
	          String comando = dividirString[0]; // String de Comando do cliente
	          
	          // Autenticacao de Login	           
	          if (comando.equals("login")){	        	  
	        	  Jogador loginJogador = new Jogador();
	        	  loginJogador = servidor.validarJogador(dividirString[1], dividirString[2]); //Passando o login e senha para o metodo
	        	  if (loginJogador==null){
	        		  capitalizedSentence = "Erro: Login e/ou senha incorreto(s) ou usuario ja esta online. \n";
	  	      		  outToClient.writeBytes(capitalizedSentence);
	        		  
	        	  } else{
	        		  // Criar pessoa
	        		  jogador = loginJogador; // Jogador da partida
	        		  capitalizedSentence = "loginOK "+jogador.getLogin()+" "+jogador.getSenha()+"\n"; // Envia esse comando para desabilitar os campos
	        		  if (servidor.listaJogadoresOnline.size()==1){
	        			  servidor.setJogo(servidor.gerarJogo()); // criando a matriz
	        			 
	        		  }
	  	      		  outToClient.writeBytes(capitalizedSentence);
	        	  }
	          
	          } 
	          
	          // Comando Fim de Jogo
	          else if (comando.equals("fim_jogo")){
	        	  capitalizedSentence = "fim_jogo\n";
	        	  servidor.gerarRanking();
	        	//  servidor.listaJogadoresOnline.clear(); // Limpando a lista de jogadores online
  	      		  outToClient.writeBytes(capitalizedSentence);
	          }
	          
	          // Comando Visualizar o Campo
	          else if (comando.equals("ver")){
	        	  int linha = Integer.parseInt(dividirString[1]);
	        	  int coluna = Integer.parseInt(dividirString[2]);
	        	  String valorCampo = servidor.jogada(linha, coluna); // recebendo o valor do campo
	        	  
	        	  capitalizedSentence = "valor " +valorCampo+"\n";
  	      		  outToClient.writeBytes(capitalizedSentence);
	        	  // Criar Comando de Atualizar Campo
  	      		  // Atualizar Pontuacao
	          }
	          
	          else if (comando.equals("atualizar_pontos")){
	        	  int pontuacao = Integer.parseInt(dividirString[2]);
	        	  servidor.atualizarPontos(dividirString[1], pontuacao);
	        	  capitalizedSentence = "pontuacao atualizada\n";
  	      		  outToClient.writeBytes(capitalizedSentence);
  	      		  
	          }
		} // Fechando o WHILE
		
	} // Fechando o MAIN
	
	
	// Criar Metodo de Vez do jogador
	// o comando "ver linha coluna" tem que ser: "jogador ver linha coluna"
	
	public Jogador validarJogador(String login, String senha){
		System.out.println("\n Verificando Login e Senha... \n");
		try{
			Jogador jogador = new Jogador(login, senha, 0);
      	  	boolean autenticacao = daoJogador.consultarJogador(jogador);
      	  	daoJogador.getListaJogadores().get(0);
	      	if (autenticacao==false){
	      		return null;
	      	}else{
	      		// Verificando se o jogador ja esta online
	      		if (listaJogadoresOnline.contains(jogador)==true){
	      			return null;
	      		}else{
	      			// Caso nao esteja online inserir ele na lista
	      			listaJogadoresOnline.add(jogador);
	      			listarJogadoresOnline(); // Listando os jogadores Online
	      		}
	      		return jogador;
	      	}
	  	  }catch(Exception e){
	  		  System.out.println("Erro no login: "+e.getMessage());  
	  		  return null;
	  	  }
	    } // Fechando o Metodo validarJogador
			
	public void listarJogadoresOnline(){
		System.out.println("JOGADORES ONLINE");
		for (int i = 0; i < listaJogadoresOnline.size(); i++) {
			System.out.println(listaJogadoresOnline.get(i).getLogin());
		}
	}
	
	public String[][] gerarJogo(){
		
		int quantidade_bomba = 30;
		int quantidade_nada = 30;
		int quantidade_ouro = 39;
		
		int linha;
		int coluna;
		System.out.println("\nCriando a partida...");
		Random gerador = new Random();
		String[][] matriz = new String[10][10];
		System.out.println("Entrou bomba");
		int conta_bomba = 0;
		while(conta_bomba != quantidade_bomba){
			linha = gerador.nextInt(10);
			coluna = gerador.nextInt(10);
			if(matriz[linha][coluna] == null){
				matriz[linha][coluna]="bomba";
				conta_bomba++;								
			}
		}
		
		System.out.println("Entrou nada");
		int conta_nada = 0;
		while(conta_nada != quantidade_nada){
			linha = gerador.nextInt(10);
			coluna = gerador.nextInt(10);
			if(matriz[linha][coluna] == null){
				matriz[linha][coluna]="nada";
				conta_nada++;								
			}
		}
		
		System.out.println("Entrou ouro");
		int conta_ouro = 0;
		while(conta_ouro != quantidade_ouro){
			linha = gerador.nextInt(10);
			coluna = gerador.nextInt(10);
			if(matriz[linha][coluna] == null){
				matriz[linha][coluna]="ouro";
				conta_ouro++;								
			}
		}
		System.out.println("Entrou bau");
		for (int i=0; i<10; i++){
			for (int j=0; j<10; j++){
				if(matriz[i][j] == null){
					matriz[i][j] = "bau";
					break;
				}
			}				
		}
		
		System.out.println("Partida criada com sucesso!\n");
		return matriz;
	}
	
	
	public String jogada(int linha, int coluna){
		String resposta = this.jogo[linha][coluna];
		int tamanho_string = resposta.length();  
		String ultimo_caracter = resposta.substring(tamanho_string-1, tamanho_string);
		if(!ultimo_caracter.equals("X")){
			this.jogo[linha][coluna] = this.jogo[linha][coluna]+"X";
		}
		return resposta;
			
	}
	
	public void atualizarPontos(String login, int pontuacao){
		System.out.println("\n\n** Ranking parcial");
		for (int i = 0; i < listaJogadoresOnline.size(); i++) {
			if (login.equals(listaJogadoresOnline.get(i).getLogin())){
				listaJogadoresOnline.get(i).setPontuacao(pontuacao);
			}
			System.out.println(listaJogadoresOnline.get(i).getLogin()+" "+listaJogadoresOnline.get(i).getPontuacao());
		}
		
	}
	
	public void gerarRanking(){
		System.out.println("\n\n*** RANKING FINAL***");
		for (int i = 0; i < listaJogadoresOnline.size(); i++) {
			
			System.out.println(listaJogadoresOnline.get(i).getLogin()+" "+listaJogadoresOnline.get(i).getPontuacao());
		}
		System.out.println("*********************");
	}
	
	public static String[][] getJogo() {
		return jogo;
	}

	public static void setJogo(String[][] jogo) {
		TCPServidorJogo.jogo = jogo;
	}
	
} // Classe
