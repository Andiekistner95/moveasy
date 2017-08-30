package br.com.moveasy.jdbc.oracle;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexaOracle {

	public static void main(String[] args) throws SQLException {
		ConnectionPoolOracle database = new ConnectionPoolOracle();
		Connection connection = database.getConnection();
		System.out.println("Conex�o aberta!");
		connection.close();
	}

}
