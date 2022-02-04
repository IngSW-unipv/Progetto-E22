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

public class HomePage extends JPanel{
	
	/**
	 * 
	 */
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_crea, bottone_seleziona, bottone_logout;
	public HomePage() {
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
		bottone_crea = new JButton("CREA WORKSPACE");
		bottone_crea.setBorder(new LineBorder(Color.BLACK));
		bottone_crea.setFocusPainted(false);
		bottone_crea.setBackground(colore_bottoni);
		bottone_crea.setForeground(Color.BLACK);
		bottone_crea.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_seleziona = new JButton("SELEZIONA");
		bottone_seleziona.setBorder(new LineBorder(Color.BLACK));
		bottone_seleziona.setFocusPainted(false);
		bottone_seleziona.setBackground(colore_bottoni);
		bottone_seleziona.setForeground(Color.BLACK);
		bottone_seleziona.setFont(new Font("Serif", Font.PLAIN, 40));
		
		bottone_logout = new JButton("LOGOUT");
		bottone_logout.setBorder(new LineBorder(Color.BLACK));
		bottone_logout.setFocusPainted(false);
		bottone_logout.setBackground(Color.RED);
		bottone_logout.setForeground(Color.WHITE);
		bottone_logout.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(4,3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		pannello.add(bottone_crea);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(bottone_seleziona);
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
		pannello_sopra.add(bottone_logout);
		this.add(pannello_sopra, BorderLayout.NORTH);
	}
	
	public JButton getBottoneCrea() {
		return this.bottone_crea;
	}
	public JButton getBottoneSeleziona() {
		return this.bottone_seleziona;
	}
	public JButton getBottoneLogout() {
		return this.bottone_logout;
	}
}
