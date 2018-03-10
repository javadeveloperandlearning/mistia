package pe.com.cablered.seguridad.dao;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.seguridad.model.Usuario;

/**
 * Session Bean implementation class UsuarioRepository
 */

@ApplicationScoped
public class UsuarioDao extends CrudRepository<Usuario> {

	/**
	 * Default constructor.
	 */

	@Inject
	EntityManager em;

	final static Logger logger = Logger.getLogger(UsuarioDao.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Usuario validar(Usuario usuario) {

		String sql = "Select u from Usuario u where u.codUsua  =:usuario and  u.clave =:password ";
		TypedQuery<Usuario> query = getEntityManager().createQuery(sql,
				Usuario.class);
		query.setParameter("usuario", usuario.getCodUsua());
		query.setParameter("password", usuario.getClave());

		List<Usuario> list = query.getResultList();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<Usuario> getUsuarioList() {

		TypedQuery<Usuario> query = getEntityManager().createQuery(
				"Select u from Usuario u Join fetch u.estadoRegistro e", Usuario.class);
		return query.getResultList();

	}

	/**
	 * getUsuarioList obtiene la lista de un usuario tomando en cuenta el
	 * código, nombres y estado
	 * */

	public List<Usuario> getUsuarioList(Usuario usuario) {
		logger.info(" metodo: getUsuarios");
		
		List<Usuario> list =  Collections.emptyList();

		try {
			String sql = "Select u from Usuario u join u.estadoRegistro e"

					+ " where "

					+ " (UPPER(u.codUsua)  =:codUsua or :codUsua is null  )"
					+ "and  ( UPPER( u.nombres) like :nombres or :nombres is null )"
					+ "and  (u.estadoRegistro=:estadoRegistro or :estadoRegistro is null) ";
			TypedQuery<Usuario> query = getEntityManager().createQuery(sql,Usuario.class);
			query.setParameter("codUsua", usuario.getCodUsua() == null ? null: usuario.getCodUsua().toUpperCase());
			query.setParameter("nombres", usuario.getNombres() == null ? null: "%" + usuario.getNombres().toUpperCase() + "%");
			query.setParameter("estadoRegistro", usuario.getEstadoRegistro());

			list  =  query.getResultList();
			logger.info("cant items : "+list.size());
			return list;

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		
		
		}
		
		return list;

	}
	
	
	/**
	 * getUsuarioSingle
	 * @usuario
	 * 
	 * */
	public Usuario getUsuarioSingle(Usuario usuario) {

		String sql = "Select u from Usuario u Join fetch u.estadoRegistro where u.codUsua  =:usuario ";
		TypedQuery<Usuario> query = getEntityManager().createQuery(sql,
				Usuario.class);
		query.setParameter("usuario", usuario.getCodUsua());

		List<Usuario> list = query.getResultList();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}