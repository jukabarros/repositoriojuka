package usuario;

import java.io.Serializable;
import java.util.List;

import util.BeanFactory;

public class AdminService implements Serializable{

	private static final long serialVersionUID = 2500174636842187345L;
	
	private static AdminService adminService;
	private AdminService() {
		super();		
	}
	
	public static AdminService getInstance() {
		if(adminService == null){
			adminService = new AdminService();
		}
		return adminService;
	}

	private AdminDAO adminDAO = (AdminDAO) BeanFactory.getBean("adminDAO", AdminDAO.class);
	
	public List<Admin> buscarTodos(){	// Eh usado na criacao do dataTable	
		return adminDAO.buscarTodos();
	}
	
	public void gravar(Admin admin){
		adminDAO.gravar(admin);
	}
	
	public void excluir(Admin admin){
		adminDAO.excluir(admin);
	}

}
