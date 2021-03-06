package br.unb.cic.imdb.negocio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transaction;

@Entity
@Table(name = "TB_GENERO")
public class Genero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String titulo;
	@Column
	private String descricao;
	@OneToMany(mappedBy = "genero")
	private List<TrabalhoArtistico> trabalhoArtistico;
	@OneToMany
	@JoinColumn(name= "genero_id")
	private List<Filme> filmes;
	
	public Genero(){
	}

	public Genero(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<TrabalhoArtistico> getTrabalhoArtistico() {
		return trabalhoArtistico;
	}

	public void setTrabalhoArtistico(List<TrabalhoArtistico> trabalhoArtistico) {
		this.trabalhoArtistico = trabalhoArtistico;
	}
	
	public void setFilme(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
	public List<Filme> getFilme() {
		return filmes;
	}
}
