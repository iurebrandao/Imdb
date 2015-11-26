package br.unb.cic.imdb.integracao;

import br.unb.cic.imdb.negocio.Usuario;

/**
 * Define os metodos de manipulacao de um usuario na camada de persistencia
 * 
 * @author victordantas
 *
 */

public interface DAOUsuario {

	public boolean inserir(Usuario usuario);
	
	public boolean remover(String login);
	
	public Usuario recuperarPorNome(String login);
	
}
