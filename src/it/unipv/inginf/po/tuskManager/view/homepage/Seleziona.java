package it.unipv.inginf.po.tuskManager.view.homepage;

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

public class Seleziona extends JPanel{
	
	/**
	 * 
	 */
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	ArrayList<JLabel> label;
	ArrayList<JButton> bottoni;
	private JButton bottone_logout;
	public Seleziona(ArrayList<String> stringhe) {
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
		
		label = new ArrayList<JLabel>();
		for(int i = 0; i< 4; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		bottone_logout = new JButton("INDIETRO");
		bottone_logout.setBorder(new LineBorder(Color.BLACK));
		bottone_logout.setFocusPainted(false);
		bottone_logout.setBackground(Color.RED);
		bottone_logout.setForeground(Color.WHITE);
		bottone_logout.setFont(new Font("Serif", Font.BOLD, 20));
		
		pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(stringhe.size()+2,3));
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		
		bottoni = new ArrayList<JButton>();
		
		if(stringhe != null) {
			JButton bottone;
			for(String s : stringhe) {
				bottone = new JButton(s);
				bottone.setBorder(new LineBorder(Color.BLACK));
				bottone.setFocusPainted(false);
				bottone.setBackground(colore_bottoni);
				bottone.setForeground(Color.BLACK);
				bottone.setFont(new Font("Serif", Font.PLAIN, 30));
				pannello.add(new JLabel());
				pannello.add(bottone);
				pannello.add(new JLabel());
				bottoni.add(bottone);
			}
		}
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		
		this.add(pannello, BorderLayout.CENTER);
		
		JPanel pannello_sopra = new JPanel();
		pannello_sopra.setOpaque(false);
		pannello_sopra.setLayout(new GridLayout(1,3));
		pannello_sopra.add(label.get(0));
		pannello_sopra.add(label.get(1));
		pannello_sopra.add(label.get(2));
		pannello_sopra.add(label.get(3));
		pannello_sopra.add(bottone_logout);
		this.add(pannello_sopra, BorderLayout.SOUTH);
	}
	
	public JButton getBottoneIndietro() {
		return this.bottone_logout;
	}
	public ArrayList<JButton> getAllBottoni(){
		return bottoni;
	}
}