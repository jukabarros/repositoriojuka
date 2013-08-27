package refeicao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RefeicaoDAO extends HibernateDaoSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Refeicao> buscarTodos(){
		return getHibernateTemplate().loadAll(Refeicao.class);
	}
	
	public List<Refeicao> buscarPorId(long idRefeicao){
		DetachedCriteria criteria = DetachedCriteria.forClass(Refeicao.class);  
		criteria.add(Restrictions.eq("idRefeicao", idRefeicao));	
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	public void gravar(Refeicao refeicao){
		getHibernateTemplate().saveOrUpdate(refeicao);
	}
	
	public void excluir(Refeicao refeicao){
		getHibernateTemplate().delete(refeicao);
	}
}
