package it.unipv.inginf.po.tuskManager;

import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import it.unipv.inginf.po.tuskManager.model.DbDAO;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;
import it.unipv.inginf.po.tuskManager.view.apertura.Accedi;
import it.unipv.inginf.po.tuskManager.view.apertura.Apertura;
import it.unipv.inginf.po.tuskManager.view.apertura.Registrati;
import it.unipv.inginf.po.tuskManager.view.homepage.HomePage;
import it.unipv.inginf.po.tuskManager.view.homepage.Seleziona;

/**
 * Classe utilizzata semplicemente per contenere il main.
 * @version 1.0
 * */
public class Main {

	/**
	 * @param args Argomenti su linea di comando.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 * @throws CannotConnectToDbException 
	 * @throws CannotSendMailException 
	 * @throws RoleNotAcceptedException 
	 * */
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, CannotConnectToDbException, RoleNotAcceptedException, CannotSendMailException {

//		TaskManager t = TaskManager.getInstance();
//		
//		Account a = new Account("primo","pass");
//		System.out.println(t.login(a.getEmail(),a.getPassword()));
//		
//		System.out.println(t.removeWorkspace(new Workspace(9,"dd",null,null)));
		
		
//		DbDAO db = new DbDAO();
//		System.out.println(db.selectRuoloOfAccount(new Workspace(9,null,null,null),a).getRuolo().getNome());
//		Workspace w = new Workspace(12,"nome",null,null);
//		Scheda s = new Scheda("titoloscheda",null);
//		Compito c = new Compito("nomecompito","descrizionecompito",new Date(2673272),null);
//		System.out.println(db.insertIntoCompito(w, s, c));
		
//		w = db.selectWorkspace(w);
//		
//		System.out.println(w.getNome());
//		for(Scheda s: w.getLista_schede()) {
//			System.out.println(s.getTitolo());
//			for(Compito c : s.getCompiti()) {
//				System.out.println(c.getTitolo());
//			}
//		}
		
		JFrame frame = new JFrame();
		ArrayList<String> array = new ArrayList<String>();
		array.add("ciao");
		
		Seleziona s = new Seleziona(array);
		frame.add(new Accedi());
//		frame.add(s);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 900);
		frame.setVisible(true);
		frame.setResizable(false);
		try {
		    frame.setIconImage(ImageIO.read(new File("assets/background.jpg")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
	
	}

}
