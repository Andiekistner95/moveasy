package br.com.moveasy.service;

import br.com.moveasy.model.Endereco;

public class EnderecoService {

	public void cadastrar(String rua, String numero, String complemento, String bairro, Cidades cidades) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EnderecoDAO(conexao).cadastrar(rua, numero, complemento, bairro, cidades);
		}
	}
	
	public List<Empresas> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EnderecoDAO(conexao).lista();
		}
	}
	
	public void editar(Endereco endereco) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EnderecoDAO(conexao).editar(endereco.getCod_endereco());
		}
	}
	
	public void deletar(Endereco endereco) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EnderecoDAO(conexao).deletar(endereco.getCod_endereco());
		}
	}
	
}
