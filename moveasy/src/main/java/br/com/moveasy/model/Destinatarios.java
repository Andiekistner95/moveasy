package br.com.moveasy.model;

public class Destinatarios {

	int cod_dest;
	String nome_dest;
	Endereco endereco_dest;

	public Destinatarios(int cod_dest, String nome_dest, Endereco endereco_dest) {
		setCod_dest(cod_dest);
		setNome_dest(nome_dest);
		setEndereco_dest(endereco_dest);

	}

	public int getCod_dest() {
		return cod_dest;
	}

	public void setCod_dest(int cod_dest) {
		this.cod_dest = cod_dest;
	}

	public String getNome_dest() {
		return nome_dest;
	}

	public void setNome_dest(String nome_dest) {
		this.nome_dest = nome_dest;
	}

	public Endereco getEndereco_dest() {
		return endereco_dest;
	}

	public void setEndereco_dest(Endereco endereco_dest) {
		this.endereco_dest = endereco_dest;
	}

}
