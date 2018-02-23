package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.ClienteDao;
import pe.com.cablered.mistia.model.Cliente;

@Stateless
@LocalBean
public class ClienteService {
	
	
	@Inject
	private ClienteDao clienteDao;
	
	
	public List<Cliente> clienteList(){
		
		return clienteDao.clienteList();
	}
		
	
	

}
