package it.unipv.inginf.po.tuskManager.view.modifica.membro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ModificaRuolo extends JPanel{
	
	/**
	 * 
	 */
	private static Color arancione_scuro = new Color(255,128,0);
	private static Color arancione = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_invia, bottone_indietro;
	private JTextField email; 
	private JTextField ruolo;
	
	public ModificaRuolo() {
		super();
		setSize(1600, 900);
		setBackground(arancione);
		this.setLayout(new BorderLayout());
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 16; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		email = new JTextField("");
		email.setBorder(new LineBorder(Color.BLACK));
		email.setBackground(arancione_scuro);
		email.setForeground(Color.BLACK);
		email.setFont(new Font("Serif", Font.PLAIN, 30));
		email.setToolTipText("INSERIRE EMAIL ASSOCIATA AL RUOLO CHE SI VUOLE MODIFICARE");
		email.setHorizontalAlignment(JTextField.CENTER);
		
		ruolo = new JTextField("");
		ruolo.setBorder(new LineBorder(Color.BLACK));
		ruolo.setBackground(arancione_scuro);
		ruolo.setForeground(Color.BLACK);
		ruolo.setFont(new Font("Serif", Font.PLAIN, 30));
		ruolo.setToolTipText("INSERIRE IL NUOVO RUOLO");
		ruolo.setHorizontalAlignment(JTextField.CENTER);
		
		bottone_invia = new JButton("INVIA");
		bottone_invia.setBorder(new LineBorder(Color.BLACK));
		bottone_invia.setFocusPainted(false);
		bottone_invia.setBackground(arancione_scuro);
		bottone_invia.setForeground(Color.BLACK);
		bottone_invia.setFont(new Font("Serif", Font.PLAIN, 30));
		
		
		bottone_indietro = new JButton("INDIETRO");
		bottone_indietro.setBorder(new LineBorder(Color.BLACK));
		bottone_indietro.setFocusPainted(false);
		bottone_indietro.setBackground(Color.RED);
		bottone_indietro.setForeground(Color.WHITE);
		bottone_indietro.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(5,3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		pannello.add(email);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(ruolo);
		pannello.add(label.get(6));
		pannello.add(label.get(7));
		pannello.add(bottone_invia);
		pannello.add(label.get(8));
		pannello.add(label.get(9));
		pannello.add(label.get(10));
		pannello.add(label.get(11));
		this.add(pannello, BorderLayout.CENTER);
		
		JPanel pannello_sopra = new JPanel();
		pannello_sopra.setOpaque(false);
		pannello_sopra.setLayout(new GridLayout(1,3));
		pannello_sopra.add(label.get(12));
		pannello_sopra.add(label.get(13));
		pannello_sopra.add(label.get(14));
		pannello_sopra.add(label.get(15));
		pannello_sopra.add(bottone_indietro);
		this.add(pannello_sopra, BorderLayout.SOUTH);
	}
	
	public JTextField getEmail() {
		return email;
	}
	
	public JTextField getRuolo() {
		return ruolo;
	}
	
	public JButton getBottoneInvia() {
		return this.bottone_invia;
	}
	public JButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}
