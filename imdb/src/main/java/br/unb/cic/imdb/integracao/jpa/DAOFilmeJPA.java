package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import br.unb.cic.imdb.integracao.DAOFilme;
import br.unb.cic.imdb.integracao.DAOTrabalhoArtistico;
import br.unb.cic.imdb.negocio.Filme;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;

public class DAOFilmeJPA implements DAOFilme {
	
	private EntityManager em;

	@Override
	@Transactional
	public void salvar(Filme filme) {
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(filme);
			em.getTransaction().commit();
			
		} catch (RollbackException e) {
			System.err.println("Nao foi possivel inserir o album!");
		}
		
	}

	@Override
	public Filme recuperarPorTitulo(String titulo) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<Filme> filme = em.createQuery("FROM Filme WHERE titulo = :tituloParam")
				.setParameter("tituloParam", titulo).getResultList();
		return filme.size() == 1 ? filme.get(0) : null;
	}

	@Override
	public List<Filme> recuperarTodos() {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		return em.createQuery("FROM Filme").getResultList();
	}

}
