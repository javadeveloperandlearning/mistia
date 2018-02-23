package pe.com.cablered.mistia.ia.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.geometria.PuntoSortOrden;
import pe.com.cablered.mistia.geometria.Segmento;

public class Ruta {
	
	
	private List<Segmento> segmentos;
	

	
	public Ruta() {
	
	}

	public List<Segmento> getSegmentos() {
		
		
		if(this.segmentos==null){
			this.segmentos =  new ArrayList<>();
		}
		
		return segmentos;
	}

	public void setSegmentos(List<Segmento> segmentos) {
		

		
		this.segmentos = segmentos;
	} 
		
	
	public  List<Punto> getPuntosList(){

		if(this.segmentos!=null && this.segmentos.size()>0){
			List<Punto> puntosList = Geometria.toListPuntos(segmentos);
			
			// ordenamos los  puntos por el atributo orden
			Collections.sort(puntosList, new PuntoSortOrden());
			
			// reeunumerando
			int orden =  1;
			for (Punto punto : puntosList) {
				punto.setOrden(orden);
				orden++;
			}
			
			
			return puntosList;
		}
		
		return null;
	} 

}

