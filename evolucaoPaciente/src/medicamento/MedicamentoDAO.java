package medicamento;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MedicamentoDAO extends HibernateDaoSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		public List<Medicamento> buscarTodos(){
			return getHibernateTemplate().loadAll(Medicamento.class);
		}
		
		public List<Medicamento> buscarPorId(long idMedicamento){
			DetachedCriteria criteria = DetachedCriteria.forClass(Medicamento.class);  
			criteria.add(Restrictions.eq("idMedicamento", idMedicamento));	
			return getHibernateTemplate().findByCriteria(criteria);
		}
		
		public List<Medicamento> buscarPorNome(String nome){
			DetachedCriteria criteria = DetachedCriteria.forClass(Medicamento.class);  
			criteria.add(Restrictions.like("nome", "%"+nome+"%"));  			
			return getHibernateTemplate().findByCriteria(criteria);
		}
		
		public List<Medicamento> buscarPorNomeConverter(String nome){
			DetachedCriteria criteria = DetachedCriteria.forClass(Medicamento.class);  
			criteria.add(Restrictions.eq("nome", nome));			
			return getHibernateTemplate().findByCriteria(criteria);
		}
		
		public void gravar(Medicamento medicamento){
			getHibernateTemplate().saveOrUpdate(medicamento);
		}
		
		public void excluir(Medicamento medicamento){
			getHibernateTemplate().delete(medicamento);
		}
}
