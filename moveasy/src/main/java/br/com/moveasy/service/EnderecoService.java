package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.EnderecoDAO;
import br.com.moveasy.dao.EstadosDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Estados;

public class EnderecoService {

	public void cadastrar(String rua, String numero, String complemento, String bairro, Cidades cidades) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EnderecoDAO(conexao).cadastrar(rua, numero, complemento, bairro, cidades);
		}
	}
	
	public List<Endereco> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EnderecoDAO(conexao).listar();
		}
	}
	
	public Endereco listar(int codigo) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EnderecoDAO(conexao).listar(codigo);
		}
	}
	
	public void editar(Endereco endereco) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EnderecoDAO(conexao).editar(endereco);
		}
	}
	
	public void deletar(Endereco endereco) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EnderecoDAO(conexao).deletar(endereco.getCod_endereco());
		}
	}
	
}
