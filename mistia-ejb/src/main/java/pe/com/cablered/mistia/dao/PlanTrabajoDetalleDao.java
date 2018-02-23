package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.model.PlanTrabajoDetallePK;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.service.ProgramacionService;
import pe.com.cablered.mistia.service.Response;

@ApplicationScoped
public class PlanTrabajoDetalleDao extends CrudDao<PlanTrabajoDetalle> {
	
	
	
	final static Logger logger = Logger.getLogger(PlanTrabajoDetalleDao.class);
		
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public PlanTrabajoDetalleDao() {
		
		super(PlanTrabajoDetalle.class);

	}


	public List<PlanTrabajoDetalle> getPlanTrabajoDetalleList(Long numeroProgramacion, Long numerocuadrilla) {
		
		List<PlanTrabajoDetalle>  planTrabajoDetalleList = Collections.EMPTY_LIST;
		
		try{
			
			String sql =  " Select d from PlanTrabajoDetalle d"
					+ "	join d.planTrabajo pt "
					+ " join pt.programacionDetalles  pd "
					+ " where pd.programacion.numeroProgramacion =:pnumeroprogramacion and  pt.cuadrilla.numeroCuadrilla=:pnumerocuadrilla ";
			TypedQuery<PlanTrabajoDetalle> query =  getEntityManager().createQuery(sql, PlanTrabajoDetalle.class);
			query.setParameter("pnumeroprogramacion", numeroProgramacion);
			query.setParameter("pnumerocuadrilla", numerocuadrilla);
			planTrabajoDetalleList =  query.getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
			
		}
		// TODO Auto-generated method stub
		return planTrabajoDetalleList;
	}
	
	
	public List<PlanTrabajoDetalle> getPlanTrabajoDetalleList(Long numeroPlanTrabajo, Integer estadoProgramacion) {
		
		List<PlanTrabajoDetalle>  planTrabajoDetalleList = Collections.EMPTY_LIST;
		try{
			
			String sql =  " Select d from PlanTrabajoDetalle d"
					+ "	join d.planTrabajo pt join"
					+ " pt.programacionDetalles prd join prd.programacion pr  "
					+ " where pt.numeroPlanTrabajo=:pnumeroPlanTrabajo "
					+ "	and pr.estado.codigoEstado =:pcodigoestado";
			TypedQuery<PlanTrabajoDetalle> query =  getEntityManager().createQuery(sql, PlanTrabajoDetalle.class);
			query.setParameter("pnumeroPlanTrabajo", numeroPlanTrabajo);
			query.setParameter("pcodigoestado", estadoProgramacion);
			
			planTrabajoDetalleList =  query.getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
			
		}
		return planTrabajoDetalleList;
	}

	
	public List<PlanTrabajoDetalle> getPlanTrabajoDetalleList(Long numeroPlanTrabajo) {
		
		List<PlanTrabajoDetalle>  planTrabajoDetalleList = Collections.EMPTY_LIST;
		try{
			
			String sql =  " Select d from PlanTrabajoDetalle d"
					+ " where d.id.numeroPlanTrabajo=:pnumeroPlanTrabajo ";
			TypedQuery<PlanTrabajoDetalle> query =  getEntityManager().createQuery(sql, PlanTrabajoDetalle.class);
			query.setParameter("pnumeroPlanTrabajo", numeroPlanTrabajo);			
			planTrabajoDetalleList =  query.getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
			
		}
		return planTrabajoDetalleList;
	}
	
	
	

	public Response actualizarDetalle(PlanTrabajoDetalle planTrabajoDetalle) {
		
	
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{	
			
			Long 	 n = planTrabajoDetalle.getNumeroPlanTrabajo();
			Integer  s =  planTrabajoDetalle.getNumeroSecuencial();
			Integer codigoEstado = planTrabajoDetalle.getCodigoEstado();
			String obs = planTrabajoDetalle.getObservacion();
			
			String sql =  " update PlanTrabajoDetalle p "
					+ " set p.codigoEstado =:pcodigoestado,  p.indAtnd =:pindatnd, p.observacion=:pobs"
					+ " where p.id =:pid";
			Query query =  getEntityManager().createQuery(sql);
			query.setParameter("pid", new PlanTrabajoDetallePK(n, s));
			query.setParameter("pcodigoestado", codigoEstado);
			query.setParameter("pindatnd", planTrabajoDetalle.getIndAtnd());
			query.setParameter("pobs", planTrabajoDetalle.getObservacion());
			
			query.executeUpdate();
	
		}catch(Exception e){
		
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		}
		
		return response;
	}

	public Response remove(long numeroPlanTrabajo) {
		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{	
			
			String sql =  " delete from  PlanTrabajoDetalle pd where pd.id.numeroPlanTrabajo = :pnumeroplantrabajo ";
			Query query =  getEntityManager().createQuery(sql);		
			query.setParameter("pnumeroplantrabajo", numeroPlanTrabajo);
			query.executeUpdate();
	
		}catch(Exception e){
		
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		}
		
		return response;
	}
	
	
	
	

}
