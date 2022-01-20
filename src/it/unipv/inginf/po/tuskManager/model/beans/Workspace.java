package it.unipv.inginf.po.tuskManager.model.beans;

import java.util.ArrayList;

/**
 * Rappresenta un workspace.
 * @version 1.0
 * @see Scheda
 * @see Compito
 * @see Membro
 * */
public class Workspace {
	
	private int id;
	private String nome;
	private ArrayList<Membro> lista_membri;
	private ArrayList<Scheda> lista_schede;
	
	/**
	 * Crea un nuovo workspace.
	 * @param id L'id associato al workspace.
	 * @param nome Il nome del workspace.
	 * @param lista_membri La lista dei membri associati al workspace.
	 * @param lista_schede La lista delle schede associate al workspace.
	 * */
	public Workspace(int id, String nome, ArrayList<Membro> lista_membri, ArrayList<Scheda> lista_schede) {
		this.setId(id);
		this.setNome(nome);
		this.lista_membri = lista_membri;
		this.lista_schede = lista_schede;
	}
	
	/**
	 * @return L'id del workspace.
	 * */
	public int getId() {
		return id;
	}
	
	/**
	 * Sostituisce l'id del workspace.
	 * @param id Il nuovo id.
	 * */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return Il nome del workspace.
	 * */
	public String getNome() {
		return nome;
	}

	/**
	 * Sostituisce il nome del workspace.
	 * @param nome Il nuovo nome.
	 * */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return La lista dei membri del workspace.
	 * */
	public ArrayList<Membro> getLista_membri() {
		return lista_membri;
	}
	
	/**
	 * @return La lista delle schede del workspace.
	 * */
	public ArrayList<Scheda> getLista_schede() {
		return lista_schede;
	}
	
	/**
	 * Aggiunge un membro alla lista dei membri.
	 * @param membro Il membro da aggiungere.
	 * */
	public void addMembro(Membro membro) {
		lista_membri.add(membro);
	}
	
	/**
	 * Rimuove un membro dalla lista dei membri.
	 * @param membro Il membro da rimuovere.
	 * @return true se il membro è stato rimosso, false altrimenti.
	 * */
	public boolean removeMembro(Membro membro) {
		return lista_membri.remove(membro);
	}
	
	/**
	 * Aggiunge una scheda alla lista delle schede.
	 * @param scheda La scheda da aggiungere.
	 * */
	public void addScheda(Scheda scheda) {
		lista_schede.add(scheda);
	}
	
	/**
	 * Rimuove una scheda dalla lista delle schede.
	 * @param scheda La scheda da rimuovere.
	 * @return true se la scheda è stata rimossa, false altrimenti.
	 * */
	public boolean removeScheda(Scheda scheda) {
		return lista_schede.remove(scheda);
	}
	
}
