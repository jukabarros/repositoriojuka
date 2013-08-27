package paciente;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import paciente.Paciente;
import java.util.ArrayList;

@ManagedBean(name="pacienteBean")
@ViewScoped
public class PacienteController implements Serializable {
	
	private Paciente paciente = new Paciente();
	
	private List<Paciente> listaPaciente = new ArrayList<Paciente>();	
	
	private PacienteService pacienteService = PacienteService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	
	private String selectPesquisa; //Variavel que recebe o valor do selectOneMenu, responsavel por direcionar o metodo de consulta
	
	private String campoPesquisa; //Variavel que recebe o valor do inputText, eh o parametro de pesquisa dos metodo de consulta
	
	public PacienteController() {
		
		atualizarTela();
		} 

	/**
	 * Limpa os campos input e atualiza a lista de pacientes cadastrados
	 */
	
	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela Paciente\n");
		paciente = new Paciente();
		listaPaciente = pacienteService.buscarTodos();
		
	}
	
	/** Metodos de Consulta
	 * Consulta registro com a entrada do usuario
	 */
	
	public void buscarController() {
		System.out.println("\n***Consultando Registros\nCampo de Consulta Pesquisa: "+selectPesquisa);
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
			listaPaciente = pacienteService.buscarPorNome(nome);	
			}
		} //FECHANDO O IF SELECTPESQUISA(NOME)
		
		if (selectPesquisa.equals("idPaciente")){
			
			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}		
			
			else{
				System.out.println("\n*** Buscando por ID\n");
				try{ // Tratamento de Erro Caso o usuario Colocar Letras no campo
					System.out.println("ID PACIENTE: "+campoPesquisa);
					long idPaciente = Long.parseLong(campoPesquisa); 
					listaPaciente = pacienteService.buscarPorId(idPaciente);			
				}catch (Exception e) {
					System.out.println("\n ID invalido\n"+e);					
					listaPaciente = null; // Lista vai ser vazia pois o ID foi invalido
				}		
			} // FECHANDO O ELSE
		} //FECHANDO O IF SELECTPESQUISA(IDPROPRIETARIO)
		
		if (selectPesquisa.equals("cpf")){
			
			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}		
			else{
			System.out.println("\n*** Buscando por CPF\n");			
			String cpf = campoPesquisa; // Inserindo o valor que vai passar como parametro para os metodos
			listaPaciente = pacienteService.buscarPorCpf(cpf);	
			}
		
		} // Fechando IF SELECTPESQUISA(CPF)
		
	
	} // FECHANDO O METODO BUSCARCONTROLLER
	
	/**
	 * Grava novo registro ou atualiza um registro
	 */
	
	public String gravar(){
		System.out.println("\n*** Gravando Registro\n");
		System.out.println("ID: "+getPaciente().getIdPaciente());
		pacienteService.gravar(getPaciente());
		atualizarTela();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao 
		return null;
	}
		
	/**
	 * Exclui um registro da tabela paciente
	 */
	
	public void excluir(){
		System.out.println("\n*** Excluindo Registro\n");
		pacienteService.excluir(getPaciente());
		atualizarTela();
	}

		
	/**
	 * Get and Set das Variaveis
	 */
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
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
	
	

}
