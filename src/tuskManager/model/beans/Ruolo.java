package tuskManager.model.beans;

public class Ruolo implements Comparable<Ruolo>{
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
	public int compareTo(Ruolo o) {
		return this.nome.compareTo(o.getNome());
	}
}
