package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import br.unb.cic.imdb.integracao.DAOFaixaMusical;
import br.unb.cic.imdb.negocio.FaixaMusical;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;

public class DAOFaixaMusicalJPA implements DAOFaixaMusical {

	private EntityManager em;
	
	@Override
	public void salvar(FaixaMusical faixa) {
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(faixa);
			em.getTransaction().commit();
			
		} catch (RollbackException e) {
			System.err.println("Nao foi possivel adicionar a faixa!");
		}
	}

	@Override
	public List<FaixaMusical> recuperaTodos() {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		return em.createQuery("FROM FaixaMusical").getResultList();
	}

	@Override
	public FaixaMusical recuperaPorNome(String nomeFaixa) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<FaixaMusical> faixa = em.createQuery("FROM FaixaMusical WHERE titulo = :tituloParam")
				.setParameter("tituloParam", nomeFaixa).getResultList();
		return faixa.size() == 1 ? faixa.get(0) : null;
	}

}
