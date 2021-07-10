package app.enums;

public enum CorEnum {
	RGB(1),
	CMYK(2);

	private int valor;
	
	CorEnum(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
}
