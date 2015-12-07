package br.unb.cic.imdb.interfacegrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import br.unb.cic.imdb.controle.Controle;
import br.unb.cic.imdb.negocio.*;

public class TelaTrabalho extends JFrame {

	private ArrayList<JPanel> panelsEsq, panelsDir;
	private TrabalhoArtistico trabalho;
	private List<TrabalhoArtistico> listaDeTrabalhos;
	private List<Genero> generos;
	private Controle controle;
	private JPanel panelEsquerdo, panelDireito, PanelPrincipal, panelFundo;
	private JButton voltar;
	private ArrayList<JButton> avaliar,verAvaliacoes;
	private String nomeTrabalho;
	private Toolkit toolkit;
	private Dimension screensize;
	private Usuario usuario;

	public TelaTrabalho(Controle controle, String nomeTrabalho,Usuario usuario) {
		super("Trabalhos Artisticos");
		this.usuario = usuario;
		this.controle = controle;
		this.nomeTrabalho = nomeTrabalho;
		panelEsquerdo = new JPanel();
		panelDireito = new JPanel();
		panelFundo = new JPanel();
		panelsEsq = new ArrayList<>();
		panelsDir = new ArrayList<>();
		avaliar = new ArrayList<>();
		verAvaliacoes = new ArrayList<>();
		toolkit = Toolkit.getDefaultToolkit();
		screensize = toolkit.getScreenSize();
		generos = new ArrayList<>();
	}

	public void PanelPrincipal(int numTrabalhos) {

		this.setLayout(new BorderLayout());
		this.setBounds(500, 150, 400, 410);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(new BorderLayout());
		String string = "                                                             ";
		PanelPrincipal.add(new JLabel(string+"Lista de Trabalhos:"), BorderLayout.NORTH);
		PanelPrincipal.add(panelEsquerdo, BorderLayout.CENTER);
		PanelPrincipal.add(panelDireito, BorderLayout.EAST);
		PanelPrincipal.add(panelFundo, BorderLayout.SOUTH);
		
		JScrollPane p = new JScrollPane(PanelPrincipal);
		this.add(p, BorderLayout.CENTER);
		this.setVisible(true);
		if(numTrabalhos < 1)
			JOptionPane.showMessageDialog(null, "Sem trabalhos para mostrar!","Sem trabalhos",JOptionPane.WARNING_MESSAGE);
		else
			ajustaTela(numTrabalhos);
	}

