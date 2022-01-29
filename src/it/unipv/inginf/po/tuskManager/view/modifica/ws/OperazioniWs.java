package it.unipv.inginf.po.tuskManager.view.modifica.ws;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class OperazioniWs extends JPanel{
	
	/**
	 * 
	 */
	private static Color arancione_scuro = new Color(255,128,0);
	private static Color arancione = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_nome, bottone_rimuovi, bottone_indietro;
	public OperazioniWs() {
		super();
		setSize(1600, 900);
		setBackground(arancione);
		this.setLayout(new BorderLayout());
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 14; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		bottone_nome = new JButton("MODIFICA NOME");
		bottone_nome.setBorder(new LineBorder(Color.BLACK));
		bottone_nome.setFocusPainted(false);
		bottone_nome.setBackground(arancione_scuro);
		bottone_nome.setForeground(Color.BLACK);
		bottone_nome.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_rimuovi = new JButton("ELIMINA WORKSPACE");
		bottone_rimuovi.setBorder(new LineBorder(Color.BLACK));
		bottone_rimuovi.setFocusPainted(false);
		bottone_rimuovi.setBackground(arancione_scuro);
		bottone_rimuovi.setForeground(Color.BLACK);
		bottone_rimuovi.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_indietro = new JButton("INDIETRO");
		bottone_indietro.setBorder(new LineBorder(Color.BLACK));
		bottone_indietro.setFocusPainted(false);
		bottone_indietro.setBackground(Color.RED);
		bottone_indietro.setForeground(Color.WHITE);
		bottone_indietro.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(4,3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		pannello.add(bottone_nome);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(bottone_rimuovi);
		pannello.add(label.get(6));
		pannello.add(label.get(7));
		pannello.add(label.get(8));
		pannello.add(label.get(9));
		this.add(pannello, BorderLayout.CENTER);
		
		JPanel pannello_sopra = new JPanel();
		pannello_sopra.setOpaque(false);
		pannello_sopra.setLayout(new GridLayout(1,3));
		pannello_sopra.add(label.get(10));
		pannello_sopra.add(label.get(11));
		pannello_sopra.add(label.get(12));
		pannello_sopra.add(label.get(13));
		pannello_sopra.add(bottone_indietro);
		this.add(pannello_sopra, BorderLayout.SOUTH);
	}
	
	public JButton getBottoneNome() {
		return this.bottone_nome;
	}
	public JButton getBottoneRimuovi() {
		return this.bottone_rimuovi;
	}
	public JButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}