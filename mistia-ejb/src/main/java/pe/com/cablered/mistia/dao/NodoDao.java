package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;


import pe.com.cablered.mistia.model.Nodo;
import pe.com.eb.model.Modulo;

@ApplicationScoped
public class NodoDao extends CrudDao<Nodo> {

	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	final static Logger logger = Logger.getLogger(NodoDao.class);

	public List<Nodo> clienteList(){
	
		List<Nodo> list = Collections.EMPTY_LIST;	
		
		try {
			String sql = "Select c from Nodo c  ";
			System.out.println(sql);
			TypedQuery<Nodo> query = getEntityManager().createQuery(sql, Nodo.class);
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;

	}


	public List<Nodo> getNodoList() {
		
		List<Nodo> list = Collections.EMPTY_LIST;	
		
		try {
			String sql = "Select c from Nodo c  ";
			System.out.println(sql);
			TypedQuery<Nodo> query = getEntityManager().createQuery(sql, Nodo.class);
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;
	}


	public List<Nodo> getNodoList(Integer codigoMatriz, String criterio) {
	
		logger.info("metodo: getNodoList ");
		
		logger.info("codigoMatriz : "+codigoMatriz);
		logger.info("criterio : "+criterio);
		
		List<Nodo> list = Collections.EMPTY_LIST;	
		
		try {
			
			String sql = "Select c from Nodo c join c.matriz m "
					+ "	where (m.codigoMatriz=:pcodigomatriz or :pcodigomatriz is null )"
					+ "   and ( ( cast(c.codigoNodo as string ) like :pcriterio "
					+ "			or  upper(c.descripcion) like :pcriterio "
					+ "			or  cast(c.latitud  as string ) like :pcriterio "
					+ "			or  cast(c.longitud as string ) like :pcriterio ) or :pcriterio is null ) order by c.codigoNodo ";
			
			TypedQuery<Nodo> query = getEntityManager().createQuery(sql, Nodo.class);
			query.setParameter("pcodigomatriz", codigoMatriz);
			criterio = criterio==null?null:"%"+criterio.trim().trim().toUpperCase()+"%";
			query.setParameter("pcriterio", criterio);
			
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
	public int getMax(Nodo nodo) {
		
		logger.info("metodo:  getCount");
		
		try{
			
			String sql = "Select max(p.codigoNodo) From Nodo p";
			TypedQuery<Integer> query = getEntityManager().createQuery(sql,Integer.class);
			return ((Integer)query.getSingleResult()).intValue();
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
			
			return 0;
		}
	}


	/** 
	 * getNodo 
	 * obtiene nodo por descripción
	 * */
	public Nodo getNodo(String descripcion) {
		
		Nodo nodo = null;
		
		try{
			
			String sql = "Select p From Nodo p where upper(p.descripcion) = upper(:pdescripcion)";
			TypedQuery<Nodo> query  = getEntityManager().createQuery(sql, Nodo.class);
			query.setParameter("pdescripcion", descripcion.trim());
			if(query.getResultList().size()>0){
				nodo =  query.getResultList().get(0);
			}
			
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return nodo;
	}


	/** 
	 * hasPostes 
	 * Verifica si tiene postes relacionados
	 * */
	public boolean hasPostes(Nodo nodo) {
		boolean has = false;
		try{
			String sql = "Select count(1) From Nodo p join p.postes where  p.codigoNodo = :pcodigonodo ";
			TypedQuery<Long> query = getEntityManager().createQuery(sql,Long.class);
			query.setParameter("pcodigonodo", nodo.getCodigoNodo());
			if(((Long)query.getSingleResult()).intValue()>0){
				has =  true;
			}
	
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		
		return has;
	}


	public Nodo getNodo(Integer codigoNodo) {
		Nodo nodo = null;
		
		try{
			
			String sql = "Select p From Nodo p where p.codigoNodo = :pcodigonodo";
			TypedQuery<Nodo> query  = getEntityManager().createQuery(sql, Nodo.class);
			query.setParameter("pcodigonodo", codigoNodo);
			if(query.getResultList().size()>0){
				nodo =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return nodo;
	}


	
}
