import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import pe.com.cablered.mistia.ia.clustering.Point;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.util.Util;

public class Test2 {
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		

		
		// 3,2,now(), now(), 50, 1, 'av los rosales 1245', 120108 ,  now(), user,inet_server_addr(), now(),user,inet_server_addr(), 3
		String insert = "insert into solicitud_servicio("
				+ "numero_solicitud ,"
				+ "fecha_solicitud,"
				+ "fecha_atencion,"
				+ "codigo_tipo_solicitud,"
				+ "tarifa_atencion,"
				+ "numero_contrato,"
				+ "codigo_poste,"
				+ "codigo_distrito,"
				+ "codigo_estado, "
				
				+ "fecha_creacion,"
				+ "usuario_creacion,"
				+ "estacion_creacion,"
				+ "fecha_modificacion, "
				+ "usuario_modificacion,"
				+ "estacion_modificacion)";		
		int numero  = 1;
		for (int i = 0; i < 452; i++) {
			
			Calendar cal  = Calendar.getInstance();
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fechaatencion =  sdf.format(cal.getTime());
			Integer codigoTipoSolicitud = Util.randInt(1, 8);
			Integer codigoDistrito =  getDistrito().get(Util.randInt(0, getDistrito().size()-1)).getCodigoDistrito();
			String values  =  "Values("+numero+",current_date,'"+fechaatencion+"',"+codigoTipoSolicitud+",50,"+numero+",1,"+codigoDistrito+",5,now(),user,inet_server_addr(), now(),user,inet_server_addr());";
			
			System.out.println(insert + values);
			
			numero++;
		}
		
	}

	public static List<Distrito> getDistrito(){		
		List<Distrito> distritos =   new ArrayList<>();
		
		distritos.add(new Distrito(120108,"Chongos Alto"));
		distritos.add(new Distrito(120116,"HUACRAPUQUIO"));
		distritos.add(new Distrito(120117,"HUALHUAS"));
		distritos.add(new Distrito(120126,"PUCARA"));
		distritos.add(new Distrito(120128,"QUILCAS"));
		distritos.add(new Distrito(120136,"VIQUES"));

		return distritos;
	}

}
