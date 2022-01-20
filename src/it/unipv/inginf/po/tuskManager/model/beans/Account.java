package it.unipv.inginf.po.tuskManager.model.beans;

/**
 * Rappresenta un Account.
 * @version 1.0
 * @see Membro
 * */

public class Account {
	protected String email, pw;
	
	/**
	 * Crea un Account.
	 * @param email L'email dell'utente.
	 * @param pw La password che l'utente userà per accedere.
	 * */
	public Account(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}
	
	/**
	 * Sostituisce l'email dell'account.
	 * @param email Nuova email dell'account.
	 * */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sostituisce la password dell'account.
	 * @param pw Nuova password dell'account.
	 * */
	public void setPassword(String pw) {
		this.pw = pw;
	}
	
	/**
	 * @return L'email dell'account.
	 * */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return La password dell'account.
	 * */
	public String getPassword() {
		return pw;
	}
	
}
