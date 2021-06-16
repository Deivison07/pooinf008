package app;

public class Cor {
    private int red;
    private int green;
    private int blue;

    public static final Cor PRETA = new Cor(0, 0, 0);
    public static final Cor BRANCA = new Cor(255, 255, 255);
    public static final Cor RED = new Cor(255, 0, 0);
    public static final Cor GREEN = new Cor(0, 255, 0);
    public static final Cor BLUE = new Cor(0, 0, 255);

    public static final int VALOR_MINIMO = 0;
    public static final int VALOR_MAXIMO = 255;
    
    public Cor(int red, int green, int blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public Cor(){
        this(Cor.VALOR_MINIMO, Cor.VALOR_MINIMO, Cor.VALOR_MINIMO);
    }

    public Cor(Cor instancia){
        this(instancia.getRed(), instancia.getGreen(), instancia.getBlue());
    }

    private int obterValorValido(int valor) {
        return valor < Cor.VALOR_MINIMO ? Cor.VALOR_MINIMO : valor > Cor.VALOR_MAXIMO ? Cor.VALOR_MAXIMO : valor;
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
        return Math.round((float) (this.getRed() * 0.3 + this.getGreen() * 0.59 + this.getBlue() * 0.11));
    }

    public boolean equals(Cor cor2) {
        return 	cor2.getRed() == this.getRed() && cor2.getGreen() == this.getGreen() && cor2.getBlue() == this.getBlue();
    }
    
    public void clarear(double percentual) {
        this.modificar(1 + percentual);
    }

    public void escurecer(double percentual) {
        this.modificar(1 - percentual);
    }
    
    private void modificar(double percentual) {
        this.setRed((int) (this.getRed() * percentual));
        this.setGreen((int) (this.getGreen() * percentual));
        this.setBlue((int) (this.getBlue() * percentual));
    }

    public Cor clone() {
        return new Cor(this);
    }
    
    public Cor gerarCinzaEquivalente(){
        int luminosidade = this.getLuminosidade();
        return new Cor(luminosidade, luminosidade, luminosidade);
    }
    
    public String toString() {
        return "#" + this.obterHexa(this.getRed()) + this.obterHexa(this.getGreen()) + this.obterHexa(this.getBlue());
    }
    
    private String obterHexa(int valor) {
        final char caracteresHexa[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String resultado = new String();
        final int CONSTANTE_HEXA = 16;
        
        do {
            int resto = valor % CONSTANTE_HEXA;
            valor = valor / CONSTANTE_HEXA;
            resultado = caracteresHexa[resto] + resultado;
        } while (valor > 0);
        
        return resultado;
    }
}