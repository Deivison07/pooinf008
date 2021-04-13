public class Imagem {
    private Cor pixels[][];
    private int altura;
    private int largura;
    
    private final int VALOR_MINIMO = 1;
    
    public Imagem(Cor pixels[][]){
        this.pixels = pixels;
        largura = pixels.length;
        altura = pixels[0].length;
    }

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
    
    public void setAltura(int novaAltura){
        this.altura = obterValorValido(novaAltura);
    }

    public void setLargura(int novaLargura){
        this.largura = obterValorValido(novaLargura);
    }
    
    public int getAltura(){
        return this.altura;
    }

    public int getLargura(){
        return this.largura;
    }
    
    public Cor getPixel(int aPos, int lPos) {
        if ((aPos < 0 || aPos > this.getAltura()) ||
            (lPos < 0 || lPos > this.getLargura())){
            return null;
        }
        
        return this.pixels[aPos][lPos];
    }
    
    public void setPixel(int aPos, int lPos, Cor pixel){
        if ((aPos < 0 || aPos > this.getAltura()) ||
            (lPos < 0 || lPos > this.getLargura())){
            return;
        }
        
        this.pixels[aPos][lPos] = pixel;
    }
    
    public void setPixel(int aPos, int lPos, int vermelho, int verde, int azul){
        this.setPixel(aPos, lPos, new Cor(vermelho, verde, azul));
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
    }
    
    /*
    Esse metodo verifica se as imagens sao iguais em todas as rotacoes de img
    
    cada iteracao do while vai validar se as imagens tem o mesmo tamanho,
    caso nao tenha vai rotacionar img ate que mesmoTamanho seja true
    
    apenas caso tenham o mesmo tamanho as imagens vao ser comparadas normamente numa repeticao
    
    ao final de 4 rotacoes, caso nao tenha caido na condicao true, as imagens sao diferentes em
    todas as perspectivas
    
    um clone de img eh gerado para nao alterar o estado do objeto original
    */
    public boolean equals(Imagem img){
        Imagem imgClone = img.clone();
        int rotacoes = 0;
        
        while(rotacoes <= 3){
            boolean mesmoTamanho = (imgClone.getAltura() == this.getAltura() && imgClone.getLargura() == this.getLargura());
            
            if (mesmoTamanho && compararImagens(imgClone)){
                return true;
            }
            
            imgClone.rotacionarParaDireita();
            rotacoes++;
        }
        
        return false;
    }
    
    /*
    metodo de suporte para o metodo equals que compara todos os pixel entre as imagens
    */
    private boolean compararImagens(Imagem img){
        for (int a = 0; a < this.getAltura(); a++){
            for (int l = 0; l < this.getLargura(); l++){
                Cor thisP = this.getPixel(a, l);
                Cor imgP = img.getPixel(a, l);
                
                if (!this.getPixel(a, l).equals(img.getPixel(a, l))){
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
                Cor corCinza = this.getPixel(a, l).obterCinza();
                imagemCinza.setPixel(a, l, corCinza);
            }
        }
        
        return imagemCinza;
    }
    
    /*
    Esse metodo verifica se o fragmento (frag) esta contido em uma imagem (this)
    
    cada iteracao do while vai validar se frag eh menor ou igual a imagem (this),
    caso nao seja vai rotacionar frag ate que suportaFragmento seja true
    
    apenas caso a condicao acima seja verdadeira as imagens vao ser comparadas
    
    ao final de 4 rotacoes, caso nao tenha caido na condicao true, a frag
    nao eh fragmento de imagem (this) em todas as perspectivas
    
    um clone de frag eh gerado para nao alterar o estado do objeto original
    */
    public boolean possuiComoFragmento(Imagem frag){
        Imagem fragClone = frag.clone();
        int rotacoes = 0;
        
        while(rotacoes <= 3){
            boolean suportaFragmento = (fragClone.getAltura() <= this.getAltura() && fragClone.getLargura() <= this.getLargura());
            
            if (suportaFragmento && possui(fragClone)){
                return true;
            }
            
            fragClone.rotacionarParaDireita();
            rotacoes++;
        }
        
        return false;
    }
    
    /*
    metodo de suporte para o metodo possuiComoFragmento que verifica se a imagem (this) tem como fragmento frag
    
    a ideia eh que o algoritmo percorra a imagem pai verificando se possui o fragmento
    
    a verificacao so vai ocorrer enquanto o fragmento couber na largura e na altura do ponto 0,0 da verificacao
    
    caso a verificacao no ponto x,y do pai falhar, o algoritmo vai continuar do onde parou, indo para a proxima linha/coluna
    */
    private boolean possui(Imagem frag){
        boolean igual = true;
        int aI, lI, aF, lF;
        
        for (aI = 0; aI < this.getAltura(); aI++){
            for (lI = 0; lI < this.getLargura() || !igual;){
                boolean cabeNaLargura = frag.getLargura() <= (this.getLargura() - lI);
                
                if (!cabeNaLargura){
                    break;
                }
                
                for (aF = 0; aF < frag.getAltura() && igual; aF++){
                    for (lF = 0; lF < frag.getLargura() && igual; lF++){
                        igual = (this.getPixel(aF, lF).equals(frag.getPixel(aI + aF, lI + lF)));
                    }
                }
                
                if (igual){
                    return true;
                }
            }
            
            boolean cabeNaAltura = frag.getAltura() <= (this.getAltura() - aI);
                
            if (!cabeNaAltura){
                break;
            }
        }
        return false;
    }
}