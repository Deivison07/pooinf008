package app.model;

public class CorRGB extends Cor {
    private int red;
    private int green;
    private int blue;

    public static final int VALOR_MAXIMO = 255;
    public static final int VALOR_MINIMO = 0;
    
    public CorRGB(int red, int green, int blue) {
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
    }

    public CorRGB() {
        this(CorRGB.VALOR_MINIMO, CorRGB.VALOR_MINIMO, CorRGB.VALOR_MINIMO);
    }

    public CorRGB(CorRGB instancia) {
        this(instancia.getRed(), instancia.getGreen(), instancia.getBlue());
    }
    
    private int obterValorValido(int valor) {
        return valor < CorRGB.VALOR_MINIMO ? CorRGB.VALOR_MINIMO : valor > CorRGB.VALOR_MAXIMO ? CorRGB.VALOR_MAXIMO : valor;
    }

    private void setRed(int red) {
        this.red = this.obterValorValido(red);
    }

    private void setGreen(int green) {
        this.green = this.obterValorValido(green);
    }

    private void setBlue(int blue) {
        this.blue = this.obterValorValido(blue);
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

    public int getLuminosidade() {
        return (int) (this.getRed() * 0.3 + this.getGreen() * 0.59 + this.getBlue() * 0.11);
    }
}
