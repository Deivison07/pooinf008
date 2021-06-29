package app.model;


public class ConvertCMYKForRgb {

    
    public Imagem getNovoMapa(int altura, int largura){
        Imagem mapa = new Imagem(altura, largura);
        return mapa;
    }// fim de getNovoMapa


    public Cor converter(Cor cor){
        
        int red = 255 * (1-cor.getCiano())/100 * (1 - cor.getPreto());
        int green = 255 * (1-cor.getMagenta())/100 * (1 - cor.getPreto());
        int blue = 255 * (1-cor.getAmarelo())/100 * (1 - cor.getPreto());

        CorRGB novaCorRgb = new CorRGB(red,green,blue);

        return novaCorRgb;

        
    }//fim de converter




}