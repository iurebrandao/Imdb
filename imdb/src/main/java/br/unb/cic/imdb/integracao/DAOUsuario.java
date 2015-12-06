package br.unb.cic.imdb.integracao;

import br.unb.cic.imdb.negocio.Usuario;


public interface DAOUsuario {

	public boolean inserir(Usuario usuario);
	
	public boolean remover(String login);
	
	public Usuario recuperarPorNome(String login);
	
}
