package br.unb.cic.imdb.interfacegrafica;

import javax.swing.*;

import br.unb.cic.imdb.controle.Controle;

public class TelaAutor extends JFrame{
	private Controle controle;
	public TelaAutor(Controle controle){
		super("Autor");
		this.controle = controle;
		this.setBounds(500, 150, 400, 410);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
