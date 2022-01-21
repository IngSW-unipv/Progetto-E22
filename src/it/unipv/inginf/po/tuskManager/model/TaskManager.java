package it.unipv.inginf.po.tuskManager.model;

import java.util.ArrayList;

import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;

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
	}
	private boolean sendMail(String email, String testo) {//DA FARE
		return false;
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
		return db.insertIntoWorkspace(new Workspace(0,nome,null,null));
	}
	public boolean addMembro(String email, Ruolo r) {
		String testo = "Ciao, sei stato aggiunto ad un workspace su TuskManager!"; //MODIFICARE
		return sendMail(email,testo);
	}
	
	public boolean createRuolo(Ruolo r) {
		return db.insertIntoRuolo(r);
	}
	
	public boolean createCompito(Scheda s, Compito c) {
		return db.insertIntoCompito(ws_selected, s, c);
	}
	
	public boolean modifyCompito(Scheda s, Compito vecchio, Compito nuovo) {
		return db.modifyCompito(ws_selected, s, vecchio, nuovo);
	}
	
	public boolean modifyScheda(Scheda vecchia, Scheda nuova) {
		return db.modifyScheda(ws_selected, vecchia, nuova);
	}
	
	public boolean modifyMembro(Membro m, Ruolo r) {
		return db.modifyMembro(ws_selected, new Membro(m.getEmail(),null,r));
	}
	
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo) {
		return db.modifyWorkspace(vecchio, nuovo);
	}
	
	public boolean removeCompito (Scheda s, Compito c) {
		return db.removeCompito(ws_selected,s,c);
	}
	public boolean removeMembro(Membro m) {
		return db.removeMembro(ws_selected,m);
	}
	public boolean removeWorkspace(Workspace w) {
		return db.removeWorkspace(w);
	}
	
	
}
