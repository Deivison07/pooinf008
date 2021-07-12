package app.enums;

public enum SimboloEnum {
	AREA_ABERTA(1, "AREA ABERTA"),
	FLORESTA(2, "FLORESTA"),
	VEGETACAO(3, "VEGETACAO"),
	LAGO(4, "LAGO"),
	RIO(5, "RIO"),
	CANAL(6, "CANAL"),
	ESTRADA(7, "ESTRADA"),
	TRILHA(8, "TRILHA"),
	CONSTRUCAO(9, "CONSTRUCAO");
	
	private int valor;
	private String nome;
	
	SimboloEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public static SimboloEnum obterPorNome(String nome) {
		for (SimboloEnum simbolo : SimboloEnum.values()) {
			if (simbolo.getNome().equals(nome))
				return simbolo;
		}
		
		throw new IllegalArgumentException("Simbolo solicitado nao encontrado.");
	}
	
	public static SimboloEnum obterPorValor(int valor) {
		for (SimboloEnum simbolo : SimboloEnum.values()) {
			if (simbolo.getValor() == valor)
				return simbolo;
		}
		
		throw new IllegalArgumentException("Simbolo solicitado nao encontrado.");
	}
}
