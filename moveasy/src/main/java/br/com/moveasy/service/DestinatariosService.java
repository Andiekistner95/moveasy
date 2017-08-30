package br.com.moveasy.service;

import br.com.moveasy.model.Destinatarios;

public class DestinatariosService {

	public void cadastrar(String nome_dest, Endereco endereco_dest) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).cadastrar(nome_dest, endereco_dest);
		}
	}
	
	public List<Destinatarios> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new DestinatariosDAO(conexao).lista();
		}
	}
	
	public void editar(Destinatarios destinatarios) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).editar(destinatarios.getCod_dest());
		}
	}
	
	public void deletar(Destinatarios destinatarios) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new DestinatariosDAO(conexao).deletar(destinatarios.getCod_dest());
		}
	}

	
	
}


