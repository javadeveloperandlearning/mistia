package pe.com.cablered.mistia.service;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.dao.CargoDao;
import pe.com.cablered.mistia.model.Cargo;
import pe.com.cablered.mistia.model.Tecnico;


@Stateless
public class CargoService {

	
	@Inject
	private  CargoDao cargoDao;
	
	
	public List<Cargo> getCargoList() {
		return cargoDao.getCargoList();
	}


	public Cargo getCargo(String descripcion) {

		return cargoDao.getCargo(descripcion);
	}

	
}
