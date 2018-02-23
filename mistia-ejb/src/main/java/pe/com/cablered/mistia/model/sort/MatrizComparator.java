package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.Matriz;

public class MatrizComparator implements Comparator<Matriz> {

	@Override
	public int compare(Matriz o1, Matriz o2) {
		
		if(o1==null || o2==null){
			return 1;
		}
		if( o1.getCodigoMatriz()==null || o2.getCodigoMatriz()==null){
			return 1;
		}

		return o1.getCodigoMatriz().compareTo(o2.getCodigoMatriz());
	}

}
