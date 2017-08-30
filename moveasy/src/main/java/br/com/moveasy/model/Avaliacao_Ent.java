package br.com.moveasy.model;

public class Avaliacao_Ent {

	int cod_avaliacao;
	Entregadores cod_ent;
	int estrelas;

	public Avaliacao_Ent(int cod_avaliacao,	Entregadores cod_ent,
	int estrelas) {
		setCod_avaliacao(cod_avaliacao);
		setCod_ent(cod_ent);
		setEstrelas(estrelas);
	}
	
	public int getCod_avaliacao() {
		return cod_avaliacao;
	}

	public void setCod_avaliacao(int cod_avaliacao) {
		this.cod_avaliacao = cod_avaliacao;
	}

	public Entregadores getCod_ent() {
		return cod_ent;
	}

	public void setCod_ent(Entregadores cod_ent) {
		this.cod_ent = cod_ent;
	}

	public int getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}

}
