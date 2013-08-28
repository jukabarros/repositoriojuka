package evolucao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import paciente.Paciente;
import util.BeanFactory;
import evolucao.Evolucao;
import evolucao.EvolucaoDAO;
import evolucao.EvolucaoService;

public class EvolucaoService implements Serializable {

	private static final long serialVersionUID = -1034364004115284446L;
	
	private static EvolucaoService evolucaoService;	
	private EvolucaoService() {
		super();		
	}
	
	public static EvolucaoService getInstance() {
		if(evolucaoService == null){
			evolucaoService = new EvolucaoService();
		}
		return evolucaoService;
	}

	private EvolucaoDAO evolucaoDAO = (EvolucaoDAO) BeanFactory.getBean("evolucaoDAO", EvolucaoDAO.class);
	
	// Listar Todos
	public List<Evolucao> buscarTodos(){		
		return evolucaoDAO.buscarTodos();
	}
	
	// Buscar Entre Datas
	public List<Evolucao> buscarPorDatas(Date dataInicial, Date dataFinal){
		return evolucaoDAO.buscarPorDatas(dataInicial, dataFinal);		
	}
	
	// Buscar Entre Datas c/ Paciente Especifico
		public List<Evolucao> buscarPorDatasPaciente(Date dataInicial, Date dataFinal, Paciente paciente){
			return evolucaoDAO.buscarPorDatasPaciente(dataInicial, dataFinal, paciente);		
		}
	
	//Buscar Por Paciente
	public List<Evolucao> buscarPorPaciente(Paciente paciente){
		return evolucaoDAO.buscarPorPaciente(paciente);		
	}
	
	// INSERT, UPDATE e DELETE
	public void gravar(Evolucao evolucao){
		evolucaoDAO.gravar(evolucao);
	}
	
	public void excluir(Evolucao evolucao){
		evolucaoDAO.excluir(evolucao);
	}


}
