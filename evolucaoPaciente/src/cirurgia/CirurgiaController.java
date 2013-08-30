package cirurgia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import cirurgia.Cirurgia;
import cirurgia.CirurgiaService;

@ManagedBean(name="cirurgiaBean")
@ViewScoped
public class CirurgiaController implements Serializable{

	private static final long serialVersionUID = -7824495234152134527L;
	
private Cirurgia cirurgia = new Cirurgia();
	
	private List<Cirurgia> listaCirurgia = new ArrayList<Cirurgia>();	
	
	private CirurgiaService cirurgiaService = CirurgiaService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	
	private String selectPesquisa; //Variavel que recebe o valor do selectOneMenu, responsavel por direcionar o metodo de consulta
	
	private String campoPesquisa; //Variavel que recebe o valor do inputText, eh o parametro de pesquisa dos metodo de consulta
	
	public CirurgiaController() {
		
		atualizarTela();
		} 

	/**
	 * Limpa os campos input e atualiza a lista de cirurgias cadastrados
	 */
	
	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela Cirurgia\n");
		cirurgia = new Cirurgia();
		listaCirurgia = cirurgiaService.buscarTodos();
		
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
			listaCirurgia = cirurgiaService.buscarPorNome(nome);	
			}
		} //FECHANDO O IF SELECTPESQUISA(NOME)
		
		if (selectPesquisa.equals("idCirurgia")){
			
			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}		
			
			else{
				System.out.println("\n*** Buscando por ID\n");
				try{ // Tratamento de Erro Caso o usuario Colocar Letras no campo
					long idCirurgia = Long.parseLong(campoPesquisa); 
					listaCirurgia = cirurgiaService.buscarPorId(idCirurgia);			
				}catch (Exception e) {
					System.err.println("\n ** ID invalido\n"+e);					
					listaCirurgia = null; // Lista vai ser vazia pois o ID foi invalido
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
			cirurgiaService.gravar(getCirurgia());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao 
			
		}catch(Exception e){
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar a cirurgia: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
		return null;
	}
		
	/**
	 * Exclui um registro da tabela cirurgia
	 */
	
	public void excluir(){
		System.out.println("\n*** Excluindo Registro\n");
		try{
			cirurgiaService.excluir(getCirurgia());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Registro Deletado com Sucesso!!")); //Mensagem de validacao 
			
		}catch(Exception e){
			System.err.println("** Erro ao deletar: "+e.getMessage());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar a cirurgia: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
	}

		
	/**
	 * Get and Set das Variaveis
	 */
	
	public Cirurgia getCirurgia() {
		return cirurgia;
	}

	public void setCirurgia(Cirurgia cirurgia) {
		this.cirurgia = cirurgia;
	}

	public List<Cirurgia> getListaCirurgia() {
		return listaCirurgia;
	}

	public void setListaCirurgia(List<Cirurgia> listaCirurgia) {
		this.listaCirurgia = listaCirurgia;
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
