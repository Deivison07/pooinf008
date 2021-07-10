package app.DAO.interfaces;

import java.sql.SQLException;
import java.util.Collection;
import java.util.NoSuchElementException;

import app.model.Cor;

public interface ICorDAO {

	void salvar(Cor cor) throws SQLException, IllegalArgumentException, IllegalAccessException;
	
	void atualizar(Cor cor) throws SQLException, IllegalArgumentException, IllegalAccessException;
	
	Collection<Cor> obterTodos() throws SQLException, IllegalArgumentException, IllegalAccessException;
	
	Cor obterPorId(int id) throws IllegalArgumentException, IllegalAccessException, SQLException, NoSuchElementException;
	
	Collection<Cor> obterCoresPorSimbolo(int simbolo) throws SQLException, IllegalArgumentException, IllegalAccessException;
	
	Collection<String> obterSimbolosDasCores() throws SQLException;
}
