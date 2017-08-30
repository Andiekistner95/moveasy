package br.com.moveasy.service;

import br.com.moveasy.model.Estados;

public class EstadosService {

	public void cadastrar(String nome_estado, String uf) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EstadosDAO(conexao).cadastrar(nome_estado, uf);
		}
	}
	
	public List<Estados> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EstadosDAO(conexao).lista();
		}
	}
	
	public void editar(Estados estados) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EstadosDAO(conexao).editar(estados.getCod_estado());
		}
	}
	
	public void deletar(Estados estados) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EstadosDAO(conexao).deletar(estados.getCod_estado());
		}
	}
	
}
