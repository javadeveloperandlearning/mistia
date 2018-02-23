package pe.com.cablered.mistia.statistics;

import java.util.Comparator;

public class FrecuenciaComparator1 implements Comparator<Frecuencia>{
	

	@Override
	public int compare(Frecuencia o1, Frecuencia o2) {
	
		if(o1==null || o2==null){
			return 0;
		}
		
		if(o1.getCantidad()==null || o2.getCantidad()==null){
			return 0;
		}
		
		return  o1.getCantidad().compareTo(o2.getCantidad());
	}
	
}
