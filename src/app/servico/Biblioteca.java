package app.servico;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import app.DAO.CorDAO;
import app.DAO.ImagemDAO;
import app.DAO.interfaces.ICorDAO;
import app.DAO.interfaces.IImagemDAO;
import app.UI.DTO.ItemDeAnaliseDTO;
import app.enums.SimboloEnum;
import app.model.Cor;
import app.model.CorRGB;
import app.model.Imagem;
import app.servico.interfaces.IBiblioteca;

public class Biblioteca implements IBiblioteca {
    
    private final IImagemDAO imagemDAO;
    private final ICorDAO corDAO;

    public Biblioteca() {
    	this.imagemDAO = new ImagemDAO();
    	this.corDAO = new CorDAO();
    }
    
    public Collection<String> obterSimbolosDasCores() throws SQLException {
    	return this.corDAO.obterSimbolosDasCores();
    }
   
    public Collection<ItemDeAnaliseDTO> analisarImagem(String caminho, String simbolo) throws SQLException, ClassNotFoundException, IOException {
    	Collection<ItemDeAnaliseDTO> analise = new ArrayList<ItemDeAnaliseDTO>();
    	
    	int idSimbolo = SimboloEnum.obterPorNome(simbolo).getValor();
    	Collection<Cor> cores = this.corDAO.obterCoresPorSimbolo(idSimbolo);
    	
    	Imagem imagem = imagemDAO.obterImagemNoArquivo(caminho);
    	
    	double percentualDoSimbolo = imagem.obterPercentualDeIgualdadePorCorRGB(cores);
    	
    	ItemDeAnaliseDTO itemTotal = new ItemDeAnaliseDTO("Total do Simbolo", percentualDoSimbolo);
    	analise.add(itemTotal);
    	
    	for (Cor cor : cores) {
    		double percentualDaCor = imagem.obterPercentualDeIgualdadePorCorRGB(cor.toRGB());
    		
    		ItemDeAnaliseDTO item = new ItemDeAnaliseDTO(cor.getNome(), percentualDaCor);
        	analise.add(item);
    	}
    	
    	return analise;
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
    	CorRGB corBase = new CorRGB(0, red, green, blue);
    	int luminosidade = corBase.getLuminosidade();
    	
    	int lumMinima = (int) (luminosidade * (1 - limiarSimilaridade));
    	int lumMaxima = (int) (luminosidade * (1 + limiarSimilaridade));
    	
    	Collection<Imagem> mapas = new ArrayList<Imagem>();
    	Collection<Imagem> mapasSimilares = new ArrayList<Imagem>();
    	
    	for (Imagem mapa : mapas) {
    		double pctSimilar = mapa.obterPercentualDeSimilaridadePorLuminosidade(lumMinima, lumMaxima);
    		
			if (pctSimilar >= pctMinimo)
				mapasSimilares.add(mapa);
    	}
    	
        return mapasSimilares.toArray(new Imagem[mapasSimilares.size()]);                                  
    }
}
