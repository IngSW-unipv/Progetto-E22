package it.unipv.inginf.po.tuskManager.view.modifica.membro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;

public class Membro extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_aggiungi, bottone_rimuovi, bottone_ruolo, bottone_indietro;
	private Image img;
	public Membro(Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		bottone_aggiungi = new JTuskButton("aggiungi membro",colore_bottoni, Color.BLACK,false, "assets/aggiungimembro.png",new Dimension(300,150),new Dimension(40,40));
		
		bottone_rimuovi = new JTuskButton("rimuovi membro",colore_bottoni, Color.BLACK,false, "assets/rimuovimembro.png",new Dimension(300,150),new Dimension(40,40));
		
		bottone_ruolo = new JTuskButton(" modifica ruolo ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_indietro = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		
		this.add(bottone_aggiungi);
		this.add(bottone_rimuovi);
		this.add(bottone_ruolo);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		bottone_aggiungi.setBounds((int)(this.getSize().width/2-bottone_aggiungi.getSize().width/2), (int)(this.getSize().height/2+bottone_aggiungi.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_rimuovi.setBounds((int)(this.getSize().width/2-bottone_rimuovi.getSize().width/2), (int)(this.getSize().height/2-bottone_rimuovi.getSize().height*3/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_ruolo.setBounds((int)(this.getSize().width/2-bottone_ruolo.getSize().width/2), (int)(this.getSize().height/2-bottone_ruolo.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
	}	
	public JTuskButton getBottoneAggiungi() {
		return this.bottone_aggiungi;
	}
	public JTuskButton getBottoneRimuovi() {
		return this.bottone_rimuovi;
	}
	public JTuskButton getBottoneRuolo() {
		return this.bottone_ruolo;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}
