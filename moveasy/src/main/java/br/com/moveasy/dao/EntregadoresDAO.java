package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Avaliacao_Ent;
import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Tipo_servico;
import br.com.moveasy.model.Usuarios;

public class EntregadoresDAO {

private final Connection conexao;
	
	public EntregadoresDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(String nome_ent, String sobrenome_ent, String cpf, Endereco endereco_ent, Usuarios usuario) throws SQLException {
		
		String sql = "INSERT INTO ENTREGADORES ( COD_ENT, NOME_ENT, SOBRENOME_ENT, CPF_ENT, ENDERECO_ENT, USUARIO ) VALUES ( SEQ_ENT.NEXTVAL, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
	
		statement.setString(1, nome_ent);
		statement.setString(2, sobrenome_ent);
		statement.setObject(3, endereco_ent);
		statement.setObject(4, usuario);

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Entregador inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Entregadores entregadores) throws SQLException {
		String sql = "UPDATE ENTREGADORES SET NOME_ENT = ?, SOBRENOME_ENT = ?,  CPF_ENT = ?,  ENDERECO_ENT = ?,  USUARIO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, entregadores.getNome_ent());
		statement.setString(2, entregadores.getSobrenome_ent());
		statement.setObject(3, entregadores.getEndereco_ent());
		statement.setObject(4, entregadores.getUsuario());

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
		String sql = "UPDATE ENTREGADORES SET NOME_ENT = ?, SOBRENOME_ENT = ?,  CPF_ENT = ?,  ENDERECO_ENT = ?,  USUARIO = ?";

		Entregadores entregadores = null;
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, entregadores.getNome_ent());
		statement.setString(2, entregadores.getSobrenome_ent());
		statement.setObject(3, entregadores.getEndereco_ent());
		statement.setObject(4, entregadores.getUsuario());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	public String imprimirDados(int codEntregadores) throws SQLException {
		
		String dados = "";
		
		String sql;
		sql = " SELECT "
				+ " ENTREGADORES.COD_ENT, "
                + " ENTREGADORES.NOME_ENT, "
				+ " ENTREGADORES.SOBRENOME_ENT, "
				+ " ENTREGADORES.CPF_ENT, "
				+ " ENDERECO.COD_ENDERECO,"
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO, "
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF, "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " ENTREGADORES"
			+ " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = ENDERECO_ENT "
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = ENDERECO.CIDADE "
			+ " INNER JOIN ESTADOS ON "
				+ " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ "INNER JOIN USUARIOS ON "
			+ "USUARIOS.COD_USUARIO = ENTREGADORES.USUARIO" 
		             + " WHERE "
				   + " COD_ENT = ? ";
				

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codEntregadores);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					//Entregadores
					int codEntregador = rs.getInt(1);
					String nomeEntregador = rs.getString(2);
					String sobrenomeEntregador = rs.getString(3);
					String cpf = rs.getString(4);
					
					//Endereco
					int cod_endereco = rs.getInt(5);
					String rua = rs.getString(6);
					String numero = rs.getString(7);
					String complemento = rs.getString(8);
					String bairro = rs.getString(9);
					
					//Cidades
					int cod_cidade = rs.getInt(10);
					String nome_cidade = rs.getString(11);
					
					//Estados
					int cod_estado = rs.getInt(12);
					String nome_estado = rs.getString(13);
					String uf = rs.getString(14);
					
					//Usuarios
					int cod_usuario = rs.getInt(15);
					String login = rs.getString(16);
					String senha  = rs.getString(17);
					int status = rs.getInt(18);
										
