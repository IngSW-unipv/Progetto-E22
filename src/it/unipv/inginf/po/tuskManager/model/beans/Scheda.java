package it.unipv.inginf.po.tuskManager.model.beans;

import java.util.ArrayList;

public class Scheda{

	private String titolo;
	private ArrayList<Compito> lista_compiti;
	public Scheda(String titolo, ArrayList<Compito> lista_compiti) {
		this.titolo = titolo;
		this.lista_compiti = lista_compiti;
	}
	
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public ArrayList<Compito> getCompiti() {
		return lista_compiti;
	}
	
	public void addCompito(Compito compito) {
		lista_compiti.add(compito);
	}
	public boolean removeCompito(Compito compito) {
		return lista_compiti.remove(compito);
	}
}
