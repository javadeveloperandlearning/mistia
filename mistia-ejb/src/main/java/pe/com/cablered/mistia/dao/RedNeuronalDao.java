package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.model.RedNeuronal;



@ApplicationScoped
public class RedNeuronalDao  extends CrudDao<RedNeuronal> {
	
	@Inject
	private EntityManager em;
	
	
	
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	
	public RedNeuronal getRedNeuronal(Integer codigorn){
		
		RedNeuronal redNeuronal=null;
		try {
			String sql = " Select d from  RedNeuronal d where d.codigorn =:pcidigorn";
			TypedQuery<RedNeuronal> query = getEntityManager().createQuery(sql, RedNeuronal.class);
			query.setParameter("pcidigorn" , codigorn);
			redNeuronal = query.getSingleResult();

		} catch (Exception e) {
				e.printStackTrace();
		}
		return redNeuronal;
		
	}
	
	
	
	
	
	

}
