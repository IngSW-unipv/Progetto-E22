package it.unipv.inginf.po.tuskManager.view.modifica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Modifica extends JPanel{
	
	/**
	 * 
	 */
	private static Color arancione_scuro = new Color(255,128,0);
	private static Color arancione = new Color(255,178,102);
	private static final long serialVersionUID = 1L;
	private JButton bottone_membro, bottone_compito, bottone_ws, bottone_indietro;
	
	public Modifica() {
		super();
		setSize(1600, 900);
		setBackground(arancione);
		this.setLayout(new BorderLayout());
		
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 16; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		
		bottone_membro = new JButton("OPERAZIONI SUI MEMBRI");
		bottone_membro.setBorder(new LineBorder(Color.BLACK));
		bottone_membro.setFocusPainted(false);
		bottone_membro.setBackground(arancione_scuro);
		bottone_membro.setForeground(Color.BLACK);
		bottone_membro.setFont(new Font("Serif", Font.PLAIN, 30));
		
		bottone_compito = new JButton("OPERAZIONI SUI COMPITI");
		bottone_compito.setBorder(new LineBorder(Color.BLACK));
		bottone_compito.setFocusPainted(false);
		bottone_compito.setBackground(arancione_scuro);
		bottone_compito.setForeground(Color.BLACK);
		bottone_compito.setFont(new Font("Serif", Font.PLAIN, 30));
		
		bottone_ws = new JButton("OPERAZIONI SUI WORKSPACE");
		bottone_ws.setBorder(new LineBorder(Color.BLACK));
		bottone_ws.setFocusPainted(false);
		bottone_ws.setBackground(arancione_scuro);
		bottone_ws.setForeground(Color.BLACK);
		bottone_ws.setFont(new Font("Serif", Font.PLAIN, 30));
		
		
		bottone_indietro = new JButton("INDIETRO");
		bottone_indietro.setBorder(new LineBorder(Color.BLACK));
		bottone_indietro.setFocusPainted(false);
		bottone_indietro.setBackground(Color.RED);
		bottone_indietro.setForeground(Color.WHITE);
		bottone_indietro.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout(5,3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		pannello.add(bottone_membro);
		pannello.add(label.get(4));
		pannello.add(label.get(5));
		pannello.add(bottone_compito);
		pannello.add(label.get(6));
		pannello.add(label.get(7));
		pannello.add(bottone_ws);
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
		
	public JButton getBottoneMembro() {
		return this.bottone_membro;
	}
	public JButton getBottoneCompito() {
		return this.bottone_compito;
	}
	public JButton getBottoneWorkspace() {
		return this.bottone_ws;
	}
	public JButton getBottoneIndietro() {
		return this.bottone_indietro;
	}
}
