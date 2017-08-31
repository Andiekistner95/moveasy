package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.EstadosDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Estados;

public class EstadosService {

	public void cadastrar(String nome_estado, String uf) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EstadosDAO(conexao).cadastrar(nome_estado, uf);
		}
	}
	
	public List<Estados> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EstadosDAO(conexao).listar();
		}
	}
	
	public Estados listar(int codigo) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EstadosDAO(conexao).listar(codigo);
		}
	}
	
	public void editar(Estados estados) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EstadosDAO(conexao).editar(estados);
		}
	}
	
	public void deletar(Estados estados) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EstadosDAO(conexao).deletar(estados.getCod_estado());
		}
	}
	
	public String imprimirDados(Estados estados) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new EstadosDAO(conexao).imprimirDados(estados);
		}
	}
	
}
