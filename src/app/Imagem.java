package app;

public class Imagem {
    private Cor pixels[][];
    private int altura;
    private int largura;
    
    private final int VALOR_MINIMO = 1;

    public Imagem(int novaAltura, int novaLargura){
        setLargura(novaLargura);
        setAltura(novaAltura);
        
        this.pixels = new Cor[novaAltura][novaLargura];
        
        limparImagem();
    }
    
    private boolean validarValor(int valor){
        return valor >= VALOR_MINIMO;
    }
    
    private int obterValorValido(int valor){
        return validarValor(valor) ? valor : VALOR_MINIMO;
    }
    
    private void setAltura(int novaAltura){
        this.altura = obterValorValido(novaAltura);
    }

    private void setLargura(int novaLargura){
        this.largura = obterValorValido(novaLargura);
    }
    
    public int getAltura(){
        return this.altura;
    }

    public int getLargura(){
        return this.largura;
    }
    
    public Cor getPixel(int aPos, int lPos) {
        if ((aPos < 0 || aPos >= this.getAltura()) ||
            (lPos < 0 || lPos >= this.getLargura())){
            return null;
        }
        
        return this.pixels[aPos][lPos];
    }
    
    public void setPixel(int aPos, int lPos, Cor pixel){
        if ((aPos < 0 || aPos >= this.getAltura()) ||
            (lPos < 0 || lPos >= this.getLargura())){
            return;
        }
        
        this.pixels[aPos][lPos] = pixel;
    }
    
    public void setPixel(int aPos, int lPos, int vermelho, int verde, int azul){
        this.setPixel(aPos, lPos, new Cor(vermelho, verde, azul));
    }
    
    private void atualizarTamanho(){
        this.setLargura(this.pixels[0].length);
        this.setAltura(this.pixels.length);
    }
    
    private void limparImagem(){
        for (int a = 0; a < this.getAltura(); a++){
            for (int l = 0; l < this.getLargura(); l++){
                this.pixels[a][l] = Cor.BRANCA;
            }
        }
    }
    
    public Imagem clone(){
        Imagem clone = new Imagem(this.getAltura(), this.getLargura());
        
        for (int a = 0; a < this.getAltura(); a++){
            for (int l = 0; l < this.getLargura(); l++){
                clone.setPixel(a, l, this.getPixel(a, l));
            }
        }
        
        return clone;
    }
    
    public void rotacionarParaDireita(){
        Cor novosPixels[][] = new Cor[this.getLargura()][this.getAltura()];
        
        for (int a = 0, nL = this.getAltura() - 1; a < this.getAltura(); a++, nL--){
            for (int l = 0, nA = 0; l < this.getLargura(); l++, nA++){
                novosPixels[nA][nL] = this.pixels[a][l];
            }
        }
        
        this.pixels = novosPixels;
        this.atualizarTamanho();
    }
    
    public boolean equals(Imagem img){
        Imagem imgClone = img.clone();
        int rotacoes = 0;
        
        while(rotacoes <= 3){
            boolean mesmoTamanho = (imgClone.getAltura() == this.getAltura() && imgClone.getLargura() == this.getLargura());
            
            if (mesmoTamanho && this.compararImagens(imgClone)){
                return true;
            }
            
            imgClone.rotacionarParaDireita();
            rotacoes++;
        }
        
        return false;
    }
    
    private boolean compararImagens(Imagem img){
        for (int a = 0; a < this.getAltura(); a++){
            for (int l = 0; l < this.getLargura(); l++){
                if (!this.getPixel(a, l).verificaIgualdade(img.getPixel(a, l))){
                    return false;
                }
            }
        }
        return true;
    }
    
    public Imagem obterCinza(){
        Imagem imagemCinza = new Imagem(this.getAltura(), this.getLargura());
        
        for (int a = 0; a < this.getAltura(); a++){
            for (int l = 0; l < this.getLargura(); l++){
                Cor corCinza = this.getPixel(a, l).gerarCinzaEquivalente();
                imagemCinza.setPixel(a, l, corCinza);
            }
        }
        
        return imagemCinza;
    }
    
    public boolean possuiComoFragmento(Imagem frag){
        Imagem fragClone = frag.clone();
        int rotacoes = 0;
        
        while(rotacoes <= 3){
            boolean suportaFragmento = (fragClone.getAltura() <= this.getAltura() && fragClone.getLargura() <= this.getLargura());
            
            if (suportaFragmento && this.possui(fragClone)){
                return true;
            }
            
            fragClone.rotacionarParaDireita();
            rotacoes++;
        }
        
        return false;
    }
    
    private boolean possui(Imagem frag){
        int aI, lI, aF, lF;
        boolean igual;
        
        for (aI = 0; aI < this.getAltura(); aI++){
            boolean cabeNaAltura = frag.getAltura() <= (this.getAltura() - aI);
                
            if (!cabeNaAltura){
                break;
            }
            
            for (lI = 0; lI < this.getLargura(); lI++){
                boolean cabeNaLargura = frag.getLargura() <= (this.getLargura() - lI);
                
                if (!cabeNaLargura){
                    break;
                }
                
                for (aF = 0, igual = true; aF < frag.getAltura() && igual; aF++){
                    for (lF = 0; lF < frag.getLargura() && igual; lF++){
                        igual = this.getPixel(aI + aF, lI + lF).verificaIgualdade(frag.getPixel(aF, lF));
                    }
                }
                
                if (igual){
                    return true;
                }
            }
        }

        return false;
    }
}