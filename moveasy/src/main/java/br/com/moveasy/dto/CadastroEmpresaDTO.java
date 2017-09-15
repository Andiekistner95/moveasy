package br.com.moveasy.dto;

import br.com.moveasy.model.Endereco;
import br.com.moveasy.model.Usuarios;

public class CadastroEmpresaDTO {

	// Empresa
	private int cod_empresa;
	private String nome_fantasia;
	private String razao_social;
	private String cnpj;
	private String email;
	private String telefone;

	// Usuario
	private String login;
	private String senha;
	private int status;
	private int cod_usuario;

	// Endereco
	private int cod_endereco;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;

	// Cidade
	private int cod_cidade;
	private String nome_cidade;

	// Estado
	private String nome_estado;
	private String uf;

	// Contrutor Empresa
	public CadastroEmpresaDTO(int cod_empresa, String nome_fantasia, String razao_social, String cnpj, String email,
			String telefone, String login, String senha, int status, int cod_usuario, int cod_endereco, String rua,
			String numero, String complemento, String bairro, int cod_cidade, String nome_cidade, String nome_estado,
			String uf) {

		this.cod_empresa = cod_empresa;
		this.nome_fantasia = nome_fantasia;
		this.razao_social = razao_social;
		this.cnpj = cnpj;
		this.cod_endereco = cod_endereco;
		this.email = email;
		this.telefone = telefone;
		this.cod_usuario = cod_usuario;

		// Empresa
		this.cod_empresa = cod_empresa;
		this.nome_fantasia = nome_fantasia;
		this.razao_social = razao_social;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;

		// Usuario
		this.login = login;
		this.senha = senha;
		this.status = status;
		this.cod_usuario = cod_usuario;

		// Endereco
		this.cod_endereco = cod_endereco;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;

		// Cidade
		this.cod_cidade = cod_cidade;
		this.nome_cidade = nome_cidade;

		// Estado
		this.nome_estado = nome_estado;
		this.uf = uf;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCod_cidade() {
		return cod_cidade;
	}

	public void setCod_cidade(int cod_cidade) {
		this.cod_cidade = cod_cidade;
	}

	public String getNome_cidade() {
		return nome_cidade;
	}

	public void setNome_cidade(String nome_cidade) {
		this.nome_cidade = nome_cidade;
	}

	public String getNome_estado() {
		return nome_estado;
	}

	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getCod_endereco() {
		return cod_endereco;
	}

	public void setCod_endereco(int cod_endereco) {
		this.cod_endereco = cod_endereco;
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
