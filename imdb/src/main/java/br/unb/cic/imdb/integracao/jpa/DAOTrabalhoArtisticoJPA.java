package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
		try {
			
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			return em.createQuery("FROM TrabalhoArtistico").getResultList();
			
		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}

	@Override
	public TrabalhoArtistico recuperaPorGenero(String nomeGenero) {
		try {
			
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			List<TrabalhoArtistico> generos = em.createQuery("FROM Genero WHERE titulo = :nomeParam")
					.setParameter("nomeParam", nomeGenero).getResultList();
			return generos.size() == 1 ? generos.get(0) : null;
			
		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}

	@Override
	public TrabalhoArtistico recuperaPorTitulo(String nomeTitulo) {
		try {

			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			Query q = em.createQuery("select u FROM tb_trabalho_artistico u  WHERE u.titulo = :tituloParam",TrabalhoArtistico.class);
			q.setParameter("titulo", nomeTitulo);
			return (TrabalhoArtistico) q.getSingleResult();

		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}

	@Override
	public TrabalhoArtistico recuperaPorAutor(String nomeAutor) {
		try {
			
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			List<TrabalhoArtistico> autores = em.createQuery("FROM Autor WHERE nome = :nomeParam")
					.setParameter("nomeParam", nomeAutor).getResultList();
			return autores.size() == 1 ? autores.get(0) : null;
			
		} catch (IllegalArgumentException | NoResultException e) {
			return null;
		}
	}

}
