package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.GrupoAtencionDetalle;

public class GrupoAtencionDetalleSortPrioridad implements Comparator<GrupoAtencionDetalle> {
	
	
	

	@Override
	public int compare(GrupoAtencionDetalle o1, GrupoAtencionDetalle o2) {
		
		if(o1.getGradoPrioridad()==null || o2.getGradoPrioridad() ==null){
			
			return 0;
		}

		return o1.getGradoPrioridad().compareTo(o2.getGradoPrioridad());
	}
	
	

}
