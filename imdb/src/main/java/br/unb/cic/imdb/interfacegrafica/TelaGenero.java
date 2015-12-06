package br.unb.cic.imdb.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import br.unb.cic.imdb.controle.Controle;
import br.unb.cic.imdb.negocio.Genero;

public class TelaGenero extends JFrame {
	
	private String nomeGenero;
	private JPanel panelEsquerdo, panelDireito, PanelPrincipal, panelFundo;
	private JButton voltar;
	private ArrayList<JPanel> panelsEsq, panelsDir;
	private Controle controle;
	private Genero genero;
	private List<Genero> listaDeGeneros;
	
	public TelaGenero(Controle controle,String nomeGenero){
		super("Genero");
		this.nomeGenero = nomeGenero;
		this.controle = controle;
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
			JOptionPane.showMessageDialog(null, "Sem generos para mostrar!","Sem trabalhos",JOptionPane.WARNING_MESSAGE);

	}

	public void desenhaNaTela() {

		genero = controle.recuperaGeneroPorTitulo(nomeGenero);
		if (genero != null) {
			adicionarTrabalhoNoPanel(1);
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
			adicionarTrabalhoNoPanel(listaDeGeneros.size());		
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

	public void adicionarTrabalhoNoPanel(int numero) {
		if(numero < 1){
			String informacoes = "<html>";
			informacoes += genero.getTitulo() + "<br/>";
			informacoes += genero.getDescricao() + "<br/>";
			informacoes += "</html>";
			JLabel informacaoAutor = new JLabel(informacoes);
			JPanel panelesq = new JPanel();
			panelesq.add(informacaoAutor);
			
			panelEsquerdo.add(panelesq);
			
			JButton avaliar = new JButton("avaliar");
			
			avaliar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "avaliar");
				}
			});
			JPanel paneldir = new JPanel();
			paneldir.add( avaliar,BorderLayout.WEST);
			panelsDir.add(paneldir);
		}
		
		else{
			
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
