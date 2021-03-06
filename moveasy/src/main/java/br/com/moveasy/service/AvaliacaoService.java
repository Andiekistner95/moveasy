package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.AvaliacaoDAO;
import br.com.moveasy.dao.EstadosDAO;
import br.com.moveasy.dao.Tipo_servicoDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Avaliacao_Ent;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Tipo_servico;

public class AvaliacaoService {

	public String cadastrar(int cod_ent, int estrelas ) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new AvaliacaoDAO(conexao).cadastrar(cod_ent, estrelas );
		}
		
	}
	
	public List<Avaliacao_Ent> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new AvaliacaoDAO(conexao).listar();
		}
	}
	
	public Avaliacao_Ent listar(int codigo) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new AvaliacaoDAO(conexao).listar(codigo);
		}
	}
		
	public String imprimirDados(int codAvaliacao) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new AvaliacaoDAO(conexao).imprimirDados(codAvaliacao);
		}
	}

	
}
