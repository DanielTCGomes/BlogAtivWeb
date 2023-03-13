package br.com.mariojp.blog.controllers;

import java.io.IOException;

import br.com.mariojp.blog.model.Usuario;
import br.com.mariojp.blog.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/autenticado/usuario/salvar")
public class UsuarioSalvarServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private UsuarioService usuarioService = new UsuarioService();


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");	
		String perfil = request.getParameter("perfil");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setPassword(senha);
		usuario.setPerfil(perfil);
		

		if(login != null && !login.isBlank())
			usuarioService.atualizar(id, usuario);
		else
			usuarioService.criar(usuario);
		
		response.sendRedirect("/blog//autenticado/usuario/listar");
		
	}
	
}
