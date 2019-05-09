package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}
		
		return usuarios.get(0);
	}
	
	public boolean isUserExists(String email) {
		TypedQuery<Usuario> query = manager.createQuery("select distinct(u) from Usuario u join fetch u.roles where u.email = :email",Usuario.class);
		query.setParameter("email", email);
		return query.getResultList().size() > 0;
	}
	
	public List<Usuario> listarTodos() {
		return manager.createQuery("select u from Usuario u",Usuario.class).getResultList();
	}

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}
}