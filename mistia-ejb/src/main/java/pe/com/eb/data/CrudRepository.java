package pe.com.eb.data;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import pe.com.eb.model.ObjectBean;




/**
 * CrudDao abstract class  para mantenimientos simples
 * 
 * 
 * */
public abstract class CrudRepository<T> {
	
	
	private Class<T> entityClass;
	
	public CrudRepository() {
		
	}
	
	public void setClass( Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public CrudRepository( Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	protected abstract EntityManager getEntityManager();
	
	public void create(T t) {
		
		Calendar cal = Calendar.getInstance();
		
		try {
			
			((ObjectBean)t).setComCrea(InetAddress.getLocalHost().getHostName());
			((ObjectBean)t).setComModi(InetAddress.getLocalHost().getHostName());
			((ObjectBean)t).setFecCrea(cal.getTime());
			((ObjectBean)t).setFecModi(cal.getTime());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		getEntityManager().persist(t);
	}
	
	public void update(T t) {
		Calendar cal = Calendar.getInstance();
		
		try {
			((ObjectBean)t).setComModi(InetAddress.getLocalHost().getHostName());
			((ObjectBean)t).setFecModi(cal.getTime());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		getEntityManager().merge(t);
	}
	
	public   void remove(T t){
		t  =  getEntityManager().merge(t);
		getEntityManager().remove(t);
	};
	
	public T find(Object id) {
	   return getEntityManager().find(entityClass, id);
	}
	
	public int count(){
		
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query query  = getEntityManager().createQuery(cq);
		return ((Long)query.getSingleResult()).intValue();
	}

}