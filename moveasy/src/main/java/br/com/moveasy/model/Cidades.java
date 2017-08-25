package br.com.moveasy.model;

public class Cidades {

	int cod_cidade;
	String nome_cidade;
	Estados estado;

	public Cidades(int cod_cidade, String nome_cidade, Estados estado) {
		setCod_cidade(cod_cidade);
		setNome_cidade(nome_cidade);
		setEstado(estado);
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

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

}
