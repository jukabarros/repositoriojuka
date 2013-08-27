package refeicao;

import java.io.Serializable;
import java.util.List;

import util.BeanFactory;
import refeicao.Refeicao;
import refeicao.RefeicaoDAO;
import refeicao.RefeicaoService;

public class RefeicaoService implements Serializable{
	
	private static final long serialVersionUID = -2483199579180726707L;
	private static RefeicaoService refeicaoService;
	private RefeicaoService() {
		super();		
	}
	
	public static RefeicaoService getInstance() {
		if(refeicaoService == null){
			refeicaoService = new RefeicaoService();
		}
		return refeicaoService;
	}

	private RefeicaoDAO refeicaoDAO = (RefeicaoDAO) BeanFactory.getBean("refeicaoDAO", RefeicaoDAO.class);

	
	public List<Refeicao> buscarTodos(){	// Eh usado na criacao do dataTable	
		return refeicaoDAO.buscarTodos();
	}
	
	public List<Refeicao> buscarPorId(long idRefeicao){
		return refeicaoDAO.buscarPorId(idRefeicao);		
	}
	
	public void gravar(Refeicao refeicao){
		refeicaoDAO.gravar(refeicao);
	}
	
	public void excluir(Refeicao refeicao){
		refeicaoDAO.excluir(refeicao);
	}

}
