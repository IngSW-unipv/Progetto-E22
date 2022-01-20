package it.unipv.inginf.po.tuskManager.model.beans;

import java.util.ArrayList;

/**
 * Rappresenta una scheda.
 * @version 1.0
 * @see Compito
 * @see Workspace
 * */
public class Scheda{

	private String titolo;
	private ArrayList<Compito> lista_compiti;
	
	/**
	 * Crea una scheda.
	 * @param titolo Il titolo della scheda.
	 * @param lista_compiti La lista di compiti associati alla scheda.
	 * */
	public Scheda(String titolo, ArrayList<Compito> lista_compiti) {
		this.titolo = titolo;
		this.lista_compiti = lista_compiti;
	}
	
	/**
	 * @return Il titolo della scheda.
	 * */
	public String getTitolo() {
		return titolo;
	}
	
	/**
	 * Sostituisce il titolo della scheda.
	 * @param titolo Il nuovo titolo.
	 * */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	/**
	 * @return La lista di compiti della scheda.
	 * */
	public ArrayList<Compito> getCompiti() {
		return lista_compiti;
	}
	
	/**
	 * Aggiunge un compito alla lista di compiti associati alla scheda.
	 * @param compito Il compito da aggiungere.
	 * */
	public void addCompito(Compito compito) {
		lista_compiti.add(compito);
	}
	
	/**
	 * Rimuove un compito dalla lista di compiti associati alla scheda.
	 * @param compito Il compito da rimuovere.
	 * @return true se il compito è stato rimosso, false altrimenti.
	 * */
	public boolean removeCompito(Compito compito) {
		return lista_compiti.remove(compito);
	}
}
