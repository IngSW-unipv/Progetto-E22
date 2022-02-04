package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Impostazioni extends JPanel{
	
	/**
	 * 
	 */
	private static Color colore_bottoni = new Color(255,128,0);
	private static Color colore_sfondo = new Color(255,178,102);
	ArrayList<JButton> bottoni;
	private static final long serialVersionUID = 1L;
	private JButton bottone_esci;
	
	public Impostazioni() {
		super();
		
		this.setLayout(new BorderLayout());
		Properties p = System.getProperties();
		try {
			p.load(new FileInputStream("config/colors.txt"));
			colore_bottoni = new Color(Integer.parseInt(p.getProperty("bottoni_red")),Integer.parseInt(p.getProperty("bottoni_green")),Integer.parseInt(p.getProperty("bottoni_blue")));
			colore_sfondo = new Color(Integer.parseInt(p.getProperty("sfondo_red")),Integer.parseInt(p.getProperty("sfondo_green")),Integer.parseInt(p.getProperty("sfondo_blue")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		for(int i = 0; i< 16; i++) {
			JLabel lab = new JLabel();
			label.add(lab);
		}
		setBackground(colore_sfondo);
		
		bottoni = new ArrayList<JButton>();
		String nome = "";
		String data;
		try {
			File myObj = new File("config/colors.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				if(data.charAt(0) == '-') {
					nome = (data.substring(1, data.length()));
					JButton b;
					b = new JButton(nome);
					b.setBorder(new LineBorder(Color.BLACK));
					b.setFocusPainted(false);
					b.setBackground(colore_bottoni);
					b.setForeground(Color.BLACK);
					b.setFont(new Font("Serif", Font.BOLD, 20));
					bottoni.add(b);
				}
				else
					continue;
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			}	
	   
		bottone_esci = new JButton("INDIETRO");
		bottone_esci.setBorder(new LineBorder(Color.BLACK));
		bottone_esci.setFocusPainted(false);
		bottone_esci.setBackground(Color.RED);
		bottone_esci.setForeground(Color.WHITE);
		bottone_esci.setFont(new Font("Serif", Font.BOLD, 20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout((2+bottoni.size()),3));
		pannello.add(label.get(0));
		pannello.add(label.get(1));
		pannello.add(label.get(2));
		pannello.add(label.get(3));
		for(JButton b : bottoni) {
			pannello.add(b);
			pannello.add(new JLabel());
			pannello.add(new JLabel());
		}
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
		pannello_sopra.add(bottone_esci);
		this.add(pannello_sopra, BorderLayout.SOUTH);
	}
	
	
	public JButton getBottoneIndietro() {
		return this.bottone_esci;
	}
	public ArrayList<JButton> getAllBottoni(){
		return bottoni;
	}
	public void selectColor(String name) {
		String nome = "";
		String data,data1;
		String file = "";
		File myObj;
		Scanner myReader;
		BufferedWriter writer;
		
		//commenta tutto
		try {
			myObj = new File("config/colors.txt");
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				if(data.charAt(0) != '/' && data.charAt(0) != '-')
					file = file + "//" + data + '\n';
				else
					file = file + data + '\n';
			}
			myReader.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//scrivi tutto commentato
		try {
			writer = new BufferedWriter(new FileWriter("config/colors.txt"));
			writer.write(file);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//decommenta quello selezionato
		file = "";
		data= "";
		data1="";
		try {
			myObj = new File("config/colors.txt");
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				
				if(data.charAt(0) == '-') {
					nome = (data.substring(1, data.length()));
					if(nome.equals(name)) {//se leggi la riga con il nome che voglio usare decommenta le prossime sei:
						file = file + data + "\n";
						data1 = myReader.nextLine();
						data1 = data1.substring(2, data1.length());
						file = file + data1 + "\n";
						data1 = myReader.nextLine();
						data1 = data1.substring(2, data1.length());
						file = file + data1 + "\n";
						data1 = myReader.nextLine();
						data1 = data1.substring(2, data1.length());
						file = file + data1 + "\n";
						data1 = myReader.nextLine();
						data1 = data1.substring(2, data1.length());
						file = file + data1 + "\n";
						data1 = myReader.nextLine();
						data1 = data1.substring(2, data1.length());
						file = file + data1 + "\n";
						data1 = myReader.nextLine();
						data1 = data1.substring(2, data1.length());
						file = file + data1 + "\n";
						continue;
					}
				}
				file = file + data + "\n";
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//riscrivi tutto ma con quello selezionato senza commenti
		try {
			writer = new BufferedWriter(new FileWriter("config/colors.txt"));
			writer.write(file);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
