package pe.com.cablered.mistia.dao;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.CuadrillasDetalle;
import pe.com.cablered.mistia.model.Tecnico;

@ApplicationScoped
public class CuadrillasDetalleDao extends  CrudDao<CuadrillasDetalle> {
	
	
	
	final static Logger logger = Logger.getLogger(CuadrillasDetalleDao.class); 
	

	@Inject
	private EntityManager em;
	

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}


	public Cuadrilla getCuadrillaPorTecnico(Date fecPrgn, Integer codigoTecnico) {
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(fecPrgn);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		fecPrgn = cal.getTime();
		logger.info(" fecprgn : "+fecPrgn);
		logger.info(" codigoTecnico : "+codigoTecnico);
		
		Cuadrilla cuadrilla =  null;
		
		try{
			
			String sql  = " Select d.cuadrilla from CuadrillasDetalle d  join d.cuadrilla c"
					+ " where  d.tecnico.codigoTecnico =:pcodigoTecnico"
					+ "	and date_trunc('day', c.fechaProgramacion )=:pfechaProgramacion";
			
			TypedQuery<Cuadrilla>  query = getEntityManager().createQuery(sql, Cuadrilla.class);
			query.setParameter("pcodigoTecnico", codigoTecnico);
			query.setParameter("pfechaProgramacion", fecPrgn, TemporalType.TIMESTAMP);
			
			List<Cuadrilla> cuadrillaList = query.getResultList();
			
			if(cuadrillaList!=null && cuadrillaList.size()>0){
				cuadrilla =  cuadrillaList.get(0);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info(e);
		}
		
		return cuadrilla;
	
	}
	
	
	public List<CuadrillasDetalle> getCuadrillasDetalleList(Cuadrilla cuadrilla){
		
		List<CuadrillasDetalle> cuadrillasDetalleList =  Collections.EMPTY_LIST;
		
		try{
			
			String sql  = " Select d from CuadrillasDetalle d where d.cuadrilla = :pcuadrilla";
			TypedQuery<CuadrillasDetalle>  query = getEntityManager().createQuery(sql, CuadrillasDetalle.class);
			query.setParameter("pcuadrilla", cuadrilla);
			cuadrillasDetalleList = query.getResultList();
	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info(e);
		}
		
		
		return cuadrillasDetalleList;
		
	}


	public boolean isAsigando(Tecnico tecnico) {
		
		boolean asignado  = false;
	
		try{
			
			String sql  = " Select count(1) from CuadrillasDetalle d   "
					+ " where  d.tecnico =:ptecnico";
				
			TypedQuery<Long>  query = getEntityManager().createQuery(sql, Long.class);
			query.setParameter("ptecnico", tecnico);
			
			if(((Long)query.getSingleResult()).intValue()>0){
				asignado =  true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info(e);
		}
		
		return asignado;
	}
	
	
	
	
	
	
	

}
