package br.unb.cic.imdb.controle;
import br.unb.cic.imdb.usuario.*;

public class Controle {
	
	private UsuarioDAO bancoDeDadosUsuario;
	
	public int realizarLogin(String login, String senha){
		System.out.println("Entrou");
		
		UsuarioDAO.comecarOperacoes();
		Usuario usuario = bancoDeDadosUsuario.recuperar(login);
		UsuarioDAO.finalizarOperacoes();
		if(usuario == null){
			return -1;
		} else if((usuario.getSenha().equals(senha))) {
			return 1;
		}
		return -1;
	}
}
