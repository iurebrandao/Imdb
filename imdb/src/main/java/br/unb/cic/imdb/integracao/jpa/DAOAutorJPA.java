package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unb.cic.imdb.integracao.DAOAutor;
import br.unb.cic.imdb.negocio.Autor;
import br.unb.cic.imdb.negocio.Genero;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;

public class DAOAutorJPA implements DAOAutor{

	private EntityManager em;
	
	@Override
	public void salvar(Autor autor) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(autor);
		em.getTransaction().commit();
	}
	
	@Override
	public List<Autor> recuperaTodos(){
		try {
			
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			return em.createQuery("FROM Autor").getResultList();
			
		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}

	@Override
	public Autor recuperaPorNome(String nomeAutor) {
		try {

			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			Query q = em.createQuery("select u FROM Autor u  WHERE u.nome = :nomeParam",Autor.class);
			q.setParameter("nomeParam", nomeAutor);
			return (Autor) q.getSingleResult();

		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}
	
}
