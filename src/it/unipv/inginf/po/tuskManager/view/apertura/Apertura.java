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
import javax.swing.border.LineBorder;

public class Apertura extends JPanel{
	
	/**
	 * 
	 */
	
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_accedi, bottone_registrati,bottone_impostazioni, bottone_esci;
	public Apertura() {
		super();
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
		for(int i = 0; i< 14; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		bottone_accedi = new JButton("ACCEDI");
		bottone_accedi.setBorder(new LineBorder(Color.BLACK));
		bottone_accedi.setFocusPainted(false);
		bottone_accedi.setBackground(colore_bottoni);
		bottone_accedi.setForeground(Color.BLACK);
		bottone_accedi.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_registrati = new JButton("REGISTRATI");
		bottone_registrati.setBorder(new LineBorder(Color.BLACK));
		bottone_registrati.setFocusPainted(false);
		bottone_registrati.setBackground(colore_bottoni);
		bottone_registrati.setForeground(Color.BLACK);
		bottone_registrati.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_impostazioni = new JButton("IMPOSTAZIONI");
		bottone_impostazioni.setBorder(new LineBorder(Color.BLACK));
		bottone_impostazioni.setFocusPainted(false);
		bottone_impostazioni.setBackground(colore_bottoni);
		bottone_impostazioni.setForeground(Color.BLACK);
		bottone_impostazioni.setFont(new Font("Serif", Font.PLAIN, 20));
		
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
		pannello_sopra.add(bottone_impostazioni);
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
	public JButton getBottoneImpostazioni() {
		return this.bottone_impostazioni;
	}
}