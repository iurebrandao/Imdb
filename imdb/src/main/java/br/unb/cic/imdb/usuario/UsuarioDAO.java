package br.unb.cic.imdb.usuario;

import java.util.List;

import javax.persistence.*;

public class UsuarioDAO {
	
	private static EntityManagerFactory emf;
	static EntityManager em;
	private static EntityTransaction tx;
	
	public static void comecarOperacoes() {
		emf = Persistence.createEntityManagerFactory("Imdb");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
	}
	public static void finalizarOperacoes(){
		tx.commit();
		em.close(); 
		emf.close();
	}
	
	public boolean inserir(Usuario usuario) {
		try{
			em.persist(usuario);
			return true;
		} catch(EntityExistsException e) {
			return false;
		}
	}
	
	public Usuario recuperar(String login){
		try {
			return em.find(Usuario.class, login);
		} catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	public boolean login(String login, String senha){
		try {
			Query q = em.createQuery("select u from Usuario u where u.login = :login", Usuario.class );
			q.setParameter("login",login);
			Usuario usuario = (Usuario) q.getSingleResult();

			return usuario.getSenha().equals(senha) ? true : false;
			
		} catch(IllegalArgumentException e){
			return false;
		}
	}
}