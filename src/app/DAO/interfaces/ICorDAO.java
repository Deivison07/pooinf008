package app.DAO.interfaces;

import java.sql.SQLException;
import java.util.Collection;
import java.util.NoSuchElementException;

import app.model.Cor;

public interface ICorDAO {

	Collection<Cor> obterTodos() throws SQLException;
	
	Cor obterPorId(int id) throws SQLException, NoSuchElementException;
	
	Collection<Cor> obterCoresPorSimbolo(int simbolo) throws SQLException;
	
	Collection<String> obterSimbolosDasCores() throws SQLException;
}
