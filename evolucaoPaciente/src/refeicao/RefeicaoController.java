package refeicao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import refeicao.Refeicao;
import refeicao.RefeicaoService;

@ManagedBean(name="refeicaoBean")
@ViewScoped
public class RefeicaoController implements Serializable {
	
	private static final long serialVersionUID = -8727724793814018378L;

	private Refeicao refeicao = new Refeicao();
	
	private List<Refeicao> listaRefeicao = new ArrayList<Refeicao>();	
	
	private RefeicaoService refeicaoService = RefeicaoService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	
	private String campoPesquisa; //Variavel que recebe o valor do inputText, eh o parametro de pesquisa dos metodo de consulta
	
	public RefeicaoController() {

		atualizarTela();
	} 

	/**
	 * Limpa os campos input e atualiza a lista de refeicaos cadastrados
	 */
	
	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela Refeicao\n");
		refeicao = new Refeicao();
		listaRefeicao = refeicaoService.buscarTodos();
		
	}
	
	/** Metodos de Consulta
	 * Consulta registro com a entrada do usuario
	 */
	
	public void buscarController() {
		System.out.println("\n***Consultando Registros por ID");
		if (campoPesquisa.equals(null) || campoPesquisa.equals("")){			

			atualizarTela(); 			
		}

		else{

			System.out.println("\n*** Buscando por ID\n");
			try{ // Tratamento de Erro Caso o usuario Colocar Letras no campo
				System.out.println("ID REFEICAO: "+campoPesquisa);
				long idRefeicao = Long.parseLong(campoPesquisa); 
				listaRefeicao = refeicaoService.buscarPorId(idRefeicao);			
			}catch (Exception e) {
				System.out.println("\n ID invalido\n"+e);					
				listaRefeicao = null; // Lista vai ser vazia pois o ID foi invalido
			}		
		} // FECHANDO O ELSE

	} //FECHANDO O IF	
	
	/**
	 * Grava novo registro ou atualiza um registro
	 */
	
	public String gravar(){
		System.out.println("\n*** Gravando Registro\n");
		refeicaoService.gravar(getRefeicao());
		atualizarTela();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao 
		return null;
	}
		
	/**
	 * Exclui um registro da tabela refeicao
	 */
	
	public void excluir(){
		System.out.println("\n*** Excluindo Registro\n");
		refeicaoService.excluir(getRefeicao());
		atualizarTela();
	}

		
	/**
	 * Get and Set das Variaveis
	 */
	
	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public List<Refeicao> getListaRefeicao() {
		return listaRefeicao;
	}

	public void setListaRefeicao(List<Refeicao> listaRefeicao) {
		this.listaRefeicao = listaRefeicao;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}


}
