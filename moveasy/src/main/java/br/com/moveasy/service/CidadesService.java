package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.CidadesDAO;
import br.com.moveasy.dao.EstadosDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Estados;

public class CidadesService {

	public void cadastrar(String nome_cidade, Estados estado) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new CidadesDAO(conexao).cadastrar(nome_cidade, estado);
		}
	}
	
	public List<Cidades> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new CidadesDAO(conexao).listar();
		}
	}
	
	public Cidades listar(int codigo) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new CidadesDAO(conexao).listar(codigo);
		}
	}
	
	public void editar(Cidades cidades) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new CidadesDAO(conexao).editar(cidades);
		}
	}
	
	public void deletar(Cidades cidades) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new CidadesDAO(conexao).deletar(cidades.getCod_cidade());
		}
	}
	
	public String imprimirDados(Cidades cidades) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new CidadesDAO(conexao).imprimirDados(cidades);
		}
	}
	
}
