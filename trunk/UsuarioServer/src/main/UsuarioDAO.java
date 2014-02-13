package main;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import main.Usuario;

public class UsuarioDAO extends HibernateDaoSupport implements Serializable{

	private static final long serialVersionUID = -7611942161734538205L;
	
	// METODOS DE CONSULTA AO BD
		public List<Usuario> buscarTodos(){
			return getHibernateTemplate().loadAll(Usuario.class); // Recupera todos os registro referente a tabela Usuario
			}
				
		public List<Usuario> buscarPorId(long idUsuario){
			DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);  
			criteria.add(Restrictions.eq("idUsuario", idUsuario)); //Valor Igual, idUsuario eh o atributo da classe Java e nao a coluna no BD	
			return getHibernateTemplate().findByCriteria(criteria);
					
			}
		
		// METODOS DE INSERCAO, UPDATE, DELETE DO BANCO DE DADOS
		
		public void gravar(Usuario usuario){
			getHibernateTemplate().saveOrUpdate(usuario);
			//System.out.println("Cadastro Realizado com Sucesso!!");
			}
				
		public void excluir(Usuario usuario){
			getHibernateTemplate().delete(usuario);
		//	System.out.println("Usuario excluido com Sucesso!!");
			}

}
