package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Usuarios;

public class UsuariosDAO {

	private final Connection conexao;
	
	public UsuariosDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(int cod_usuario, String login, String senha, int status) throws SQLException {
		
		String sql = "INSERT INTO USUARIOS ( COD_USUARIO, LOGIN, SENHA, STATUS) VALUES ( SEQ_USUARIO.NEXTVAL, ?, ?, ?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, login);
		statement.setObject(2, senha);
		statement.setInt(3, status);

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Usuário inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Usuarios usuarios) throws SQLException {
		String sql = "UPDATE USUARIOS SET LOGIN = ?, SENHA = ?,  STATUS = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, usuarios.getLogin());
		statement.setObject(2, usuarios.getSenha());
		statement.setInt(3, usuarios.getStatus());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	public String editar(int codigo) throws SQLException {
		String sql = "UPDATE USUARIOS SET LOGIN = ?, SENHA = ?,  STATUS = ?";

		Usuarios usuarios = null;
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, usuarios.getLogin());
		statement.setObject(2, usuarios.getSenha());
		statement.setInt(3, usuarios.getStatus());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	public String imprimirDados(int codUsuarios) throws SQLException {
		
		String dados = "";
		
		String sql;
		sql = " SELECT "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " USUARIOS"
		            + " WHERE "
				   + " COD_USUARIO = ? ";
				
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codUsuarios);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
										
					//Usuarios
					int cod_usuario = rs.getInt(1);
					String login = rs.getString(2);
					String senha  = rs.getString(3);
					int status = rs.getInt(4);
										
					dados += "Código do usuário: " + cod_usuario;
					dados += "Login do usuário: " + login;
					dados += "Senha do usuário: " + senha;
					dados += "Status do usuário: " + status;
					
				}
							
			}
		}

		return dados;
	}
	
	public List<Usuarios> listar() throws SQLException {
		List<Usuarios> lUsuarios = new ArrayList<>();
		
		String sql;
		sql = " SELECT "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " USUARIOS";
		            
		          											
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet())
			{
				while (rs.next()) {
					
					
					//Usuarios
					int cod_usuario = rs.getInt(1);
					String login = rs.getString(2);
					String senha  = rs.getString(3);
					int status = rs.getInt(4);
					
					Usuarios usuarios = new Usuarios(cod_usuario, login, senha, status);
					
					lUsuarios.add(usuarios);
					
														}
			}
		}

		return lUsuarios;

	}
	
	public Usuarios listar(int codigo) throws SQLException {
		Usuarios usuarios = null;
		
		String sql;
		sql = " SELECT "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " USUARIOS"
		            + " WHERE "
				   + " COD_USUARIO = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					
					//Usuarios
					int cod_usuario = rs.getInt(1);
					String login = rs.getString(2);
					String senha  = rs.getString(3);
					int status = rs.getInt(4);
					
					usuarios = new Usuarios(cod_usuario, login, senha, status);
															
				}
			}
		}

		return usuarios;

	}
	
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = "DELETE USUARIOS WHERE COD_USUARIO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, codigo);


		boolean deletado = statement.executeUpdate() > 0; 
		String info;
		
		if ( deletado == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	
		
}
	
	public String deletar(Usuarios usuarios) throws SQLException {
		String sql = "DELETE USUARIOS WHERE COD_USUARIO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setObject(1, usuarios.getCod_usuario());


		boolean deletado = statement.executeUpdate() > 0; 
		String info;
		
		if ( deletado == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	
	}
}
