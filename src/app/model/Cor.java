package app.model;

public abstract class Cor implements java.io.Serializable {
	protected int id;
	protected int tipo;
	protected String nome;
	protected int simbolo;
	
	public Cor(int tipo) {
		this.setTipo(tipo);
	}
	
	public Cor(int id, int tipo) {
		this(tipo);
		this.setId(id);
	}
	
	public Cor(int id, int tipo, String nome, int simbolo) {
		this(id, tipo);
		this.setNome(nome);
		this.setSimbolo(simbolo);
	}
	
	public abstract int getLuminosidade();
	
	private void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	private void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public int getTipo() {
		return this.tipo;
	}
	
	public void setSimbolo(int simbolo) {
		this.simbolo = simbolo;
	}
	
	public int getSimbolo() {
		return this.simbolo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}