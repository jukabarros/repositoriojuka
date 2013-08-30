package usuario;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EquipeMedicaDAO extends HibernateDaoSupport implements Serializable{

	private static final long serialVersionUID = -5442548381535364802L;
	
	public List<EquipeMedica> buscarTodos(){
		return getHibernateTemplate().loadAll(EquipeMedica.class);
	}
	
	public void gravar(EquipeMedica equipeMedica){
		getHibernateTemplate().saveOrUpdate(equipeMedica);
	}
	
	public void excluir(EquipeMedica equipeMedica){
		getHibernateTemplate().delete(equipeMedica);
	}

}
