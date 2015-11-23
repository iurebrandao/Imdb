package br.unb.cic.imdb.usuario;

import java.sql.Date;
import javax.persistence.*;

@Entity(name = "Usuario")
public class Usuario {
	
	@Id
	@Column(name="login", nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String dataNascimento;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha(){
		return senha;
	}
	public void setSenha(String senha){
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento(){
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento){
		this.dataNascimento = dataNascimento;
	}
}
