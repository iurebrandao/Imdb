package br.unb.cic.imdb.integracao;

import java.util.List;

import br.unb.cic.imdb.negocio.AlbumMusical;
import br.unb.cic.imdb.negocio.FaixaMusical;

public interface DAOAlbumMusical {
	
	public void salvar(AlbumMusical album);
	public List<AlbumMusical> recuperaTodos();
	public AlbumMusical recuperaPorNome(String nomeAlbum);
}
