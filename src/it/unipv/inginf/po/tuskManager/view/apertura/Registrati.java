package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskField;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskPassword;

public class Registrati extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_accedi, bottone_esci;
	private JTuskField email; 
	private JTuskPassword pw,pw_conferma;
	private Image img;
	public Registrati() {
		super();
		
		Properties p = System.getProperties();
		try {
			p.load(new FileInputStream("config/colors.txt"));
			colore_bottoni = new Color(Integer.parseInt(p.getProperty("bottoni_red")),Integer.parseInt(p.getProperty("bottoni_green")),Integer.parseInt(p.getProperty("bottoni_blue")));
//			colore_sfondo = new Color(Integer.parseInt(p.getProperty("sfondo_red")),Integer.parseInt(p.getProperty("sfondo_green")),Integer.parseInt(p.getProperty("sfondo_blue")));
			img = ImageIO.read(new File("assets/background.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setLayout(new BorderLayout());
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 18; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		email = new JTuskField("email",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		pw = new JTuskPassword("password",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		pw_conferma = new JTuskPassword("password",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		bottone_accedi = new JTuskButton("registrati",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		
		bottone_esci = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
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
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
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
	
	public JTuskButton getBottoneInvia() {
		return this.bottone_accedi;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_esci;
	}
}
