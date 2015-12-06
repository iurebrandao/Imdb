package br.unb.cic.imdb.integracao;

import br.unb.cic.imdb.negocio.Avaliacao;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;
import br.unb.cic.imdb.negocio.Usuario;

public interface DAOAvaliacao {

	public void salvar(Avaliacao avaliacao);
	public Avaliacao recuperarPorUsuario(Usuario usuario);
	public Avaliacao recuperarPorTrabalhoArtistico(TrabalhoArtistico trabalho);
	
	
}
