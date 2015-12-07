package br.unb.cic.imdb.negocio;


import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="TB_USUARIO")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Long id;
	@Column (nullable = false, unique = true)
	private String login;
	@Column (nullable = false, unique = true)
	private String senha;
	@Column
	private String nome;
	@Column
	private Date dataNasc;
	@OneToMany(mappedBy = "usuario")
	private List<Avaliacao> avaliacoes;

	
	public Usuario() {}

	public Usuario(String login, String senha, String nome, Date dataNasc) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.dataNasc = dataNasc;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
}
