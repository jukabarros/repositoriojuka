package exame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import exame.Exame;
import exame.ExameService;

@ManagedBean(name="exameBean")
@ViewScoped
public class ExameController implements Serializable {
	
	private static final long serialVersionUID = -2181956373007986864L;

	private Exame exame = new Exame();
	
	private List<Exame> listaExame = new ArrayList<Exame>();	
	
	private ExameService exameService = ExameService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	
	private String selectPesquisa; //Variavel que recebe o valor do selectOneMenu, responsavel por direcionar o metodo de consulta
	
	private String campoPesquisa; //Variavel que recebe o valor do inputText, eh o parametro de pesquisa dos metodo de consulta
	
	public ExameController() {
		
		atualizarTela();
		} 

	/**
	 * Limpa os campos input e atualiza a lista de exames cadastrados
	 */
	
	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela Exame\n");
		exame = new Exame();
		listaExame = exameService.buscarTodos();
		
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
			listaExame = exameService.buscarPorNome(nome);	
			}
		} //FECHANDO O IF SELECTPESQUISA(NOME)
		
		if (selectPesquisa.equals("idExame")){
			
			if(campoPesquisa.equals(null)){ // Evitar o Erro de passar parametro nulo para o metodo
				atualizarTela(); 	
			}		
			
			else{
				System.out.println("\n*** Buscando por ID\n");
				try{ // Tratamento de Erro Caso o usuario Colocar Letras no campo
					System.out.println("ID EXAME: "+campoPesquisa);
					long idExame = Long.parseLong(campoPesquisa); 
					listaExame = exameService.buscarPorId(idExame);			
				}catch (Exception e) {
					System.out.println("\n ID invalido\n"+e);					
					listaExame = null; // Lista vai ser vazia pois o ID foi invalido
				}		
			} // FECHANDO O ELSE
		} //FECHANDO O IF SELECTPESQUISA(IDPROPRIETARIO)
		
	
	} // FECHANDO O METODO BUSCARCONTROLLER
	
	/**
	 * Grava novo registro ou atualiza um registro
	 */
	
	public String gravar(){
		System.out.println("\n*** Gravando Registro\n");
		exameService.gravar(getExame());
		atualizarTela();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao 
		return null;
	}
		
	/**
	 * Exclui um registro da tabela exame
	 */
	
	public void excluir(){
		System.out.println("\n*** Excluindo Registro\n");
		exameService.excluir(getExame());
		atualizarTela();
	}

		
	/**
	 * Get and Set das Variaveis
	 */
	
	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Exame> getListaExame() {
		return listaExame;
	}

	public void setListaExame(List<Exame> listaExame) {
		this.listaExame = listaExame;
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
