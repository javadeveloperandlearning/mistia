package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.model.Encuesta;
import pe.com.cablered.mistia.model.EncuestaPregunta;
import pe.com.cablered.mistia.model.Programacion;


@ApplicationScoped
public class EncuestaPreguntaDao extends CrudDao<EncuestaPregunta> {
	
	
		
	
	@Inject
	private EntityManager em;
	
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
	public List<EncuestaPregunta> getEncuestaPreguntaList(Encuesta encuesta){
	
		List<EncuestaPregunta> list = Collections.EMPTY_LIST;
		
		try {
			
			String sql = "Select s from EncuestaPregunta s "
					+ " where s.encuesta =:pencuesta  order by s.id";
			TypedQuery<EncuestaPregunta> query = getEntityManager().createQuery(sql, EncuestaPregunta.class);
			query.setParameter("pencuesta", encuesta);		
			list = query.getResultList();
			

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	
	
	

}
