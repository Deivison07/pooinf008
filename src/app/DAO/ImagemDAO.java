package app.DAO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import app.DAO.interfaces.IImagemDAO;
import app.model.Imagem;

public class ImagemDAO implements IImagemDAO {
	
	private static final String CAMINHO_PADRAO = "C:\\Users\\davi.sousa\\Documents\\imagem.bin"; 
	
	public ImagemDAO () {
	}
	
	private String getCaminho(String caminho) {
		return caminho != null && !caminho.trim().equals("") ? caminho : ImagemDAO.CAMINHO_PADRAO;
	}
	
	@Override
	public Imagem obterImagemNoArquivo(String caminho) throws IOException, ClassNotFoundException {
		caminho = this.getCaminho(caminho);
		
		FileInputStream in = new FileInputStream(caminho);
		ObjectInputStream ois = new ObjectInputStream(in);
		
		Imagem img = (Imagem) ois.readObject();
		
		ois.close();
		in.close();
		
		return img;
	}
	
	@Override
	public void salvarImagemNoArquivo(Imagem img) throws IOException {
		FileOutputStream out = new FileOutputStream(ImagemDAO.CAMINHO_PADRAO);
		ObjectOutputStream oos = new ObjectOutputStream(out);
		
		oos.writeObject(img);
		
		oos.close();
		out.close();
	}
}
