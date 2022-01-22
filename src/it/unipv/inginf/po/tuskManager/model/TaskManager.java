package it.unipv.inginf.po.tuskManager.model;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;

import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;
import it.unipv.inginf.po.tuskManager.model.utils.EmailSender;

public class TaskManager {
	private ArrayList<Workspace> workspaces;
	private Membro membro_logged;
	private Workspace ws_selected;
	private ITaskManagerDAO db;
	private static TaskManager tm;
	
	private TaskManager() {
		db = new DbDAO();
	}
	
	public static TaskManager getInstance() {
		if(tm == null) {
			tm = new TaskManager();
		}
		return tm;
	}
	
	public Workspace getWorkspace() {
		updateWorkspace();
		return ws_selected;
	}
	
	private void updateWorkspace() {
		ws_selected = db.selectWorkspace(ws_selected);
		membro_logged = db.selectMembro(ws_selected, membro_logged);
	}
	private boolean sendMail(String email, String oggetto, String contenuto) throws CannotSendMailException {
		EmailSender es = new EmailSender("tuskmanagerSMC","passwordSMC","smtp.gmail.com"
										,"tuskmanagerSMC@gmail.com",email,oggetto,contenuto);
		boolean res = false;
		try {
			res = es.inviaEmail();
		}catch(AddressException ae) {
	      ae.printStackTrace();
	      throw new CannotSendMailException();
	    }catch(NoSuchProviderException nspe){
	      nspe.printStackTrace();
	      throw new CannotSendMailException();
	    }catch(MessagingException me){
	      me.printStackTrace();
	      throw new CannotSendMailException();
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    	throw new CannotSendMailException();
	    }
		return res;
	}
	
	public boolean createAccount(String email, String pw) {
		ArrayList<String> lista_email = db.selectAllEmails();
		if(lista_email.contains(email))
			return false;
		return db.insertIntoAccount(new Account(email,pw));
	}
	
	public boolean login(String email, String pw) {
		if(db.login(email, pw)) {
			membro_logged = db.selectRuoloOfAccount(ws_selected, new Account(email,pw));
			return true;
		}
		return false;
	}
	public void logout() {
		workspaces = null;
		membro_logged = null;
	}
	
	public ArrayList<Workspace> getAllWorkspaces(){
		workspaces = db.selectWorkspaceByAccount((Account)membro_logged);
		return workspaces;
	}
	
	public Workspace getWorkspace(Workspace w) {
		ws_selected = db.selectWorkspace(w);
		return ws_selected;
	}
	
	public boolean createWorkspace(String nome) {
		boolean res = db.insertIntoWorkspace(new Workspace(0,nome,null,null));
		updateWorkspace();
		return res;
	}
	public boolean addMembro(String email, Ruolo r) throws RoleNotAcceptedException, CannotSendMailException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			String oggetto = "Ciao, sei stato aggiunto ad un workspace su TuskManager!"; //MODIFICARE
			String contenuto = "";
			return sendMail(email,oggetto,contenuto);
		}else throw new RoleNotAcceptedException();
	}
	
	public boolean createRuolo(Ruolo r) throws RoleNotAcceptedException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			return db.insertIntoRuolo(r);
		}
		else throw new RoleNotAcceptedException();
	}
	
	public boolean createCompito(Scheda s, Compito c) throws RoleNotAcceptedException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			return db.insertIntoCompito(ws_selected, s, c);
		}
		else throw new RoleNotAcceptedException();
	}
	
	public boolean modifyCompito(Scheda s, Compito vecchio, Compito nuovo) throws RoleNotAcceptedException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager")) || vecchio.getRuoli().contains(membro_logged.getRuolo())) {
			return db.modifyCompito(ws_selected, s, vecchio, nuovo);
		}
		else throw new RoleNotAcceptedException();
	}
	
	public boolean modifyScheda(Scheda vecchia, Scheda nuova) {
		return db.modifyScheda(ws_selected, vecchia, nuova);
	}
	
	public boolean modifyMembro(Membro m, Ruolo r) throws RoleNotAcceptedException, CannotSendMailException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			if(db.modifyMembro(ws_selected, new Membro(m.getEmail(),null,r))) {
				String oggetto = "Ciao, il tuo ruolo nel workspace "+ws_selected.getNome()+"è cambiato!\nOra è: "+r.getNome()+" su TuskManager!"; //MODIFICARE
				String contenuto = "";
				return sendMail(m.getEmail(),oggetto,contenuto);
			}else {
				return false;
			}
		}
		else throw new RoleNotAcceptedException();
	}
	
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo) throws RoleNotAcceptedException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager")) ) {
			return db.modifyWorkspace(vecchio, nuovo);
		}
		else throw new RoleNotAcceptedException();
	}
	
	public boolean removeCompito (Scheda s, Compito c) throws RoleNotAcceptedException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager")) || c.getRuoli().contains(membro_logged.getRuolo())) {
			return db.removeCompito(ws_selected,s,c);
		}
		else throw new RoleNotAcceptedException();
	}
	public boolean removeMembro(Membro m) throws RoleNotAcceptedException, CannotSendMailException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			if(db.removeMembro(ws_selected,m)) {
				String oggetto = "Ciao, sei stato rimosso dal workspace "+ws_selected.getNome()+" su TuskManager!"; //MODIFICARE
				String contenuto = "";
				return sendMail(m.getEmail(),oggetto,contenuto);
			}else {
				return false;
			}
		}
		else throw new RoleNotAcceptedException();
	}
	
	public boolean removeWorkspace(Workspace w) throws RoleNotAcceptedException, CannotSendMailException{
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			ArrayList<String> emails = db.selectAllEmailsByWorkspace(ws_selected);
			if(db.removeWorkspace(w)) {
				String oggetto = "Ciao, il workspace "+ws_selected.getNome()+" è stato eliminato su TuskManager!"; //MODIFICARE
				String contenuto = "";
				for(String email : emails) {
					sendMail(email,oggetto,contenuto);
				}
				return true;
			}else {
				return false;
			}
		}
		else throw new RoleNotAcceptedException();
	}
	
	
}
