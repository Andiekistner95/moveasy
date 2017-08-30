package br.com.moveasy.service;

import br.com.moveasy.model.Cidades;

public class CidadesService {

	public void cadastrar(String nome_cidade, Estados estado) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new CidadesDAO(conexao).cadastrar(nome_cidade, estado);
		}
	}
	
	public List<Empresas> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EmpresasDAO(conexao).lista();
		}
	}
	
	public void editar(Cidades cidades) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new CidadesDAO(conexao).editar(cidades.getCod_cidade());
		}
	}
	
	public void deletar(Cidades cidades) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new CidadesDAO(conexao).deletar(cidades.getCod_cidade());
		}
	}
	
}
