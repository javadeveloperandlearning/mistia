package pe.com.cablered.mistia.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.ObjectBean;
import pe.com.cablered.mistia.service.ClienteService;



public abstract class  CrudDao <T> {
	
	
	private Class<T> entityClass;   
        
        final static Logger logger = Logger.getLogger(CrudDao.class);
	
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
		logger.info(" metodo : create ");
		Calendar cal = Calendar.getInstance();
		
		try {
                        
                        String hostname  =  InetAddress.getLocalHost().getHostName().trim();
                        if(hostname.length()>15){
                            hostname =  InetAddress.getLocalHost().getHostName().trim().substring(0, 14);
                        }
                        logger.info("IP Server: "+InetAddress.getLocalHost().getHostName());
                        logger.info("hostname :  "+hostname);
                        logger.info("Fecha tiempo : "+cal.getTime());
                    
			((ObjectBean)t).setEstacionCreacion(hostname);
			((ObjectBean)t).setEstacionModificacion(hostname);
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