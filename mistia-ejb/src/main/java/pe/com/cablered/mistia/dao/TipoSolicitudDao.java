package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.TipoSolicitud;


@ApplicationScoped
public class TipoSolicitudDao extends CrudDao<TipoSolicitud>{
	
	@Inject
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<TipoSolicitud> getTipoSolicitudList(){
		
		List<TipoSolicitud> tipoSolicitudList =  Collections.EMPTY_LIST;
		try{
			String sql =  " select t from TipoSolicitud  t";
			TypedQuery<TipoSolicitud> query =  getEntityManager().createQuery(sql, TipoSolicitud.class);	
			tipoSolicitudList =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return tipoSolicitudList;
	}



	
	

}
