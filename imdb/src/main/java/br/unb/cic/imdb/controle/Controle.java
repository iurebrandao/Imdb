package br.unb.cic.imdb.controle;
import java.util.List;

import br.unb.cic.imdb.integracao.jpa.DAOAutorJPA;
import br.unb.cic.imdb.integracao.jpa.DAOAvaliacaoJPA;
import br.unb.cic.imdb.integracao.jpa.DAOGeneroJPA;
import br.unb.cic.imdb.integracao.jpa.DAOTrabalhoArtisticoJPA;
import br.unb.cic.imdb.integracao.jpa.DAOUsuarioJPA;
import br.unb.cic.imdb.negocio.Autor;
import br.unb.cic.imdb.negocio.Avaliacao;
import br.unb.cic.imdb.negocio.Genero;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;
import br.unb.cic.imdb.negocio.Usuario;

public class Controle {
	
	private DAOUsuarioJPA bancoDeDadosUsuario = new DAOUsuarioJPA();
	
	public int realizarLogin(String login, String senha){
		
		Usuario usuario = bancoDeDadosUsuario.recuperarPorNome(login);
		if(usuario == null){
			return -1;
		} else if((usuario.getSenha().equals(senha))) {
			return 1;
		}
		return -1;
	}
	
	public boolean enviarCadastro(Usuario novoUsuario){
		
		System.out.println(novoUsuario.getNome() + novoUsuario.getLogin() + novoUsuario.getSenha() + novoUsuario.getDataNasc());
		boolean sucesso = bancoDeDadosUsuario.inserir(novoUsuario);
		
		return sucesso;
	}
	
	public Usuario recuperarUsuarioPorLogin(String login){
		return bancoDeDadosUsuario.recuperarPorNome(login);
	}
	
	public TrabalhoArtistico recuperaTrabPorTitulo(String nome){
		DAOTrabalhoArtisticoJPA trabJPA = new DAOTrabalhoArtisticoJPA();
		return trabJPA.recuperaPorTitulo(nome);
	}
	
	public List<TrabalhoArtistico> recuperaListaDeTrabs(){
		DAOTrabalhoArtisticoJPA trabJPA = new DAOTrabalhoArtisticoJPA();
		return trabJPA.recuperaTodos();
	}
	public List<Autor> recuperaListaDeAutores(){
		DAOAutorJPA autorJPA = new DAOAutorJPA();
		return autorJPA.recuperaTodos();
	}
	public Autor recuperaAutorPorTitulo(String nomeAutor){
		DAOAutorJPA autorJPA = new DAOAutorJPA();
		return autorJPA.recuperaPorNome(nomeAutor);
	}
	public List<Genero> recuperaListaDeGeneros(){
		DAOGeneroJPA generoJPA = new DAOGeneroJPA();
		return generoJPA.recuperaTodos();
	}
	public Genero recuperaGeneroPorTitulo(String nomeGenero){
		DAOGeneroJPA generoJPA = new DAOGeneroJPA();
		return generoJPA.recuperaPorTitulo(nomeGenero);
	}
	
	public boolean salvarAvaliacao(Avaliacao avaliacao){
		DAOAvaliacaoJPA avaliacaoJPA = new DAOAvaliacaoJPA();
		return avaliacaoJPA.salvar(avaliacao);	
	}
	
	public Long recuperarIdUsuario(String login){
		return bancoDeDadosUsuario.recuperarId(login);
	}
}
