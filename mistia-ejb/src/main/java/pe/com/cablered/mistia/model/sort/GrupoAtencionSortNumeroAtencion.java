package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.GrupoAtencion;

public class GrupoAtencionSortNumeroAtencion implements Comparator<GrupoAtencion>{
	
	

	@Override
	public int compare(GrupoAtencion o1, GrupoAtencion o2) {
		
		
		if(o1==null || o2==null){
		 return 0;
		}
		// TODO Auto-generated method stub
		return  (int) (o1.getNumeroGrupoAtencion() -  o2.getNumeroGrupoAtencion());
	}

}
