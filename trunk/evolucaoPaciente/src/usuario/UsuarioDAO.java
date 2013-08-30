package usuario;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import usuario.Usuario;

public class UsuarioDAO extends HibernateDaoSupport implements Serializable {

	private static final long serialVersionUID = -8194343898086893800L;
	
	// METODOS DE CONSULTA AO BD
	public List<Usuario> buscarTodos(){
		return getHibernateTemplate().loadAll(Usuario.class);
	}

	public List<Usuario> buscarPorNome(String nome){
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);  
		criteria.add(Restrictions.like("nome", "%"+nome+"%"));  			
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<Usuario> buscarPorTipo(String tipo){
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);  
		criteria.add(Restrictions.like("tipo", "%"+tipo+"%"));  			
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<Usuario> buscarPorNomeConverter(String nome){
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);  
		criteria.add(Restrictions.eq("nome", nome));			
		return getHibernateTemplate().findByCriteria(criteria);
	}

	// METODOS DE INSERCAO, UPDATE, DELETE DO BANCO DE DADOS
	public void gravar(Usuario usuario){
		getHibernateTemplate().saveOrUpdate(usuario);
	}
	
	public void excluir(Usuario usuario){
		getHibernateTemplate().delete(usuario);
	}

}
