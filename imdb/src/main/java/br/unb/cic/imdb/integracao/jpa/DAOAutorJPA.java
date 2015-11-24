package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import br.unb.cic.imdb.integracao.DAOAutor;
import br.unb.cic.imdb.negocio.Autor;
import br.unb.cic.imdb.negocio.Genero;

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
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		return em.createQuery("FROM Autor").getResultList();
	}

	@Override
	public Autor recuperaPorNome(String nomeAutor) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<Autor> autores = em.createQuery("FROM Autor WHERE nome = :nomeParam")
				.setParameter("nomeParam", nomeAutor).getResultList();
		return autores.size() == 1 ? autores.get(0) : null;
	}
	
}
