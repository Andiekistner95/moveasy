package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.UsuariosDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Usuarios;


public class UsuariosService {

	public void cadastrar(int cod_usuario, String login, String senha, int status) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).cadastrar(cod_usuario, login, senha, status);
		}
	}
	
	public void editar(Usuarios usuarios) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).editar(usuarios.getCod_usuario());
		}
	}
	
	public void editar(int codUsuario) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).editar(codUsuario);
		}
	}
	
	public String imprimirDados(int codUsuario) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new UsuariosDAO(conexao).imprimirDados(codUsuario);
		}
	}
	
	public List<Usuarios> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new UsuariosDAO(conexao).listar();
		}
	}
	public Usuarios listar(int codUsuario) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new UsuariosDAO(conexao).listar(codUsuario);
		}
	}	
	public void deletar(Usuarios usuarios) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).deletar(usuarios.getCod_usuario());
		}
	}
	public void deletar(int codUsuario) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).deletar(codUsuario);
		}
	}
	
}
