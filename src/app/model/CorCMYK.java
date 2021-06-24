package app.model;

public class CorCMYK extends Cor {
    private int ciano;
    private int magenta;
    private int amarelo;
    private int preto;

    public static final int VALOR_MAXIMO = 100;
    public static final int VALOR_MINIMO = 0;
    
    public CorCMYK(int ciano, int magenta, int amarelo, int preto) {
        this.setCiano(ciano);
        this.setMagenta(magenta);
        this.setAmarelo(amarelo);
        this.setPreto(preto);
    }

    public CorCMYK() {
        this(CorCMYK.VALOR_MINIMO, CorCMYK.VALOR_MINIMO, CorCMYK.VALOR_MINIMO, CorCMYK.VALOR_MINIMO);
    }

    public CorCMYK(CorCMYK instancia) {
        this(instancia.getCiano(), instancia.getMagenta(), instancia.getAmarelo(), instancia.getPreto());
    }
    
    private int obterValorValido(int valor) {
        return valor < CorCMYK.VALOR_MINIMO ? CorCMYK.VALOR_MINIMO : valor > CorCMYK.VALOR_MAXIMO ? CorCMYK.VALOR_MAXIMO : valor;
    }

    private void setCiano(int ciano) {
        this.ciano = this.obterValorValido(ciano);
    }

    private void setMagenta(int magenta) {
        this.magenta = this.obterValorValido(magenta);
    }

    private void setAmarelo(int amarelo) {
        this.amarelo = this.obterValorValido(amarelo);
    }

    private void setPreto(int preto) {
        this.preto = this.obterValorValido(preto);
    }

    private int getCiano() {
    	return this.ciano;
    }

    private int getMagenta() {
    	return this.magenta;
    }

    private int getAmarelo() {
    	return this.amarelo;
    }

    private int getPreto() {
    	return this.preto;
    }
    
	public int getLuminosidade() {
		return (int) ((preto * 255) / 100);
	}
	
	public CorCMYK getCinzaEquivalente() {
        int luminosidade = this.getLuminosidade();
        return new CorCMYK(luminosidade, luminosidade, luminosidade, luminosidade);
    }
}
