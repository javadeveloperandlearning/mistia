package pe.com.cablered.seguridad.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.com.cablered.seguridad.model.Perfil;
import pe.com.cablered.seguridad.model.PerfilOpcion;

@Stateless
public class PerfilOpcionDao extends CrudRepository<PerfilOpcion> {

	@Inject
	EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<PerfilOpcion> getPerfilOpcion(List<Perfil> perfilList) {

		List<PerfilOpcion> perfilOpcion = null;

		if (perfilList.size() > 0) {

			String sql = "Select p From PerfilOpcion p Where p.perfil in (:perfilList)";
			TypedQuery<PerfilOpcion> query = getEntityManager().createQuery(
					sql, PerfilOpcion.class);
			query.setParameter("perfilList", perfilList);
			perfilOpcion = query.getResultList();
		}
		// return query.getResultList();
		return perfilOpcion;
	}

}
