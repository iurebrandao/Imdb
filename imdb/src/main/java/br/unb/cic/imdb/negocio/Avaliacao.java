package br.unb.cic.imdb.negocio;

import java.util.List;

import javax.persistence.*;



@Entity
@Table(name="tb_avaliacao")
public class Avaliacao {
	@Id
	@GeneratedValue
	@Column(name="avaliacao_id")
	private Long id;
	@Column
	private Integer avalicao;
	@Column
	private String comentario;
	@OneToMany(mappedBy = "avaliacao")
	private List<Usuario> usuario;
	@OneToMany(mappedBy = "avaliacao")
	private List<TrabalhoArtistico> trabalho;
	
	public Avaliacao(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAvalicao() {
		return avalicao;
	}

	public void setAvalicao(Integer avalicao) {
		this.avalicao = avalicao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public List<TrabalhoArtistico> getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(List<TrabalhoArtistico> trabalho) {
		this.trabalho = trabalho;
	}

}
