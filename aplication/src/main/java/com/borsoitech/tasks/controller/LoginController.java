package com.borsoitech.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.borsoitech.tasks.entity.Usuario;
import com.borsoitech.tasks.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/")
	public ModelAndView goToLogin() {
		return new ModelAndView("redirect:/login");
	}

	@GetMapping("/login")
	public ModelAndView showLoginPage() {
		return new ModelAndView("login");
	}

	@PostMapping("/validar")
	public ModelAndView validateLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
		Usuario usuario = usuarioRepository.findByEmail(username).get();

		if (usuario != null && usuario.isCredenciaisValida(username, password)) {
			usuario.setLogado(true);
			usuarioRepository.saveAndFlush(usuario);
			session.setAttribute("email", usuario.getEmail());
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("login").addObject("error", "Usuário ou senha inválidos");
		}
	}

	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		String username = (String) session.getAttribute("email");
		Usuario usuario = usuarioRepository.findByEmail(username).get();

		if (!usuario.isLogado()) {
			return new ModelAndView("redirect:/login");
		}

		usuario.setLogado(false);
		usuarioRepository.save(usuario);
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
}