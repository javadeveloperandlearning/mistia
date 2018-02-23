package pe.com.cablered.mistia.geometria;

import java.util.Comparator;

public class PuntoSortOrden implements Comparator<Punto>{

	@Override
	public int compare(Punto o1, Punto o2) {

		return  o1.getOrden().compareTo(o2.getOrden());
		
	}
	
	
}