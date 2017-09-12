package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.EmpresasDAO;
import br.com.moveasy.dao.EntregadoresDAO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Destinatarios;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Usuarios;

public class EmpresasService {

	public void cadastrar(int cod_empresa, String nome_fantasia, String razao_social, String cnpj, int endereco_empresa,
			String email, String telefone, int usuario) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).cadastrar(cod_empresa, nome_fantasia, razao_social, cnpj, endereco_empresa,
					email, telefone, usuario);
		}
	}
	
	public void editar(Empresas empresas) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).editar(empresas.getCod_empresa());
		}
	}
	
	public void editar(int codEmpresa) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).editar(codEmpresa);
		}
	}
	
	public String imprimirDados(int codEmpresa) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new EmpresasDAO(conexao).imprimirDados(codEmpresa);
		}
	}
	
	public List<Empresas> listar() throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EmpresasDAO(conexao).listar();
		}
	}
	public Empresas listar(int codEmpresa) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
            return new EmpresasDAO(conexao).listar(codEmpresa);
		}
	}	
	public void deletar(Empresas empresas) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).deletar(empresas.getCod_empresa());
		}
	}
	public void deletar(int codEmpresa) throws SQLException{
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).deletar(codEmpresa);
		}
	}
}
