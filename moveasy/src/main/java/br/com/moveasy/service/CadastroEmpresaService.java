package br.com.moveasy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.moveasy.dao.CidadesDAO;
import br.com.moveasy.dao.EmpresasDAO;
import br.com.moveasy.dao.EnderecoDAO;
import br.com.moveasy.dao.UsuariosDAO;
import br.com.moveasy.dto.CadastroEmpresaDTO;
import br.com.moveasy.jdbc.oracle.ConnectionPoolOracle;
import br.com.moveasy.model.Cidades;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Estados;
import br.com.moveasy.model.Usuarios;

public class CadastroEmpresaService {
	
	public void cadastrar(CadastroEmpresaDTO cadastroEmpresaDTO) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			
			//Estados
			Estados estados = new Estados();
			estados.setNome_estado(cadastroEmpresaDTO.getNome_estado());
			
			//Cidades
			Cidades cidades = new Cidades();
			cidades.setNome_cidade(cadastroEmpresaDTO.getNome_cidade());
			cidades.setEstado(estados);
			new CidadesDAO(conexao).cadastrar(nome_cidade, estado);
			
			//Endereço
			Endereco endereco = new Endereco();			
			endereco.setRua(cadastroEmpresaDTO.getRua());
			endereco.setNumero(cadastroEmpresaDTO.getNumero());
			endereco.setBairro(cadastroEmpresaDTO.getBairro());
			endereco.setComplemento(cadastroEmpresaDTO.getComplemento());
			endereco.setCidade(cidades);
			new EnderecoDAO(conexao).cadastrar(rua, numero, complemento, bairro, cidade);			
			
			//Usuarios
			Usuarios usuarios = new Usuarios();
			usuarios.setLogin(cadastroEmpresaDTO.getLogin());
			usuarios.setSenha(cadastroEmpresaDTO.getSenha());
			new UsuariosDAO(conexao).cadastrar( cadastroEmpresaDTO.getLogin(), cadastroEmpresaDTO.getSenha(), 0);
			
			//Empresas
			Empresas empresas = new Empresas();
			empresas.setNome_fantasia(cadastroEmpresaDTO.getNome_fantasia());
			empresas.setRazao_social(cadastroEmpresaDTO.getRazao_social());
			empresas.setCnpj(cadastroEmpresaDTO.getCnpj());
			empresas.setEmail(cadastroEmpresaDTO.getEmail());
			empresas.setTelefone(cadastroEmpresaDTO.getTelefone());
			empresas.setEndereco_empresa(endereco);
			new EmpresasDAO(conexao).cadastrar(cod_empresa, nome_fantasia, razao_social, cnpj, endereco_empresa, email, telefone, usuario);
							
			
		}
	}

	public void editar(Empresas empresas) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).editar(empresas.getCod_empresa());
		}
	}

	public void editar(int codEmpresa) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).editar(codEmpresa);
		}
	}

	public String imprimirDados(int codEmpresa) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new EmpresasDAO(conexao).imprimirDados(codEmpresa);
		}
	}

	public List<Empresas> listar() throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new EmpresasDAO(conexao).listar();
		}
	}

	public Empresas listar(int codEmpresa) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			return new EmpresasDAO(conexao).listar(codEmpresa);
		}
	}

	public void deletar(Empresas empresas) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).deletar(empresas.getCod_empresa());
		}
	}

	public void deletar(int codEmpresa) throws SQLException {
		try (Connection conexao = new ConnectionPoolOracle().getConnection()) {
			new EmpresasDAO(conexao).deletar(codEmpresa);
		}
	}
}
