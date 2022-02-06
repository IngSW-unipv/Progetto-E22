package it.unipv.inginf.po.tuskManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import it.unipv.inginf.po.tuskManager.controller.Controller;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;
import it.unipv.inginf.po.tuskManager.model.facade.TaskManager;
import it.unipv.inginf.po.tuskManager.model.persistence.DbDAO;
import it.unipv.inginf.po.tuskManager.view.TMFrame;
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

		DbDAO db = new DbDAO();
		TaskManager t = TaskManager.getInstance(db);
		TMFrame frame = new TMFrame();
		@SuppressWarnings("unused")
		Controller c = new Controller(frame,t);
	
	}

}