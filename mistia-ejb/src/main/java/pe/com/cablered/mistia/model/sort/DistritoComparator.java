package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.Distrito;

public class DistritoComparator implements  Comparator<Distrito> {

	@Override
	public int compare(Distrito o1, Distrito o2) {
		
		if(o1==null || o2==null){
			return 1;
		}
		if( o1.getCodigoDistrito()==null || o2.getCodigoDistrito()==null){
			return 1;
		}
		return o1.getCodigoDistrito().compareTo(o2.getCodigoDistrito());
		
	}

}
