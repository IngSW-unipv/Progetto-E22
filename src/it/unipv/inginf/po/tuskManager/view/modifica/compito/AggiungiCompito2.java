package it.unipv.inginf.po.tuskManager.view.modifica.compito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;

import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskField;

public class AggiungiCompito2 extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_aggiungi, bottone_fine, bottone_indietro;
	private JTuskField ruolo;
	private ArrayList<String> ruoli;
	private String titolo,descrizione;
	private int giorno,mese,anno;
	private Image img;
	public AggiungiCompito2(String titolo, String descrizione, int giorno, int mese, int anno,Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.titolo = titolo;
		this.descrizione=descrizione;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
		ruoli = new ArrayList<String>();
		
		this.setLayout(new BorderLayout());
		
		ruolo = new JTuskField("ruolo",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		bottone_aggiungi = new JTuskButton(" + ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_fine = new JTuskButton(" fine ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		
		bottone_indietro = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		this.add(ruolo);
		this.add(bottone_aggiungi);
		this.add(bottone_fine);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		ruolo.setBounds((int)(this.getSize().width/2-ruolo.getSize().width/2), (int)(this.getSize().height/2-ruolo.getSize().height*3/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_aggiungi.setBounds((int)(this.getSize().width/2-bottone_aggiungi.getSize().width/2), (int)(this.getSize().height/2-bottone_aggiungi.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_fine.setBounds((int)(this.getSize().width/2-bottone_fine.getSize().width/2), (int)(this.getSize().height/2+bottone_fine.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		
	}	
	public JTuskField getRuolo() {
		return ruolo;
	}
	public void addRuolo(String s) {
		ruoli.add(s);
	}
	public ArrayList<String> getLista(){
		return ruoli;
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	public int getGiorno() {
		return this.giorno;
	}
	public int getMese() {
		return this.mese;
	}
	public int getAnno() {
		return this.anno;
	}
	public JTuskButton getBottoneAggiungi() {
		return this.bottone_aggiungi;
	}
	public JTuskButton getBottoneFine() {
		return this.bottone_fine;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}

