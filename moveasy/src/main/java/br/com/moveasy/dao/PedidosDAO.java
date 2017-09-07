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
	
	public String cadastrar(int codEmpresa, int codDestinatario, int codTipoServico, int codEntregadores, Date data, double taxa_extra, double valor_total, String descricao) throws SQLException {
		
		String sql = " INSERT INTO PEDIDOS"
						+ " ( COD_PEDIDO, EMPRESA, DESTINATARIO, TIPO_SERVICO, ENTREGADORES, DATA_PEDIDO, TAXA_EXTRA, VALOR_TOTAL, DESCRICAO ) " + 
						" VALUES "
						+ " ( SEQ_PEDIDOS.NEXTVAL , ?, ?, ?, ?, ? , ?, ? , ? ) ";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
 
		statement.setInt(1, codEmpresa);
		statement.setInt(2, codDestinatario);
		statement.setInt(3, codTipoServico);
		statement.setInt(4, codEntregadores);
		statement.setDate(5, new java.sql.Date(data.getTime()) );
		statement.setDouble(6, taxa_extra);
		statement.setDouble(7, valor_total);
		statement.setString(8, descricao);
		
		boolean inserido = statement.executeUpdate() > 0; 
		String info;
		if ( inserido == true ) {
			info = "Pedido inserido com sucesso";
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
	
	
	// falta terminar a string de saída
	public String imprimirDados(int codPedido) throws SQLException {
		String dados = "";
		
		String sql;
		sql = "    \r\n" + 
				"SELECT \r\n" + 
				"    PEDIDOS.COD_PEDIDO pedido, \r\n" + 
				"    \r\n" + 
				"    PEDIDOS.EMPRESA cod_remetente, \r\n" + 
				"    EMPRESAS.NOME_FANTASIA nome_empresa_remetente, \r\n" + 
				"    EMPRESAS.RAZAO_SOCIAL razao_social_remetente,\r\n" + 
				"    EMPRESAS.CNPJ cnpj_remetente,\r\n" + 
				"    EMPRESAS.TELEFONE telefone_remetente,\r\n" + 
				"    EMPRESAS.EMAIL email_remetente,\r\n" + 
				"    EMPRESAS.CAIXA_TERMICA caixa_termica_remetente,\r\n" + 
				"    EMPRESAS.USUARIO usuario_remetente,\r\n" + 
				"    USUARIOREM.LOGIN login_remetente,\r\n" + 
				"    USUARIOREM.SENHA senha_remetente,\r\n" + 
				"    USUARIOREM.STATUS status_usuario_remetente,\r\n" + 
				"    \r\n" + 
				"    -- Dados do remetente / empresa\r\n" + 
				"    ENDERREM.COD_ENDERECO cod_endereco_remetente,\r\n" + 
				"    ENDERREM.RUA rua_remetente,\r\n" + 
				"    ENDERREM.NUMERO numero_remetente,\r\n" + 
				"    ENDERREM.COMPLEMENTO complemento_remetente,\r\n" + 
				"    ENDERREM.BAIRRO bairro_remetente,\r\n" + 
				"    ENDERREM.CIDADE cod_cidade_remetente,\r\n" + 
				"    CIDADEREM.NOME_CIDADE nome_cidade_remetente, \r\n" + 
				"    CIDADEREM.ESTADO cod_estado_remetente,\r\n" + 
				"    ESTADOREM.NOME_ESTADO nome_estado_remetente,\r\n" + 
				"    ESTADOREM.UF uf_estado_remetente, \r\n" + 
				"    \r\n" + 
				"    -- Dados do destinatario\r\n" + 
				"    PEDIDOS.DESTINATARIO cod_destinatario,\r\n" + 
				"    DESTINATARIOS.NOME_DEST nome_desstinatario,\r\n" + 
				"    ENDERDEST.COD_ENDERECO cod_endereco_destinatario,\r\n" + 
				"    ENDERDEST.RUA rua_destinatario,\r\n" + 
				"    ENDERDEST.NUMERO numero_destinatario,\r\n" + 
				"    ENDERDEST.COMPLEMENTO complemento_destinatario,\r\n" + 
				"    ENDERDEST.BAIRRO bairro_destinatario,\r\n" + 
				"    ENDERDEST.CIDADE cod_cidade_destinatario,\r\n" + 
				"    CIDADEDEST.NOME_CIDADE nome_cidade_destinatario, \r\n" + 
				"    CIDADEDEST.ESTADO cod_estado_destinatrio,\r\n" + 
				"    ESTADODEST.NOME_ESTADO nome_estado_destinatario,\r\n" + 
				"    ESTADODEST.UF uf_estado_destinatario, \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    -- dados dos entregaodres\r\n" + 
				"    PEDIDOS.ENTREGADORES cod_entregador,\r\n" + 
				"    ENTREGADORES.NOME_ENT nome_entregador,\r\n" + 
				"    ENTREGADORES.SOBRENOME_ENT sobrenome_entregador,\r\n" + 
				"    ENTREGADORES.CPF_ENT cpf_entregador,\r\n" + 
				"    ENTREGADORES.USUARIO usuario_entregador,\r\n" + 
				"    USUARIOENTREG.LOGIN login_entregador,\r\n" + 
				"    USUARIOENTREG.SENHA senha_entregador,\r\n" + 
				"    USUARIOENTREG.STATUS status_usuario_entregador,\r\n" + 
				"    \r\n" + 
				"    ENDERENTREG.COD_ENDERECO cod_endereco_entregador,\r\n" + 
				"    ENDERENTREG.RUA rua_entregador,\r\n" + 
				"    ENDERENTREG.NUMERO numero_entregador,\r\n" + 
				"    ENDERENTREG.COMPLEMENTO complemento_entregador,\r\n" + 
				"    ENDERENTREG.BAIRRO bairro_entregador,\r\n" + 
				"    ENDERENTREG.CIDADE cod_cidade_entregador,\r\n" + 
				"    CIDADEENTREG.NOME_CIDADE nome_cidade_entregador, \r\n" + 
				"    CIDADEENTREG.ESTADO cod_estado_entregador,\r\n" + 
				"    ESTADOENTREG.NOME_ESTADO nome_estado_entregador,\r\n" + 
				"    ESTADOENTREG.UF uf_estado_entregador, \r\n" + 
				"    \r\n" + 
				"    PEDIDOS.DESCRICAO descricao_pedido,\r\n" + 
				"    PEDIDOS.TIPO_SERVICO servico,\r\n" + 
				"    SERVICO.DESCRICAO descricao_servico,\r\n" + 
				"    SERVICO.TAXA taxa_servico, \r\n" + 
				"    PEDIDOS.TAXA_EXTRA taxa_extra_pedido,\r\n" + 
				"    PEDIDOS.VALOR_TOTAL valor_total_pedido,\r\n" + 
				"    PEDIDOS.DATA_PEDIDO emissao\r\n" + 
				"    \r\n" + 
				"FROM \r\n" + 
				"    PEDIDOS \r\n" + 
				"    \r\n" + 
				"INNER JOIN ENTREGADORES  ON\r\n" + 
				"    ENTREGADORES.COD_ENT = PEDIDOS.ENTREGADORES\r\n" + 
				"    \r\n" + 
				"INNER JOIN EMPRESAS ON \r\n" + 
				"    EMPRESAS.COD_EMPRESA = PEDIDOS.EMPRESA\r\n" + 
				"    \r\n" + 
				"INNER JOIN DESTINATARIOS ON\r\n" + 
				"    DESTINATARIOS.COD_DEST = PEDIDOS.DESTINATARIO\r\n" + 
				"    \r\n" + 
				"INNER JOIN ENDERECO ENDERDEST ON\r\n" + 
				"    ENDERDEST.COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEDEST ON\r\n" + 
				"    CIDADEDEST.COD_CIDADE = ENDERDEST.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADODEST ON \r\n" + 
				"    ESTADODEST.COD_ESTADO = CIDADEDEST.ESTADO \r\n" + 
				"        \r\n" + 
				"INNER JOIN ENDERECO ENDERREM ON\r\n" + 
				"    ENDERREM.COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEREM ON\r\n" + 
				"    CIDADEREM.COD_CIDADE = ENDERREM.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADOREM ON \r\n" + 
				"    ESTADOREM.COD_ESTADO = CIDADEREM.ESTADO \r\n" + 
				"    \r\n" + 
				"INNER JOIN ENDERECO ENDERENTREG ON\r\n" + 
				"    ENDERENTREG.COD_ENDERECO = ENTREGADORES.ENDERECO_ENT\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEENTREG ON\r\n" + 
				"    CIDADEENTREG.COD_CIDADE = ENDERENTREG.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADOENTREG ON \r\n" + 
				"    ESTADOENTREG.COD_ESTADO = CIDADEREM.ESTADO     \r\n" + 
				"        \r\n" + 
				"INNER JOIN TIPO_SERVICO SERVICO ON\r\n" + 
				"    SERVICO.COD_SERVICO = PEDIDOS.TIPO_SERVICO   \r\n" + 
				"    \r\n" + 
				"INNER JOIN USUARIOS USUARIOREM ON\r\n" + 
				"    USUARIOREM.COD_USUARIO = EMPRESAS.USUARIO\r\n" + 
				"    \r\n" + 
				"INNER JOIN USUARIOS USUARIOENTREG ON\r\n" + 
				"    USUARIOENTREG.COD_USUARIO = ENTREGADORES.USUARIO\r\n" + 
				"WHERE \r\n"
				+ " PEDIDOS.COD_PEDIDO = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codPedido);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {

					// dados do pedido
					int codPedidos = rs.getInt("pedido");
					String descricaoPedido = rs.getString("descricao_pedido");
					double valorTotalPedido = rs.getDouble("valor_total_pedido");
					double taxaExtraPedido = rs.getDouble("taxa_extra_pedido");
					Date emissaoPedido = rs.getDate("emissao");
					
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
					int codUsuarioEntregador = rs.getInt("usuario_entregador");
					String loginEntregador = rs.getString("login_entregador");
					String senhaEntregador = rs.getString("senha_entregador");
					int statusEntreagor = rs.getInt("status_usuario_entregador");
					

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
					
					
					dados = "";
					
				}
							
			}
		}

		return dados;
	}
	
	public List<Pedidos> listar() throws SQLException {
		List<Pedidos> lPedidos = new ArrayList<>();
		
		String sql;
		sql = "    \r\n" + 
				"SELECT \r\n" + 
				"    PEDIDOS.COD_PEDIDO pedido, \r\n" + 
				"    \r\n" + 
				"    PEDIDOS.EMPRESA cod_remetente, \r\n" + 
				"    EMPRESAS.NOME_FANTASIA nome_empresa_remetente, \r\n" + 
				"    EMPRESAS.RAZAO_SOCIAL razao_social_remetente,\r\n" + 
				"    EMPRESAS.CNPJ cnpj_remetente,\r\n" + 
				"    EMPRESAS.TELEFONE telefone_remetente,\r\n" + 
				"    EMPRESAS.EMAIL email_remetente,\r\n" + 
				"    EMPRESAS.CAIXA_TERMICA caixa_termica_remetente,\r\n" + 
				"    EMPRESAS.USUARIO usuario_remetente,\r\n" + 
				"    USUARIOREM.LOGIN login_remetente,\r\n" + 
				"    USUARIOREM.SENHA senha_remetente,\r\n" + 
				"    USUARIOREM.STATUS status_usuario_remetente,\r\n" + 
				"    \r\n" + 
				"    -- Dados do remetente / empresa\r\n" + 
				"    ENDERREM.COD_ENDERECO cod_endereco_remetente,\r\n" + 
				"    ENDERREM.RUA rua_remetente,\r\n" + 
				"    ENDERREM.NUMERO numero_remetente,\r\n" + 
				"    ENDERREM.COMPLEMENTO complemento_remetente,\r\n" + 
				"    ENDERREM.BAIRRO bairro_remetente,\r\n" + 
				"    ENDERREM.CIDADE cod_cidade_remetente,\r\n" + 
				"    CIDADEREM.NOME_CIDADE nome_cidade_remetente, \r\n" + 
				"    CIDADEREM.ESTADO cod_estado_remetente,\r\n" + 
				"    ESTADOREM.NOME_ESTADO nome_estado_remetente,\r\n" + 
				"    ESTADOREM.UF uf_estado_remetente, \r\n" + 
				"    \r\n" + 
				"    -- Dados do destinatario\r\n" + 
				"    PEDIDOS.DESTINATARIO cod_destinatario,\r\n" + 
				"    DESTINATARIOS.NOME_DEST nome_desstinatario,\r\n" + 
				"    ENDERDEST.COD_ENDERECO cod_endereco_destinatario,\r\n" + 
				"    ENDERDEST.RUA rua_destinatario,\r\n" + 
				"    ENDERDEST.NUMERO numero_destinatario,\r\n" + 
				"    ENDERDEST.COMPLEMENTO complemento_destinatario,\r\n" + 
				"    ENDERDEST.BAIRRO bairro_destinatario,\r\n" + 
				"    ENDERDEST.CIDADE cod_cidade_destinatario,\r\n" + 
				"    CIDADEDEST.NOME_CIDADE nome_cidade_destinatario, \r\n" + 
				"    CIDADEDEST.ESTADO cod_estado_destinatrio,\r\n" + 
				"    ESTADODEST.NOME_ESTADO nome_estado_destinatario,\r\n" + 
				"    ESTADODEST.UF uf_estado_destinatario, \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    -- dados dos entregaodres\r\n" + 
				"    PEDIDOS.ENTREGADORES cod_entregador,\r\n" + 
				"    ENTREGADORES.NOME_ENT nome_entregador,\r\n" + 
				"    ENTREGADORES.SOBRENOME_ENT sobrenome_entregador,\r\n" + 
				"    ENTREGADORES.CPF_ENT cpf_entregador,\r\n" + 
				"    ENTREGADORES.USUARIO usuario_entregador,\r\n" + 
				"    USUARIOENTREG.LOGIN login_entregador,\r\n" + 
				"    USUARIOENTREG.SENHA senha_entregador,\r\n" + 
				"    USUARIOENTREG.STATUS status_usuario_entregador,\r\n" + 
				"    \r\n" + 
				"    ENDERENTREG.COD_ENDERECO cod_endereco_entregador,\r\n" + 
				"    ENDERENTREG.RUA rua_entregador,\r\n" + 
				"    ENDERENTREG.NUMERO numero_entregador,\r\n" + 
				"    ENDERENTREG.COMPLEMENTO complemento_entregador,\r\n" + 
				"    ENDERENTREG.BAIRRO bairro_entregador,\r\n" + 
				"    ENDERENTREG.CIDADE cod_cidade_entregador,\r\n" + 
				"    CIDADEENTREG.NOME_CIDADE nome_cidade_entregador, \r\n" + 
				"    CIDADEENTREG.ESTADO cod_estado_entregador,\r\n" + 
				"    ESTADOENTREG.NOME_ESTADO nome_estado_entregador,\r\n" + 
				"    ESTADOENTREG.UF uf_estado_entregador, \r\n" + 
				"    \r\n" + 
				"    PEDIDOS.DESCRICAO descricao_pedido,\r\n" + 
				"    PEDIDOS.TIPO_SERVICO servico,\r\n" + 
				"    SERVICO.DESCRICAO descricao_servico,\r\n" + 
				"    SERVICO.TAXA taxa_servico, \r\n" + 
				"    PEDIDOS.TAXA_EXTRA taxa_extra_pedido,\r\n" + 
				"    PEDIDOS.VALOR_TOTAL valor_total_pedido,\r\n" + 
				"    PEDIDOS.DATA_PEDIDO emissao\r\n" + 
				"    \r\n" + 
				"FROM \r\n" + 
				"    PEDIDOS \r\n" + 
				"    \r\n" + 
				"INNER JOIN ENTREGADORES  ON\r\n" + 
				"    ENTREGADORES.COD_ENT = PEDIDOS.ENTREGADORES\r\n" + 
				"    \r\n" + 
				"INNER JOIN EMPRESAS ON \r\n" + 
				"    EMPRESAS.COD_EMPRESA = PEDIDOS.EMPRESA\r\n" + 
				"    \r\n" + 
				"INNER JOIN DESTINATARIOS ON\r\n" + 
				"    DESTINATARIOS.COD_DEST = PEDIDOS.DESTINATARIO\r\n" + 
				"    \r\n" + 
				"INNER JOIN ENDERECO ENDERDEST ON\r\n" + 
				"    ENDERDEST.COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEDEST ON\r\n" + 
				"    CIDADEDEST.COD_CIDADE = ENDERDEST.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADODEST ON \r\n" + 
				"    ESTADODEST.COD_ESTADO = CIDADEDEST.ESTADO \r\n" + 
				"        \r\n" + 
				"INNER JOIN ENDERECO ENDERREM ON\r\n" + 
				"    ENDERREM.COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEREM ON\r\n" + 
				"    CIDADEREM.COD_CIDADE = ENDERREM.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADOREM ON \r\n" + 
				"    ESTADOREM.COD_ESTADO = CIDADEREM.ESTADO \r\n" + 
				"    \r\n" + 
				"INNER JOIN ENDERECO ENDERENTREG ON\r\n" + 
				"    ENDERENTREG.COD_ENDERECO = ENTREGADORES.ENDERECO_ENT\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEENTREG ON\r\n" + 
				"    CIDADEENTREG.COD_CIDADE = ENDERENTREG.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADOENTREG ON \r\n" + 
				"    ESTADOENTREG.COD_ESTADO = CIDADEREM.ESTADO     \r\n" + 
				"        \r\n" + 
				"INNER JOIN TIPO_SERVICO SERVICO ON\r\n" + 
				"    SERVICO.COD_SERVICO = PEDIDOS.TIPO_SERVICO   \r\n" + 
				"    \r\n" + 
				"INNER JOIN USUARIOS USUARIOREM ON\r\n" + 
				"    USUARIOREM.COD_USUARIO = EMPRESAS.USUARIO\r\n" + 
				"    \r\n" + 
				"INNER JOIN USUARIOS USUARIOENTREG ON\r\n" + 
				"    USUARIOENTREG.COD_USUARIO = ENTREGADORES.USUARIO\r\n" + 
				"    ";
											
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
					Date emissaoPedido = rs.getDate("emissao");
					
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
					int codUsuarioEntregador = rs.getInt("usuario_entregador");
					String loginEntregador = rs.getString("login_entregador");
					String senhaEntregador = rs.getString("senha_entregador");
					int statusEntreagor = rs.getInt("status_usuario_entregador");
					

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
	
	public Pedidos listar(int codigo) throws SQLException {
		Pedidos pedido = null;
		
		String sql;
		sql = "    \r\n" + 
				"SELECT \r\n" + 
				"    PEDIDOS.COD_PEDIDO pedido, \r\n" + 
				"    \r\n" + 
				"    PEDIDOS.EMPRESA cod_remetente, \r\n" + 
				"    EMPRESAS.NOME_FANTASIA nome_empresa_remetente, \r\n" + 
				"    EMPRESAS.RAZAO_SOCIAL razao_social_remetente,\r\n" + 
				"    EMPRESAS.CNPJ cnpj_remetente,\r\n" + 
				"    EMPRESAS.TELEFONE telefone_remetente,\r\n" + 
				"    EMPRESAS.EMAIL email_remetente,\r\n" + 
				"    EMPRESAS.CAIXA_TERMICA caixa_termica_remetente,\r\n" + 
				"    EMPRESAS.USUARIO usuario_remetente,\r\n" + 
				"    USUARIOREM.LOGIN login_remetente,\r\n" + 
				"    USUARIOREM.SENHA senha_remetente,\r\n" + 
				"    USUARIOREM.STATUS status_usuario_remetente,\r\n" + 
				"    \r\n" + 
				"    -- Dados do remetente / empresa\r\n" + 
				"    ENDERREM.COD_ENDERECO cod_endereco_remetente,\r\n" + 
				"    ENDERREM.RUA rua_remetente,\r\n" + 
				"    ENDERREM.NUMERO numero_remetente,\r\n" + 
				"    ENDERREM.COMPLEMENTO complemento_remetente,\r\n" + 
				"    ENDERREM.BAIRRO bairro_remetente,\r\n" + 
				"    ENDERREM.CIDADE cod_cidade_remetente,\r\n" + 
				"    CIDADEREM.NOME_CIDADE nome_cidade_remetente, \r\n" + 
				"    CIDADEREM.ESTADO cod_estado_remetente,\r\n" + 
				"    ESTADOREM.NOME_ESTADO nome_estado_remetente,\r\n" + 
				"    ESTADOREM.UF uf_estado_remetente, \r\n" + 
				"    \r\n" + 
				"    -- Dados do destinatario\r\n" + 
				"    PEDIDOS.DESTINATARIO cod_destinatario,\r\n" + 
				"    DESTINATARIOS.NOME_DEST nome_desstinatario,\r\n" + 
				"    ENDERDEST.COD_ENDERECO cod_endereco_destinatario,\r\n" + 
				"    ENDERDEST.RUA rua_destinatario,\r\n" + 
				"    ENDERDEST.NUMERO numero_destinatario,\r\n" + 
				"    ENDERDEST.COMPLEMENTO complemento_destinatario,\r\n" + 
				"    ENDERDEST.BAIRRO bairro_destinatario,\r\n" + 
				"    ENDERDEST.CIDADE cod_cidade_destinatario,\r\n" + 
				"    CIDADEDEST.NOME_CIDADE nome_cidade_destinatario, \r\n" + 
				"    CIDADEDEST.ESTADO cod_estado_destinatrio,\r\n" + 
				"    ESTADODEST.NOME_ESTADO nome_estado_destinatario,\r\n" + 
				"    ESTADODEST.UF uf_estado_destinatario, \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    -- dados dos entregaodres\r\n" + 
				"    PEDIDOS.ENTREGADORES cod_entregador,\r\n" + 
				"    ENTREGADORES.NOME_ENT nome_entregador,\r\n" + 
				"    ENTREGADORES.SOBRENOME_ENT sobrenome_entregador,\r\n" + 
				"    ENTREGADORES.CPF_ENT cpf_entregador,\r\n" + 
				"    ENTREGADORES.USUARIO usuario_entregador,\r\n" + 
				"    USUARIOENTREG.LOGIN login_entregador,\r\n" + 
				"    USUARIOENTREG.SENHA senha_entregador,\r\n" + 
				"    USUARIOENTREG.STATUS status_usuario_entregador,\r\n" + 
				"    \r\n" + 
				"    ENDERENTREG.COD_ENDERECO cod_endereco_entregador,\r\n" + 
				"    ENDERENTREG.RUA rua_entregador,\r\n" + 
				"    ENDERENTREG.NUMERO numero_entregador,\r\n" + 
				"    ENDERENTREG.COMPLEMENTO complemento_entregador,\r\n" + 
				"    ENDERENTREG.BAIRRO bairro_entregador,\r\n" + 
				"    ENDERENTREG.CIDADE cod_cidade_entregador,\r\n" + 
				"    CIDADEENTREG.NOME_CIDADE nome_cidade_entregador, \r\n" + 
				"    CIDADEENTREG.ESTADO cod_estado_entregador,\r\n" + 
				"    ESTADOENTREG.NOME_ESTADO nome_estado_entregador,\r\n" + 
				"    ESTADOENTREG.UF uf_estado_entregador, \r\n" + 
				"    \r\n" + 
				"    PEDIDOS.DESCRICAO descricao_pedido,\r\n" + 
				"    PEDIDOS.TIPO_SERVICO servico,\r\n" + 
				"    SERVICO.DESCRICAO descricao_servico,\r\n" + 
				"    SERVICO.TAXA taxa_servico, \r\n" + 
				"    PEDIDOS.TAXA_EXTRA taxa_extra_pedido,\r\n" + 
				"    PEDIDOS.VALOR_TOTAL valor_total_pedido,\r\n" + 
				"    PEDIDOS.DATA_PEDIDO emissao\r\n" + 
				"    \r\n" + 
				"FROM \r\n" + 
				"    PEDIDOS \r\n" + 
				"    \r\n" + 
				"INNER JOIN ENTREGADORES  ON\r\n" + 
				"    ENTREGADORES.COD_ENT = PEDIDOS.ENTREGADORES\r\n" + 
				"    \r\n" + 
				"INNER JOIN EMPRESAS ON \r\n" + 
				"    EMPRESAS.COD_EMPRESA = PEDIDOS.EMPRESA\r\n" + 
				"    \r\n" + 
				"INNER JOIN DESTINATARIOS ON\r\n" + 
				"    DESTINATARIOS.COD_DEST = PEDIDOS.DESTINATARIO\r\n" + 
				"    \r\n" + 
				"INNER JOIN ENDERECO ENDERDEST ON\r\n" + 
				"    ENDERDEST.COD_ENDERECO = DESTINATARIOS.ENDERECO_DEST\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEDEST ON\r\n" + 
				"    CIDADEDEST.COD_CIDADE = ENDERDEST.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADODEST ON \r\n" + 
				"    ESTADODEST.COD_ESTADO = CIDADEDEST.ESTADO \r\n" + 
				"        \r\n" + 
				"INNER JOIN ENDERECO ENDERREM ON\r\n" + 
				"    ENDERREM.COD_ENDERECO = EMPRESAS.ENDERECO_EMPRESA\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEREM ON\r\n" + 
				"    CIDADEREM.COD_CIDADE = ENDERREM.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADOREM ON \r\n" + 
				"    ESTADOREM.COD_ESTADO = CIDADEREM.ESTADO \r\n" + 
				"    \r\n" + 
				"INNER JOIN ENDERECO ENDERENTREG ON\r\n" + 
				"    ENDERENTREG.COD_ENDERECO = ENTREGADORES.ENDERECO_ENT\r\n" + 
				"    \r\n" + 
				"INNER JOIN CIDADES CIDADEENTREG ON\r\n" + 
				"    CIDADEENTREG.COD_CIDADE = ENDERENTREG.CIDADE \r\n" + 
				"    \r\n" + 
				"INNER JOIN ESTADOS ESTADOENTREG ON \r\n" + 
				"    ESTADOENTREG.COD_ESTADO = CIDADEREM.ESTADO     \r\n" + 
				"        \r\n" + 
				"INNER JOIN TIPO_SERVICO SERVICO ON\r\n" + 
				"    SERVICO.COD_SERVICO = PEDIDOS.TIPO_SERVICO   \r\n" + 
				"    \r\n" + 
				"INNER JOIN USUARIOS USUARIOREM ON\r\n" + 
				"    USUARIOREM.COD_USUARIO = EMPRESAS.USUARIO\r\n" + 
				"    \r\n" + 
				"INNER JOIN USUARIOS USUARIOENTREG ON\r\n" + 
				"    USUARIOENTREG.COD_USUARIO = ENTREGADORES.USUARIO\r\n" + 
				"WHERE \r\n"
				+ " PEDIDOS.COD_PEDIDO = ? ";
								
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {

					// dados do pedido
					int codPedido = rs.getInt("pedido");
					String descricaoPedido = rs.getString("descricao_pedido");
					double valorTotalPedido = rs.getDouble("valor_total_pedido");
					double taxaExtraPedido = rs.getDouble("taxa_extra_pedido");
					Date emissaoPedido = rs.getDate("emissao");
					
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
					int codUsuarioEntregador = rs.getInt("usuario_entregador");
					String loginEntregador = rs.getString("login_entregador");
					String senhaEntregador = rs.getString("senha_entregador");
					int statusEntreagor = rs.getInt("status_usuario_entregador");
					

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
					
					pedido = new Pedidos(codPedido, remetente, destinatario, tipoServico, entregador, emissaoPedido, taxaExtraPedido, valorTotalPedido, descricaoPedido);

				}
			}
		}

		return pedido;

	}
	
	
	public String deletar(Integer codigo) throws SQLException {
		String sql = " DELETE PEDIDOS WHERE COD_PEDIDO = ? ";

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
