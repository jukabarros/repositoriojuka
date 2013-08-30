package usuario;

import java.io.Serializable;
import java.util.List;

import util.BeanFactory;
import usuario.Usuario;
import usuario.UsuarioDAO;
import usuario.UsuarioService;

public class UsuarioService implements Serializable{

	private static final long serialVersionUID = -3058412941976141382L;
	
	private static UsuarioService usuarioService;
	private UsuarioService() {
		super();		
	}
	
	public static UsuarioService getInstance() {
		if(usuarioService == null){
			usuarioService = new UsuarioService();
		}
		return usuarioService;
	}

	private UsuarioDAO usuarioDAO = (UsuarioDAO) BeanFactory.getBean("usuarioDAO", UsuarioDAO.class);

	
	public List<Usuario> buscarTodos(){	// Eh usado na criacao do dataTable	
		return usuarioDAO.buscarTodos();
	}
	
	public List<Usuario> buscarPorNome(String nome){ // Criteria eh da clausula Like
		return usuarioDAO.buscarPorNome(nome);		
	}
	
	public List<Usuario> buscarPorTipo(String tipo){ // Criteria eh da clausula Like
		return usuarioDAO.buscarPorTipo(tipo);		
	}
	
	//Esse Metodo eh usado na Classe UsuarioConverter.java 
	public List<Usuario> buscarPorNomeConverter(String nome){ // Criteria eh da clausula Where
		return usuarioDAO.buscarPorNomeConverter(nome);		
	}
	
	public void gravar(Usuario usuario){
		usuarioDAO.gravar(usuario);
	}
	
	public void excluir(Usuario usuario){
		usuarioDAO.excluir(usuario);
	}

}
