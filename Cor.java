public class Cor {
    private int red;
    private int green;
    private int blue;

    private final int VALOR_MINIMO = 0;
    private final int VALOR_MAXIMO = 255;
    
    public Cor(int red, int green, int blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    private boolean validarValorDaCor(int valor) {
        return valor >= VALOR_MINIMO && valor <= VALOR_MAXIMO;
    }

    public void setRed(int red) {
        this.red = validarValorDaCor(red) ? red : VALOR_MINIMO;
    }

    public void setGreen(int green) {
        this.green = validarValorDaCor(green) ? green : VALOR_MINIMO;
    }

    public void setBlue(int blue) {
        this.blue = validarValorDaCor(blue) ? blue : VALOR_MINIMO;
    }
    
    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }
    
    public int getLuminosidade(){
        return (int) (getRed ( ) * 0.3 + getGreen ( ) * 0.59 + getBlue ( ) * 0.11);
    }

    public boolean verificaIgualdade (Cor cor2) {
        return cor2.red == getRed() && cor2.green == getGreen() && cor2.blue == getBlue();
    }

    
}

