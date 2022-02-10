package it.unipv.inginf.po.tuskManager.view.homepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskField;

public class Crea extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_invia, bottone_indietro;
	private JTuskField nome; 
	private Image img;
	public Crea(Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		nome = new JTuskField("nome",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		bottone_invia = new JTuskButton("  CREA  ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_indietro = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		
		this.add(nome);
		this.add(bottone_invia);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		nome.setBounds((int)(this.getSize().width/2-bottone_invia.getSize().width/2), (int)(this.getSize().height/2-bottone_invia.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_invia.setBounds((int)(this.getSize().width/2-bottone_invia.getSize().width/2), (int)(this.getSize().height/2+bottone_invia.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);

	}
	public JTuskField getNome() {
		return nome;
	}
	public JTuskButton getBottoneInvia() {
		return this.bottone_invia;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}