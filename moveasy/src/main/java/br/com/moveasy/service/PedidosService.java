package br.com.moveasy.service;

import br.com.moveasy.model.Pedidos;

public class PedidosService {
		
	public void cadastrar(Empresas empresa, Destinatario destinatario, Tipo_servico tipo_servico, Entregadores entregadores, Date data, double taxa_extra, double valor_total, String descricao) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new PedidosDAO(conexao).cadastrar(empresa, destinatario, tipo_servico, entregadores, data, taxa_extra, valor_total, descricao);
			}
		}
		
		public List<Pedidos> listar() throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
	            return new PedidosDAO(conexao).lista();
			}
		}
		
		public void editar(Pedidos pedidos) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new PedidosDAO(conexao).editar(pedidos.getCod_pedido());
			}
		}
		
		public void deletar(Pedidos pedidos) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new PedidosDAO(conexao).deletar(pedidos.getCod_pedido());
			}
		}

		
		
	}

	

