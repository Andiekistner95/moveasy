	package br.com.moveasy.dto;

import java.util.Date;

import br.com.moveasy.model.Destinatarios;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Pedidos;
import br.com.moveasy.model.Tipo_servico;

public class PedidosDTO {

	int codPedido;
	int codEmpresa;
	int codDestinatario;
	int codTipoServico;
	int codEntregadores;
	Date data;
	double taxa_extra;
	double valor_total;
	String descricao;
	
	
	public PedidosDTO(int codEmpresa, int codDestinatario, int codTipoServico, int codEntregadores, Date data, double taxa_extra, double valor_total, String descricao) {
		this.codEmpresa = codEmpresa;
		this.codDestinatario = codDestinatario;
		this.codTipoServico = codTipoServico;
		this.data = data;
		this.taxa_extra = taxa_extra;
		this.descricao = descricao;
		
	}
	
	public int getCodPedido() {
		return codPedido;
	}


	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}


	public int getCodEmpresa() {
		return codEmpresa;
	}



	public void setCodEmpresa(int codEmpresa) {
		this.codEmpresa = codEmpresa;
	}



	public int getCodDestinatario() {
		return codDestinatario;
	}



	public void setCodDestinatario(int codDestinatario) {
		this.codDestinatario = codDestinatario;
	}



	public int getCodTipoServico() {
		return codTipoServico;
	}



	public void setCodTipoServico(int codTipoServico) {
		this.codTipoServico = codTipoServico;
	}



	public int getCodEntregadores() {
		return codEntregadores;
	}



	public void setCodEntregadores(int codEntregadores) {
		this.codEntregadores = codEntregadores;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public double getTaxa_extra() {
		return taxa_extra;
	}



	public void setTaxa_extra(double taxa_extra) {
		this.taxa_extra = taxa_extra;
	}



	public double getValor_total() {
		return valor_total;
	}



	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	
}
