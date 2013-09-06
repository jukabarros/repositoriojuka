package evolucao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import paciente.Paciente;
import paciente.PacienteService;
import usuario.Usuario;
import evolucao.Evolucao;
import evolucao.EvolucaoService;

@ManagedBean(name="evolucaoBean")
@ViewScoped
public class EvolucaoController implements Serializable {

	private static final long serialVersionUID = 1938324740571341455L;
	
	private Evolucao evolucao;
	
	private List<Evolucao> listaEvolucao = new ArrayList<Evolucao>();				
	
	private List<Evolucao> listaEvolucaoTopPaciente = new ArrayList<Evolucao>();
	
	private EvolucaoService evolucaoService = EvolucaoService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	
	// As duas Var abaixo sao responsaveis por listar os valores no metodo Buscar Controller
	private List<Paciente> listaPaciente = new ArrayList<Paciente>();	
	
	private PacienteService pacienteService = PacienteService.getInstance();	// Nao existe Get e Set pois nao eh chamado nos arquivos JSF 
	
	
	//Variaveis usadas no dialog de pesquisa entre datas
	private Date dataInicial;
	
	private Date dataFinal;
	
	//Variavel que recebe o valor (id_paciente) do selectOneMenu com os nomes dos pacientes
	private long selectPaciente; 
	
	
	public EvolucaoController() {
		evolucao = new Evolucao();
		atualizarTela();
		
	} 
	
	/**
	 * Limpa os campos input e atualiza a lista de proprietarios cadastrados
	 */
	
	private void atualizarTela() {
		System.out.println("\n*** Refresh da Pagina / Consultando Todos os Registro da Tabela Evolucao\n");
		evolucao = new Evolucao();
		listaEvolucao = evolucaoService.buscarTodos();
		//listaEvolucaoTopPaciente = evolucaoService.top2Paciente();
	}
	
	/** Metodos de Consulta
	 * Consulta registro com a entrada do usuario
	 */
	
	public void buscarController() {
		System.out.println("\n*** Consultando Registros do Paciente ID: "+selectPaciente);
		if (selectPaciente==0){				
			atualizarTela(); 			
		}
		
		else{
			try{			
			listaPaciente = pacienteService.buscarPorId(selectPaciente);
			Paciente paciente = listaPaciente.get(0); // Pegando os dados do Paciente na listaPaciente
			listaEvolucao = evolucaoService.buscarPorPaciente(paciente); // Passando os parametros para o metodo
			
			}catch(Exception e){
				System.err.println("** VALOR INVALIDO: "+selectPaciente);
				System.out.println("ERRO: "+e);
				listaEvolucao = null;
			}
		} // FECHANDO O ELSE
		} //FECHANDO O METODO
	
	//ENTRE DATAS
	public void buscarPorDatasController() {
			
		System.out.println("\n*** Consultando Registros Entre Datas: "+dataInicial+" e "+dataFinal );
		try{	
			if (dataInicial.after(dataFinal)){ // Se a data Inicial for maior que a data final retornar lista vazia
				System.err.println("** Erro: Data Inicial maior que a data Final");
				listaEvolucao = null;
			}else{	
				if (selectPaciente==0){	// Caso for pra Listar com Todos os Pacientes
					listaEvolucao = evolucaoService.buscarPorDatas(dataInicial, dataFinal); // Passando o valor da lista como parametro
				}else{
					System.out.println("** Paciente Selecionado: "+selectPaciente);
					listaPaciente = pacienteService.buscarPorId(selectPaciente);
					Paciente paciente = listaPaciente.get(0); // Pegando os dados do Paciente na listaPaciente
					listaEvolucao = evolucaoService.buscarPorDatasPaciente(dataInicial, dataFinal, paciente); // Passando os parametros para o metodo 
				}
				
			}
			}catch(Exception e){
				System.err.println("** VALORES DAS DATAS INVALIDOS: "+dataInicial+ " e "+dataFinal);
				System.out.println("** ERRO: "+e);
				atualizarTela(); // Refresh da Pagina com todos os Registros
			}
		
		} //FECHANDO O METODO
	
	/**
	 * Grava novo registro ou atualiza um registro
	 */
	
	public String gravar(){
		System.out.println("\n*** Gravando Registro\n");
		try{
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			evolucao.setUsuario(usuario); // Setando o usuario
			evolucaoService.gravar(getEvolucao());
			atualizarTela();	
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("Registro Cadastrado com Sucesso!!")); //Mensagem de validacao
			
		}catch (Exception e){
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar a evolução: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
		return null;
	}
		
	/**
	 * Exclui um registro da tabela evolcao
	 */
	
	public void excluir(){
		System.out.println("\n*** Excluindo Registro\n");
		try{
			evolucaoService.excluir(getEvolucao());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("** Registro Deletado com Sucesso!!")); //Mensagem de validacao 
			
		}catch(Exception e){
			System.out.println("Erro ao deletar: "+e.getMessage());
			atualizarTela();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar a evolução: "+e.getMessage(), "")); //Mensagem de erro 
			
		}
		
	}
	
	
	/**
	 * GET E SET DAS VARIAVEIS
	 */
	public Evolucao getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	public List<Evolucao> getListaEvolucao() {
		return listaEvolucao;
	}

	public void setListaEvolucao(List<Evolucao> listaEvolucao) {
		this.listaEvolucao = listaEvolucao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public long getSelectPaciente() {
		return selectPaciente;
	}

	public void setSelectPaciente(long selectPaciente) {
		this.selectPaciente = selectPaciente;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public List<Evolucao> getListaEvolucaoTopPaciente() {
		return listaEvolucaoTopPaciente;
	}

	public void setListaEvolucaoTopPaciente(List<Evolucao> listaEvolucaoTopPaciente) {
		this.listaEvolucaoTopPaciente = listaEvolucaoTopPaciente;
	}

	
	

}
