package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Estados;

public class AvalicaoService {

	public void cadastrar(int cod_ent, int estrelas ) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new AvaliacaoDAO(conexao).cadastrar(cod_ent, estrelas );
		}
	}
	
	public List<Avaliacao> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new AvaliacaoDAO(conexao).lista();
		}
	}
	

	
}
