package it.unipv.inginf.po.tuskManager.model.persistence;

import java.util.ArrayList;

import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;

public interface IDao {
	
	public ArrayList<String> selectAllEmails() throws CannotConnectToDbException;
	public ArrayList<String> selectAllEmailsByWorkspace(Workspace w) throws CannotConnectToDbException;
	public ArrayList<Workspace> selectWorkspaceByAccount(Account a) throws CannotConnectToDbException;//ritorna solo id e nome workspace associati
	public Workspace selectWorkspace(Workspace w) throws CannotConnectToDbException;
	public Membro selectRuoloOfAccount(Workspace w, Account a) throws CannotConnectToDbException;
	public Membro selectMembro(Workspace w, Membro m) throws CannotConnectToDbException;
	
	public boolean insertIntoAccount(Account a) throws CannotConnectToDbException;
	public boolean insertIntoWorkspace(Workspace w) throws CannotConnectToDbException;
	public boolean insertIntoRuolo(Ruolo r) throws CannotConnectToDbException;
	public boolean insertIntoCompito(Workspace w, Scheda s, Compito c) throws CannotConnectToDbException;
	public boolean insertIntoScheda(Workspace w, Scheda s) throws CannotConnectToDbException;
	
	public boolean createAssociazioneMembroWorkspace(Workspace w, Membro m) throws CannotConnectToDbException;
	public boolean modifyCompito(Workspace w, Scheda s, Compito vecchio, Compito nuovo) throws CannotConnectToDbException;
	public boolean modifyMembro(Workspace w, Membro m) throws CannotConnectToDbException;
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo) throws CannotConnectToDbException;//serve a cambiare il nome e basta
	public boolean removeCompito(Workspace w, Scheda s, Compito c) throws CannotConnectToDbException;
	public boolean removeMembro(Workspace w, Membro m) throws CannotConnectToDbException;
	public boolean removeWorkspace(Workspace w) throws CannotConnectToDbException;
	public boolean login(String user, String pw) throws CannotConnectToDbException;
	
}
