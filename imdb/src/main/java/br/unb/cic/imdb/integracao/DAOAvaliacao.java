package br.unb.cic.imdb.integracao;

import br.unb.cic.imdb.negocio.Avaliacao;

public interface DAOAvaliacao {

	public void salvar(Avaliacao avaliacao);
	public Avaliacao recuperarPorUsuario();
	public Avaliacao recuperarPorTrabalhoArtistico();
	
	
}
