package pe.com.cablered.mistia.ia.tsp;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import pe.com.cablered.mistia.geometria.Punto;

public class PuntoHeuristicaComparator1 implements Comparator<Punto>{
	
	
	

	@Override
	public int compare(Punto o1, Punto o2) {
		
		
		Map mp1  =  o1.getDatos();
		Map mp2  =  o2.getDatos();
		
		if(mp1==null || mp2==null){
			return  0;
		}
	
		Long tiempoat1= (Long) mp1.get("tiempoat"); // tiempo atencion
		Integer priorida1= (Integer) mp1.get("priorida"); // prioridad
		Integer urgencia1= (Integer) mp1.get("urgencia"); // urgencia
		Double distanci1= (Double) mp1.get("distanci"); // distancia
		
		Long tiempoat2= (Long) mp2.get("tiempoat"); // tiempo atencion
		Integer priorida2= (Integer) mp2.get("priorida"); // prioridad
		Integer urgencia2= (Integer) mp2.get("urgencia"); // urgencia
		Double distanci2= (Double) mp2.get("distanci"); // distancia
		
		int i = 0;
		
		if(tiempoat1==null || tiempoat2==null || priorida1==null || priorida2==null || urgencia1==null || urgencia2==null || distanci1==null || distanci2==null){
			return 0;
		}
		
		i =  tiempoat1.compareTo(tiempoat2);
		if(i!=0) return i;

		i =  priorida1.compareTo(priorida2);
		if(i!=0) return i;


		i =  urgencia1.compareTo(urgencia2);
		if(i!=0) return i;
		
		return  distanci1.compareTo(distanci2);

	}
	
	
	
	

}
