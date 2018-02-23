package pe.com.cablered.mistia.ia.tsp;

import java.util.Comparator;
import java.util.Map;

public class SubRutaComparator implements Comparator<Map> {
	
	
	

	@Override
	public int compare(Map o1, Map o2) {
		   
		// dmin distancia minima
		Double min1 =  (Double)o1.get("dmin");
		Double min2 =  (Double)o2.get("dmin");
		return min1.compareTo(min2);
		
		
	}

}
