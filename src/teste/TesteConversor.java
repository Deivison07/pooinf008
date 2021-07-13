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
		CorCMYK corCMYK;
		CorRGB corRGB;
		
		// vermelho
		corCMYK = new CorCMYK(0, 0, 100, 100, 0);
		corRGB = (CorRGB) (new ConversorCorCMYKEmRGB()).converter(corCMYK);
		
		assertTrue(corRGB.getRed() == 255);
		assertTrue(corRGB.getGreen() == 0);
		assertTrue(corRGB.getBlue() == 0);
		
		// verde
		corCMYK = new CorCMYK(0, 100, 0, 100, 0);
		corRGB = (CorRGB) (new ConversorCorCMYKEmRGB()).converter(corCMYK);
		
		assertTrue(corRGB.getRed() == 0);
		assertTrue(corRGB.getGreen() == 255);
		assertTrue(corRGB.getBlue() == 0);
		
		// azul
		corCMYK = new CorCMYK(0, 100, 100, 0, 0);
		corRGB = (CorRGB) (new ConversorCorCMYKEmRGB()).converter(corCMYK);
		
		assertTrue(corRGB.getRed() == 0);
		assertTrue(corRGB.getGreen() == 0);
		assertTrue(corRGB.getBlue() == 255);
		
		// uma aleatoria
		corCMYK = new CorCMYK(0, 58, 0, 32, 24);
		corRGB = (CorRGB) (new ConversorCorCMYKEmRGB()).converter(corCMYK);
		
		assertTrue(corRGB.getRed() == 81);
		assertTrue(corRGB.getGreen() == 194);
		assertTrue(corRGB.getBlue() == 132);
	}

	@Test
	void testarConversorDeImagem() {
		CorCMYK cor = new CorCMYK(0, 45, 73, 9, 46);
		
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
