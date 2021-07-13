package app.servico.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import app.UI.DTO.ItemDeAnaliseDTO;

public interface IBiblioteca {
	Collection<String> obterSimbolosDasCores() throws SQLException;
	
	Collection<ItemDeAnaliseDTO> analisarImagem(String caminho, String simbolo) throws SQLException, ClassNotFoundException, IOException;
}
