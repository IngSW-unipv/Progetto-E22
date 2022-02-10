package it.unipv.inginf.po.tuskManager.view.modifica.compito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Calendar;
import javax.swing.JPanel;

import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskField;

public class AggiungiCompito extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_avanti, bottone_indietro;
	private JTuskField titolo, descrizione,scadenza_gg,scadenza_mm,scadenza_yyyy;
	private JPanel pannello_scadenza;
	private Image img;
	
	public AggiungiCompito(Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		
		titolo = new JTuskField("titolo",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		descrizione = new JTuskField("descrizione",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		pannello_scadenza = new JPanel();
		pannello_scadenza.setBackground(colore_sfondo);
		
		scadenza_gg = new JTuskField("giorno",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		scadenza_mm = new JTuskField("mese",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		scadenza_yyyy = new JTuskField("anno",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		pannello_scadenza.setLayout(new GridLayout(1,3));
		pannello_scadenza.add(scadenza_gg);
		pannello_scadenza.add(scadenza_mm);
		pannello_scadenza.add(scadenza_yyyy);
		
		bottone_avanti = new JTuskButton(" avanti ",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		
		bottone_indietro = new JTuskButton("    INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		
		this.add(titolo);
		this.add(descrizione);
		this.add(pannello_scadenza);
		this.add(bottone_avanti);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		titolo.setBounds((int)(this.getSize().width/2-titolo.getSize().width/2), (int)(this.getSize().height/2-titolo.getSize().height*2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		descrizione.setBounds((int)(this.getSize().width/2-descrizione.getSize().width/2), (int)(this.getSize().height/2-descrizione.getSize().height), (int)this.getSize().width/4, (int)this.getSize().height/8);
		pannello_scadenza.setBounds((int)(this.getSize().width/2-pannello_scadenza.getSize().width/2), (int)(this.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_avanti.setBounds((int)(this.getSize().width/2-bottone_avanti.getSize().width/2 ), (int)(this.getSize().height/2+bottone_avanti.getSize().height), (int)this.getSize().width/4, (int)this.getSize().height/8);
		
	}
	public JTuskField getTitolo() {
		return titolo;
	}
	public JTuskField getDescrizione() {
		return descrizione;
	}
	public Calendar getScadenza() {
		Calendar temp = Calendar.getInstance();
//		Date res;
		try {
			temp.set(Integer.parseInt(scadenza_yyyy.getText()), (Integer.parseInt(scadenza_mm.getText())-1), Integer.parseInt(scadenza_gg.getText()));
//			res = new Date(temp.getTimeInMillis());
		}catch(Exception ex) {
			return null;
		}
		
		return temp;
	}
	public JTuskField getGiorno() {
		return this.scadenza_gg;
	}
	public JTuskField getMese() {
		return this.scadenza_mm;
	}
	public JTuskField getAnno() {
		return this.scadenza_yyyy;
	}
	public JTuskButton getBottoneAvanti() {
		return this.bottone_avanti;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}


