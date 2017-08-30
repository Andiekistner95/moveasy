package br.com.moveasy.service;

import br.com.moveasy.model.Destinatarios;
import br.com.moveasy.model.Empresas;

public class EmpresasService {

	public void cadastrar(String nome_fantasia, String razao_social, String cnpj, Endereco endereco_empresa, String email, String telefone) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).cadastrar(nome_fantasia, razao_social, cnpj, endereco_empresa, email, telefone);
		}
	}
	
	public List<Empresas> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EmpresasDAO(conexao).lista();
		}
	}
	
	public void editar(Empresas empresas) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).editar(empresas.getCod_empresa());
		}
	}
	
	public void deletar(Empresas empresas) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).deletar(empresas.getCod_empresa());
		}
	}
	
}
