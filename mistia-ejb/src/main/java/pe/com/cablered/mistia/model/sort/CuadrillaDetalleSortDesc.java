package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.CuadrillasDetalle;

public class CuadrillaDetalleSortDesc implements Comparator<CuadrillasDetalle> {


	@Override
	public int compare(CuadrillasDetalle o1, CuadrillasDetalle o2) {
		
		if(o1==null || o2==null){
			return 1;
		}
		
		if( o1.getId()==null || o2.getId()==null){
			return 1;
		}
		
		if( o1.getId().getNumeroSecuencia()==null || o2.getId().getNumeroSecuencia()==null){
			return 1;
		}
		
		//descendente
		return o2.getId().getNumeroSecuencia().compareTo( o1.getId().getNumeroSecuencia());
	
	}

}
