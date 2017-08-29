package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.moveasy.model.Estados;

public class EstadosDAO {
	
	private final Connection conexao;
	
	public EstadosDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public String cadastrar(String nome_estado, String uf) throws SQLException {
		
		String sql = "INSERT INTO ESTADOS ( COD_ESTADO, NOME_ESTADO, UF ) VALUES ( SEQ_EST.NEXTVAL, ?, ? )";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nome_estado);
		statement.setString(2, uf);

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Estado inserido com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
		
	}
	
	public String editar(Estados estado) throws SQLException {
		String sql = "UPDATE ESTADOS SET NOME_ESTADO = ?, UF = ? WHERE CODIGO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, estado.getNome_estado());
		statement.setString(2, estado.getUf());
		statement.setInt(3, estado.getCod_estado());

		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		
		if ( inserido == true ) {
			info = "Dados atualizados com sucesso";
		} else {
			info = "Ocorreu um erro inesperado.";
		}
		
		return info;
	}
	
	
	public String imprimirDados(Estados estado) throws SQLException {
		
		String dados = "";
		
		String sql = "SELECT  COD_ESTADO, NOME_ESTADO, UF ";
		sql += " FROM ESTADOS ";
		sql += " WHERE COD_ESTADO = ? ";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, estado.getCod_estado());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoEstado = rs.getInt(1);
					String nomeEstado = rs.getString(2);
					String ufEstado = rs.getString(3);
					
					dados = "Código: " + codigoEstado + "Nome: "+ nomeEstado + "UF: " + ufEstado;
				}
				
				
			}
		}

		return dados;
	}
	
	public List<Estados> listar() throws SQLException {
		List<Estados> lEstados = new ArrayList<>();

		String sql = "SELECT COD_ESTADO, NOME_ESTADO, UF FROM ESTADOS";
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int codigoEstado = rs.getInt(1);
					String nomeEstado = rs.getString(2);
					String uf = rs.getString(3);
					
					lEstados.add(new Estados(codigoEstado, nomeEstado, uf));
				}
			}
		}

		return lEstados;

	}
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = "DELETE ESTADOS WHERE COD_ESTADO = ?";

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
