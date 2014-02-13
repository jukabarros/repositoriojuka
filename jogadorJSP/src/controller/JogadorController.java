package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.JogadorService;

import entidade.Jogador;

public class JogadorController {
	
	private List<Jogador> listaJogador;
	private JogadorService service;
	private Jogador jogador;
	
	public JogadorController() {
		listaJogador = new ArrayList<Jogador>();
		service = new JogadorService();
		jogador = new Jogador();
	}
	
	
	public void listarTodosController() throws SQLException{
		listaJogador = service.listarTodosService();
	}
	
	public void consultarNomeController(String nome) throws SQLException{
		listaJogador = service.consultarNomeService(nome);
	}
	
	public void consultarIdController(int id) throws SQLException{
		listaJogador = service.consultarIdService(id);
	}
	
	public void inserirController(Jogador j) throws SQLException{
		service.inserirService(getJogador());
	}
	
	public void editarController(Jogador j) throws SQLException{
		service.editarService(getJogador());
	}
	
	public void excluirController(int id) throws SQLException{
		service.excluirService(id);
	}
	
	
	
	/*
	 * GET AND SET
	 */
	
	public List<Jogador> getListaJogador() {
		return listaJogador;
	}


	public void setListaJogador(List<Jogador> listaJogador) {
		this.listaJogador = listaJogador;
	}


	public Jogador getJogador() {
		return jogador;
	}


	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	
	

}
