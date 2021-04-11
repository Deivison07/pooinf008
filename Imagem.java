
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
                //Usar aqui o atributo static geral pra cor branca.
                this.cor[altura][largura] = Cor.BRANCA;
            }
        }
        
    }
}
