package pressao_arterial;

import java.io.Serializable;
import java.util.List;

import util.BeanFactory;
import pressao_arterial.PressaoArterial;
import pressao_arterial.PressaoArterialDAO;
import pressao_arterial.PressaoArterialService;

public class PressaoArterialService implements Serializable {
	
	private static final long serialVersionUID = -6697190792345538802L;
	private static PressaoArterialService pressaoArterialService;
	private PressaoArterialService() {
		super();		
	}
	
	public static PressaoArterialService getInstance() {
		if(pressaoArterialService == null){
			pressaoArterialService = new PressaoArterialService();
		}
		return pressaoArterialService;
	}

	private PressaoArterialDAO pressaoArterialDAO = (PressaoArterialDAO) BeanFactory.getBean("pressaoArterialDAO", PressaoArterialDAO.class);

	
	public List<PressaoArterial> buscarTodos(){	// Eh usado na criacao do dataTable	
		return pressaoArterialDAO.buscarTodos();
	}
	
	public List<PressaoArterial> buscarPorId(long idPressaoArterial){
		return pressaoArterialDAO.buscarPorId(idPressaoArterial);		
	}
	
	public List<PressaoArterial> pressaoArterialConverter(int valor){
		return pressaoArterialDAO.pressaoArterialConverter(valor);		
	}
	
	

}
