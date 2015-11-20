package br.unb.cic.imdb.integracao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import br.unb.cic.imdb.integracao.DAOUsuario;
import br.unb.cic.imdb.negocio.Usuario;


public class DAOUsuarioJPA implements DAOUsuario {
	
	private EntityManager em;
	
	@Override
	public boolean inserir(Usuario usuario) {
		
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			return true; 
			
		}catch(RollbackException e){
			System.err.println("Usuario nao cadastrado");
			return false;
		}
	}

	@Override
	public boolean remover(String login) {
		
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			em.remove(recuperar(login));
			em.getTransaction().commit();
			return true;
			
		}catch(IllegalArgumentException e){
			em.getTransaction().rollback();
			return false;
		}
		
	
	}

	@Override
	public Usuario recuperar(String login) {
		
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			Query q = em.createQuery("select u from Usuario u where u.login = :login_usuario", Usuario.class);
			q.setParameter("login_usuario", login);
			return (Usuario) q.getSingleResult();
			
		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}

}
