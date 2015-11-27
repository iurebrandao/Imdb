package br.unb.cic.imdb.interfacegrafica;

import javax.swing.*;

import br.unb.cic.imdb.controle.Controle;

public class TelaGenero extends JFrame {
	
	private Controle controle;
	public TelaGenero(Controle controle){
		super("Genero");
		this.controle = controle;
		this.setBounds(500, 150, 400, 410);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
