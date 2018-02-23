package pe.com.cablered.mistia.controller;

import java.util.ArrayList;
import java.util.List;

import pe.com.cablered.mistia.model.Cliente;

public class AbastractSolicitudServicioBean {
	
	
	
	protected List<Cliente> clienteList;
	
	public List<String> completeText(String query) {		
		return buscarCliente(query);
	}
	
	
	
	private List<String> buscarCliente(String criterio){
		
		
		List<String> filtro =  new ArrayList();
	
		for (Cliente cliente : clienteList) {
			String nombre  =  cliente.getApellidos()+" "+cliente.getNombres();
			if(nombre.toUpperCase().startsWith(criterio.trim().toUpperCase())){
				filtro.add(cliente.getApellidos()+" "+cliente.getNombres());
			}
		}
		return filtro;
	}


	
	protected Integer getCodigoCliente(String nombreCliente){
		Integer codigoClient = null; 
		if(nombreCliente!=null){
			for (Cliente cliente : clienteList) {
				String  nomclie =  cliente.getApellidos().trim()+" "+cliente.getNombres().trim();
				if(nomclie.trim().equals(nombreCliente.trim())){
					codigoClient =   cliente.getCodigoCliente();
					break ;
				}
			}
		}
		return codigoClient;
	}

}
