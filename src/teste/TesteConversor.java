package teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.conversor.ConversorCorCMYKEmRGB;
import app.conversor.ConversorImagem;
import app.model.Cor;
import app.model.CorCMYK;
import app.model.CorRGB;
import app.model.Imagem;

class TesteConversor {

	@Test
	void testarConversaoDeCorCMYKParaRGB() {
		CorCMYK cor = new CorCMYK(45, 73, 9, 46);
		
		Cor novaCor = (new ConversorCorCMYKEmRGB()).converter(cor);
		
		assertTrue(novaCor instanceof CorRGB);
		
		CorRGB novaCorRGB = (CorRGB) novaCor;
		
		int red = converterValorCMYKParaRGB(cor.getCiano(), cor.getPreto());
        int green = converterValorCMYKParaRGB(cor.getMagenta(), cor.getPreto());
        int blue = 	converterValorCMYKParaRGB(cor.getAmarelo(), cor.getPreto());
		
		assertTrue(novaCorRGB.getRed() == red);
		assertTrue(novaCorRGB.getGreen() == green);
		assertTrue(novaCorRGB.getBlue() == blue);
	}
	
	private int converterValorCMYKParaRGB(int valor, int preto) {
		return 255 * (1 - valor) / 100 * (1 - preto) / 100;
	}

	@Test
	void testarConversorDeImagem() {
		CorCMYK cor = new CorCMYK(45, 73, 9, 46);
		
		Imagem img = new Imagem(new Cor[][] {
			{ cor, cor, cor, cor, cor },
			{ cor, cor, cor, cor, cor },
			{ cor, cor, cor, cor, cor },
			{ cor, cor, cor, cor, cor }
		});
		
		Imagem nImagem = (new ConversorImagem()).converter(img, new ConversorCorCMYKEmRGB());
		
		boolean todosRGB = true;
		
		for (int altura = 0; altura < nImagem.getAltura(); altura++) {
			for (int largura = 0; largura < nImagem.getLargura(); largura++) {
				todosRGB = todosRGB && (nImagem.getPixel(altura, largura) instanceof CorRGB);
			}
		}
		
		assertTrue(todosRGB);
	}
}
