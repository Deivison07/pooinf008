public class Cor {
    private int red;
    private int green;
    private int blue;

    public static final Cor PRETA = new Cor(0,0,0);
    public static final Cor BRANCA = new Cor(255,255,255);
    public static final Cor RED = new Cor(255,0,0);
    public static final Cor GREEN = new Cor(0,255,0);
    public static final Cor BLUE = new Cor(0,0,255);

    private final int VALOR_MINIMO = 0;
    private final int VALOR_MAXIMO = 255;
    
    public Cor(int red, int green, int blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }
    public Cor(){
    	this(0,0,0);
    }

    public Cor(Cor instancia){
    	this(instancia.getRed(), instancia.getGreen(), instancia.getBlue());
    }

    private int obterValorValido(int valor) {
        return valor < VALOR_MINIMO ? VALOR_MINIMO : valor > VALOR_MAXIMO ? VALOR_MAXIMO : valor;
    }

    private void setRed(int red) {
        this.red = obterValorValido(red);
    }

    private void setGreen(int green) {
        this.green = obterValorValido(green);
    }

    private void setBlue(int blue) {
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

    public Cor novaCorIgual() {
        return new Cor(this.getRed(), this.getGreen(), this.getBlue());
    }
    
    public String toString() {
        return "#" + this.obterHexa(this.getRed()) + this.obterHexa(this.getGreen()) + this.obterHexa(this.getBlue());
    }

    public gerarCinzaEquivalente(Cor instancia){
        int cinza = instancia.getLuminosidade();
        return new Cor(cinza,cinza,cinza);

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
