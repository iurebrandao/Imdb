package br.unb.cic.imdb.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import br.unb.cic.imdb.controle.Controle;
import br.unb.cic.imdb.negocio.TrabalhoArtistico;

public class TelaTrabalho extends JFrame {

	private ArrayList<JPanel> panelsEsq, panelsDir;
	private TrabalhoArtistico trabalho;
	private List<TrabalhoArtistico> listaDeTrabalhos;
	private Controle controle;
	private JPanel panelEsquerdo, panelDireito, PanelPrincipal, panelFundo;
	private JButton voltar, salvar;
	private String nomeTrabalho;

	public TelaTrabalho(Controle controle, String nomeTrabalho) {
		super("Trabalhos Artisticos");
		this.controle = controle;
		this.nomeTrabalho = nomeTrabalho;
		panelEsquerdo = new JPanel();
		panelDireito = new JPanel();
		panelFundo = new JPanel();
	}

	public void PanelPrincipal(int numTrabalhos) {

		this.setLayout(new BorderLayout());
		this.setBounds(500, 150, 400, 410);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(new BorderLayout());
		PanelPrincipal.add(panelEsquerdo, BorderLayout.CENTER);
		PanelPrincipal.add(panelDireito, BorderLayout.EAST);
		PanelPrincipal.add(panelFundo, BorderLayout.SOUTH);

		this.setVisible(true);
		this.add(PanelPrincipal);
		if(numTrabalhos < 1)
			JOptionPane.showMessageDialog(null, "Sem trabalhos para mostrar!","Sem trabalhos",JOptionPane.WARNING_MESSAGE);

	}

	public void desenhaNaTela() {

		trabalho = controle.recuperaTrabPorTitulo(nomeTrabalho);
		if (trabalho != null) {
			adicionarTrabalhoNoPanel(1);
			voltar = new JButton("Voltar");
			salvar = new JButton("Salvar");

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
			String informacoes = "<html>";
			informacoes += trabalho.getTitulo() + "<br/>";
			informacoes += trabalho.getAno() + "<br/>";
			informacoes += trabalho.getGenero() + "<br/>";
			informacoes += "</html>";
			JLabel informacaoTrab = new JLabel(informacoes);
			JPanel panelesq = new JPanel();
			panelesq.add(informacaoTrab);
			
			panelEsquerdo.add(panelesq);
			
			JButton avaliar = new JButton("avaliar");
			JButton comentar = new JButton("comentar");
			avaliar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "avaliar");
				}
			});
			comentar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "comentar");
				}
			});
			JPanel paneldir = new JPanel();
			paneldir.add( avaliar,BorderLayout.WEST);
			paneldir.add(comentar, BorderLayout.EAST);
			panelsDir.add(paneldir);
		}
		
		else{
			
		}
	}

	private class ButtonHandler implements ActionListener {
		// Metodo que trata evento de botao
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == voltar) {
				TelaTrabalho.this.dispose();
			}
			if (event.getSource() == salvar) {
				JOptionPane.showMessageDialog(null, "O comentario e a avaliacao foram salvos", "Salvar",
						JOptionPane.DEFAULT_OPTION);
			}
		}
	}
}
