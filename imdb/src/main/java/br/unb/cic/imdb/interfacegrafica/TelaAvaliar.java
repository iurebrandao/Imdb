package br.unb.cic.imdb.interfacegrafica;

import javax.swing.JFrame;

import br.unb.cic.imdb.controle.Controle;
import br.unb.cic.imdb.integracao.jpa.DAOUsuarioJPA;
import br.unb.cic.imdb.negocio.Avaliacao;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;
import br.unb.cic.imdb.negocio.Usuario;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TelaAvaliar extends JFrame{
	
	private JTextArea comentario;
	private JButton aval1,aval2,aval3,aval4,aval5,enviar;
	private int avaliar=1;
	private Controle controle;
	private Avaliacao avaliacao;
	private Usuario usuario;
	private TrabalhoArtistico trabalho;
	
	
	public TelaAvaliar(Controle controle,Usuario usuario, TrabalhoArtistico trabalho){
		
		super("Avaliacao");
		this.usuario = usuario;
		this.controle = controle;
		this.trabalho = trabalho;
		this.setBounds(500,200, 350, 370);
		avaliacao = new Avaliacao();
	}
		
	public void desenharTela(){
		
		this.setLayout(null);
		JLabel notas = new JLabel("Avaliacao:");
		notas.setBounds(135,10,270,30);
		aval1 = new JButton("1");
		aval1.setBounds(20,50,50,35);
		aval1.setBackground(SystemColor.white);
		aval2 = new JButton("2");
		aval2.setBounds(80,50,50,35);
		aval2.setBackground(SystemColor.white);
		aval3 = new JButton("3");
		aval3.setBounds(140,50,50,35);
		aval3.setBackground(SystemColor.white);
		aval4 = new JButton("4");
		aval4.setBounds(200,50,50,35);
		aval4.setBackground(SystemColor.white);
		aval5 = new JButton("5");
		aval5.setBounds(260,50,50,35);
		aval5.setBackground(SystemColor.white);
		JLabel comentar = new JLabel("Escreva um comentario logo abaixo:");
		comentar.setBounds(60,100,270,30);
		comentario = new JTextArea();
		comentario.setBounds(20,150,290,100);
		
		enviar = new JButton("Enviar");
		enviar.setBounds(130, 270, 80, 35);
		
		this.add(notas);
		this.add(aval1);
		this.add(aval2);
		this.add(aval3);
		this.add(aval4);
		this.add(aval5);
		this.add(comentar);
		this.add(comentario);
		this.add(enviar);
		
		ButtonHandler handler = new ButtonHandler();
		aval1.addActionListener(handler);
		aval2.addActionListener(handler);
		aval3.addActionListener(handler);
		aval4.addActionListener(handler);
		aval5.addActionListener(handler);
		
		enviar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				avaliacao.setAvalicao(avaliar);
				avaliacao.setComentario(comentario.getText());
				avaliacao.setTrabalhoArtistico(trabalho);
				avaliacao.setUsuario(usuario);
				if(controle.salvarAvaliacao(avaliacao))
					JOptionPane.showMessageDialog(null, "Comentario e avaliacao enviados com sucesso!");
				else
					JOptionPane.showMessageDialog(null, "Erro ao enviar comentario e avaliacao!","Erro",JOptionPane.ERROR_MESSAGE);
				TelaAvaliar.this.dispose();
			}
		});
		this.setVisible(true);
	}
	
	public class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			aval1.setBackground(SystemColor.white);
			aval2.setBackground(SystemColor.white);
			aval3.setBackground(SystemColor.white);
			aval4.setBackground(SystemColor.white);
			aval5.setBackground(SystemColor.white);
			if (event.getSource() == aval1) {
				avaliar=1;
				aval1.setBackground(SystemColor.green);
			}
			if (event.getSource() == aval2) {
				avaliar=2;
				aval2.setBackground(SystemColor.green);
			}
			if (event.getSource() == aval3) {
				avaliar=3;
				aval3.setBackground(SystemColor.green);
			}
			if (event.getSource() == aval4) {
				avaliar = 4;
				aval4.setBackground(SystemColor.green);
			}
			if (event.getSource() == aval5) {
				avaliar=5;
				aval5.setBackground(SystemColor.green);
			}
		}
	}
}

