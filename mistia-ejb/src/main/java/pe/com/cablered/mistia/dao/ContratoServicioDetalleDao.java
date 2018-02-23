package pe.com.cablered.mistia.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pe.com.cablered.mistia.model.ContratoServicioDetalle;

@ApplicationScoped
public class ContratoServicioDetalleDao extends CrudDao<ContratoServicioDetalle> {
	
	
	@Inject
	private EntityManager em;
	
	
	public ContratoServicioDetalleDao() {
		setClass(ContratoServicioDetalle.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
	
	
	
	

}
