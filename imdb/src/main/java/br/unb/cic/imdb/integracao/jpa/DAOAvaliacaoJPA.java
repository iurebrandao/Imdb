package br.unb.cic.imdb.integracao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import br.unb.cic.imdb.integracao.DAOAvaliacao;
import br.unb.cic.imdb.negocio.Avaliacao;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;
import br.unb.cic.imdb.negocio.Usuario;

public class DAOAvaliacaoJPA implements DAOAvaliacao {
	
	private EntityManager em;
	
	@Override
	public boolean salvar(Avaliacao avaliacao) {
		try {
			em = EMFactoryHelper.instance().getFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(avaliacao);
			em.getTransaction().commit();
			return true;
		}catch(RollbackException e) {
			System.err.println("Falha ao enviar a avaliacao!");
			return false;
		}
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
