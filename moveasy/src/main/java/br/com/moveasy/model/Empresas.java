package br.com.moveasy.model;

public class Empresas {

	int cod_empresa;
	String nome_fantasia;
	String razao_social;
	String cnpj;
	Endereco endereco_empresa;
	String email;
	String telefone;

	public Empresas(int cod_empresa, String nome_fantasia, String razao_social, String cnpj, Endereco endereco_empresa,
			String email, String telefone) {
		setCod_empresa(cod_empresa);
		setNome_fantasia(nome_fantasia);
		setRazao_social(razao_social);
		setCnpj(cnpj);
		setEndereco_empresa(endereco_empresa);
		setEmail(email);
		setTelefone(telefone);
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

}
