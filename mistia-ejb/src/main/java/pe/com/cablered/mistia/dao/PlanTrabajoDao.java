package pe.com.cablered.mistia.dao;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.service.Response;

@ApplicationScoped
public class PlanTrabajoDao extends CrudDao<PlanTrabajo> {
	
	
	final static Logger logger = Logger.getLogger(PlanTrabajoDao.class); 
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	
	
	
	public  Response insert(PlanTrabajo planTrabajo){
	
		Response response =  new Response(Response.OK, Response.MSG_OK);
		try{
	
			String sqlcount  =  "Select count(1) from PlanTrabajo p";
			TypedQuery<Long>  query = getEntityManager().createQuery(sqlcount, Long.class);
			Long cant  = query.getSingleResult();
			
			Long np =  cant+1;
			planTrabajo.setNumeroPlanTrabajo(np);
			create(planTrabajo);
			response.setData(np);
			
		}catch(Exception e ){
			
			e.printStackTrace();
			return new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		
		
		
		return response;
		
	}




	public List<PlanTrabajo> getPlanTrabajoListPorProgramacion(Long numeroProgramacion) {
		logger.info(" metodo : getPlanTrabajoListPorProgramacion");
		
		
		List<PlanTrabajo> planTrabajoList =  Collections.EMPTY_LIST;
		try{
			String sqlcount  =  "Select p from PlanTrabajo p "
					+ "	join p.programacionDetalles pd "
					+ " where pd.programacion.numeroProgramacion=:pnumeroProgramacion";
			TypedQuery<PlanTrabajo>  query = getEntityManager().createQuery(sqlcount, PlanTrabajo.class);
			query.setParameter("pnumeroProgramacion", numeroProgramacion);
			planTrabajoList = query.getResultList();
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info(e);
		}
		
		return planTrabajoList;
	}


	public PlanTrabajo getPlanTrabajoPorCualdrilla(Date fecPrgn, Long numeroCuadrilla) {
		
		



				Calendar cal = Calendar.getInstance(); // locale-specific
				cal.setTime(fecPrgn);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				fecPrgn = cal.getTime();
		
		
		logger.info(" fecprgn : "+fecPrgn);
		logger.info(" numeroCuadrilla : "+numeroCuadrilla);
		
		logger.info(" metodo : getPlanTrabajo ( fecPrgn.  numeroPlanTrabajo ) ");
		
		PlanTrabajo planTrabajo =  null;
	
		try{
			
			String sql  =  "Select p from PlanTrabajo p "
					+ "	where  date_trunc('day',p.fechaProgramacion)=:pfechaProgramacion  "
					+ "		and p.cuadrilla.numeroCuadrilla =:pnumeroCuadrilla";
			
			TypedQuery<PlanTrabajo>  query = getEntityManager().createQuery(sql, PlanTrabajo.class);
			
			query.setParameter("pnumeroCuadrilla",1l);
			query.setParameter("pfechaProgramacion", fecPrgn, TemporalType.TIMESTAMP);
			
			List<PlanTrabajo>  planTrabajoList =  query.getResultList();
			if(planTrabajoList!=null && planTrabajoList.size()>0){
				planTrabajo = planTrabajoList.get(0);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info(e);
		}
		
		return planTrabajo;
	}

	
	public Response remove(long numeroPlanTrabajo) {
		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{	
			
			String sql =  " delete from  PlanTrabajo p where p.numeroPlanTrabajo = :pnumeroplantrabajo ";
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
