package app.test;

import app.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestaCor
{
    private Cor cor1;
    private Cor cor2;
    private Cor clone;
    private Cor corCinza;
    private Cor corClara;
    private Cor corEscura;
    private double percentual = 0;
    
    public TestaCor()
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
    
    @Test
    public void TestaIgualdade()
    {
        cor1 = new Cor(1, 1, 1);
        cor2 = new Cor(1, 1, 1);
        
        assertTrue(cor1.verificaIgualdade(cor2));
        
        cor1 = new Cor(1, 1, 1);
        cor2 = new Cor(1, 0, 1);
        
        assertFalse(cor1.verificaIgualdade(cor2));
    }
    
    @Test
    public void TestaToString()
    {
        cor1 = new Cor(37, 150, 190);
        
        assertTrue(cor1.toString().equals("#2596BE"));
    }
    
    @Test
    public void TestaGerarCinza()
    {
        cor1 = new Cor(37, 150, 190);
        corCinza = cor1.gerarCinzaEquivalente();
        
        assertTrue(corCinza.toString().equals("#797979"));
    }
    
    @Test
    public void TestaClonagem()
    {
        cor1 = new Cor(1, 1, 1);
        clone = cor1.novaCorIgual();
        
        assertTrue(cor1.verificaIgualdade(clone));
    }
    
    @Test
    public void TestaClarear()
    {
        percentual = 0.1;
        cor1 = new Cor(37, 150, 190);
        corClara = cor1.novaCorIgual();
        corClara.clarear(percentual);
        
        assertTrue(corClara.toString().equals("#3BA1C5"));
    }
    
    @Test
    public void TestaEscurecer()
    {
        percentual = 0.1;
        cor1 = new Cor(37, 150, 190);
        corEscura = cor1.novaCorIgual();
        corEscura.escurecer(percentual);
        
        assertTrue(corEscura.toString().equals("#2187AB"));
    }
}