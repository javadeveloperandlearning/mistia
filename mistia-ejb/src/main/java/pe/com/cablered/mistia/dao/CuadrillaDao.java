package pe.com.cablered.mistia.dao;

import java.io.Console;
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

import pe.com.cablered.mistia.excepcion.BusinessException;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.Programacion;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.service.Response;

@Stateless
public class CuadrillaDao extends CrudDao<Cuadrilla> {
	
	
	@Inject
	private EntityManager em;

	
	final static Logger logger = Logger.getLogger(CuadrillaDao.class);
	
	public CuadrillaDao() {
		setClass(Cuadrilla.class);
		
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	

	public List<Cuadrilla> getCuadrillaList(Date fechaProgramacion ) {

		List<Cuadrilla> list = Collections.EMPTY_LIST;
		try {
			String sql = "Select c from Cuadrilla c join c.vehiculo v  ";
			System.out.println(sql);
			TypedQuery<Cuadrilla> query = getEntityManager().createQuery(sql, Cuadrilla.class);
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		return list;

	}
	
	public List<Cuadrilla> getCuadrillaList() {

		List<Cuadrilla> list = Collections.EMPTY_LIST;
		try {
			String sql = "Select c from Cuadrilla c   ";
			System.out.println(sql);
			TypedQuery<Cuadrilla> query = getEntityManager().createQuery(sql, Cuadrilla.class);
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		return list;

	}
	
	
	public boolean isAsigando(Vehiculo vehiculo) {
		
		boolean asignado  = false;
	
		try{
			
			String sql  = " Select count(1) from Cuadrilla d   "
					+ " where  d.vehiculo =:pvehiculo";
				
			TypedQuery<Long>  query = getEntityManager().createQuery(sql, Long.class);
			query.setParameter("pvehiculo", vehiculo);
			
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

	public List<Cuadrilla> getCuadrillaList(Date fechaIni, Date fechaFin, Vehiculo vehiculo, String criterio) {	

		List<Cuadrilla> list = Collections.emptyList();
		
		try {
			
			
			String sql = "Select c from Cuadrilla c join c.vehiculo v  where "
					+ "  date_trunc('day', c.fechaProgramacion) between  :paramfecIni and  :paramfecFin"
					+ "  and  (c.vehiculo = :pvehiculo or :pvehiculo is null ) "
					+ "  and  (upper(c.nombre) = :pcriterio or :pcriterio is null )  order by c.fechaProgramacion, c.numeroCuadrilla"
					;
			
			TypedQuery<Cuadrilla> query = getEntityManager().createQuery(sql, Cuadrilla.class);
			query.setParameter("paramfecIni", fechaIni, TemporalType.TIMESTAMP);
			query.setParameter("paramfecFin", fechaFin, TemporalType.TIMESTAMP);
			query.setParameter("pvehiculo", vehiculo);
			criterio = criterio==null?null:"%"+criterio.trim().trim().toUpperCase()+"%";
			query.setParameter("pcriterio", criterio);
			list = query.getResultList();
			//list =  getCuadrillaList(null);

		} catch (Exception e) {
				e.printStackTrace();
		}
		return list;
	
		
		
	}



	public long getMax() {
		long  max =  0;
		try{
				
			String sql = "Select max(p.numeroCuadrilla) From Cuadrilla p";
			TypedQuery<Long> query = getEntityManager().createQuery(sql,Long.class);
			return ((Long)query.getSingleResult()).longValue();
				
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
			return 0;
		}
		
		
	}

	public Cuadrilla getCuarilla(Date fechaProgramacion,  String nombre) {
		
		Cuadrilla cuadrilla = null;

		try{
	
			String sql = "Select p From Cuadrilla p where  date_trunc('day', p.fechaProgramacion) =:pfecprgn and upper(p.nombre) = upper(:pnombre)";
			TypedQuery<Cuadrilla> query  = getEntityManager().createQuery(sql, Cuadrilla.class);
			query.setParameter("pnombre", nombre);
			query.setParameter("pfecprgn", fechaProgramacion, TemporalType.TIMESTAMP);
			
			if(query.getResultList().size()>0){
				cuadrilla =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
		return cuadrilla;
	}
	
	

	
	
	/*
	 
	 
	 String sql = "Select p from Programacion "
					+ " p where date_trunc('day', p.fechaProgramacion) between  :paramfecIni and  :paramfecFin"
					+ " order by p.numeroProgramacion";
			
			TypedQuery<Programacion> query = getEntityManager().createQuery(sql, Programacion.class);
			
			query.setParameter("paramfecIni", fechaInicio, TemporalType.TIMESTAMP);
			query.setParameter("paramfecFin", fechaFin, TemporalType.TIMESTAMP);
	 
	 * 
	 * 
	 * */
	
	
	
	
	
	
	

}
