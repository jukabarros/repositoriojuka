package cirurgia;

import java.io.Serializable;
import java.util.List;

import util.BeanFactory;
import cirurgia.Cirurgia;
import cirurgia.CirurgiaDAO;
import cirurgia.CirurgiaService;

public class CirurgiaService implements Serializable{

	private static final long serialVersionUID = -8277045224990912138L;
	
	private static CirurgiaService cirurgiaService;
	private CirurgiaService() {
		super();		
	}
	
	public static CirurgiaService getInstance() {
		if(cirurgiaService == null){
			cirurgiaService = new CirurgiaService();
		}
		return cirurgiaService;
	}

	private CirurgiaDAO cirurgiaDAO = (CirurgiaDAO) BeanFactory.getBean("cirurgiaDAO", CirurgiaDAO.class);

	
	public List<Cirurgia> buscarTodos(){	// Eh usado na criacao do dataTable	
		return cirurgiaDAO.buscarTodos();
	}
	
	public List<Cirurgia> buscarPorId(long idCirurgia){
		return cirurgiaDAO.buscarPorId(idCirurgia);		
	}
	
	public List<Cirurgia> buscarPorNome(String nome){ // Criteria eh da clausula Like
		return cirurgiaDAO.buscarPorNome(nome);		
	}
	
	//Esse Metodo eh usado na Classe CirurgiaConverter.java 
	public List<Cirurgia> buscarPorNomeConverter(String nome){ // Criteria eh da clausula Where
		return cirurgiaDAO.buscarPorNomeConverter(nome);		
	}
	
	public void gravar(Cirurgia cirurgia){
		cirurgiaDAO.gravar(cirurgia);
	}
	
	public void excluir(Cirurgia cirurgia){
		cirurgiaDAO.excluir(cirurgia);
	}	


}
