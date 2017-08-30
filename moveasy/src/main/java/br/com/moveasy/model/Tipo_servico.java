package br.com.moveasy.model;

public class Tipo_servico {

	int cod_servico;
	String descricao;
	double taxa;

	public Tipo_servico(int cod_servico, String descricao, double taxa) {
		setCod_servico(cod_servico);	
		setDescricao(descricao);
		setTaxa(taxa);

	}

	public int getCod_servico() {
		return cod_servico;
	}

	public void setCod_servico(int cod_servico) {
		this.cod_servico = cod_servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

}
