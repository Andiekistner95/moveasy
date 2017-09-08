package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.AvaliacaoDAO;
import br.com.moveasy.dao.EntregadoresDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Avaliacao_Ent;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Usuarios;

public class EntregadoresService {

	public void cadastrar(String nome_ent, String sobrenome_ent, String cpf, Endereco endereco_ent, Usuarios usuario) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EntregadoresDAO(conexao).cadastrar(nome_ent, sobrenome_ent, cpf, endereco_ent, usuario);
		}
	}
	
	public void editar(Entregadores entregadores) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EntregadoresDAO(conexao).editar(entregadores.getCod_ent());
		}
	}
	
	public void editar(int codEntregador) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EntregadoresDAO(conexao).editar(codEntregador);
		}
	}
	
	public String imprimirDados(int codEntregador) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new EntregadoresDAO(conexao).imprimirDados(codEntregador);
		}
	}
	
	public List<Entregadores> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EntregadoresDAO(conexao).listar();
		}
	}
	public Entregadores listar(int codEntregador) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EntregadoresDAO(conexao).listar(codEntregador);
		}
	}	
	public void deletar(Entregadores entregadores) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EntregadoresDAO(conexao).deletar(entregadores.getCod_ent());
		}
	}
	public void deletar(int codEntregador) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EntregadoresDAO(conexao).deletar(codEntregador);
		}
	}
	
	
}
