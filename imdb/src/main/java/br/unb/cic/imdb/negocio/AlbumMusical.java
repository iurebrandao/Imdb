package br.unb.cic.imdb.negocio;

import java.util.List;

public class AlbumMusical {
	
	private List<FaixaMusical> faixas;
	
	public AlbumMusical(){}
	
	public List<FaixaMusical> getfaixas(){
		return faixas;
	}
	
	public void setfaixas( List<FaixaMusical> faixas){
		this.faixas = faixas;
	}
}
