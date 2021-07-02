package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import app.model.Cor;
import app.model.CorRGB;
import app.model.Imagem;
import app.servico.Biblioteca;

class TesteBiblioteca {
	
	private Biblioteca biblioteca;
	
	/**
	 * Metodo para verificar se a luminosidade da cor atente as especificacoes de faixa e se deve ou nao estar na faixa
	 * @param luminosidade luminosidade da cor para validar
	 * @param lumMinima valor minimo (inclusivo) da luminosidade
	 * @param lumMaxima valor maximo (inclusivo) da luminosidade
	 * @param noLimiar booleano indicando se a luminosidade deve ou ao estar na faixa indicada
	 * @return boleano indicando se a luminosidade atende as especificacoes
	 */
	private boolean checarLuminosidade(int luminosidade, double lumMinima, double lumMaxima, boolean noLimiar) {
				// dentro do limite e deveria estar
		return 	((luminosidade >= lumMinima && luminosidade <= lumMaxima) && noLimiar) || 
				// fora do limite e deveria estar
				((luminosidade < lumMinima || luminosidade > lumMaxima) && !noLimiar);
	}
	
	/**
	 * Metodo para gerar uma nova cor aletoria com base em outra cor.
	 * @param corBase cor que sera usada como base para gerar uma nova cor dentro (ou nao) do limiar de sua luminosidade
	 * @param limiar percentual da faixa (para mais e para menos) que a luminosidade da nova cor deve estar em relacao a corBase
	 * @param noLimiar booleano que indica se a nova cor deve estar ou nao dentro do limiar da corBase
	 * @return uma nova cor rgb
	 */
	private CorRGB obterNovaCorRGB(CorRGB corBase, double limiar, boolean noLimiar) {
		int luminosidade = corBase.getLuminosidade();
    	
    	int lumMinima = (int) (luminosidade * (1 - limiar));
    	int lumMaxima = (int) (luminosidade * (1 + limiar));
    	
    	CorRGB novaCor = new CorRGB();
    	Random rdn = new Random();
    	
    	while (!checarLuminosidade(novaCor.getLuminosidade(), lumMinima, lumMaxima, noLimiar)) {
    		int r = rdn.nextInt(CorRGB.VALOR_MAXIMO + 1);
    		int g = rdn.nextInt(CorRGB.VALOR_MAXIMO + 1);
    		int b = rdn.nextInt(CorRGB.VALOR_MAXIMO + 1);
    		
    		novaCor = new CorRGB(r, g, b);
    	}
    	
    	return novaCor;
	}
	
	/**
	 * Metodo para obter uma nova imagem dentro do percentual minimo (ou nao) de similaridade com uma determinada cor 
	 * @param corBase cor que sera usada como base para gerar uma nova cor dentro (ou nao) do limiar de sua luminosidade
	 * @param limiar percentual da faixa (para mais e para menos) que a luminosidade da nova cor deve estar em relacao a corBase
	 * @param pctMinimo percentual minimo de pixel similares a corBase que a imagem deve ter
	 * @param noPctMinimo se a imagem atual dever atender ou nao ao pctMinimo
	 * @return uma nova imagem
	 */
	private Imagem obterNovaImagem(CorRGB corBase, double limiar, double pctMinimo, boolean noPctMinimo) {
		Random rdn = new Random();
		
		Imagem img = new Imagem(rdn.nextInt(20) + 1, rdn.nextInt(20) + 1);
		int pixelsSimilares = 0;
		double pctAtual = 0;
		
		for (int altura = 0; altura < img.getAltura(); altura++) {
			for (int largura = 0; largura < img.getLargura(); largura++) {
				
				// saber se a prox cor deve estar no limiar da corBase
				// com base no percentual atual e no parametro noPctMinimo
				pctAtual = (pixelsSimilares * 100) /  img.getTamanho();
				boolean noLimiar = noPctMinimo && (pctAtual < pctMinimo);
				
				Cor cor = obterNovaCorRGB(corBase, limiar, noLimiar);
				img.setPixel(altura, largura, cor);
				
				if (noLimiar) pixelsSimilares++;
			}
		}
		
		return img;
	}
	
	/**
	 * Metodo para inserir uma quantidade aleatoria de imagens na bibliotca, tambem com quantidades aleatorias
	 * das que irao obedecer ou nao ao pctMinimo
	 * @param corBase cor que sera usada como base para gerar uma nova cor dentro (ou nao) do limiar de sua luminosidade
	 * @param limiar percentual da faixa (para mais e para menos) que a luminosidade da nova cor deve estar em relacao a corBase
	 * @param pctMinimo percentual minimo de pixel similares a corBase que a imagem deve ter
	 * @return quantidade de imagens que estao dentro do pctMinimo
	 */
	private int popularBiblioteca(CorRGB corBase, double limiar, double pctMinimo) {
		Random rdn = new Random();
		int qtdImagens = rdn.nextInt(15);
		int imagensNoPct = 0;
		
		for (int index = 0; index < qtdImagens; index++) {
			boolean noPctMinimo = rdn.nextBoolean();
			
			Imagem mapa = obterNovaImagem(corBase, limiar, pctMinimo, noPctMinimo);
			
			biblioteca.addMapa(mapa);
			
			if (noPctMinimo) imagensNoPct++;
		}
		
		return imagensNoPct;
	}
	
	@Test
	void testarObterImagemPorLuminosidade() {
		biblioteca = new Biblioteca();
		
		// valores de arumento para Biblioteca.getImagemPorLuminosidade
		CorRGB corParaComparar = new CorRGB(12, 54, 100);
		double pctMinimo = 40;
		double limiar = 0.5;
		
		// adicionar imagens na biblioteca
		int qtdImagensSimilares = popularBiblioteca(corParaComparar, limiar, pctMinimo);
		
		Imagem[] imagensSimilares = biblioteca.getImagemPorLuminosidade(
				corParaComparar.getRed(),
				corParaComparar.getGreen(),
				corParaComparar.getBlue(),
				pctMinimo,
				limiar);

		assertTrue(qtdImagensSimilares == imagensSimilares.length);
	}

}
