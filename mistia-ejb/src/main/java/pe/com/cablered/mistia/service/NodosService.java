package pe.com.cablered.mistia.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;


import pe.com.cablered.mistia.dao.NodoDao;
import pe.com.cablered.mistia.dao.PosteDao;
import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.ia.clustering.Cluster;
import pe.com.cablered.mistia.ia.clustering.KMeans2;
import pe.com.cablered.mistia.ia.clustering.Point;
import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Poste;

@Stateless
public class NodosService {
	
	
	@Inject
	private PosteDao posteDao;
	
	@Inject
	private NodoDao nodoDao;
	
	final static Logger logger = Logger.getLogger(NodosService.class);
	
	public Response registrar(Nodo nodo){
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);

		try{
			
			if(nodo.getDescripcion()==null){
				response =  new Response(Response.ERROR, " El nombre del nodo no es válido");
				return response;
			}
			
			if(nodo.getDescripcion()!=null){
				 Nodo _nodo =  	nodoDao.getNodo(nodo.getDescripcion());
				 if(_nodo!=null){
					 response =  new Response(Response.ERROR, " El nombre del nodo ya existe. Ingrese otro nombre");
					 return response;
				 }
			}
			

			int codigoNodo = nodoDao.getMax(nodo)+1;
			nodo.setCodigoNodo(codigoNodo);
			nodoDao.create(nodo);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
	
		return response;
	}
	
	
	public Response modificar(Nodo nodo){
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			if(nodo.getDescripcion()==null){
				response =  new Response(Response.ERROR, " El nombre del nodo no es válido");
				return response;
			}
			
			if(nodo.getDescripcion()!=null){
				 Nodo _nodo =  	nodoDao.getNodo(nodo.getDescripcion());
				 if(_nodo!=null){
					 response =  new Response(Response.ERROR, " El nombre del nodo ya existe. Ingrese otro nombre");
					 return response;
				 }
			}
			
			nodoDao.update(nodo);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
		}
	
		return response;
	}
	
	
	
	public Response eliminar(Nodo nodo){
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		if(nodoDao.hasPostes(nodo)){
			response =  new Response(Response.ERROR, "El nodo tiene postes asociados");
			return response;
		}
		
		
		try{
			nodoDao.remove(nodo);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
	
		return response;
	}
	
	
	
	public List<Map> getcoberuraPostes(){
		
		List<Map> coberturaList =  new ArrayList<>();
		List<Poste> posteList =  posteDao.getPostesLIst();
	
		for (Poste poste : posteList) {
		
			Map map =  new HashMap<>();
			map.put("area", null);
			map.put("descripcion", "nodo");
			map.put("latitudcentral", poste.getLatitud());
			map.put("longitudcentral",poste.getLongitud());
			map.put("radio", 0);
			map.put("color", "#003399");
			/*List<Map> mpposteList=  new ArrayList<>();
			for( Point p :points){
				Map  mpposte = new HashMap<>();
				mpposte.put("latitud", p.getX());
				mpposte.put("longitud", p.getY());
				mpposteList.add(mpposte);
			}
			map.put("postes", mpposteList);
			*/
			coberturaList.add(map);
		}
		
		return coberturaList;
	}
	
	
	
	
	
	
	
	public List<Map> getcoberuraNodos(){
		List<Map> coberturaList =  new ArrayList<>();
		
		
		
		KMeans2  kmean =  new KMeans2();
		kmean.startkpp(90);
		kmean.calculate();
		List<Cluster> _clusters =  kmean.getClusters(); 
		for (Cluster cluster : _clusters) {
			
			
			 List<Point>  points =   cluster.getPoints();
			
			
			
		    Punto[] puntos =  new Punto[points.size()];
		    int i = 0;
		    for (Point p : points) {
		    	 puntos[i] =new Punto(p.getX(), p.getY()); 
		    	 i++;
			}
		    
		    
			Map map =  new HashMap<>();
	
		    
		    //obtener el punto central(latitud, longitud) del grupo
		    Punto puntoCentral = Geometria.puntoCentral(puntos);
		    // obtener el radio máximo del grupo
		    if(puntoCentral!=null){
			    double radio =  Geometria.getRadioMaximo(puntoCentral, puntos);		    
				map.put("area", Geometria.getAreaCirculo(radio));
				map.put("descripcion", "nodo 1");
				map.put("latitudcentral", puntoCentral.getLatitud() );
				map.put("longitudcentral", puntoCentral.getLongitud());
				map.put("radio", radio);
				map.put("color", "#003399");
				
				List<Map> mpposteList=  new ArrayList<>();
				for( Point p :points){
					Map  mpposte = new HashMap<>();
					mpposte.put("latitud", p.getX());
					mpposte.put("longitud", p.getY());
					mpposteList.add(mpposte);
				}
				
				map.put("postes", mpposteList);
		    }
			/*Map map =  new HashMap<>();
			map.put("area", "400");
			map.put("descripcion", "nodo 1");
			map.put("latitudcentral", -12.057825855980015 );
			map.put("longitudcentral", -75.19839901930298);
			map.put("radio", 1066.4659765130987);
			map.put("color", "#003399");
			coberturaList.add(map);*/
			

			coberturaList.add(map);
		
			
		}
		
		

		
		
		
		return coberturaList;
	}


	public List<Nodo> getNodoList() {
		
		return nodoDao.getNodoList();
	}


	public List<Nodo> getNodoList(Integer codigoMatriz, String criterio) {
		
	 	return nodoDao.getNodoList(codigoMatriz, criterio );
	}


	public Nodo getNodo(Integer codigoNodo) {

		return nodoDao.getNodo(codigoNodo);
	}


	public Nodo getNodo(String descripcion) {
		
		return nodoDao.getNodo(descripcion);
	}

	

}
