package main;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import util.BeanFactory;

@Path("/")
public class Receiver {
	
	private UsuarioDAO usuarioDAO = (UsuarioDAO) BeanFactory.getBean("usuarioDAO", UsuarioDAO.class);
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	@POST
	@Path("/gravar")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario gravar(Usuario usuario) throws Exception {
		
		System.out.println("Gravando registro...");
		usuarioDAO.gravar(usuario);
		Usuario u = new Usuario();
		return u;
	}
	
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarTodos(){		
		System.out.println("Listando os Usuarios...");
		listaUsuario = usuarioDAO.buscarTodos();		
	
		return listaUsuario;
		
	}
	
	@POST
	@Path("/consultarID")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> consultarID(String idUsuario){
		
		System.out.println("Listando por ID Usuario...");
		listaUsuario = usuarioDAO.buscarPorId(Long.valueOf(idUsuario));
				
		return listaUsuario;
	}
	
	@POST
	@Path("/excluir")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario excluir(Usuario usuario){
		
		System.out.println("Excluindo usuario...");
		usuarioDAO.excluir(usuario);
		Usuario u = new Usuario();
				
		return u;
	}
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}	
	
	
	
}
