package br.unb.cic.imdb.controle;

import javax.swing.*;
import br.unb.cic.imdb.interfacegrafica.TelaLogin;

public class Main {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Erro ao definir modo de compatibilidade com a plataforma MAC OS");
		}
		mensagem();
		TelaLogin login = new TelaLogin();
	}
	
	public static void mensagem(){
		JOptionPane.showMessageDialog(null,"                          Bem-vindo ao IMDB!\n "
				+ "Para ter acesso ao programa, digite seu login e senha.");
	}
}
