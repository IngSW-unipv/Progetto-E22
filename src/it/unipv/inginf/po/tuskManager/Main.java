package it.unipv.inginf.po.tuskManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import it.unipv.inginf.po.tuskManager.model.DbDAO;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;

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
		
		
		DbDAO db = new DbDAO();
//		System.out.println(db.selectRuoloOfAccount(new Workspace(9,null,null,null),a).getRuolo().getNome());
		Workspace w = new Workspace(12,"nome",null,null);
//		Scheda s = new Scheda("titoloscheda",null);
//		Compito c = new Compito("nomecompito","descrizionecompito",new Date(2673272),null);
//		System.out.println(db.insertIntoCompito(w, s, c));
		
		w = db.selectWorkspace(w);
		
		System.out.println(w.getNome());
		for(Scheda s: w.getLista_schede()) {
			System.out.println(s.getTitolo());
			for(Compito c : s.getCompiti()) {
				System.out.println(c.getTitolo());
			}
		}
	
	}

}
