package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.Cargo;

public class CargoComparator implements Comparator<Cargo>{

	@Override
	public int compare(Cargo o1, Cargo o2) {
		if(o1==null || o2==null){
			return 1;
		}
		if( o1.getCodigoCargo()==null || o2.getCodigoCargo()==null){
			return 1;
		}
		return o1.getCodigoCargo().compareTo(o2.getCodigoCargo());
	}

}
