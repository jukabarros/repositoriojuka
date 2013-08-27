package paciente;

import java.io.Serializable;
import java.util.List;
import util.BeanFactory;
import paciente.PacienteDAO;
import paciente.Paciente;


public class PacienteService implements Serializable{
	
	private static PacienteService pacienteService;
	private PacienteService() {
		super();		
	}
	
	public static PacienteService getInstance() {
		if(pacienteService == null){
			pacienteService = new PacienteService();
		}
		return pacienteService;
	}

	private PacienteDAO pacienteDAO = (PacienteDAO) BeanFactory.getBean("pacienteDAO", PacienteDAO.class);

	
	public List<Paciente> buscarTodos(){	// Eh usado na criacao do dataTable	
		return pacienteDAO.buscarTodos();
	}
	
	public List<Paciente> buscarPorId(long idPaciente){
		return pacienteDAO.buscarPorId(idPaciente);		
	}
	
	public List<Paciente> buscarPorNome(String nome){ // Criteria eh da clausula Like
		return pacienteDAO.buscarPorNome(nome);		
	}
	
	//Esse Metodo eh usado na Classe PacienteConverter.java 
	public List<Paciente> buscarPorNomeConverter(String nome){ // Criteria eh da clausula Where
		return pacienteDAO.buscarPorNomeConverter(nome);		
	}
	
	public List<Paciente> buscarPorCpf(String cpf){
		return pacienteDAO.buscarPorCpf(cpf);		
	}
	
	public void gravar(Paciente paciente){
		pacienteDAO.gravar(paciente);
	}
	
	public void excluir(Paciente paciente){
		pacienteDAO.excluir(paciente);
	}	

}
