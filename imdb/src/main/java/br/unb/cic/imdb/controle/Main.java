package br.unb.cic.imdb.controle;

import javax.swing.UIManager;
import br.unb.cic.imdb.interfacegrafica.TelaLogin;

public class Main {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Erro ao definir modo de compatibilidade com a plataforma MAC OS");
		}
		
		TelaLogin login = new TelaLogin();
	}
}
