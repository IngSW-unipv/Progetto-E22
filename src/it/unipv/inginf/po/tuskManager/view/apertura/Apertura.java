package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;

public class Apertura extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_accedi, bottone_registrati, bottone_impostazioni, bottone_esci;
	private Image img;
	public Apertura() {
		super();
		Properties p = System.getProperties();
		try {
			p.load(new FileInputStream("config/colors.txt"));
			colore_bottoni = new Color(Integer.parseInt(p.getProperty("bottoni_red")),Integer.parseInt(p.getProperty("bottoni_green")),Integer.parseInt(p.getProperty("bottoni_blue")));
//			colore_sfondo = new Color(Integer.parseInt(p.getProperty("sfondo_red")),Integer.parseInt(p.getProperty("sfondo_green")),Integer.parseInt(p.getProperty("sfondo_blue")));
			img = ImageIO.read(new File("assets/background_apertura.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bottone_impostazioni = new JTuskButton("OPZIONI",colore_bottoni, Color.BLACK,false, "assets/impostazioni.png",new Dimension(150,75),new Dimension(20,20));
		
		bottone_esci = new JTuskButton("    ESCI",Color.red, Color.BLACK,true, "assets/quit.png",new Dimension(150,75),new Dimension(20,20));
		
		bottone_accedi = new JTuskButton("ACCEDI    ",colore_bottoni, Color.BLACK,false, "assets/accedi.png",new Dimension(300,150),new Dimension(40,40));
		
		bottone_registrati = new JTuskButton("REGISTRATI",colore_bottoni, Color.BLACK,false, "assets/registrati.png",new Dimension(300,150),new Dimension(40,40));
		

		this.add(bottone_impostazioni,0,0);
		this.add(bottone_esci);
		this.setOpaque(false);
		this.add(bottone_accedi);
		this.add(bottone_registrati);	
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
	
		bottone_impostazioni.setBounds(0, 0, (int)this.getSize().width/8, (int)this.getSize().height/12);
		bottone_esci.setBounds((int)this.getSize().width-bottone_esci.getSize().width, 0, (int)this.getSize().width/8, (int)this.getSize().height/12);
		bottone_accedi.setBounds((int)(this.getSize().width/2-bottone_accedi.getSize().width/2), (int)(this.getSize().height/2-bottone_accedi.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_registrati.setBounds((int)(this.getSize().width/2-bottone_registrati.getSize().width/2), (int)(this.getSize().height/2+bottone_registrati.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
	}
	public JTuskButton getBottoneAccedi() {
		return this.bottone_accedi;
	}
	public JTuskButton getBottoneRegistrati() {
		return this.bottone_registrati;
	}
	public JTuskButton getBottoneEsci() {
		return this.bottone_esci;
	}
	public JTuskButton getBottoneImpostazioni() {
		return this.bottone_impostazioni;
	}
}