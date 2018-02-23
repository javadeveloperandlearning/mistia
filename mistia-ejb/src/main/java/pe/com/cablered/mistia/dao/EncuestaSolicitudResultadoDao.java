package pe.com.cablered.mistia.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pe.com.cablered.mistia.model.EncuestaRespuesta;
import pe.com.cablered.mistia.model.EncuestaSolicitudResultado;

@ApplicationScoped
public class EncuestaSolicitudResultadoDao extends CrudDao<EncuestaSolicitudResultado> {
	
	
	@Inject
	private EntityManager em;
	
	
	public EncuestaSolicitudResultadoDao() {
	
		super(EncuestaSolicitudResultado.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	

}
