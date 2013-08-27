package exame;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ExameDAO extends HibernateDaoSupport implements Serializable {
	
	// METODOS DE CONSULTA AO BD
		public List<Exame> buscarTodos(){
			return getHibernateTemplate().loadAll(Exame.class);
		}
		
		public List<Exame> buscarPorId(long id_exame){
			DetachedCriteria criteria = DetachedCriteria.forClass(Exame.class);  
			criteria.add(Restrictions.eq("idExame", id_exame));	
			return getHibernateTemplate().findByCriteria(criteria);
		}
		
		public List<Exame> buscarPorNome(String nome){
			DetachedCriteria criteria = DetachedCriteria.forClass(Exame.class);  
			criteria.add(Restrictions.like("nome", "%"+nome+"%"));  			
			return getHibernateTemplate().findByCriteria(criteria);
		}
		
		public List<Exame> buscarPorNomeConverter(String nome){
			DetachedCriteria criteria = DetachedCriteria.forClass(Exame.class);  
			criteria.add(Restrictions.eq("nome", nome));			
			return getHibernateTemplate().findByCriteria(criteria);
		}
		
		public void gravar(Exame exame){
			getHibernateTemplate().saveOrUpdate(exame);
		}
		
		public void excluir(Exame exame){
			getHibernateTemplate().delete(exame);
		}
}