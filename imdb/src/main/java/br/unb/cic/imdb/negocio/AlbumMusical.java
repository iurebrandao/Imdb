package br.unb.cic.imdb.negocio;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="tb_album_musical")
public class AlbumMusical extends TrabalhoArtistico {
	@OneToMany(mappedBy = "album_musical")
	private List<FaixaMusical> faixas;
	
	public AlbumMusical(){}
	
	public List<FaixaMusical> getfaixas(){
		return faixas;
	}

	public List<FaixaMusical> getFaixas() {
		return faixas;
	}

	public void setFaixas(List<FaixaMusical> faixas) {
		this.faixas = faixas;
	}
	
	
}
