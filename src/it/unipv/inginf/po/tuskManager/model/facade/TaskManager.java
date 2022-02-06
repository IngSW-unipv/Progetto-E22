package it.unipv.inginf.po.tuskManager.model.facade;

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
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;
import it.unipv.inginf.po.tuskManager.model.facade.utils.EmailSender;
import it.unipv.inginf.po.tuskManager.model.facade.utils.PasswordCoder;
import it.unipv.inginf.po.tuskManager.model.persistence.DbDAO;
import it.unipv.inginf.po.tuskManager.model.persistence.IDao;

/**
 * Rappresenta il task manager stesso, fa comunicare controller e dao.
 * Applica, inoltre, regole e convenzioni scelte in fase di progettazione, quali l'invio della 
 * password non in chiaro e la convenzione scelta con cui denominare i ruoli. 
 * In particolare si occupa di conservare un'istanza del workpsace selezionato sempre aggiornata.
 * @version 1.0
 * @see Workspace
 * @see Scheda
 * @see Compito
 * @see Ruolo
 * @see DbDAO
 * */
public class TaskManager implements ITaskManager{
	private ArrayList<Workspace> workspaces;
	private Membro membro_logged;
	private Workspace ws_selected;
	private IDao dao;
	private static TaskManager tm;
	
	private TaskManager(IDao db) {
		this.dao = db;
	}
	
	/**
	 * @return L'istanza del task manager.
	 * */
	public static TaskManager getInstance(IDao db) {
		if(tm == null) {
			tm = new TaskManager(db);
		}
		return tm;
	}
	
	public Membro getMembroLogged() throws CannotConnectToDbException {
		return membro_logged;
	}
	/**
	 * @return Il workspace(aggiornato) precedentemente selezionato.
	 * */
	public Workspace getWorkspace() throws CannotConnectToDbException {
		updateWorkspace();
		return ws_selected;
	}
	
	/**
	 * Aggiorna le informazione di questa classe.
	 * */
	private void updateWorkspace() throws CannotConnectToDbException {
		ws_selected = dao.selectWorkspace(ws_selected);
		membro_logged = dao.selectMembro(ws_selected, membro_logged);
	}
	
	/**
	 * @see EmailSender
	 * */
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
	
	/**
	 * Crea un account.
	 * @param email
	 * @param pw
	 * @return true se l'inserimento � andato a buon fine, false altrimenti.
	 * */
	public boolean createAccount(String email, String pw) throws CannotConnectToDbException {
		ArrayList<String> lista_email = dao.selectAllEmails();
		if(lista_email.contains(email))
			return false;
		return dao.insertIntoAccount(new Account(email,PasswordCoder.codifica(pw)));
	}
	
	/**
	 * Effettua il login alla piattaforma.
	 * @param email
	 * @param pw
	 * @return true se login andato a buon fine.
	 * */
	public boolean login(String email, String pw) throws CannotConnectToDbException {
		if(dao.login(email, PasswordCoder.codifica(pw))) {
			membro_logged = new Membro(email,"",null);
			return true;
		}
		return false;
	}
	
	/**
	 * Cancella le informazioni contenute in questa classe.
	 * */
	public void logout() {
		workspaces = null;
		membro_logged = null;
		ws_selected = null;
	}
	
	/**
	 * Restituisce gli id dei workspace associati all'account che � loggato,
	 * permettendo cosi, in futuro, di selezionarne uno.
	 * @return Workspace associati.
	 * @see DbDAO
	 * */
	public ArrayList<Workspace> getAllWorkspaces() throws CannotConnectToDbException{
		workspaces = dao.selectWorkspaceByAccount((Account)membro_logged);
		return workspaces;
	}
	
	/**
	 * Seleziona un workspace dal db.
	 * @param w Workspace il cui id coincide con quello del workspace richiesto.
	 * @return Il workspace richiesto.
	 * */
	public Workspace getWorkspace(Workspace w) throws CannotConnectToDbException {
		ws_selected = dao.selectWorkspace(w);
		membro_logged.setRuolo(dao.selectRuoloOfAccount(ws_selected, (Account) membro_logged).getRuolo());
		return ws_selected;
	}
	
	/**
	 * Crea un workspace.
	 * @param nome Il nome del workspace.
	 * @return true se la creazione va a buon fine.
	 * */
	public boolean createWorkspace(String nome) throws CannotConnectToDbException {
		if(nome.isEmpty())
			return false;
		boolean res = dao.insertIntoWorkspace(new Workspace(0,nome,null,null));
		if(res) {
			ws_selected = dao.selectWorkspace(new Workspace(0,nome,null,null));
			dao.createAssociazioneMembroWorkspace(ws_selected,new Membro(membro_logged.getEmail()," ",new Ruolo("manager")));
		}
		return res;
	}
	
