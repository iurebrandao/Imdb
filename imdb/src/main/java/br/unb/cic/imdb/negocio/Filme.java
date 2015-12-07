package br.unb.cic.imdb.negocio;

import javax.persistence.*;

@Entity
@Table(name="tb_filme")
@AttributeOverrides({ 
	@AttributeOverride(name = "titulo", column = @Column),
	@AttributeOverride(name = "ano", column = @Column ),
	@AttributeOverride(name = "autor", column = @Column(name = "autor_id")),
	@AttributeOverride(name = "genero", column = @Column(name = "genero_id"))
})
public class Filme extends TrabalhoArtistico{
	@Column
	private Integer duracao;
	
	public Filme(){
		super();
	}
	
	public Filme(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

}