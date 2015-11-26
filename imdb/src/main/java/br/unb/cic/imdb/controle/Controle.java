package br.unb.cic.imdb.controle;
import br.unb.cic.imdb.integracao.jpa.DAOUsuarioJPA;
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
	
}
