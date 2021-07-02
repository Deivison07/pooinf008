package app.conversor;

import app.conversor.interfaces.IConversorCor;
import app.model.Cor;
import app.model.Imagem;

public class ConversorImagem {

    public Imagem converter(Imagem imagem, IConversorCor conversorDePixel) {
        Imagem novaImagem = new Imagem(imagem.getAltura(), imagem.getLargura());

         for (int iCont = 0; iCont< imagem.getAltura(); iCont++){
             for (int jCont = 0; jCont < imagem.getLargura(); jCont++){
                Cor cor = imagem.getPixel(iCont, jCont);
                Cor novaCor =  conversorDePixel.converter(cor);
                novaImagem.setPixel (iCont, jCont, novaCor);
             }
         }
         
        return novaImagem;
    }
}