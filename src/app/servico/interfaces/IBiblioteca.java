package app.servico.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface IBiblioteca {
	Collection<String> obterSimbolosDasCores() throws SQLException;
	
	String analisarImagem(String caminho, String simbolo) throws SQLException, ClassNotFoundException, IOException;
}
