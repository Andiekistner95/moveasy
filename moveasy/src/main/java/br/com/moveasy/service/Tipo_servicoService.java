package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.CidadesDAO;
import br.com.moveasy.dao.Tipo_servicoDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Cidades;
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
	            return new Tipo_servicoDAO(conexao).listar();
			}
		}
		
		public void editar(Tipo_servico tipo_servico) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new Tipo_servicoDAO(conexao).editar(tipo_servico);
			}
		}
		
		public Tipo_servico listar(int codigo) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
	            return new Tipo_servicoDAO(conexao).listar(codigo);
			}
		}
		
		public void deletar( int codigo) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				new Tipo_servicoDAO(conexao).deletar(codigo);
			}
		}

		public String imprimirDados(Tipo_servico tipo_servico) throws SQLException{
			try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
				return new Tipo_servicoDAO(conexao).imprimirDados(tipo_servico);
			}
		}
		
	}

	

