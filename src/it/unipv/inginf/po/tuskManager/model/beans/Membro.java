package it.unipv.inginf.po.tuskManager.model.beans;

/**
 * Estende la classe Account, aggiungendo un ruolo.
 * Rappresenta un membro del team associato ad un workspace, ha quindi un ruolo. 
 * @see Account
 * @see Ruolo
 * @version 1.0
 * */
public class Membro extends Account{

	private Ruolo ruolo;
	
	/**
	 * Crea un membro.
	 * @param email L'email dell'account.
	 * @param pw La password da usare per accedere.
	 * @param ruolo Il ruolo che questo account ha nel workspace. 
	 * */
	public Membro(String email, String pw, Ruolo ruolo) {
		super(email, pw);
		this.ruolo = ruolo;
	}
	
	/**
	 * @return Il ruolo del membro.
	 * */
	public Ruolo getRuolo() {
		return ruolo;
	}
	/**
	 * Assegna un nuovo ruolo al membro.
	 * @param ruolo Il nuovo ruolo.
	 * */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	
}
