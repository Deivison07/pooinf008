package app.model;

public class Imagem  {
    private Cor pixels[][];
    
    public Imagem(int novaAltura, int novaLargura) {
        this.pixels = new Cor[novaAltura][novaLargura];
    }
    
    public int getAltura() {
        return this.pixels.length;
    }

    public int getLargura() {
        return this.pixels[0].length;
    }
    
    private void validarPosicoes(int aPos, int lPos) throws IllegalArgumentException {
    	if ((aPos < 0 || aPos >= this.getAltura()) || (lPos < 0 || lPos >= this.getLargura())) {
        	String mensagem = String.format("Argumentos de posição inválidos."
        			+ " Foi passado [%d,%d] quando o máximo suportado é [%d,%d].",
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
        	throw new IllegalArgumentException("A cor não pode ser nula.");
        }
        
        this.pixels[aPos][lPos] = pixel;
    }
}