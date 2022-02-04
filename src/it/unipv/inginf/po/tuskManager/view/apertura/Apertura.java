package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Apertura extends JPanel{
	
	/**
	 * 
	 */
	private static Color arancione_scuro = new Color(255,128,0);
	private static Color arancione = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_accedi, bottone_registrati, bottone_esci;
	public Apertura() {
		super();
		setSize(1600, 900);
		setBackground(arancione);
		this.setLayout(new BorderLayout());
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 14; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		bottone_accedi = new JButton("ACCEDI");
		bottone_accedi.setBorder(new LineBorder(Color.BLACK));
		bottone_accedi.setFocusPainted(false);
		bottone_accedi.setBackground(arancione_scuro);
		bottone_accedi.setForeground(Color.BLACK);
		bottone_accedi.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_registrati = new JButton("REGISTRATI");
		bottone_registrati.setBorder(new LineBorder(Color.BLACK));
		bottone_registrati.setFocusPainted(false);
		bottone_registrati.setBackground(arancione_scuro);
		bottone_registrati.setForeground(Color.BLACK);
		bottone_registrati.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_esci = new JButton("ESCI");
		bottone_esci.setBorder(new LineBorder(Color.BLACK));
		bottone_esci.setFocusPainted(false);
		bottone_esci.setBackground(Color.RED);
		bottone_esci.setForeground(Color.WHITE);
		bottone_esci.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(4,3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		pannello.add(bottone_accedi);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(bottone_registrati);
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
		pannello_sopra.add(bottone_esci);
		this.add(pannello_sopra, BorderLayout.NORTH);
	}
	
	public JButton getBottoneAccedi() {
		return this.bottone_accedi;
	}
	public JButton getBottoneRegistrati() {
		return this.bottone_registrati;
	}
	public JButton getBottoneEsci() {
		return this.bottone_esci;
	}
}