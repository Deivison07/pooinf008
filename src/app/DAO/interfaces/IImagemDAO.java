package app.DAO.interfaces;

import java.util.Collection;
import app.model.Imagem;

public interface IImagemDAO {
	
	Collection<Imagem> obterTodos();
	
	void Inserir(Imagem img);
}
