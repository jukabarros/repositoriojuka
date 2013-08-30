package evolucao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import paciente.Paciente;
import evolucao.Evolucao;

public class EvolucaoDAO extends HibernateDaoSupport implements Serializable{

	private static final long serialVersionUID = 3651627621087763661L;
	
	public List<Evolucao> buscarTodos(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Evolucao.class);
		criteria.addOrder(Order.desc("dataHora"));
		return getHibernateTemplate().findByCriteria(criteria); // Retornando Ordenado pela DataHora
	}
	
	public List<Evolucao> buscarPorPaciente(Paciente paciente){
		DetachedCriteria criteria = DetachedCriteria.forClass(Evolucao.class);  
		criteria.add(Restrictions.eq("paciente", paciente)); //Valor Igual	
		criteria.addOrder(Order.desc("dataHora")); //Ordenado pela DataHora
		return getHibernateTemplate().findByCriteria(criteria);
		
	}
	
	public List<Evolucao> buscarPorDatas(Date dataInicial, Date dataFinal){
		DetachedCriteria criteria = DetachedCriteria.forClass(Evolucao.class);  
		criteria.add(Restrictions.between("dataHora", dataInicial, dataFinal)); //Valores Entre Datas	
		criteria.addOrder(Order.desc("dataHora")); //Ordenado pela DataHora
		return getHibernateTemplate().findByCriteria(criteria);
		
	}
	
	public List<Evolucao> buscarPorDatasPaciente(Date dataInicial, Date dataFinal, Paciente paciente){
		DetachedCriteria criteria = DetachedCriteria.forClass(Evolucao.class);  
		criteria.add(Restrictions.between("dataHora", dataInicial, dataFinal)); //Valores Entre Datas
		criteria.add(Restrictions.eq("paciente", paciente)); //Paciente Especifico	
		criteria.addOrder(Order.desc("dataHora")); //Ordenado pela DataHora
		return getHibernateTemplate().findByCriteria(criteria);
		
	}
	
	
	// METODOS DE INSERCAO, UPDATE, DELETE DO BANCO DE DADOS
	public void gravar(Evolucao evolucao){
		getHibernateTemplate().saveOrUpdate(evolucao);
	}

	public void excluir(Evolucao evolucao){
		getHibernateTemplate().delete(evolucao);
	}

}
