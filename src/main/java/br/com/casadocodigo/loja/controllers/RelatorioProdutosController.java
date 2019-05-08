package br.com.casadocodigo.loja.controllers;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.loja.application.ProdutoDTO;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@RestController
public class RelatorioProdutosController {
	@Autowired
	private ProdutoDAO dao;

	@RequestMapping(path = "/relatorio-produtos", method = RequestMethod.GET)
	public ProdutoDTO getProdutos(@RequestParam(name = "data", required = false) 
								  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataLancamento) {
		List<Produto> produtos = null;
		if (dataLancamento == null) {
			produtos = dao.listarTodos();
		} else {
			produtos = dao.listarTodosComDataLancamentoPosteriorA(dataLancamento);
		}
		return new ProdutoDTO(Calendar.getInstance(), produtos);
	}

}
