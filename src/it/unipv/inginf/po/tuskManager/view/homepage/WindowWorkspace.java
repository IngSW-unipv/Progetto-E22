package it.unipv.inginf.po.tuskManager.view.homepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;

public class WindowWorkspace extends JPanel{
	
	/**
	 * 
	 */
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_operazioni, bottone_indietro;
	private ArrayList<JButton> bottoni;
	public WindowWorkspace(Workspace w, Ruolo r) {
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
		for(int i = 0; i< 19; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		bottoni = new ArrayList<JButton>();
		
		bottone_operazioni = new JButton("OPERAZIONI");
		bottone_operazioni.setBorder(new LineBorder(Color.BLACK));
		bottone_operazioni.setFocusPainted(false);
		bottone_operazioni.setBackground(colore_bottoni);
		bottone_operazioni.setForeground(Color.BLACK);
		bottone_operazioni.setFont(new Font("Serif", Font.PLAIN, 30));
		
		
		bottone_indietro = new JButton("INDIETRO");
		bottone_indietro.setBorder(new LineBorder(Color.BLACK));
		bottone_indietro.setFocusPainted(false);
		bottone_indietro.setBackground(Color.RED);
		bottone_indietro.setForeground(Color.WHITE);
		bottone_indietro.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new BorderLayout());
	
		JPanel pannello_schede = new JPanel();

		pannello_schede.setBorder(new LineBorder(Color.WHITE));
		pannello_schede.setBackground(colore_bottoni);
		pannello_schede.setLayout(new GridLayout(1,w.getLista_schede().size()));
		
		int numero_max_compiti = 0;//serve per avere le liste dei compiti grandi uguali
		int count=0;
		for(Scheda s : w.getLista_schede()) {
			for(@SuppressWarnings("unused") Compito c : s.getCompiti()) {
				count++;
			}
			numero_max_compiti = (count > numero_max_compiti) ? count : numero_max_compiti;
			count = 0;
		}
		
		for(Scheda s : w.getLista_schede()) {
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(Color.WHITE));
			panel.setBackground(colore_bottoni);
			JLabel titolo = new JLabel(s.getTitolo());
			titolo.setHorizontalAlignment(JLabel.CENTER);
			titolo.setBackground(colore_bottoni);
			titolo.setForeground(Color.WHITE);
			titolo.setFont(new Font("Serif", Font.BOLD, 28));
			
			
			panel.setLayout(new GridLayout(numero_max_compiti+4,1));
			panel.add(titolo);
			for(Compito c: s.getCompiti()) {
				JButton b = new JButton(c.getTitolo());
				b.setBorder(new LineBorder(Color.BLACK));
				b.setFocusPainted(false);
				b.setBackground(colore_bottoni);
				b.setForeground(Color.BLACK);
				b.setFont(new Font("Serif", Font.PLAIN, 20));
				panel.add(b);
				bottoni.add(b);
			}
			pannello_schede.add(panel);
		}
		
		
		pannello.add(pannello_schede,BorderLayout.CENTER);
		pannello.add(new JLabel(" \n\n\n "),BorderLayout.WEST);
		pannello.add(new JLabel(" \n\n\n "),BorderLayout.NORTH);
		pannello.add(new JLabel(" \n\n\n "),BorderLayout.SOUTH);
		pannello.add(new JLabel(" \n\n\n "),BorderLayout.EAST);
		
		this.add(pannello, BorderLayout.CENTER);
		
		JPanel pannello_sopra = new JPanel();
		pannello_sopra.setOpaque(false);
		pannello_sopra.setLayout(new GridLayout(1,3));
		label.get(10).setText("Il tuo ruolo è: "+r.getNome());
		label.get(10).setBackground(colore_bottoni);
		label.get(10).setForeground(Color.WHITE);
		label.get(10).setFont(new Font("Serif", Font.ITALIC, 15));
		pannello_sopra.add(label.get(10));
		pannello_sopra.add(label.get(11));
		pannello_sopra.add(label.get(12));
		pannello_sopra.add(label.get(13));
		pannello_sopra.add(bottone_operazioni);
		this.add(pannello_sopra, BorderLayout.NORTH);
		
		JPanel pannello_sotto = new JPanel();
		pannello_sotto.setOpaque(false);
		pannello_sotto.setLayout(new GridLayout(1,3));
		pannello_sotto.add(label.get(14));
		pannello_sotto.add(label.get(15));
		pannello_sotto.add(label.get(16));
		pannello_sotto.add(label.get(17));
		pannello_sotto.add(bottone_indietro);
		this.add(pannello_sotto, BorderLayout.SOUTH);
	}
	
	public JButton getBottoneOperazioni() {
		return this.bottone_operazioni;
	}
	public JButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
	public ArrayList<JButton> getAllBottoni(){
		return bottoni;
	}
}