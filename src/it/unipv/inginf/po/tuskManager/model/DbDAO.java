package it.unipv.inginf.po.tuskManager.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.inginf.po.tuskManager.model.beans.Account;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.utils.DBConnection;

public class DbDAO implements ITaskManagerDAO{
	
	private Connection conn;
	
	@Override
	public ArrayList<String> selectAllEmails() throws CannotConnectToDbException {
		try {
			ArrayList<String> res = new ArrayList<String>();
			conn = DBConnection.startConnection();
			
			Statement statement = conn.createStatement();
			ResultSet resultset=statement.executeQuery("(SELECT EMAIL FROM TUSKMANAGER.UTENTE)");
			
			while(resultset.next()) {
				res.add(resultset.getString("EMAIL"));
			}
			conn = DBConnection.closeConnection(conn);
			return res;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public ArrayList<String> selectAllEmailsByWorkspace(Workspace w) throws CannotConnectToDbException {//da provare
		try {
			ArrayList<String> res = new ArrayList<String>();
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			ResultSet resultset;
			
			statement = conn.prepareStatement("(SELECT EMAIL FROM TUSKMANAGER.MEMBRO WHERE ID_WORKSPACE = ?)");
			statement.setInt(1,w.getId());
			
			resultset=statement.executeQuery();
			
			while(resultset.next()) {
				res.add(resultset.getString("EMAIL"));
			}
			conn = DBConnection.closeConnection(conn);
			return res;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public ArrayList<Workspace> selectWorkspaceByAccount(Account a) throws CannotConnectToDbException { // da provare
		try {
			ArrayList<Workspace> res = new ArrayList<Workspace>();
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			ResultSet resultset;
			
			statement = conn.prepareStatement("((SELECT ID_WORKSPACE, NOME FROM (TUSKMANAGER.UTENTE JOIN TUSKMANAGER.MEMBRO ON UTENTE.EMAIL = MEMBRO.EMAIL_UTENTE)JOIN TUSKMANAGER.WORKSPACE ON ID_WORKSPACE = WORKSPACE.ID WHERE EMAIL = ? GROUP BY ID_WORKSPACE))");
			statement.setString(1,a.getEmail());
			
			resultset=statement.executeQuery();
			
			while(resultset.next()) {
				res.add(new Workspace(resultset.getInt("ID_WORKSPACE"),resultset.getString("NOME"),null,null));
			}
			conn = DBConnection.closeConnection(conn);
			return res;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public Workspace selectWorkspace(Workspace w) throws CannotConnectToDbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Membro selectRuoloOfAccount(Workspace w, Account a) throws CannotConnectToDbException {
		try {
			Membro res = null;
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			ResultSet resultset;
			
			statement = conn.prepareStatement("(SELECT NOME_RUOLO FROM TUSKMANAGER.MEMBRO WHERE EMAIL_UTENTE = ? AND ID_WORKSPACE = ?)");
			statement.setString(1,a.getEmail());
			statement.setInt(2,w.getId());
			
			resultset=statement.executeQuery();
			
			while(resultset.next()) {
				res = new Membro(a.getEmail(), a.getPassword(), new Ruolo(resultset.getString("NOME_RUOLO")));
			}
			conn = DBConnection.closeConnection(conn);
			return res;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public Membro selectMembro(Workspace w, Membro m) throws CannotConnectToDbException {
		try {
			Membro res = null;
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			ResultSet resultset;
			
			statement = conn.prepareStatement("(SELECT NOME_RUOLO FROM TUSKMANAGER.MEMBRO WHERE EMAIL_UTENTE = ? AND ID_WORKSPACE = ?)");
			statement.setString(1,m.getEmail());
			statement.setInt(2,w.getId());
			
			resultset=statement.executeQuery();
			
			while(resultset.next()) {
				res = new Membro(m.getEmail(), m.getPassword(), new Ruolo(resultset.getString("NOME_RUOLO")));
			}
			conn = DBConnection.closeConnection(conn);
			return res;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean insertIntoAccount(Account a) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("INSERT INTO TUSKMANAGER.UTENTE(EMAIL,PW) VALUES(?,?)");
			statement.setString(1,a.getEmail());
			statement.setString(2,a.getPassword());
			
			statement.executeUpdate();
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean insertIntoWorkspace(Workspace w) throws CannotConnectToDbException {//da provare
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("INSERT INTO TUSKMANAGER.WORKSPACE(NOME) VALUES(?)");
			statement.setString(1,w.getNome());
			
			statement.executeUpdate();
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean insertIntoRuolo(Ruolo r) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("INSERT INTO TUSKMANAGER.RUOLO(NOME) VALUES(?)");
			statement.setString(1,r.getNome());
			
			statement.executeUpdate();
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}catch(SQLIntegrityConstraintViolationException e) {//se c'è gia va bene uguale
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean insertIntoScheda(Workspace w, Scheda s) throws CannotConnectToDbException { //da provare
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("INSERT INTO TUSKMANAGER.SCHEDA(TITOLO,ID_WORKSPACE) VALUES(?,?)");
			statement.setString(1,s.getTitolo());
			statement.setInt(2,w.getId());
			
			statement.executeUpdate();
			
			for(Compito c : s.getCompiti()) {
				insertIntoCompito(w,s,c);
			}
		
			conn = DBConnection.closeConnection(conn);
			return true;
		}catch(SQLIntegrityConstraintViolationException e) {
			conn = DBConnection.closeConnection(conn);
			return false;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean insertIntoCompito(Workspace w, Scheda s, Compito c) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("INSERT INTO TUSKMANAGER.COMPITO(TITOLO,DESCRIZIONE,SCADENZA,ID_WORKSPACE,TITOLO_SCHEDA) VALUES(?,?,?,?,?)");
			statement.setString(1,c.getTitolo());
			statement.setString(2,c.getDescrizione());
			statement.setDate(3,(Date) c.getScadenza());
			statement.setInt(4,w.getId());
			statement.setString(5,s.getTitolo());
			
			statement.executeUpdate();
			
			for(Ruolo r : c.getRuoli()) {
				statement = conn.prepareStatement("INSERT INTO TUSKMANAGER.GESTIONE(TITOLO_COMPITO,NOME_RUOLO) VALUES(?,?)");
				statement.setString(1,c.getTitolo());
				statement.setString(2,r.getNome());
				
				statement.executeUpdate();
			}
		
			conn = DBConnection.closeConnection(conn);
			return true;
		}catch(SQLIntegrityConstraintViolationException e) {
			conn = DBConnection.closeConnection(conn);
			return false;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean createAssociazioneMembroWorkspace(Workspace w, Membro m) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("INSERT INTO TUSKMANAGER.MEMBRO(EMAIL_UTENTE,ID_WORKSPACE,NOME_RUOLO) VALUES(?,?,?)");
			statement.setString(1,m.getEmail());
			statement.setString(2,m.getPassword());
			statement.executeUpdate();
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}catch(SQLIntegrityConstraintViolationException e) {//se c'è gia va bene uguale
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean modifyCompito(Workspace w, Scheda s, Compito vecchio, Compito nuovo) throws CannotConnectToDbException { //da provare
		try {
			conn = DBConnection.startConnection();
			
			removeCompito(w,s,vecchio);
			
			insertIntoCompito(w,s,nuovo);
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean modifyScheda(Workspace w, Scheda vecchia, Scheda nuova) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("DELETE FROM TUSKMANAGER.SCHEDA WHERE TITOLO = ? AND ID_WORKSPACE = ?");
			statement.setString(1,vecchia.getTitolo());
			statement.setInt(2,w.getId());
			
			statement.executeUpdate();
			
			insertIntoScheda(w, nuova);
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean modifyMembro(Workspace w, Membro m) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			removeMembro(w,m);
			
			createAssociazioneMembroWorkspace(w, m);
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean modifyWorkspace(Workspace vecchio, Workspace nuovo) throws CannotConnectToDbException {//serve a cambiare il nome e basta
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("UPDATE TUSKMANAGER.WORKSPACE SET NOME = ? WHERE ID = ?");
			statement.setString(1,nuovo.getNome());
			statement.setInt(2,vecchio.getId());
			
			statement.executeUpdate();
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean removeCompito(Workspace w, Scheda s, Compito c) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("DELETE FROM TUSKMANAGER.COMPITO WHERE TITOLO = ? AND TITOLO_SCHEDA = ? AND ID_WORKSPACE = ?");
			statement.setString(1,c.getTitolo());
			statement.setString(2,s.getTitolo());
			statement.setInt(3,w.getId());
			
			statement.executeUpdate();
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean removeMembro(Workspace w, Membro m) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			
			statement = conn.prepareStatement("DELETE FROM TUSKMANAGER.MEMBRO WHERE EMAIL_UTENTE = ? AND ID_WORKSPACE = ?");
			statement.setString(1,m.getEmail());
			statement.setInt(2,w.getId());
			
			statement.executeUpdate();
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}
		catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean removeWorkspace(Workspace w) throws CannotConnectToDbException {//da provare
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			ResultSet resultset;
			
			statement = conn.prepareStatement("(DELETE FROM TUSKMANAGER.WORKSPACE WHERE ID == ?)");
			statement.setInt(1,w.getId());
			
			statement.executeUpdate();
			
			
			conn = DBConnection.closeConnection(conn);
			return true;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}

	@Override
	public boolean login(String user, String pw) throws CannotConnectToDbException {
		try {
			conn = DBConnection.startConnection();
			
			PreparedStatement statement;
			ResultSet resultset;
			
			statement = conn.prepareStatement("(SELECT EMAIL FROM TUSKMANAGER.UTENTE WHERE EMAIL = ? AND PW = ?)");
			statement.setString(1,user);
			statement.setString(2,pw);
			
			resultset=statement.executeQuery();
			
			while(resultset.next()) {
				conn = DBConnection.closeConnection(conn);
				return true;
			}
			conn = DBConnection.closeConnection(conn);
			return false;
		}catch(Exception ex) {
			throw new CannotConnectToDbException();
		}
	}



}
