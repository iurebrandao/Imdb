package br.unb.cic.imdb.negocio;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="tb_album_musical")
@AttributeOverrides({ 
	@AttributeOverride(name = "titulo", column = @Column),
	@AttributeOverride(name = "ano", column = @Column ),
	@AttributeOverride(name = "autor", column = @Column(name = "autor_autor_id")),
	@AttributeOverride(name = "genero", column = @Column(name = "genero_genero_id"))
})
public class AlbumMusical extends TrabalhoArtistico{
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
