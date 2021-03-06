package br.unb.cic.imdb.interfacegrafica;

import br.unb.cic.imdb.controle.Controle;
import br.unb.cic.imdb.negocio.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;


public class TelaUsuario extends JFrame{
	
	private Controle controle;
	private JTextField trabalhoArtistico,autor,genero;
	private JLabel pesquisar;
	private JButton pesquisarTrab,pesquisarAutor,pesquisarGenero,sairDaConta,sairPrograma;
	private JButton listarTrab,listarAutor,listarGenero;
	private Usuario usuario;
	
	public TelaUsuario(Controle controle,Usuario usuario){
		super("Tela usuario");
		this.controle = controle;
		this.usuario = usuario;
		this.setLayout(null);
		this.setBounds(500, 150, 400, 410);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		String espaco = "                        ";
		JOptionPane.showMessageDialog(null, espaco+"Login realizado! \n","Usuario",JOptionPane.DEFAULT_OPTION);
		this.setVisible(true);
		
		pesquisar = new JLabel("Pesquise logo abaixo o trabalho artístico desejado ou liste todos:");
		pesquisar.setBounds(10,5,370,70);
		add(pesquisar);
		
		trabalhoArtistico = new JTextField("Trabalho Artistico");
		trabalhoArtistico.setBounds(10,70,170,35);
		trabalhoArtistico.setToolTipText("Pesquise o trabalho artistico desejado");
		add(trabalhoArtistico);
		
		pesquisarTrab = new JButton("Pesquisar");
		pesquisarTrab.setBounds(181,70,98,35);
		add(pesquisarTrab);
		
		listarTrab = new JButton("Listar todos");
		listarTrab.setBounds(280,70,103,35);
		add(listarTrab);
		
		pesquisar = new JLabel("Pesquise logo abaixo o autor desejado ou liste todos:");
		pesquisar.setBounds(40,100,370,70);
		add(pesquisar);
		
		autor = new JTextField("Autor");
		autor.setBounds(10,160,170,35);
		autor.setToolTipText("Pesquise o autor desejado");
		add(autor);
		
		pesquisarAutor = new JButton("Pesquisar");
		pesquisarAutor.setBounds(181,160,98,35);
		add(pesquisarAutor);
		
		listarAutor = new JButton("Listar todos");
		listarAutor.setBounds(280,160,103,35);
		add(listarAutor);
		
		pesquisar = new JLabel("Pesquise logo abaixo o genero desejado ou liste todos:");
		pesquisar.setBounds(40,190,370,70);
		add(pesquisar);
		
		genero = new JTextField("Genero");
		genero.setBounds(10,250,170,35);
		genero.setToolTipText("Pesquise o genero desejado");
		add(genero);
		
		pesquisarGenero = new JButton("Pesquisar");
		pesquisarGenero.setBounds(181,250,98,35);
		add(pesquisarGenero);
		
		listarGenero = new JButton("Listar todos");
		listarGenero.setBounds(280,250,103,35);
		add(listarGenero);
		
		sairDaConta = new JButton("Sair da Conta");
		sairDaConta.setBounds(25,320,130,35);
		add(sairDaConta);
		
		sairPrograma = new JButton("Sair do Programa");
		sairPrograma.setBounds(230,320,140,35);
		add(sairPrograma);
		
		ButtonHandler handler = new ButtonHandler();
		sairDaConta.addActionListener(handler);
		sairPrograma.addActionListener(handler);
		listarTrab.addActionListener(handler);
		listarAutor.addActionListener(handler);
		listarGenero.addActionListener(handler);
		pesquisarTrab.addActionListener(handler);
		pesquisarAutor.addActionListener(handler);
		pesquisarGenero.addActionListener(handler);
		
		ManipuladorMouse handler_mouse = new ManipuladorMouse();
		trabalhoArtistico.addMouseListener(handler_mouse);
		autor.addMouseListener(handler_mouse);
		genero.addMouseListener(handler_mouse);
		
		trabalhoArtistico.addKeyListener( new myKeyListener() );
		trabalhoArtistico.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		autor.addKeyListener( new myKeyListener() );
		autor.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		genero.addKeyListener( new myKeyListener() );
		genero.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		
	}
	
	public class ButtonHandler implements ActionListener { 
		public void actionPerformed(ActionEvent event) {

			// Pesquisar trabalhos artisticos			
			if(event.getSource() == pesquisarTrab){
				System.out.println(trabalhoArtistico.getText());
				TelaTrabalho telaTrab = new TelaTrabalho(controle,trabalhoArtistico.getText(),usuario);
				telaTrab.desenhaNaTela();
			}
			
			if(event.getSource() == listarTrab){
				TelaTrabalho telaTrab = new TelaTrabalho(controle,trabalhoArtistico.getText(),usuario);
				telaTrab.desenhaNaTelaTodos();
			}
			
			// Pesquisar autores			
			if(event.getSource() == pesquisarAutor){
				System.out.println(autor.getText());
				TelaAutor telaTrab = new TelaAutor(controle,autor.getText());
				telaTrab.desenhaNaTela();
			}
			if(event.getSource() == listarAutor){
				TelaAutor telaAutor = new TelaAutor(controle,autor.getText());
				telaAutor.desenhaNaTelaTodos();
			}
			
			//Pesquisar generos			
			if(event.getSource() == pesquisarGenero){
				System.out.println(genero.getText());
				TelaGenero telaGenero= new TelaGenero(controle,genero.getText());
				telaGenero.desenhaNaTela();
			}
			if(event.getSource() == listarGenero){
				TelaGenero telaGenero = new TelaGenero(controle,genero.getText());
				telaGenero.desenhaNaTelaTodos();
			}
			
			if (event.getSource() == sairDaConta) {
				TelaUsuario.this.dispose();
				new TelaLogin(controle);
			}
			if(event.getSource() == sairPrograma){
				System.exit(0);
			}
		}
	}
	
	private class ManipuladorMouse implements MouseListener {
		public void mousePressed(MouseEvent event) {
			
			trabalhoArtistico.setBackground(Color.WHITE);
			autor.setBackground(Color.WHITE);
			genero.setBackground(Color.WHITE);
			
			if (event.getSource() == trabalhoArtistico){
				trabalhoArtistico.setText("");
				trabalhoArtistico.setBackground(Color.LIGHT_GRAY);
			}
			
			if (event.getSource() == autor){
				autor.setText("");
				autor.setBackground(Color.LIGHT_GRAY);
			}
			if (event.getSource() == genero){
				genero.setText("");
				genero.setBackground(Color.LIGHT_GRAY);
			}
		}
		public void mouseClicked(MouseEvent arg0){}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	private class myKeyListener implements KeyListener { 
	      public void keyPressed( KeyEvent event ) {
	    
	    	  if ( event.getKeyCode() == KeyEvent.VK_TAB ) {
	    		  if(event.getSource() == trabalhoArtistico){
	    			  trabalhoArtistico.setBackground(Color.WHITE);
		    		  autor.setText("");
		    		  autor.setBackground(Color.LIGHT_GRAY);
		    		  autor.requestFocus();
	    		  }
	    		  else if(event.getSource() == autor){
	    			  autor.setBackground(Color.WHITE);
	    			  genero.setText("");
		    		  genero.setBackground(Color.LIGHT_GRAY);
		    		  genero.requestFocus();
	    		  }
	    	  }
	      }

		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {}
	}
}
