package br.unb.cic.imdb.negocio;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TRABALHO_ARTISTICO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TrabalhoArtistico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trabalho_artistico_id")
	private Long id;
	@Column
	private String titulo;
	@Column
	private int ano;
	@ManyToOne
	private Genero genero;
	@ManyToOne
	private Autor autor;
	@ManyToOne
	private Avaliacao avaliacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Avaliacao getAvaliacaoT() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	

}
