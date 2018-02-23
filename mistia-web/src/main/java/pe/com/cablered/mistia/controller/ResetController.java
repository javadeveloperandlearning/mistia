package pe.com.cablered.mistia.controller;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.GeneralDao;




@ManagedBean(name="resetbean")
@RequestScoped
@Stateless
public class ResetController {
	
	
	@Inject
	GeneralDao generalDao;
	
	
	public void reset(){
		
		generalDao.reset();
		
	}
	

}
