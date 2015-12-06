package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import br.unb.cic.imdb.integracao.DAOAvaliacao;
import br.unb.cic.imdb.negocio.Avaliacao;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;
import br.unb.cic.imdb.negocio.Usuario;

public class DAOAvaliacaoJPA implements DAOAvaliacao {
	
	private EntityManager em;
	
	@Override
	public void salvar(Avaliacao avaliacao) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(avaliacao);
		em.getTransaction().commit();
	}

	@Override
	public Avaliacao recuperarPorUsuario(Usuario usuario) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<Avaliacao> avaliacaoUsuarios = em.createQuery("FROM Usuario WHERE avaliacao = :nomeParam")
				.setParameter("nomeParam", usuario.getNome()).getResultList();
		return avaliacaoUsuarios.size() == 1 ? avaliacaoUsuarios.get(0) : null;
	}

	@Override
	public Avaliacao recuperarPorTrabalhoArtistico(TrabalhoArtistico trabalho) {
		em = EMFactoryHelper.instance().getFactory().createEntityManager();
		List<Avaliacao> avaliacaoUsuarios = em.createQuery("FROM TrabalhoArtistico WHERE avaliacao = :tituloParam")
				.setParameter("nomeParam", trabalho.getTitulo()).getResultList();
		return avaliacaoUsuarios.size() == 1 ? avaliacaoUsuarios.get(0) : null;
	}

}
