package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import main.Usuario;

@ManagedBean(name="usuarioController")
@ViewScoped
public class UsuarioController implements Serializable{
	
	private static final long serialVersionUID = 1022845665467843595L;
	private Usuario usuario;
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private String campoPesquisa;
		
	public UsuarioController() {
		usuario = new Usuario();
		listarTodos();
		
	}

	public List<Usuario> listarTodos(){
		GenericType<List<Usuario>> generic = new GenericType<List<Usuario>>() {};
		String uri = "http://localhost:8080/UsuarioServer/rest";
        WebResource resource = Client.create().resource(uri);
        List<Usuario> result = resource
                        .path("/listarTodos")      
                        .accept(MediaType.APPLICATION_JSON)
                                               .get(generic);
       
        campoPesquisa = null;
        listaUsuario = result;
        return result;
	}
	
	public List<Usuario> consultarID(){
		
		 try{ 
			System.out.println("ID: "+campoPesquisa);
			Long.parseLong(campoPesquisa); 
						
				      
			GenericType<List<Usuario>> generic = new GenericType<List<Usuario>>() {};
			String uri = "http://localhost:8080/UsuarioServer/rest";
			WebResource resource = Client.create().resource(uri);
       
       
			List<Usuario> result = resource
                       .path("/consultarID")      
                       .accept(MediaType.APPLICATION_JSON)
                       .entity(campoPesquisa)
                       .post(generic);
			
			listaUsuario=result;
			
       	return result;	
		 }
		 catch (Exception e) {
		    listaUsuario = null;
			System.out.println("\n ID invalido\n"+e);					
			return null;
			}	
	   
	}
	
	public Usuario gravar(){
		
		System.out.println("ID USUARIO: "+usuario.getIdUsuario());
		
		GenericType<Usuario> generic = new GenericType<Usuario>() {};
		String uri = "http://localhost:8080/UsuarioServer/rest";
        WebResource resource = Client.create().resource(uri);
        
        Usuario result = resource
                        .path("/gravar")      
                        .accept(MediaType.APPLICATION_JSON)
                        .entity(getUsuario())
                        .post(generic);

       
        listaUsuario = listarTodos();
        return result;
		
	}
	
	public Usuario excluir(){
		System.out.println("ID USUARIO: "+usuario.getIdUsuario());
		
		GenericType<Usuario> generic = new GenericType<Usuario>() {};
		String uri = "http://localhost:8080/UsuarioServer/rest";
        WebResource resource = Client.create().resource(uri);
        
        Usuario result = resource
                        .path("/excluir")      
                        .accept(MediaType.APPLICATION_JSON)
                        .entity(getUsuario())
                        .post(generic);
        
        listaUsuario = listarTodos();
        return result;
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}
	
	
}
