package br.com.moveasy.model;

public class Entregadores {

	int cod_ent;
	String nome_ent;
	String sobrenome_ent;
	String cpf;
	Endereco endereco_ent;

	public Entregadores(int cod_ent, String nome_ent, String sobrenome_ent, String cpf, Endereco endereco_ent) {
		setCod_ent(cod_ent);
		setNome_ent(sobrenome_ent);
		setSobrenome_ent(sobrenome_ent);
		setCpf(cpf);
		setEndereco_ent(endereco_ent);
	}

	public int getCod_ent() {
		return cod_ent;
	}

	public void setCod_ent(int cod_ent) {
		this.cod_ent = cod_ent;
	}

	public String getNome_ent() {
		return nome_ent;
	}

	public void setNome_ent(String nome_ent) {
		this.nome_ent = nome_ent;
	}

	public String getSobrenome_ent() {
		return sobrenome_ent;
	}

	public void setSobrenome_ent(String sobrenome_ent) {
		this.sobrenome_ent = sobrenome_ent;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco_ent() {
		return endereco_ent;
	}

	public void setEndereco_ent(Endereco endereco_ent) {
		this.endereco_ent = endereco_ent;
	}

}
