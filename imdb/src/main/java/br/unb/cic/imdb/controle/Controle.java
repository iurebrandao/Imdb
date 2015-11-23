package br.unb.cic.imdb.controle;
import br.unb.cic.imdb.integracao.jpa.DAOUsuarioJPA;
import br.unb.cic.imdb.negocio.Usuario;

public class Controle {
	
	private DAOUsuarioJPA bancoDeDadosUsuario;
	
	public int realizarLogin(String login, String senha){
		
		DAOUsuarioJPA.comecarOperacoes();
		Usuario usuario = bancoDeDadosUsuario.recuperar(login);
		DAOUsuarioJPA.finalizarOperacoes();
		if(usuario == null){
			return -1;
		} else if((usuario.getSenha().equals(senha))) {
			return 1;
		}
		return -1;
	}
}
