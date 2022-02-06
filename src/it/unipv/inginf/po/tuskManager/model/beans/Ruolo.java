package it.unipv.inginf.po.tuskManager.model.beans;

/**
 * Rappresenta un ruolo.
 * @version 1.0
 * @see Membro
 * */
public class Ruolo{
	private String nome;
	
	/**
	 * Crea un ruolo.
	 * @param nome Il nome del ruolo.
	 * */
	public Ruolo(String nome) {
		this.nome = nome;
		this.nome = this.nome.toLowerCase();
	}
	/**
	 * Sostituisce il nome del ruolo.
	 * @param nome Il nuovo nome.
	 * */
	public void setNome(String nome) {
		this.nome = nome;
		this.nome = this.nome.toLowerCase();
	}
	/**
	 * @return Il nome del ruolo.
	 * */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Due ruoli sono uguali se hanno lo stesso nome.
	 * @param o Il ruolo da confrontare.
	 * @return true se i ruoli sono uguali, false altrimenti.
	 * */
	@Override
	public boolean equals(Object o) {
		Ruolo r;
		try {
			r = (Ruolo)o;
		}catch(ClassCastException ex) {
			return false;
		}
		return r.getNome().equals(this.getNome());
	}
}
