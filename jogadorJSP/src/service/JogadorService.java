package service;

import java.sql.SQLException;
import java.util.List;

import dao.JogadorDAO;
import entidade.Jogador;

public class JogadorService {
	
	private JogadorDAO dao;

	public JogadorService() {
		dao = new JogadorDAO();
	}
	
	public List<Jogador> listarTodosService() throws SQLException{
		return dao.listarTodos();
	}
	
	public List<Jogador> consultarNomeService(String nome) throws SQLException{
		return dao.consultarNome(nome);
	}
	
	public List<Jogador> consultarIdService(int id) throws SQLException{
		return dao.consultarId(id);
	}
	
	public void inserirService(Jogador j) throws SQLException{
		dao.inserir(j);
	}
	
	public void editarService(Jogador j) throws SQLException{
		dao.editar(j);
	}
	
	public void excluirService(int id) throws SQLException{
		dao.excluir(id);
	}

}
