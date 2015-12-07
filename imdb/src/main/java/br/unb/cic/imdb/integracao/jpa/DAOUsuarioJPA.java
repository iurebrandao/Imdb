package br.unb.cic.imdb.integracao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import br.unb.cic.imdb.integracao.DAOUsuario;
import br.unb.cic.imdb.negocio.Usuario;


public class DAOUsuarioJPA implements DAOUsuario {
	
	private EntityManager em;
	
	
   	@Override
   	@Transactional
	public boolean inserir(Usuario usuario) {
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			return true;
			
		} catch (RollbackException e) {
			System.err.println("Nao foi possivel realizar o cadastro!");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean remover(String login) {
		
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			em.remove(recuperarPorNome(login));
			em.flush();
			em.getTransaction().commit();
			return true;
			
		}catch(IllegalArgumentException e){
			em.getTransaction().rollback();
			return false;
		}
	}

	@Override
	public Usuario recuperarPorNome(String login) {
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			Query q = em.createQuery("select u from Usuario u where u.login = :login", Usuario.class);
			q.setParameter("login", login);
			return (Usuario) q.getSingleResult();
			
		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}
	
	public Long recuperarId(String login){
		Usuario auxUsuario;
		Long auxId;
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			Query q = em.createQuery("select u from Usuario u where u.login = :login", Usuario.class);
			q.setParameter("login", login);
			auxUsuario = (Usuario) q.getSingleResult();
			auxId = auxUsuario.getId();
			return auxId;
			
		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}
}