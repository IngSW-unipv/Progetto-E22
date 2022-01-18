package it.unipv.inginf.po.tuskManager.model.beans;

public class Ruolo{
	private String nome;
	
	public Ruolo(String nome) {
		this.nome = nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	@Override
	public boolean equals(Object o) {
		Ruolo r;
		try {
			r = (Ruolo)o;
		}catch(ClassCastException ex) {
			return false;
		}
		if(r.getNome().equals(this.getNome())) {
			return true;
		}
		return false;
	}
}
