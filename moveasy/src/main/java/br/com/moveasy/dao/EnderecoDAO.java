package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Estados;

public class EnderecoDAO {
	
	private final Connection conexao;
	
	public EnderecoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(String rua, String numero, String complemento, String bairro, Cidades cidade ) throws SQLException {
		
		String sql = "INSERT INTO ENDERECO ( COD_ENDERECO, RUA, NUMERO, COMPLEMENTO, BAIRRO, CIDADE ) VALUES ( SEQ_ENDERECO.NEXTVAL,  ?, ?, ?, ?, ? )";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, rua);
		statement.setString(2, numero);
		statement.setString(3, complemento);
		statement.setString(4, bairro);
		statement.setInt(5, cidade.getCod_cidade());
		

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Estado inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Endereco endereco) throws SQLException {
		String sql = "UPDATE ENDERECO SET RUA = ?, NUMERO = ? COMPLEMENTO = ? BAIRRO = ? CIDADE = ? WHERE CODIGO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, endereco.getRua());
		statement.setString(2, endereco.getNumero());
		statement.setString(3, endereco.getComplemento());
		statement.setString(4, endereco.getBairro());
		statement.setInt(5, endereco.getCidade().getCod_cidade());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	
	public String imprimirDados(Endereco endereco) throws SQLException {
		
		String dados = "";
		
		String sql;
		sql = " SELECT "
				+ "COD_ENDERECO,"
				+ "RUA,"
				+ "NUMERO"
				+ "COMPLEMENTO"
				+ "BAIRRO"
				+ "CIDADE"
				+ "NOME_CIDADE"
				+ "UF" 
				    
			+ " FROM " 
				+ " ENDERECO "
				    
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = CIDADE "
				    
			+ " INNER JOIN ESTADOS ON "
				+ " COD_ESTADO = ESTADO "
			+ " WHERE "
				+ " COD_ENDERECO = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, endereco.getCod_endereco());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoEndereco = rs.getInt(1);
					String rua = rs.getString(2);
					String numero = rs.getString(3);
					String complemento = rs.getString(4);
					String bairro = rs.getString(5);
					int codigoCidade = rs.getInt(6);
					String nomeCidade = rs.getString(7);
					String uf = rs.getString(8);
					
					dados = "Rua: " + rua + ", " + numero + " " + complemento;
					dados += "\nBairro:" + bairro + " " + "Cidade: " + nomeCidade + " - " + uf;

				}
				
				
			}
		}

		return dados;
	}
	
	public List<Endereco> listar() throws SQLException {
		List<Endereco> lEnderecos = new ArrayList<>();
		
		String sql;
		sql = " SELECT "
				+ "COD_ENDERECO,"
				+ "RUA,"
				+ "NUMERO"
				+ "COMPLEMENTO"
				+ "BAIRRO"
				+ "CIDADE"
				+ "NOME_CIDADE"
				+ "UF"
				+ "COD_ESTADO"
				+ "NOME_ESTADO" 
				    
			+ " FROM " 
				+ " ENDERECO "
				    
			+ " INNER JOIN CIDADES ON "
				+ " COD_CIDADE = CIDADE "
				    
			+ " INNER JOIN ESTADOS ON "
				+ " COD_ESTADO = ESTADO ";
					
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoEndereco = rs.getInt(1);
					String rua = rs.getString(2);
					String numero = rs.getString(3);
					String complemento = rs.getString(4);
					String bairro = rs.getString(5);
					int codigoCidade = rs.getInt(6);
					String nomeCidade = rs.getString(7);
					String uf = rs.getString(8);
					String codigoEstado = rs.getString(9);
					String nomeEstado = rs.getString(10);
					
					//Endereco endereco = new Endereco(codigoEndereco, rua, numero, complemento, bairro, new Cidades(codigoCidade, nomeCidade, nomeCidade, new Estados(codigoEstado, nomeEstado, uf)));
					//lEnderecos.add(endereco);
				}
			}
		}

		return lEnderecos;

	}
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = "DELETE CIDADES WHERE COD_CIDADE = ?";

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

	
}
