package it.unipv.inginf.po.tuskManager.view.modifica.compito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;

public class OperazioniCompito extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_aggiungi, bottone_rimuovi, bottone_modifica, bottone_sposta, bottone_indietro;
	private Image img;
	public OperazioniCompito(Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		
		
		bottone_aggiungi = new JTuskButton(" aggiungi compito",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_rimuovi = new JTuskButton(" rimuovi compito",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_modifica = new JTuskButton(" modifica compito",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_sposta = new JTuskButton(" sposta compito ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		
		bottone_indietro = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		this.add(bottone_aggiungi);
		this.add(bottone_rimuovi);
		this.add(bottone_modifica);
		this.add(bottone_sposta);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		bottone_aggiungi.setBounds((int)(this.getSize().width/2-bottone_aggiungi.getSize().width/2), (int)(this.getSize().height/2-bottone_aggiungi.getSize().height*2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_rimuovi.setBounds((int)(this.getSize().width/2-bottone_rimuovi.getSize().width/2), (int)(this.getSize().height/2-bottone_rimuovi.getSize().height), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_modifica.setBounds((int)(this.getSize().width/2-bottone_modifica.getSize().width/2), (int)(this.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_sposta.setBounds((int)(this.getSize().width/2-bottone_sposta.getSize().width/2 ), (int)(this.getSize().height/2+bottone_sposta.getSize().height), (int)this.getSize().width/4, (int)this.getSize().height/8);
		
	}
	public JTuskButton getBottoneAggiungi() {
		return this.bottone_aggiungi;
	}
	public JTuskButton getBottoneRimuovi() {
		return this.bottone_rimuovi;
	}
	public JTuskButton getBottoneModifica() {
		return this.bottone_modifica;
	}
	
	public JTuskButton getBottoneSposta() {
		return this.bottone_sposta;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}

