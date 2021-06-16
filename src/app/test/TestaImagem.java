package app.test;

import app.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestaImagem
{
    private int altura;
    private int largura;
    private Imagem img1;
    private Imagem img2;
    private Imagem imgCinza;
    private Cor pixels1[][];
    private Cor pixels2[][];
    
    public TestaImagem()
    {
    }

    @BeforeEach
    public void setUp()
    {
    }

    @AfterEach
    public void tearDown()
    {
    }
    
    private Imagem criarImagem(Cor[][] pixels) {
    	int largura = pixels[0].length;
    	int altura = pixels.length;
    	
    	Imagem imagem = new Imagem(altura, largura);
    	
    	for(int i = 0; i < altura; i++) {
    		for (int j = 0; j < largura; j++) {
    			imagem.setPixel(i, j, pixels[i][j]);
    		}
    	}
    	
    	return imagem;
    }
    
    @Test
    public void TestaConstrutor()
    {
        altura = largura = 5;
        boolean tudoBranco = true;
        img1 = new Imagem(altura, largura);
        
        for (int a = 0; a < img1.getAltura() && tudoBranco; a++){
            for (int l = 0; l < img1.getLargura() && tudoBranco; l++){
                tudoBranco = img1.getPixel(a, l).equals(Cor.BRANCA);
            }
        }
        
        assertTrue(tudoBranco);
    }
    
    @Test
    public void TestaModificarPixel()
    {
        altura = largura = 5;
        img1 = new Imagem(altura, largura);
        
        img1.setPixel(3, 4, Cor.PRETA);
        
        assertTrue(Cor.PRETA.equals(img1.getPixel(3, 4)));
    }
    
    @Test
    public void TestaGerarImagemCinza()
    {
        Cor cinzaDoBranco = new Cor(255, 255, 255);
        Cor cinzaDoBlue = new Cor(28, 28, 28);
        Cor cinzaDoRed = new Cor(77, 77, 77);
        Cor cinzaDoPreto = new Cor(0, 0, 0);
        boolean tudoCinza = true;
        
        pixels1 = new Cor[][]{
            {Cor.RED,   Cor.PRETA,  Cor.RED,    Cor.BLUE,  Cor.BRANCA},
            {Cor.BLUE,  Cor.PRETA,  Cor.BRANCA, Cor.PRETA, Cor.RED},
            {Cor.PRETA, Cor.BRANCA, Cor.PRETA,  Cor.RED,   Cor.BLUE},
            {Cor.RED,   Cor.RED,    Cor.PRETA,  Cor.BLUE,  Cor.BRANCA},
            {Cor.BLUE,  Cor.PRETA,  Cor.BLUE,   Cor.BLUE,  Cor.RED},
        };
        img1 = criarImagem(pixels1);
        imgCinza = img1.obterCinza();
        
        pixels2 = new Cor[][]{
            {cinzaDoRed,   cinzaDoPreto,  cinzaDoRed,    cinzaDoBlue,  cinzaDoBranco},
            {cinzaDoBlue,  cinzaDoPreto,  cinzaDoBranco, cinzaDoPreto, cinzaDoRed},
            {cinzaDoPreto, cinzaDoBranco, cinzaDoPreto,  cinzaDoRed,   cinzaDoBlue},
            {cinzaDoRed,   cinzaDoRed,    cinzaDoPreto,  cinzaDoBlue,  cinzaDoBranco},
            {cinzaDoBlue,  cinzaDoPreto,  cinzaDoBlue,   cinzaDoBlue,  cinzaDoRed},
        };
        
        for (int a = 0; a < imgCinza.getAltura() && tudoCinza; a++){
            for (int l = 0; l < imgCinza.getLargura() && tudoCinza; l++){
                tudoCinza = imgCinza.getPixel(a, l).verificaIgualdade(pixels2[a][l]);
            }
        }
        
        assertTrue(tudoCinza);
    }
    
    @Test
    public void TestaIgualdadeVerdadeiraSemRotacao()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.BRANCA},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.BRANCA},
        };
        img2 = criarImagem(pixels2);
        
        assertTrue(img1.equals(img2));
    }
    
    @Test
    public void TestaIgualdadeFalsa()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.BRANCA},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.BRANCA},
        };
        img2 = criarImagem(pixels2);
        
        assertFalse(img1.equals(img2));
    }
    
    @Test
    public void TestaIgualdadeVerdadeiraComRotacao()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.BRANCA},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.PRETA,  Cor.BRANCA},
            {Cor.BRANCA, Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.BRANCA, Cor.PRETA,  Cor.BRANCA},
            {Cor.BRANCA, Cor.BLUE,   Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
        };
        img2 = criarImagem(pixels2);
        
        assertTrue(img1.equals(img2));
    }
    
    @Test
    public void TestaFragmentoVerdadeiroSemRotacao_1()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.PRETA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.RED},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.PRETA},
            {Cor.RED},
        };
        img2 = criarImagem(pixels2);
        
        assertTrue(img1.possuiComoFragmento(img2));
    }
    
    @Test
    public void TestaFragmentoVerdadeiroSemRotacao_2()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.PRETA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.RED},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.BLUE, Cor.BRANCA,  Cor.RED},
        };
        img2 = criarImagem(pixels2);
        
        assertTrue(img1.possuiComoFragmento(img2));
    }
    
    @Test
    public void TestaFragmentoVerdadeiroComRotacao_1()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.PRETA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.RED},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.RED,   Cor.BRANCA},
            {Cor.PRETA, Cor.BRANCA},
        };
        img2 = criarImagem(pixels2);
        
        assertTrue(img1.possuiComoFragmento(img2));
    }
    
    @Test
    public void TestaFragmentoVerdadeiroComRotacao_2()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.PRETA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.RED},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.RED,   Cor.BRANCA, Cor.BLUE},
        };
        img2 = criarImagem(pixels2);
        
        assertTrue(img1.possuiComoFragmento(img2));
    }
    
    @Test
    public void TestaFragmentoFalso()
    {
        pixels1 = new Cor[][]{
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BLUE,   Cor.BRANCA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.BRANCA, Cor.RED},
            {Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA, Cor.BRANCA},
            {Cor.RED,    Cor.BRANCA, Cor.PRETA,  Cor.BRANCA, Cor.PRETA},
            {Cor.BRANCA, Cor.PRETA,  Cor.BLUE,   Cor.BRANCA, Cor.RED},
        };
        img1 = criarImagem(pixels1);
        
        pixels2 = new Cor[][]{
            {Cor.BLUE,   Cor.BRANCA, Cor.BLUE},
        };
        img2 = criarImagem(pixels2);
        
        assertFalse(img1.possuiComoFragmento(img2));
    }
}