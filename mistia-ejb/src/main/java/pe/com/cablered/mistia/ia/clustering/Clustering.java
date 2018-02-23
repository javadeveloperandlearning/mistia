package pe.com.cablered.mistia.ia.clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.geometria.Segmento;
import pe.com.cablered.mistia.statistics.Frecuencia;
import pe.com.cablered.mistia.statistics.FrecuenciaComparator1;
import pe.com.cablered.mistia.statistics.Limite;
import pe.com.cablered.mistia.statistics.StatisticalMath;

public class Clustering {
	
	
	
	
	private void  mostrarSegmentos(List<Segmento> segmentos){
		
		for (Segmento s : segmentos) {
			System.out.println(" distancia :"+s.getDistancia());
		}
		
	}
	
	private void  mostrarFrecuencias(List<Frecuencia> frecuencias){
		
		int cant = 0;
		for (Frecuencia f : frecuencias) {
			System.out.println(" distancia :"+f.toString());
			
			if(f.getCantidad()!=null){
				cant=cant+f.getCantidad();
			}
		}
		System.out.println("cantidad :"+cant);
		
	}
	
	private void mostrarLimites(List<Limite> limites ){
	
		for (Limite l : limites) {
			System.out.println(" ### "+l.toString());
		}
	}
	 
	
	
	
	/***
	 * getClusters genera cluster de puntos geográficos en base al costo promedio que debe tener cada 
	 * uno de ellos
	 * 
	 * 
	 * */
	public  List<Map<Integer,Punto>>  getClusters(List<Punto> puntoList, Double costoPromedioCluster, Integer cantcluster){
		
		
		// formando segmentos
		List<Segmento> segmentos = new ArrayList<Segmento>();

		// todos contra todos
		for (Punto p1 : puntoList) {
			for (Punto p2 : puntoList) {
				if( (p1!=null && p2!=null) && !p1.equals(p2)  ){
					double d =  Geometria.distanciaDeCoordenadas(p1, p2);
					segmentos.add(new Segmento(p1, p2, d));
					
				}
			}
		}
		
		System.out.println(" # de segmentos : "+segmentos.size());
		// ordenamos los segementos
		Collections.sort(segmentos);
		//mostrarSegmentos(segmentos);
		
		
		//######  aplicando estadistica 
		
		//hallamos el ancho de clase de la distancia de los segmentos
		
		double min  =  segmentos.get(0).getDistancia();
		
		double max  =  segmentos.get(segmentos.size()-1).getDistancia();
		
		double ancho = StatisticalMath.getAnchoClase(min, max, segmentos.size());
		
		System.out.println("min : "+min);
		System.out.println("max : "+max);
		System.out.println("ancho : "+ancho);
		
		

		List<Limite> limites =   StatisticalMath.getLimites(min, max, ancho);
		
		List<Double> items =  new ArrayList<Double>();
		for(Segmento s : segmentos){
			items.add(s.getDistancia());
		}
		
		List<Frecuencia> frecuencias =  StatisticalMath.getFrecuencias(limites, items);
		
		// ordenando frecuencias
		Collections.sort(frecuencias, new FrecuenciaComparator1());
		
		mostrarFrecuencias(frecuencias);
		

		double meddist =  0 ;
		
		if(frecuencias!=null && frecuencias.size()>0){
			 meddist =  frecuencias.get(frecuencias.size()-1).getLimite().getSuperior();
		}
		
		System.out.println(" distancia ideal para formar grupos "+meddist);
		
		// finalizando esatidistica
		
	
		// fitramos hasta la distancia media para obetner los segmentos candidatos para los grupos
		List<Segmento> segmentocandidatos= new ArrayList<Segmento>();
	
		int idx = 0;
		for (Segmento s : segmentos) {
			if(s.getDistancia().intValue()<=meddist){
				segmentocandidatos.add(s);
				idx++;
			}
		}
		//
	
		//nos aseguramos que todos los puntos esten considerados
		List<Punto>  puntoscandidatos = Geometria.toListPuntos(segmentocandidatos);

		System.out.println(" segmentos candidatos procesados");
		//
		
		//cálculo de la cantidad promedio de grupos en base el tiempo
		Double costototal = 0.0; 
		for (Punto punto : puntoscandidatos) {
			costototal = costototal+punto.getPeso();
		}
		
		System.out.println("costo total "+costototal);
		
		
		
		
		if(cantcluster==null  || cantcluster==0){
			Double dcantcluster= costototal/costoPromedioCluster ;
			cantcluster =  (int)Math.ceil(dcantcluster);
		}else{			
			// forzando cantidad de cluster
			costoPromedioCluster = costototal/cantcluster;
		}
		System.out.println(" cantidad de cluster sugeridos "+cantcluster);
		
		System.out.println(" distancia promedio entre puntos:"+meddist );

		
		
		// creamos los clusters según datos estadisticos y pesos de los puntos 
		// para este caso el tiempo
		List<Map<Integer,Punto>> clusters =   new ArrayList<>();
		Map <Integer,Punto> mpts = new HashMap<Integer, Punto>();  
		clusters.add(mpts);
		for (Punto p1 : puntoList) {
			
			for (Punto p2 : puntoList) {

				if(!p1.getNumero().equals(p2.getNumero()) ){
					double d  = Geometria.distanciaDeCoordenadas(p1, p2);
				
					if(d<=meddist){
						
						Punto punto =  p2;
						boolean found = false;
						for (Map<Integer, Punto> map : clusters) {
							  Punto _punto =  map.get(punto.getNumero());
							  if(_punto!=null){
								  found = true;
							  }
						}
						// seleccion de punto para el cluster 
						if(!found){	
							if(getCosto(mpts)>=costoPromedioCluster){
								mpts = new HashMap<Integer, Punto>();  
								mpts.put(punto.getNumero(), punto);
								clusters.add(mpts);
							}else{

								Map<Integer, Punto>  _mpts  = clusters.get(clusters.size()-1);
								_mpts.put(punto.getNumero(), punto);

							}
						}
					}
				}
			}
		}
		
		
		//creamos los centroides
		List<Punto> centroides =  new ArrayList<>();
		List<Cluster> _clusters =  new ArrayList<>();
		int j = 0;
		for (Map<Integer, Punto> map : clusters) {
			Iterator<Integer> it =  map.keySet().iterator();
			
			Punto[] puntosArray =  new Punto[map.size()];
			map.values().toArray(puntosArray);
			Punto puntocentral = Geometria.puntoCentral(puntosArray);
			centroides.add(puntocentral);
			//libreria kmeans
			Cluster c  =  new Cluster(j);
			Point pc=  new Point(puntocentral.getLatitud(), puntocentral.getLongitud());
			//System.out.println("centroide " +pc.toString());
			c.setCentroid(pc);
			List<Point> points =  new ArrayList<>();
			
			double ct =  0.0;
			while(it.hasNext()){
				Integer i =  it.next();
				Punto punto =  map.get(i);				
				//libreria kmeans
				
				Point point =  new Point(punto.getLatitud(), punto.getLongitud());
				//System.out.println(" point : "+point.toString());
				points.add(point);
			}
			j++;
			c.setPoints(points);
			_clusters.add(c);
		}
		
		
		
		System.out.println("Aplicando algoritmo kmeans");	
		List<Point> points = new ArrayList<>();
		for(Punto p : puntoList){
			points.add( new Point(p.getNumero(),p.getLatitud(),p.getLongitud()));
		}	
		KMeans2  kmean =  new KMeans2();
		kmean.NUM_CLUSTERS  =  _clusters.size();
		kmean.setClusters(_clusters);
		kmean.setPoints(points);
		kmean.calculate();
		_clusters =  kmean.getClusters(); 
		
		
		System.out.println("Aplicando formato ");
		// pasando a formato		
	    clusters =   new ArrayList<>();
		for (Cluster cluster : _clusters) {
			Map<Integer,Punto> mp =  new HashMap<Integer, Punto>();
			for(Point p : cluster.getPoints()){
				//System.out.println("punto kmean "+p.toString());
				mp.put(  p.getNumber(), new Punto(p.getNumber(),p.getX(), p.getY(),0.0   ));
				
			}
			clusters.add(mp);
			
		}


		System.out.println("enviando clusters ");

		return clusters;
	}
	
	
	
