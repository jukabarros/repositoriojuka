package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import config.Conexao;

import entidade.Jogador;

public class JogadorDAO {

	private ArrayList<Jogador> listaJogador;
	private Jogador jogador;
	private String query;
	
	public JogadorDAO() {
		listaJogador = new ArrayList<Jogador>();
		jogador = new Jogador();
	}
	
	public List<Jogador> listarTodos() throws SQLException{
		Connection conectar = new Conexao().conectar();
		query = "SELECT * FROM jogador";
		PreparedStatement queryExec = conectar.prepareStatement(query);
		ResultSet result = queryExec.executeQuery();
		while (result.next()){
			jogador = new Jogador();
			jogador.setId(result.getInt(1));
			jogador.setNome(result.getString(2));
			jogador.setPosicao(result.getString(3));
			jogador.setIdade(result.getInt(4));
			jogador.setTime(result.getString(5));
			listaJogador.add(jogador);
			
		}
		conectar.close();
		return listaJogador;
		
	}
	
	public List<Jogador> consultarNome(String nome) throws SQLException{
		Connection conectar = new Conexao().conectar();
		query = "SELECT * FROM jogador WHERE nome = ?";
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, nome);
		ResultSet result = queryExec.executeQuery();
		while (result.next()){
			jogador = new Jogador();
			jogador.setId(result.getInt(1));
			jogador.setNome(result.getString(2));
			jogador.setPosicao(result.getString(3));
			jogador.setIdade(result.getInt(4));
			jogador.setTime(result.getString(5));
			listaJogador.add(jogador);
			
		}
		conectar.close();
		return listaJogador;
		
	}
	
	public List<Jogador> consultarId(int id) throws SQLException{
		Connection conectar = new Conexao().conectar();
		query = "SELECT * FROM jogador WHERE id = ?";
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setInt(1, id);
		ResultSet result = queryExec.executeQuery();
		while (result.next()){
			jogador = new Jogador();
			jogador.setId(result.getInt(1));
			jogador.setNome(result.getString(2));
			jogador.setPosicao(result.getString(3));
			jogador.setIdade(result.getInt(4));
			jogador.setTime(result.getString(5));
			listaJogador.add(jogador);
			
		}
		conectar.close();
		return listaJogador;
		
	}
	
	public void inserir(Jogador j) throws SQLException{
		Connection conectar = new Conexao().conectar();
		query = "INSERT INTO jogador (nome,posicao,idade,time) VALUES (?,?,?,?)";
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, j.getNome());
		queryExec.setString(2, j.getPosicao());
		queryExec.setInt(3, j.getIdade());
		queryExec.setString(4, j.getTime());
		
		queryExec.execute();
		queryExec.close();
		System.out.println("Jogador inserido com sucesso!!");
	}
	
	public void editar(Jogador j) throws SQLException{
		Connection conectar = new Conexao().conectar();
		query = "UPDATE jogador SET nome = ?, posicao = ?, idade = ?, time = ? WHERE id = ?";
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, j.getNome());
		queryExec.setString(2, j.getPosicao());
		queryExec.setInt(3, j.getIdade());
		queryExec.setString(4, j.getTime());
		queryExec.setInt(5, j.getId());
		
		queryExec.execute();
		queryExec.close();
		System.out.println("Jogador editado com sucesso!!");
	}
	
	public void excluir(int id) throws SQLException{
		Connection conectar = new Conexao().conectar();
		query = "DELETE FROM jogador WHERE id = ?";
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setInt(1, id);
		queryExec.execute();
		queryExec.close();
		System.out.println("Jogador Excluido com sucesso!!");
	}
	
	
	
	/*
	 * GET AND SET
	 */
	public ArrayList<Jogador> getListaJogador() {
		return listaJogador;
	}
	public void setListaJogador(ArrayList<Jogador> listaJogador) {
		this.listaJogador = listaJogador;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	
}
