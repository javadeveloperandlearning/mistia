package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.model.Encuesta;
import pe.com.cablered.mistia.model.EncuestaPregunta;
import pe.com.cablered.mistia.model.EncuestaRespuesta;

@ApplicationScoped
public class EncuestaRespuestaDao extends CrudDao<EncuestaRespuesta>{
	
	
	@Inject
	private EntityManager em;
	

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	
	
	public List<EncuestaRespuesta> getEncuestaRespuestaList(Encuesta encuesta){
		
		List<EncuestaRespuesta> list = Collections.EMPTY_LIST;
		
		try {
			
			String sql = "Select s from EncuestaRespuesta s "
					+ " where s.id.numeroEncuesta =:pnumeroencuesta  order by s.id";
			TypedQuery<EncuestaRespuesta> query = getEntityManager().createQuery(sql, EncuestaRespuesta.class);
			query.setParameter("pnumeroencuesta", encuesta.getNumeroEncuesta());		
			list = query.getResultList();
			

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;
		

	}



	public EncuestaRespuesta getEncuestaRespuesta(Integer numeroEncuesta, String respuesta) {
		
		EncuestaRespuesta encuestaRespuesta =  null;
		
		try {
			
			String sql = "Select s from EncuestaRespuesta s "
					+ " where s.id.numeroEncuesta =:pnumeroencuesta  and s.descripcion = :pdescripcion";
			TypedQuery<EncuestaRespuesta> query = getEntityManager().createQuery(sql, EncuestaRespuesta.class);
			query.setParameter("pnumeroencuesta",numeroEncuesta);		
			query.setParameter("pdescripcion",respuesta);	
			encuestaRespuesta = query.getSingleResult();
			

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return encuestaRespuesta;
	}
	
	
}
