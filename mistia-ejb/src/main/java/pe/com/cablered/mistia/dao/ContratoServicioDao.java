package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.ContratoServicio;
import pe.com.cablered.mistia.model.Tipo;
import pe.com.cablered.mistia.service.Response;

@ApplicationScoped
public class ContratoServicioDao extends CrudDao<ContratoServicio> {
	

	
	final static Logger logger = Logger.getLogger(ContratoServicioDao.class);
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	
	public List<ContratoServicio> getContratoServicioList(Integer  codigoCliente) {

		logger.info(" metodo : getContratoServicioList (codigoGrupo) ");
		List<ContratoServicio> contratoServicioList =  Collections.EMPTY_LIST;
		try{
			String sql =  " Select c from ContratoServicio c where "
					+ " c.cliente.codigoCliente  = :pcodigocliente";
			TypedQuery<ContratoServicio> query =  getEntityManager().createQuery(sql, ContratoServicio.class);	
			query.setParameter("pcodigocliente", codigoCliente);
			contratoServicioList =  query.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return contratoServicioList;
	}



	public Response insert(ContratoServicio contratoServicio) {
	
		Response response =  new Response(Response.OK, Response.MSG_OK);
		try{
			String sqlcount  =  "Select max(numeroContrato) from ContratoServicio p";
			TypedQuery<Long>  query = getEntityManager().createQuery(sqlcount, Long.class);
			Long cant  = query.getSingleResult();
			Long nc =  cant==null?1:cant+1;
			contratoServicio.setNumeroContrato(nc);		
			create(contratoServicio);
			response.setData(nc);
			
		}catch(Exception e ){
			e.printStackTrace();
			return new Response(Response.ERROR, Response.MSG_ERROR);
		}
		
		return response;
	}
	

}
