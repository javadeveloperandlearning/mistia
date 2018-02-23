package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Servicio;

@ApplicationScoped
public class ServicioDao extends CrudDao<Servicio> {
	
	
	final static Logger logger = Logger.getLogger(ServicioDao.class);
	
	@Inject
	private EntityManager em;

	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}


	public Servicio getServicioPorPaquete(Integer codigoTipoPaquete, Integer codigoCantTele) {
		
		logger.info(" metodo : getServicioPorPaquete  ");
		logger.info(" codigoTipoPaquete:"+codigoTipoPaquete);
		logger.info(" codigoCantTele:"+codigoCantTele);
		
		Servicio servicio = null;
		
		try{
			
			
			String sql =  " Select s from Servicio s"
					+ " where s.tipoPaqueteCable =:pcodigotipopaquete "
					+ " and s.cantidadTelevisor =:pcantidadtelevisor";
			TypedQuery<Servicio> query =  getEntityManager().createQuery(sql, Servicio.class);	
			query.setParameter("pcodigotipopaquete", codigoTipoPaquete);
			query.setParameter("pcantidadtelevisor", codigoCantTele);
			
			
			List<Servicio> servicioList =  query.getResultList();
			if(servicioList!=null && servicioList.size()>0){
				servicio = servicioList.get(0);
			}
			

			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return servicio;

	}


	public Servicio getServicioPorVelocidad(Integer codigoTipoVeloInternet) {

		logger.info(" metodo : getServicioPorPaquete (codigoTipoVeloInternet) ");
		Servicio servicio = null;
		try{
			String sql =  " Select s from Servicio s"
					+ " where s.veloInterMbps = :pvelointermbps";
			TypedQuery<Servicio> query =  getEntityManager().createQuery(sql, Servicio.class);	
			query.setParameter("pvelointermbps", codigoTipoVeloInternet);
			
			List<Servicio> servicioList =  query.getResultList();
			if(servicioList!=null && servicioList.size()>0){
				servicio = servicioList.get(0);
			}
		
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return servicio;

	}
	
	

	
	
	
	
	

}
