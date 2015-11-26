package br.unb.cic.imdb.interfacegrafica;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import javax.swing.*;

import br.unb.cic.imdb.controle.Controle;
import br.unb.cic.imdb.negocio.Usuario;


public class TelaCadastro extends JFrame{
	
	private Controle controle;
	private JTextField nome,login;
	private JLabel campoSenha,campoNascimento;
	private JPasswordField senha;
	private JButton enviar,voltar;
	private JComboBox box_data,box_mes,box_ano;
	private boolean valida = true;
	private String dataUsuario="01",mesUsuario="01",anoUsuario="1900",dataNascimento;
	private Date dataNasc;
	private final int tamData= 31,tamMes=12,tamAno=115;
	int i;
	private Font FonteUsual,FonteItalico;
	private Usuario usuario; // Armazena as informacoes do novo usuario

	public TelaCadastro(Controle controle){
		this.controle = controle;
		this.setLayout(null);
		this.setBounds(530, 140,400,370);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); 
		
		FonteUsual = new Font("Serif",Font.PLAIN,14); //Definir uma fonte e tamanho das letras
		FonteItalico = new Font("Serif",Font.ITALIC,14);
		
		String [] data = new String[tamData];
		String [] mes = new String[tamMes];
		String [] ano = new String[tamAno];
		
		usuario = new Usuario();
		
//		Criando os campos data, mes e ano
		for(i=1;i<=31;i++){
			data[i-1] = String.format("%d",i);
		}
		for(i=1;i<=12;i++){
			mes[i-1] = String.format("%d", i);
		}
		for(i=1900;i<=2014;i++){
			ano[i-1900] = String.format("%d",i);
		}
		
		nome = new JTextField("Nome Completo", 20);
		nome.setFont(FonteItalico);
		nome.setToolTipText("Insira aqui seu nome completo"); // Dicas que aparecem quando o passar o mouse por cima
		nome.setBounds(100,20,185,40); // Definindo o tamanho do campo e o lugar que ficarao no frame
		add(nome);

		login = new JTextField("Login", 20);
		login.setFont(FonteItalico);
		login.setToolTipText("Insira aqui seu login");
		login.setBounds(100,80,185,40);
		add(login);
		
		campoSenha = new JLabel("Senha:");
		campoSenha.setBounds(25,140,40,40);
		add(campoSenha);
		senha = new JPasswordField("****", 20);
		senha.setBounds(100,140,185,40);
		senha.setToolTipText("Digite sua senha"); // refazer com o pessoal de seguranca
		add(senha);
		
		campoNascimento = new JLabel(" Data de nascimento: ");
		campoNascimento.setBounds(5,200,170,40);
		add(campoNascimento);
		
		box_data = new JComboBox(data); 
		box_data.setMaximumRowCount(5);
//		Classe interna anonima
		box_data.addItemListener(new ItemListener(){
//			Trata evento de JComboBox para caso o usuario clique em uma das datas
			public void itemStateChanged (ItemEvent event){
//				determina o item selecionado
				if(event.getStateChange() == ItemEvent.SELECTED){
					dataUsuario = event.getItem().toString();
				}
			}
		});
		box_data.setBounds(135,200,43,40);
		add(box_data);

		box_mes = new JComboBox(mes); 
		box_mes.setMaximumRowCount(5);
//		Classe interna anonima
		box_mes.addItemListener(new ItemListener(){
//			Trata evento de JComboBox para caso o usuario clique em um dos meses
			public void itemStateChanged (ItemEvent event){
//				determina o item selecionado
				if(event.getStateChange() == ItemEvent.SELECTED){
					mesUsuario = event.getItem().toString();
				}
			}
		});
		box_mes.setBounds(180,200,43,40);
		add(box_mes);

		box_ano = new JComboBox(ano); 
		box_ano.setMaximumRowCount(5); 
//		Classe interna anonima
		box_ano.addItemListener(new ItemListener(){
//			Trata evento de JComboBox para caso o usuario clique em um dos anos
			public void itemStateChanged (ItemEvent event){
//				determina o item selecionado
				if(event.getStateChange() == ItemEvent.SELECTED){
					anoUsuario = event.getItem().toString();
				}
			}
		});
		box_ano.setBounds(225,200,60,40);
		add(box_ano);
		
		voltar = new JButton("Voltar");
		voltar.setBounds(90,270,80,30);
		add(voltar);
		
		enviar = new JButton("Fazer Cadastro");
		enviar.setBounds(195,270,130,30);
		add(enviar);

