package br.com.moveasy.dto;

import java.util.Date;

import br.com.moveasy.model.Destinatarios;
import br.com.moveasy.model.Empresas;
import br.com.moveasy.model.Entregadores;
import br.com.moveasy.model.Pedidos;
import br.com.moveasy.model.Tipo_servico;

public class PedidosDTO {

	int cod_pedido;
	Empresas empresa;
	Destinatarios destinatario;
	Tipo_servico tipo_servico;
	Entregadores entregador;
	Date data;
	double taxa_extra;
	double valor_total;
	String descricao;

	public PedidosDTO(int cod_pedido, Empresas empresa, Destinatarios destinatario, Tipo_servico tipo_servico,
			Entregadores entregador, Date data, double taxa_extra, double valor_total, String descricao) {
		setCod_pedido(cod_pedido);
		setEmpresa(empresa);
		setDestinatario(destinatario);
		setTipo_servico(tipo_servico);
		setEntregador(entregador);
		setData(data);
		setTaxa_extra(taxa_extra);
		setValor_total(valor_total);
		setDescricao(descricao);
	}

	public int getCod_pedido() {
		return cod_pedido;
	}

	public void setCod_pedido(int cod_pedido) {
		this.cod_pedido = cod_pedido;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}

	public Destinatarios getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatarios destinatario) {
		this.destinatario = destinatario;
	}

	public Tipo_servico getTipo_servico() {
		return tipo_servico;
	}

	public void setTipo_servico(Tipo_servico tipo_servico) {
		this.tipo_servico = tipo_servico;
	}

	public Entregadores getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregadores entregador) {
		this.entregador = entregador;
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
	
	public Pedidos toPedidos() {
		return new Pedidos(this.cod_pedido, this.empresa, this.destinatario, this.tipo_servico,
				this.entregador, this.data, this.taxa_extra, this.valor_total, this.descricao);
	}

	
}
