package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Tipo_servico;

public class CidadesDAO {
	
	private final Connection conexao;
	
	public CidadesDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(String nome_cidade, Estados estado) throws SQLException {
		
		String sql = "INSERT INTO CIDADES ( COD_CIDADE, NOME_CIDADE, ESTADO ) VALUES ( SEQ_CIDADE.NEXTVAL, ?, ? )";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nome_cidade);
		statement.setInt(2, estado.getCod_estado());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Estado inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Cidades cidade) throws SQLException {
		String sql = "UPDATE CIDADES SET NOME_CIDADE = ?, ESTADO = ? WHERE CODIGO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, cidade.getNome_cidade());
		statement.setInt(2, cidade.getEstado().getCod_estado());
		statement.setInt(3, cidade.getCod_cidade());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	
	public String imprimirDados(Tipo_servico tipo_servico) throws SQLException {
		
		String dados = "";
		
		String sql;
		sql = " SELECT  "
				+ " COD_CIDADE, "
				+ " NOME_CIDADE, "
				+ " ESTADO,"
				+ " NOME_ESTADO, "
				+ " UF "
			+ " FROM "
				+ " CIDADES "
			+ " INNER JOIN ESTADOS ON"
				+ " COD_ESTADO = ESTADO "
			+ " WHERE"
				+ "	COD_CIDADE = ? ";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, tipo_servico.getCod_cidade());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoCidade = rs.getInt(1);
					String nomeCidade = rs.getString(2);
					int codigoEstado = rs.getInt(3);
					String nomeEstado = rs.getString(4);
					String uf = rs.getString(5);
					
					dados = "Cidade: " + codigoCidade + " - " + nomeCidade;
					dados += "\n Estado: " + codigoEstado + " - " + nomeEstado + " - UF: " + uf; 
				}
				
				
			}
		}

		return dados;
	}
	
	public List<Cidades> listar() throws SQLException {
		List<Cidades> lCidades = new ArrayList<>();
		
		String sql;
		sql = " SELECT "
				+ " COD_CIDADE, "
				+ " NOME_CIDADE, "
				+ " ESTADO,"
				+ " NOME_ESTADO, "
				+ " UF "
			+ " FROM "
				+ " CIDADES "
			+ " INNER JOIN ESTADOS ON"
				+ " COD_ESTADO = ESTADO"
			+ " WHERE "
				+ " ROWNUM < 10 ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoCidade = rs.getInt(1);
					String nomeCidade = rs.getString(2);
					int codigoEstado = rs.getInt(3);
					String nomeEstado = rs.getString(4);
					String uf = rs.getString(5);
					
					Cidades cidade = new Cidades(codigoCidade, nomeCidade, new Estados(codigoEstado, nomeEstado, uf));
					lCidades.add(cidade);
				}
			}
		}

		return lCidades;

	}
	
	public Cidades listar(int codigo) throws SQLException {
		Cidades cidade = null;
		
		String sql;
		sql = " SELECT "
				+ " COD_CIDADE, "
				+ " NOME_CIDADE, "
				+ " ESTADO,"
				+ " NOME_ESTADO, "
				+ " UF "
			+ " FROM "
				+ " CIDADES "
			+ " INNER JOIN ESTADOS ON"
				+ " COD_ESTADO = ESTADO"
			+ " WHERE "
				+ " COD_CIDADE = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoCidade = rs.getInt(1);
					String nomeCidade = rs.getString(2);
					int codigoEstado = rs.getInt(3);
					String nomeEstado = rs.getString(4);
					String uf = rs.getString(5);
					
					cidade = new Cidades(codigoCidade, nomeCidade, new Estados(codigoEstado, nomeEstado, uf));
				}
			}
		}

		return cidade;

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
