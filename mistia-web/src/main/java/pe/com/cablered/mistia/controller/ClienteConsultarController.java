package pe.com.cablered.mistia.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="clientecon")
@SessionScoped
public class ClienteConsultarController {
	
	
	
	private List<Map> lista;
	
	
	@PostConstruct
	public void init(){
		
	
		
		
		lista =  new ArrayList<>();
		Map map1  =  new HashMap<>();
		map1.put("numerocliente", "6565656");
		map1.put("razonsocial", "La Ganadora SRL");
		map1.put("nombres", "Perez Tello, Juan Manuel");
		map1.put("telefonos", "545454885");
		map1.put("email", "juan.manuel@gmail.com");
		
		Map map2  =  new HashMap<>();
		map2.put("numerocliente", "45485599");
		map2.put("razonsocial", "");
		map2.put("nombres", "Arone Rodriguez, Carla Milagros");
		map2.put("telefonos", "89895986");
		map2.put("email", "Carla.Milagro@gmail.com");
		
		
		
		Map map3  =  new HashMap<>();
		map3.put("numerocliente", "6589898");
		map3.put("razonsocial", "La venturosa S.A ");
		map3.put("nombres", "");
		map3.put("telefonos", "787898959");
		map3.put("email", "contacto@venturosa.com");

		
		
		lista.add(map1);
		lista.add(map2);
		lista.add(map3);
		
		
		
	}


	public List<Map> getLista() {
		return lista;
	}


	public void setLista(List<Map> lista) {
		this.lista = lista;
	}
	
	
	
	
	
	
	
	

}