					dados += "Código do entregador: " + codEntregador;
					dados += "Nome do entregador: " + nomeEntregador;
					dados += "Sobrenome do entregador: " + sobrenomeEntregador;
					dados += "CPF do entregador: " + cpf;
					
				}
							
			}
		}

		return dados;
	}
	
	public List<Entregadores> listar() throws SQLException {
		List<Entregadores> lEntregadores = new ArrayList<>();
		
		String sql;
		sql = " SELECT "
				+ " ENTREGADORES.COD_ENT, "
                + " ENTREGADORES.NOME_ENT, "
				+ " ENTREGADORES.SOBRENOME_ENT, "
				+ " ENTREGADORES.CPF_ENT, "
				+ " ENDERECO.COD_ENDERECO,"
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO, "
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF, "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " ENTREGADORES"
			+ " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = ENDERECO_ENT "
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = ENDERECO.CIDADE "
			+ " INNER JOIN ESTADOS ON "
				+ " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ "INNER JOIN USUARIOS ON "
			+ "USUARIOS.COD_USUARIO = ENTREGADORES.USUARIO"; 
		          											
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet())
			{
				while (rs.next()) {
					//Entregadores
					int codEntregador = rs.getInt(1);
					String nomeEntregador = rs.getString(2);
					String sobrenomeEntregador = rs.getString(3);
					String cpf = rs.getString(4);
					
					//Endereco
					int cod_endereco = rs.getInt(5);
					String rua = rs.getString(6);
					String numero = rs.getString(7);
					String complemento = rs.getString(8);
					String bairro = rs.getString(9);
					
					//Cidades
					int cod_cidade = rs.getInt(10);
					String nome_cidade = rs.getString(11);
					
					//Estados
					int cod_estado = rs.getInt(12);
					String nome_estado = rs.getString(13);
					String uf = rs.getString(14);
					
					//Usuarios
					int cod_usuario = rs.getInt(15);
					String login = rs.getString(16);
					String senha  = rs.getString(17);
					int status = rs.getInt(18);
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
					Usuarios usuarios = new Usuarios(cod_usuario, login, senha, status);
					Entregadores entregador = new Entregadores(codEntregador, nomeEntregador, sobrenomeEntregador, cpf, endereco, usuarios );
					
					lEntregadores.add(entregador);
					
														}
			}
		}

		return lEntregadores;

	}
	
	public Entregadores listar(int codigo) throws SQLException {
		Entregadores entregadores = null;
		
		String sql;
		sql = " SELECT "
				+ " ENTREGADORES.COD_ENT, "
                + " ENTREGADORES.NOME_ENT, "
				+ " ENTREGADORES.SOBRENOME_ENT, "
				+ " ENTREGADORES.CPF_ENT, "
				+ " ENDERECO.COD_ENDERECO,"
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO, "
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF, "
				+ " USUARIOS.COD_USUARIO, "
				+ " USUARIOS.LOGIN, "
				+ " USUARIOS.SENHA, "
				+ " USUARIOS.STATUS "
				+ " FROM "
				+ " ENTREGADORES"
			+ " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = ENDERECO_ENT "
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = ENDERECO.CIDADE "
			+ " INNER JOIN ESTADOS ON "
				+ " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ "INNER JOIN USUARIOS ON "
			+ "USUARIOS.COD_USUARIO = ENTREGADORES.USUARIO" 
		             + " WHERE "
				   + " COD_ENT = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					//Entregadores
					int codEntregador = rs.getInt(1);
					String nomeEntregador = rs.getString(2);
					String sobrenomeEntregador = rs.getString(3);
					String cpf = rs.getString(4);
					
					//Endereco
					int cod_endereco = rs.getInt(5);
					String rua = rs.getString(6);
					String numero = rs.getString(7);
					String complemento = rs.getString(8);
					String bairro = rs.getString(9);
					
					//Cidades
					int cod_cidade = rs.getInt(10);
					String nome_cidade = rs.getString(11);
					
					//Estados
					int cod_estado = rs.getInt(12);
					String nome_estado = rs.getString(13);
					String uf = rs.getString(14);
					
					//Usuarios
					int cod_usuario = rs.getInt(15);
					String login = rs.getString(16);
					String senha  = rs.getString(17);
					int status = rs.getInt(18);
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
					Usuarios usuarios = new Usuarios(cod_usuario, login, senha, status);
					entregadores = new Entregadores(codEntregador, nomeEntregador, sobrenomeEntregador, cpf, endereco, usuarios );
															
				}
			}
		}

		return entregadores;

	}
	
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = "DELETE ENTREGADORES WHERE COD_ENT = ?";

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
	
	public String deletar(Entregadores entregadores) throws SQLException {
		String sql = "DELETE ENTREGADORES WHERE COD_ENT = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setObject(1, entregadores.getCod_ent());


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