package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskField;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskPassword;

public class Accedi extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_accedi, bottone_indietro;
	private JTuskField email; 
	private JTuskPassword pw;
	private Image img;
	
	public Accedi() {
		super();
		
		this.setLayout(new BorderLayout());
		Properties p = System.getProperties();
		try {
			p.load(new FileInputStream("config/colors.txt"));
			colore_bottoni = new Color(Integer.parseInt(p.getProperty("bottoni_red")),Integer.parseInt(p.getProperty("bottoni_green")),Integer.parseInt(p.getProperty("bottoni_blue")));
//			colore_sfondo = new Color(Integer.parseInt(p.getProperty("sfondo_red")),Integer.parseInt(p.getProperty("sfondo_green")),Integer.parseInt(p.getProperty("sfondo_blue")));
			img = ImageIO.read(new File("assets/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		email = new JTuskField("email",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		pw = new JTuskPassword("password",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		bottone_accedi = new JTuskButton("accedi",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		bottone_indietro = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		this.setOpaque(false);
		this.add(email);
		this.add(pw);
		this.add(bottone_accedi);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		bottone_accedi.setBounds((int)(this.getSize().width/2-bottone_accedi.getSize().width/2), (int)(this.getSize().height/2+bottone_accedi.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		email.setBounds((int)(this.getSize().width/2-email.getSize().width/2), (int)(this.getSize().height/2-email.getSize().height*3/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		pw.setBounds((int)(this.getSize().width/2-pw.getSize().width/2), (int)(this.getSize().height/2-pw.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
	}
	public JTextField getEmail() {
		return email;
	}
	
	public JTuskPassword getPw() {
		return pw;
	}
	
	public JTuskButton getBottoneInvia() {
		return this.bottone_accedi;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}