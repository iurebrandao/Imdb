package br.unb.cic.imdb.interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.unb.cic.imdb.controle.Controle;


public class TelaInicial extends JFrame{
	private JButton possui_cadastro; //botao que seleciona se a pessoa possui cadastro
	private JButton nao_possui_cadastro;
	private JLabel selecione;
	private JOptionPane frame;
	private Controle controle;
	
	public TelaInicial(Controle controle){
		super("Opcoes de usuario");
		this.controle = controle;
		this.setLayout(null);
		this.setLocation(450,200);  //Posicionar o layout no lugar desejado da tela
		
	
		selecione = new JLabel("Selecione uma das opcoes:");
		selecione.setBounds(170, 20, 300, 30);
		add(selecione);
		
		possui_cadastro = new JButton("Fazer Login");
		possui_cadastro.setBounds(40, 70, 200, 70);
		add(possui_cadastro);
		
		nao_possui_cadastro = new JButton("Fazer cadastro");
		nao_possui_cadastro.setBounds(250, 70, 200, 70);
		add(nao_possui_cadastro);
		
		ButtonHandler handler = new ButtonHandler();
		nao_possui_cadastro.addActionListener(handler);
		possui_cadastro.addActionListener(handler);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,200);
		this.setVisible(true);
	}
	
/*	Classe interna para tratamento de evento de botao para caso a pessoa clique 
 *  em um dos botoes "Fazer Login" ou "Fazer cadastro".
 */
	private class ButtonHandler implements ActionListener{
//		Metodo que trata evento de botao
		public void actionPerformed(ActionEvent event){
			//Fecha a janela com as opcoes de fazer login ou cadastro
			TelaInicial.this.dispose();	
			if(event.getSource() == possui_cadastro ){
				
				new TelaLogin(controle);
			}	
//			A pessoa clicou em "Requisitar cadastro"
			if(event.getSource() == nao_possui_cadastro ){

//				Fecha a janela com as opcoes de ja possuir ou requisitar cadastro
				
//				Cria um frame da classe Cadastro_Usuario, classe que mostra a janela de cadastro
				new TelaCadastro(controle); 
			}
		}
	}
}
