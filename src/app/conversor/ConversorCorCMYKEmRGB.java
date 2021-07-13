package app.conversor;

import app.conversor.interfaces.IConversorCor;
import app.model.Cor;
import app.model.CorCMYK;
import app.model.CorRGB;

public class ConversorCorCMYKEmRGB implements IConversorCor {
	
    public Cor converter(Cor cor)  throws IllegalArgumentException {
    	if (!(cor instanceof CorCMYK))
    		throw new IllegalArgumentException("Argumento 'cor' deve ser do tipo CorCMYK.");
    	
        CorCMYK corCMYK = (CorCMYK) cor;
        
        int red = converterValorCMYKParaRGB(corCMYK.getCiano(), corCMYK.getPreto());
        int green = converterValorCMYKParaRGB(corCMYK.getMagenta(), corCMYK.getPreto());
        int blue = converterValorCMYKParaRGB(corCMYK.getAmarelo(), corCMYK.getPreto());

        CorRGB corRgb = new CorRGB(corCMYK.getSimbolo(), red, green, blue);

        return corRgb;
    }
    
    private int converterValorCMYKParaRGB(int valor, int preto) {
    	return Math.round(255 * (1 - ((float) valor / 100)) * (1 - ((float) preto / 100)));
	}
}
