package br.unb.cic.imdb.interfacegrafica;

import br.unb.cic.imdb.controle.Controle;
import javax.swing.*;


public class TelaUsuario extends JFrame{
	
	private Controle controle;
	public TelaUsuario(Controle controle){
		super("Tela usuario");
		this.controle = controle;
		this.setLayout(null);
		this.setBounds(500, 200, 300, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		String espaco = "                        ";
		JOptionPane.showMessageDialog(null, espaco+"Login realizado! \n","Usuario",JOptionPane.DEFAULT_OPTION);
		this.setVisible(true);
		
	}
}
