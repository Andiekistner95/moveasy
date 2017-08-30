package br.com.moveasy.service;

import java.sql.SQLException;
import br.com.moveasy.model.Usuarios;

public class Usuarios {

	public void cadastrar(String login, String senha) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).cadastrar(login, senha);
		}
	}
	
	public List<Usuarios> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new UsuariosDAO(conexao).lista();
		}
	}
	
	public void editar(Usuarios usuarios) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).editar(usuarios.getCod_usuario);
		}
	}
	
	public void deletar(Usuarios usuarios) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new UsuariosDAO(conexao).deletar(usuarios.getCod_usuario);
		}
	}
	
}