	private List<Map<Integer,Punto>>   addCluster(List<Map<Integer,Punto>> clusters ,Map <Integer,Punto> mpts ,Punto punto, Double costoPromedioCluster){
		
		// validar que el punto no este en el cluster
		//System.out.println(" clusters size :"+clusters.size());
		boolean found = false;
		for (Map<Integer, Punto> map : clusters) {
			  Punto _punto =  map.get(punto.getNumero());
			  
			  if(_punto!=null){
				  //System.out.println(" found : "+_punto.toString());
				  found = true;
				  //break;
			  }
			
		}
		
		if(!found){	
			System.out.println((getCosto(mpts)));
			if(getCosto(mpts)>=costoPromedioCluster){
				mpts = new HashMap<Integer, Punto>();  
				clusters.add(mpts);
			}else{
				System.out.println(" valor :"+punto);
				if(clusters.size()>0){
					Map _mpts  = clusters.get(clusters.size()-1);
					_mpts.put(punto.getNumero(), punto);
					//clusters.add(mpts);
				}
			}
		}
		
		return clusters;
	}
	
	
	
	
	
	
	public double getCosto(Map <Integer,Punto> mpts ){
		
		double costo = 0.0;
		Iterator<Integer> it =   mpts.keySet().iterator();
		while(it.hasNext()){
			Integer i =  it.next();
			Punto p =  mpts.get(i);
			costo = costo+p.getPeso();
		}
		
		return costo;
		
	} 
	

	
	
	
	
	
}
