package usuario;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdminDAO extends HibernateDaoSupport implements Serializable{

	private static final long serialVersionUID = -5442548381535364802L;
	
	public List<Admin> buscarTodos(){
		return getHibernateTemplate().loadAll(Admin.class);
	}
	public void gravar(Admin admin){
		getHibernateTemplate().saveOrUpdate(admin);
	}
	
	public void excluir(Admin admin){
		getHibernateTemplate().delete(admin);
	}

}
