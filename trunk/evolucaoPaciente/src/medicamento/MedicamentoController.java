package medicamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import medicamento.Medicamento;
import medicamento.MedicamentoService;

@ManagedBean(name="medicamentoBean")
@ViewScoped
public class MedicamentoController implements Serializable{
	
	private static final long serialVersionUID = 1722959998040091165L;

	private Medicamento medicamento = new Medicamento();
	
	private List<Medicamento> listaMedicamento = new ArrayList<Medicamento>();	
	
	private MedicamentoService medicamentoService = MedicamentoService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	
	private String selectPesquisa; //Variavel que recebe o valor do selectOneMenu, responsavel por direcionar o metodo de consulta
	
	private String campoPesquisa; //Variavel que recebe o valor do inputText, eh o parametro de pesquisa dos metodo de consulta
	
	public MedicamentoController() {
		
		atualizarTela();
		} 

	/**
	 * Limpa os campos input e atualiza a lista de medicamentos cadastrados
	 */
	
	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela Medicamento\n");
		medicamento = new Medicamento();
		listaMedicamento = medicamentoService.buscarTodos();
		
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
			listaMedicamento = medicamentoService.buscarPorNome(nome);	
			}
		} //FECHANDO O IF SELECTPESQUISA(NOME)
		
		if (selectPesquisa.equals("idMedicamento")){
			
			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}		
			
			else{
				System.out.println("\n*** Buscando por ID\n");
				try{ // Tratamento de Erro Caso o usuario Colocar Letras no campo
					System.out.println("** ID MEDICAMENTO: "+campoPesquisa);
					long idMedicamento = Long.parseLong(campoPesquisa); 
					listaMedicamento = medicamentoService.buscarPorId(idMedicamento);			
				}catch (Exception e) {
					System.err.println("\n ** ID invalido\n"+e);					
					listaMedicamento = null; // Lista vai ser vazia pois o ID foi invalido
				}		
			} // FECHANDO O ELSE
		} //FECHANDO O IF SELECTPESQUISA(IDPROPRIETARIO)
		
	
	} // FECHANDO O METODO BUSCARCONTROLLER
	
	/**
	 * Grava novo registro ou atualiza um registro
	 */
	
	public String gravar(){
		System.out.println("\n*** Gravando Registro\n");
		try{
			medicamentoService.gravar(getMedicamento());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao 
			
		}catch(Exception e){
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar o medicamento: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
		return null;
	}
		
	/**
	 * Exclui um registro da tabela medicamento
	 */
	
	public void excluir(){
		System.out.println("\n*** Excluindo Registro\n");
		try{
			medicamentoService.excluir(getMedicamento());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Registro Deletado com Sucesso!!")); //Mensagem de validacao 
			
		}catch(Exception e){
			System.err.println("** Erro ao deletar: "+e.getMessage());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar o medicamento: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
	}

		
	/**
	 * Get and Set das Variaveis
	 */
	
	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<Medicamento> getListaMedicamento() {
		return listaMedicamento;
	}

	public void setListaMedicamento(List<Medicamento> listaMedicamento) {
		this.listaMedicamento = listaMedicamento;
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
