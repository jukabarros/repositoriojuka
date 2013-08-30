package usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import usuario.Usuario;
import usuario.UsuarioService;
import util.Stringmd5;


@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioController implements Serializable{

	private static final long serialVersionUID = 4434214301868615299L;
	
	private Usuario usuario = new Usuario();
	private EquipeMedica equipeMedica = new EquipeMedica();
	private Admin admin = new Admin();
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	private List<EquipeMedica> listaEquipeMedica = new ArrayList<EquipeMedica>();	
	private List<Admin> listaAdmin = new ArrayList<Admin>();	
	private UsuarioService usuarioService = UsuarioService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	private EquipeMedicaService equipeMedicaService = EquipeMedicaService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	private AdminService adminService = AdminService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	private String selectPesquisa; //Variavel que recebe o valor do selectOneMenu, responsavel por direcionar o metodo de consulta
	private String campoPesquisa; //Variavel que recebe o valor do inputText, eh o parametro de pesquisa dos metodo de consulta
	private String password; // Recebe a senha do formulario
	private Stringmd5 setMd5; // Criptografar a senha
	private String equipeMedicaForm; // Responsavel por desabilitar os botoes da EM nos formularios
	private String adminForm;
	
	public UsuarioController() {
		equipeMedicaForm = "true";
		adminForm = "true";
		setMd5 = new Stringmd5();
		atualizarTela();
		} 

	/**
	 * Limpa os campos input e atualiza a lista de usuarios cadastrados
	 */
	
	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela Usuario\n");
		usuario = new Usuario();
		admin = new Admin();
		equipeMedica = new EquipeMedica();
		listaUsuario = usuarioService.buscarTodos();
		listaEquipeMedica = equipeMedicaService.buscarTodos();
		listaAdmin = adminService.buscarTodos();
	}
	
	/** Metodos de Consulta
	 * Consulta registro com a entrada do usuario
	 */
	
	public void buscarController() {
		System.out.println("\n*** Consultando Registros\nCampo de Consulta Pesquisa: "+selectPesquisa);
		if (selectPesquisa.equals(null) || selectPesquisa.equals("")){			
			atualizarTela(); 			
		}
		
		if (selectPesquisa.equals("nome")){
			
			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}
			else{
			System.out.println("\n*** Buscando por Nome\n");
			String nome = campoPesquisa; // Inserindo o valor que vai passar como parametro para os metodos
			listaUsuario = usuarioService.buscarPorNome(nome);	
			}
		} //FECHANDO O IF SELECTPESQUISA(NOME)
		
		if (selectPesquisa.equals("cpf")){

			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}
			else{
				System.out.println("\n*** Buscando por Cpf\n");
				String cpf = campoPesquisa; // Inserindo o valor que vai passar como parametro para os metodos
				listaUsuario = usuarioService.buscarPorCpf(cpf);	
			}
		} //FECHANDO O IF SELECTPESQUISA(CPF)

		if (selectPesquisa.equals("tipo")){

			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}
			else{
				System.out.println("\n*** Buscando por Tipo\n");
				String tipo = campoPesquisa; // Inserindo o valor que vai passar como parametro para os metodos
				listaUsuario = usuarioService.buscarPorTipo(tipo);	
			}
		} //FECHANDO O IF SELECTPESQUISA(CPF)


	
	} // FECHANDO O METODO BUSCARCONTROLLER
	
	/**
	 * Grava novo registro ou atualiza um registro
	 */
	
	public String gravar(){
		System.out.println("\n*** Gravando Registro\n");
		password = setMd5.md5Converter(password);
		usuario.setSenha(password); // Criptografando a senha
		try{
			// Gravando EquipeMedica
			if(usuario.getTipo().equals("Equipe Medica")){
				usuarioService.gravar(getUsuario());
				equipeMedica.setUsuario(getUsuario());
				equipeMedicaService.gravar(getEquipeMedica());
				atualizarTela();
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao 
				// Gravando Admin
			}else{
				usuarioService.gravar(getUsuario());
				admin.setUsuario(getUsuario());
				adminService.gravar(getAdmin());
				atualizarTela();
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao 
				
			}
			
		}catch(Exception e){
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar o usuário: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
		return null;
	}
		
	/**
	 * Exclui um registro da tabela usuario
	 */
	
	public void excluir(){
		System.out.println("\n*** Excluindo Registro\n");
		try{
			usuarioService.excluir(getUsuario());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Registro Deletado com Sucesso!!")); //Mensagem de validacao 
			
		}catch(Exception e){
			System.err.println("** Erro ao deletar: "+e.getMessage());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar o usuário: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
	}
	
	/*
	 * Responsavel por desabilitar os campos dos forms dependendo do tipo de usuario
	 */
	public void habilitarCampos(){
		if(usuario.getTipo().equals("Equipe Medica")){
			equipeMedicaForm="false"; //habilitando o campo para a EM
			adminForm="true"; // desabilitando
		}else{
			adminForm="false"; //habilitando o campo para o ADMIN
			equipeMedicaForm="true"; //desabilitando
		}
	
	}
	
	
	/**
	 * Get and Set das Variaveis
	 */
	
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

	public String getSelectPesquisa() {
		return selectPesquisa;
	}

	public void setSelectPesquisa(String selectPesquisa) {
		this.selectPesquisa = selectPesquisa;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEquipeMedicaForm() {
		return equipeMedicaForm;
	}

	public void setEquipeMedicaForm(String equipeMedicaForm) {
		this.equipeMedicaForm = equipeMedicaForm;
	}

	public String getAdminForm() {
		return adminForm;
	}

	public void setAdminForm(String adminForm) {
		this.adminForm = adminForm;
	}

	public EquipeMedica getEquipeMedica() {
		return equipeMedica;
	}

	public void setEquipeMedica(EquipeMedica equipeMedica) {
		this.equipeMedica = equipeMedica;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<EquipeMedica> getListaEquipeMedica() {
		return listaEquipeMedica;
	}

	public void setListaEquipeMedica(List<EquipeMedica> listaEquipeMedica) {
		this.listaEquipeMedica = listaEquipeMedica;
	}

	public List<Admin> getListaAdmin() {
		return listaAdmin;
	}

	public void setListaAdmin(List<Admin> listaAdmin) {
		this.listaAdmin = listaAdmin;
	}

}
