package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Usuarios;

public class EmpresasDAO {

	private final Connection conexao;
	
	public EmpresasDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(int cod_empresa, String nome_fantasia, String razao_social, String cnpj, int endereco_empresa,
			String email, String telefone, int usuario) throws SQLException {
		
		String sql = "INSERT INTO EMPRESAS ( COD_EMPRESA, NOME_FANTASIA, RAZAO_SOCIAL, CNPJ, TELEFONE, EMAIL, CAIXA_TERMICA, ENDERECO_EMPRESA, USUARIO) VALUES ( SEQ_EMPRESA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
				
		statement.setInt(1, cod_empresa);
		statement.setString(2, nome_fantasia);
		statement.setString(3, razao_social);
		statement.setString(4, cnpj);
		statement.setInt(5, endereco_empresa);
		statement.setString(6, email);
		statement.setString(7, telefone);
		statement.setInt(8, usuario);

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Empresa inserida com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Empresas empresas) throws SQLException {
		String sql = "UPDATE EMPRESAS SET NOME_FANTASIA = ?, RAZAO_SOCIAL = ?, CNPJ = ?, TELEFONE = ?, EMAIL = ?, CAIXA_TERMICA = ?, ENDERECO_EMPRESA = ?, USUARIO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, empresas.getCod_empresa());
		statement.setString(2, empresas.getNome_fantasia());
		statement.setString(3, empresas.getRazao_social());
		statement.setString(4, empresas.getCnpj());
		statement.setObject(5, empresas.getEndereco_empresa());
		statement.setString(6, empresas.getEmail());
		statement.setString(7, empresas.getTelefone());
		statement.setObject(8, empresas.getUsuario());

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
		String sql = "UPDATE EMPRESAS SET NOME_FANTASIA = ?, RAZAO_SOCIAL = ?, CNPJ = ?, TELEFONE = ?, EMAIL = ?, CAIXA_TERMICA = ?, ENDERECO_EMPRESA = ?, USUARIO = ?";

		Empresas empresas = null;
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, empresas.getCod_empresa());
		statement.setString(2, empresas.getNome_fantasia());
		statement.setString(3, empresas.getRazao_social());
		statement.setString(4, empresas.getCnpj());
		statement.setObject(5, empresas.getEndereco_empresa());
		statement.setString(6, empresas.getEmail());
		statement.setString(7, empresas.getTelefone());
		statement.setObject(8, empresas.getUsuario());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	public String imprimirDados(int codEmpresas) throws SQLException {
		
		String dados = "";
		
		String sql;
		sql =  "SELECT "
				+ " EMPRESAS.COD_EMPRESA, "
				+ " EMPRESAS.NOME_FANTASIA, "
				+ " EMPRESAS.RAZAO_SOCIAL, "
				+ " EMPRESAS.CNPJ, "
				 + " EMPRESAS.TELEFONE, "
				 + " EMPRESAS.EMAIL, "
				 + " EMPRESAS.CAIXA_TERMICA, "
				 + " ENDERECO.COD_ENDERECO, "
				 + " ENDERECO.RUA, "
				 + " ENDERECO.NUMERO, "
				 + " ENDERECO.COMPLEMENTO, "
				 + " ENDERECO.BAIRRO, "
				 + " CIDADES.COD_CIDADE, "
				 + " CIDADES.NOME_CIDADE, "
				 + " ESTADOS.COD_ESTADO," 
				 + " ESTADOS.NOME_ESTADO, "
				 + " ESTADOS.UF, "
				 + " USUARIOS.COD_USUARIO," 
				 + " USUARIOS.LOGIN, "
				 + " USUARIOS.SENHA, "
				 + " USUARIOS.STATUS "
				 + " FROM "
				 + " EMPRESAS "                
                 + " INNER JOIN ENDERECO ON "
				 + " COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA "
			 + " INNER JOIN CIDADES ON "
				 + " CIDADES.COD_CIDADE = ENDERECO.CIDADE " 
			 + " INNER JOIN ESTADOS ON "
				 + " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ " INNER JOIN USUARIOS ON "
			+ " USUARIOS.COD_USUARIO = EMPRESAS.USUARIO "
                    + " WHERE "
				    + " COD_EMPRESA = ? ";
		
						
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codEmpresas);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					//Empresas			
					int cod_empresa = rs.getInt(1);
					String nome_fantasia = rs.getString(2);
					String razao_social = rs.getString(3);
					String cnpj = rs.getString(4);
					String email = rs.getString(5);
					String telefone = rs.getString(6);
										
					//Endereco
					int cod_endereco = rs.getInt(7);
					String rua = rs.getString(8);
					String numero = rs.getString(9);
					String complemento = rs.getString(10);
					String bairro = rs.getString(11);
					
					
					//Cidades
					int cod_cidade = rs.getInt(12);
					String nome_cidade = rs.getString(13);
					
					//Estados
					int cod_estado = rs.getInt(14);
					String nome_estado = rs.getString(15);
					String uf = rs.getString(16);
					
					//Usuarios
					int cod_usuario = rs.getInt(17);
					String login = rs.getString(18);
					String senha  = rs.getString(19);
					int status = rs.getInt(20);
										
