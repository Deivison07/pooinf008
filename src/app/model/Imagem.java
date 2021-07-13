package app.model;

import java.util.Collection;

@SuppressWarnings("serial")
public class Imagem implements java.io.Serializable {
	private Cor pixels[][];
    
    public Imagem(int novaAltura, int novaLargura) {
        this.pixels = new Cor[novaAltura][novaLargura];
    }
    
    public Imagem(Cor pixels[][]) {
        this.pixels = pixels;
    }
    
    public int getAltura() {
        return this.pixels.length;
    }

    public int getLargura() {
        return this.pixels[0].length;
    }

    public int getTamanho() {
        return (this.getAltura() * this.getLargura());
    }
    
    private void validarPosicoes(int aPos, int lPos) throws IllegalArgumentException {
    	if ((aPos < 0 || aPos >= this.getAltura()) || (lPos < 0 || lPos >= this.getLargura())) {
        	String mensagem = String.format("Argumentos de posicao invalidos."
        			+ " Foi passado [%d,%d] quando o maximo suportado eh [%d,%d].",
        			aPos, lPos, this.getAltura(), this.getLargura());
        	
        	throw new IllegalArgumentException(mensagem);
        }
    }
    
    public Cor getPixel(int aPos, int lPos) throws IllegalArgumentException {
    	this.validarPosicoes(aPos, lPos);
        
        return this.pixels[aPos][lPos];
    }
    
    public void setPixel(int aPos, int lPos, Cor pixel) throws IllegalArgumentException {
    	this.validarPosicoes(aPos, lPos);
        
        if (pixel == null) {
        	throw new IllegalArgumentException("A cor nao pode ser nula.");
        }
        
        this.pixels[aPos][lPos] = pixel;
    }
    
    public boolean equals(Imagem img) {
    	for (int altura = 0; altura < this.getAltura(); altura++) {
            for (int largura = 0; largura < this.getLargura(); largura++) {
            	Cor pixel1 = this.getPixel(altura, largura);
            	Cor pixel2 = img.getPixel(altura, largura);
            	if (!pixel1.equals(pixel2))
            		return false;
            }
        }
    	
    	return true;
    }
    
    public double obterPercentualDeIgualdadePorCorRGB(Collection<Cor> cores) {
    	double percentual = 0;
    	
    	for (Cor cor : cores) {
    		percentual += this.obterPercentualDeIgualdadePorCorRGB(cor.toRGB());
    	}
    	
    	return percentual;
    }
    
    public double obterPercentualDeIgualdadePorCorRGB(CorRGB cor) throws IllegalArgumentException {
    	int totalDePixels = this.getTamanho();
    	int qtdSimilar = 0;
    	
    	for (int altura = 0; altura < this.getAltura(); altura++) {
            for (int largura = 0; largura < this.getLargura(); largura++) {
            	CorRGB pixel1 = this.getPixel(altura, largura).toRGB();
            	
                if (pixel1.equals(cor)) {
                	qtdSimilar += 1;
                }
            }
        }
    	
    	double pctSimilar = (qtdSimilar * 100) /  totalDePixels;
    	return pctSimilar;
    }
    
    public double obterPercentualDeSimilaridadePorLuminosidade(int lumMinima, int lumMaxima) throws IllegalArgumentException {
    	int totalDePixels = this.getTamanho();
    	int qtdSimilar = 0;
    	
    	for (int altura = 0; altura < this.getAltura(); altura++) {
            for (int largura = 0; largura < this.getLargura(); largura++) {
            	Cor pixel = this.getPixel(altura, largura);
            	int lumDoPixel = pixel.getLuminosidade();
            	
                if (lumDoPixel >= lumMinima && lumDoPixel <= lumMaxima) {
                	qtdSimilar += 1;
                }
            }
        }
    	
    	double pctSimilar = (qtdSimilar * 100) /  totalDePixels;
    	return pctSimilar;
    }
}