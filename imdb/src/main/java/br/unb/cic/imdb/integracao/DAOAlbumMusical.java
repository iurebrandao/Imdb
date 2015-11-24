package br.unb.cic.imdb.integracao;

import java.util.List;

import br.unb.cic.imdb.negocio.FaixaMusical;

public interface DAOAlbumMusical {
	
	public void salvar(FaixaMusical faixa);
	public List<FaixaMusical> recuperaTodos();
	public FaixaMusical recuperaPorNome(String nomeFaixa);
}
