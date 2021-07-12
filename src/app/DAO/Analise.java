package app.DAO;

import app.model.Imagem;
import app.model.Cor;
import app.model.CorCMYK;
import app.model.CorRGB;
import app.enums.SimboloEnum;



class Analise {

	public void analisar(String tipo, String caminho){
		Cor pixel;
		Cor cores;
		int icont;
		int jcont;
		int x;
		int y;
		Imagem img;
		img = Imagem.obterImagemNoArquivo(caminho);

		x = img.getAltura();
		y = img.getLargura();

		for (int icont = 0;icont<x;icont++) {
			for (jcont = 0;jcont<y;jcont++) {
				pixel = img.getPixel(x,y);
				cores = obterCoresPorSimbolo(SimboloEnum.obterCoresPorSimbolo(tipo))
				

			}	
			
		}


	}

}