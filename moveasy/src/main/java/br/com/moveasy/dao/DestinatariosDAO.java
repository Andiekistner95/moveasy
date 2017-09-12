package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Destinatarios;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Usuarios;

public class DestinatariosDAO {
	
private final Connection conexao;
	
	public DestinatariosDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(int cod_dest, String nome_dest, int endereco_dest) throws SQLException {
		
		String sql = "INSERT INTO DESTINATARIOS ( COD_DEST, NOME_DEST, ENDERECO_DEST) VALUES ( SEQ_DEST.NEXTVAL, ?, ?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
				
		statement.setInt(1, cod_dest);
		statement.setString(2, nome_dest);
		statement.setInt(3, endereco_dest);
	
		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Destinatário inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Destinatarios destinatarios) throws SQLException {
		String sql = "UPDATE DETINATARIOS SET NOME_DEST = ?, ENDERECO_DEST = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
	
		statement.setInt(1, destinatarios.getCod_dest());
		statement.setString(2, destinatarios.getNome_dest());
		statement.setObject(3, destinatarios.getEndereco_dest());
	

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
		String sql = "UPDATE DETINATARIOS SET NOME_DEST = ?, ENDERECO_DEST = ?";

		Destinatarios destinatarios = null;
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setInt(1, destinatarios.getCod_dest());
		statement.setString(2, destinatarios.getNome_dest());
		statement.setObject(3, destinatarios.getEndereco_dest());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	public String imprimirDados(int codDestinatarios) throws SQLException {
		
		String dados = "";
		
		String sql;
		sql =  " SELECT "
				+ " DESTINATARIOS.COD_DEST," 
				+ " DESTINATARIOS.NOME_DEST, "
				+ " ENDERECO.COD_ENDERECO, "
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO,"
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF "
				+ " FROM "
				+ " DESTINATARIOS "
                     + " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST " 
			         + " INNER JOIN CIDADES ON "
			    + " CIDADES.COD_CIDADE = ENDERECO.CIDADE "
			         + " INNER JOIN ESTADOS ON "
			    + " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
                    + " WHERE "
				    + "COD_DEST = ? ";
		
						
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codDestinatarios);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
									
					//Destinatários			
					int cod_dest = rs.getInt(1);
					String nome_dest = rs.getString(2);
					
														
					//Endereco
					int cod_endereco = rs.getInt(3);
					String rua = rs.getString(4);
					String numero = rs.getString(5);
					String complemento = rs.getString(6);
					String bairro = rs.getString(7);
					
					
					//Cidades
					int cod_cidade = rs.getInt(8);
					String nome_cidade = rs.getString(9);
					
					//Estados
					int cod_estado = rs.getInt(10);
					String nome_estado = rs.getString(11);
					String uf = rs.getString(12);
			
										
					dados += "Código do Destinatário: " + cod_dest;
					dados += "Nome do Destinatário: " + nome_dest;
				
				
					
				}
							
			}
		}

		return dados;
	}
	
	public List<Destinatarios> listar() throws SQLException {
		List<Destinatarios> lDestinatarios = new ArrayList<>();
		
		String sql;
		sql =  " SELECT "
				+ " DESTINATARIOS.COD_DEST," 
				+ " DESTINATARIOS.NOME_DEST, "
				+ " ENDERECO.COD_ENDERECO, "
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO,"
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF "
				+ " FROM "
				+ " DESTINATARIOS "
                     + " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST " 
			         + " INNER JOIN CIDADES ON "
			    + " CIDADES.COD_CIDADE = ENDERECO.CIDADE "
			         + " INNER JOIN ESTADOS ON "
			    + " ESTADOS.COD_ESTADO = CIDADES.ESTADO ";
                    
                   		            
		          											
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet())
			{
				while (rs.next()) {
					
					//Destinatários			
					int cod_dest = rs.getInt(1);
					String nome_dest = rs.getString(2);
					
														
					//Endereco
					int cod_endereco = rs.getInt(3);
					String rua = rs.getString(4);
					String numero = rs.getString(5);
					String complemento = rs.getString(6);
					String bairro = rs.getString(7);
					
					
					//Cidades
					int cod_cidade = rs.getInt(8);
					String nome_cidade = rs.getString(9);
					
					//Estados
					int cod_estado = rs.getInt(10);
					String nome_estado = rs.getString(11);
					String uf = rs.getString(12);
			
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
					Destinatarios destinatarios = new Destinatarios(cod_dest, nome_dest, endereco);
					
					lDestinatarios.add(destinatarios);
					
														}
			}
		}

		return lDestinatarios;

	}
	
	public Destinatarios listar(int codigo) throws SQLException {
		Destinatarios destinatarios = null;
		
		String sql;
		sql =  " SELECT "
				+ " DESTINATARIOS.COD_DEST," 
				+ " DESTINATARIOS.NOME_DEST, "
				+ " ENDERECO.COD_ENDERECO, "
				+ " ENDERECO.RUA, "
				+ " ENDERECO.NUMERO, "
				+ " ENDERECO.COMPLEMENTO, "
				+ " ENDERECO.BAIRRO, "
				+ " CIDADES.COD_CIDADE, "
				+ " CIDADES.NOME_CIDADE, "
				+ " ESTADOS.COD_ESTADO,"
				+ " ESTADOS.NOME_ESTADO, "
				+ " ESTADOS.UF "
				+ " FROM "
				+ " DESTINATARIOS "
                     + " INNER JOIN ENDERECO ON "
				+ " COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST " 
			         + " INNER JOIN CIDADES ON "
			    + " CIDADES.COD_CIDADE = ENDERECO.CIDADE "
			         + " INNER JOIN ESTADOS ON "
			    + " ESTADOS.COD_ESTADO = CIDADES.ESTADO "
                    + " WHERE "
				    + "COD_DEST = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
					//Destinatários			
					int cod_dest = rs.getInt(1);
					String nome_dest = rs.getString(2);
					
														
					//Endereco
					int cod_endereco = rs.getInt(3);
					String rua = rs.getString(4);
					String numero = rs.getString(5);
					String complemento = rs.getString(6);
					String bairro = rs.getString(7);
					
					
					//Cidades
					int cod_cidade = rs.getInt(8);
					String nome_cidade = rs.getString(9);
					
					//Estados
					int cod_estado = rs.getInt(10);
					String nome_estado = rs.getString(11);
					String uf = rs.getString(12);
			
					
					Estados estado = new Estados(cod_estado, nome_estado, uf);
					Cidades cidade = new Cidades(cod_cidade, nome_cidade, estado);
					Endereco endereco = new Endereco(cod_endereco, rua, numero, complemento, bairro, cidade);
				    destinatarios = new Destinatarios(cod_dest, nome_dest, endereco);
															
				}
			}
		}

		return destinatarios;

	}
	
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = "DELETE DESTINATARIOS WHERE COD_DEST = ?";

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
	
	public String deletar(Destinatarios destinatarios) throws SQLException {
		String sql = "DELETE DESTINATARIOS WHERE COD_DEST = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setObject(1, destinatarios.getCod_dest());


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