	public void desenhaNaTela() {

		trabalho = controle.recuperaTrabPorTitulo(nomeTrabalho);
		if (trabalho != null) {
			adicionarTrabalhoNoPanel(0);
			voltar = new JButton("Voltar");

			panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.PAGE_AXIS));
			panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.PAGE_AXIS));

			panelFundo.setLayout(new FlowLayout());
			panelFundo.add(voltar);
			

			// Manipulador de evento de botao
			ButtonHandler handler = new ButtonHandler();
			voltar.addActionListener(handler);

			PanelPrincipal(1);
		}

		else {
			JOptionPane.showMessageDialog(null, "Trabalho artistico não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			TelaTrabalho.this.dispose();
		}
	}

	public void desenhaNaTelaTodos() {

		listaDeTrabalhos = controle.recuperaListaDeTrabs();
		if(listaDeTrabalhos.size() > 0){
			adicionarTrabalhoNoPanel(listaDeTrabalhos.size());		
		}
	
		voltar = new JButton("Voltar");

		panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.PAGE_AXIS));
		panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.PAGE_AXIS));

		panelFundo.setLayout(new FlowLayout());
		panelFundo.add(voltar);

		// Manipulador de evento de botao
		ButtonHandler handler = new ButtonHandler();
		voltar.addActionListener(handler);

		PanelPrincipal(listaDeTrabalhos.size());
		
	}

	public void adicionarTrabalhoNoPanel(int numero) {
		if(numero < 1){
			
			generos.add(0, trabalho.getGenero());
			
			String informacoes = "<html>";
			informacoes += "Titulo: "+trabalho.getTitulo() + "<br/>";
			informacoes += "Ano: "+trabalho.getAno() + "<br/>";
			informacoes += "Genero: "+generos.get(0).getTitulo() + "<br/>";
			informacoes += "</html>";
			JLabel informacaoTrab = new JLabel(informacoes);
			JPanel panelesq = new JPanel();
			panelesq.add(informacaoTrab);
			panelesq.setBorder(BorderFactory.createLineBorder(Color.white));
			panelesq.setBackground(SystemColor.activeCaption);
			panelEsquerdo.add(panelesq);
			
			JButton avaliar = new JButton("Avaliar");
			
			avaliar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaAvaliar telaAvaliar = new TelaAvaliar(controle,usuario);
					telaAvaliar.desenharTela();
				}
			});
			
			JButton verAvaliacoes = new JButton("Ver avaliacoes");
			
			verAvaliacoes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaVerAvaliacoes telaAvaliacoes = new TelaVerAvaliacoes(trabalho);
					telaAvaliacoes.desenhaNaTelaTodos();
				}
			});
			
			JPanel paneldir = new JPanel();
			paneldir.setLayout(new BorderLayout());
			paneldir.add( avaliar,BorderLayout.WEST);
			paneldir.add( verAvaliacoes,BorderLayout.EAST);
			panelDireito.add(paneldir);
		}
		
		else{
			int i = 0;
			for(TrabalhoArtistico arrayTrab:listaDeTrabalhos){
				generos.add(i,arrayTrab.getGenero());
				String informacoes = "<html>";
				informacoes += "Titulo: "+arrayTrab.getTitulo() + "<br/>";
				informacoes += "Ano: "+arrayTrab.getAno() + "<br/>";
				informacoes += "Genero: "+generos.get(i).getTitulo()+ "<br/>";
				informacoes += "</html>";
				JLabel informacaoTrab = new JLabel(informacoes);
				JPanel panelesq = new JPanel();
				panelesq.add(informacaoTrab);
				panelesq.setBorder(BorderFactory.createLineBorder(Color.white));
				panelesq.setBackground(SystemColor.activeCaption);
				panelsEsq.add(i,panelesq);
				
				JButton avaliarButton = new JButton("Avaliar");
				avaliar.add(i,avaliarButton);
				(avaliar.get(i)).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaAvaliar telaAvaliar = new TelaAvaliar(controle,usuario);
						telaAvaliar.desenharTela();
					}
				});
				
				JButton avaliacoesButton = new JButton("Ver avaliacoes");
				verAvaliacoes.add(i,avaliacoesButton);
				(verAvaliacoes.get(i)).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaVerAvaliacoes telaAvaliacoes = new TelaVerAvaliacoes(arrayTrab);
						telaAvaliacoes.desenhaNaTelaTodos();
					}
				});
				
				JPanel paneldir = new JPanel();
				paneldir.setLayout(new BorderLayout());
				paneldir.add(avaliar.get(i), BorderLayout.WEST);
				paneldir.add(verAvaliacoes.get(i), BorderLayout.EAST);
				panelsDir.add(i,paneldir);
				i++;
			}
			for (JPanel panel_aux : panelsEsq) {
				panelEsquerdo.add(panel_aux);
			}
			for (JPanel panel_aux : panelsDir) {
				panelDireito.add(panel_aux);
			}
		}
	}
	
	public void ajustaTela(int num_usuarios){
		if (num_usuarios <= 7) {
			if(num_usuarios <=2)
				this.setBounds(screensize.width / 3, screensize.height / 6, 600, 250);	
			else
				this.setBounds(screensize.width / 3, screensize.height / 6, 600, 200 + (num_usuarios * 50));
			this.repaint();
		}
		else{
			this.setBounds(screensize.width / 3, screensize.height / 30, 600, 700);
			this.repaint();
		}
	}
	
	private class ButtonHandler implements ActionListener {
		// Metodo que trata evento de botao
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == voltar) {
				TelaTrabalho.this.dispose();
			}
		}
	}
}
