package br.unb.cic.imdb.interfacegrafica;

import br.unb.cic.imdb.controle.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.*;

public class TelaLogin extends JFrame {

	private JLabel campoLogin, campoSenha;
	private JTextField Login;
	private JPasswordField Senha;
	private JButton fazerLogin,voltar;
	private Controle controle;
	private String login,senha;
	
	public TelaLogin(Controle controle) {

		super("Login");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(500, 200,320,230); // Posicionar o layout no lugar desejado da tela
		this.controle = controle;
		
		campoLogin = new JLabel("Login: ");
		campoLogin.setBounds(20, 1, 70, 70);

		add(campoLogin);
		// Constroi o Login com 10 colunas
		Login = new JTextField("", 13);
		Login.setBorder(BorderFactory.createLineBorder(Color.red));
		Login.setBounds(80, 20, 200, 30);

		add(Login); // Adiciona Login1 ao JFrame
		Login.setToolTipText("Insira o seu login");

		campoSenha = new JLabel("Senha: ");
		campoSenha.setBounds(20, 42, 70, 70);
		add(campoSenha);
		Senha = new JPasswordField("", 10);
		Senha.setBorder(BorderFactory.createLineBorder(Color.gray));
		Senha.setBounds(80, 60, 200, 30);
		add(Senha);
		Senha.setToolTipText("Insira a sua senha");
		
		voltar = new JButton("Voltar");
		voltar.setBounds(25,120,80,35);
		add(voltar);
		
		fazerLogin = new JButton("Fazer Login");
		fazerLogin.setBounds(125, 120, 150, 35);
		add(fazerLogin);

		// Manipulador de evento de mouse
		ManipuladorMouse handler_mouse = new ManipuladorMouse();
		Senha.addMouseListener(handler_mouse);
		Login.addMouseListener(handler_mouse);

		// Manipulador de evento de botao
		ButtonHandler handler_botao = new ButtonHandler();
		fazerLogin.addActionListener(handler_botao);
		voltar.addActionListener(handler_botao);
		
		// Manipulador de teclado
		Login.addKeyListener(new myKeyListener());
		Login.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		Senha.addKeyListener(new myKeyListener());
		Senha.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

		
	}

	// Classe interna para tratamento de mouse, para caso o usuario clicar em um
	// dos campos, a borda do campo em que ele est� digitando ficar vermelha.
	private class ManipuladorMouse implements MouseListener {
		public void mousePressed(MouseEvent event) {
			// Caso o usuario clique em Login, a borda desse campo fica
			// vermelha.
			if (event.getSource() == Login) {
				Login.setText("");
				Login.setBorder(BorderFactory.createLineBorder(Color.red));
				Senha.setBorder(BorderFactory.createLineBorder(Color.gray));
			}
			// Caso o usuario clique em Senha, a borda desse campo fica
			// vermelha.
			else if (event.getSource() == Senha) {
				Senha.setText("");
				Login.setBorder(BorderFactory.createLineBorder(Color.gray));
				Senha.setBorder(BorderFactory.createLineBorder(Color.red));
			}
		}

		public void mouseEntered(MouseEvent event) {
		}

		public void mouseExited(MouseEvent event) {
		}

		public void mouseClicked(MouseEvent event) {
		}

		public void mouseReleased(MouseEvent event) {
		}
	}

	// Classe interna que trata evento de teclado para caso o usuario apertar
	// a tecla TAB, va para o campo Senha e a borda desse campo fique vermelha.
	private class myKeyListener implements KeyListener {
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_TAB) {
				if (event.getSource() == Login) {
					Login.setBorder(BorderFactory.createLineBorder(Color.gray));
					Senha.setBorder(BorderFactory.createLineBorder(Color.red));
					Senha.requestFocus();
				}
			}
		}

		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
		}
	}

	// Classe interna para tratamento de evento de botao para caso o usuario
	// clique
	// em fazer Login, receber o que ele digitou nos campos Login e Senha
	private class ButtonHandler implements ActionListener {
		// trata evento de botao
		public void actionPerformed(ActionEvent event) {
//			O usuario apertou em voltar
			if (event.getSource() == voltar) {
				TelaLogin.this.dispose();
				new TelaInicial(controle);
			}
			
			if (event.getSource() == fazerLogin) {

				login = Login.getText();
				senha = Senha.getText();
				System.out.println("login = "+login);
				System.out.println("senha = "+senha);
				
				JanelaOpcao(controle.realizarLogin(login,senha));
				//JanelaOpcao(1);
			}
		}
	}

	// Metodo, que a partir da opcao de login que o controle retornar, mostra a
	// mensagem
	// adequada ou executa o login do usuario
	public void JanelaOpcao(int opcao) {
		switch (opcao) {

		// Mostra que o login e/ou senha est�o incorretos e mantem a janela do login
		case -1:
			JOptionPane.showMessageDialog(null, "Login e/ou senha incorreto(s). Nao foi possivel efetuar o login",
					"Erro", JOptionPane.ERROR_MESSAGE);
			break;

		// Abre a janela de usuario e fecha a do Login
		case 1:
			TelaLogin.this.dispose();
			new TelaUsuario(controle);
			break;
			
		default:
			break;
		}
	}
}