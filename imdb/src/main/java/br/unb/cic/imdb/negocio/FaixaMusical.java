package br.unb.cic.imdb.negocio;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="tb_faixa_musical")
public class FaixaMusical{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="faixa_musica_id")
	private Long id;
	@Column
	private String titulo;
	@Column
	private Integer duracao;
	@ManyToOne
	private AlbumMusical album_musical;


	public FaixaMusical(Long id, String titulo, Integer duracao) {
		this.id = id;
		this.titulo = titulo;
		this.duracao = duracao;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
}
