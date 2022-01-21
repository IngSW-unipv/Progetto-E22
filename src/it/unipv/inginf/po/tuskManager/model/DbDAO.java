package it.unipv.inginf.po.tuskManager.model;

import java.util.ArrayList;

import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;

public class DbDAO implements ITaskManagerDAO{

	@Override
	public ArrayList<String> selectAllEmails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Workspace> selectWorkspaceByAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workspace selectWorkspace(Workspace w) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Membro selectRuoloOfAccount(Workspace w, Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertIntoAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertIntoWorkspace(Workspace w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertIntoRuolo(Ruolo r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertIntoCompito(Workspace w, Scheda s, Compito c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createAssociazioneMembroWorkspace(Membro m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyCompito(Workspace w, Scheda s, Compito vecchio, Compito nuovo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyScheda(Workspace w, Scheda vecchia, Scheda nuova) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyMembro(Workspace w, Membro m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCompito(Workspace w, Scheda s, Compito c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMembro(Workspace w, Membro m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeWorkspace(Workspace w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(String user, String pw) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
