package pe.com.cablered.mistia.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;


import pe.com.cablered.mistia.model.ProgramacionDetalle;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.service.Response;

@ApplicationScoped
public class ProgramacionDetalleDao extends CrudDao<ProgramacionDetalle> {
	
	
	final static Logger logger = Logger.getLogger(ProgramacionDetalleDao.class);
	
	@Inject
	private EntityManager em;
	
	
	@Override
	protected EntityManager getEntityManager() {
	
		return em;
	}

	
	public List<SolicitudServicio> getSolicitudListPorProgramacion(Long numeroProgramacion) {
		
		logger.info(" ##  getSolicitudListPorProgramacion ## "  );
		
		String sql = "Select ptd.solicitudServicio 	from  ProgramacionDetalle d  "
				+ " join  d.planTrabajo pt  "
				+ " join  pt.planTrabajoDetalles ptd "
				+ " where d.programacion.numeroProgramacion =:pnumeroProgramacion";
		
		TypedQuery<SolicitudServicio> query =  getEntityManager().createQuery(sql, SolicitudServicio.class);
		query.setParameter("pnumeroProgramacion", numeroProgramacion);
		List<SolicitudServicio>  solicitudServicioList = query.getResultList();
		
		return solicitudServicioList;
	}


	public Response remove(Long numeroProgramacion) {
		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{
			
		   String sql = "delete from ProgramacionDetalle p  where p.id.numeroProgramacion = :pnumeroprogramacion";
		   Query query =  getEntityManager().createQuery(sql);
		   query.setParameter("pnumeroprogramacion", numeroProgramacion);
		   query.executeUpdate();
		   
		}catch(Exception e ){			
			
			e.printStackTrace();
			response = new Response(Response.OK, Response.MSG_OK);
		}
			
		return response;
		
	}
	
	
	
	
	

}
