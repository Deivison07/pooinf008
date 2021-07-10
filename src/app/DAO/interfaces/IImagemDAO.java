package app.DAO.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.model.Imagem;

public interface IImagemDAO {
	Imagem obterImagemNoArquivo(String caminho) throws IOException, ClassNotFoundException;

	void salvarImagemNoArquivo(Imagem img) throws FileNotFoundException, IOException;
}
