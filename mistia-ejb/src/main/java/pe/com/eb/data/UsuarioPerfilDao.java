package pe.com.eb.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.com.eb.model.Usuario;
import pe.com.eb.model.UsuarioPerfil;

@Stateless
public class UsuarioPerfilDao extends CrudRepository<UsuarioPerfil>{
	
	
	@Inject
	EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<UsuarioPerfil> getUsuarioPerfilList(
			Usuario usuario) {
		
		String sql = " Select u From UsuarioPerfil u Where u.usuario=:usuario";
		TypedQuery<UsuarioPerfil> query  = getEntityManager().createQuery(sql, UsuarioPerfil.class);
		query.setParameter("usuario", usuario);
		
		return query.getResultList();
	}
	
	
	

}
