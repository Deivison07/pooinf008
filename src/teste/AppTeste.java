package teste;

import java.sql.SQLException;
import java.util.Collection;

import app.DAO.CorDAO;
import app.DAO.ImagemDAO;
import app.DAO.interfaces.ICorDAO;
import app.DAO.interfaces.IImagemDAO;
import app.model.Cor;
import app.model.CorCMYK;
import app.model.CorRGB;
import app.model.Imagem;
import app.enums.SimboloEnum;

public class AppTeste {
	private static ICorDAO corDAO = new CorDAO();
	private static IImagemDAO imagemDAO = new ImagemDAO();
	
	public static void main(String[] args) throws Exception {
		System.out.println("rodarTesteDeCor ####################################");
		AppTeste.rodarTesteDeCor();

		System.out.println("rodarTesteDeImagem ####################################");
		AppTeste.rodarTesteDeImagem();

		System.out.println("rodarTesteDeSimboloEnum ####################################");
		AppTeste.rodarTesteDeSimboloEnum();
	}
	
	private static void rodarTesteDeSimboloEnum() {
		SimboloEnum s1 = SimboloEnum.AREA_ABERTA;
		SimboloEnum s2 = SimboloEnum.obterPorValor(s1.getValor());
		
		System.out.println(String.format("obterPorValor %d == %d (%b)",
				s1.getValor(),
				s2.getValor(),
				s1.getValor() == s2.getValor()));
		
		SimboloEnum s3 = SimboloEnum.obterPorNome(s1.getNome());
		System.out.println(String.format("obterPorValor '%s' == '%s' (%b)",
				s1.getNome(),
				s3.getNome(),
				s1.getNome() == s3.getNome()));
	}
	
	// TODO
	private static void rodarTesteDeImagem() throws Exception {
		Imagem img1 = new Imagem(new Cor[][] {
			{new CorRGB(0, 255, 0, 0), new CorRGB(0, 0, 255, 0), new CorRGB(0, 0, 0, 255)},
			{new CorCMYK(0, 100, 0, 0, 0), new CorCMYK(0, 0, 100, 0, 0), new CorCMYK(0, 0, 0, 100, 0)},
			{new CorCMYK(0, 100, 0, 0, 100), new CorCMYK(0, 0, 0, 0, 100), new CorCMYK(0, 0, 100, 100, 0)},
		});
		
		imagemDAO.salvarImagemNoArquivo(img1);
		System.out.println("salvarImagemNoArquivo -- salvou");
		
		Imagem img2 = imagemDAO.obterImagemNoArquivo(null);
		System.out.println(String.format("obterImagemNoArquivo -- obteve (%b)", img1.equals(img2)));
	}
	
	private static void rodarTesteDeCor() throws SQLException {
		System.out.println("obterTodos");
		Collection<Cor> cores = AppTeste.corDAO.obterTodos();
		AppTeste.printCores(cores);
		
		int simbolo = SimboloEnum.AREA_ABERTA.getValor();
		System.out.println("obterCoresPorSimbolo " + simbolo);
		cores = AppTeste.corDAO.obterCoresPorSimbolo(simbolo);
		AppTeste.printCores(cores);
		
		int id = 1;
		System.out.println("obterPorId " + id);
		Cor cor = AppTeste.corDAO.obterPorId(id);
		AppTeste.printCor(cor);
		
		System.out.println("obterSimbolosDasCores ");
		Collection<String> simbolos = AppTeste.corDAO.obterSimbolosDasCores();
		for (String s : simbolos) {
			System.out.println(s);
		}
	}
	
	private static void printCores(Collection<Cor> cores) {
		for (Cor cor : cores) {
			AppTeste.printCor(cor);
		}
	}
	
	private static void printCor(Cor cor) {
		System.out.println("Cor -----");
		System.out.println("id: " + cor.getId());
		System.out.println("nome: " + cor.getNome());
		System.out.println("simbolo: " + cor.getSimbolo());
		System.out.println("tipo: " + cor.getTipo());
		
		if (cor instanceof CorRGB) {
			CorRGB cRGB = (CorRGB) cor;
			System.out.println("r: " + cRGB.getRed());
			System.out.println("g: " + cRGB.getGreen());
			System.out.println("b: " + cRGB.getBlue());
		} else if (cor instanceof CorCMYK) {
			CorCMYK cCMYK = (CorCMYK) cor;
			System.out.println("ciano: " + cCMYK.getCiano());
			System.out.println("magenta: " + cCMYK.getMagenta());
			System.out.println("amarelo: " + cCMYK.getAmarelo());
			System.out.println("preto: " + cCMYK.getPreto());
		}

		System.out.println("");
	}
}
