package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.ContratoServicioDao;
import pe.com.cablered.mistia.dao.ContratoServicioDetalleDao;
import pe.com.cablered.mistia.model.ContratoServicio;
import pe.com.cablered.mistia.model.ContratoServicioDetalle;
import pe.com.cablered.mistia.model.SolicitudServicioHorarioAtencion;

@Stateless
public class ContratoServicioService {
	
	
	
	@Inject
	private ContratoServicioDao contratoServicioDao;
	
	
	@Inject
	private ContratoServicioDetalleDao contratoServicioDetalleDao; 
	
	
	
	
	public List<ContratoServicio> getContratoServicioList(Integer  codigoCliente) {
		// TODO Auto-generated method stub
		return contratoServicioDao.getContratoServicioList(codigoCliente);
	}


	public Response insertar(ContratoServicio contratoServicio) {
		
		Response response =  new  Response(Response.OK, Response.MSG_OK) ;
		
		try{
			// insertando el conratro
			response =  contratoServicioDao.insert(contratoServicio);
			Long nc= (Long)response.getData();
			
			// insertando el detalle de los servicios  
			List<ContratoServicioDetalle> contratoServicioDetalleList =    contratoServicio.getContratoServicioDetalleList();
			
			if(contratoServicioDetalleList!=null){
				for (ContratoServicioDetalle d : contratoServicioDetalleList) {
					d.getId().setNumeroContrato(nc);
					contratoServicioDetalleDao.create(d);
				}
			}
		
			
		}catch (Exception e) {
			e.printStackTrace();
			response =  new  Response(Response.ERROR, Response.MSG_ERROR) ;
		}
		return response;
	}

}