		// cria o manipulador de eventos de botao
		ButtonHandler handler = new ButtonHandler();
		// cria escutas para os botoes
		enviar.addActionListener(handler); 
		voltar.addActionListener(handler); 
		
//		Manipulador de evento de teclado (TAB)
		nome.addKeyListener( new myKeyListener() );
		nome.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		login.addKeyListener( new myKeyListener() );
		login.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		senha.addKeyListener( new myKeyListener() );
		senha.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		
		// Manipulador de evento de mouse
		ManipuladorMouse handler_mouse = new ManipuladorMouse();

//		Cria escutas para evento de mouse
		nome.addMouseListener(handler_mouse);
		login.addMouseListener(handler_mouse);
		senha.addMouseListener(handler_mouse);
		
	}
	
//	Classe interna que trata evento de teclado para caso o usuÃ¡rio apertar a tecla
//	TAB,vÃ¡ para o prÃ³ximo campo, limpe ele e deixe ele com a cor cinza.
	private class myKeyListener implements KeyListener { 
	      public void keyPressed( KeyEvent event ) {
	    
	    	  if ( event.getKeyCode() == KeyEvent.VK_TAB ) {
	    		  if(event.getSource() == nome){
	    			  nome.setBackground(Color.WHITE);
		    		  login.setText("");
		    		  login.setBackground(Color.LIGHT_GRAY);
		    		  login.setFont(FonteUsual);
		    		  login.requestFocus();
	    		  }
	    		  else if(event.getSource() == login){
	    			  login.setBackground(Color.WHITE);
	    			  senha.setText("");
		    		  senha.setBackground(Color.LIGHT_GRAY);
		    		  senha.setFont(FonteUsual);
		    		  senha.requestFocus();
	    		  }
		      }

	      }
	      public void keyTyped(KeyEvent event){}
	      public void keyReleased(KeyEvent event){}
	}
	
//	Classe de tratamento de botao para caso o usuario selecione um dos generos,
//	pegar qual dos dois generos foi selecionado. E para caso o usuario aperte
//	em enviar pegar todos os campos e verificar por meio de outras classes se 
//	todos os campos sÃ£o validos
	
	public class ButtonHandler implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
//			O usuario apertou em voltar
			if (event.getSource() == voltar) {
				TelaCadastro.this.dispose();
				new TelaInicial(controle);
			}
			
//			O usuario apertou em fazer cadastro
			if (event.getSource() == enviar) {

				dataNascimento = dataUsuario+"/"+mesUsuario+"/"+anoUsuario;
				SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
				try {
					dataNasc=sdf1.parse(dataNascimento);
				} catch (ParseException e) {
					valida = false;
				}
				usuario.setNome(nome.getText());
				usuario.setLogin(login.getText());
				usuario.setSenha(senha.getText());
				usuario.setDataNasc(dataNasc);
				
				if(usuario.getNome().equals("") || usuario.getNome().equals("Nome Completo")
					|| usuario.getSenha().equals("") || usuario.getSenha().equals("****")
					|| usuario.getLogin().equals("") || usuario.getLogin().equals("Login"))
					valida = false;   //Se a variavel booleana "valida", for true, entao o cadastro pode ser realizado
				
				if (valida) {
					TelaCadastro.this.dispose();
					// Envia a novo cadastro e notifica se foi enviado com sucesso
					
					if(controle.enviarCadastro(usuario)){
						JOptionPane.showMessageDialog(null, "Cadastro enviado com sucesso");
						new TelaLogin(controle);
					}
					else
						JOptionPane.showMessageDialog(null, "Falha ao enviar o cadastro");
				}

				//Se a variavel booleana "valida", for false, entao o cadastro nao pode ser realizado
				else{
						JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o cadastro. Tente novamente", "Cadastro",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	//	Classe interna para tratamento de mouse, para caso o usuario clicar em um
	//	dos campos, apagar o que tem no campo e ficar cinza no campo que ele estÃ¡ digitando. 
	private class ManipuladorMouse implements MouseListener {
		public void mousePressed(MouseEvent event) {
			
			nome.setBackground(Color.WHITE);
			login.setBackground(Color.WHITE);
			senha.setBackground(Color.WHITE);
			
			// Apagar o que tem escrito no campo nome e deixar cinza
			if (event.getSource() == nome){
				nome.setText("");
				nome.setBackground(Color.LIGHT_GRAY);
				nome.setFont(FonteUsual);
			}
			// Apagar o que tem escrito no campo login e deixar cinza
			else if (event.getSource() == login){
				login.setText("");
				login.setBackground(Color.LIGHT_GRAY);
				login.setFont(FonteUsual);
			}
			
			// Apagar o que tem escrito no campo senha e deixar cinza
			else if (event.getSource() == senha){
				senha.setText("");
				senha.setBackground(Color.LIGHT_GRAY);
				senha.setFont(FonteUsual);
			}
		}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
	}
}
