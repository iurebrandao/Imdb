package br.unb.cic.imdb.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import br.unb.cic.imdb.controle.Controle;
import br.unb.cic.imdb.negocio.Autor;
import br.unb.cic.imdb.negocio.Filme;
import br.unb.cic.imdb.negocio.Genero;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;

public class TelaGenero extends JFrame {
	
	private String nomeGenero;
	private JPanel panelEsquerdo, panelDireito, PanelPrincipal, panelFundo;
	private JButton voltar;
	private ArrayList<JPanel> panelsEsq, panelsDir;
	private Controle controle;
	private Genero genero;
	private List<Genero> listaDeGeneros;
	private List<TrabalhoArtistico> listaDeTrabalhos;
	private List<Filme> listaDeFilmes;
	private Toolkit toolkit;
	private Dimension screensize;
	
	public TelaGenero(Controle controle,String nomeGenero){
		super("Genero");
		this.nomeGenero = nomeGenero;
		this.controle = controle;
		panelEsquerdo = new JPanel();
		panelDireito = new JPanel();
		panelFundo = new JPanel();
		panelsEsq = new ArrayList<>();
		panelsDir = new ArrayList<>();
		toolkit = Toolkit.getDefaultToolkit();
		screensize = toolkit.getScreenSize();
	}
	
	public void PanelPrincipal(int numGeneros) {

		this.setLayout(new BorderLayout());
		this.setBounds(500, 150, 400, 410);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(new BorderLayout());
		String string = "                                                             ";
		PanelPrincipal.add(new JLabel(string+"Lista de Generos:"), BorderLayout.NORTH);
		PanelPrincipal.add(panelEsquerdo, BorderLayout.CENTER);
		PanelPrincipal.add(panelDireito, BorderLayout.EAST);
		PanelPrincipal.add(panelFundo, BorderLayout.SOUTH);
		
		JScrollPane p = new JScrollPane(PanelPrincipal);
		this.add(p, BorderLayout.CENTER);
		this.setVisible(true);
		if(numGeneros < 1)
			JOptionPane.showMessageDialog(null, "Sem generos para mostrar!","Sem generos",JOptionPane.WARNING_MESSAGE);
		else
			ajustaTela(numGeneros);

	}

	public void desenhaNaTela() {

		genero = controle.recuperaGeneroPorTitulo(nomeGenero);
		if (genero != null) {
			adicionarGeneroNoPanel(0);
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
			JOptionPane.showMessageDialog(null, "Genero não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			TelaGenero.this.dispose();
		}
	}

	public void desenhaNaTelaTodos() {

		listaDeGeneros = controle.recuperaListaDeGeneros();
		if(listaDeGeneros.size() > 0){
			adicionarGeneroNoPanel(listaDeGeneros.size());		
		}
	
		voltar = new JButton("Voltar");

		panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.PAGE_AXIS));
		panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.PAGE_AXIS));

		panelFundo.setLayout(new FlowLayout());
		panelFundo.add(voltar);

		// Manipulador de evento de botao
		ButtonHandler handler = new ButtonHandler();
		voltar.addActionListener(handler);

		PanelPrincipal(listaDeGeneros.size());
		
	}

	public void adicionarGeneroNoPanel(int numero) {
		if(numero < 1){
			String informacoes = "<html>";
			informacoes += "Genero: "+genero.getTitulo() + "<br/>";
			informacoes += "Descricao: "+genero.getDescricao() + "<br/>";
			informacoes += "</html>";
			JLabel informacaoAutor = new JLabel(informacoes);
			JPanel panelesq = new JPanel();
			panelesq.setBorder(BorderFactory.createLineBorder(Color.white));
			panelesq.setBackground(SystemColor.activeCaption);
			panelesq.add(informacaoAutor);
			panelEsquerdo.add(panelesq);
		}
		
		else{
			int i = 0;
			for(Genero arrayGeneros:listaDeGeneros){
				
				String informacoes = "<html>";
				informacoes += "Genero: "+arrayGeneros.getTitulo() + "<br/>";
				informacoes += "Descricao: "+arrayGeneros.getDescricao() + "<br/>";
				
				informacoes += "</html>";
				JLabel informacaoAutores = new JLabel(informacoes);
				JPanel panelesq = new JPanel();
				panelesq.add(informacaoAutores);
				panelesq.setBorder(BorderFactory.createLineBorder(Color.white));
				panelesq.setBackground(SystemColor.activeCaption);
				panelsEsq.add(i,panelesq);
				
				i++;
			}
			for (JPanel panel_aux : panelsEsq) {
				panelEsquerdo.add(panel_aux);
			}
		}
	}
	public void ajustaTela(int num_usuarios){
		if (num_usuarios <= 7) {
			if(num_usuarios <=2)
				this.setBounds(screensize.width / 3, screensize.height / 6, 500, 200);	
			else
				this.setBounds(screensize.width / 3, screensize.height / 6, 500, 200 + (num_usuarios * 50));
			this.repaint();
		}
		else{
			this.setBounds(screensize.width / 3, screensize.height / 30, 500, 700);
			this.repaint();
		}
	}

	private class ButtonHandler implements ActionListener {
		// Metodo que trata evento de botao
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == voltar) {
				TelaGenero.this.dispose();
			}
		}
	}
}
