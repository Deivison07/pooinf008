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

    private int obterValorValido(int valor) {
        return valor < VALOR_MINIMO ? VALOR_MINIMO : valor > VALOR_MAXIMO ? VALOR_MAXIMO : valor;
    }

    public void setRed(int red) {
        this.red = obterValorValido(red);
    }

    public void setGreen(int green) {
        this.green = obterValorValido(green);
    }

    public void setBlue(int blue) {
        this.blue = obterValorValido(blue);
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
        return (int) (getRed() * 0.3 + getGreen() * 0.59 + getBlue() * 0.11);
    }

    public boolean verificaIgualdade (Cor cor2) {
        return cor2.red == getRed() && cor2.green == getGreen() && cor2.blue == getBlue();
    }
    
    public void clarearCor (float p) {
        int percentualClarear = (int) p * 100 + 100;
        this.setRed(this.getRed() * percentualClarear / 100);
        this.setGreen(this.getGreen() * percentualClarear / 100);
        this.setBlue(this.getBlue() * percentualClarear / 100);
    }

    public void escurecerCor (float p) {
        int percentualEscurecer = ((int) p * 100 - 100) * -1;
        this.setRed(this.getRed() * percentualEscurecer / 100);
        this.setGreen(this.getGreen() * percentualEscurecer / 100);
        this.setBlue(this.getBlue() * percentualEscurecer / 100);
    }

    public Cor novaCorIgual() {
        return new Cor(this.getRed(), this.getGreen(), this.getBlue());
    }
}
