package usuario;

import java.io.Serializable;
import java.util.List;

import util.BeanFactory;

public class EquipeMedicaService implements Serializable {
	
	
	private static final long serialVersionUID = -631614864451298461L;
	private static EquipeMedicaService equipeMedicaService;
	private EquipeMedicaService() {
		super();		
	}
	
	public static EquipeMedicaService getInstance() {
		if(equipeMedicaService == null){
			equipeMedicaService = new EquipeMedicaService();
		}
		return equipeMedicaService;
	}

	private EquipeMedicaDAO equipeMedicaDAO = (EquipeMedicaDAO) BeanFactory.getBean("equipeMedicaDAO", EquipeMedicaDAO.class);
	
	public List<EquipeMedica> buscarTodos(){	// Eh usado na criacao do dataTable	
		return equipeMedicaDAO.buscarTodos();
	}
	
	public void gravar(EquipeMedica equipeMedica){
		equipeMedicaDAO.gravar(equipeMedica);
	}
	
	public void excluir(EquipeMedica equipeMedica){
		equipeMedicaDAO.excluir(equipeMedica);
	}


}
