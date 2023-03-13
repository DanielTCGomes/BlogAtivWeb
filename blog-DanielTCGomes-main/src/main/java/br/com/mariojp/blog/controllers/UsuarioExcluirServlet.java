package br.com.mariojp.blog.controllers;

import java.io.IOException;

import br.com.mariojp.blog.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/autenticado/usuario/excluir")
public class UsuarioExcluirServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private UsuarioService usuarioService = new UsuarioService();


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("id");
		
		usuarioService.excluir(login);
		response.sendRedirect("/blog/autenticado/usuario/listar");
	}
	
}
