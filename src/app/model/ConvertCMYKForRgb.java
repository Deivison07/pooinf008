package app.model;


public class ConvertCMYKForRgb implements ConversorCor {

    
    public Imagem getNovoMapa(int altura, int largura){
        Imagem mapa = new Imagem(altura, largura);
        return mapa;
    }// fim de getNovoMapa


    public Cor converter(Cor cor){
        CorCMYK novaCor = (CorCMYK) cor;
        
        int red = 255 * (1- novaCor.getCiano() )/100 * (1 - novaCor.getPreto());
        int green = 255 * (1-novaCor.getMagenta())/100 * (1 - novaCor.getPreto());
        int blue = 255 * (1-novaCor.getAmarelo())/100 * (1 - novaCor.getPreto());

        CorRGB novaCorRgb = new CorRGB(red,green,blue);

        return novaCorRgb;

        
    }//fim de converter




}