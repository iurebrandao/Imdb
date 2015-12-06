package br.unb.cic.imdb.integracao;

import java.util.List;

import br.unb.cic.imdb.negocio.Filme;

public interface DAOFilme {

	public void salvar(Filme filme);
	public Filme recuperarPorTitulo(String titulo);
	public List<Filme> recuperarTodos();

}
