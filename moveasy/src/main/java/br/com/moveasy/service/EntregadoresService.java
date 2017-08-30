package br.com.moveasy.service;

import br.com.moveasy.model.Entregadores;

public class EntregadoresService {

	public void cadastrar(String nome_ent, String sobrenome_ent, String cpf_ent, Endereco endereco_ent) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).cadastrar(nome_ent, sobrenome_ent, cpf_ent, endereco_ent);
		}
	}
	
	public List<Entregadores> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EntregadoresDAO(conexao).lista();
		}
	}
	
	public void editar(Entregadores entregadores) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EntregadoresDAO(conexao).editar(entregadores.getCod_ent());
		}
	}
	
	public void deletar(Entregadores entregadores) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EntregadoresDAO(conexao).deletar(entregadores.getCod_ent());
		}
	}
	
}
