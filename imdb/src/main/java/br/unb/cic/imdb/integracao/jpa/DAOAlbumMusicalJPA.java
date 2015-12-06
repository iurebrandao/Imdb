package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import br.unb.cic.imdb.integracao.*;
import br.unb.cic.imdb.negocio.AlbumMusical;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;


public class DAOAlbumMusicalJPA implements DAOAlbumMusical{
	
	private DAOTrabalhoArtistico trabalhoArtisticoDAO;
	private EntityManager em;
	
	@Override
	@Transactional
	public void salvar(AlbumMusical album) {
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			trabalhoArtisticoDAO.salvar(album);
			em.getTransaction().commit();
			
		} catch (RollbackException e) {
			System.err.println("Nao foi possivel inserir o album!");
		}
	}

	@Override
	public List<AlbumMusical> recuperaTodos() {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		return em.createQuery("FROM AlbumMusical").getResultList();
	}

	@Override
	public AlbumMusical recuperaPorNome(String nomeAlbum) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<AlbumMusical> album = em.createQuery("FROM AlbumMusical WHERE titulo = :tituloParam")
				.setParameter("tituloParam", nomeAlbum).getResultList();
		return album.size() == 1 ? album.get(0) : null;
		
	}

	

}
