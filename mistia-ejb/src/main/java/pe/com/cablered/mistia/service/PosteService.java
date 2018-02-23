package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.PosteDao;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Poste;

@Stateless
public class PosteService {
	
	
	
	
	@Inject
	private  PosteDao posteDao;
	
	
	final static Logger logger = Logger.getLogger(MatrizService.class);
	
	public Response registrar(Poste poste){
		logger.info(" metodo : registrar");
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			
			
			if(poste.getDescripcion()==null){
				response =  new Response(Response.ERROR, " El nombre del Poste no es válido");
				return response;
			}
			
			if(poste.getDescripcion()!=null){
				 Poste _poste =  	posteDao.getPoste(poste.getDescripcion());
				 if(_poste!=null){
					 response =  new Response(Response.ERROR, " El nombre del Poste ya existe. Ingrese otro nombre");
					 return response;
				 }
			}
			
			
			int codigoPoste =posteDao.getMax(poste)+1;
			poste.setCodigoPoste(codigoPoste);
			
			posteDao.create(poste);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
	
		return response;
	}
	
	
	public Response modificar(Poste poste){
		
		logger.info(" metodo : modificar");
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			posteDao.update(poste);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
	
		return response;
	}
	
	
	public Response eliminar(Poste poste){
		
		logger.info(" metodo : eliminar");
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{			
			
			posteDao.remove(poste);

		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
		}
	
		return response;
	}


	public List<Poste> getPosteList() {

		return posteDao.getPostesLIst();
		
	}


	public List<Poste> getPosteList(Integer codigoNodo, String criterio) {
	
		return  posteDao.getPostesLIst(codigoNodo, criterio);
		
	}


	public Poste getPoste(Integer codigoPoste) {
		
		return posteDao.getPoste(codigoPoste);
		
	}
	
	
	
	

}
