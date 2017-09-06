package br.com.moveasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Destinatarios;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Pedidos;
import br.com.moveasy.model.Tipo_servico;
import br.com.moveasy.model.Usuarios;

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
	
	public List<Pedidos> listar() throws SQLException {
		List<Pedidos> lPedidos = new ArrayList<>();
		
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
					
					// dados do pedido
					int codPedido = rs.getInt("pedido");
					String descricaoPedido = rs.getString("descricao_pedido");
					double valorTotalPedido = rs.getDouble("valor_total_pedido");
					double taxaExtraPedido = rs.getDouble("taxa_extra_pedido");
					Date emissaoPedido = rs.getDate("emissao_pedido");
					
					// dados do remetente / empresa
					int codRemetente = rs.getInt("cod_remetente");
					String nomeRemetente = rs.getString("nome_empresa_remetente");
					String razaoSocialRemetente = rs.getString("razao_social_remetente");
					String cnpjRemetente = rs.getString("cnpj_remetente");
					String telefoneRemetente = rs.getString("telefone_remetente");
					String emailRemetente = rs.getString("email_remetente");
					int caixaTermicaRemetente = rs.getInt("caixa_termica_remetente");
					int codUsuarioRemetente = rs.getInt("usuario_remetente");
					String loginRemetente = rs.getString("login_remetente");
					String senhaRemetente = rs.getString("senha_remetente");
					int statusRemetente = rs.getInt("status_usuario_remetente");
					
					// dados do endereco
					int codEnderecoRemetente = rs.getInt("cod_endereco_remetente");
					String ruaRemetente = rs.getString("rua_remetente");
					String numeroRemetente = rs.getString("numero_remetente");
					String complementoRemetente = rs.getString("complemento_remetente");
					String bairroRemetente = rs.getString("bairro_remetente");
					
					
					// dados da cidade
					int codCidadeRemetente = rs.getInt("cod_cidade_remetente");
					String nomeCidadeRemetente = rs.getString("nome_cidade_remetente");
					
					// dados do estado
					int codEstadoRemetente = rs.getInt("cod_estado_remetente");
					String nomeEstadoRemetente = rs.getString("nome_estado_remetente");
					String ufEstadoRemetente = rs.getString("uf_estado_remetente");

					// Criação do endereço do remetente
					Estados estadoRemetente = new Estados(codEstadoRemetente, nomeEstadoRemetente, ufEstadoRemetente);
					Cidades cidadeRemetente = new Cidades(codCidadeRemetente, nomeCidadeRemetente, estadoRemetente);
					Endereco enderecoRemetente = new Endereco(codEnderecoRemetente, ruaRemetente, numeroRemetente, complementoRemetente, bairroRemetente, cidadeRemetente);
					Usuarios usuarioRemetente = new Usuarios(codUsuarioRemetente, loginRemetente, senhaRemetente, statusRemetente);
					
					// criação do remetente / empresa
					Empresas remetente = new Empresas(codRemetente, nomeRemetente, razaoSocialRemetente, cnpjRemetente, enderecoRemetente, emailRemetente, telefoneRemetente, usuarioRemetente);
					
					// dados do entregador
					int codEntregador = rs.getInt("cod_entregador");
					String nomeEntregador = rs.getString("nome_entregador");
					String sobrenomeEntregador = rs.getString("sobrenome_entregador");
					String cpfEntregador = rs.getString("cpf_entregador");
					int codUsuarioEntregador = rs.getInt("login_entregador");
					String loginEntregador = rs.getString("login_remetente");
					String senhaEntregador = rs.getString("senha_remetente");
					int statusEntreagor = rs.getInt("status_usuario_remetente");
					

					// dados do endereco do entregador
					int codEnderecoEntregador = rs.getInt("cod_endereco_entregador");
					String ruaEntregador = rs.getString("rua_entregador");
					String numeroEntregador = rs.getString("numero_entregador");
					String complementoEntregador = rs.getString("complemento_entregador");
					String bairroEntregador = rs.getString("bairro_entregador");
					
					
					// dados da cidade do entregador
					int codCidadeEntregador = rs.getInt("cod_cidade_entregador");
					String nomeCidadeEntregador = rs.getString("nome_cidade_entregador");
					
					// dados do estado do entregador
					int codEstadoEntregador = rs.getInt("cod_estado_entregador");
					String nomeEstadoEntregador = rs.getString("nome_estado_entregador");
					String ufEstadoEntregador = rs.getString("uf_estado_entregador");

					
					// criação endereco do entregador
					Estados estadoEntregador = new Estados(codEstadoEntregador, nomeEstadoEntregador, ufEstadoEntregador);
					Cidades cidadeEntregador = new Cidades(codCidadeEntregador, nomeCidadeEntregador, estadoEntregador);
					Endereco enderecoEntregador = new Endereco(codEnderecoEntregador, ruaEntregador, numeroEntregador, complementoEntregador, bairroEntregador, cidadeRemetente);
					Usuarios usuarioEntregador = new Usuarios(codUsuarioEntregador, loginEntregador, senhaEntregador, statusEntreagor);
					
					// criação do entregador
					Entregadores entregador = new Entregadores(codEntregador, nomeEntregador, sobrenomeEntregador, cpfEntregador, enderecoEntregador, usuarioEntregador);
					
					// dados do destinatario
					int codDestinatario = rs.getInt("cod_destinatario");
					String nomeDestinatario = rs.getString("nome_desstinatario");
					
					// dados do endereco do destinatario
					int codEnderecoDestinatario = rs.getInt("cod_endereco_destinatario");
					String ruaDestinatario = rs.getString("rua_destinatario");
					String numeroDestinatario = rs.getString("numero_destinatario");
					String complementoDestinatario = rs.getString("complemento_destinatario");
					String bairroDestinatario = rs.getString("bairro_destinatario");
					
					// dados da cidade do destinatario
					int codCidadeDestinatario = rs.getInt("cod_cidade_destinatario");
					String nomeCidadeDestinatario = rs.getString("nome_cidade_destinatario");
					
					// dados do estado do destinatario
					int codEstadoDestinatario = rs.getInt("cod_estado_destinatrio");
					String nomeEstadoDestinatario = rs.getString("nome_estado_destinatario");
					String ufEstadoDestinatario = rs.getString("uf_estado_destinatario");
					
					// criação endereco do destinatario
					Estados estadoDestinatario = new Estados(codEstadoDestinatario, nomeEstadoDestinatario, ufEstadoDestinatario);
					Cidades cidadeDestinatario = new Cidades(codCidadeDestinatario, nomeCidadeDestinatario, estadoDestinatario);
					Endereco enderecoDestinatario = new Endereco(codEnderecoDestinatario, ruaDestinatario, numeroDestinatario, complementoDestinatario, bairroDestinatario, cidadeDestinatario);
					
					// criação do destinatário
					Destinatarios destinatario = new Destinatarios(codDestinatario, nomeDestinatario, enderecoDestinatario);
					
					// Dados do tipo de serviço
					int codTipoServico = rs.getInt("servico");
					String descricaoServico = rs.getString("descricao_servico");
					double taxaServico = rs.getDouble("taxa_servico");
					
					// criação do tipo de serviço
					Tipo_servico tipoServico = new Tipo_servico(codTipoServico, descricaoServico, taxaServico);
					
					Pedidos pedido = new Pedidos(codPedido, remetente, destinatario, tipoServico, entregador, emissaoPedido, taxaExtraPedido, valorTotalPedido, descricaoPedido);
					lPedidos.add(pedido);
				}
			}
		}

		return lPedidos;

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
