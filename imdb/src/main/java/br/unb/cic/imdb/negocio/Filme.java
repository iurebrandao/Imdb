package br.unb.cic.imdb.negocio;

import javax.persistence.*;

@Entity
@Table(name="tb_filme")
public class Filme extends TrabalhoArtistico{
	@Column
	private Integer duracao;
	
	public Filme(){}
	
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