package pe.com.cablered.mistia.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pe.com.cablered.mistia.model.SolicitudServicioHorarioAtencion;


@ApplicationScoped
public class SolicitudServicioHorarioAtencionDao extends CrudDao<SolicitudServicioHorarioAtencion>{
	
	
	@Inject
	private EntityManager em;
	

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	
	
	
}
