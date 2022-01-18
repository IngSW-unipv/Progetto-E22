package it.unipv.inginf.po.tuskManager.model.beans;

import java.util.ArrayList;

public class Workspace {
	
	private int id;
	private String nome;
	private ArrayList<Membro> lista_membri;
	private ArrayList<Scheda> lista_schede;
	
	public Workspace(int id, String nome, ArrayList<Membro> lista_membri, ArrayList<Scheda> lista_schede) {
		this.setId(id);
		this.setNome(nome);
		this.lista_membri = lista_membri;
		this.lista_schede = lista_schede;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Membro> getLista_membri() {
		return lista_membri;
	}

	public ArrayList<Scheda> getLista_schede() {
		return lista_schede;
	}
	
	public void addMembro(Membro membro) {
		lista_membri.add(membro);
	}
	public boolean removeMembro(Membro membro) {
		return lista_membri.remove(membro);
	}
	
	public void addScheda(Scheda scheda) {
		lista_schede.add(scheda);
	}
	public boolean removeScheda(Scheda scheda) {
		return lista_schede.remove(scheda);
	}
	
}
