package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.application.PedidoDTO;
import br.com.casadocodigo.loja.dao.PedidoDAO;

@Repository
public class Pedidos {
	@Autowired
	private PedidoDAO dao;

	public List<Pedido> buscarAtuais() {
		List<Pedido> pedidos = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		List<PedidoDTO> resposta = dao.listar();
		resposta.forEach(dto -> {
			LocalDate date = Instant.ofEpochMilli(dto.getData()).atZone(ZoneId.systemDefault()).toLocalDate();
			
			pedidos.add(new Pedido(dto.getId(), formatter.format(date), dto.getValor(), montaTitulos(dto)));
		});
		return pedidos;
	}

	private String montaTitulos(PedidoDTO dto) {
		StringBuilder itens = new StringBuilder();
		dto.getProdutos().forEach(p -> {
			itens.append(p.getTitulo());
			itens.append(", ");
		});
		return StringUtils.chomp(itens.toString(), ", ");
	}
}
