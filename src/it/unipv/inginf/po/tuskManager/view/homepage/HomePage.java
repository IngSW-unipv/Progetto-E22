package it.unipv.inginf.po.tuskManager.view.homepage;

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

public class HomePage extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_crea, bottone_seleziona, bottone_indietro;
	private Image img;
	public HomePage() {
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
		bottone_crea = new JTuskButton("  CREA  ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_seleziona = new JTuskButton("SELEZIONA",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_indietro = new JTuskButton("    LOGOUT",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		this.setOpaque(false);
		
		this.add(bottone_crea);
		
		this.add(bottone_seleziona);
		
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		bottone_crea.setBounds((int)(this.getSize().width/2-bottone_crea.getSize().width/2), (int)(this.getSize().height/2-bottone_crea.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_seleziona.setBounds((int)(this.getSize().width/2-bottone_seleziona.getSize().width/2), (int)(this.getSize().height/2+bottone_seleziona.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);

	}
	public JTuskButton getBottoneCrea() {
		return this.bottone_crea;
	}
	public JTuskButton getBottoneSeleziona() {
		return this.bottone_seleziona;
	}
	public JTuskButton getBottoneLogout() {
		return this.bottone_indietro;
	}
}
