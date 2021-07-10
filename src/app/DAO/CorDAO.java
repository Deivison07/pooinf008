package app.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import app.DAO.interfaces.ICorDAO;
import app.model.Cor;
import app.model.CorCMYK;
import app.model.CorRGB;
import app.Util.Util;
import app.enums.CorEnum;
import app.enums.SimboloEnum;

public class CorDAO implements ICorDAO {
	
	private static final String CAMPO_ID = "id";
	
	public CorDAO() {
	}
	
	/**
	 * Metodo para salvar uma cor no banco de dados
	 * @param cor cor a ser salva
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void salvar(Cor cor) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Statement sttmt = Conexao.getConexao().createStatement();
		
		String query = new String("INSERT INTO COR (%s) VALUES (%s)");
		Map<String, Object> camposMapeados = this.mapearPropriedades(cor, false);
		
		String colunas = "";
		String valores = "";
		
		for (Map.Entry<String, Object> campo : camposMapeados.entrySet()) {
			if (campo.getValue() instanceof String)
				valores += "'" + campo.getValue() + "',";
			else
				valores += campo.getValue() + ",";
			
			colunas += campo.getKey() + ",";
		}
		
		colunas = colunas.substring(0, colunas.length() - 1);
		valores = valores.substring(0, valores.length() - 1);
		
		query = String.format(query, colunas, valores);
		
		sttmt.executeUpdate(query);
	}
	
	/**
	 * Metodo para atualizar uma cor no banco de dados
	 * @param cor cor a ser atualizada
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void atualizar(Cor cor) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Statement sttmt = Conexao.getConexao().createStatement();
		
		String query = new String("UPDATE COR SET %s WHERE " + CorDAO.CAMPO_ID +  " = " + cor.getId());
		Map<String, Object> camposMapeados = this.mapearPropriedades(cor, false);
		
		String valores = "";
		for (Map.Entry<String, Object> campo : camposMapeados.entrySet()) {
			if (campo.getValue() instanceof String)
				valores += campo.getKey() + " = " + "'" + campo.getValue() + "',";
			else
				valores += campo.getKey() + " = " + campo.getValue() + ",";
		}
		
		valores = valores.substring(0, valores.length() - 1);
		
		query = String.format(query, valores);
		
		sttmt.execute(query);
	}
	
	/**
	 * Metodo para obter todas as cores do banco de dados
	 * @return Colecao de cores
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public Collection<Cor> obterTodos() throws SQLException, IllegalArgumentException, IllegalAccessException {
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
	public Cor obterPorId(int id) throws IllegalArgumentException, IllegalAccessException, SQLException, NoSuchElementException {
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
	public Collection<Cor> obterCoresPorSimbolo(int simbolo) throws SQLException, IllegalArgumentException, IllegalAccessException {
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
	private Collection<Cor> mapearResultSetParaCores(ResultSet rSet) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Collection<Cor> cores = new ArrayList<Cor>();
		
		while(rSet.next()) {
			int tipo = rSet.getInt("tipo");
			
			Cor cor = this.obterCorPorTipo(tipo);
			Field[] campos = Util.obterPropriedades(cor.getClass());
			
			for (Field campo : campos) {
				if (Modifier.isFinal(campo.getModifiers()))
					continue;
				
				campo.setAccessible(true);
				
				campo.set(cor, rSet.getObject(campo.getName()));
			}
			
			cores.add(cor);
		}
		
		return cores;
	}
	
	/**
	 * Metodo para obter uma instancia de uma cor dado seu tipo
	 * @param tipo tipo de cor presente em CorEnum
	 * @return instancia de cor ou null caso o tipo seja invalido
	 */
	private Cor obterCorPorTipo(int tipo) {
		if (tipo == CorEnum.RGB.getValor())
			return new CorRGB();
		else if (tipo == CorEnum.CMYK.getValor())
			return new CorCMYK();
		
		return null;
	}
	
	/**
	 * Metodo para mapear as propriedades de uma instância de classe Cor para um Mapa<campo, valor>.
	 * O mapa gerado terá todas as propriedades da superclasse e da classe filha e seus valores,
	 * exeto as que sao final.
	 * @param cor a cor para gerar o mapa
	 * @param comId booleano que indica se o metodo deve retornar a propriedade id no mapa
	 * @return Mapa com todas as propriedades 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private Map<String, Object> mapearPropriedades(Cor cor, boolean comId) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> propriedades = new HashMap<String, Object>();

		Field[] campos = Util.obterPropriedades(cor.getClass());
		
		for (Field campo : campos) {
			if ((Modifier.isFinal(campo.getModifiers())) || (campo.getName().equals(CorDAO.CAMPO_ID) && !comId))
				continue;

		    campo.setAccessible(true);
		    
		    propriedades.put(campo.getName(), campo.get(cor));
		}
		
		return propriedades;
	}
}
