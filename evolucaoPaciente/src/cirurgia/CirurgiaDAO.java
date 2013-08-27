package cirurgia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CirurgiaDAO extends HibernateDaoSupport implements Serializable {
	

	// METODOS DE CONSULTA AO BD
	public List<Cirurgia> buscarTodos(){
		return getHibernateTemplate().loadAll(Cirurgia.class);
	}
	
	public List<Cirurgia> buscarPorId(long idCirurgia){
		DetachedCriteria criteria = DetachedCriteria.forClass(Cirurgia.class);  
		criteria.add(Restrictions.eq("idCirurgia", idCirurgia));	
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	public List<Cirurgia> buscarPorNome(String nome){
		DetachedCriteria criteria = DetachedCriteria.forClass(Cirurgia.class);  
		criteria.add(Restrictions.like("nome", "%"+nome+"%"));  			
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	public List<Cirurgia> buscarPorNomeConverter(String nome){
		DetachedCriteria criteria = DetachedCriteria.forClass(Cirurgia.class);  
		criteria.add(Restrictions.eq("nome", nome));			
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	// METODOS DE INSERCAO, UPDATE, DELETE DO BANCO DE DADOS
	public void gravar(Cirurgia cirurgia){
		getHibernateTemplate().saveOrUpdate(cirurgia);
	}
	
	public void excluir(Cirurgia cirurgia){
		getHibernateTemplate().delete(cirurgia);
	}
}
