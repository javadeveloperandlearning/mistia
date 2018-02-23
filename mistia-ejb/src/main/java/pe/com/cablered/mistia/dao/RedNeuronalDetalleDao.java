package pe.com.cablered.mistia.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pe.com.cablered.mistia.model.RedNeuronalDetalle;



@ApplicationScoped
public class RedNeuronalDetalleDao extends CrudDao<RedNeuronalDetalle> {
	
	
	@Inject
	private EntityManager em;
	
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
