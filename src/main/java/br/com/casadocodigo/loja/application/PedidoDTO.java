package br.com.casadocodigo.loja.application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.casadocodigo.loja.models.Produto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoDTO {
	private long id;
	private double valor;
	private long data;
	private List<Produto> produtos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "PedidoDTO [id=" + id + ", valor=" + valor + ", data=" + data + ", produtos=" + produtos + "]";
	}

}
