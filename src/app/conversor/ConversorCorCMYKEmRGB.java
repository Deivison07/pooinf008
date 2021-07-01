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
        
        int red = 255 * (1 - corCMYK.getCiano()) / 100 * (1 - corCMYK.getPreto());
        int green = 255 * (1 - corCMYK.getMagenta()) / 100 * (1 - corCMYK.getPreto());
        int blue = 255 * (1 - corCMYK.getAmarelo()) / 100 * (1 - corCMYK.getPreto());

        CorRGB corRgb = new CorRGB(red, green, blue);

        return corRgb;
    }
}