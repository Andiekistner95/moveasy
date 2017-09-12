package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.DestinatariosDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Destinatarios;

public class DestinatariosService {

	public void cadastrar(int cod_dest, String nome_dest, int endereco_dest) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).cadastrar(cod_dest, nome_dest, endereco_dest);
		}
	}

	public void editar(Destinatarios destinatarios) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).editar(destinatarios.getCod_dest());
		}
	}

	public void editar(int codDestinatario) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).editar(codDestinatario);
		}
	}

	public String imprimirDados(int codDestinatario) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new DestinatariosDAO(conexao).imprimirDados(codDestinatario);
		}
	}

	public List<Destinatarios> listar() throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new DestinatariosDAO(conexao).listar();
		}
	}

	public Destinatarios listar(int codDestinatario) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new DestinatariosDAO(conexao).listar(codDestinatario);
		}
	}

	public void deletar(Destinatarios destinatarios) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).deletar(destinatarios.getCod_dest());
		}
	}

	public void deletar(int codDestinatario) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).deletar(codDestinatario);
		}
	}
}
