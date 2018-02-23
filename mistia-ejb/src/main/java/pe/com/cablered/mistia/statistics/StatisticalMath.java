package pe.com.cablered.mistia.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatisticalMath {
	
	
	
	
	public static double getAnchoClase(double min, double max, double cant){
				
		double K = Math.floor(1+3.3 * Math.log10(cant));
		double ancho = (max-min)/K  ;
		return ancho;
		
	}
	
	
	public static List<Limite> getLimites(double min, double max , double ancho){
		
		
		 List<Limite> limites  =  new ArrayList<Limite>();
		 
		 double anchoact = min;	
		
		 while(anchoact<max){
			 
			 Limite limite =  new Limite();
			
			 limite.setInferior(anchoact);
			 
			 anchoact= anchoact+ancho;
			 
			 limite.setSuperior(anchoact);
			
			 limites.add(limite);
			  
		 }	
		 
		 return limites;
	}
	
	
	
	public static List<Frecuencia> getFrecuencias(List<Limite> limites, List<Double> items ){
		
		System.out.println(" #  items"+items.size());
		Collections.sort(items);
		
		List<Frecuencia> frecuencias =  new ArrayList<Frecuencia>();
		int idx  = 1;
		for(Limite limite : limites){
			
			Frecuencia frec =  new Frecuencia(idx);
			frec.setLimite(limite);
			frecuencias.add(frec);
			idx++;
		}
		
		
			
		for(Frecuencia frec : frecuencias){
				
			Limite limite =  frec.getLimite();
			int cantidad  =  0; 
				
			for (Double item : items) {
				if( limite.getInferior()<= item && item<=limite.getSuperior()){
					cantidad++;
					frec.setCantidad(cantidad); 
				}
			}
		
		}
		
		return frecuencias;
	}

	
	
	
	
	
	
	

}
