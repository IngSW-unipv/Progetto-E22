package it.unipv.inginf.po.tuskManager.model.beans;

import java.util.ArrayList;
import java.util.Date;

/**
 * Rappresenta un compito.
 * @version 1.0
 * @see Scheda
 * @see Workspace
 * */
public class Compito{

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
	 * Sostituisce il titolo del compito.
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
	 * Sostituisce la descrizione del compito.
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
	 * Sostituisce la scadenza del compito.
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
	 * Aggiunge un ruolo alla lista dei ruoli associati al compito.
	 * @param ruolo Il ruolo da aggiungere.
	 * */
	public void addRuolo(Ruolo ruolo) {
		ruoli.add(ruolo);
	}
	/**
	 * Rimuove un ruolo dalla lista dei ruoli associati al compito.
	 * @param ruolo Il ruolo da rimuovere.
	 * @return true se il ruolo è stato rimosso, false altrimenti.
	 * */
	public boolean removeRuolo(Ruolo ruolo) {
		return ruoli.remove(ruolo);
	}
	
	/**
	 * Uguali solo se hanno tutti i parametri uguali e le liste di ruoli contengono gli stessi ruoli.
	 * @param o Compito da confrontare.
	 * @return true se @param o è uguale al compito.
	 * */
	@Override
	public boolean equals(Object o) {
		Compito c;
		try {
			c = (Compito)o;
		}catch(ClassCastException ex) {
			return false;
		}
		if (this.titolo.equals(c.getTitolo()) && this.descrizione.equals(c.getDescrizione()) && this.scadenza.equals(c.getScadenza())) {
			if(this.ruoli.containsAll(c.getRuoli()) && c.getRuoli().containsAll(this.getRuoli())) {
				return true;
			}
		}
		return false;
	}
}
