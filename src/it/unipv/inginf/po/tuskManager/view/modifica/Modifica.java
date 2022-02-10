package it.unipv.inginf.po.tuskManager.view.modifica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;

public class Modifica extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_membro, bottone_compito, bottone_ws, bottone_indietro;
	private Image img;
	public Modifica(Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		bottone_membro = new JTuskButton(" operazioni sui membri ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		bottone_compito = new JTuskButton(" operazioni sui compiti ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		bottone_ws = new JTuskButton(" operazioni sul workspace ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		bottone_indietro = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		this.add(bottone_membro);
		this.add(bottone_compito);
		this.add(bottone_ws);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		bottone_membro.setBounds((int)(this.getSize().width/2-bottone_membro.getSize().width/2), (int)(this.getSize().height/2+bottone_membro.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_compito.setBounds((int)(this.getSize().width/2-bottone_compito.getSize().width/2), (int)(this.getSize().height/2-bottone_compito.getSize().height*3/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_ws.setBounds((int)(this.getSize().width/2-bottone_ws.getSize().width/2), (int)(this.getSize().height/2-bottone_ws.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
	}	
	public JTuskButton getBottoneMembro() {
		return this.bottone_membro;
	}
	public JTuskButton getBottoneCompito() {
		return this.bottone_compito;
	}
	public JTuskButton getBottoneWorkspace() {
		return this.bottone_ws;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}
