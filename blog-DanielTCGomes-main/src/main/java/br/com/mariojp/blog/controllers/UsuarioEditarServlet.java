package br.com.mariojp.blog.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.com.mariojp.blog.model.Usuario;
import br.com.mariojp.blog.services.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/autenticado/usuario/alterar")
public class UsuarioEditarServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private UsuarioService usuarioService = new UsuarioService();


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("id");
		
		Usuario usuario =  usuarioService.findByLogin(login);
		
		request.setAttribute("model", usuario);
		
		List<String> perfis = Arrays.asList("ADMIN", "REDATOR", "REVISOR");
 		
		request.setAttribute("perfis", perfis);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/autenticado/usuarioform.jsp");			
		requestDispatcher.forward(request, response);
	
	}
	
}
