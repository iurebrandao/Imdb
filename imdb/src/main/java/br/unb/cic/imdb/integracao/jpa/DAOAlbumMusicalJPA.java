package br.unb.cic.imdb.integracao.jpa;
import java.util.List;

import javax.persistence.EntityManager;

import br.unb.cic.imdb.integracao.*;
import br.unb.cic.imdb.negocio.Autor;
import br.unb.cic.imdb.negocio.FaixaMusical;

public class DAOAlbumMusicalJPA implements DAOAlbumMusical{

	private EntityManager em;
	@Override
	public void salvar(FaixaMusical faixa) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(faixa);
		em.getTransaction().commit();
	}

	@Override
	public List<FaixaMusical> recuperaTodos() {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		return em.createQuery("FROM FaixaMusical").getResultList();
	}

	@Override
	public FaixaMusical recuperaPorNome(String titulo) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<FaixaMusical> faixas = em.createQuery("FROM FaixaMusical WHERE titulo = :tituloParam")
				.setParameter("tituloParam", titulo).getResultList();
		return faixas.size() == 1 ? faixas.get(0) : null;
	}

}
