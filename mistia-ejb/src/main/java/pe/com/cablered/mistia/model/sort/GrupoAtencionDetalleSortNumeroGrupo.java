package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;

import pe.com.cablered.mistia.model.GrupoAtencionDetalle;

public class GrupoAtencionDetalleSortNumeroGrupo implements Comparator<GrupoAtencionDetalle> {

	@Override
	public int compare(GrupoAtencionDetalle o1, GrupoAtencionDetalle o2) {
		
		
		if(o1.getGrupoAtencion() ==null || o2.getGrupoAtencion()==null){
			return 0;
		}
		

		return (int) (o1.getGrupoAtencion().getNumeroGrupoAtencion() - o2.getGrupoAtencion().getNumeroGrupoAtencion());
	}

}
