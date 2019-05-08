package br.com.casadocodigo.loja.dao;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.loja.application.PedidoDTO;

@Repository
public class PedidoDAO {
	private final String URL = "https://book-payment.herokuapp.com/orders";
	
	public List<PedidoDTO> listar() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<PedidoDTO>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PedidoDTO>>() {
				});
		
		return response.getBody();
	}
}
