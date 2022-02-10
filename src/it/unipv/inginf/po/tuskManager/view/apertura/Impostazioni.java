package it.unipv.inginf.po.tuskManager.view.apertura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;

public class Impostazioni extends JPanel{
	
	private static Color colore_bottoni = new Color(255,128,0);
//	private static Color colore_sfondo = new Color(255,178,102);
	ArrayList<JTuskButton> bottoni;
	private static final long serialVersionUID = 1L;
	private JTuskButton bottone_esci;
	private Image img;
	public Impostazioni(Image image, Color col_bottoni) {
		super();
		img = image;
		colore_bottoni = col_bottoni;
		this.setLayout(new BorderLayout());
		bottoni = new ArrayList<JTuskButton>();
		String nome = "";
		String data;
		try {
			File myObj = new File("config/colors.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				if(data.charAt(0) == '-') {
					nome = (data.substring(1, data.length()));
					JTuskButton b;
					b = new JTuskButton(nome,colore_bottoni, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
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
	   
		bottone_esci = new JTuskButton("INDIETRO",Color.RED, Color.BLACK,false,new Dimension(150,75),new Dimension(20,20));
		
		JPanel pannello = new JPanel();
		pannello.setOpaque(false);
		pannello.setLayout(new GridLayout((3+bottoni.size()),3));
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		pannello.add(new JLabel());
		for(JTuskButton b : bottoni) {
			pannello.add(b);
			pannello.add(new JLabel());
			pannello.add(new JLabel());
		}
		pannello.add(bottone_esci);
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
//		bottone_esci.setBounds(bottone_esci, 0, (int)this.getSize().width/8, (int)this.getSize().height/12);

	}
	
	public JTuskButton getBottoneIndietro() {
		return this.bottone_esci;
	}
	public ArrayList<JTuskButton> getAllBottoni(){
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
