package pe.com.cablered.mistia.dao;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;


import pe.com.cablered.mistia.dao.CrudDao;
import pe.com.cablered.mistia.model.Estado;
import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.SolicitudServicioEstado;
import pe.com.cablered.mistia.service.Response;


@ApplicationScoped
public class SolicitudServicioEstadoDao extends CrudDao<SolicitudServicioEstado> {
	
	
	@Inject
	private EntityManager em ;
	
	final static Logger logger = Logger.getLogger(SolicitudServicioEstadoDao.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	public  Response insert(Long numeroSolicitud, Integer codigoEstado){
		
		Response response =   new Response(Response.OK, Response.MSG_OK);
				
		try{
				String sqlcount  =  "Select count(1) from SolicitudServicioEstado s"
						+ "	where s.solicitudServicio.numeroSolicitud = :pnumerosolicitud";
				TypedQuery<Long>  query = getEntityManager().createQuery(sqlcount, Long.class);
				query.setParameter("pnumerosolicitud", numeroSolicitud);
				Long cant  = query.getSingleResult();
				Integer nse =  cant.intValue()+1;
				
				SolicitudServicioEstado se =  new SolicitudServicioEstado(numeroSolicitud, nse);
				se.setFechaHora(new  Date());
				se.setEstado(new Estado(codigoEstado));
				create(se);
				
		}catch(Exception e){
			e.printStackTrace();
			
			logger.info(e);
			logger.error(e);
			response =   new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		return response;
	}
	
	
	
	
		public  Response remove (Long numeroSolicitud, Integer codigoEstado){
			
				Response response =   new Response(Response.OK, Response.MSG_OK);
				
				try{
					String sql =  "delete from SolicitudServicioEstado s where "
							+ "    s.solicitudServicio.numeroSolicitud = :pnumerosolicitud  "
							+ "and s.estado.codigoEstado = :pcodigoestado ";
					Query query =  getEntityManager().createQuery(sql);
					query.setParameter("pnumerosolicitud", numeroSolicitud);
					query.setParameter("pcodigoestado", codigoEstado);
					query.executeUpdate();
					getEntityManager().flush();
						
				}catch(Exception e){
					e.printStackTrace();
					
					logger.info(e);
					logger.error(e);
					response =   new Response(Response.ERROR, Response.MSG_ERROR);
					
				}
				return response;
			}
	
	
	
	
	public List<SolicitudServicioEstado> getSolicitudServicioEstadoList(Long numeroSolicitud){
		
		List<SolicitudServicioEstado> list =  Collections.EMPTY_LIST;
		try{
			String sqlcount  =  "Select s from SolicitudServicioEstado s  "
							 + " where s.solicitudServicio.numeroSolicitud=:pnumerosolicitud";
			TypedQuery<SolicitudServicioEstado>  query = getEntityManager().createQuery(sqlcount, SolicitudServicioEstado.class);
			query.setParameter("pnumerosolicitud", numeroSolicitud);
			list =  query.getResultList();
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info(e);
		}
		
		return list;

		
	}
	
	
	
	

}
