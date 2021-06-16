package app;

public class Imagem {
    private Cor pixels[][];
    
    public Imagem(int novaAltura, int novaLargura){
        this.pixels = new Cor[novaAltura][novaLargura];
        
        limparImagem();
    }
    
    public int getAltura(){
        return this.pixels.length;
    }

    public int getLargura(){
        return this.pixels[0].length;
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
    
    private void limparImagem(){
        for (int altura = 0; altura < this.getAltura(); altura++){
            for (int largura = 0; largura < this.getLargura(); largura++){
                this.pixels[altura][largura] = Cor.BRANCA;
            }
        }
    }
    
    public Imagem clone(){
        Imagem iClone = new Imagem(this.getAltura(), this.getLargura());
        
        for (int altura = 0; altura < this.getAltura(); altura++){
            for (int largura = 0; largura < this.getLargura(); largura++){
            	iClone.setPixel(altura, largura, this.getPixel(altura, largura).clone());
            }
        }
        
        return iClone;
    }
    
    public void rotacionarParaDireita(){
        Cor novosPixels[][] = new Cor[this.getLargura()][this.getAltura()];
        
        for (int altura = 0, rotLargura = this.getAltura() - 1; altura < this.getAltura(); altura++, rotLargura--){
            for (int largura = 0, rotAltura = 0; largura < this.getLargura(); largura++, rotAltura++){
                novosPixels[rotAltura][rotLargura] = this.pixels[altura][largura];
            }
        }
        
        this.pixels = novosPixels;
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
        for (int altura = 0; altura < this.getAltura(); altura++){
            for (int largura = 0; largura < this.getLargura(); largura++){
                if (!this.getPixel(altura, largura).equals(img.getPixel(altura, largura))){
                    return false;
                }
            }
        }
        return true;
    }
    
    public Imagem obterCinza(){
        Imagem imagemCinza = new Imagem(this.getAltura(), this.getLargura());
        
        for (int altura = 0; altura < this.getAltura(); altura++){
            for (int largura = 0; largura < this.getLargura(); largura++){
                Cor corCinza = this.getPixel(altura, largura).gerarCinzaEquivalente();
                imagemCinza.setPixel(altura, largura, corCinza);
            }
        }
        
        return imagemCinza;
    }
    
    public boolean possuiComoFragmento(Imagem frag){
        Imagem fragClone = frag.clone();
        int rotacoes = 0;
        
        while(rotacoes <= 3){
            boolean suportaFragmento = (fragClone.getAltura() <= this.getAltura() && fragClone.getLargura() <= this.getLargura());
            
            if (suportaFragmento && this.fragmento(fragClone)){
                return true;
            }
            
            fragClone.rotacionarParaDireita();
            rotacoes++;
        }
        
        return false;
    }
    
    private boolean fragmento(Imagem frag){
        int imgAltura, imgLargura, fragAltura, fragLargura;
        boolean igual;
        
        for (imgAltura = 0; imgAltura < this.getAltura(); imgAltura++){
            boolean cabeNaAltura = frag.getAltura() <= (this.getAltura() - imgAltura);
                
            if (!cabeNaAltura){
                break;
            }
            
            for (imgLargura = 0; imgLargura < this.getLargura(); imgLargura++){
                boolean cabeNaLargura = frag.getLargura() <= (this.getLargura() - imgLargura);
                
                if (!cabeNaLargura){
                    break;
                }
                
                for (fragAltura = 0, igual = true; fragAltura < frag.getAltura() && igual; fragAltura++){
                    for (fragLargura = 0; fragLargura < frag.getLargura() && igual; fragLargura++){
                    	int imgLPos = imgLargura + fragLargura;
                    	int imgAPos = imgAltura + fragAltura;
                    	
                        igual = this.getPixel(imgAPos, imgLPos).equals(frag.getPixel(fragAltura, fragLargura));
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