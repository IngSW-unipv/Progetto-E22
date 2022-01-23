package it.unipv.inginf.po.tuskManager.model;

import java.util.ArrayList;

import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;

public interface ITaskManagerDAO {
	
	public ArrayList<String> selectAllEmails() throws CannotConnectToDbException;
	public ArrayList<String> selectAllEmailsByWorkspace(Workspace w) throws CannotConnectToDbException;
	public ArrayList<Workspace> selectWorkspaceByAccount(Account a) throws CannotConnectToDbException;//ritorna solo id e nome workspace associati
	public Workspace selectWorkspace(Workspace w) throws CannotConnectToDbException;
	public Membro selectRuoloOfAccount(Workspace w, Account a) throws CannotConnectToDbException;
	public Membro selectMembro(Workspace w, Membro m) throws CannotConnectToDbException;
	
	public boolean insertIntoAccount(Account a);
	public boolean insertIntoWorkspace(Workspace w);
	public boolean insertIntoRuolo(Ruolo r);
	public boolean insertIntoCompito(Workspace w, Scheda s, Compito c);
	
	public boolean createAssociazioneMembroWorkspace(Membro m);
	public boolean modifyCompito(Workspace w, Scheda s, Compito vecchio, Compito nuovo);
	public boolean modifyScheda(Workspace w, Scheda vecchia, Scheda nuova);
	public boolean modifyMembro(Workspace w, Membro m);
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo);
	public boolean removeCompito(Workspace w, Scheda s, Compito c);
	public boolean removeMembro(Workspace w, Membro m);
	public boolean removeWorkspace(Workspace w);
	public boolean login(String user, String pw);
	
}
