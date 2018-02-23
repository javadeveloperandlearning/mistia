package pe.com.cablered.mistia.service;

import static pe.com.cablered.mistia.util.ConstantBusiness.DOM;
import static pe.com.cablered.mistia.util.ConstantBusiness.JUE;
import static pe.com.cablered.mistia.util.ConstantBusiness.LUN;
import static pe.com.cablered.mistia.util.ConstantBusiness.MAR;
import static pe.com.cablered.mistia.util.ConstantBusiness.MIE;
import static pe.com.cablered.mistia.util.ConstantBusiness.SAB;
import static pe.com.cablered.mistia.util.ConstantBusiness.VIE;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.HorarioAtencionDetalleDao;
import pe.com.cablered.mistia.model.HorarioAtencionDetalle;

@Stateless
public class HorarioAtencionDetalleService {
	
	
	@Inject
	HorarioAtencionDetalleDao horarioAtencionDetalleDao;

	
	public List<HorarioAtencionDetalle> getHorarioAtencionDetalleList(Integer numeroHorario){
		
		return horarioAtencionDetalleDao.getHorarioAtencionDetalleList(numeroHorario);
	}
	
	
	
	
	public List<Map> getHorarioAtencionList(Integer numeroHorario) {
		List<Map> horarioList  =  new ArrayList<>();
		
		 List<HorarioAtencionDetalle>  horarios  =  horarioAtencionDetalleDao.getHorarioAtencionDetalleList(numeroHorario);
		 for (HorarioAtencionDetalle d : horarios) {				
				horarioList.add(createhorario(d));
		}	
		return horarioList;
	}
	
	
	
	private Map createhorario(HorarioAtencionDetalle d){
		Map mp =  new LinkedHashMap<>();
		mp.put("rango", d.getRango());
		mp.put("nse", d.getId().getNumeroSecuencial());
		mp.put("horainicio", d.getHoraInicio());
		mp.put("horafin", d.getHoraFin());
		Map checkdias = new LinkedHashMap<>();
		for (int i = 1; i <  7 ; i++) {
			checkdias.put(i, false);
		}
		mp.put("checkdias", checkdias);
		return mp;
		
	}
	
	
	
}
