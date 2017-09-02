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

public class Tipo_servicoDAO {

	private final Connection conexao;
	
	public Tipo_servicoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(String descricao, double taxa) throws SQLException {
		
		String sql = "INSERT INTO TIPO_SERVICO ( COD_SERVICO, DESCRICAO, TAXA ) VALUES ( SEQ_CIDADE.NEXTVAL, ?, ? )";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, descricao);
		statement.setDouble(2, (double) taxa);

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Tipo de servi�o inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Tipo_servico tipo_servico) throws SQLException {
		String sql = "UPDATE TIPO_SERVICO SET DESCRICAO = ?, TAXA = ?,  WHERE COD_SERVICO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, tipo_servico.getDescricao() );
		statement.setInt(2, (int) tipo_servico.getTaxa());
		statement.setInt(3, tipo_servico.getCod_servico());

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
				+ " COD_SERVICO, "
				+ " DESCRICAO, "
				+ " TAXA"
			+ " FROM "
				+ " TIPO_SERVICO "
			+ " WHERE "
				+ "	COD_SERVICO = ? ";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, tipo_servico.getCod_servico());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int cod_servico = rs.getInt(1);
					String descricao = rs.getString(2);
					double taxa = rs.getDouble(3);
										
					dados += "\nCódigo do Serviço: " + cod_servico;
					dados += "\nDescrição: " + descricao;
					dados += "\nTaxa: " + taxa;
					
				}
							
			}
		}

		return dados;
	}
	
	public List<Tipo_servico> listar() throws SQLException {
		List<Tipo_servico> lTipo_servico = new ArrayList<>();
		
		String sql;
		sql = " SELECT "
				+ " COD_SERVICO, "
				+ " DESCRICAO, "
				+ " TAXA"
			+ " FROM "
				+ " TIPO_SERVICO ";
											
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet())
			{
				while (rs.next()) {
					int cod_servico = rs.getInt(1);
					String descricao = rs.getString(2);
					double taxa = rs.getDouble(3);
					
					Tipo_servico tipo_servico = new Tipo_servico(cod_servico, descricao, taxa);
					lTipo_servico.add(tipo_servico);
				}
			}
		}

		return lTipo_servico;

	}
	
	public Tipo_servico listar(int codigo) throws SQLException {
		Tipo_servico tipo_servico = null;
		
		String sql;
		sql = " SELECT "
				+ " COD_SERVICO, "
				+ " DESCRICAO, "
				+ " TAXA"
				+ " FROM "
			+ " TIPO_SERVICO "
				+ " WHERE "
				+ " COD_SERVICO = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int cod_servico = rs.getInt(1);
					String descricao = rs.getString(2);
					double taxa = rs.getDouble(3);
					
					tipo_servico = new Tipo_servico(cod_servico, descricao, taxa);
				}
			}
		}

		return tipo_servico;

	}
	
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = "DELETE TIPO_SERVICO WHERE COD_SERVICO = ?";

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
