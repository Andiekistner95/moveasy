package br.com.moveasy.dto;

import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Usuarios;

public class CadastroEmpresaDTO {

	int cod_empresa;
	String nome_fantasia;
	String razao_social;
	String cnpj;
	Endereco endereco_empresa;
	String email;
	String telefone;
	String login;
	String senha;
	
	public CadastroEmpresaDTO(int cod_empresa, String nome_fantasia, String razao_social, String cnpj, Endereco endereco_empresa,
			String email, String telefone,String login, String senha) {
	
		this.cod_empresa = cod_empresa;
		this.nome_fantasia = nome_fantasia;
		this.razao_social = razao_social;
		this.cnpj = cnpj;
		this.endereco_empresa = endereco_empresa;
		this.email = email;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
	}
	

	public int getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(int cod_empresa) {
		this.cod_empresa = cod_empresa;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco_empresa() {
		return endereco_empresa;
	}

	public void setEndereco_empresa(Endereco endereco_empresa) {
		this.endereco_empresa = endereco_empresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	
	
}
