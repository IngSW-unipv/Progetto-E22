package tuskManager.model.beans;

import java.util.ArrayList;
import java.util.Date;

/**
 * Rappresenta un compito.
 * @version 1.0
 * @see Scheda
 * @see Workspace
 * */
public class Compito implements Comparable<Compito>{

	private String titolo, descrizione;
	private Date scadenza;
	private ArrayList<Ruolo> ruoli;
	
	/**
	 * Crea un compito.
	 * @param titolo Il titolo del compito.
	 * @param descrizione La descrizione più dettagliata del compito.
	 * @param scadenza Deadline prevista per il compito.
	 * @param ruoli Lista di ruoli a cui associare il compito.
	 * */
	public Compito(String titolo, String descrizione, Date scadenza, ArrayList<Ruolo> ruoli) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.scadenza = scadenza;
		this.ruoli = ruoli;
	}
	
	/**
	 * @param titolo Nuovo titolo del compito.
	 * */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * @return Il titolo del compito.
	 * */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * @param descrizione Nuova descrizione del compito.
	 * */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * @return La descrizione del compito.
	 * */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param scadenza Nuova scadenza del compito.
	 * */
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	/**
	 * @return La scadenza del compito.
	 * */
	public Date getScadenza() {
		return scadenza;
	}
	/**
	 * @return La lista di ruoli associati al compito.
	 * */
	public ArrayList<Ruolo> getRuoli() {
		return ruoli;
	}
	
	/**
	 * @param ruolo Il ruolo da aggiungere.
	 * */
	public void addRuolo(Ruolo ruolo) {
		ruoli.add(ruolo);
	}
	/**
	 * @param ruolo Il ruolo da aggiungere.
	 * @return true se il ruolo è stato rimosso, false altrimenti.
	 * */
	public boolean removeRuolo(Ruolo ruolo) {
		return ruoli.remove(ruolo);
	}
	
	/**
	 * Uguali solo se hanno tutti i parametri uguali, ordinamento in base alla scadenza.
	 * */
	@Override
	public int compareTo(Compito c) {
		if(this.titolo.equals(c.getTitolo()) && this.descrizione.equals(c.getDescrizione()) && this.scadenza.equals(c.getScadenza()) && this.ruoli.equals(c.getRuoli())) {
			return 0;
		}
		return this.scadenza.compareTo(c.getScadenza());
	}
}
