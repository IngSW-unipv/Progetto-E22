package tuskManager.model.beans;

public class Membro extends Account{

	private Ruolo ruolo;
	public Membro(String email, String pw, Ruolo ruolo) {
		super(email, pw);
		this.ruolo = ruolo;
	}
	
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	
}
