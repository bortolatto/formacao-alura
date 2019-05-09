package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioDAO dao;
	@Autowired
	private RoleDAO roleDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation(dao));
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(usuario);
		}
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		dao.gravar(usuario);

		redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		List<Usuario> usuarios = dao.listarTodos();
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	@RequestMapping(value = "/detalhe/{email}/")
	public ModelAndView detalhe(@PathVariable("email") String email, RedirectAttributes redirectAttributes,
			HttpSession session) {
		Usuario usuario = dao.loadUserByUsername(email);

		ModelAndView modelAndView = new ModelAndView("usuarios/detalhe");
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("roles", roleDao.listar());
		
		session.setAttribute("usuarioSession", usuario);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/atualizar")
	public ModelAndView atualizar(Usuario usuario, RedirectAttributes redirectAttributes, HttpSession session) {
		Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
		Usuario usuarioAlterar = dao.loadUserByUsername(usuarioSession.getEmail());
		usuarioAlterar.setRoles(usuario.getRoles());
	
		dao.atualizar(usuarioAlterar);

		redirectAttributes.addFlashAttribute("message", "Permissões alteradas com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}

}
