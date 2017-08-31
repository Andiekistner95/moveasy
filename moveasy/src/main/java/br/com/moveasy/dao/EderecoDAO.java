package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.moveasy.service.Cidades;

public class EderecoDAO {

private final Connection conexao;
	
	public EnderecoDAO(Connection conexao) {
		this.conexao = conexao;
		public String cadastrar(String nome_estado, String uf) throws SQLException {
			
			String sql = "INSERT INTO ENDERECO ( RUA, NUMERO, COMPLEMENTO, BAIRRO, CIDADES ) VALUES ( SEQ_END.NEXTVAL, ?, ?, ? )";
			
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
}
