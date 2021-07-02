package app.servico;

import java.util.ArrayList;
import java.util.Collection;

import app.DAO.ImagemDAO;
import app.DAO.interfaces.IImagemDAO;
import app.model.Cor;
import app.model.CorRGB;
import app.model.Imagem;

public class Biblioteca {
    
    private final IImagemDAO imagemDAO;

    public Biblioteca() {
    	this.imagemDAO = new ImagemDAO();
    }

    public void addMapa(Imagem mapa) {
    	this.imagemDAO.Inserir(mapa);
    }
    
    /**
     * Metodo para obter um array de imagens que possuem um determinada percentual de seus pixels similares com uma
     * determinada cor.
     * 
     * @param red valor numerico do vermelho da cor rgb
     * @param green valor numerico do verde da cor rgb
     * @param blue valor numerico do azul da cor rgb
     * @param pctMinimo percentual minimo de pixels similares que uma imagem deve ter para ser aceita
     * @param limiarSimilaridade limiar de luminosidade que irá variar para mais ou menos. Valor em percentual entre 1 e 0. 
     * 
     * @return array de imagens que passaram na verificacao
     */
    public Imagem[] getImagemPorLuminosidade(int red, int green, int blue, double pctMinimo, double limiarSimilaridade) throws IllegalArgumentException {
    	CorRGB corBase = new CorRGB(red, green, blue);
    	int luminosidade = corBase.getLuminosidade();
    	
    	int lumMinima = (int) (luminosidade * (1 - limiarSimilaridade));
    	int lumMaxima = (int) (luminosidade * (1 + limiarSimilaridade));
    	
    	Collection<Imagem> mapas = this.imagemDAO.obterTodos();
    	Collection<Imagem> mapasSimilares = new ArrayList<Imagem>();
    	
    	for (Imagem mapa : mapas) {
    		double pctSimilar = this.obterSimilaridadeDoMapa(mapa, corBase, lumMinima, lumMaxima);
    		
			if (pctSimilar >= pctMinimo)
				mapasSimilares.add(mapa);
    	}
    	
        return mapasSimilares.toArray(new Imagem[mapasSimilares.size()]);                                  
    }
    
    private double obterSimilaridadeDoMapa(Imagem mapa, CorRGB cor, int lumMinima, int lumMaxima) throws IllegalArgumentException {
    	int totalDePixels = mapa.getAltura() * mapa.getLargura();
    	int qtdSimilar = 0;
    	
    	for (int altura = 0; altura < mapa.getAltura(); altura++){
            for (int largura = 0; largura < mapa.getLargura(); largura++){
            	Cor pixel = mapa.getPixel(altura, largura);
            	int lumDoPixel = pixel.getLuminosidade();
            	
                if (lumDoPixel >= lumMinima && lumDoPixel <= lumMaxima){
                	qtdSimilar += 1;
                }
            }
        }
    	
    	double pctSimilar = (qtdSimilar * 100) /  totalDePixels;
    	return pctSimilar;
    }
}
