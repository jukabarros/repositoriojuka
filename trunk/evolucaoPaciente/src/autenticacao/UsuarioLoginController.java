package autenticacao;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import usuario.Usuario;
import usuario.UsuarioService;
import util.Stringmd5;

@ManagedBean(name="loginController")
@SessionScoped
public class UsuarioLoginController implements Serializable {

	private static final long serialVersionUID = -3828647560457801428L;
	
	private Usuario usuario;
	private List<Usuario> usuarioList;
	private UsuarioService usuarioService = UsuarioService.getInstance();
	private String cpf;
	private String senha;
	private Stringmd5 setMd5;
	
	public UsuarioLoginController() {
		setMd5 = new Stringmd5();
		usuario = new Usuario();
		usuarioList  = new ArrayList<Usuario>();
	}
	
	public String autenticar(){
		try{
			senha = setMd5.md5Converter(senha);
			usuarioList = usuarioService.autenticar(getCpf(), getSenha());
			if(!usuarioList.isEmpty()){
				usuario = usuarioList.get(0);
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("usuario", usuario);
				System.out.println("** Usuário: "+usuario.getNome()+" autenticado!");
				this.senha = "";
				if(usuario.getTipo().equals("Equipe Medica")){
					return "evolucao/listaEvolucao?faces-redirect=true";
				}else{
					return "usuario/listaUsuario?faces-redirect=true";
				}
				
			}else{
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na autenticação!", "")); //Mensagem de Erro
				usuario = null;
				return "index";
			}
			
		}catch(Exception e){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro fatal: "+e.getMessage(), "")); //Mensagem de Erro
			return "";
		}
	}
	
	public String logout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
		session.invalidate();
		System.out.println("** Usuario: "+usuario.getNome()+" logout!");
		return "../index.jsf?faces-redirect=true";
	}
	
	public String logoutEquipeMedica(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
		session.invalidate();
		System.out.println("** Usuario: "+usuario.getNome()+" logout!");
		return "index.jsf?faces-redirect=true";
	}
	
	public void verificarAutorizacao(ComponentSystemEvent e){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("usuario", usuario);
		if (usuario.getTipo().equals("Equipe Medica")) {
			try {
				System.out.println("*** Usuário: "+usuario.getNome()+" não autorizado para acessar a pagina!");
				FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsf");
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: usuário não pode acessar essa página!", ""));				usuario = null;
				session.invalidate();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	/*
	 * GET AND SET
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}
	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
