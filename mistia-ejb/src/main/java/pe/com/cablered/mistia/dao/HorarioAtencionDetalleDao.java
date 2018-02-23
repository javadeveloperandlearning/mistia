package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.HorarioAtencionDetalle;

@ApplicationScoped
public class HorarioAtencionDetalleDao extends CrudDao<HorarioAtencionDetalle> {

	
	final static Logger logger = Logger.getLogger(HorarioAtencionDetalle.class);
	
	@Inject
	private EntityManager em;
	
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	public List<HorarioAtencionDetalle> getHorarioAtencionDetalleList(Integer numeroHorario){
		
		List<HorarioAtencionDetalle> horarioAtencionDetalleList =  Collections.EMPTY_LIST;
		
		try{
			String sql =  " Select d from HorarioAtencionDetalle d "
					+ " where d.horarioAtencion.numeroHorario=:pnumerohorario";
			
			TypedQuery<HorarioAtencionDetalle> query =  getEntityManager().createQuery(sql, HorarioAtencionDetalle.class);	
			query.setParameter("pnumerohorario", numeroHorario);
			horarioAtencionDetalleList =  query.getResultList();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return horarioAtencionDetalleList;
	}
	
	

}
