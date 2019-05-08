package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedido;
import br.com.casadocodigo.loja.models.Pedidos;

@Controller
@RequestMapping("/pedidos")
public class PedidosServicoController {
	@Autowired 
	private Pedidos repositorioDePedidos;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPedidos() {
		List<Pedido> pedidos = repositorioDePedidos.buscarAtuais();
		
		ModelAndView modelAndView = new ModelAndView("pedidos");
		modelAndView.addObject("pedidos", pedidos);
		return modelAndView;
	}
}
