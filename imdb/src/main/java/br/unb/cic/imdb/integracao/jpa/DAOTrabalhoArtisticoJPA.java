package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import br.unb.cic.imdb.integracao.DAOTrabalhoArtistico;
import br.unb.cic.imdb.negocio.Autor;
import br.unb.cic.imdb.negocio.Genero;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;

public class DAOTrabalhoArtisticoJPA implements DAOTrabalhoArtistico{
	
	private EntityManager em;
	
	@Override
	public void salvar(TrabalhoArtistico trabalhoArtistico) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(trabalhoArtistico);
		em.getTransaction().commit();
	}

	@Override
	public List<TrabalhoArtistico> recuperaTodos() {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		return em.createQuery("FROM TrabalhoArtistico").getResultList();
	}

	@Override
	public TrabalhoArtistico recuperaPorGenero(String nomeGenero) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<TrabalhoArtistico> generos = em.createQuery("FROM Genero WHERE titulo = :nomeParam")
				.setParameter("nomeParam", nomeGenero).getResultList();
		return generos.size() == 1 ? generos.get(0) : null;
	}

	@Override
	public TrabalhoArtistico recuperaPorTitulo(String nomeTitulo) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<TrabalhoArtistico> trabalhos = em.createQuery("FROM TrabalhoArtistico WHERE titulo = :tituloParam")
				.setParameter("tituloParam", nomeTitulo).getResultList();
		return trabalhos.size() == 1 ? trabalhos.get(0) : null;
	}

	@Override
	public TrabalhoArtistico recuperaPorAutor(String nomeAutor) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<TrabalhoArtistico> autores = em.createQuery("FROM Autor WHERE nome = :nomeParam")
				.setParameter("nomeParam", nomeAutor).getResultList();
		return autores.size() == 1 ? autores.get(0) : null;
	}

}
