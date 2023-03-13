package br.com.mariojp.blog.services;

import java.util.List;

import javax.security.auth.login.FailedLoginException;

import br.com.mariojp.blog.model.Usuario;
import br.com.mariojp.blog.persistencia.UsuarioDAO;

public class UsuarioService {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	
	public Usuario autenticar(String login, String senha) throws FailedLoginException {
		Usuario usuario = usuarioDAO.findByLogin(login);
		if(senha!=null && senha.equals(usuario.getPassword())) {
			return usuario;
		}
		throw new FailedLoginException("suario ou Senha invalidos");
	}


	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}


	public Usuario findByLogin(String login) {
		return usuarioDAO.findByLogin(login);
	}


	public void atualizar(String id, Usuario usuario) {
		usuarioDAO.delete(id);
		usuarioDAO.save(usuario);
	}
	
	public void criar(Usuario usuario ) {
		usuarioDAO.save(usuario);
	}


	public void excluir(String login) {
		usuarioDAO.delete(login);
	}
	
	
	
	
	

}
