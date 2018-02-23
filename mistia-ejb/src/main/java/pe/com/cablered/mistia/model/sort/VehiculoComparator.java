package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.Vehiculo;


public class VehiculoComparator implements Comparator<Vehiculo>{

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		if(o1==null || o2==null){
			return 1;
		}
		if( o1.getPlacaVehiculo()==null || o2.getPlacaVehiculo()==null){
			return 1;
		}

		return o1.getPlacaVehiculo().compareTo(o2.getPlacaVehiculo());
	}
	

}
