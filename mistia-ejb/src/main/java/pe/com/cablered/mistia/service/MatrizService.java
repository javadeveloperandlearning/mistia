package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.MatrizDao;
import pe.com.cablered.mistia.model.Matriz;


@Stateless
public class MatrizService {
		
	@Inject
	private  MatrizDao matrizDao;
	
	
	final static Logger logger = Logger.getLogger(MatrizService.class);
	
	public Response registrar(Matriz matriz){
		logger.info(" metodo : registrar");
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			matrizDao.create(matriz);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
	
		return response;
	}
	
	
	public Response modificar(Matriz matriz){
		
		logger.info(" metodo : modificar");
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			matrizDao.update(matriz);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
	
		return response;
	}
	
	
	public Response eliminar(Matriz matriz){
		
		logger.info(" metodo : eliminar");
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			matrizDao.remove(matriz);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
	
		return response;
	}


	public Matriz getMatriz(String  descripcion) {
		
		return matrizDao.getMatriz(descripcion);
	}


	public List<Matriz> getMatrizList() {

		return  matrizDao.getMatrizList();
	}


}
