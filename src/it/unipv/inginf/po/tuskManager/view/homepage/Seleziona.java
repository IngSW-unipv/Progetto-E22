package it.unipv.inginf.po.tuskManager.view.homepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;

public class Seleziona extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	ArrayList<JLabel> label;
	ArrayList<JTuskButton> bottoni;
	private JTuskButton bottone_indietro;
	Image img;
	public Seleziona(ArrayList<String> stringhe,Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		
		label = new ArrayList<JLabel>();
		for(int i = 0; i< 4; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		bottone_indietro = new JTuskButton("INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(stringhe.size()+5,5));
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		bottoni = new ArrayList<JTuskButton>();
		
		if(stringhe != null) {
			JTuskButton bottone;
			for(String s : stringhe) {
				bottone = new JTuskButton(s,colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
				pannello.add(new JLabel());
				pannello.add(new JLabel());
				pannello.add(bottone);
				pannello.add(new JLabel());
				pannello.add(new JLabel());
				bottoni.add(bottone);
			}
		}
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		JPanel p1 = new JPanel();
		p1.add(bottone_indietro);
		p1.setOpaque(false);
		pannello.add(p1);
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		
		this.add(pannello, BorderLayout.CENTER);
		
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
	public ArrayList<JTuskButton> getAllBottoni(){
		return bottoni;
	}
}