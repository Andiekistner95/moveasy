package br.com.moveasy.service;

import br.com.moveasy.model.Pedidos;
import br.com.moveasy.model.Tipo_servico;

public class Tipo_servicoService {

	
		public void cadastrar(String descricao, double taxa) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new Tipo_servicoDAO(conexao).cadastrar(descricao, taxa);
			}
		}
		
		public List<Tipo_servico> listar() throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
	            return new Tipo_servicoDAO(conexao).lista();
			}
		}
		
		public void editar(Tipo_servico tipo_servico) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new Tipo_servicoDAO(conexao).editar(tipo_servico.getCod_servico());
			}
		}
		
		public void deletar(Tipo_servico tipo_servico) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new Tipo_servicoDAO(conexao).deletar(tipo_servico.getCod_servico());
			}
		}

		
		
	}

	

