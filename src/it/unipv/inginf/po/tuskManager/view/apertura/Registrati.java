package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Registrati extends JPanel{
	
	/**
	 * 
	 */
	
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_accedi, bottone_esci;
	private JTextField email; 
	private JPasswordField pw,pw_conferma;
	
	public Registrati() {
		super();
		setSize(1600, 900);
		
		Properties p = System.getProperties();
		try {
			p.load(new FileInputStream("config/colors.txt"));
			colore_bottoni = new Color(Integer.parseInt(p.getProperty("bottoni_red")),Integer.parseInt(p.getProperty("bottoni_green")),Integer.parseInt(p.getProperty("bottoni_blue")));
			colore_sfondo = new Color(Integer.parseInt(p.getProperty("sfondo_red")),Integer.parseInt(p.getProperty("sfondo_green")),Integer.parseInt(p.getProperty("sfondo_blue")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBackground(colore_sfondo);
		this.setLayout(new BorderLayout());
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 18; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		email = new JTextField("");
		email.setBorder(new LineBorder(Color.BLACK));
		email.setBackground(colore_bottoni);
		email.setForeground(Color.BLACK);
		email.setFont(new Font("Serif", Font.PLAIN, 30));
		email.setToolTipText("INSERIRE EMAIL");
		email.setHorizontalAlignment(JTextField.CENTER);
		
		pw = new JPasswordField("");
		pw.setBorder(new LineBorder(Color.BLACK));
		pw.setBackground(colore_bottoni);
		pw.setForeground(Color.BLACK);
		pw.setFont(new Font("Serif", Font.PLAIN, 30));
		pw.setToolTipText("INSERIRE PASSWORD");
		pw.setHorizontalAlignment(JTextField.CENTER);
		
		pw_conferma = new JPasswordField("");
		pw_conferma.setBorder(new LineBorder(Color.BLACK));
		pw_conferma.setBackground(colore_bottoni);
		pw_conferma.setForeground(Color.BLACK);
		pw_conferma.setFont(new Font("Serif", Font.PLAIN, 30));
		pw_conferma.setToolTipText("CONFERMARE PASSWORD");
		pw_conferma.setHorizontalAlignment(JTextField.CENTER);
		
		bottone_accedi = new JButton("INVIA");
		bottone_accedi.setBorder(new LineBorder(Color.BLACK));
		bottone_accedi.setFocusPainted(false);
		bottone_accedi.setBackground(colore_bottoni);
		bottone_accedi.setForeground(Color.BLACK);
		bottone_accedi.setFont(new Font("Serif", Font.PLAIN, 30));
		
		
		bottone_esci = new JButton("INDIETRO");
		bottone_esci.setBorder(new LineBorder(Color.BLACK));
		bottone_esci.setFocusPainted(false);
		bottone_esci.setBackground(Color.RED);
		bottone_esci.setForeground(Color.WHITE);
		bottone_esci.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(6,3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		pannello.add(email);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(pw);
		pannello.add(label.get(16));
		pannello.add(label.get(17));
		pannello.add(pw_conferma);
		pannello.add(label.get(6));
		pannello.add(label.get(7));
		pannello.add(bottone_accedi);
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
		pannello_sopra.add(bottone_esci);
		this.add(pannello_sopra, BorderLayout.SOUTH);
	}
	
	public JTextField getEmail() {
		return email;
	}
	
	public JPasswordField getPw() {
		return pw;
	}
	
	public JPasswordField getPwConferma() {
		return pw_conferma;
	}
	
	public JButton getBottoneInvia() {
		return this.bottone_accedi;
	}
	public JButton getBottoneIndietro() {
		return this.bottone_esci;
	}
}
