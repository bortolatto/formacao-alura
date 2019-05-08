package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class Pedido {
	private final long id;
	private final String dataPedido;
	private final double valor;
	private final String itens;

	public Pedido(long id, String dataPedido, double valor, String itens) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.valor = valor;
		this.itens = itens;
	}

	public long getId() {
		return id;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public double getValor() {
		return valor;
	}

	public String getItens() {
		return itens;
	}

}
