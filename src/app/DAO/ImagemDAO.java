package app.DAO;

import java.util.ArrayList;
import java.util.Collection;

import app.DAO.interfaces.IImagemDAO;
import app.model.Imagem;

public class ImagemDAO implements IImagemDAO {
	
	Collection<Imagem> imagens;
	
	public ImagemDAO () {
		this.imagens = new ArrayList<Imagem>();
	}
	
	@Override
	public Collection<Imagem> obterTodos() {
		return this.imagens;
	}

	@Override
	public void Inserir(Imagem img) {
		this.imagens.add(img);
	}

}
