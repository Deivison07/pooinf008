package app.model;

import app.conversor.ConversorCorCMYKEmRGB;
import app.enums.CorEnum;

@SuppressWarnings("serial")
public class CorCMYK extends Cor {
    private int ciano;
    private int magenta;
    private int amarelo;
    private int preto;

    public static final int VALOR_MAXIMO = 100;
    public static final int VALOR_MINIMO = 0;
    
    public CorCMYK(int id, String nome, int simbolo, int ciano, int magenta, int amarelo, int preto) {
        super(id, CorEnum.CMYK.getValor(), nome, simbolo);
        this.setCiano(ciano);
        this.setMagenta(magenta);
        this.setAmarelo(amarelo);
        this.setPreto(preto);
    }
    
    public CorCMYK(String nome, int simbolo, int ciano, int magenta, int amarelo, int preto) {
        this(0, nome, simbolo, ciano, magenta, amarelo, preto);
    }
    
    public CorCMYK(int simbolo, int ciano, int magenta, int amarelo, int preto) {
    	this("", simbolo, ciano, magenta, amarelo, preto);
    }

    public CorCMYK(int simbolo) {
        this(simbolo, CorCMYK.VALOR_MINIMO, CorCMYK.VALOR_MINIMO, CorCMYK.VALOR_MINIMO, CorCMYK.VALOR_MINIMO);
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

    public int getCiano() {
    	return this.ciano;
    }

    public int getMagenta() {
    	return this.magenta;
    }

    public int getAmarelo() {
    	return this.amarelo;
    }

    public int getPreto() {
    	return this.preto;
    }
    
    @Override
	public int getLuminosidade() {
		return (int) ((this.getPreto() * 255) / 100);
	}

	@Override
	public CorRGB toRGB() {
		ConversorCorCMYKEmRGB conversor = new ConversorCorCMYKEmRGB();
		return (CorRGB) conversor.converter(this);
	}

	@Override
	public boolean equals(Cor cor) {
		if (!(cor instanceof CorCMYK))
			return false;
		
		CorCMYK corCMYK = (CorCMYK) cor;
		
		return 	this.getCiano() == corCMYK.getCiano() &&
				this.getMagenta() == corCMYK.getMagenta() &&
				this.getAmarelo() == corCMYK.getAmarelo() &&
				this.getPreto() == corCMYK.getPreto();
	}
}