	/**
	 * Aggiunge un utente alla lista di utenti associati al workspace.
	 * @param email
	 * @param r Il ruolo addociato al nuovo utente.
	 * @return true se l'aggiunta � andata a buon fine.
	 * */
	public boolean addMembro(String email, Ruolo r) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException{
		updateWorkspace();
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			if(dao.createAssociazioneMembroWorkspace(ws_selected,new Membro(email," ",r))) {
				String oggetto = "NOTIFICA TUSKMANAGER"; 
				String contenuto = "Ciao!\nSei stato associato ad un workspace su TuskManager,\nse non sai di cosa si tratta visita : https://github.com/IngSW-unipv/Progetto-E22.git\n";
				return sendMail(email,oggetto,contenuto);
			}else
				return false;
		}else throw new RoleNotAcceptedException();
	}
	
	/**
	 * Crea un compito.
	 * @param s La scheda che deve contenere il compito.
	 * @param c 
	 * @return true se la creazione � andata a buon fine.
	 * */
	public boolean createCompito(Scheda s, Compito c) throws RoleNotAcceptedException, CannotConnectToDbException{
		updateWorkspace();
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			if(dao.insertIntoCompito(ws_selected, s, c)) {
				updateWorkspace();
				return true;
			}
			return false;
		}
		else throw new RoleNotAcceptedException();
	}
	
	/**
	 * Crea una scheda.
	 * @param s 
	 * @return true se la creazione � andata a buon fine.
	 * */
	public boolean createScheda(Scheda s) throws RoleNotAcceptedException, CannotConnectToDbException{
		updateWorkspace();
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			if(dao.insertIntoScheda(ws_selected, s)) {
				updateWorkspace();
				return true;
			}
			return false;
		}
		else throw new RoleNotAcceptedException();
	}
	
	/**
	 * Modifica un compito.
	 * @param s La scheda contentente il vecchio compito.
	 * @param vecchio Compito vecchio.
	 * @param nuovo Compito nuovo.
	 * @return true se la modifica � andata a buon fine. 
	 * */
	public boolean modifyCompito(Scheda s, Compito vecchio, Compito nuovo) throws RoleNotAcceptedException, CannotConnectToDbException{
		updateWorkspace();
		if(dao.modifyCompito(ws_selected, s, vecchio, nuovo)) {
			updateWorkspace();
			return true;
		}
		return false;
		
	}
	
	/**
	 * Modifica il ruolo di un membro.
	 * @param m
	 * @param r
	 * @return true se la modifica � andata a buon fine.
	 * */
	public boolean modifyMembro(String  email, Ruolo r) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException{
		updateWorkspace();
		if(membro_logged.getRuolo().equals(new Ruolo("manager")) && !email.equals(membro_logged.getEmail())) {
			if(dao.modifyMembro(ws_selected, new Membro(email,null,r))) {
				String contenuto = "Ciao!\nIl tuo ruolo nel workspace "+ws_selected.getNome()+" e' cambiato!\nOra e': "+r.getNome(); 
				String oggetto = "NOTIFICA TUSKMANAGER";
				updateWorkspace();
				return sendMail(email,oggetto,contenuto);
			}else {
				return false;
			}
		}
		else throw new RoleNotAcceptedException();
	}
	
	/**
	 * Modifica un workspace.
	 * @param vecchio
	 * @param nuovo
	 * @return true se la modifica � andata a buon fine.
	 * */
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo) throws RoleNotAcceptedException, CannotConnectToDbException{
		updateWorkspace();
		if(membro_logged.getRuolo().equals(new Ruolo("manager")) ) {
			if(dao.modifyWorkspace(vecchio, nuovo)) {
				return true;
			}
			return false;
		}
		else throw new RoleNotAcceptedException();
	}
	
	/**
	 * Rimuove un compito.
	 * @param s La scheda che contiene il compito.
	 * @param c Il compito.
	 * @return true se la rimozione � andata a buon fine.
	 * */
	public boolean removeCompito (Compito c) throws RoleNotAcceptedException, CannotConnectToDbException{
		updateWorkspace();
		Scheda scheda_sel = null;
		for(Scheda sc : ws_selected.getLista_schede()) {
			for(Compito co : sc.getCompiti()) {
				if(co.getTitolo().equals(c.getTitolo())) {
					scheda_sel = sc;
				}
			}
		}
		if(scheda_sel == null) {
			return false;
		}
		
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			if(dao.removeCompito(ws_selected,scheda_sel,c)) {
				updateWorkspace();
				return true;
			}
			return false;
		}
		else throw new RoleNotAcceptedException();
	}
	
	/**
	 * Rimuove un membro, inviando anche una mail di avviso.
	 * @param m
	 * @return true se la rimozione � andata a buon fine.
	 * */
	public boolean removeMembro(Membro m) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException{
		updateWorkspace();
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			if(dao.removeMembro(ws_selected,m)) {
				String contenuto = "Ciao!\nSei stato rimosso dal workspace "+ws_selected.getNome()+".\nControlla le tue restanti attivita' su: https://github.com/IngSW-unipv/Progetto-E22.git\n"; 
				String oggetto = "NOTIFICA TUSKMANAGER";
				updateWorkspace();
				return sendMail(m.getEmail(),oggetto,contenuto);
			}else {
				return false;
			}
		}
		else throw new RoleNotAcceptedException();
	}
	
	/**
	 * Elimina un workspace, inviando anche una mail di avviso ai membri coinvolti.
	 * @param w
	 * @return true se la rimozione � andata a buon fine.
	 * */
	public boolean removeWorkspace(Workspace w) throws RoleNotAcceptedException, CannotSendMailException, CannotConnectToDbException{
		
		if(membro_logged.getRuolo().equals(new Ruolo("manager"))) {
			ArrayList<String> emails = dao.selectAllEmailsByWorkspace(ws_selected);
			if(dao.removeWorkspace(w)) {
				String contenuto = "Ciao!\nIl workspace "+ws_selected.getNome()+" e' stato eliminato.\nControlla lo stato delle tue restanti attivita' sulla nostra applicazione.\nSe non sai di cosa stiamo parlando visita: https://github.com/IngSW-unipv/Progetto-E22.git\n"; 
				String oggetto = "NOTIFICA TUSKMANAGER";
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
