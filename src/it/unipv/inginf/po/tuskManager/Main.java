package it.unipv.inginf.po.tuskManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import it.unipv.inginf.po.tuskManager.model.DbDAO;
import it.unipv.inginf.po.tuskManager.model.TaskManager;
import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;
import it.unipv.inginf.po.tuskManager.model.utils.DBConnection;

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
		Account a = new Account("primo","pass");
//		System.out.println(t.login(a.getEmail(),a.getPassword()));
//		
//		System.out.println(t.removeWorkspace(new Workspace(9,"dd",null,null)));
		
		
		DbDAO db = new DbDAO();
//		System.out.println(db.selectRuoloOfAccount(new Workspace(9,null,null,null),a).getRuolo().getNome());
		Workspace w = new Workspace(10,"nome",null,null);
		Scheda s = new Scheda("titoloscheda",null);
		Compito c = new Compito("nomecompito","descrizionecompito",new Date(2673272),null);
		System.out.println(db.insertIntoCompito(w, s, c));
	
	
	}

}
