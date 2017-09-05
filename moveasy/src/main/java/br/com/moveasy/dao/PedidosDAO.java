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

public class PedidosDAO {

	private final Connection conexao;
	
	public PedidosDAO(Connection conexao) {
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
				+ " PEDIDOS.COD_PEDIDO pedido, "
				+ " PEDIDOS.EMPRESA cod_remetente, "
				+ " EMPRESAS.NOME_FANTASIA nome_empresa, "
				+ " ENDERREM.COD_ENDERECO cod_endereco_remetente, "
				+ " ENDERREM.RUA rua_remetente, "
				+ " ENDERREM.NUMERO numero_remetente, "
				+ " ENDERREM.COMPLEMENTO complemento_remetente, "
				+ " ENDERREM.BAIRRO bairro_remetente, "
				+ " ENDERREM.CIDADE cod_cidade_remetente, "
				+ " CIDADEREM.NOME_CIDADE nome_cidade_remetente, "
				+ " CIDADEREM.ESTADO cod_estado_remetente, "
				+ " ESTADOREM.NOME_ESTADO nome_estado_remetente, "
				+ " ESTADOREM.UF uf_estado_remetente, "
				+ " PEDIDOS.DESTINATARIO cod_destinatario, "
				+ " DESTINATARIOS.NOME_DEST nome_desstinatario, "
				+ " ENDERDEST.COD_ENDERECO cod_endereco_destinatario, "
				+ " ENDERDEST.RUA rua_destinatario, "
				+ " ENDERDEST.NUMERO numero_destinatario, "
				+ " ENDERDEST.COMPLEMENTO complemento_destinatario, "
				+ " ENDERDEST.BAIRRO bairro_destinatario, "
				+ " ENDERDEST.CIDADE cod_cidade_destinatario, "
				+ " CIDADEDEST.NOME_CIDADE nome_cidade_destinatario, "
				+ " CIDADEDEST.ESTADO cod_estado_destinatrio, "
				+ " ESTADODEST.NOME_ESTADO nome_estado_destinatario, "
				+ " ESTADODEST.UF uf_estado_destinatario,  "
				+ " PEDIDOS.ENTREGADORES cod_entregador, "
				+ " ENTREGADORES.NOME_ENT nome_entregador, "
				+ " ENDERENTREG.COD_ENDERECO cod_endereco_entregador, "
				+ " ENDERENTREG.RUA rua_entregador, "
				+ " ENDERENTREG.NUMERO numero_entregador, "
				+ " ENDERENTREG.COMPLEMENTO complemento_entregador, "
				+ " ENDERENTREG.BAIRRO bairro_entregador, "
				+ " ENDERENTREG.CIDADE cod_cidade_entregador, "
				+ " CIDADEENTREG.NOME_CIDADE nome_cidade_entregador, "
				+ " CIDADEENTREG.ESTADO cod_estado_entregador, "
				+ " ESTADOENTREG.NOME_ESTADO nome_estado_entregador, "
				+ " ESTADOENTREG.UF uf_estado_entregador,  "
				+ " PEDIDOS.DESCRICAO descricao_pedido, "
				+ " PEDIDOS.TIPO_SERVICO servico, "
				+ " SERVICO.DESCRICAO descricao_servico, "
				+ " PEDIDOS.TAXA_EXTRA, "
				+ " PEDIDOS.VALOR_TOTAL valor_total, "
				+ " TO_CHAR(PEDIDOS.DATA_PEDIDO, 'DD/MM/YYYY') emissao "
			+ " FROM  "
				+ " PEDIDOS "
			+ " INNER JOIN ENTREGADORES  ON "
				+ " ENTREGADORES.COD_ENT = PEDIDOS.ENTREGADORES "
			+ " INNER JOIN EMPRESAS ON "
				+ " EMPRESAS.COD_EMPRESA = PEDIDOS.EMPRESA "
			+ " INNER JOIN DESTINATARIOS ON "
				+ " DESTINATARIOS.COD_DEST = PEDIDOS.DESTINATARIO "
			+ " INNER JOIN ENDERECO ENDERDEST ON "
				+ " ENDERDEST.COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST "
			+ " INNER JOIN CIDADES CIDADEDEST ON "
				+ " CIDADEDEST.COD_CIDADE = ENDERDEST.CIDADE "
			+ " INNER JOIN ESTADOS ESTADODEST ON "
				+ " ESTADODEST.COD_ESTADO = CIDADEDEST.ESTADO "
			+ " INNER JOIN ENDERECO ENDERREM ON "
				+ " ENDERREM.COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA "
			+ " INNER JOIN CIDADES CIDADEREM ON "
				+ " CIDADEREM.COD_CIDADE = ENDERREM.CIDADE "
			+ " INNER JOIN ESTADOS ESTADOREM ON "
				+ " ESTADOREM.COD_ESTADO = CIDADEREM.ESTADO "
			+ " INNER JOIN ENDERECO ENDERENTREG ON "
				+ " ENDERENTREG.COD_ENDERECO = ENTREGADORES.ENDERECO_ENT "
			+ " INNER JOIN CIDADES CIDADEENTREG ON "
				+ " CIDADEENTREG.COD_CIDADE = ENDERENTREG.CIDADE "
			+ " INNER JOIN ESTADOS ESTADOENTREG ON "
				+ " ESTADOENTREG.COD_ESTADO = CIDADEREM.ESTADO "
			+ " INNER JOIN TIPO_SERVICO SERVICO ON "
				+ " SERVICO.COD_SERVICO = PEDIDOS.TIPO_SERVICO ";
											
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
