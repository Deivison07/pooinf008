package app.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import app.DAO.interfaces.ICorDAO;
import app.model.Cor;
import app.model.CorCMYK;
import app.model.CorRGB;
import app.enums.CorEnum;
import app.enums.SimboloEnum;

public class CorDAO implements ICorDAO {
	
	public CorDAO() {
	}
	
	/**
	 * Metodo para obter todas as cores do banco de dados
	 * @return Colecao de cores
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Override
	public Collection<Cor> obterTodos() throws SQLException {
		Statement sttmt = Conexao.getConexao().createStatement();
		
		ResultSet rSet = sttmt.executeQuery("SELECT * FROM COR");
		
		Collection<Cor> cores = this.mapearResultSetParaCores(rSet);
		
		return cores;
	}
	
	/**
	 * Metodo para obter a cor por seu identificador
	 * @param id id da cor para buscar
	 * @return cor solicitada
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws NoSuchElementException
	 */
	@Override
	public Cor obterPorId(int id) throws SQLException, NoSuchElementException {
		Statement sttmt = Conexao.getConexao().createStatement();
		
		ResultSet rSet = sttmt.executeQuery("SELECT * FROM COR WHERE id = " + id);
		
		Cor cor = this.mapearResultSetParaCores(rSet).iterator().next();
		
		return cor;
	}
	
	/**
	 * Metodo para obter todas as cores filtrando pelo seu simbolo
	 * @param simbolo simbolo para filtro
	 * @return Colecao de cores que representam o simbolo informado
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Override
	public Collection<Cor> obterCoresPorSimbolo(int simbolo) throws SQLException {
		Statement sttmt = Conexao.getConexao().createStatement();
		
		ResultSet rSet = sttmt.executeQuery("SELECT * FROM COR WHERE simbolo = " + simbolo);
		
		Collection<Cor> cores = this.mapearResultSetParaCores(rSet);
		
		return cores;
	}
	
	/**
	 * Metodo para obter todos simbolos em SimboloEnum que as cores do banco utilizam
	 * @return Colecao com os nomes dos simbolos utilizados
	 * @throws SQLException
	 */
	@Override
	public Collection<String> obterSimbolosDasCores() throws SQLException {
		Collection<String> simbolos = new ArrayList<String>();
		
		Statement sttmt = Conexao.getConexao().createStatement();
		
		ResultSet rSet = sttmt.executeQuery("SELECT DISTINCT simbolo FROM COR ORDER BY simbolo");
		
		while(rSet.next()) {
			int idSimbolo = rSet.getInt("simbolo");
			String nomeSimbolo = SimboloEnum.obterPorValor(idSimbolo).getNome();
			simbolos.add(nomeSimbolo);
		}
		
		return simbolos;
	}
	
	/**
	 * Metodo para transformar o ResultSet do resultado de uma consulta em uma colecao de cores
	 * @param rSet ResultSet retornado por uma consulta
	 * @return Colecao de cores presentes no ResultSet
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private Collection<Cor> mapearResultSetParaCores(ResultSet rSet) throws SQLException {
		Collection<Cor> cores = new ArrayList<Cor>();
		
		while(rSet.next()) {
			Cor cor = null;
			int id = rSet.getInt("id");
			int tipo = rSet.getInt("tipo");
			String nome = rSet.getString("nome");
			int simbolo = rSet.getInt("simbolo");
			
			if (tipo == CorEnum.RGB.getValor()) {
				int red = rSet.getInt("red");
				int green = rSet.getInt("green");
				int blue = rSet.getInt("blue");

				cor = new CorRGB(id, nome, simbolo, red, green, blue);
			}
			else if (tipo == CorEnum.CMYK.getValor()){
				int ciano = rSet.getInt("ciano");
				int magenta = rSet.getInt("magenta");
				int amarelo = rSet.getInt("amarelo");
				int preto = rSet.getInt("preto");

				cor = new CorCMYK(id, nome, simbolo, ciano, magenta, amarelo, preto);
			}
			
			cores.add(cor);
		}
		
		return cores;
	}
}
