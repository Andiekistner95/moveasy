package br.com.moveasy.model;

public class Endereco {

	int cod_endereco;
	String rua;
	String numero;
	String complemento;
	String bairro;
	Cidades cidade;

	public Endereco(int cod_endereco, String rua, String numero, String complemento, String bairro, Cidades cidade) {
		setCod_endereco(cod_endereco);
		setRua(rua);
		setNumero(numero);
		setComplemento(complemento);
		setBairro(bairro);
		setCidade(cidade);
	}


	public Endereco() {
		
	}
	
	public int getCod_endereco() {
		return cod_endereco;
	}

	public void setCod_endereco(int cod_endereco) {
		this.cod_endereco = cod_endereco;
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

	public Cidades getCidade() {
		return cidade;
	}

	public void setCidade(Cidades cidade) {
		this.cidade = cidade;
	}

}
