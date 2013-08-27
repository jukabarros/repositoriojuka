package paciente;

import paciente.Paciente;
import org.hibernate.criterion.*;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PacienteDAO extends HibernateDaoSupport implements Serializable {
	
	// METODOS DE CONSULTA AO BD
		public List<Paciente> buscarTodos(){
			return getHibernateTemplate().loadAll(Paciente.class); // Recupera todos os registro referente a tabela paciente
		}
		
		public List<Paciente> buscarPorId(long idPaciente){
			DetachedCriteria criteria = DetachedCriteria.forClass(Paciente.class);  
			criteria.add(Restrictions.eq("idPaciente", idPaciente)); //Valor Igual, idPaciente eh o atributo da classe Java e nao a coluna no BD	
			return getHibernateTemplate().findByCriteria(criteria);
			
		}
		
		public List<Paciente> buscarPorNome(String nome){
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Paciente.class);  
			criteria.add(Restrictions.like("nome", "%"+nome+"%")); //VALOR ENTRE OS % SIGNIFICA: QUE CONTEM A STRING  			
			return getHibernateTemplate().findByCriteria(criteria);
			
		}
		
		//Metodo Usado na Classe PacienteConverter.java, retorna apenas um registro (coluna nome -> unique)
		public List<Paciente> buscarPorNomeConverter(String nome){
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Paciente.class);  
			criteria.add(Restrictions.eq("nome", nome));			
			return getHibernateTemplate().findByCriteria(criteria);
			
		}
		
		public List<Paciente> buscarPorCpf(String cpf){
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Paciente.class);  
			criteria.add(Restrictions.like("cpf", "%"+cpf+"%")); //VALOR ENTRE OS % SIGNIFICA: QUE CONTEM A STRING  			
			return getHibernateTemplate().findByCriteria(criteria);		
			
		}
		
		// METODOS DE INSERCAO, UPDATE, DELETE DO BANCO DE DADOS
		public void gravar(Paciente paciente){
			getHibernateTemplate().saveOrUpdate(paciente);
		}
		
		public void excluir(Paciente paciente){
			getHibernateTemplate().delete(paciente);
		}
	

}
