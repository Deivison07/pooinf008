package app.model;

//Classe que contém todos os mapas(Imagem)
public class Biblioteca {
    
    private Imagem mapas[];

    public Biblioteca(int novaAltura, int novaLargura){
        // $mapa deve conter todas as imagens individualmente
        this.mapas[0] = new Imagem(novaAltura, novaLargura);
    }

    public void addImagem(int novaAltura, int novaLargura){
        Imagem novaImagem = new Imagem(novaAltura, novaLargura);
        Imagem[] novo = new Imagem[this.mapas.length + 1];
        for(int i = 0; i < this.mapas.length; i++)
            novo[i] = this.mapas[i];
        novo[novo.length - 1] = novaImagem;
        this.mapas = novo;
    }

    public int getTamanho() {
        return this.mapas.length;
    }

    public int getLuminosidade(double red, double green, double blue) {
        return (int) (red * 0.3 + green * 0.59 + blue * 0.11);
    }

    public Imagem[] getImagemPorLuminosidade(double red, double green, double blue, double pctMinimo, double limiarSimilaridade){
        
        int luminosidade = getLuminosidade(red, green, blue);
        Imagem[] mapasSimilares = new Imagem[0];
        
        //Iterando sob a largura do array de imagens
        for (int i = 0; i < this.mapas.length; i++){
            double contagemPct = 0;
            double pctAceitavel = this.mapas[i].getTamanho() * pctMinimo;

            //Iterando sob a altura da matriz Cor
            for(int a = 0; a < this.mapas[i].getAltura(); a++){
                
                //Iterando sob a largura da matriz Cor
                for (int l = 0; l < this.mapas[i].getLargura(); l++){
                    
                    Cor pixelComparado = this.mapas[i].getPixel(a, l);
                    if (luminosidade < (pixelComparado.getLuminosidade() * (1 + limiarSimilaridade)) && luminosidade > (pixelComparado.getLuminosidade() * (1 - limiarSimilaridade))){
                        contagemPct += 1;
                    }
                }
            }

            if (contagemPct >= pctAceitavel){
                Imagem[] novo = new Imagem[mapasSimilares.length + 1];
                for(int j = 0; j < mapasSimilares.length; j++)
                    novo[i] = mapasSimilares[i];
                novo[novo.length - 1] = mapas[i];
                mapasSimilares = novo;
            }

            return mapasSimilares;
        }
        
        return mapasSimilares;                                  
    }
}
