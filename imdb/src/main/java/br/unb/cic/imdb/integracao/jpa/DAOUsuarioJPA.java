package br.unb.cic.imdb.integracao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import br.unb.cic.imdb.integracao.DAOUsuario;
import br.unb.cic.imdb.negocio.Usuario;


public class DAOUsuarioJPA implements DAOUsuario {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
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
	
	
	public static void comecarOperacoes() {
		emf = Persistence.createEntityManagerFactory("Midas");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
	}
	
	public static void finalizarOperacoes(){
		tx.commit();
		em.close(); 
		emf.close();
	}

}
