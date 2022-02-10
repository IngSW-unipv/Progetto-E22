package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskField;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskPassword;

public class Registrati extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_accedi, bottone_indietro;
	private JTuskField email; 
	private JTuskPassword pw,pw_conferma;
	private Image img;
	public Registrati(Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		
		email = new JTuskField("email",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		pw = new JTuskPassword("password",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		pw_conferma = new JTuskPassword("password",colore_bottoni,Color.BLACK,new Font("Arial", Font.PLAIN, 20));
		
		bottone_accedi = new JTuskButton("REGISTRATI",colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		bottone_indietro = new JTuskButton("INDIETRO",Color.red, Color.BLACK,true,new Dimension(150,75),new Dimension(20,20));
		
		
		this.add(email);
		this.add(pw);
		this.add(pw_conferma);
		this.add(bottone_accedi);
		this.add(bottone_indietro);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0,this.getParent().getSize().width, this.getParent().getSize().height, null);
		this.setSize(this.getParent().getSize());
		bottone_indietro.setBounds((int)this.getSize().width-bottone_indietro.getSize().width, (int)(this.getSize().height-bottone_indietro.getSize().height), (int)this.getSize().width/8, (int)this.getSize().height/12);
		email.setBounds((int)(this.getSize().width/2-bottone_accedi.getSize().width/2), (int)(this.getSize().height/2-bottone_accedi.getSize().height*2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		pw.setBounds((int)(this.getSize().width/2-bottone_accedi.getSize().width/2), (int)(this.getSize().height/2-bottone_accedi.getSize().height), (int)this.getSize().width/4, (int)this.getSize().height/8);
		pw_conferma.setBounds((int)(this.getSize().width/2-bottone_accedi.getSize().width/2), (int)(this.getSize().height/2), (int)this.getSize().width/4, (int)this.getSize().height/8);
		bottone_accedi.setBounds((int)(this.getSize().width/2-bottone_accedi.getSize().width/2), (int)(this.getSize().height/2+bottone_accedi.getSize().height), (int)this.getSize().width/4, (int)this.getSize().height/8);
		
	}
	public JTuskField getEmail() {
		return email;
	}
	
	public JTuskPassword getPw() {
		return pw;
	}
	
	public JTuskPassword getPwConferma() {
		return pw_conferma;
	}
	
	public JTuskButton getBottoneInvia() {
		return this.bottone_accedi;
	}
	public JTuskButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}
