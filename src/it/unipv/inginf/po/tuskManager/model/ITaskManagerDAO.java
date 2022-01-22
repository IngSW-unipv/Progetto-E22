package it.unipv.inginf.po.tuskManager.model;

import java.util.ArrayList;

import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;

public interface ITaskManagerDAO {
	
	public ArrayList<String> selectAllEmails();
	public ArrayList<String> selectAllEmailsByWorkspace(Workspace w);
	public ArrayList<Workspace> selectWorkspaceByAccount(Account a);//ritorna solo id e nome workspace associati
	public Workspace selectWorkspace(Workspace w);
	public Membro selectRuoloOfAccount(Workspace w, Account a);
	public Membro selectMembro(Workspace w, Membro m);
	
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
