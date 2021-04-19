
public class Imagem {
    
    private Cor cor[][];
    private int altura;
    private int largura;

    //

    public Imagem(int novaAltura, int novaLargura){
    setAltura(novaAltura);
    setLargura(novaLargura);
    this.cor[novaAltura][novaLargura];
    setAllWhite();
    }

    public Imagem(){
        this.altura = 1;
        this.largura = 1;
        this.cor[this.altura][this.largura];
    }

    public void setAltura(int novaAltura){
        this.altura = novaAltura;
    }

    public void setLargura(int novaLargura){
        this.largura = novaLargura;
    }

    private void setAllWhite(){

        for (int a = 0; a < this.altura; a++){
            for (int l = 0; l < this.largura; l++){
                this.cor[a][l] = Cor.BRANCA;
            }
        }
        
    }

    private boolean validaPosicao(int posicaoX, int posicaoY){
        if ((posicaoX >= 0) && (posicaoX<=this.altura) && (posicaoY >= 0) && (posicaoY<=this.largura){
            return true;
        }
        else{
            return false;
        }

    }


    public void modificaPixel(int posicaoX, int posicaoY, Cor instancia){

        if(validaPosicao(int posicaoX, int posicaoY) == true){
            this.cor[posicaoX][posicaoY] = new instancia;
        }
    }

    public void modificaPixel(int posicaoX, int posicaoY, int red, int green, int blue){
        
        if(validaPosicao(int posicaoX, int posicaoY) == true){
            this.cor[posicaoX][posicaoY].setRed(red);
            this.cor[posicaoX][posicaoY].setGreen(green);
            this.cor[posicaoX][posicaoY].setBlue(blue);
        }
    }
}
