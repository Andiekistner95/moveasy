package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.moveasy.dao.PedidosDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Destinatarios;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Pedidos;
import br.com.moveasy.model.Tipo_servico;

public class PedidosService {
		
	public void cadastrar(int codEmpresa, int codDestinatario, int codTipoServico, int codEntregadores, Date data, double taxa_extra, double valor_total, String descricao) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new PedidosDAO(conexao).cadastrar(codEmpresa, codDestinatario, codTipoServico, codEntregadores, data, taxa_extra, valor_total, descricao);
			}
		}
		
		public List<Pedidos> listar() throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
		        return new PedidosDAO(conexao).listar();
			}
		}
		
		public Pedidos listar(int codigo) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
	            return new PedidosDAO(conexao).listar(codigo);
			}
		}
		
		public String imprimirDados(int codigo) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
	            return new PedidosDAO(conexao).imprimirDados(codigo);
			}
		}
		
		public void editar(int codPedido) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				//new PedidosDAO(conexao).editar(codPedido);
			}
		}
		
		public void deletar(int codPedido) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new PedidosDAO(conexao).deletar(codPedido);
			}
		}

				
		
	}

	

