package br.com.casadocodigo.loja.controllers;


import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.loja.application.PedidoDTO;
import br.com.casadocodigo.loja.models.Produto;

public class PedidosServicoControllerTest {
	@Test
	public void deveRetornarPedidosDoWebService() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://book-payment.herokuapp.com/orders";

		ResponseEntity<List<PedidoDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PedidoDTO>>() {
				});
		List<PedidoDTO> pedidos = response.getBody();

		Produto produto = new Produto();
		produto.setId(7416);
		
		//assertThat(pedidos, containsInAnyOrder(produto));
	}
}
