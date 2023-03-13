package br.com.mariojp.blog.persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.mariojp.blog.model.Post;

public class PostDAO {
	
	private static AtomicInteger atomicInteger = new AtomicInteger();

	
	private static Map<Integer,Post> posts = new HashMap<Integer,Post>();
	
	
	
	/**
	 * Obter a lista
	 * @return
	 */
	public List<Post> findAll(){
		return posts.values().stream().toList();
	}
	
	/**
	 * Salvar ou atualizar
	 * @param usuario
	 * @return
	 */
	public Post save(Post post) {
		if(post.getId() == null ) {
			post.setId(atomicInteger.getAndIncrement());
		}
		return 	posts.put(post.getId(), post);
	}
	
	/**
	 * Buscar pelo login
	 * 
	 * @param login
	 * @return
	 */
	public Post findById(Integer id) {
			return posts.get(id);
	}
	
	
	/**
	 * Excluir pelo login
	 * 
	 * @param login
	 * @return
	 */
	public Post delete(Integer id) {
		return posts.remove(id);
	}
	
	
	
	

}
