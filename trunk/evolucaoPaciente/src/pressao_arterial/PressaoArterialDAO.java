package pressao_arterial;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pressao_arterial.PressaoArterial;

public class PressaoArterialDAO extends HibernateDaoSupport implements Serializable{
	
	private static final long serialVersionUID = -6603194793222027436L;

	// METODOS DE CONSULTA AO BD
	public List<PressaoArterial> buscarTodos(){
		return getHibernateTemplate().loadAll(PressaoArterial.class);
	}
	
	public List<PressaoArterial> buscarPorId(long idPressaoArterial){
		DetachedCriteria criteria = DetachedCriteria.forClass(PressaoArterial.class);  
		criteria.add(Restrictions.eq("idPressaoArterial", idPressaoArterial));	
		return getHibernateTemplate().findByCriteria(criteria);
	}
	/*
	 * Valores de sistolica e diastolica sao iguais na mesma linha
	 */
	public List<PressaoArterial> pressaoArterialConverter(int valor){
		DetachedCriteria criteria = DetachedCriteria.forClass(PressaoArterial.class);  
		criteria.add(Restrictions.eq("sistolica", valor));	
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	
}
