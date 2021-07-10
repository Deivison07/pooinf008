package app.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Conexao {
	private static final String URI = "jdbc:postgresql://localhost/aviii";
	private static final String USER = "postgres";
	private static final String PWD = "ads";
	
	public static Connection getConexao() throws SQLException {
		return DriverManager.getConnection(Conexao.URI, Conexao.USER, Conexao.PWD);
	}
}
