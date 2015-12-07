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
import br.unb.cic.imdb.negocio.*;

public class TelaVerAvaliacoes extends JFrame {
	private Controle controle;
	private JPanel panelEsquerdo, panelDireito, PanelPrincipal, panelFundo;
	private JButton voltar;
	private ArrayList<JPanel> panelsEsq, panelsDir;
	private Toolkit toolkit;
	private Dimension screensize;
	private TrabalhoArtistico trabalho;
	private List<Avaliacao> avaliacoes;

	public TelaVerAvaliacoes(TrabalhoArtistico trabalho) {

		super("Avaliacoes");
		this.controle = controle;
		this.trabalho = trabalho;
		panelEsquerdo = new JPanel();
		panelDireito = new JPanel();
		panelFundo = new JPanel();
		panelsEsq = new ArrayList<>();
		panelsDir = new ArrayList<>();
		toolkit = Toolkit.getDefaultToolkit();
		screensize = toolkit.getScreenSize();
	}

	public void PanelPrincipal(int numAvaliacoes) {

		this.setLayout(new BorderLayout());
		this.setBounds(500, 150, 400, 410);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(new BorderLayout());
		String string = "                                                          ";
		PanelPrincipal.add(new JLabel(string + "Lista de Avaliacoes:"), BorderLayout.NORTH);
		PanelPrincipal.add(panelEsquerdo, BorderLayout.CENTER);
		PanelPrincipal.add(panelDireito, BorderLayout.EAST);
		PanelPrincipal.add(panelFundo, BorderLayout.SOUTH);

		JScrollPane p = new JScrollPane(PanelPrincipal);
		this.add(p, BorderLayout.CENTER);
		this.setVisible(true);
		if (numAvaliacoes < 1)
			JOptionPane.showMessageDialog(null, "Sem avaliacoes para mostrar!", "Sem avaliacoes",
					JOptionPane.WARNING_MESSAGE);
		else
			ajustaTela(numAvaliacoes);
	}

	public void desenhaNaTelaTodos() {

		avaliacoes = trabalho.getAvaliacoes();
		if (avaliacoes.size() > 0) {
			adicionarAvaliacaoNoPanel(avaliacoes.size());
		}

		voltar = new JButton("Voltar");

		panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.PAGE_AXIS));
		panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.PAGE_AXIS));

		panelFundo.setLayout(new FlowLayout());
		panelFundo.add(voltar);

		// Manipulador de evento de botao
		ButtonHandler handler = new ButtonHandler();
		voltar.addActionListener(handler);

		PanelPrincipal(avaliacoes.size());

	}

	public void adicionarAvaliacaoNoPanel(int numero) {
		int i = 0;
		for (Avaliacao arrayAvaliacoes : avaliacoes) {

			String informacoes = "<html>";
			informacoes += "Nota: " + arrayAvaliacoes.getAvalicao() + "<br/>";
			informacoes += "Comentario: " + arrayAvaliacoes.getComentario() + "<br/>";
			informacoes += "</html>";
			JLabel informacaoAutores = new JLabel(informacoes);
			JPanel panelesq = new JPanel();
			panelesq.add(informacaoAutores);
			panelesq.setBorder(BorderFactory.createLineBorder(Color.white));
			panelesq.setBackground(SystemColor.activeCaption);
			panelsEsq.add(i, panelesq);

			i++;
		}
		for (JPanel panel_aux : panelsEsq) {
			panelEsquerdo.add(panel_aux);
		}
	}

	public void ajustaTela(int num_usuarios) {
		if (num_usuarios <= 7) {
			if (num_usuarios <= 2)
				this.setBounds(screensize.width / 3, screensize.height / 6, 500, 200);
			else
				this.setBounds(screensize.width / 3, screensize.height / 6, 500, 200 + (num_usuarios * 50));
			this.repaint();
		} else {
			this.setBounds(screensize.width / 3, screensize.height / 30, 500, 700);
			this.repaint();
		}
	}

	private class ButtonHandler implements ActionListener {
		// Metodo que trata evento de botao
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == voltar) {
				TelaVerAvaliacoes.this.dispose();
			}
		}
	}
}
