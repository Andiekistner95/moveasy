package br.com.moveasy.model;

public class Estados {

	int cod_estado;
	String nome_estado;
	String uf;

	public Estados(int cod_estado, String nome_estado, String uf) {
		setCod_estado(cod_estado);
		setNome_estado(nome_estado);
		setUf(uf);
	}

	public Estados() {
		
	}
	
	
	public int getCod_estado() {
		return cod_estado;
	}

	public void setCod_estado(int cod_estado) {
		this.cod_estado = cod_estado;
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

}
