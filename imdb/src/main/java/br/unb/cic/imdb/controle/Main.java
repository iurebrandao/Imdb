package br.unb.cic.imdb.controle;

import javax.swing.*;
import br.unb.cic.imdb.interfacegrafica.*;

public class Main {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Erro ao definir modo de compatibilidade com a plataforma MAC OS");
		}
		Controle controle = new Controle();
		JOptionPane.showMessageDialog(null,"     Bem-vindo ao IMDB!");
		new TelaInicial(controle);
	}
	
}
