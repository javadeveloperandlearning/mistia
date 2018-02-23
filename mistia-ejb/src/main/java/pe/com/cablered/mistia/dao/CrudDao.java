package pe.com.cablered.mistia.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pe.com.cablered.mistia.model.ObjectBean;



public abstract class  CrudDao <T> {
	
	
	private Class<T> entityClass;
	
	public CrudDao() {
		
	}
	
	public void setClass( Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public CrudDao( Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	protected abstract EntityManager getEntityManager();
	
	public void create(T t) {
		
		Calendar cal = Calendar.getInstance();
		
		try {
			((ObjectBean)t).setEstacionCreacion(InetAddress.getLocalHost().getHostName());
			((ObjectBean)t).setEstacionModificacion(InetAddress.getLocalHost().getHostName());
			((ObjectBean)t).setFechaCreacion(cal.getTime());
			((ObjectBean)t).setFechaModificacion(cal.getTime());
			
			((ObjectBean)t).setUsuarioCreacion("RCHANAME");
			((ObjectBean)t).setUsuarioModificacion("RCHANAME");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		getEntityManager().persist(t);
		getEntityManager().flush();
	}
	
	public void update(T t) {
	
		getEntityManager().merge(t);
	}
	
	public   void remove(T t){
		t  =  getEntityManager().merge(t);
		getEntityManager().remove(t);
		//getEntityManager().flush();
		
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