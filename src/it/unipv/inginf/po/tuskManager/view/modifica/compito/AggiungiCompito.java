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

public class AggiungiCompito extends JPanel{
	
	/**
	 * 
	 */
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_avanti, bottone_indietro;
	private JTextField titolo, descrizione,scadenza_gg,scadenza_mm,scadenza_yyyy;
	private JPanel pannello_scadenza;
	public AggiungiCompito() {
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
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 18; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		titolo = new JTextField("");
		titolo.setBorder(new LineBorder(Color.BLACK));
		titolo.setBackground(colore_bottoni);
		titolo.setForeground(Color.BLACK);
		titolo.setFont(new Font("Serif", Font.PLAIN, 30));
		titolo.setToolTipText("INSERIRE IL TITOLO DEL COMPITO");
		titolo.setHorizontalAlignment(JTextField.CENTER);
		
		descrizione = new JTextField("");
		descrizione.setBorder(new LineBorder(Color.BLACK));
		descrizione.setBackground(colore_bottoni);
		descrizione.setForeground(Color.BLACK);
		descrizione.setFont(new Font("Serif", Font.PLAIN, 30));
		descrizione.setToolTipText("INSERIRE UNA DESCRIZIONE");
		descrizione.setHorizontalAlignment(JTextField.CENTER);
		
		pannello_scadenza = new JPanel();
		pannello_scadenza.setBackground(colore_sfondo);
		
		scadenza_gg = new JTextField();
		scadenza_gg.setBorder(new LineBorder(Color.BLACK));
		scadenza_gg.setBackground(colore_bottoni);
		scadenza_gg.setForeground(Color.BLACK);
		scadenza_gg.setFont(new Font("Serif", Font.PLAIN, 30));
		scadenza_gg.setToolTipText("gg");
		scadenza_gg.setHorizontalAlignment(JTextField.CENTER);;
		
		scadenza_mm = new JTextField();
		scadenza_mm.setBorder(new LineBorder(Color.BLACK));
		scadenza_mm.setBackground(colore_bottoni);
		scadenza_mm.setForeground(Color.BLACK);
		scadenza_mm.setFont(new Font("Serif", Font.PLAIN, 30));
		scadenza_mm.setToolTipText("mm");
		scadenza_mm.setHorizontalAlignment(JTextField.CENTER);;
		
		scadenza_yyyy = new JTextField();
		scadenza_yyyy.setBorder(new LineBorder(Color.BLACK));
		scadenza_yyyy.setBackground(colore_bottoni);
		scadenza_yyyy.setForeground(Color.BLACK);
		scadenza_yyyy.setFont(new Font("Serif", Font.PLAIN, 30));
		scadenza_yyyy.setToolTipText("yyyy");
		scadenza_yyyy.setHorizontalAlignment(JTextField.CENTER);;
		
		pannello_scadenza.setLayout(new GridLayout(1,3));
		pannello_scadenza.add(scadenza_gg);
		pannello_scadenza.add(scadenza_mm);
		pannello_scadenza.add(scadenza_yyyy);
		
		bottone_avanti = new JButton("AVANTI");
		bottone_avanti.setBorder(new LineBorder(Color.BLACK));
		bottone_avanti.setFocusPainted(false);
		bottone_avanti.setBackground(colore_bottoni);
		bottone_avanti.setForeground(Color.BLACK);
		bottone_avanti.setFont(new Font("Serif", Font.PLAIN, 30));
		
		
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
		pannello.add(titolo);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(descrizione);
		pannello.add(label.get(16));
		pannello.add(label.get(17));
		pannello.add(pannello_scadenza);
		pannello.add(label.get(6));
		pannello.add(label.get(7));
		pannello.add(bottone_avanti);
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
	
	public JTextField getTitolo() {
		return titolo;
	}
	public JTextField getDescrizione() {
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
	public JTextField getGiorno() {
		return this.scadenza_gg;
	}
	public JTextField getMese() {
		return this.scadenza_mm;
	}
	public JTextField getAnno() {
		return this.scadenza_yyyy;
	}
	public JButton getBottoneAvanti() {
		return this.bottone_avanti;
	}
	public JButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}


