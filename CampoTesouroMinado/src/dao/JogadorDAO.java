package dao;

import java.util.ArrayList;

import entidade.Jogador;

public class JogadorDAO {
	
	private ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
	
	/*
	 * Inserindo os jogadores no jogo
	 * executar apenas 1 vez.
	 */
	public void inserirJogador(){
		System.out.println("Inserindo os jogadores...");
		Jogador jogador1 = new Jogador("grupo1", "1", 0);
		Jogador jogador2 = new Jogador("grupo2", "2", 0);
		Jogador jogador3 = new Jogador("grupo3", "3", 0);
		Jogador jogador4 = new Jogador("grupo4", "4", 0);
		Jogador jogador5 = new Jogador("grupo5", "5", 0);
		
		listaJogadores.add(jogador1);
		listaJogadores.add(jogador2);
		listaJogadores.add(jogador3);
		listaJogadores.add(jogador4);
		listaJogadores.add(jogador5);
		
		System.out.println("Jogadores criados com sucesso!");
	}
	/*
	 * Metodo usado na Autenticacao do Login!
	 */
	public boolean consultarJogador(Jogador j){
		for (int i = 0; i < listaJogadores.size(); i++) {
			if (j.getLogin().equals(listaJogadores.get(i).getLogin()) && j.getSenha().equals(listaJogadores.get(i).getSenha())){
				return true;
			}
			
		}
		return false;
	}
	
	public void removerJogador(Jogador j){
		listaJogadores.remove(j);
	}

	public ArrayList<Jogador> getListaJogadores() {
		return listaJogadores;
	}

	public void setListaJogadores(ArrayList<Jogador> listaJogadores) {
		this.listaJogadores = listaJogadores;
	}
	
	

}
