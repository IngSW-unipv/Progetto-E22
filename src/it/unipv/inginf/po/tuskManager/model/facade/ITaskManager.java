package it.unipv.inginf.po.tuskManager.model.facade;

import java.util.ArrayList;

import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;

public interface ITaskManager {
	public Membro getMembroLogged() throws CannotConnectToDbException;
	public Workspace getWorkspace() throws CannotConnectToDbException;
	public boolean createAccount(String email, String pw) throws CannotConnectToDbException;
	public boolean login(String email, String pw) throws CannotConnectToDbException;
	public void logout();
	public ArrayList<Workspace> getAllWorkspaces() throws CannotConnectToDbException;
	public Workspace getWorkspace(Workspace w) throws CannotConnectToDbException;
	public boolean createWorkspace(String nome) throws CannotConnectToDbException;
	public boolean addMembro(String email, Ruolo r) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException;
	public boolean createCompito(Scheda s, Compito c) throws RoleNotAcceptedException, CannotConnectToDbException;
	public boolean createScheda(Scheda s) throws RoleNotAcceptedException, CannotConnectToDbException;
	public boolean modifyCompito(Scheda s, Compito vecchio, Compito nuovo) throws RoleNotAcceptedException, CannotConnectToDbException;
	public boolean modifyMembro(String  email, Ruolo r) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException;
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo) throws RoleNotAcceptedException, CannotConnectToDbException;
	public boolean removeCompito (Compito c) throws RoleNotAcceptedException, CannotConnectToDbException;
	public boolean removeMembro(Membro m) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException;
	public boolean removeWorkspace(Workspace w) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException;
	

}
