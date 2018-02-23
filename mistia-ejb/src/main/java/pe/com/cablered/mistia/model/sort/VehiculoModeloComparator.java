package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.VehiculoModelo;

public class VehiculoModeloComparator implements Comparator<VehiculoModelo> {

	
	
	@Override
	public int compare(VehiculoModelo o1, VehiculoModelo o2) {
		if(o1==null || o2==null){
			return 1;
		}
		if( o1.getCodigoModelo()==null || o2.getCodigoModelo()==null){
			return 1;
		}

		return o1.getCodigoModelo().compareTo(o2.getCodigoModelo());
	}

}
