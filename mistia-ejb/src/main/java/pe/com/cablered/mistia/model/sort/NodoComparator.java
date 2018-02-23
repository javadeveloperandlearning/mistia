package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.Matriz;
import pe.com.cablered.mistia.model.Nodo;

public class NodoComparator implements Comparator<Nodo> {

	@Override
	public int compare(Nodo o1, Nodo o2) {

		
		if(o1==null || o2==null){
			return 1;
		}
		if( o1.getCodigoNodo()==null || o2.getCodigoNodo()==null){
			return 1;
		}

		return o1.getCodigoNodo().compareTo(o2.getCodigoNodo());
		
	}

}