					dados += "Código da Empresa: " + cod_empresa;
					dados += "Nome fantasia da Empresa: " + nome_fantasia;
					dados += "Razao social do Empresa: " + razao_social;
					dados += "CNPJ do Empresa: " + cnpj;
					dados += "Email do Empresa: " + email;
					dados += "Telefone do Empresa: " + telefone;
					
				}
							
			}
		}

		return dados;
	}
	
	public List<Empresas> listar() throws SQLException {
		List<Empresas> lEmpresas = new ArrayList<>();
		
		String sql;
		sql = "SELECT "
				+ " EMPRESAS.COD_EMPRESA, "
				+ " EMPRESAS.NOME_FANTASIA, "
				+ " EMPRESAS.RAZAO_SOCIAL, "
				+ " EMPRESAS.CNPJ, "
				 + " EMPRESAS.TELEFONE, "
				 + " EMPRESAS.EMAIL, "
				 + " EMPRESAS.CAIXA_TERMICA, "
				 + " ENDERECO.COD_ENDERECO, "
				 + " ENDERECO.RUA, "
				 + " ENDERECO.NUMERO, "
				 + " ENDERECO.COMPLEMENTO, "
				 + " ENDERECO.BAIRRO, "
				 + " CIDADES.COD_CIDADE, "
				 + " CIDADES.NOME_CIDADE, "
				 + " ESTADOS.COD_ESTADO," 
				 + " ESTADOS.NOME_ESTADO, "
				 + " ESTADOS.UF, "
				 + " USUARIOS.COD_USUARIO," 
				 + " USUARIOS.LOGIN, "
				 + " USUARIOS.SENHA, "
				 + " USUARIOS.STATUS "
				 + " FROM "
				 + " EMPRESAS "
                
                 + " INNER JOIN ENDERECO ON "
				 + " COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA "
			 + " INNER JOIN CIDADES ON "
				 + " CIDADES.COD_CIDADE = ENDERECO.CIDADE " 
			 + " INNER JOIN ESTADOS ON "
				 + " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ " INNER JOIN USUARIOS ON "
			+ " USUARIOS.COD_USUARIO = EMPRESAS.USUARIO ";
                   
                   		            
		          											
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet())
			{
				while (rs.next()) {
					
					//Empresas			
					int cod_empresa = rs.getInt(1);
					String nome_fantasia = rs.getString(2);
					String razao_social = rs.getString(3);
					String cnpj = rs.getString(4);
					String email = rs.getString(5);
					String telefone = rs.getString(6);
										
					//Endereco
					int cod_endereco = rs.getInt(7);
					String rua = rs.getString(8);
					String numero = rs.getString(9);
					String complemento = rs.getString(10);
					String bairro = rs.getString(11);
					
					
					//Cidades
					int cod_cidade = rs.getInt(12);
					String nome_cidade = rs.getString(13);
					
					//Estados
					int cod_estado = rs.getInt(14);
					String nome_estado = rs.getString(15);
					String uf = rs.getString(16);
					
					//Usuarios
					int cod_usuario = rs.getInt(17);
					String login = rs.getString(18);
					String senha  = rs.getString(19);
					int status = rs.getInt(20);
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
					Usuarios usuarios = new Usuarios(cod_usuario, login, senha, status);
					Empresas empresas = new Empresas(cod_empresa, nome_fantasia, razao_social, cnpj, endereco, email, telefone, usuarios);
					
					lEmpresas.add(empresas);
					
														}
			}
		}

		return lEmpresas;

	}
	
	public Empresas listar(int codigo) throws SQLException {
		Empresas empresas = null;
		
		String sql;
		sql = "SELECT "
				+ " EMPRESAS.COD_EMPRESA, "
				+ " EMPRESAS.NOME_FANTASIA, "
				+ " EMPRESAS.RAZAO_SOCIAL, "
				+ " EMPRESAS.CNPJ, "
				 + " EMPRESAS.TELEFONE, "
				 + " EMPRESAS.EMAIL, "
				 + " EMPRESAS.CAIXA_TERMICA, "
				 + " ENDERECO.COD_ENDERECO, "
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
				 + " EMPRESAS "                
                 + " INNER JOIN ENDERECO ON "
				 + " COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA "
			 + " INNER JOIN CIDADES ON "
				 + " CIDADES.COD_CIDADE = ENDERECO.CIDADE " 
			 + " INNER JOIN ESTADOS ON "
				 + " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
			+ " INNER JOIN USUARIOS ON "
			+ " USUARIOS.COD_USUARIO = EMPRESAS.USUARIO "
                    + " WHERE "
				    + " COD_EMPRESA = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					
					//Empresas			
					int cod_empresa = rs.getInt(1);
					String nome_fantasia = rs.getString(2);
					String razao_social = rs.getString(3);
					String cnpj = rs.getString(4);
					String email = rs.getString(5);
					String telefone = rs.getString(6);
										
					//Endereco
					int cod_endereco = rs.getInt(7);
					String rua = rs.getString(8);
					String numero = rs.getString(9);
					String complemento = rs.getString(10);
					String bairro = rs.getString(11);
					
					
					//Cidades
					int cod_cidade = rs.getInt(12);
					String nome_cidade = rs.getString(13);
					
					//Estados
					int cod_estado = rs.getInt(14);
					String nome_estado = rs.getString(15);
					String uf = rs.getString(16);
					
					//Usuarios
					int cod_usuario = rs.getInt(17);
					String login = rs.getString(18);
					String senha  = rs.getString(19);
					int status = rs.getInt(20);
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
					Usuarios usuarios = new Usuarios(cod_usuario, login, senha, status);
					empresas = new Empresas(cod_empresa, nome_fantasia, razao_social, cnpj, endereco, email, telefone, usuarios);
															
				}
			}
		}

		return empresas;

	}
	
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = "DELETE EMPRESAS WHERE COD_EMPRESA = ?";

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
	
	public String deletar(Empresas empresas) throws SQLException {
		String sql = "DELETE EMPRESAS WHERE COD_EMPRESA = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setObject(1, empresas.getCod_empresa());


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