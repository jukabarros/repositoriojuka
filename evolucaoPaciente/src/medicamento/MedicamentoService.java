package medicamento;

import java.io.Serializable;
import java.util.List;

import util.BeanFactory;
import medicamento.Medicamento;
import medicamento.MedicamentoDAO;
import medicamento.MedicamentoService;

public class MedicamentoService implements Serializable{
	
	private static final long serialVersionUID = 8628920372789585082L;
	private static MedicamentoService medicamentoService;
	private MedicamentoService() {
		super();		
	}
	
	public static MedicamentoService getInstance() {
		if(medicamentoService == null){
			medicamentoService = new MedicamentoService();
		}
		return medicamentoService;
	}

	private MedicamentoDAO medicamentoDAO = (MedicamentoDAO) BeanFactory.getBean("medicamentoDAO", MedicamentoDAO.class);

	
	public List<Medicamento> buscarTodos(){	// Eh usado na criacao do dataTable	
		return medicamentoDAO.buscarTodos();
	}
	
	public List<Medicamento> buscarPorId(long idMedicamento){
		return medicamentoDAO.buscarPorId(idMedicamento);		
	}
	
	public List<Medicamento> buscarPorNome(String nome){ // Criteria eh da clausula Like
		return medicamentoDAO.buscarPorNome(nome);		
	}
	
	//Esse Metodo eh usado na Classe MedicamentoConverter.java 
	public List<Medicamento> buscarPorNomeConverter(String nome){ // Criteria eh da clausula Where
		return medicamentoDAO.buscarPorNomeConverter(nome);		
	}
	
	public void gravar(Medicamento medicamento){
		medicamentoDAO.gravar(medicamento);
	}
	
	public void excluir(Medicamento medicamento){
		medicamentoDAO.excluir(medicamento);
	}	


}
