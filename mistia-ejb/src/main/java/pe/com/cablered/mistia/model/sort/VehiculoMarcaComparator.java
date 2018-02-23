package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.VehiculoMarca;

public class VehiculoMarcaComparator implements Comparator<VehiculoMarca> {

	
	
	@Override
	public int compare(VehiculoMarca o1, VehiculoMarca o2) {
		if(o1==null || o2==null){
			return 1;
		}
		if( o1.getCodigoMarca()==null || o2.getCodigoMarca()==null){
			return 1;
		}

		return o1.getCodigoMarca().compareTo(o2.getCodigoMarca());
	}
	
	
	

}
