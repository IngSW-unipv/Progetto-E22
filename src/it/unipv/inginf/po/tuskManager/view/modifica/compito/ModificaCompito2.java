package it.unipv.inginf.po.tuskManager.view.modifica.compito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;

public class ModificaCompito2 extends JPanel{
	
	/**
	 * 
	 */
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_aggiungi, bottone_rimuovi, bottone_fine, bottone_indietro;
	private JTextField ruolo;
	private String titolo,titolo_vecchio, descrizione;
	private Calendar scadenza;
	ArrayList<Ruolo> ruoli_da_togliere;
	ArrayList<Ruolo> ruoli_da_aggiungere;
	
	public ModificaCompito2(String titolo_vecchio, String titolo, String descrizione, Calendar scadenza) {
		super();
		Properties p = System.getProperties();
		try {
			p.load(new FileInputStream("config/colors.txt"));
			colore_bottoni = new Color(Integer.parseInt(p.getProperty("bottoni_red")),Integer.parseInt(p.getProperty("bottoni_green")),Integer.parseInt(p.getProperty("bottoni_blue")));
			colore_sfondo = new Color(Integer.parseInt(p.getProperty("sfondo_red")),Integer.parseInt(p.getProperty("sfondo_green")),Integer.parseInt(p.getProperty("sfondo_blue")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBackground(colore_sfondo);
		this.setLayout(new BorderLayout());
		
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.scadenza = scadenza;
		this.titolo_vecchio = titolo_vecchio;
		ruoli_da_togliere = new ArrayList<Ruolo>();
		ruoli_da_aggiungere = new ArrayList<Ruolo>();
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 18; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		ruolo = new JTextField("");
		ruolo.setBorder(new LineBorder(Color.BLACK));
		ruolo.setBackground(colore_bottoni);
		ruolo.setForeground(Color.BLACK);
		ruolo.setFont(new Font("Serif", Font.PLAIN, 30));
		ruolo.setToolTipText("INSERIRE IL RUOLO DA ASSOCIARE O ELIMINARE");
		ruolo.setHorizontalAlignment(JTextField.CENTER);
		
		bottone_aggiungi = new JButton("+");
		bottone_aggiungi.setBorder(new LineBorder(Color.BLACK));
		bottone_aggiungi.setFocusPainted(false);
		bottone_aggiungi.setBackground(colore_bottoni);
		bottone_aggiungi.setForeground(Color.BLACK);
		bottone_aggiungi.setFont(new Font("Serif", Font.PLAIN, 30));
		
		bottone_rimuovi = new JButton("-");
		bottone_rimuovi.setBorder(new LineBorder(Color.BLACK));
		bottone_rimuovi.setFocusPainted(false);
		bottone_rimuovi.setBackground(colore_bottoni);
		bottone_rimuovi.setForeground(Color.BLACK);
		bottone_rimuovi.setFont(new Font("Serif", Font.PLAIN, 30));
		
		bottone_fine = new JButton("FINE");
		bottone_fine.setBorder(new LineBorder(Color.BLACK));
		bottone_fine.setFocusPainted(false);
		bottone_fine.setBackground(colore_bottoni);
		bottone_fine.setForeground(Color.BLACK);
		bottone_fine.setFont(new Font("Serif", Font.PLAIN, 30));
		
		
		bottone_indietro = new JButton("INDIETRO");
		bottone_indietro.setBorder(new LineBorder(Color.BLACK));
		bottone_indietro.setFocusPainted(false);
		bottone_indietro.setBackground(Color.RED);
		bottone_indietro.setForeground(Color.WHITE);
		bottone_indietro.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(6,3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		pannello.add(ruolo);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(bottone_aggiungi);
		pannello.add(label.get(16));
		pannello.add(label.get(17));
		pannello.add(bottone_rimuovi);
		pannello.add(label.get(6));
		pannello.add(label.get(7));
		pannello.add(bottone_fine);
		pannello.add(label.get(8));
		pannello.add(label.get(9));
		pannello.add(label.get(10));
		pannello.add(label.get(11));
		this.add(pannello, BorderLayout.CENTER);
		
		JPanel pannello_sopra = new JPanel();
		pannello_sopra.setOpaque(false);
		pannello_sopra.setLayout(new GridLayout(1,3));
		pannello_sopra.add(label.get(12));
		pannello_sopra.add(label.get(13));
		pannello_sopra.add(label.get(14));
		pannello_sopra.add(label.get(15));
		pannello_sopra.add(bottone_indietro);
		this.add(pannello_sopra, BorderLayout.SOUTH);
	}
	
	public JTextField getRuolo() {
		return ruolo;
	}
	public JButton getBottoneAggiungi() {
		return this.bottone_aggiungi;
	}
	public JButton getBottoneRimuovi() {
		return this.bottone_rimuovi;
	}
	
	public String getTitolo() {
		return titolo;
	}
	public String getTitoloVecchio() {
		return titolo_vecchio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public Calendar getScadenza() {
		return scadenza;
	}
	public void addRuolo(Ruolo r) {
		ruoli_da_aggiungere.add(r);
	}
	public void removeRuolo(Ruolo r) {
		ruoli_da_togliere.add(r);
	}
	public ArrayList<Ruolo> getRuoliDaAggiungere(){
		return ruoli_da_aggiungere;
	}
	public ArrayList<Ruolo> getRuoliDaTogliere(){
		return ruoli_da_togliere;
	}
	public JButton getBottoneFine() {
		return this.bottone_fine;
	}
	public JButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}

