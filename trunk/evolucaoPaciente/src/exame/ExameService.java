package exame;

import java.io.Serializable;
import java.util.List;

import exame.Exame;
import exame.ExameDAO;
import util.BeanFactory;

public class ExameService implements Serializable{
	
	private static final long serialVersionUID = 7684092795587475196L;
	
	private static ExameService exameService;
	private ExameService() {
		super();		
	}
	
	public static ExameService getInstance() {
		if(exameService == null){
			exameService = new ExameService();
		}
		return exameService;
	}

	private ExameDAO exameDAO = (ExameDAO) BeanFactory.getBean("exameDAO", ExameDAO.class);

	
	public List<Exame> buscarTodos(){	// Eh usado na criacao do dataTable	
		return exameDAO.buscarTodos();
	}
	
	public List<Exame> buscarPorId(long idExame){
		return exameDAO.buscarPorId(idExame);		
	}
	
	public List<Exame> buscarPorNome(String nome){ // Criteria eh da clausula Like
		return exameDAO.buscarPorNome(nome);		
	}
	
	//Esse Metodo eh usado na Classe ExameConverter.java 
	public List<Exame> buscarPorNomeConverter(String nome){ // Criteria eh da clausula Where
		return exameDAO.buscarPorNomeConverter(nome);		
	}
	
	public void gravar(Exame exame){
		exameDAO.gravar(exame);
	}
	
	public void excluir(Exame exame){
		exameDAO.excluir(exame);
	}	

}
